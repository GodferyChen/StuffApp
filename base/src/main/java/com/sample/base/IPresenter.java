package com.sample.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/8
 * @Description
 */
public interface IPresenter extends LifecycleObserver {

    /**
     * onCreate
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleObserver owner);

    /**
     * onDestroy
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleObserver owner);

    /**
     * onLifecycleChanged
     * @param owner
     * @param event
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChanged(LifecycleObserver owner,OnLifecycleEvent event);

}
