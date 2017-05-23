package com.example.dell2.e_transport;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

import collector.BaseActivity;

/**
 * Created by wangyan on 2017/5/23.
 */

public class AccountActivity extends BaseActivity{
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_account);
        init();
    }
    public void init(){
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("账户信息");

    }
}
