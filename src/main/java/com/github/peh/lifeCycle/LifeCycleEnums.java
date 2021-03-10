package com.github.peh.lifeCycle;

import com.github.peh.exception.ConfigurationException;

import java.util.Objects;

/**
 * created by guanjian on 2021/3/10 16:52
 */
public enum LifeCycleEnums {
    /**
     * 初始
     */
    INITIAL("initial", "初始"),

    /**
     * 运行
     */
    RUNNING("running", "运行"),

    /**
     * 停止
     */
    STOP("stop", "停止"),

    /**
     * 返回
     */
    AFTER_RETURNING("afterReturning", "返回"),

    /**
     * 完成
     */
    FINISH("finish", "完成"),

    /**
     * 异常
     */
    EXCEPTION("exception", "异常"),
    ;

    /**
     * 周期
     */
    private String stage;

    /**
     * 描述
     */
    private String desc;


    LifeCycleEnums(String stage, String desc) {
        this.stage = stage;
        this.desc = desc;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static LifeCycleEnums getEnums(String stage) {
        Objects.requireNonNull(stage,"stage can not be null");

        for (LifeCycleEnums each : LifeCycleEnums.values()) {
            if (stage.equals(each.getStage())) return each;
        }
        throw new ConfigurationException(String.format("%s can not find which one matches.", stage));
    }

    public static boolean checkValid(String stage) {
        boolean res = false;

        if (null == stage || 0 == stage.length()) return res;

        for (LifeCycleEnums each : LifeCycleEnums.values()) {
            if (stage.equals(each.getStage())) return true;
        }
        return res;
    }

    public static boolean isEquals(LifeCycleEnums one, LifeCycleEnums other) {
        if (null == one || null == other) return false;
        return one == other;
    }
}
