package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

@Data
@TableName("t_type")
public class Type extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //分类名称
    private String name;
    //排序
    private Integer sort;
}
