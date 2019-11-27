package com.jh.statelayoutlibrary;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class DefaultLayoutManager extends StateLayoutManager {


    public DefaultLayoutManager(Context mContext) {
        super(mContext);
    }

    @Override
    protected int loadingLayout() {
        return R.layout.layout_defalut_loading;
    }

    @Override
    protected void showLoadingAfter(View parentView) {
        LottieAnimationView lottieAnimationView = parentView.findViewById(R.id.lottieAnimationView);
   /*     lottieAnimationView.setImageAssetsFolder("images");
        lottieAnimationView.setAnimation("data.json");*/
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
    }

    @Override
    protected int emptyLayout() {
        return R.layout.layout_defalut_empty;
    }

    @Override
    protected void showEmptyAfter(View parentView) {

    }

    @Override
    protected View.OnClickListener clickEmptyListener() {
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "empty", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected int errorLayout() {
        return R.layout.layout_defalut_error;
    }

    @Override
    protected void showErrorAfter(View parentView, String errorMsg) {

    }

    @Override
    protected View.OnClickListener clickErrorListener() {
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void showSuccessAfter(View parentView) {

    }
}
