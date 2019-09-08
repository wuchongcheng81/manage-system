package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wcc
 * @date 2019/9/8 10:12
 */


@Data
@TableName("t_website")
public class Website {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String keyWord;
    private String description;
    private String domainName;
    private String logo;
    private String wechatLogo;
    private String adCooperate;
    private String copyright;
    private String olService;
    private String inforJoin;
    private String searchTip;
    private String popularType;

}
