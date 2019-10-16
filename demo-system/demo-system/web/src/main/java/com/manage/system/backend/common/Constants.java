package com.manage.system.backend.common;

public interface Constants {

    String STATE = "state";
    String NOTE = "note";
    String STATE_SUCCESS = "11";
    String STATE_FAIL = "00";
    String PARAM_ERROR = "参数错误";
    String SYS_EXCEPTION = "系统异常，请稍后再试";
    String GET_SMS_TOO_MUCH = "获取验证码次数过于频繁，请10分钟后再试";
    String ACTIVITY_SMS_USEUP = "活动太火爆，短信已用完！";
    String PHONE_ERROR = "手机号码有误，请重新输入";
    String SMS_CODE_EXPIRE = "验证码超时，请重新获取";

    String SMS_SUCCESS_MSG = "OK";
    String GET_SMS_LATER = "您已获取过验证码了，请60s后重试";
    String USER_ALREADY_EXIST = "该用户已存在，请直接登录";
    String USER_NOT_EXIST = "用户不存在";
    String SMS_NOT_CORRECT = "验证码错误";
    String LOGIN_ERROR_TIMES_LIMIT = "当前登录次数过于频繁，请稍后重试";
    String LOGIN_PASSWORD_ERROR = "密码错误，请重新输入";
    String LOGIN_SUCCESS = "登录成功";
    String UPDATE_PASSWORD_SUCCESS = "密码修改成功";
    String UPDATE_INFO_SUCCESS = "修改成功";

    String UPDATE_FILE_FAIL = "文件上传失败";
    String UPDATE_HEAD_IMG_FAIL = "头像上传失败";
    String USER_INFO_ERROR = "用户信息有误";
    String USER_PHONE_NOT_CORRECT = "该手机号非用户注册时填写的手机号";

    String SESSION_TIME_OUT = "页面已过期，请刷新";
}