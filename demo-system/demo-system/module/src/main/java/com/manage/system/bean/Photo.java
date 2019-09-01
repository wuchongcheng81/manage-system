package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author wucc
 * @date 2019/8/29 8:24
 */
@Data
@TableName("t_photo")
public class Photo extends BaseModel {

    private String id;
    private int isShow;
    private String imgUrl;
    private String relatedId;
    private Date createTime;

    public Photo(String id) {
        this.id = id;
    }

    public Photo() {
    }
}
