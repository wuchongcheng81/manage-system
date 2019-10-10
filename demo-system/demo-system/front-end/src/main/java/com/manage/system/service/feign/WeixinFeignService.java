package com.manage.system.service.feign;

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
    JSONObject getAccessToken(@RequestParam String grant_type, @RequestParam String appid, @RequestParam String secret);
}
