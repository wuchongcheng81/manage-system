package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<RoleMenu> queryListByRoleId(String roleId);

    int deleteByRoleId(String roleId);

    int save(@Param("roleId") String roleId, @Param("menuIds") String[] menuIds);
}
