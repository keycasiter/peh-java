package com.github.peh.context;

import com.github.peh.state.LifeCycleEnums;
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

    public static boolean isTerminate() {
        Object terminate = ContextHolder.getLocal(PROCESSOR_TERMINATE);
        LOGGER.debug("[PROCESSOR-STATUS-CHECK] processor terminate key=【{}】 status=【{}】.",
                PROCESSOR_TERMINATE,
                (Boolean) Optional.ofNullable(terminate).orElse(false)
                        ? LifeCycleEnums.STOP.getStage()
                        : LifeCycleEnums.RUNNING.getStage()
        );
        return (Boolean) Optional.ofNullable(terminate).orElse(false);
    }

    public static void stop() {
        stop("");
    }

    public static void stop(String handleNode) {
        ContextHolder.bindLocal(PROCESSOR_TERMINATE, Boolean.TRUE);
        ContextHolder.bindLocal(KeyGenerator.genProcessorTraceMarkKey(), handleNode);
        LOGGER.debug("[PROCESSOR-STATUS-STOP] processor terminate=【{}】 works , handle node=【{}】", PROCESSOR_TERMINATE, handleNode);
    }

    public static void resume() {
        resume("");
    }

    public static void resume(String handleNode) {
        ContextHolder.remove(PROCESSOR_TERMINATE);
        ContextHolder.remove(KeyGenerator.genProcessorTraceMarkKey());
        LOGGER.debug("[PROCESSOR-STATUS-RESUME] processor terminate=【{}】 resume , handle node=【{}】", PROCESSOR_TERMINATE, handleNode);
    }
}
