package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.BrandShareRecord;
import com.manage.system.dto.CountDetailDTO;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/17 21:01
 */
public interface BrandShareRecordService extends BaseService<BrandShareRecord, Integer> {

    /**统计当天分享*/
    int countShare(Integer brandId);

    /**统计昨天分享量*/
    int countYesterDayShare(Integer brandId);

    /**查询前几天的统计分享量*/
    List<CountDetailDTO> countShareByBeforeDay(String beforeDay, Integer brandId);
}
