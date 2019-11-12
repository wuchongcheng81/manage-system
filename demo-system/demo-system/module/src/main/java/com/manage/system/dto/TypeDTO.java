package com.manage.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/14 22:27
 */
@Data
public class TypeDTO {
    private Integer id;
    private String name;
    //链接颜色
    private String linkColor;
    //icon
    private String icon;

    private Integer parentId;

    private List<BrandDTO> brandList;

    private List<TypeDTO> childs;
}
