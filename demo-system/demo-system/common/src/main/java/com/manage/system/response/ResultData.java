package com.manage.system.response;

import io.lettuce.core.output.BooleanOutput;
import lombok.Data;

@Data
public class ResultData<T> {
    private int state;
    private String note;
    private String errorCode;
    private T data;

    public ResultData() {
    }

    public ResultData(boolean isSuccess) {
        this.setNote(this.note);
        this.setState(isSuccess ? 11 : 00);
        this.setData(data);
    }

    public ResultData(boolean isSuccess, T data) {
        this.setNote(this.note);
        this.setState(isSuccess ? 11 : 00);
        this.setData(data);
    }

    public ResultData(boolean isSuccess, T data, String note) {
        this.setNote(note);
        this.setState(isSuccess ? 11 : 00);
        this.setData(data);
    }

    public ResultData(boolean isSuccess, T data, String note, String errorCode) {
        this.setNote(note);
        this.setState(isSuccess ? 11 : 00);
        this.setData(data);
        this.setErrorCode(errorCode);
    }

    public Boolean isSuccess() {
        return this.state == 11;
    }
}
