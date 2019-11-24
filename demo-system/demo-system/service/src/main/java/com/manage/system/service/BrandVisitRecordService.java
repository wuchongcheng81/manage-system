package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.BrandVisitRecord;
import com.manage.system.dto.CountDetailDTO;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/24 20:58
 */
public interface BrandVisitRecordService extends BaseService<BrandVisitRecord, Integer> {
    /**统计浏览量*/
    int countBrowse(Integer brandId);
    /**统计昨天浏览量*/
    int countYesterDayBrowse(Integer brandId);
    /**查询前几天的统计浏览量*/
    List<CountDetailDTO> countBrowseByBeforeDay(String beforeDay, Integer brandId);
}
