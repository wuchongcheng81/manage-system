package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.InformationVisitRecord;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/18 23:30
 */
public interface InformationVisitRecordService extends BaseService<InformationVisitRecord , Integer> {
    List<InformationVisitRecord> findCurrentDay(String requestIp, int informationId);
}
