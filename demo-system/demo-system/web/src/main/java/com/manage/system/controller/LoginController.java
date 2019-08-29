package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.common.Constants;
import com.manage.system.service.UserService;
import com.manage.system.shiro.util.ShiroUtils;
import com.manage.system.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author wucc
 * @date 2019/8/28 16:26
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestParam String username, @RequestParam String password, Boolean rememberMe) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        password = MD5Util.getPasswordMD5(password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            Subject subject = ShiroUtils.getSubject();
            subject.login(token);

            userService.updateLogin(username);

            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            result.put(Constants.NOTE, e.getMessage());
        } catch (AuthenticationException e) {
            result.put(Constants.NOTE, "认证失败！");
        }
        return result;
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
