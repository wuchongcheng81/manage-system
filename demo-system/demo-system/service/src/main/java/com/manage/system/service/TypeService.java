package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Type;
import com.manage.system.dto.TypeDTO;

import java.util.List;

public interface TypeService extends BaseService<Type, Integer> {
    List<Type> findAll(Type type);

    List<Type> findAllWithBrandCount();

    List<Type> findAllParent();

    Integer findListByParentId(int parentId);

    List<TypeDTO> findAllWithChilds();
}
