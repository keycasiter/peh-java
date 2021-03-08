package com.github.peh.exception;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/04/29 18:53
 * @description: 配置错误
 */
public class ConfigurationException extends BaseRuntimeException {

    public ConfigurationException() {
    }

    public ConfigurationException(String errorMessage) {
        super(errorMessage);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String showMessage, Throwable cause) {
        super(showMessage, cause);
    }

    public ConfigurationException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }

    public ConfigurationException(String errorCode, String errorMessage, String showMessage, Throwable cause) {
        super(errorCode, errorMessage, showMessage, cause);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
