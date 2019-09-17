package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Type;
import com.manage.system.dao.BrandMapper;
import com.manage.system.dao.TypeMapper;
import com.manage.system.service.BrandService;
import com.manage.system.service.PhotoService;
import com.manage.system.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
@Transactional
public class TypeServiceImpl extends AbstractService<Type, Integer, TypeMapper> implements TypeService {

    @Resource
    private PhotoService photoService;
    @Resource
    private BrandService brandService;

    @Override
    public IPage<Type> findPage(Type entity) {
        QueryWrapper<Type> wrapper = getWrapper(entity);
        IPage<Type> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        if(result != null && !CollectionUtils.isEmpty(result.getRecords())) {
            result.getRecords().forEach(
                t -> {
                    t.setBrandCount(brandService.countByTypeId(t.getId()));
                }
            );
        }
        return result;
    }

    @Override
    public int delete(Integer id) {
        Type type = new Type();
        type.setId(id);
        type.setIsDel(1);
        return mapper.updateById(type);
    }

    @Override
    public List<Type> findAll() {
        QueryWrapper wrapper = getWrapper(null);
        return mapper.selectList(wrapper);
    }

    private QueryWrapper getWrapper(Type entity) {
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", 0);
        if(entity != null) {
            if (entity.getId() != null) {
                wrapper.eq("id", entity.getId());
            }
            if (StringUtils.isNotBlank(entity.getName())) {
                wrapper.like("name", entity.getName());
            }
        }
        wrapper.orderByAsc("sort");
        return wrapper;
    }
}
