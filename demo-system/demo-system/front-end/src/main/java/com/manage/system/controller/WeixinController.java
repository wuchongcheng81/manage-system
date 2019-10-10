package com.manage.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.dto.weixin.AccessToken;
import com.manage.system.redis.JedisUtils;
import com.manage.system.response.ResultData;
import com.manage.system.service.feign.WeixinFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcc
 * @date 2019/10/9 21:52
 */
@RestController
@RequestMapping(value = "/api/weixin")
public class WeixinController {

    private static final String GRANT_TYPE = "client_credential";
    private static final String ACCESS_TOKEN = "access_token";

    @Autowired
    private WeixinFeignService weixinFeignService;

    @Value("${weixin.appid}")
    String appId;
    @Value("${weixin.secret}")
    String secret;

    @RequestMapping(value = "/getAccessToken")
    public ResultData getAccessToken() {
        String accessToken = JedisUtils.getInstance().get(ACCESS_TOKEN);
        if(StringUtils.isNotBlank(accessToken)) {
            return new ResultData(true, accessToken);
        }
        JSONObject tokenJSON = weixinFeignService.getAccessToken(GRANT_TYPE, appId, secret);
        if(tokenJSON != null && tokenJSON.containsKey("access_token")) {
            accessToken = tokenJSON.getString("access_token");
            if(StringUtils.isNotBlank(accessToken)) {
                JedisUtils.getInstance().set(ACCESS_TOKEN, accessToken, 7100);
                return new ResultData(true, accessToken);
            }
        }
        return new ResultData(false);
    }
}
