package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Advertisement;
import com.manage.system.dao.AdvertisementMapper;
import com.manage.system.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@Transactional
public class AdvertisementServiceImpl extends AbstractService<Advertisement, Integer, AdvertisementMapper> implements AdvertisementService {
    @Override
    public IPage<Advertisement> findPage(Advertisement entity) {
        QueryWrapper<Advertisement> wrapper = getWrapper(entity);
        IPage<Advertisement> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    private QueryWrapper getWrapper(Advertisement entity) {
        QueryWrapper<Advertisement> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", 0);
        if(entity.getId() != null) {
            wrapper.eq("id", entity.getId());
        }
        wrapper.orderByAsc("sort");
        return wrapper;
    }
}
