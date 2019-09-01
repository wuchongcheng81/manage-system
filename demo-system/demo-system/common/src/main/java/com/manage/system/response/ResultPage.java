package com.manage.system.response;

import lombok.Data;

import java.util.List;

@Data
public class ResultPage<T> {
    private Long total;
    private List<T> rows;

    public ResultPage() {}

    public ResultPage(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
