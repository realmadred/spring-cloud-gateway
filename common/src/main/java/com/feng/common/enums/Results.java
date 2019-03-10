package com.feng.common.enums;

import lombok.Data;

/**
 * 返回结果
 *
 * @author liufeng
 * @date 2019年3月10日
 */
public enum Results {

    SUCCESS(200, "成功"),
    ERROR(500, "失败");

    Results(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
