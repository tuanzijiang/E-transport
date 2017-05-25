package com.example.dell2.e_transport;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;

import collector.BaseActivity;

/**
 * Created by wangyan on 2017/5/23.
 */

public class AccountActivity extends BaseActivity implements View.OnClickListener{
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private LinearLayout setUserName;
    private TextView userName;
    private LinearLayout setUserGender;
    private TextView userGender;
    private LinearLayout setUserTel;
    private TextView userTel;
    private LinearLayout setUserAddress;
    private TextView userAddress;
    private LinearLayout setPwLogin;
    private TextView pwLogin;
    private LinearLayout setPwCover;
    private TextView pwCover;
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
        /*实例化*/
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        setUserName=(LinearLayout)findViewById(R.id.setUserName);
        setUserAddress=(LinearLayout)findViewById(R.id.setUserAddress);
        setUserTel=(LinearLayout)findViewById(R.id.setTel);
        setUserGender=(LinearLayout)findViewById(R.id.setUserGender);
        setPwCover=(LinearLayout)findViewById(R.id.set_PwCover);
        setPwLogin=(LinearLayout)findViewById(R.id.set_PwLogin);
        userName=(TextView)findViewById(R.id.userName);
        userGender=(TextView)findViewById(R.id.userGender);
        userTel=(TextView)findViewById(R.id.tel);
        userAddress=(TextView)findViewById(R.id.userAddress);
        pwLogin=(TextView)findViewById(R.id.pwLogin);
        pwCover=(TextView)findViewById(R.id.pwCover);
        /*响应事件设定*/
        setUserName.setOnClickListener(this);
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("账户信息");
    }
    /*响应事件*/
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.header_back_1:
                finish();
                break;
            case R.id.setUserName:
                intent=new Intent(AccountActivity.this,UserNameActivity.class);
                startActivity(intent);
                break;
            case R.id.setUserGender:
                intent=new Intent(AccountActivity.this,UserSexActivity.class);
                startActivity(intent);
            case R.id.setTel:
//                intent=new Intent(AccountActivity.this)
            default:
                break;
        }
    }
}
