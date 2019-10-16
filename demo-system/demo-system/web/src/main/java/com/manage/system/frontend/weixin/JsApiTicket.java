package com.manage.system.frontend.weixin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wucc
 * @date 2019/10/10 15:51
 */
@Data
public class JsApiTicket implements Serializable {
    private String nonceStr;
    private String signature;
    private String timestamp;
    private String url;
    private String jsapi_ticket;
}
