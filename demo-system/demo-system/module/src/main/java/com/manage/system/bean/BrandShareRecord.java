package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wcc
 * @date 2019/11/17 20:59
 */
@Data
@TableName("t_brand_share_record")
public class BrandShareRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer brandId;
    private Date createTime;
    private String requestIp;
}
