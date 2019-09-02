package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.FocusMap;
import com.manage.system.dto.FocusMapGroupDTO;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/2 8:37
 */
public interface FocusMapMapper extends BaseMapper<FocusMap> {
    List<FocusMapGroupDTO> findListByGroup();
}
