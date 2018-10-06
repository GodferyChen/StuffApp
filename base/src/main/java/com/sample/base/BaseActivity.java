package com.sample.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.elvishew.xlog.Logger;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/8
 * @Description
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();

    private Unbinder unbinder;
    protected Toolbar mToolbar;
    protected ActionBar mActionBar;
    protected Logger mLogger;
    protected Context mContext;
    protected ImmersionBar mImmersionBar;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);

        mImmersionBar = ImmersionBar.with(this);
        //所有子类都将继承这些相同的属性
        mImmersionBar.init();
        //初始化沉浸式
        if (isImmersionBarEnabled()){
            initImmersionBar();
        }
        init(savedInstanceState);
    }

    /**
     * 初始化操作
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    protected void initToolbar(String title) {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            setSupportActionBar(mToolbar);
            mActionBar = getSupportActionBar();
            if (mActionBar != null) {
                mActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar
                        .DISPLAY_SHOW_TITLE);
                mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
                mActionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    private void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    /**
     * jump to other activity
     *
     * @param tarActivity
     */
    public void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(BaseActivity.this, tarActivity);
        startActivity(intent);
    }

    /**
     * jump to other activity-with params
     *
     * @param tarActivity
     * @param bundle
     */
    public void intent2Activity(Class<? extends Activity> tarActivity, Bundle bundle) {
        Intent intent = new Intent(BaseActivity.this, tarActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * jump to other activity - for result
     *
     * @param tarActivity
     */
    protected void intent2ActivityForResult(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(BaseActivity.this, tarActivity);
        startActivityForResult(intent, 1000);
    }

    /**
     * jump to other activity - for result and with params
     *
     * @param tarActivity
     * @param bundle
     */
    protected void intent2ActivityForResult(Class<? extends Activity> tarActivity, Bundle bundle) {
        Intent intent = new Intent(BaseActivity.this, tarActivity);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1000);
    }

}
