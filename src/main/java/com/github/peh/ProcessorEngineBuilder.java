package com.github.peh;

import com.github.peh.context.ParamContextHolder;
import com.github.peh.exception.ConfigurationException;
import com.github.peh.handler.IHandler;
import com.github.peh.processor.BaseProcessor;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

/**
 * created by guanjian on 2021/3/10 16:54
 */
public final class ProcessorEngineBuilder {

    protected LinkedList<BaseProcessor> processors = Lists.newLinkedList();

    volatile BaseProcessor curProcessor = null;

    private ProcessorEngineBuilder() {
    }

    public static ProcessorEngineBuilder builder() {
        return new ProcessorEngineBuilder();
    }

    //param method

    public ProcessorEngineBuilder request(Object request) {
        Objects.requireNonNull(request, "request can not be null");
        ParamContextHolder.bindRequest(request);
        return this;
    }

    public ProcessorEngineBuilder response(Object response) {
        Objects.requireNonNull(response, "response can not be null");
        ParamContextHolder.bindResponse(response);
        return this;
    }

    //processor method

    public ProcessorEngineBuilder processors(LinkedList<BaseProcessor> processors) {
        Objects.requireNonNull(processors, "processor can not be null");
        this.processors = processors;
        return this;
    }

    public ProcessorEngineBuilder appendProcessor() {
        this.curProcessor = new BaseProcessor();
        this.processors.addLast(this.curProcessor);
        return this;
    }

    public ProcessorEngineBuilder appendProcessor(BaseProcessor processor) {
        Objects.requireNonNull(processor, "processor can not be null");
        this.curProcessor = processor;
        this.processors.addLast(processor);
        return this;
    }

    public ProcessorEngineBuilder appendProcessors(LinkedList<BaseProcessor> processors) {
        Objects.requireNonNull(processors, "processor can not be null");
        if (0 == processors.size()) throw new ConfigurationException("processor can not be empty.");

        Optional.ofNullable(processors)
                .orElse(Lists.newLinkedList())
                .forEach(processor -> {
                    appendProcessor(processor);
                });

        return this;
    }

    public ProcessorEngineBuilder preposeProcessor() {
        this.curProcessor = new BaseProcessor();
        this.processors.addFirst(this.curProcessor);
        return this;
    }

    public ProcessorEngineBuilder preposeProcessor(BaseProcessor processor) {
        Objects.requireNonNull(processor, "processor can not be null");
        this.curProcessor = processor;
        this.processors.addFirst(processor);
        return this;
    }

    public ProcessorEngineBuilder preposeProcessors(LinkedList<BaseProcessor> processors) {
        Objects.requireNonNull(processors, "processor can not be null");
        if (0 == processors.size()) throw new ConfigurationException("processor can not be empty.");

        Optional.ofNullable(processors)
                .orElse(Lists.newLinkedList())
                .forEach(processor -> {
                    preposeProcessor(processor);
                });

        return this;
    }

    // executor method

    public ProcessorEngineBuilder parallel() {
        this.curProcessor.parallel();
        return this;
    }

    public ProcessorEngineBuilder serial() {
        this.curProcessor.serial();
        return this;
    }

    //handler method

    public ProcessorEngineBuilder appendHandler(IHandler handler) {
        Objects.requireNonNull(handler, "handler can not be null");
        Objects.requireNonNull(this.curProcessor, "current processor can not be null , pleas config processor before handler");

        this.curProcessor.getExecutor().addLast(handler);
        return this;
    }

    public ProcessorEngineBuilder preposeHandler(IHandler handler) {
        Objects.requireNonNull(handler, "handler can not be null");
        Objects.requireNonNull(this.curProcessor, "current processor can not be null , pleas config processor before handler");

        this.curProcessor.getExecutor().addFirst(handler);
        return this;
    }

    public ProcessorEngine build() {
        //check
        if (!ParamContextHolder.check()) {
            throw new ConfigurationException("please check request param or response param setting in processorEngine.");
        }
        if (this.processors.size() == 0) {
            throw new ConfigurationException("processors can not be empty");
        }

        ProcessorEngine processorEngine = new ProcessorEngine();
        processorEngine.processors = this.processors;
        return processorEngine;
    }
}
