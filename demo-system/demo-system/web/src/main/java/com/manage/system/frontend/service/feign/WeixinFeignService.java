package com.manage.system.frontend.service.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wcc
 * @date 2019/10/9 22:10
 */
@Component
@FeignClient(name = "weixin-service", url = "https://api.weixin.qq.com")
public interface WeixinFeignService {

    @GetMapping(value = "/cgi-bin/token", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JSONObject getAccessToken(@RequestParam("grant_type") String grant_type, @RequestParam("appid") String appid, @RequestParam("secret") String secret);

    @GetMapping(value = "/cgi-bin/ticket/getticket", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JSONObject getTicket(@RequestParam("access_token") String access_token, @RequestParam("type") String type);
}
