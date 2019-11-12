package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.util.List;

@Data
@TableName("t_type")
public class Type extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer isDel;
    //分类名称
    private String name;
    //排序
    private Integer sort;

    private Integer pageImgIsShow;
    private Integer detailImgIsShow;
    //页面广告图片
    private String pageImgUrl;
    //详情广告图片
    private String detailImgUrl;
    //链接颜色
    private String linkColor;
    //icon
    private String icon;
    //父级id
    private Integer parentId;

    @TableField(exist = false)
    private int brandCount;

    @TableField(exist = false)
    private List<Type> childs;
}
