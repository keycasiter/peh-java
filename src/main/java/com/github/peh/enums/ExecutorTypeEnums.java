package com.github.peh.enums;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/02/26 14:07
 * @description: 执行器类型枚举
 */
public enum ExecutorTypeEnums {

    /**
     * 串行化
     */
    SERIAL("serial", "串行化"),

    /**
     * 并行化
     */
    PARALLEL("parallel", "并行化"),

    ;

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    ExecutorTypeEnums(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
