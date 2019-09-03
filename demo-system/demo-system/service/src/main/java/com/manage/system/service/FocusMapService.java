package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.FocusMap;
import com.manage.system.dto.FocusMapGroupDTO;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/2 8:38
 */
public interface FocusMapService extends BaseService<FocusMap, Integer> {
    List<FocusMapGroupDTO> findListByGroup();
}
