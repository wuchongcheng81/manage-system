package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.redis.JedisUtils;
import com.manage.system.response.ResultData;
import com.manage.system.service.WeixinService;
import com.manage.system.service.feign.WeixinFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcc
 * @date 2019/10/9 21:52
 */
@RestController
@RequestMapping(value = "/api/weixin")
public class WeixinController {

    @Autowired
    private WeixinService weixinService;

    @RequestMapping(value = "/getAccessToken")
    public ResultData getAccessToken() {
        String accessToken = weixinService.getAccessToken();
        if(StringUtils.isNotBlank(accessToken)) {
            return new ResultData(true, accessToken);
        }
        return new ResultData(false);
    }

    @RequestMapping(value = "/getJsapiTicket")
    public ResultData getJsapiTicket(String url) {
        return new ResultData(true, weixinService.getJsApiTicket(url));
    }

}
