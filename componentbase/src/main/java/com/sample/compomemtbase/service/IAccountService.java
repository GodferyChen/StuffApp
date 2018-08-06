package com.sample.compomemtbase.service;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public interface IAccountService {

    /**
     * 是否已经登录
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的AccountId
     * @return
     */
    String getAccountId();

    /**
     * 创建 UserFragment
     * @param activity
     * @param containerId
     * @param manager
     * @param bundle
     * @param tag
     * @return
     */
    Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle
            bundle,String tag);

}
