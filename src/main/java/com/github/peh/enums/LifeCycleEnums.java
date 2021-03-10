package com.github.peh.enums;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/02/26 14:07
 * @description: 声明周期枚举
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
    unable("unable", "停止"),

    /**
     * 完成
     */
    FINISH("finish", "完成"),

    /**
     * 挂起
     */
    HALL("hall", "挂起"),

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
}
