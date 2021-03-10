package com.github.peh;

import com.github.peh.context.ParamContextHolder;
import com.github.peh.exception.ConfigurationException;
import com.github.peh.handler.IHandler;
import com.github.peh.lifeCycle.ILifeCycle;
import com.github.peh.lifeCycle.LifeCycleEnums;
import com.github.peh.processor.BaseProcessor;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/07/10 13:49
 * @description: 处理器引擎
 */
public class ProcessorEngine extends BaseProcessor implements ILifeCycle {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessorEngine.class);

    protected volatile LinkedList<BaseProcessor> processors;

    @Override
    public void process() {
        Objects.requireNonNull(processors, "processors can not be null");

        //processors must keep execute serially by order
        for (BaseProcessor processor : processors) {
            if (!available()) {
                LOGGER.debug("[ProcessorEngine] engine has stopped , it will unable all processors.");
                break;
            }

            LOGGER.debug("[ProcessorEngine] engine is running , current processor is [{}]", processor.getProcessorName());
            processor.process();
        }
    }

    @Override
    public Object process(Object request) {
        ParamContextHolder.bindRequest(request);

        process();

        return ParamContextHolder.getResponse();
    }

    @Override
    public void initial() {

    }

    @Override
    public void running() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

    @Override
    public void exception() {

    }

    @Override
    public LifeCycleEnums getLifeCycle() {
        return null;
    }
}
