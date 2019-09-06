package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author wucc
 * @date 2019/9/6 13:10
 */
@Data
@TableName("t_brand")
public class Brand extends BaseModel {
    private Integer id;
    private Integer isDel;
    private String logo;
    private String name;
    private String companyName;
    private Integer typeId;
    private Integer establishYear;
    private String originPlace;
    private String brandPopular;
    private String joinCondition;
    private Integer labelAuth;
    private Integer labelHonest;
    private Integer labelVip;
    private Integer labelQuality;
    private String description;
    private String logoA;
    private String logoB;
    private String logoC;
    private String logoD;
    private Integer isInvest;
    private String investCondition;
    private String investArea;
    private String investPolicy;
    private Date createTime;
}
