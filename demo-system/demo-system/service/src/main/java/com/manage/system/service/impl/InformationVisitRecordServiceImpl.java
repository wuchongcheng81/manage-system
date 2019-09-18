package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.InformationVisitRecord;
import com.manage.system.dao.InformationVisitRecordMapper;
import com.manage.system.service.InformationVisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/18 23:30
 */
@Slf4j
@Component
@Transactional
public class InformationVisitRecordServiceImpl extends AbstractService<InformationVisitRecord, Integer, InformationVisitRecordMapper> implements InformationVisitRecordService {

    @Override
    public IPage<InformationVisitRecord> findPage(InformationVisitRecord entity) {
        return null;
    }

    @Override
    public List<InformationVisitRecord> findCurrentDay(String requestIp, int informationId) {
        return mapper.findCurrentDay(requestIp, informationId);
    }
}
