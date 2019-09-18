package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wucc
 * @date 2019/9/6 13:10
 */
@Data
@TableName("t_brand")
public class Brand extends BaseModel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer isDel;
    private String logo;
    private String name;
    private String companyName;
    private Integer typeId;
    private Integer establishYear;
    private String originPlace;
    private Integer brandPopular;
    private String joinCondition;
    private Integer labelAuth;
    private Integer labelHonest;
    private Integer labelVip;
    private Integer labelQuality;
    private String description;
    private String logoA;
    private String logoB;
    private String logoC;
    private String logoViewpoint;
    private Integer isInvest;
    private String investArea;
    private String investPolicy;
    private Date createTime;
    private Integer status;
    private String investType;

    private Integer recIndex;
    private Integer recTypeIndex;
    private Integer recTypeRank;
    private Integer recTypeInfor;
    private Integer recInforHead;
    private Integer recInforEval;
    private Integer recInforDetail;
    private Integer isPublish;

    @TableField(exist = false)
    private String createTimeStr;

    @TableField(exist = false)
    private String typeName;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getCreateTimeStr() {
        if(createTime !=null) {
            return sdf.format(createTime);
        }
        return "";
    }
}
