package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BrandPopularRecord;
import com.manage.system.dao.BrandPopularRecordMapper;
import com.manage.system.service.BrandPopularRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wucc
 * @date 2019/9/10 15:22
 */
@Slf4j
@Component
@Transactional
public class BrandPopularRecordServiceImpl extends AbstractService<BrandPopularRecord, Integer, BrandPopularRecordMapper> implements BrandPopularRecordService {
    @Override
    public IPage<BrandPopularRecord> findPage(BrandPopularRecord entity) {
        return null;
    }

    @Override
    public int save(BrandPopularRecord entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public List<BrandPopularRecord> findList(BrandPopularRecord entity) {
        QueryWrapper wrapper = getWrapper(entity);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<BrandPopularRecord> findTodayList(int brandId, String requestIp, String type) {
        return mapper.findTodayList(brandId, requestIp, type);
    }

    private QueryWrapper getWrapper(BrandPopularRecord entity) {
        QueryWrapper<BrandPopularRecord> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(entity.getRequestIp())) {
            wrapper.eq("request_ip", entity.getRequestIp());
        }
        if(StringUtils.isNotBlank(entity.getType())) {
            wrapper.eq("type", entity.getType());
        }
        if(entity.getBrandId() != null) {
            wrapper.eq("brand_id", entity.getBrandId());
        }
        return wrapper;
    }
}
