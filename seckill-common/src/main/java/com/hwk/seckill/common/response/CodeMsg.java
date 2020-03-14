package com.hwk.seckill.common.response;


import java.io.Serializable;

/**
 * 状态信息码
 * @author hwk
 * @since
 */
public class CodeMsg implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;


    /**
     * 通用类型
     */
    public static CodeMsg SUCCESS = new CodeMsg(0,"successs");


    /**
     * 构造方法
     * @param code
     * @param msg
     */
    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
