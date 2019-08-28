package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.bean.User;
import com.manage.system.common.Constants;
import com.manage.system.redis.JedisUtils;
import com.manage.system.service.UserService;
import com.manage.system.shiro.util.ShiroUtils;
import com.manage.system.util.MD5Util;
import com.manage.system.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final String REDIS_FORGETPWD_PHONE_KEY = "redis_forget_pwd_";
    private static final String REDIS_CHANGE_PHONE_KEY = "redis_change_phone_";
    private static final String REDIS_PHONE_CODE_GET_TIMES_KEY = "redis_get_code_times_";
    private static final String REDIS_USER_CHANGE_PWD_KEY = "redis_user_change_pwd_";

    @Autowired
    private UserService userService;
    @Autowired
    private JedisUtils jedisUtils;

    @PostMapping(value = "/findList")
    public JSONObject findList(User entity) {
        JSONObject result = new JSONObject();

        User currentUser = ShiroUtils.getUser();
        if(currentUser != null && StringUtils.isNotBlank(currentUser.getRoleCode())) {
            entity.setId(currentUser.getId());
            entity.setRoleCode(currentUser.getRoleCode());
        }
        List<User> list = userService.queryList(entity);
        int total = userService.queryTotal(entity);
        result.put("total", total);
        result.put("rows", list);
        return result;
    }

    @PostMapping(value = "/save")
    public JSONObject save(User entity) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        int saveFlag = userService.save(entity);
        if (saveFlag > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/update")
    public JSONObject update(User entity) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        if(StringUtils.isNotBlank(entity.getOldPwd()) && StringUtils.isNotBlank(entity.getPassword())) {
            String oldPwd = MD5Util.getPasswordMD5(entity.getOldPwd());
            User oldUser = userService.findById(entity.getId());
            if(!StringUtils.equals(oldPwd, oldUser.getPassword())) {
                result.put(Constants.NOTE, Constants.LOGIN_PASSWORD_ERROR);
                return result;
            }
        }
        int updateFlag = userService.update(entity);
        if (updateFlag > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/updateRoleId")
    public JSONObject updateRoleId(String userId, Integer roleId) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        int updateFlag = userService.updateRoleId(userId, roleId);
        if (updateFlag > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

    @PostMapping(value = "/deleteByIds")
    public JSONObject deleteByIds(@RequestParam(value = "ids", required = true) String ids) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        ids = ids.replace("\"", "");
        String[] userIds = ids.split(",");
        int deleteResult = userService.deleteByIds(userIds);
        result.put(Constants.STATE, Constants.STATE_SUCCESS);
        return result;
    }

    @PostMapping(value = "/getUserInfo")
    public JSONObject getUserInfo() {
        JSONObject result = new JSONObject();
        result.put("user", userService.findById(ShiroUtils.getUserId()));
        return result;
    }

    @PostMapping(value = {"checkUserIsLogin"})
    public JSONObject checkUserIsLogin() {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        boolean flag = StringUtils.isNotBlank(ShiroUtils.getUserId())? true : false;
        if(flag) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }

//    @RequestMapping(value = {"getSmsCode"})
//    public JSONObject getSmsCode(@RequestParam(required = true) String phone) {
//        JSONObject result = new JSONObject();
//        result.put(Constants.STATE, Constants.STATE_FAIL);
//        String redisKey = REDIS_FORGETPWD_PHONE_KEY + phone;
//        String smsCode = jedisUtils.get(redisKey);
//        if (StringUtils.isNotBlank(smsCode)) {
//            result.put(Constants.NOTE, Constants.GET_SMS_LATER);
//        }else {
//            //10分钟内只能获取5次验证码
//            long getTimes = jedisUtils.incrBy(REDIS_PHONE_CODE_GET_TIMES_KEY + phone, 1, 600);
//            if (getTimes >= 5) {
//                result.put(Constants.NOTE, Constants.GET_SMS_TOO_MUCH);
//            } else {
//                //获取4位数随机数
//                smsCode = SmsUtils.getSmsCode();
//                SmsSingleSenderResult sendResult = SmsUtils.sendTextSms(null, phone, smsCode);
//                if (Constants.SMS_SUCCESS_MSG.equals(sendResult.errMsg)) {
//                    //存放缓存，过期时间55秒
//                    jedisUtils.set(redisKey, smsCode, 55);
//                    result.put(Constants.STATE, Constants.STATE_SUCCESS);
//                } else {
//                    result.put(Constants.NOTE, sendResult.errMsg);
//                }
//            }
//        }
//        return result;
//    }

    @PostMapping(value = "/checkCode")
    public JSONObject checkCode(String userName, String phone, String code, String password) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);

        User user = userService.findByUserName(userName);
        if(user == null) {
            result.put("errorCode", 1);
            result.put(Constants.NOTE, Constants.USER_NOT_EXIST);
            return result;
        }
        if(!StringUtils.equals(phone, user.getPhone())) {
            result.put("errorCode", 2);
            result.put(Constants.NOTE, Constants.USER_PHONE_NOT_CORRECT);
            return result;
        }
        String redisCode = jedisUtils.get(REDIS_FORGETPWD_PHONE_KEY + phone);
        if(StringUtils.isEmpty(redisCode)) {
            result.put("errorCode", 3);
            result.put(Constants.NOTE, Constants.SMS_CODE_EXPIRE);
            return result;
        }
        if(!StringUtils.equals(code, redisCode)) {
            result.put("errorCode", 3);
            result.put(Constants.NOTE, Constants.SMS_NOT_CORRECT);
            return result;
        }
        String uuid = ObjectUtil.getUUID();
        //校验通过后用户在120s内完成提交动作，否则过期
        jedisUtils.set(REDIS_USER_CHANGE_PWD_KEY + userName, uuid, 120);

        result.put("key", uuid);
        result.put(Constants.STATE, Constants.STATE_SUCCESS);
        return result;
    }

    @PostMapping(value = "/updatePassword")
    public JSONObject updatePassword(@RequestParam String userName,
                                     @RequestParam String password,
                                     @RequestParam String key) {
        JSONObject result = new JSONObject();
        result.put(Constants.STATE, Constants.STATE_FAIL);
        String redisKey = jedisUtils.get(REDIS_USER_CHANGE_PWD_KEY + userName);
        if(StringUtils.isEmpty(redisKey) || !StringUtils.equals(redisKey, key)) {
            result.put(Constants.NOTE, Constants.SESSION_TIME_OUT);
            return result;
        }

        int updateFlag = userService.updatePasswordByUserName(userName, password);
        if (updateFlag > 0) {
            result.put(Constants.STATE, Constants.STATE_SUCCESS);
        }
        return result;
    }
}
