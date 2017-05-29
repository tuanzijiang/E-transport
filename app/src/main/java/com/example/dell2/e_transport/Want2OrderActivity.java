package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import collector.BaseActivity;

/**
 * Created by dell2 on 2017/5/23.
 */

public class Want2OrderActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_want2order);
    }
    public void init()
    {

    }
    public void onClick(View view){

    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,Want2OrderActivity.class);
        MainActivity mainActivity=(MainActivity)context;
        mainActivity.startActivityForResult(intent,2);
    }
}
