package com.github.peh.context;

import java.util.Optional;

import static com.github.peh.constants.Constants.*;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/09 9:59
 * @description:
 * @copyright 参数环境变量
 */
public class ParamContextHolder extends ContextHolder {

    public static void bindRequest(Object value) {
        bindLocal(REQUEST_PARAM, value);
    }

    public static Object getRequest() {
        return getLocal(REQUEST_PARAM);
    }

    public static <D> D getRequest(Class<D> clazz) {
        return (D) getLocal(REQUEST_PARAM);
    }

    public static void bindResponse(Object value) {
        bindLocal(RESPONSE_PARAM, value);
    }

    public static Object getResponse() {
        return getLocal(RESPONSE_PARAM);
    }

    public static <D> D getResponse(Class<D> clazz) {
        return (D) getLocal(RESPONSE_PARAM);
    }

    public static void bindTransmit(Object value) {
        bindLocal(TRANSMIT_PARAM, value);
    }

    public static Object getTransmit() {
        return getLocal(TRANSMIT_PARAM);
    }

    public static boolean check() {
        return Optional.ofNullable(getLocal(REQUEST_PARAM)).isPresent()
                &&
                Optional.ofNullable(getLocal(RESPONSE_PARAM)).isPresent();
    }
}
