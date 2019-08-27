package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

@Data
@TableName("t_role")
public class Role extends BaseModel<String> {

    //角色名称
    private String name;
    //否是启动，1-启动，0-禁用
    private int state;
}
