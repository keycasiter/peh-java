package com.github.peh.lifeCycle;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/10 16:44
 * @description: 声明周期
 */
public interface ILifeCycle {

    void initial(Object... objects);

    void running(Object... objects);

    void stop(Object... objects);

    Object afterReturning(Object... objects);

    void finish(Object... objects);

    void exception(Object... objects);

    LifeCycleEnums getLifeCycle();
}
