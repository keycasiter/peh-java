package com.github.peh.context;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.github.peh.constants.Constants.*;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/07/08 9:31
 * @description: 环境变量
 */
public class ContextHolder {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContextHolder.class);

    /**
     * 线程变量
     */
    private final static ThreadLocal<Map<Object, Object>> THREAD_LOCAL_VARIABLE = ThreadLocal.withInitial(() -> Maps.newConcurrentMap());

    public static void bindLocal(Object key, Object value) {
        Objects.requireNonNull(key, "key can not be null");

        Map holder = THREAD_LOCAL_VARIABLE.get();

        holder.put(key, value);

        THREAD_LOCAL_VARIABLE.set(holder);

        LOGGER.debug("[CONTEXT-HOLDER] key=[{}],value=[{}] binded.", key, JSON.toJSONString(value));
    }

    public static Object getLocal(Object key) {
        if (!Optional.ofNullable(THREAD_LOCAL_VARIABLE.get()).isPresent()) {
            return null;
        }

        Object value = THREAD_LOCAL_VARIABLE.get().get(key);

        LOGGER.debug("[CONTEXT-HOLDER] key=[{}],value=[{}] getted.", key, JSON.toJSONString(value));
        return value;
    }

    public static void remove(Object key) {
        THREAD_LOCAL_VARIABLE.get().remove(key);
    }

    public static void clear() {
        THREAD_LOCAL_VARIABLE.remove();
    }

}
