package com.sample.stuffapp.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.sample.compomemtbase.ServiceFactory;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
@Interceptor(priority = 8,name = "登录状态拦截器")
public class LoginInterceptor implements IInterceptor{

    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        // onContinue 和 onInterrupt 至少需要调用其中一种，否则不会继续路由
        if("/share/share".equals(postcard.getPath())){
            if(ServiceFactory.getInstance().getAccountService().isLogin()){
                // 处理完成，交还控制权
                callback.onContinue(postcard);
            }else {
                // 中断路由流程
                callback.onInterrupt(new RuntimeException("请登录"));
            }
        }else {
            // 处理完成，交还控制权
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
