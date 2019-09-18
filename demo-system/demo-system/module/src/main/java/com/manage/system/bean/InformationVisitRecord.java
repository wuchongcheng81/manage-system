package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wcc
 * @date 2019/9/18 23:28
 */
@Data
@TableName("t_information_visit_record")
public class InformationVisitRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer informationId;
    private String requestIp;
    private Date createTime;
}
