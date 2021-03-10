package com.github.peh.context;

import com.github.peh.executor.IExecutor;
import com.github.peh.enums.LifeCycleEnums;
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

    public static boolean isTerminate(IExecutor executor) {
        String terminateKey = KeyGenerator.genExecutorTerminateKey(executor);
        Object terminateValue = ContextHolder.getLocal(terminateKey);
        LOGGER.debug("[EXECUTOR-STATUS-CHECK] executor terminate key=[{}] status=[{}].",
                terminateKey,
                (Boolean) Optional.ofNullable(terminateValue).orElse(false)
                        ? LifeCycleEnums.STOP.getStage() :
                        LifeCycleEnums.RUNNING.getStage()
        );
        return (Boolean) Optional.ofNullable(terminateValue).orElse(false);
    }

    public static void stop(IExecutor executor) {
        stop(executor, "");
    }

    public static void stop(IExecutor executor, String handleNode) {
        String terminateKey = KeyGenerator.genExecutorTerminateKey(executor);
        ContextHolder.bindLocal(terminateKey, Boolean.TRUE);
        ContextHolder.bindLocal(KeyGenerator.genExecutorTraceMarkKey(), handleNode);
        LOGGER.debug("[EXECUTOR-STATUS-STOP] executor terminate=[{}] stop , handle node=[{}]", terminateKey, handleNode);
    }

    public static void resume(IExecutor executor) {
        resume(executor, "");
    }

    public static void resume(IExecutor executor, String handleNode) {
        String terminateKey = KeyGenerator.genExecutorTerminateKey(executor);
        ContextHolder.remove(terminateKey);
        ContextHolder.remove(KeyGenerator.genExecutorTraceMarkKey());
        LOGGER.debug("[EXECUTOR-STATUS-RESUME] executor terminate=[{}] resume , handle node=[{}]", terminateKey, handleNode);
    }
}
