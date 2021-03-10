package com.github.peh.context;

import com.github.peh.lifeCycle.LifeCycleEnums;
import com.github.peh.util.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.github.peh.constants.Constants.PROCESSOR_BLOCK;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/08 17:26
 * @description: 处理器环境变量
 */
public class ProcessorContextHolder extends ContextHolder {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContextHolder.class);

    public static boolean available() {
        Object block = ContextHolder.get(PROCESSOR_BLOCK);
        LOGGER.debug("[PROCESSOR-STATUS-CHECK] processor block key=[{}] status=[{}].",
                PROCESSOR_BLOCK,
                (Boolean) Optional.ofNullable(block).orElse(Boolean.TRUE)
                        ? LifeCycleEnums.RUNNING.getStage()
                        : LifeCycleEnums.STOP.getStage()
        );
        return (Boolean) Optional.ofNullable(block).orElse(Boolean.TRUE);
    }

    public static void unable() {
        unable("");
    }

    public static void unable(String handleNode) {
        ContextHolder.put(PROCESSOR_BLOCK, Boolean.FALSE);
        ContextHolder.put(KeyGenerator.genProcessorTraceMarkKey(), handleNode);
        LOGGER.debug("[PROCESSOR-STATUS-enable] processor block=[{}] enable , handle node=[{}]", PROCESSOR_BLOCK, handleNode);
    }

    public static void enable() {
        enable("");
    }

    public static void enable(String handleNode) {
        ContextHolder.put(PROCESSOR_BLOCK, Boolean.TRUE);
        ContextHolder.remove(KeyGenerator.genProcessorTraceMarkKey());
        LOGGER.debug("[PROCESSOR-STATUS-unable] processor block=[{}] works , handle node=[{}]", PROCESSOR_BLOCK, handleNode);
    }
}
