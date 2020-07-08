package com.xie.myblog.bto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 谢
 * @time: 2020/6/26 21:35
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BtoResult<T> {
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 传输的数据
     */
    private T data;
    /**
     * 传输数据是否成功
     */
    private boolean flag;

    public BtoResult(boolean flag, String msg, T data) {
        this.msg = msg;
        this.data = data;
        this.flag = flag;
    }

    public BtoResult(boolean flag, String msg) {
        this.msg = msg;
        this.flag = flag;
    }

    public BtoResult(String msg) {
        this.msg = msg;
    }

    public BtoResult() {
    }
}
