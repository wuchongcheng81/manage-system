package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Information;
import com.manage.system.dto.InformationDTO;
import com.manage.system.dto.InformationFrontDTO;

import java.util.Date;
import java.util.List;

/**
 * @author wcc
 * @date 2019/9/4 21:56
 */
public interface InformationService extends BaseService<Information, Integer> {
    List<Information> findListByPage(InformationDTO informationDTO);
    int queryTotal(InformationDTO informationDTO);
    List<InformationFrontDTO> findListByColumnCode(String columnCode, Integer typeId, Integer brandId, Integer pageNumber, Integer pageSize);
    void addClickNum(int id);
    List<InformationFrontDTO> findLastWeek(Date startTime, Integer typeId,
                                           Integer brandId, Integer searchCount);
}
