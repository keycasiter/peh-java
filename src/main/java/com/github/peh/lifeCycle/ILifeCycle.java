package com.github.peh.lifeCycle;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/10 16:44
 * @description: 声明周期
 */
public interface ILifeCycle {

    void initial();

    void running();

    void stop();

    void finish();

    void exception();

    LifeCycleEnums getLifeCycle();
}
