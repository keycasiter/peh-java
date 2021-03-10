package com.github.peh.executor;

import com.github.peh.context.ExecutorContextHolder;
import com.github.peh.context.ProcessorContextHolder;
import com.github.peh.handler.IHandler;
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

    public boolean available(IExecutor executor) {
        return ExecutorContextHolder.available(executor) && ProcessorContextHolder.available();
    }

    public String getExecutorType() {
        if (this instanceof ParallelExecutor) {
            return ExecutorTypeEnums.PARALLEL.getType();
        }
        if (this instanceof SerialExecutor) {
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
        LOGGER.debug("[Executor] handler is [{}].", handlers.get(0).getClass().getName());
        return handlers.get(0);
    }

    public void addLast(IHandler handler) {
        handlers.addLast(handler);
    }

    public void addFirst(IHandler handler) {
        handlers.addFirst(handler);
    }

    enum ExecutorTypeEnums {

        /**
         * 串行化
         */
        SERIAL("serial", "串行化"),

        /**
         * 并行化
         */
        PARALLEL("parallel", "并行化"),

        ;

        /**
         * 类型
         */
        private String type;

        /**
         * 描述
         */
        private String desc;

        ExecutorTypeEnums(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
