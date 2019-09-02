package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

/**
 * @author wucc
 * @date 2019/9/2 8:35
 */
@Data
@TableName("t_focus_map")
public class FocusMap extends BaseModel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer isDel;
    private String position;
    private String link;
    private String imgUrl;
    private Integer imgIsShow;
    private Integer sort;
    private String positionCode;
}
