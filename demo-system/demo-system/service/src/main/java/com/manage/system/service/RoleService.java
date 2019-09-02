package com.manage.system.service;

import com.manage.system.bean.Role;

import java.util.List;

public interface RoleService {
    int save(Role entity);

    int update(Role entity);

    int deleteByIds(String[] ids);

    int queryTotal(Role entity);

    List<Role> queryListByPage(Role entity);
}
