package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BrandVisitRecord;
import com.manage.system.dao.BrandVisitRecordMapper;
import com.manage.system.dto.CountDetailDTO;
import com.manage.system.service.BrandVisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/24 20:59
 */
@Slf4j
@Component
@Transactional
public class BrandVisitRecordServiceImpl extends AbstractService<BrandVisitRecord, Integer, BrandVisitRecordMapper> implements BrandVisitRecordService
{
    @Override
    public IPage<BrandVisitRecord> findPage(BrandVisitRecord entity) {
        return null;
    }


    @Override
    public int countBrowse(Integer brandId) {
        return mapper.countBrowse(brandId);
    }

    @Override
    public int countYesterDayBrowse(Integer brandId) {
        return mapper.countYesterDayBrowse(brandId);
    }

    @Override
    public List<CountDetailDTO> countBrowseByBeforeDay(String beforeDay, String endDay, Integer brandId) {
        return mapper.countBrowseByBeforeDay(beforeDay, endDay, brandId);
    }
}
