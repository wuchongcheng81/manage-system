package com.manage.system.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseModel<ID extends Serializable> implements Serializable {
    protected static final long serialVersionUID = 1L;

    private ID id;
    @TableField(exist = false)
    private Integer sort;
    @TableField(exist = false)
    private Integer pageNumber;
    @TableField(exist = false)
    private Integer pageSize;
    @TableField(exist = false)
    private Date createTime;
    @TableField(exist = false)
    private Date updateTime;
}
