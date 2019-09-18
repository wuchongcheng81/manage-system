package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.InformationVisitRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/18 23:29
 */
public interface InformationVisitRecordMapper extends BaseMapper<InformationVisitRecord> {
    List<InformationVisitRecord> findCurrentDay(@Param("requestIp") String requestIp, @Param("informationId") int informationId);
}
