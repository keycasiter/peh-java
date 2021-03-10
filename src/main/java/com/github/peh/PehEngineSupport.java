package com.github.peh;

import com.github.peh.context.ContextHolder;
import com.github.peh.context.LifeCycleContextHolder;
import com.github.peh.lifeCycle.ILifeCycle;
import com.github.peh.lifeCycle.LifeCycleEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by guanjian on 2021/3/10 18:01
 */
public abstract class PehEngineSupport implements ILifeCycle {
    private final static Logger LOGGER = LoggerFactory.getLogger(PehEngine.class);

    @Override
    public void initial(Object... objects) {
        LOGGER.debug("[ENGINE-INITIAL] initial phase ...");
        LifeCycleContextHolder.initial();
        doInitial(objects);
    }

    public abstract void doInitial(Object... objects);

    @Override
    public void running(Object... objects) {
        LOGGER.debug("[ENGINE-RUNNING] running phase ...");
        LifeCycleContextHolder.running();
        doRunning();
    }

    public abstract void doRunning(Object... objects);

    @Override
    public void stop(Object... objects) {
        LOGGER.debug("[ENGINE-STOP] stop phase ...");
        LifeCycleContextHolder.stop();
        doStop();
    }

    public abstract void doStop(Object... objects);

    @Override
    public Object afterReturning(Object... objects) {
        LifeCycleContextHolder.afterReturning();
        return doAfterReturning();
    }

    public abstract Object doAfterReturning(Object... objects);

    @Override
    public void finish(Object... objects) {
        LOGGER.debug("[ENGINE-FINISH] finish phase ...");
        LifeCycleContextHolder.finish();

        ContextHolder.clear();

        doFinish();
    }

    public abstract void doFinish(Object... objects);

    @Override
    public void exception(Object... objects) {
        LOGGER.debug("[ENGINE-EXCEPTION] exception phase ...");
        LifeCycleContextHolder.exception();
        doException();
    }

    public abstract void doException(Object... objects);

    @Override
    public LifeCycleEnums getLifeCycle() {
        return LifeCycleContextHolder.getLifeCycle();
    }

    public boolean isInitial() {
        return LifeCycleEnums.isEquals(getLifeCycle(), LifeCycleEnums.INITIAL);
    }

    public boolean isRunning() {
        return LifeCycleEnums.isEquals(getLifeCycle(), LifeCycleEnums.RUNNING);
    }

    public boolean isStop() {
        return LifeCycleEnums.isEquals(getLifeCycle(), LifeCycleEnums.STOP);
    }

    public boolean isFinish() {
        return LifeCycleEnums.isEquals(getLifeCycle(), LifeCycleEnums.FINISH);
    }

    public boolean isException() {
        return LifeCycleEnums.isEquals(getLifeCycle(), LifeCycleEnums.EXCEPTION);
    }
}
