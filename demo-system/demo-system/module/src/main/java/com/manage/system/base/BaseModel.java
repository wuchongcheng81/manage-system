package com.manage.system.base;

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
    private Integer sort;
    private Integer count;
    private Integer pageNumber;
    private Integer pageSize;
    private Date dateStart;
    private Date dateEnd;
    private Date createTime;
    private Date updateTime;
    private LinkedHashMap<String, Object[]> idsMap;
    private LinkedHashMap<String, String> orderByMap;
}
