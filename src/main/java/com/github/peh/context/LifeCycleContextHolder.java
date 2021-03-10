package com.github.peh.context;

import com.github.peh.lifeCycle.LifeCycleEnums;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/03/10 17:01
 * @description: 生命周期环境变量
 */
public class LifeCycleContextHolder extends ContextHolder {

    private final static String LIFE_CYCLE = "LIFE_CYCLE";

    public static void initial() {
        put(LIFE_CYCLE, LifeCycleEnums.INITIAL.getStage());
    }

    public static void running() {
        put(LIFE_CYCLE, LifeCycleEnums.RUNNING.getStage());
    }

    public static void stop() {
        put(LIFE_CYCLE, LifeCycleEnums.STOP.getStage());
    }

    public static void finish() {
        put(LIFE_CYCLE, LifeCycleEnums.FINISH.getStage());
    }

    public static void exception() {
        put(LIFE_CYCLE, LifeCycleEnums.EXCEPTION.getStage());
    }

    public static LifeCycleEnums getLifeCycle() {
        return LifeCycleEnums.getEnums(get(LIFE_CYCLE, String.class));
    }
}
