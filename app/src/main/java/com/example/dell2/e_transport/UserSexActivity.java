package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/24.
 */

public class UserSexActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private E_Trans_Application app;
    private LinearLayout ll_setting_male;
    private LinearLayout ll_setting_female;
    private ImageView iv_setting_male;
    private ImageView iv_setting_female;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_usersex);
        init();
    }
    private void init(){
        /*实例化*/
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        ll_setting_female=(LinearLayout)findViewById(R.id.ll_setting_female);
        ll_setting_male=(LinearLayout)findViewById(R.id.ll_setting_male);
        iv_setting_female=(ImageView)findViewById(R.id.iv_setting_female);
        iv_setting_male=(ImageView)findViewById(R.id.iv_setting_male);
        app=(E_Trans_Application)getApplication();
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        ll_setting_female.setOnClickListener(this);
        ll_setting_male.setOnClickListener(this);
        /*初始化标题*/
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("性别");
        /*信息初始化*/
        if(app.getUser().getUserGender()==0){
            iv_setting_female.setVisibility(View.GONE);
        }
        else {
            iv_setting_male.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.ll_setting_female:
                changeToOtherGender(1);
                if(setUserGender(1)){
                    app.getUser().setUserGender(1);
                }
                else{
                    app.getUser().setUserGender(0);
                }
                break;
            case R.id.ll_setting_male:
                changeToOtherGender(0);
                if(setUserGender(0)){
                    app.getUser().setUserGender(0);
                }
                else{
                    app.getUser().setUserGender(1);
                }
                break;
            default:
                break;
        }
    }
    public void changeToOtherGender(int gender){
        switch (gender){
            case 0:
                iv_setting_male.setVisibility(View.VISIBLE);
                iv_setting_female.setVisibility(View.GONE);
                break;
            case 1:
                iv_setting_male.setVisibility(View.GONE);
                iv_setting_female.setVisibility(View.VISIBLE);
                break;
        }
    }
    /**
     * 设置数据库中用户的性别
     * @param gender   0---男,1--女
     * @return 设置是否成功
     */
    public boolean setUserGender(int gender){
        if(gender==0){
            Toast.makeText(UserSexActivity.this,"男",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(UserSexActivity.this,"女",Toast.LENGTH_SHORT);
        }
        return true;
    }
}
