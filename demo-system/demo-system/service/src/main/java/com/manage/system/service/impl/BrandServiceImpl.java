package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Brand;
import com.manage.system.dao.BrandMapper;
import com.manage.system.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wucc
 * @date 2019/9/6 13:16
 */
@Slf4j
@Component
@Transactional
public class BrandServiceImpl extends AbstractService<Brand, Integer, BrandMapper> implements BrandService {
    @Override
    public IPage<Brand> findPage(Brand entity) {
        QueryWrapper<Brand> wrapper = getWrapper(entity);
        IPage<Brand> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    private QueryWrapper getWrapper(Brand entity) {
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", 0);
        if (entity.getId() != null) {
            wrapper.eq("id", entity.getId());
        }
        if(StringUtils.isNotBlank(entity.getName())) {
            wrapper.like("name", entity.getName());
        }
        wrapper.orderByAsc("create_time");
        return wrapper;
    }
}
