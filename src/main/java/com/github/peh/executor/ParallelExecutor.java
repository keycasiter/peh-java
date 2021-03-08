package com.github.peh.executor;

import com.github.peh.handler.IHandler;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/02/25 19:50
 * @description: 并行处理器
 */
public class ParallelExecutor extends AbstractExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(ParallelExecutor.class);

    private ExecutorService executors = Executors.newFixedThreadPool(5);

    private final static int EXECUTOR_TIMEOUT_MILLISECONDS = 3000;

    public ParallelExecutor() {
        super("parallel executor");
    }

    public ParallelExecutor(String executorName) {
        super(executorName);
    }

    public ParallelExecutor(LinkedList<IHandler> handlers) {
        super(handlers);
    }

    @Override
    public void execute() {
        LOGGER.debug("[PARALLEL-EXECUTOR] works.");

        CountDownLatch cdl = new CountDownLatch(handlers.size());

        Optional.ofNullable(handlers)
                .orElse(Lists.newLinkedList())
                .forEach(handler -> {
                    executors.execute(() -> {
                        cdl.countDown();
                        handler.handle();
                    });
                });

        try {
            cdl.await(EXECUTOR_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
