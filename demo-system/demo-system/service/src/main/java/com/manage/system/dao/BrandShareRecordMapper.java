package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.BrandShareRecord;
import com.manage.system.dto.CountDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/17 21:00
 */
public interface BrandShareRecordMapper extends BaseMapper<BrandShareRecord> {

    /**统计当天分享*/
    int countShare(@Param("brandId") Integer brandId);

    /**统计昨天分享量*/
    int countYesterDayShare(@Param("brandId") Integer brandId);

    /**查询前几天的统计分享量*/
    List<CountDetailDTO> countShareByBeforeDay(@Param("beforeDay") String beforeDay, @Param("brandId") Integer brandId);
}
