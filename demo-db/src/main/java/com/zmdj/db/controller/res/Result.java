package com.zmdj.db.controller.res;

import java.io.Serializable;


public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3774852590539435668L;
    /**
     * 业务是否成功；默认失败；
     */
    private boolean success = false;

    /**
     * 错误域
     */
    private String errCode;

    /**
     * 错误信息，即提示文案
     */
    private String errMsg;

    /**
     * debug详细错误信息，开发和测试环境才会输出此字段
     */
    private String errDebugMsg;

    private String errKey;

    /**
     * 业务数据实例对象
     */
    private T data;


    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public Result setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrDebugMsg() {
        return errDebugMsg;
    }

    public Result setErrDebugMsg(String errDebugMsg) {
        this.errDebugMsg = errDebugMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * 获取成功对象
     *
     * @return Result 成功实例对象；
     */
    public Result succeed() {
        this.setSuccess(true);
        return this;
    }

    /**
     * 获取成功对象
     *
     * @param data 业务对象
     * @return Result 成功实例对象；
     */
    public Result<T> succeed(T data) {
        this.setSuccess(true).setData(data);
        return this;
    }

    /**
     * 获取失败对象
     *
     * @param errKey 错误码配置项对应的key值；
     * @return Result 失败实例对象；
     */
    public Result failed(String errKey) {
        this.setSuccess(false).setErrKey(errKey);
        return this;
    }

    /**
     * 获取失败对象
     *
     * @param errKey      错误码配置项对应的key值；
     * @param errDebugMsg debug错误信息
     * @return Result 失败实例对象；
     */
    public Result failed(String errKey, String errDebugMsg) {
        this.setSuccess(false).setErrKey(errKey).setErrDebugMsg(errDebugMsg);
        return this;
    }

    public Result failed(String errKey, String errDebugMsg, T t) {
        this.setSuccess(false).setErrKey(errKey).setErrDebugMsg(errDebugMsg).setData(t);
        return this;
    }

    public Result failed(String errKey, String errMsg, String errDebugMsg) {
        this.setSuccess(false).setErrKey(errKey).setErrMsg(errMsg).setErrDebugMsg(errDebugMsg);
        return this;
    }

    public Result failed(String errKey, String errMsg, String errDebugMsg, T t) {
        this.setSuccess(false).setErrKey(errKey).setErrMsg(errMsg).setErrDebugMsg(errDebugMsg).setData(t);
        return this;
    }

    public Result failed(String errCode, String errKey, String errMsg, String errDebugMsg) {
        this.setSuccess(false).setErrCode(errCode)
            .setErrKey(errKey).setErrMsg(errMsg)
            .setErrDebugMsg(errDebugMsg);
        return this;
    }

    public Result failed(String errCode, String errKey, String errMsg, String errDebugMsg,T t) {
        this.setSuccess(false).setErrCode(errCode)
                .setErrKey(errKey).setErrMsg(errMsg)
                .setErrDebugMsg(errDebugMsg)
                .setData(t);
        return this;
    }

    public String getErrKey() {
        return errKey;
    }

    public Result setErrKey(String errKey) {
        this.errKey = errKey;
        return this;
    }
}
