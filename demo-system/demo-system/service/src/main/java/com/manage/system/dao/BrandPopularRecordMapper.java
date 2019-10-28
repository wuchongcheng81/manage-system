package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.BrandPopularRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/10 15:21
 */
public interface BrandPopularRecordMapper extends BaseMapper<BrandPopularRecord> {
    List<BrandPopularRecord> findTodayList(@Param("brandId") int brandId, @Param("requestIp") String requestIp, @Param("type") String type);

    int insertList(@Param("list") List<BrandPopularRecord> list);
}
