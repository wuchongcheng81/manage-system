package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.bean.User;
import com.manage.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    private static final String REDIS_FORGETPWD_PHONE_KEY = "redis_forget_pwd_";
    private static final String REDIS_CHANGE_PHONE_KEY = "redis_change_phone_";
    private static final String REDIS_PHONE_CODE_GET_TIMES_KEY = "redis_get_code_times_";
    private static final String REDIS_USER_CHANGE_PWD_KEY = "redis_user_change_pwd_";

    @Autowired
    private UserService userService;

    @PostMapping(value = "/findList")
    public JSONObject findList(User entity) {
        JSONObject result = new JSONObject();

//        User currentUser = ShiroUtils.getUserEntity();
//        if(currentUser != null && StringUtils.isNotBlank(currentUser.getRoleCode())) {
//            entity.setId(currentUser.getId());
//            entity.setRoleCode(currentUser.getRoleCode());
//        }
        List<User> list = userService.queryList(entity);
        int total = userService.queryTotal(entity);
        result.put("total", total);
        result.put("rows", list);
        return result;
    }
}
