package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import application.E_Trans_Application;
import collector.BaseActivity;

/**
 * Created by wangyan on 2017/5/23.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private LinearLayout go_account;
    private LinearLayout button_exit;
    private LinearLayout setGeneral;
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
        /*获取实例*/
        setGeneral=(LinearLayout)findViewById(R.id.setGeneral);
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        go_account=(LinearLayout)findViewById(R.id.go_account);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        button_exit=(LinearLayout)findViewById(R.id.button_exit);
        /*设置相应事件*/
        header_front_1.setOnClickListener(this);
        go_account.setOnClickListener(this);
        button_exit.setOnClickListener(this);
        setGeneral.setOnClickListener(this);
        /*初始化title*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("设置");

    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,SettingActivity.class);
        BaseActivity baseActivity=(BaseActivity)context;
        baseActivity.startActivityForResult(intent,2);
    }
    public void onClick(View view){
        E_Trans_Application app;
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.go_account:
                app=(E_Trans_Application)getApplication();
                if(app.getLoginState()==1) {
                    Intent intent = new Intent(SettingActivity.this, AccountActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SettingActivity.this,"未登录,请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_exit:
                app=(E_Trans_Application)getApplication();
                app.exit();
                finish();
                break;
            case R.id.setGeneral:
                Intent intent=new Intent(SettingActivity.this,SettingActivityGeneral.class);
                startActivity(intent);
            default:
                break;
        }
    }
}
