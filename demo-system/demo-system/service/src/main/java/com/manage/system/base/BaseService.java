package com.manage.system.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.bean.Role;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {

    int save(T entity);
    int update(T entity);
    int delete(ID id);
    int deleteByIds(ID[] ids);
    int queryTotal(T entity);
    T findById(ID id);
    IPage<T> findPage(T entity);
    List<T> queryListByPage(T entity);
}
