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
    private Integer labelAuth;
    /**诚信*/
    private Integer labelHonest;
    /**VIP*/
    private Integer labelVip;
    /**优质*/
    private Integer labelQuality;

    /**品牌分类*/
    private String typeName;
    /**加盟条件*/
    private String joinCondition;
    /**是否招商*/
    private Integer isInvest;

    /**创立时间*/
    private Integer establishYear;
    /**品牌发源地*/
    private String originPlace;
    /**品牌热度*/
    private Integer brandPopular;

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

    /**广告位一*/
    private String logoA;
    /**广告位二*/
    private String logoB;
    /**广告位三*/
    private String logoC;

    private String officialWebsite;
    private String onlineShopUrl;
    /**微信分享图片*/
    private String wechatShareImgUrl;
}
