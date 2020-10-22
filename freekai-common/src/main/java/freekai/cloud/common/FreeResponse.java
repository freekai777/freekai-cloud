package freekai.cloud.common;

import java.io.Serializable;

/**
 * 接口传输消息封装
 * @param <T>
 */
public class FreeResponse<T> implements Serializable {

    private Integer code;
    private T data;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public FreeResponse<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public FreeResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public FreeResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public FreeResponse() {
    }

    private FreeResponse(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> FreeResponse ok(T data){
        return new FreeResponse(200, data, "ok" );
    }

    public static <T> FreeResponse fail(T data){
        return new FreeResponse(400, data, "fail" );
    }
}
