package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Information;
import com.manage.system.dto.InformationDTO;
import com.manage.system.dto.InformationFrontDTO;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/4 21:56
 */
public interface InformationService extends BaseService<Information, Integer> {
    List<Information> findListByPage(InformationDTO informationDTO);
    int queryTotal(InformationDTO informationDTO);
    List<InformationFrontDTO> findListByColumnCode(String columnCode, Integer pageNumber, Integer pageSize);
}
