package com.sample.base;

import android.app.Application;
import android.content.Context;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public abstract class BaseApp extends Application{

    private static BaseApp mInstance;

    public static Context getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

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
