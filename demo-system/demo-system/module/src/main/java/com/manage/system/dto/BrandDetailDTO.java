package com.manage.system.dto;

import lombok.Data;

/**
 * @author wcc
 * @date 2019/9/15 17:59
 */
@Data
public class BrandDetailDTO {
    /**名称*/
    private String name;
    /**图标*/
    private String logo;
    /**企业名称*/
    private String companyName;

    /**认证*/
    private int labelAuth;
    /**诚信*/
    private int labelHonest;
    /**VIP*/
    private int labelVip;
    /**优质*/
    private int labelQuality;

    /**品牌分类*/
    private String typeName;
    /**加盟条件*/
    private String joinCondition;
    /**是否招商*/
    private int isInvest;

    /**创立时间*/
    private int establishYear;
    /**品牌发源地*/
    private String originPlace;
    /**品牌热度*/
    private int brandPopular;

    /**品牌简介*/
    private String description;

    /**招商类型*/
    private String investType;
    /**招商地区*/
    private String investArea;
    /**招商政策*/
    private String investPolicy;

    /**大咖观点*/
    private String logoViewpoint;


}