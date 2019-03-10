package com.feng.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页参数
 * @author liufeng
 * @date 2019年3月10日
 */
@Getter
public class PageParam implements Serializable {

    private final int page;
    private final int size;

    public PageParam(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        } else if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        } else {
            this.page = page;
            this.size = size;
        }
    }
}
