package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wcc
 * @date 2019/11/24 20:44
 */
@Data
@TableName("t_brand_visit_record")
public class BrandVisitRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer brandId;
    private Date createTime;

    public BrandVisitRecord(Integer brandId, Date createTime) {
        this.brandId = brandId;
        this.createTime = createTime;
    }

    public BrandVisitRecord() {
    }
}
