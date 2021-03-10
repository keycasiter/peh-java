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

    public static boolean available(IExecutor executor) {
        String terminateKey = KeyGenerator.genExecutorTerminateKey(executor);
        Object terminateValue = ContextHolder.get(terminateKey);
        LOGGER.debug("[EXECUTOR-STATUS-CHECK] executor terminate key=[{}] status=[{}].",
                terminateKey,
                (Boolean) Optional.ofNullable(terminateValue).orElse(false)
                        ? LifeCycleEnums.unable.getStage() :
                        LifeCycleEnums.RUNNING.getStage()
        );
        return (Boolean) Optional.ofNullable(terminateValue).orElse(false);
    }

    public static void unable(IExecutor executor) {
        unable(executor, "");
    }

    public static void unable(IExecutor executor, String handleNode) {
        String terminateKey = KeyGenerator.genExecutorTerminateKey(executor);
        ContextHolder.put(terminateKey, Boolean.TRUE);
        ContextHolder.put(KeyGenerator.genExecutorTraceMarkKey(), handleNode);
        LOGGER.debug("[EXECUTOR-STATUS-unable] executor terminate=[{}] unable , handle node=[{}]", terminateKey, handleNode);
    }

    public static void enable(IExecutor executor) {
        enable(executor, "");
    }

    public static void enable(IExecutor executor, String handleNode) {
        String terminateKey = KeyGenerator.genExecutorTerminateKey(executor);
        ContextHolder.remove(terminateKey);
        ContextHolder.remove(KeyGenerator.genExecutorTraceMarkKey());
        LOGGER.debug("[EXECUTOR-STATUS-enable] executor terminate=[{}] enable , handle node=[{}]", terminateKey, handleNode);
    }
}
