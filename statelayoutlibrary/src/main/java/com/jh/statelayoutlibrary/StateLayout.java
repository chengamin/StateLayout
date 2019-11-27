package com.jh.statelayoutlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StateLayout extends FrameLayout {

    // 上下文
    private Context mContext = null;
    // 布局加载器
    private LayoutInflater mInflater = null;
    // 当前页面状态
    private State mCurrentState = State.LOADING;
    // 是否是初次初始化
    private boolean mIsFirstInit = false;
    // 布局管理器
    private StateLayoutManager mStateLayoutManager = null;
    // 依次为: 加载布局的资源id,空布局的资源id,错误布局的资源id
    private int mLoadingLayoutResId, mEmptyLayoutResId, mErrorLayoutResId = 0;
    // 依次为: 加载布局,空布局,错误布局,加载成功布局
    private View mLoadingLayout, mEmptyLayout, mErrorLayout, mSuccessLayout = null;

    /**
     * 构造方法
     * 如果开发者使用的是动态构建的方式,也就是new的方式,该个构造方法会被调用
     *
     * @param context 上下文
     */
    public StateLayout(@NonNull Context context) {
        super(context);
        initStateLayout(context);
    }

    /**
     * 构造方法
     * 如果开发者使用的xml构建的方法,该个构造方法会被调用
     *
     * @param context 上下文
     * @param attrs   自定义属性的结果集
     */
    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initStateLayout(context);
    }

    /**
     * 构造方法
     * 该方法一般不会自动调用,一般是在上面的构造方法中主动调用,比如View有style属性时
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStateLayout(context);
    }

    /**
     * 在StateLayout刚被创建时,进行调用
     *
     * @param context
     */
    private void initStateLayout(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    /**
     * 会在xml文件绘制完成后调用
     * <p>
     * 我们会在此拿到StateLayout包围的子View,也就是Successview
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!mIsFirstInit) {
            if (mSuccessLayout == null) {
                mSuccessLayout = getChildAt(0);
            }
            mIsFirstInit = true;
        }
    }

    /**
     * 绑定布局管理器  绑定的是默认的
     */
    public void setStateLayoutManager() {
        this.mStateLayoutManager = new DefaultLayoutManager(mContext);
        setState(State.LOADING,null);
    }

    /**
     * 绑定布局管理器
     *
     * @param mStateLayoutManager StateLayoutManager的子类
     */
    public void setStateLayoutManager(StateLayoutManager mStateLayoutManager) {
        this.mStateLayoutManager = mStateLayoutManager;
    }

    /**
     * 改变当前
     *
     * @param state
     * @param errorMsg
     */
    public void setState(State state, String errorMsg) {
        this.mCurrentState = state;
        hideAllLayout();
        switch (this.mCurrentState) {
            case EMPTY:
                showEmptyLayout();
                break;
            case ERROR:
                showErrorLayout(errorMsg);
                break;
            case SUCCESS:
                showSuccessLayout();
                break;
            case LOADING:
                showLoadingLayout();
                break;
        }
    }

    /**
     * 因为我们需要展示某一个特定状态的布局,所以需要先隐藏所有的子view
     */
    private void hideAllLayout() {
        if (mSuccessLayout != null) {
            mSuccessLayout.setVisibility(View.GONE);
        }
        if (mLoadingLayout != null) {
            mLoadingLayout.setVisibility(View.GONE);
        }
        if (mEmptyLayout != null) {
            mEmptyLayout.setVisibility(View.GONE);
        }
        if (mErrorLayout != null) {
            mErrorLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 展示成功布局
     */
    private void showSuccessLayout() {
        if (mSuccessLayout != null) {
            mSuccessLayout.setVisibility(View.VISIBLE);
            mStateLayoutManager.showSuccessAfter(mSuccessLayout);
        }
    }

    /**
     * 展示loading布局
     */
    private void showLoadingLayout() {
        if (mLoadingLayout == null) {
            mLoadingLayoutResId = mStateLayoutManager.loadingLayout();
            if (mLoadingLayoutResId != 0) {
                mLoadingLayout = mInflater.inflate(mLoadingLayoutResId, this, false);
                this.addView(mLoadingLayout);
                mStateLayoutManager.showLoadingAfter(mLoadingLayout);
            }
        } else {
            mLoadingLayout.setVisibility(View.VISIBLE);
            mStateLayoutManager.showLoadingAfter(mLoadingLayout);
        }
    }


    /**
     * 展示empty布局
     */
    private void showEmptyLayout() {
        if (mEmptyLayout == null) {
            mEmptyLayoutResId = mStateLayoutManager.emptyLayout();
            if (mEmptyLayoutResId != 0) {
                mEmptyLayout = mInflater.inflate(mEmptyLayoutResId, this, false);
                this.addView(mEmptyLayout);
                mStateLayoutManager.showEmptyAfter(mEmptyLayout);
            }
        } else {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mStateLayoutManager.showEmptyAfter(mEmptyLayout);
        }
        if (mStateLayoutManager.clickEmptyListener()!=null){
            mEmptyLayout.setOnClickListener(mStateLayoutManager.clickEmptyListener());
        }
    }

    /**
     * 展示error布局
     */
    private void showErrorLayout(String errorMsg) {
        if (mErrorLayout == null) {
            mErrorLayoutResId = mStateLayoutManager.errorLayout();
            if (mEmptyLayoutResId != 0) {
                mErrorLayout = mInflater.inflate(mErrorLayoutResId, this, false);
                this.addView(mErrorLayout);
                mStateLayoutManager.showErrorAfter(mErrorLayout, errorMsg);
            }
        } else {
            mErrorLayout.setVisibility(View.VISIBLE);
            mStateLayoutManager.showErrorAfter(mErrorLayout, errorMsg);
        }
        if (mStateLayoutManager.clickErrorListener()!=null){
            mErrorLayout.setOnClickListener(mStateLayoutManager.clickErrorListener());
        }
    }
}
