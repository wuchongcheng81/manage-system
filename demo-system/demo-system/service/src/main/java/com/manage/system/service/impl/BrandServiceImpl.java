package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Brand;
import com.manage.system.dao.BrandMapper;
import com.manage.system.dto.BrandDTO;
import com.manage.system.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Override
    public int save(Brand entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public Brand findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public List<Brand> findList(Brand entity) {
        QueryWrapper<Brand> wrapper = getWrapper(entity);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<BrandDTO> findPopularList() {
        return mapper.findPopularList();
    }

    @Override
    public List<BrandDTO> findWeekPopular(Integer pageNumber, Integer pageSize) {
        if(pageNumber == null)
            pageNumber = 0;
        if(pageSize == null)
            pageSize = 5;
        return mapper.findWeekPopular(pageNumber, pageSize);
    }

    @Override
    public List<BrandDTO> findMonthPopular(Integer pageNumber, Integer pageSize) {
        if(pageNumber == null)
            pageNumber = 0;
        if(pageSize == null)
            pageSize = 5;
        return mapper.findMonthPopular(pageNumber, pageSize);
    }

    @Override
    public List<BrandDTO> findAllPopular(Integer pageNumber, Integer pageSize) {
        if(pageNumber == null)
            pageNumber = 0;
        if(pageSize == null)
            pageSize = 5;
        return mapper.findAllPopular(pageNumber, pageSize);
    }

    @Override
    public List<BrandDTO> findRandom() {
        return mapper.findRandom();
    }

    @Override
    public List<BrandDTO> findPageByTypeId(Integer pageNumber, Integer pageSize, Integer typeId) {
        if(pageNumber == null)
            pageNumber = 0;
        if(pageSize == null)
            pageSize = 5;
        return mapper.findPageByTypeId(pageNumber, pageSize, typeId);
    }

    @Override
    public List<BrandDTO> findAll(Integer typeId) {
        return mapper.findAll(typeId);
    }

    @Override
    public int countByTypeId(int typeId) {
        return mapper.countByTypeId(typeId);
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
        if(entity.getTypeId() != null) {
            wrapper.eq("type_id", entity.getTypeId());
        }
        wrapper.orderByAsc("create_time");
        return wrapper;
    }
}
