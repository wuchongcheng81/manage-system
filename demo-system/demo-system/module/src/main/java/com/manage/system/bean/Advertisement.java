package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

@Data
@TableName("t_advertisement")
public class Advertisement extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer isDel;
    //广告图位置
    private String position;
    //广告图链接
    private String link;
    //广告图url
    private String imgUrl;
    //广告图是否展示
    private String imgIsShow;

    private Integer sort;
}
