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

    public static final String VISIT_TYPE = "visit";
    public static final String MANUAL_TYPE = "manual";

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer brandId;
    private Date createTime;
    private String requestIp;
    @TableField("visit_type")
    private String type;

    public BrandPopularRecord(String requestIp, String type, Integer brandId) {
        this.requestIp = requestIp;
        this.type = type;
        this.brandId = brandId;
    }

    public BrandPopularRecord() {
    }
}
