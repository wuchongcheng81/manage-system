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
 * @author wucc
 * @date 2019/9/9 17:08
 */
@Data
@TableName("t_consult")
public class Consult extends BaseModel {
    public static final String JOIN_STATUS = "join";
    public static final String CONSULT_STATUS = "consult";

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String coustomerName;
    private String coustomerPhone;
    private String coustomerContent;
    private String coustomerBrand;
    private Integer status;
    private String type;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private String requestIp;

    @TableField(exist = false)
    private String createTimeStr;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getCreateTimeStr() {
        if(createTime !=null) {
            return sdf.format(createTime);
        }
        return "";
    }
}
