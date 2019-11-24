package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.dto.CountDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/17 13:06
 */
public interface CountMapper extends BaseMapper<CountDetailDTO> {
    /**统计Ip数*/
    int countIp(@Param("brandId") Integer brandId);
    /**统计热度*/
    int countPopular(@Param("brandId") Integer brandId);

    /**统计Ip数*/
    int countYesterDayIp(@Param("brandId") Integer brandId);
    /**统计热度*/
    int countYesterDayPopular(@Param("brandId") Integer brandId);

    /**查询前几天的统计Ip数*/
    List<CountDetailDTO> countIpByBeforeDay(@Param("beforeDay") String beforeDay, @Param("brandId") Integer brandId);
    /**查询前几天的统计热度*/
    List<CountDetailDTO> countPopularByBeforeDay(@Param("beforeDay") String beforeDay, @Param("brandId") Integer brandId);
}
