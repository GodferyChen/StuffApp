package com.sample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.elvishew.xlog.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/8
 * @Description
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContextViewId();

    protected abstract int getLayoutId();

    private Unbinder unbinder;
    protected Toolbar mToolbar;
    protected ActionBar mActionBar;
    protected Logger mLogger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);

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
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
