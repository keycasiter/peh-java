package com.github.peh.processor;

import com.github.peh.context.ParamContextHolder;
import com.github.peh.context.ProcessorContextHolder;
import com.github.peh.executor.AbstractExecutor;
import com.github.peh.executor.ParallelExecutor;
import com.github.peh.executor.SerialExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/10 14:46
 * @description: 基础处理器
 */
public class BaseProcessor implements IProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseProcessor.class);

    /**
     * 处理器名称
     */
    protected String processorName = "default processor";
    /**
     * 执行器
     */
    protected AbstractExecutor executor = new SerialExecutor();

    public BaseProcessor() {
    }

    public BaseProcessor(String processorName) {
        this.processorName = processorName;
    }

    public BaseProcessor(AbstractExecutor executor) {
        this.executor = executor;
    }

    public BaseProcessor(String processorName, AbstractExecutor executor) {
        this.processorName = processorName;
        this.executor = executor;
    }

    @Override
    public void configurate() {

    }

    @Override
    public void process() {
        Objects.requireNonNull(this.executor, "executor can not be null , processor needs it to work.");
        LOGGER.debug("[PROCESSOR-PROCESS] processorName=[{}] , executorType=[{}] , executorName=[{}]", processorName, executor.getExecutorType(), executor.getExecutorName());
        executor.execute();
    }

    public boolean available() {
        return ProcessorContextHolder.available();
    }

    // executor change method
    public void executor(AbstractExecutor executor) {
        this.executor = executor;
    }

    public void parallel() {
        this.executor = new ParallelExecutor(this.executor.getHandlers());
    }

    public void serial() {
        this.executor = new SerialExecutor(this.executor.getHandlers());
    }

    //get set method

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public AbstractExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(AbstractExecutor executor) {
        this.executor = executor;
    }


    public static final class Builder {
        private AbstractExecutor executor = new SerialExecutor();

        private Builder() {
        }

        public static Builder aBaseProcessor() {
            return new Builder();
        }

        public Builder executor(AbstractExecutor executor) {
            this.executor = executor;
            return this;
        }

        public BaseProcessor build() {
            BaseProcessor baseProcessor = new BaseProcessor();
            baseProcessor.setExecutor(executor);
            return baseProcessor;
        }
    }
}
