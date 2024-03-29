package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.Information;
import com.manage.system.dto.InformationDTO;
import com.manage.system.dto.InformationFrontDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author wcc
 * @date 2019/9/4 21:53
 */
public interface InformationMapper extends BaseMapper<Information> {
    int queryTotal(InformationDTO information);
    List<Information>  findListByPage(InformationDTO information);
    Information findById(@Param("id") Integer id);
    List<InformationFrontDTO> findListByColumnCode(@Param("columnCode") String columnCode, @Param("typeId") Integer typeId,
                                                   @Param("brandId") Integer brandId,
                                                   @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    void addClickNum(@Param("id") int id);
    List<InformationFrontDTO> findLastWeek(@Param("startTime") Date startTime, @Param("typeId") Integer typeId,
                                           @Param("brandId") Integer brandId);
}
