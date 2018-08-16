package com.sample.x5web;

import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;

import com.sample.base.BaseApp;
import com.tencent.smtt.sdk.WebView;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/16
 * @Description
 */
public class X5WebApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {

    }

    @Override
    public void initModuleData(Application application) {

    }
}