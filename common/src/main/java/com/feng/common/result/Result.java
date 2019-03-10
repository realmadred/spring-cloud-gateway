package com.feng.common.result;

import com.feng.common.enums.Results;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    /**
     * 成功
     */
    public static Result newSuccessInstance() {
        return newSuccessInstance(null);
    }

    /**
     * 成功 + data
     */
    public static Result newSuccessInstance(Object data) {
        return new Result(Results.SUCCESS.getCode()
                , Results.SUCCESS.getMsg(), data);
    }

    /**
     * 失败
     */
    public static Result newErrorInstance(int code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * 获取实例
     */
    public static Result newInstance() {
        return new Result();
    }

    /**
     * 获取实例
     */
    public Result error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * 设置成功
     */
    public Result success() {
        return success(null);
    }

    /**
     * 设置成功 + data
     */
    public Result success(Object data) {
        this.code = Results.SUCCESS.getCode();
        this.msg = Results.SUCCESS.getMsg();
        this.data = data;
        return this;
    }
}
