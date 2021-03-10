package com.github.peh.util;

import com.github.peh.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.peh.constants.Constants.*;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/08 16:23
 * @description: Key生成器
 */
public class KeyGenerator {

    private final static Logger LOGGER = LoggerFactory.getLogger(KeyGenerator.class);

    /**
     * 生成Executor Terminate Key
     *
     * @param uniqueObj
     * @return
     */
    public static String genExecutorTerminateKey(Object uniqueObj) {
        return EXECUTOR_BLOCK + Constants.LINE + uniqueObj.hashCode();
    }

    /**
     * 生成Executor Trace Mark Key
     *
     * @param
     * @return
     */
    public static String genExecutorTraceMarkKey() {
        return EXECUTOR_TRACE_MARK;
    }

    /**
     * 生成Processor Terminate Key
     *
     * @param uniqueObj
     * @return
     */
    public static String genProcessorTerminateKey(Object uniqueObj) {
        return PROCESSOR_BLOCK + Constants.LINE + uniqueObj.hashCode();
    }

    /**
     * 生成Processor Trace Mark Key
     *
     * @return
     */
    public static String genProcessorTraceMarkKey() {
        return PROCESSOR_TRACE_MARK;
    }

}
