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
import com.sample.base.helper.WindowHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/8
 * @Description
 */
public abstract class BaseFragment extends Fragment {

    protected Logger mLogger;
    protected Context mContext;
    private Unbinder unbinder;

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
        initData(savedInstanceState);
        initPresenter();
        initView(view);
    }

    @Override
    public void onPause() {
        //Hide softKeyboard
        WindowHelper.hideKeyboard(getActivity());
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            WindowHelper.hideKeyboard(getActivity());
        }

        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

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
