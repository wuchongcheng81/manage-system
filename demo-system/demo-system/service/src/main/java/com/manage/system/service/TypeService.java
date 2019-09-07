package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Type;

import java.util.List;

public interface TypeService extends BaseService<Type, Integer> {
    List<Type> findAll();
}
