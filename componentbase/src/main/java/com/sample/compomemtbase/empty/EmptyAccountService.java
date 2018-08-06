package com.sample.compomemtbase.empty;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sample.compomemtbase.service.IAccountService;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public class EmptyAccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager,
                                    Bundle bundle, String tag) {
        return null;
    }
}
