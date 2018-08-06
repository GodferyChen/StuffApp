package com.sample.login;

import android.app.Application;

import com.sample.base.BaseApp;
import com.sample.compomemtbase.ServiceFactory;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public class LoginApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new AccountService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
