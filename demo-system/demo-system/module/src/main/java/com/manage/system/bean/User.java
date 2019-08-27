package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("t_user")
public class User extends BaseModel<String> {
    //用户账号
    private String userName;
    //用户密码
    private String password;
    //用户真实姓名
    private String realName;
    //用户邮箱
    private String email;
    //用户联系方式
    private String phone;
    //用户性别1男2女
    private Integer sex;
    //用户出生年月
    private LocalDateTime birthday;
    //用户状态 0删除1启用2禁用
    private Integer status;
    //角色id
    private String roleId;
    //角色code
    private String roleCode;

    private String oldPwd;

    private Date loginTime;

    public String getAuthCacheKey() {
        return getId();
    }
}
