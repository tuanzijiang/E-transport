package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;
import handler.CountDownHandler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by dell2 on 2017/5/25.
 */

public class UserPhoneActivity extends BaseActivity implements View.OnClickListener {
    private Drawable background_theme;
    private Drawable background_gray;
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private TextView verify_button;
    private TextView userOldTel;
    private E_Trans_Application application;
    private CountDownHandler cdh=new CountDownHandler(new WeakReference<BaseActivity>(this),"UserPhoneActivity");
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_userphone);
        init();
    }
    private void init(){
        /*实例化*/
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        verify_button=(TextView)findViewById(R.id.verify_button);
        application=(E_Trans_Application)getApplication();
        userOldTel=(TextView)findViewById(R.id.userOldTel);
        background_gray=ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_no_click,null);
        background_theme=ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_theme,null);
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        verify_button.setOnClickListener(this);
        /*标题*/
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("手机");
        /*信息初始化*/
        initInfo();
    }
    public void initInfo(){
        String tel=application.getUser().getUserTel();
        userOldTel.setText(tel.substring(0,3)+"****"+tel.substring(7));
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.verify_button:
                cdh.sendEmptyMessageDelayed(CountDownHandler.START,1000);
            default:
                break;
        }
    }
    public CountDownHandler getCdh(){
        return this.cdh;
    }
    public TextView getVerify_button(){
        return verify_button;
    }
    /*0--灰色，1--蓝色*/
    public void setVerify_button_bg(int bg){
        switch (bg){
            case 0:
                verify_button.setBackground(background_gray);
                break;
            default:
                verify_button.setBackground(background_theme);
                break;
        }
    }
}
