package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.util.List;

@Data
@TableName("t_menu")
public class Menu extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //菜单名
    private String name;
    //地址
    private String url;
    //父级id
    private Integer parentId;
    //1父级2子级
    private Integer level;

    private Integer sort;

    @TableField(exist = false)
    private List<Menu> childs;
}
