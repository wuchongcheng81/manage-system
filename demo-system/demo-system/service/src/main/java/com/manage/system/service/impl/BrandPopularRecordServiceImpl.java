package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BrandPopularRecord;
import com.manage.system.dao.BrandPopularRecordMapper;
import com.manage.system.service.BrandPopularRecordService;

import java.util.Date;

/**
 * @author wucc
 * @date 2019/9/10 15:22
 */
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
}
