package com.manage.system.dto;

import lombok.Data;

/**
 * @author wcc
 * @date 2019/9/23 21:59
 */
@Data
public class WangEditorDTO {
    private Integer errno; //错误代码，0 表示没有错误。
    private String[] data; //已上传的图片路径

    public WangEditorDTO(String[] data) {
        this.errno = 0;
        this.data = data;
    }

    public WangEditorDTO() {
    }
}
