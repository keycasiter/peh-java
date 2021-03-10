package com.github.peh.context;

import java.util.Optional;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/09 9:59
 * @description:
 * @copyright 参数环境变量
 */
public class ParamContextHolder extends ContextHolder {

    /**
     * 入参对象
     */
    public final static String REQUEST_PARAM = "REQUEST_PARAM";

    /**
     * 出参对象
     */
    public final static String RESPONSE_PARAM = "RESPONSE_PARAM";

    public static void setRequest(Object value) {
        put(REQUEST_PARAM, value);
    }

    public static Object getRequest() {
        return get(REQUEST_PARAM);
    }

    public static <D> D getRequest(Class<D> clazz) {
        return (D) get(REQUEST_PARAM);
    }

    public static void setResponse(Object value) {
        put(RESPONSE_PARAM, value);
    }

    public static Object getResponse() {
        return get(RESPONSE_PARAM);
    }

    public static <D> D getResponse(Class<D> clazz) {
        return (D) get(RESPONSE_PARAM);
    }

    public static boolean check() {
        return Optional.ofNullable(get(REQUEST_PARAM)).isPresent()
                &&
                Optional.ofNullable(get(RESPONSE_PARAM)).isPresent();
    }
}
