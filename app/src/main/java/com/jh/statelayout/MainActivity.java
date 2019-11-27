package com.jh.statelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jh.statelayoutlibrary.State;
import com.jh.statelayoutlibrary.StateLayout;

public class MainActivity extends AppCompatActivity {

    private StateLayout stateLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stateLayout = findViewById(R.id.stateLayout);
        stateLayout.setStateLayoutManager(/*new CustomLayoutManager(this)*/);
    }

    public void loading(View view){
        stateLayout.setState(State.LOADING,null);
    }

    public void empty(View view){
        stateLayout.setState(State.EMPTY,null);
    }

    public void error(View view){
        stateLayout.setState(State.ERROR,"网络错误");
    }

    public void success(View view){
        stateLayout.setState(State.SUCCESS,null);
    }
}
