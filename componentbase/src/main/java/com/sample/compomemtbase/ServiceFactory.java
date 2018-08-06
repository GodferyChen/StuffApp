package com.sample.compomemtbase;

import com.sample.compomemtbase.empty.EmptyAccountService;
import com.sample.compomemtbase.service.IAccountService;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
public class ServiceFactory {

    private IAccountService mAccountService;

    /**
     * 禁止外部创建 ServiceFactory 对象
     */
    private ServiceFactory(){

    }

    /**
     * 通过静态内部类方式实现 ServiceFactory 的单例
     */
    public static ServiceFactory getInstance(){
        return Inner.serviceFactory;
    }

    private static class Inner{
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }

    /**
     * 返回 Login 组件的 Service 实例
     */
    public IAccountService getAccountService() {
        if(mAccountService == null){
            mAccountService = new EmptyAccountService();
        }
        return mAccountService;
    }

    /**
     * 接收 Login 组件实现的 Service 实例
     * @param accountService
     */
    public void setAccountService(IAccountService accountService) {
        mAccountService = accountService;
    }
}
