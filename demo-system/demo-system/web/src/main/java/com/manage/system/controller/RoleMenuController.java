package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.common.Constants;
import com.manage.system.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wucc
 * @date 2019/8/28 16:33
 */
@RestController
@RequestMapping(value = "/roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;

    @PostMapping(value = "/save")
    public JSONObject save(@RequestParam String roleId, @RequestParam String menuIds) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        try {
            String[] menuIdsArray = menuIds.split(",");
            int saveResult = roleMenuService.save(roleId, menuIdsArray);
            if (saveResult > 0) {
                result.put(Constants.STATE, Constants.STATE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
