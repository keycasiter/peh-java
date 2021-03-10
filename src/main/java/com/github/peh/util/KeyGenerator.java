package com.github.peh.util;

import com.github.peh.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        return Constants.EXECUTOR_BLOCK + Constants.LINE + uniqueObj.hashCode();
    }

    /**
     * 生成Executor Trace Mark Key
     *
     * @param
     * @return
     */
    public static String genExecutorTraceMarkKey() {
        return Constants.EXECUTOR_TRACE_MARK;
    }

    /**
     * 生成Processor Terminate Key
     *
     * @param uniqueObj
     * @return
     */
    public static String genProcessorTerminateKey(Object uniqueObj) {
        return Constants.PROCESSOR_BLOCK + Constants.LINE + uniqueObj.hashCode();
    }

    /**
     * 生成Processor Trace Mark Key
     *
     * @return
     */
    public static String genProcessorTraceMarkKey() {
        return Constants.PROCESSOR_TRACE_MARK;
    }

}
