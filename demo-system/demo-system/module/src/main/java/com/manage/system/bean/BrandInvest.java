package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author wucc
 * @date 2019/9/20 17:44
 */
@Data
@TableName("t_brand_invest")
public class BrandInvest extends BaseModel {
    @TableId(type = IdType.AUTO)
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
    private Date createTime;
    private Date expireDate;
    private Date publishTime;

    @TableField(exist = false)
    private Long expireDateTimeStamp;

}
