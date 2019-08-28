package com.manage.system.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseModel implements Serializable {
    protected static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Integer pageNumber;
    @TableField(exist = false)
    private Integer pageSize;
}
