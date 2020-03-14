package com.hwk.seckill.common.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用请求应答结果类型
 * @param <T>
 * @author hwk
 * @since
 */
@Data
@ToString
public class BaseResponse<T> implements Serializable {

    /**
     * 应答状态码
     */
    private int code;

    /**
     * 应答信息
     */
    private String msg;

    /**
     * 应答数据
     */
    private T data;


    /**
     * 响应成功应答
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> ok(T data){
        return new BaseResponse<>(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(),data);
    }

    /**
     * 响应错误应答
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(CodeMsg codeMsg){
        return new BaseResponse<>(codeMsg);
    }


    /**
     * 构造方法
     */
    public BaseResponse(){}

    /**
     * 构造方法
     * @param code
     * @param msg
     * @param data
     */
    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 构造方法
     * @param codeMsg
     */
    public BaseResponse(CodeMsg codeMsg){
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }
}
