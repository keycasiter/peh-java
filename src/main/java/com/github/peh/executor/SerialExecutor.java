package com.github.peh.executor;

import com.github.peh.handler.IHandler;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Optional;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/02/25 19:50
 * @description: 串行处理器
 */
public class SerialExecutor extends AbstractExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(SerialExecutor.class);

    public SerialExecutor() {
        super("serial executor");
    }

    public SerialExecutor(String executorName) {
        super(executorName);
    }

    public SerialExecutor(LinkedList<IHandler> handlers) {
        super(handlers);
    }

    @Override
    public void execute() {
        LOGGER.debug("[SERIAL-EXECUTOR] works.");

        Optional.ofNullable(handlers)
                .orElse(Lists.newLinkedList())
                .forEach(handler -> {
                    if (available(this)) {
                        LOGGER.debug("[SERIAL-EXECUTOR] block is unable status , it will unable all handlers.");
                        return;
                    }

                    handler.handle();
                });
    }
}
