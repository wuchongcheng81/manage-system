package com.manage.system.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.util.CollectionUtils;
import javax.sql.DataSource;
import java.util.Set;


/**
 * @author wucc
 * @date 2019/8/26 17:37
 */
@Configuration
@Slf4j
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean("pluginConfiguration")
    public MybatisConfiguration mybatisConfiguration() throws ClassNotFoundException {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.addInterceptor(new PerformanceInterceptor());
        mybatisConfiguration.addInterceptor(new PaginationInterceptor());
        mybatisConfiguration.setLogImpl(Log4j2Impl.class);
        addMapper(mybatisConfiguration);
        return mybatisConfiguration;
    }

    private void addMapper(MybatisConfiguration mybatisConfiguration) throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider = newProvider();
        provider.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            ClassMetadata metadata = metadataReader.getClassMetadata();
            if (!metadata.isInterface()) {
                return false;
            }
            String[] names = metadata.getInterfaceNames();
            return Sets.newHashSet(names).contains(BaseMapper.class.getName());
        });
        Set<BeanDefinition> definitions = provider.findCandidateComponents("com.manage.system.dao");
        if (CollectionUtils.isEmpty(definitions)) {
            throw new IllegalStateException("Mappers can not be null, please check!");
        }

        for (BeanDefinition definition : definitions) {
            String beanName = definition.getBeanClassName();
            log.info("find mapper : {}", beanName);
            Class<? extends BaseMapper> mapper = (Class<? extends BaseMapper>) Class.forName(beanName);
            mybatisConfiguration.addMapper(mapper);
        }
    }

    ClassPathScanningCandidateComponentProvider newProvider() {
        return new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return true;
            }
        };
    }

    @Bean("pluginSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("pluginConfiguration") MybatisConfiguration mybatisConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] xmlMappers = resolver.getResources("classpath*:mybatis/mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(xmlMappers);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("pluginSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Autowired @Qualifier("pluginSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
