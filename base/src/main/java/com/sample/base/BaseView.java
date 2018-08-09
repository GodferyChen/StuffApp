package com.sample.base;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/8
 * @Description
 */
public interface BaseView<T> {

    /**
     * 设置当前业务的操作对象
     *
     * @param presenter
     */
    void setPresenter(T presenter);

}
