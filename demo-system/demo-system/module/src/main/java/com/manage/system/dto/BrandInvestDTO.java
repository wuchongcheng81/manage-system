package com.manage.system.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author wcc
 * @date 2019/9/22 15:28
 */
@Data
public class BrandInvestDTO {
    private Integer id;
    private String title;
    private String brandName;
    private String investLogo;
    private String type;
    private String investType;
    private String investArea;
    private Integer isTop;
    private Integer topSort;
    private Integer isPublish;
    private String investContentLogo;
    private String mark;
    private String createTimeStr;
    private String publishTimeStr;
    private boolean isExpire;
}
