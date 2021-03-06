package com.sample.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sample.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/6
 * @Description
 */
@Route(path = "/account/login")
public class LoginActivity extends BaseActivity {

    @BindView(R2.id.tv_login_state)
    TextView tvState;
    @BindView(R2.id.text)
    TextView tvText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        updateLoginState();
    }

    public void login(View view) {
        AccountUtils.userInfo = new UserInfo("10086", "Admin");
        updateLoginState();
    }

    public void exit(View view) {
        AccountUtils.userInfo = null;
        updateLoginState();
    }

    public void loginShare(View view) {
        ARouter.getInstance().build("/share/share").withString("share_content", "分享数据到微博")
                .navigation();
    }

    public void showText(View view) {
        tvText.setVisibility(View.VISIBLE);
        tvText.setText("hello espresso!");
    }

    private void updateLoginState() {
        tvState.setText(String.format("这里是登录界面：%s", AccountUtils.userInfo == null ? "未登录" :
                AccountUtils.userInfo.getUserName()));
    }

}
