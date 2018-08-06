package com.sample.base;

import android.app.Application;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public abstract class BaseApp extends Application{

    /**
     * Application 初始化
     * @param application
     */
    public abstract void initModuleApp(Application application);

    /**
     * 所有 Application 初始化后的自定义操作
     * @param application
     */
    public abstract void initModuleData(Application application);

}
