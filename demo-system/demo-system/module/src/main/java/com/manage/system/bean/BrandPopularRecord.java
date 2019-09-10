package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wucc
 * @date 2019/9/10 15:16
 */
@Data
@TableName("t_brand_popular_record")
public class BrandPopularRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer brandId;
    private Date createTime;
}
