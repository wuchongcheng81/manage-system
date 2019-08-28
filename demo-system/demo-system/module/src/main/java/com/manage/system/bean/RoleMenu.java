package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

@Data
@TableName("t_role_menu")
public class RoleMenu extends BaseModel{
    private String roleId;
    private String menuId;
}
