package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.bean.Menu;
import com.manage.system.common.Constants;
import com.manage.system.service.MenuService;
import com.manage.system.shiro.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wucc
 * @date 2019/8/28 16:27
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping(value = "/findList")
    public JSONObject findList(Menu entity) {
        JSONObject result = new JSONObject();
        List<Menu> list = menuService.queryListByPage(entity);
        int total = menuService.queryTotal(entity);
        result.put("total", total);
        result.put("rows", list);
        return result;
    }

    @PostMapping(value = "/save")
    public JSONObject save(Menu entity) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_SUCCESS);

        int saveResult = menuService.save(entity);
        if (saveResult > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/update")
    public JSONObject update(Menu entity) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_SUCCESS);

        int updateResult = menuService.update(entity);
        if (updateResult > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/deleteByIds")
    public JSONObject deleteByIds(@RequestParam String ids) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        ids = ids.replace("\"", "");
        String[] idsArr = ids.split(",");
        int deleteResult = menuService.deleteByIds(idsArr);
        result.put(Constants.STATE, Constants.STATE_SUCCESS);
        return result;
    }

    @PostMapping(value = "/findMenuByUser")
    public JSONObject findMenuByUser() {
        JSONObject result = new JSONObject();
        result.put("menus", menuService.findByUserId(ShiroUtils.getUserId()));
        return result;
    }
}
