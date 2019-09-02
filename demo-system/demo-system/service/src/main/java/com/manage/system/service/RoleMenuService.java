package com.manage.system.service;

import com.manage.system.bean.RoleMenu;

import java.util.List;

public interface RoleMenuService {
    int deleteByRoleId(String roleId);

    int save(String roleId, String[] menuIds);

    List<RoleMenu> queryListByRoleId(String roleId);
}
