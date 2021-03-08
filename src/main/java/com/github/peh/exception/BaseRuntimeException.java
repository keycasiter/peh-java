package com.github.peh.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/4/13 20:01
 * @description: 异常基础类
 */
public class BaseRuntimeException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6769235192102128204L;

    /**
     * 异常描述
     */
    private String showMessage;

    /**
     * 异常原因
     */
    private Throwable cause;

    public BaseRuntimeException() {
    }

    public BaseRuntimeException(String errorMessage) {
        super(errorMessage);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String showMessage, Throwable cause) {
        super(showMessage, cause);
        this.showMessage = showMessage;
        this.cause = cause;
    }

    public BaseRuntimeException(String errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public BaseRuntimeException(String errorCode, String errorMessage, String showMessage, Throwable cause) {
        super(showMessage, cause);
        this.cause = cause;
        this.showMessage = showMessage;
    }

    public String getShowMessage() {
        if (this.showMessage == null) {
            return super.getMessage();
        }
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    /**
     * 获取异常信息
     *
     * @return
     */
    @Override
    public String getMessage() {
        String msg = super.getMessage();
        String causeMsg = null;
        if (this.cause != null) {
            causeMsg = this.cause.getMessage();
        }
        if (msg != null) {
            if (causeMsg != null) {
                return msg + " caused by: " + causeMsg;
            }
            return msg;
        }
        return causeMsg;
    }

    /**
     * Description:获取异常堆栈信息
     *
     * @return
     */
    public String getErrorStack() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bo);
        printStackTrace(ps);
        String errorStack = new String(bo.toByteArray());
        return errorStack;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        if (this.cause == null) {
            return super.toString();
        }
        return super.toString() + " caused by: " + this.cause.toString();
    }
}
