package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BrandInvest;
import com.manage.system.dao.BrandInvestMapper;
import com.manage.system.service.BrandInvestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wucc
 * @date 2019/9/20 17:50
 */
@Slf4j
@Component
@Transactional
public class BrandInvestServiceImpl extends AbstractService<BrandInvest, Integer, BrandInvestMapper> implements BrandInvestService {

    @Override
    public int save(BrandInvest entity) {
        Date now = new Date();
        entity.setCreateTime(now);
        if(entity.getExpireDateTimeStamp() != null) {
            entity.setExpireDate(new Date(entity.getExpireDateTimeStamp()));
        }
        if(entity.getIsTop() == null || entity.getIsTop() != 1) {
            entity.setTopSort(0);
        }
        if(entity.getIsPublish() == 1) {
            entity.setPublishTime(now);
        }
        return super.save(entity);
    }

    @Override
    public int update(BrandInvest entity) {
        if(entity.getExpireDateTimeStamp() != null) {
            entity.setExpireDate(new Date(entity.getExpireDateTimeStamp()));
        }
        if(entity.getIsTop() == null || entity.getIsTop() != 1) {
            entity.setTopSort(0);
        }
        BrandInvest e = findById(entity.getId());
        if((e.getIsPublish() == null || e.getIsPublish() != 1) && entity.getIsPublish() == 1) {
            entity.setPublishTime(new Date());
        }

        return super.update(entity);
    }

    @Override
    public IPage<BrandInvest> findPage(BrandInvest entity) {
        QueryWrapper<BrandInvest> wrapper = getWrapper(entity);
        IPage<BrandInvest> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    @Override
    public List<BrandInvest> findList(Integer pageNumber, Integer pageSize) {
        if(pageNumber == null)
            pageNumber = 0;
        if(pageSize == null)
            pageSize = 5;
        pageNumber = setPageNumber(pageNumber, pageSize);
        return mapper.findList(pageNumber, pageSize);
    }

    private QueryWrapper getWrapper(BrandInvest entity) {
        QueryWrapper<BrandInvest> wrapper = new QueryWrapper<>();
        if (entity.getId() != null) {
            wrapper.eq("id", entity.getId());
        }
        if(StringUtils.isNotBlank(entity.getTitle())) {
            wrapper.like("title", entity.getTitle());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

}
