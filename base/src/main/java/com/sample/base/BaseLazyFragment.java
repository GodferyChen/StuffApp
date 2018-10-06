package com.sample.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elvishew.xlog.Logger;
import com.gyf.barlibrary.ImmersionBar;
import com.sample.base.helper.WindowHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/21
 * @Description 当使用viewpager加载Fragment，原理懒加载
 */
public abstract class BaseLazyFragment extends Fragment {

    protected Logger mLogger;
    protected Context mContext;
    private Unbinder unbinder;
    protected ImmersionBar mImmersionBar;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            initData();
            if (isImmersionBarEnabled()){
                initImmersionBar();
            }
        }
        initPresenter();
        initView(view);
    }

    @Override
    public void onPause() {
        //Hide softKeyboard
        if (null != getActivity()) {
            WindowHelper.hideKeyboard(getActivity());
        }
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden && null != getActivity()) {
            WindowHelper.hideKeyboard(getActivity());
        }
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.init();
        }

        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * getLayoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * initData
     *
     */
    protected abstract void initData();

    /**
     * initPresenter
     */
    protected abstract void initPresenter();

    /**
     * init UI
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 用户不可见执行
     */
    protected void onInvisible() {

    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }

    /**
     * 是否懒加载
     *
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return true;
    }

    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            initData();
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }


    /**
     * jumnp to other activity
     *
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mContext, tarActivity);
        mContext.startActivity(intent);
    }

    /**
     * jumnp to other activity-with params
     *
     * @param tarActivity
     * @param mBundle
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity, Bundle mBundle) {
        Intent intent = new Intent(mContext, tarActivity);
        intent.putExtras(mBundle);
        mContext.startActivity(intent);
    }
}
