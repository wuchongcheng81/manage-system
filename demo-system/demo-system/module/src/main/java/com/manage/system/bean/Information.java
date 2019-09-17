package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wcc
 * @date 2019/9/4 21:43
 */
@Data
@TableName("t_information")
public class Information extends BaseModel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer isDel;
    private Integer isPublish;
    private String coverUrl;
    private String title;
    private String columnCode;
    private Integer typeId;
    private Integer brandId;
    private Integer clickNum;
    private String content;
    private String guideContent;
    private Date createTime;
    private Date publishTime;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String brandName;

    @TableField(exist = false)
    private String publishTimeStr;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getPublishTimeStr() {
        if(publishTime !=null) {
            return sdf.format(publishTime);
        }
        return "";
    }
}
