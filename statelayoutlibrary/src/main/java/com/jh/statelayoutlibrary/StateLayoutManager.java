package com.jh.statelayoutlibrary;

import android.content.Context;
import android.view.View;

public abstract class StateLayoutManager {

    protected Context mContext = null;

    public StateLayoutManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 返回加载中的布局
     *
     * @return loading layout res id
     */
    protected abstract int loadingLayout();

    /**
     * 在展示loading布局以后进行调用,我们可在加载成功的布局中设置一些动画,或改变文字等等
     *
     * @param parentView loading view
     */
    protected abstract void showLoadingAfter(View parentView);

    /**
     * 返回空数据的布局
     *
     * @return empty layout res id
     */
    protected abstract int emptyLayout();

    /**
     * 在展示空数据布局以后进行调用,我们可在其中中设置一些动画,或改变文字等等
     *
     * @param parentView empty view
     */
    protected abstract void showEmptyAfter(View parentView);

    /**
     *  点击无数据的回调事件
     *
     * @return
     */
    protected abstract View.OnClickListener clickEmptyListener();

    /**
     * 返回错误的布局
     *
     * @return empty layout res id
     */
    protected abstract int errorLayout();

    /**
     * 在展示加载错误布局以后进行调用,我们可在其中中设置一些动画,或改变文字等等
     *
     * @param parentView error view
     */
    protected abstract void showErrorAfter(View parentView,String errorMsg);


    /**
     *  点击错误的回调事件
     *
     * @return
     */
    protected abstract View.OnClickListener clickErrorListener();

    /**
     * 在展示加载成功布局以后进行调用,我们可在其中中设置一些动画,或改变文字等等
     *
     * @param parentView success view
     */
    protected abstract void showSuccessAfter(View parentView);




}
