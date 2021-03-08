package com.github.peh.processor;

import com.github.peh.exception.ConfigurationException;
import com.github.peh.handler.IHandler;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/07/10 13:49
 * @description: 流程处理器
 */
public class ProcessorEngine extends BaseProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessorEngine.class);

    protected volatile LinkedList<BaseProcessor> processors;

    @Override
    public void process() {
        Objects.requireNonNull(processors, "processors can not be null");

        //processors must keep execute serially by order
        for (BaseProcessor processor : processors) {
            if (isTerminate()) {
                LOGGER.debug("[ProcessorEngine] engine has stopped , it will stop all processors.");
                break;
            }

            LOGGER.debug("[ProcessorEngine] engine is running , current processor is 【{}】", processor.getProcessorName());
            processor.process();
        }
    }

    public static final class Builder {
        protected LinkedList<BaseProcessor> processors = Lists.newLinkedList();

        volatile BaseProcessor curProcessor = null;

        private Builder() {
        }

        public static Builder aBaseProcessor() {
            return new Builder();
        }

        //processor method

        public Builder processors(LinkedList<BaseProcessor> processors) {
            Objects.requireNonNull(processors, "processor can not be null");
            this.processors = processors;
            return this;
        }

        public Builder appendProcessor() {
            this.curProcessor = new BaseProcessor();
            this.processors.addLast(this.curProcessor);
            return this;
        }

        public Builder appendProcessor(BaseProcessor processor) {
            Objects.requireNonNull(processor, "processor can not be null");
            this.curProcessor = processor;
            this.processors.addLast(processor);
            return this;
        }

        public Builder preposeProcessor() {
            this.curProcessor = new BaseProcessor();
            this.processors.addFirst(this.curProcessor);
            return this;
        }

        public Builder preposeProcessor(BaseProcessor processor) {
            Objects.requireNonNull(processor, "processor can not be null");
            this.curProcessor = processor;
            this.processors.addFirst(processor);
            return this;
        }

        // executor method

        public Builder parallel() {
            this.curProcessor.parallel();
            return this;
        }

        public Builder serial() {
            this.curProcessor.serial();
            return this;
        }

        //handler method

        public Builder appendHandler(IHandler handler) {
            Objects.requireNonNull(handler, "handler can not be null");
            Objects.requireNonNull(this.curProcessor, "current processor can not be null , pleas config processor before handler");

            this.curProcessor.getExecutor().addLast(handler);
            return this;
        }

        public Builder preposeHandler(IHandler handler) {
            Objects.requireNonNull(handler, "handler can not be null");
            Objects.requireNonNull(this.curProcessor, "current processor can not be null , pleas config processor before handler");

            this.curProcessor.getExecutor().addFirst(handler);
            return this;
        }

        public ProcessorEngine build() {
            Objects.requireNonNull(this.processors, "processors can not be null");

            if (this.processors.size() == 0) {
                throw new ConfigurationException("processors can not be empty");
            }

            ProcessorEngine processorEngine = new ProcessorEngine();
            processorEngine.processors = this.processors;
            return processorEngine;
        }
    }
}
