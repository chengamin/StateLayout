package com.jh.statelayout;

import android.content.Context;
import android.view.View;

import com.jh.statelayoutlibrary.StateLayoutManager;

public class CustomLayoutManager extends StateLayoutManager {

    public CustomLayoutManager(Context mContext) {
        super(mContext);
    }

    @Override
    protected int loadingLayout() {
        return R.layout.layout_loading;
    }

    @Override
    protected void showLoadingAfter(View parentView) {

    }

    @Override
    protected int emptyLayout() {
        return R.layout.layout_empty;
    }

    @Override
    protected void showEmptyAfter(View parentView) {

    }

    @Override
    protected View.OnClickListener clickEmptyListener() {
        return null;
    }

    @Override
    protected int errorLayout() {
        return R.layout.layout_error;
    }

    @Override
    protected void showErrorAfter(View parentView, String errorMsg) {

    }

    @Override
    protected View.OnClickListener clickErrorListener() {
        return null;
    }

    @Override
    protected void showSuccessAfter(View parentView) {

    }
}
