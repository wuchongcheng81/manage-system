package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    int queryTotal(Role entity);

    List<Role> queryListByPage(Role entity);
}
