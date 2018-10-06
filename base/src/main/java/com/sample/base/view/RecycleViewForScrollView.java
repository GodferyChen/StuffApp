package com.sample.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by chen on 2016/6/1.
 */
public class RecycleViewForScrollView extends RecyclerView {

    public RecycleViewForScrollView(Context context) {
        super(context);
    }

    public RecycleViewForScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecycleViewForScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
