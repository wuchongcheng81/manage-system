package com.manage.system.dto;

import lombok.Data;

/**
 * @author wucc
 * @date 2019/9/10 16:26
 */
@Data
public class InformationFrontDTO {
    private Integer id;
    private String title;
    private String coverUrl;
    private String guideContent;
    private String companyName;
    private String createTime;
    private Integer typeId;
    private Integer brandId;
}
