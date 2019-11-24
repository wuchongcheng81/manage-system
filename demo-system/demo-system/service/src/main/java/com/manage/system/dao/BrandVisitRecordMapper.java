package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.BrandVisitRecord;
import com.manage.system.dto.CountDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/24 20:53
 */
public interface BrandVisitRecordMapper extends BaseMapper<BrandVisitRecord> {
    /**统计浏览量*/
    int countBrowse(@Param("brandId") Integer brandId);
    /**统计昨天浏览量*/
    int countYesterDayBrowse(@Param("brandId") Integer brandId);
    /**查询前几天的统计浏览量*/
    List<CountDetailDTO> countBrowseByBeforeDay(@Param("beforeDay") String beforeDay, @Param("brandId") Integer brandId);
}
