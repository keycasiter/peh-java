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
public final class PehEngineBuilder {

    protected LinkedList<BaseProcessor> processors = Lists.newLinkedList();

    volatile BaseProcessor curProcessor = null;

    private PehEngineBuilder() {
    }

    public static PehEngineBuilder builder() {
        return new PehEngineBuilder();
    }

    //param method

    public PehEngineBuilder request(Object request) {
        Objects.requireNonNull(request, "request can not be null");
        ParamContextHolder.setRequest(request);
        return this;
    }

    public PehEngineBuilder response(Object response) {
        Objects.requireNonNull(response, "response can not be null");
        ParamContextHolder.setResponse(response);
        return this;
    }

    //processor method

    public PehEngineBuilder processors(LinkedList<BaseProcessor> processors) {
        Objects.requireNonNull(processors, "processor can not be null");
        this.processors = processors;
        return this;
    }

    public PehEngineBuilder appendProcessor() {
        this.curProcessor = new BaseProcessor();
        this.processors.addLast(this.curProcessor);
        return this;
    }

    public PehEngineBuilder appendProcessor(BaseProcessor processor) {
        Objects.requireNonNull(processor, "processor can not be null");
        this.curProcessor = processor;
        this.processors.addLast(processor);
        return this;
    }

    public PehEngineBuilder appendProcessors(LinkedList<BaseProcessor> processors) {
        Objects.requireNonNull(processors, "processor can not be null");
        if (0 == processors.size()) throw new ConfigurationException("processor can not be empty.");

        Optional.ofNullable(processors)
                .orElse(Lists.newLinkedList())
                .forEach(processor -> {
                    appendProcessor(processor);
                });

        return this;
    }

    public PehEngineBuilder preposeProcessor() {
        this.curProcessor = new BaseProcessor();
        this.processors.addFirst(this.curProcessor);
        return this;
    }

    public PehEngineBuilder preposeProcessor(BaseProcessor processor) {
        Objects.requireNonNull(processor, "processor can not be null");
        this.curProcessor = processor;
        this.processors.addFirst(processor);
        return this;
    }

    public PehEngineBuilder preposeProcessors(LinkedList<BaseProcessor> processors) {
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

    public PehEngineBuilder parallel() {
        this.curProcessor.parallel();
        return this;
    }

    public PehEngineBuilder serial() {
        this.curProcessor.serial();
        return this;
    }

    //handler method

    public PehEngineBuilder appendHandler(IHandler handler) {
        Objects.requireNonNull(handler, "handler can not be null");
        Objects.requireNonNull(this.curProcessor, "current processor can not be null , pleas config processor before handler");

        this.curProcessor.getExecutor().addLast(handler);
        return this;
    }

    public PehEngineBuilder preposeHandler(IHandler handler) {
        Objects.requireNonNull(handler, "handler can not be null");
        Objects.requireNonNull(this.curProcessor, "current processor can not be null , pleas config processor before handler");

        this.curProcessor.getExecutor().addFirst(handler);
        return this;
    }

    public PehEngine build() {
        if (this.processors.size() == 0) {
            throw new ConfigurationException("processors can not be empty");
        }

        PehEngine processorEngine = new PehEngine();
        processorEngine.processors = this.processors;
        return processorEngine;
    }
}
