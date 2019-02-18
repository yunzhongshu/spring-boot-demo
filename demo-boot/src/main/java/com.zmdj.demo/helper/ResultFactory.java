package com.zmdj.demo.helper;

import com.zmdj.demo.domain.res.Result;

public class ResultFactory {

    public static <T> Result<T> succeed() {
        return new Result<>(true);
    }

    public static <T> Result<T> succeed(T data) {
        return new Result<T>().succeed(data);
    }

    /**
     * 设置errKey,errMsg/errDebugMsg从error-code-service获取
     * @param errKey 错误码配置项对应的key值；
     * @param <T>
     * @return
     */
    public static <T> Result<T> failed(String errKey) {
        return new Result<>(false).failed(errKey);
    }

    public static <T> Result<T> failed(String errKey, String errDebugMsg, T t) {
        return new Result<>(false).failed(errKey,errDebugMsg,t);
    }

    public static <T> Result<T> failed(String errKey, String errMsg, String errDebugMsg) {
        return new Result<>(false).failed(errKey, errMsg, errDebugMsg);
    }

    public static <T> Result<T> failed(String errKey, String errCode, String errMsg, String errDebugMsg) {
        return new Result<>(false).failed(errKey, errCode, errMsg, errDebugMsg);
    }

    public static <T> Result<T> failed(String errKey, String errMsg, String errDebugMsg, T t) {
        return new Result<>(false).failed(errKey, errMsg,errDebugMsg, t);
    }

    /**
     * 设置errKey/errDebugMsg,errMsg从error-code-service获取
     * @param errKey 错误码配置项对应的key值;
     * @param errDebugMsg debug错误信息
     * @param <T>
     * @return
     */
    public static <T> Result<T> failed(String errKey, String errDebugMsg) {
        return new Result<>(false).failed(errKey,errDebugMsg);
    }
}
