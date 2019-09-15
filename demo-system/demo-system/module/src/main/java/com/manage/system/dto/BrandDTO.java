package com.manage.system.dto;

import lombok.Data;

/**
 * @author wucc
 * @date 2019/9/10 14:44
 */
@Data
public class BrandDTO {
    private Integer id;
    private String logo;
    private String name;
    private Integer increaseNum;
    private String typeName;
    private String companyName;
    private Integer labelAuth;
    private Integer labelHonest;
    private Integer labelVip;
    private Integer labelQuality;
}
