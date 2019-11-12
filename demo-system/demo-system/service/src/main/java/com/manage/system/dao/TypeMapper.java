package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.Type;
import com.manage.system.dto.TypeDTO;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {
    List<TypeDTO> findAllType();
}
