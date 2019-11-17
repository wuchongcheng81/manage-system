package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BrandShareRecord;
import com.manage.system.dao.BrandShareRecordMapper;
import com.manage.system.dto.CountDetailDTO;
import com.manage.system.service.BrandShareRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/17 21:02
 */
@Slf4j
@Component
@Transactional
public class BrandShareRecordServiceImpl extends AbstractService<BrandShareRecord, Integer, BrandShareRecordMapper> implements BrandShareRecordService {



    @Override
    public IPage<BrandShareRecord> findPage(BrandShareRecord entity) {
        return null;
    }

    @Override
    public int countShare(Integer brandId) {
        return mapper.countShare(brandId);
    }

    @Override
    public int countYesterDayShare(Integer brandId) {
        return mapper.countYesterDayShare(brandId);
    }

    @Override
    public List<CountDetailDTO> countShareByBeforeDay(String beforeDay, Integer brandId) {
        return mapper.countShareByBeforeDay(beforeDay, brandId);
    }
}
