package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.BrandPopularRecord;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/10 15:21
 */
public interface BrandPopularRecordService extends BaseService<BrandPopularRecord, Integer> {
    List<BrandPopularRecord> findList(BrandPopularRecord entity);
    List<BrandPopularRecord> findTodayList(int brandId, String requestIp, String type);
}
