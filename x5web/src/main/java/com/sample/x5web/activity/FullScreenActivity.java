package com.sample.x5web.activity;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.sample.base.BaseActivity;
import com.sample.x5web.R;
import com.sample.x5web.helper.WebViewJavaScriptFunction;
import com.sample.x5web.helper.X5WebView;

import butterknife.BindView;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/20
 * @Description
 */
public class FullScreenActivity extends BaseActivity {

    @BindView(R.id.web_filechooser)
    X5WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_filechooser;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        webView.loadUrl("file:///android_asset/webpage/fullscreenVideo.html");

        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        webView.addJavascriptInterface(new WebViewJavaScriptFunction() {

            @Override
            public void onJsFunctionCalled(String tag) {
                // TODO Auto-generated method stub

            }

            @JavascriptInterface
            public void onX5ButtonClicked() {
                enableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onCustomButtonClicked() {
                disableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onLiteWndButtonClicked() {
                enableLiteWndFunc();
            }

            @JavascriptInterface
            public void onPageVideoClicked() {
                enablePageVideoFunc();
            }
        }, "Android");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        try {
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == Configuration
                    .ORIENTATION_LANDSCAPE) {

            } else if (getResources().getConfiguration().orientation == Configuration
                    .ORIENTATION_PORTRAIT) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 向webview发出信息
     */
    private void enableX5FullscreenFunc() {

        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            // true表示标准全屏，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", false);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", false);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 2);

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void disableX5FullscreenFunc() {
        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "恢复webkit初始状态", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", true);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", false);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 2);

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enableLiteWndFunc() {
        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启小窗模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", false);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", true);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 2);

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enablePageVideoFunc() {
        if (webView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "页面内全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", false);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", false);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 1);

            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }
}
