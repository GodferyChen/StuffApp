package com.sample.x5web.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.sample.base.BaseActivity;
import com.sample.x5web.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * @author chen
 * @version 1.0.0
 * @date 2018/8/17
 * @Description
 */
public class MainActivity extends BaseActivity {

    /**
     * add constant here
     */
    private static final int TBS_WEB = 0;
    private static final int FULL_SCREEN_VIDEO = 1;
    private static final int FILE_CHOOSER = 2;
    private static boolean mainInit = false;
    private ArrayList<HashMap<String, Object>> items;
    private static String[] titles = null;
    @BindView(R.id.item_grid)
    GridView mGridView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mContext = this;
        if (!mainInit) {
            newInit();
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar).init();
        mToolbar = findViewById(com.sample.base.R.id.toolbar);
        if(mToolbar != null){
            mToolbar.setTitle(getResources().getString(R.string.app_name));
        }
    }

    @Override
    protected void onResume() {
        newInit();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            tbsSuiteExit();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void newInit() {
        items = new ArrayList<>();

        if (mGridView == null) {
            throw new IllegalArgumentException("the gridView is null");
        }

        titles = getResources().getStringArray(R.array.index_titles);
        int[] iconResource = {R.mipmap.tbsweb, R.mipmap.fullscreen,
                R.mipmap.filechooser};

        HashMap<String, Object> item = null;
        // HashMap<String, ImageView> block = null;
        for (int i = 0; i < titles.length; i++) {
            item = new HashMap<>();
            item.put("title", titles[i]);
            item.put("icon", iconResource[i]);

            items.add(item);
        }

        SimpleAdapter gridAdapter = new SimpleAdapter(this, items,
                R.layout.item_block, new String[]{"title", "icon"},
                new int[]{R.id.Item_text, R.id.Item_bt});
        if (null != mGridView) {
            mGridView.setAdapter(gridAdapter);
            gridAdapter.notifyDataSetChanged();
            mGridView.setOnItemClickListener((gridView, view, position, id) -> {
                switch (position) {
                    case FILE_CHOOSER:
                        intent2Activity(FileChooserActivity.class);
                        break;
                    case FULL_SCREEN_VIDEO:
                        intent2Activity(FullScreenActivity.class);
                        break;
                    case TBS_WEB:
                        intent2Activity(BrowserActivity.class);
                        break;
                    default:
                        break;

                }

            });

        }
        mainInit = true;
    }

    private void tbsSuiteExit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("X5功能演示");
        dialog.setPositiveButton("OK", (dialog1, which) -> Process.killProcess(Process.myPid()));
        dialog.setMessage("quit now?");
        dialog.create().show();
    }
}
