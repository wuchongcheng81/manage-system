package com.manage.system.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author Eason.Zhang
 * @date 2019/5/6
 * @email vipzhsh@163.com
 * @description
 */
public abstract class AbstractService<T, ID extends Serializable, M extends BaseMapper<T>>  implements BaseService<T, ID>{

    protected M mapper;

    @Autowired
    @Qualifier("pluginSqlSessionFactory")
    protected SqlSessionFactory sqlSessionFactory;

    @Autowired
    @Qualifier("pluginSqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;


    @PostConstruct
    public void init() {
        this.addMapper();
    }

    /**
     * A<D,T,M> extends AbstractService<T, M extends BaseMapper<T>>
     * Class<M> clazz = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
     * 此时getActualTypeArguments()[1]拿不到AbstractService<T, M extends BaseMapper<T>> 中的第1个泛型，因为此时的getClass = A 而不是AbstractService
     */
    protected void addMapper() {
        // To get genetic param M's Class
        Class<M> clazz = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        mapper = sqlSessionFactory.getConfiguration().getMapper(clazz, sqlSessionTemplate);
    }


    protected String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    protected String combine(Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : collection) {
            sb.append(obj.toString()).append("|");
        }
        return sb.toString();
    }

    protected Collection extract(String str) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        // '|' is a special character in java
        String[] items = str.split("\\|");
        Collection collection = new HashSet();
        collection.addAll(Arrays.asList(items));
        return collection;
    }


    public int save(T entity) {
        return this.mapper.insert(entity);
    }

    public int update(T entity) {
        return this.mapper.updateById(entity);
    }

    public int delete(ID id) {
        return this.mapper.deleteById(id);
    }

    public int deleteByIds(ID[] ids) {
        return this.mapper.deleteBatchIds(Arrays.asList(ids));
    }

    public T findById(ID id) {
        return this.mapper.selectById(id);
    }
}
