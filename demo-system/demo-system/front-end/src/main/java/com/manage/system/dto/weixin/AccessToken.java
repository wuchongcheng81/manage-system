package com.manage.system.dto.weixin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wcc
 * @date 2019/10/9 22:13
 */
@Data
public class AccessToken implements Serializable {

    private String access_token;
    private Long expires_in;
    private int errcode;
    private String errmsg;
}
