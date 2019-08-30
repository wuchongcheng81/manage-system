package com.manage.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.bean.Type;

public interface TypeService {
    int save(Type entity);
    int update(Type entity);
    int delete(int id);
    IPage<Type> findPage(Type entity);
}
