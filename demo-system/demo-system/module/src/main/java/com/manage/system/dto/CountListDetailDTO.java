package com.manage.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/17 19:52
 */
@Data
public class CountListDetailDTO {
    private List<Integer> browseList;
    private List<Integer> ipList;
    private List<Integer> popularList;
    private List<Integer> shareList;

    private List<String> beforeDays;
}
