package com.sample.base.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/9
 * @Description
 */
public class WindowHelper {

    /**
     * set status bar color
     * @param activity
     * @param color
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColor(Activity activity, @ColorInt int color){
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    /**
     * set navigation bar color
     * @param activity
     * @param color
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setNavigationBarColor(Activity activity, @ColorInt int color){
        Window window = activity.getWindow();
        window.setNavigationBarColor(color);
    }

    /**
     * hide keyboard
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        View mv = activity.getWindow().peekDecorView();
        if (mv != null){
            InputMethodManager inputmanger = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(mv.getWindowToken(), 0);
        }
    }

    /**
     * open keyboard
     * @param activity
     */
    public static void openKeyboard(Activity activity){
        View view = activity.getWindow().peekDecorView();
        if(view == null){
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(view.getRootView(),InputMethodManager.RESULT_SHOWN);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}
