package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.manage.system.base.BaseModel;
import lombok.Data;

/**
 * @author wcc
 * @date 2019/9/4 21:43
 */
@Data
@TableName("t_information")
public class Information extends BaseModel {
    private Integer id;
    private Integer isDel;
    private Integer isPublish;
    private String coverUrl;
    private String title;
    private String column;
    private Integer typeId;
    private Integer brandId;
    private Integer clickNum;
    private String content;
    private String guideContent;

}
