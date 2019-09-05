package com.manage.system.dto;

import lombok.Data;

/**
 * @author wcc
 * @date 2019/9/4 22:35
 */
@Data
public class InformationDTO {
    private String title;
    private String name;
    private String column;
    private Integer typeId;
    private Integer pageNumber;
    private Integer pageSize;
}
