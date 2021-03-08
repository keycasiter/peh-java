package com.github.peh.handler;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2021/02/26 15:55
 * @description: 处理器抽象类
 */
public abstract class AbstractHandler implements IHandler {

    /**
     * 执行体名称
     */
    private String handlerName = "default handler";

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
}
