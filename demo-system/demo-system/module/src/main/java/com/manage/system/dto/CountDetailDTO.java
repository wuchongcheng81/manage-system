package com.manage.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wcc
 * @date 2019/11/17 13:15
 */
@Data
public class CountDetailDTO implements Serializable {
    private int countBrowse;
    private int countIp;
    private int countPopular;
    private int countShare;

    private int countYesBrowse;
    private int countYesIp;
    private int countYesPopular;
    private int countYesShare;

    private int countNum;
    private String cdate;
}
