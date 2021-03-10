package com.github.peh.context;

import com.github.peh.enums.LifeCycleEnums;
import com.github.peh.util.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.github.peh.constants.Constants.PROCESSOR_TERMINATE;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/08 17:26
 * @description: 处理器环境变量
 */
public class ProcessorContextHolder extends ContextHolder {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContextHolder.class);

    public static boolean available() {
        Object terminate = ContextHolder.get(PROCESSOR_TERMINATE);
        LOGGER.debug("[PROCESSOR-STATUS-CHECK] processor terminate key=[{}] status=[{}].",
                PROCESSOR_TERMINATE,
                (Boolean) Optional.ofNullable(terminate).orElse(false)
                        ? LifeCycleEnums.unable.getStage()
                        : LifeCycleEnums.RUNNING.getStage()
        );
        return (Boolean) Optional.ofNullable(terminate).orElse(false);
    }

    public static void unable() {
        unable("");
    }

    public static void unable(String handleNode) {
        ContextHolder.put(PROCESSOR_TERMINATE, Boolean.TRUE);
        ContextHolder.put(KeyGenerator.genProcessorTraceMarkKey(), handleNode);
        LOGGER.debug("[PROCESSOR-STATUS-unable] processor terminate=[{}] works , handle node=[{}]", PROCESSOR_TERMINATE, handleNode);
    }

    public static void enable() {
        enable("");
    }

    public static void enable(String handleNode) {
        ContextHolder.remove(PROCESSOR_TERMINATE);
        ContextHolder.remove(KeyGenerator.genProcessorTraceMarkKey());
        LOGGER.debug("[PROCESSOR-STATUS-enable] processor terminate=[{}] enable , handle node=[{}]", PROCESSOR_TERMINATE, handleNode);
    }
}
