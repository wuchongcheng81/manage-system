package com.manage.system.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.backend.common.Constants;
import com.manage.system.bean.Role;
import com.manage.system.service.MenuService;
import com.manage.system.service.RoleMenuService;
import com.manage.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wucc
 * @date 2019/8/28 16:31
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    @PostMapping(value = "/findList")
    public JSONObject findList(Role entity) {
        JSONObject result = new JSONObject();
        List<Role> list = roleService.queryListByPage(entity);
        int total = roleService.queryTotal(entity);
        result.put("total", total);
        result.put("rows", list);
        return result;
    }

    @PostMapping(value = "/save")
    public JSONObject save(Role entity) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);

        int saveResult = roleService.save(entity);
        if (saveResult > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/update")
    public JSONObject update(Role entity) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);

        int updateResult = roleService.update(entity);
        if (updateResult > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/deleteByIds")
    public JSONObject deleteByIds(@RequestParam(value = "ids", required = true) String ids) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        ids = ids.replace("\"", "");
        String[] roleIds = ids.split(",");
        int deleteResult = roleService.deleteByIds(roleIds);
        result.put(Constants.STATE, Constants.STATE_SUCCESS);
        return result;
    }

    @PostMapping(value = "/getRoleMenu")
    public JSONObject getRoleMenu(@RequestParam(value = "roleId", required = true) String roleId) {
        JSONObject result = new JSONObject();
        result.put("roleMenu", roleMenuService.queryListByRoleId(roleId));
        result.put("menu", menuService.queryList());
        return result;
    }
}
