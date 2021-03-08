package com.github.peh.context;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/07/08 9:35
 * @description: 执行环境定义
 * @copyright
 */
public interface IContext {

    /**
     * 环境构建
     */
    void configurate();

    /**
     * 环境运行
     */
    void run();

    /**
     * 环境销毁
     */
    void destroy();
}
