package com.github.peh.processor;

import com.github.peh.executor.IExecutor;
import com.github.peh.executor.SerialExecutor;

/**
 * @author: <a href=mailto:keycasiter@qq.com>guanjian</a>
 * @date: 2020/07/08 10:17
 * @description: 流程器定义
 */
public interface IProcessor {

    /**
     * 配置方法
     */
    void configurate();

    /**
     * 处理方法
     */
    void process();
}
