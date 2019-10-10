package com.manage.system.service;

import com.alibaba.fastjson.JSONObject;
import com.manage.system.dto.weixin.JsApiTicket;
import com.manage.system.redis.JedisUtils;
import com.manage.system.service.feign.WeixinFeignService;
import com.manage.system.util.ShaUtil;
import com.manage.system.util.WeixinSignatureUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.DigestException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wucc
 * @date 2019/10/10 15:27
 */
@Service
public class WeixinService {

    private static final String GRANT_TYPE = "client_credential";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String JS_API_TICKET = "js_api_ticket";
    private static final String JS_API_TICKET_CLASS = "js_api_ticket_class";

    private static final String GET_TICKET_TYPE = "jsapi";

    private static final String URL = "http://m.pinpinpp.com";

    @Autowired
    private JedisUtils jedisUtils;
    @Autowired
    private WeixinFeignService weixinFeignService;

    @Value("${weixin.appid}")
    String appId;
    @Value("${weixin.secret}")
    String secret;

    /**
     * 获取token
     * @return
     * {"access_token":"ACCESS_TOKEN","expires_in":7200}
     * {"errcode":40013,"errmsg":"invalid appid"}
     */
    public String getAccessToken() {
        String accessToken = jedisUtils.get(ACCESS_TOKEN);
        if(StringUtils.isNotBlank(accessToken))
            return accessToken;

        JSONObject tokenJSON = weixinFeignService.getAccessToken(GRANT_TYPE, appId, secret);
        if(tokenJSON != null && tokenJSON.containsKey("access_token")) {
            accessToken = tokenJSON.getString("access_token");
            if(StringUtils.isNotBlank(accessToken)) {
                jedisUtils.set(ACCESS_TOKEN, accessToken, 7100);
                return accessToken;
            }
        }
        return null;
    }

    /**
     * 获取jsapi ticket
     * @return
     * {
     *   "errcode":0,
     *   "errmsg":"ok",
     *   "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
     *   "expires_in":7200
     * }
     */
    public String getTicket() {
        String accessToken = getAccessToken();
        if(StringUtils.isNotBlank(accessToken)) {
            JSONObject ticketJSON = weixinFeignService.getTicket(accessToken, GET_TICKET_TYPE);
            if(ticketJSON != null && ticketJSON.containsKey("errcode") && ticketJSON.getInteger("errcode") == 0) {
                String ticket = ticketJSON.getString("ticket");
                if(StringUtils.isNotBlank(ticket)) {
                    jedisUtils.set(JS_API_TICKET, ticket, 7100);
                    return ticket;
                }
            }
        }
        return null;
    }

    public JsApiTicket getJsApiTicket(String url) {

        String ticket = jedisUtils.get(JS_API_TICKET);
        if(StringUtils.isBlank(ticket))
            ticket = getTicket();

        JsApiTicket jsApiTicket = WeixinSignatureUtil.sign(ticket, url);

        System.out.println(JSONObject.toJSONString(jsApiTicket));
        return jsApiTicket;
    }



}
