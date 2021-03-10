package com.github.peh;

import com.github.peh.context.ParamContextHolder;
import com.github.peh.exception.BaseRuntimeException;
import com.github.peh.processor.BaseProcessor;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/07/10 13:49
 * @description: 处理器引擎
 */
public class PehEngine extends LifeCycleManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(PehEngine.class);

    protected volatile LinkedList<BaseProcessor> processors;

    @Override
    public void doInitial(Object... requests) {
        //handle request objects
        Optional.ofNullable(requests).ifPresent(reqs -> {
            if (reqs.length == 1) {
                ParamContextHolder.setRequest(reqs[0]);
            } else {
                //store request object by order
                Set treeSet = Sets.newTreeSet();
                for (Object req : reqs) {
                    treeSet.add(req);
                }
                ParamContextHolder.setRequest(treeSet);
            }
        });
    }

    @Override
    public void doRunning(Object... objects) {
        //processors must keep execute serially by order
        for (BaseProcessor processor : processors) {
            if (!isRunning()) {
                LOGGER.debug("[PehEngine] engine has stopped , it will unable all processors.");
                break;
            }

            LOGGER.debug("[PehEngine] engine is running , current processor is [{}]", processor.getProcessorName());

            processor.process();
        }
    }

    @Override
    public void doStop(Object... objects) {

    }

    @Override
    public Object doAfterReturning(Object... objects) {
        return ParamContextHolder.getResponse();
    }

    @Override
    public void doFinish(Object... objects) {
    }

    @Override
    public void doException(Object... objects) {

    }

    public Object process(Object... requests) {
        Objects.requireNonNull(processors, "processors can not be null");

        try {
            initial();

            running();

            return afterReturning();
        } catch (BaseRuntimeException bre) {
            bre.printStackTrace();
            exception();
        } catch (Exception ex) {
            ex.printStackTrace();
            exception();
        } finally {
            finish();
        }

        return null;
    }

}
