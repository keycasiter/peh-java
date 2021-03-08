package com.github.peh.executor;

import com.github.peh.context.ExecutorContextHolder;
import com.github.peh.context.ProcessorContextHolder;
import com.github.peh.handler.IHandler;
import com.github.peh.state.ExecutorTypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author: <a href=mailto:keycaster@qq.com>guanjian</a>
 * @date: 2021/02/26 15:58
 * @description: 执行器抽象类
 */
public abstract class AbstractExecutor implements IExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractExecutor.class);

    /**
     * 执行器名称
     */
    private String executorName = "default executor";

    /**
     * 执行器集合
     */
    protected volatile LinkedList<IHandler> handlers = new LinkedList();

    public AbstractExecutor() {
    }

    public AbstractExecutor(String executorName) {
        this.executorName = executorName;
    }

    public AbstractExecutor(LinkedList<IHandler> handlers) {
        Objects.requireNonNull(handlers, "handlers can not be empty.");

        this.handlers = handlers;
    }

    public AbstractExecutor(String executorName, LinkedList<IHandler> handlers) {
        this.executorName = executorName;
        this.handlers = handlers;
    }

    public boolean isTerminate(IExecutor executor) {
        return ExecutorContextHolder.isTerminate(executor) || ProcessorContextHolder.isTerminate();
    }

    public String getExecutorType() {
        if (this instanceof ParallelExecutor){
            return ExecutorTypeEnums.PARALLEL.getType();
        }
        if (this instanceof SerialExecutor){
            return ExecutorTypeEnums.SERIAL.getType();
        }

        return "undefined executor type";
    }

    // handler method


    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public LinkedList<IHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(LinkedList<IHandler> handlers) {
        this.handlers = handlers;
    }

    public IHandler getHandler() {
        Objects.requireNonNull(handlers, "handlers can not be empty.");
        LOGGER.debug("[Executor] handler is 【{}】.", handlers.get(0).getClass().getName());
        return handlers.get(0);
    }

    public void addLast(IHandler handler) {
        handlers.addLast(handler);
    }

    public void addFirst(IHandler handler) {
        handlers.addFirst(handler);
    }
}
