package com.example.dell2.e_transport;

import android.os.Bundle;

import collector.BaseActivity;

/**
 * Created by dell2 on 2017/5/23.
 */

public class PasswordLoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_passwordlogin);
    }

    public void actionStart(){

    }
}
