package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
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
import java.util.stream.Collectors;

@Slf4j
@Component
@Transactional
public class TypeServiceImpl extends AbstractService<Type, Integer, TypeMapper> implements TypeService {

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
    public List<Type> findAll(Type type) {
        QueryWrapper wrapper = getWrapper(type);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Type> findAllWithBrandCount() {
        QueryWrapper wrapper = getWrapper(null);
        List<Type> list = mapper.selectList(wrapper);

        if(!CollectionUtils.isEmpty(list)) {
            List<Type> parentList = list.stream().filter(t -> t.getParentId() == 0).collect(Collectors.toList());
            list.removeAll(parentList);
            List<Type> chileList = Lists.newArrayList(list);

            chileList.forEach(t ->
                t.setBrandCount(brandService.countByTypeId(t.getId()))
            );

            parentList.forEach(p ->
                chileList.forEach(c -> {
                    if(c.getParentId() == p.getId()) {
                        p.setBrandCount(p.getBrandCount()+c.getBrandCount());
                    }
                })
            );
            parentList.addAll(chileList);
            return parentList;
        }
        return null;
    }

    @Override
    public List<Type> findAllParent() {
        Type type = new Type();
        type.setParentId(0);
        QueryWrapper wrapper = getWrapper(type);
        return mapper.selectList(wrapper);
    }

    @Override
    public Integer findListByParentId(int parentId) {
        Type type = new Type();
        type.setParentId(parentId);
        QueryWrapper wrapper = getWrapper(type);

        return mapper.selectCount(wrapper);
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
            if(entity.getParentId() != null) {
                if(entity.getParentId() == -1) {
                    wrapper.ne("parent_id", 0);
                }else {
                    wrapper.eq("parent_id", entity.getParentId());
                }
            }
        }
        wrapper.orderByAsc("sort");
        return wrapper;
    }
}
