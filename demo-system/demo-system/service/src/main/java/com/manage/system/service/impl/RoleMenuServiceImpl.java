package com.manage.system.service.impl;

import com.manage.system.base.AbstractService;
import com.manage.system.bean.RoleMenu;
import com.manage.system.dao.RoleMenuMapper;
import com.manage.system.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@Transactional
public class RoleMenuServiceImpl extends AbstractService<RoleMenu, RoleMenuMapper> implements RoleMenuService {
    @Override
    public int deleteByRoleId(String roleId) {
        return mapper.deleteByRoleId(roleId);
    }

    @Override
    public int save(String roleId, String[] menuIds) {
        mapper.deleteByRoleId(roleId);
        return mapper.save(roleId, menuIds);
    }

    @Override
    public List<RoleMenu> queryListByRoleId(String roleId) {
        return mapper.queryListByRoleId(roleId);
    }
}
