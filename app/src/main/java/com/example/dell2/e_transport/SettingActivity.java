package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import collector.BaseActivity;

/**
 * Created by wangyan on 2017/5/23.
 */

public class SettingActivity extends BaseActivity{
    private ImageView header_front_1;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_setting);
        init();
    }

    public void init(){
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_front_1.setImageResource(R.drawable.last_white);
    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,SettingActivity.class);
        BaseActivity baseActivity=(BaseActivity)context;
        baseActivity.startActivityForResult(intent,2);
    }
}
