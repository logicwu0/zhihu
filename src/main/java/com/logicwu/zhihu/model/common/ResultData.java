package com.logicwu.zhihu.model.common;

public class ResultData {
    private boolean success;
    private String code;
    private String message;
    private Object data;

    public ResultData() {
        this.success = true;
        this.code = "200";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
