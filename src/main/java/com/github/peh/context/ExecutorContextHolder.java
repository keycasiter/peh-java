package com.github.peh.context;

import com.github.peh.executor.IExecutor;
import com.github.peh.lifeCycle.LifeCycleEnums;
import com.github.peh.util.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/08 17:25
 * @description: 执行器环境变量
 */
public class ExecutorContextHolder extends ContextHolder {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContextHolder.class);

    public static boolean available(IExecutor executor) {
        String blockKey = KeyGenerator.genExecutorTerminateKey(executor);
        Object blockValue = ContextHolder.get(blockKey);
        LOGGER.debug("[EXECUTOR-STATUS-CHECK] executor block key=[{}] status=[{}].",
                blockKey,
                (Boolean) Optional.ofNullable(blockValue).orElse(Boolean.TRUE)
                        ? LifeCycleEnums.RUNNING.getStage() :
                        LifeCycleEnums.STOP.getStage()
        );
        return (Boolean) Optional.ofNullable(blockValue).orElse(Boolean.TRUE);
    }

    public static void unable(IExecutor executor) {
        unable(executor, "");
    }

    public static void unable(IExecutor executor, String handleNode) {
        String blockKey = KeyGenerator.genExecutorTerminateKey(executor);
        ContextHolder.put(blockKey, Boolean.FALSE);
        ContextHolder.put(KeyGenerator.genExecutorTraceMarkKey(), handleNode);
        LOGGER.debug("[EXECUTOR-STATUS-unable] executor block=[{}] unable , handle node=[{}]", blockKey, handleNode);
    }

    public static void enable(IExecutor executor) {
        enable(executor, "");
    }

    public static void enable(IExecutor executor, String handleNode) {
        String blockKey = KeyGenerator.genExecutorTerminateKey(executor);
        ContextHolder.put(blockKey, Boolean.TRUE);
        ContextHolder.remove(KeyGenerator.genExecutorTraceMarkKey());
        LOGGER.debug("[EXECUTOR-STATUS-enable] executor block=[{}] enable , handle node=[{}]", blockKey, handleNode);
    }
}
