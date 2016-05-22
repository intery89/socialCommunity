package com.wwb.demo.utils.result;

import com.wwb.demo.utils.result.enums.ResultResponseCode;

public class ResultResponse<T> {
    private Status status;
    private Result<T> result;

    public ResultResponse() {

    }

    public ResultResponse(ResultResponseCode resultResponseCode, T data) {
        this.status = new Status();
        this.status.setCode(resultResponseCode.getCode());
        this.status.setMsg(resultResponseCode.getMsg());
        this.result = new Result();
        this.result.setData(data);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Result<T> getResult() {
        return result;
    }

    public void setResult(Result<T> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return status != null && status.getCode().equals(ResultResponseCode.SUCCESS.getCode());
    }
}
