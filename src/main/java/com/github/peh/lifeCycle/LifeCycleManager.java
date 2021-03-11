package com.github.peh.lifeCycle;

import com.github.peh.context.ContextHolder;
import com.github.peh.context.LifeCycleContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/11 9:26
 * @description: 生命周期管理
 */
public abstract class LifeCycleManager implements ILifeCycle {
    private final static Logger LOGGER = LoggerFactory.getLogger(LifeCycleManager.class);

    @Override
    public final void initial(Object... objects) {
        LOGGER.debug("[LIFECYCLE-MANAGER-INITIAL] initial phase ...");
        LifeCycleContextHolder.initial();
        doInitial(objects);
    }

    public abstract void doInitial(Object... objects);

    @Override
    public final void running(Object... objects) {
        LOGGER.debug("[LIFECYCLE-MANAGER-RUNNING] running phase ...");
        LifeCycleContextHolder.running();
        doRunning();
    }

    public abstract void doRunning(Object... objects);

    @Override
    public final void stop(Object... objects) {
        LOGGER.debug("[LIFECYCLE-MANAGER-STOP] stop phase ...");
        LifeCycleContextHolder.stop();
        doStop(objects);
    }

    public abstract void doStop(Object... objects);

    @Override
    public final Object afterReturning(Object... objects) {
        LifeCycleContextHolder.afterReturning();
        return doAfterReturning(objects);
    }

    public abstract Object doAfterReturning(Object... objects);

    @Override
    public final void finish(Object... objects) {
        LOGGER.debug("[LIFECYCLE-MANAGER-FINISH] finish phase ...");
        LifeCycleContextHolder.finish();

        ContextHolder.clear();

        doFinish(objects);
    }

    public abstract void doFinish(Object... objects);

    @Override
    public final void exception(Object... objects) {
        LOGGER.debug("[LIFECYCLE-MANAGER-EXCEPTION] exception phase ...");
        LifeCycleContextHolder.exception();
        doException(objects);
    }

    public abstract void doException(Object... objects);

    @Override
    public final LifeCycleEnums getLifeCycle() {
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
