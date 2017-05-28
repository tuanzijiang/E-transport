package com.example.dell2.e_transport;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;

import application.E_Trans_Application;
import collector.BaseActivity;
import entity.User;

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
    private LinearLayout setIsAvoidPw;
    private E_Trans_Application app;
    private User user;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_account);
        init();
    }
    @Override
    public void onResume(){
        super.onResume();
        initContent();
    }
    public void init(){
        /*实例化*/
        app=(E_Trans_Application)getApplication();
        user=app.getUser();
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
        setIsAvoidPw=(LinearLayout)findViewById(R.id.setIsAvoidPw);
        /*响应事件设定*/
        setIsAvoidPw.setOnClickListener(this);
        header_front_1.setOnClickListener(this);
        setUserName.setOnClickListener(this);
        setUserGender.setOnClickListener(this);
        setUserTel.setOnClickListener(this);
        setUserAddress.setOnClickListener(this);
        setPwCover.setOnClickListener(this);
        setPwLogin.setOnClickListener(this);
        setPwCover.setOnClickListener(this);
        /*标题初始化*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("账户信息");
        /*内容初始化*/
        initContent();
    }
    /*内容初始化函数*/
    public void initContent(){
        if(user.getUserName()!=null&&!user.getUserName().equals(""))
            userName.setText(user.getUserName());
        if(user.getUserGenderString()!=null&&!user.getUserGenderString().equals(""))
            userGender.setText(user.getUserGenderString());
        if(user.getUserTel()!=null&&!user.getUserTel().equals(""))
            userTel.setText(user.getUserTel());
        if(user.getUserAddress()!=null&&!user.getUserAddress().equals(""))
            userAddress.setText(user.getUserAddress());
        if(user.getUserPwCover()==null||user.getUserPwCover().equals("")){
            pwCover.setText("未设置");
        }
        else{
            pwCover.setText("已设置");
        }
        if(user.getUserPwLogin()==null||user.getUserPwLogin().equals("")){
            pwLogin.setText("未设置");
        }
        else{
            pwLogin.setText("已设置");
        }
    }
    /*响应事件函数*/
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.setUserName:
                intent=new Intent(AccountActivity.this,UserNameActivity.class);
                startActivity(intent);
                break;
            case R.id.setUserGender:
                intent=new Intent(AccountActivity.this,UserSexActivity.class);
                startActivity(intent);
                break;
            case R.id.setTel:
                if(user.getUserTel()!=null&&!user.getUserTel().equals("")){
                    intent=new Intent(AccountActivity.this,UserPhoneActivity.class);
                    startActivity(intent);
                }
                else{
                    intent=new Intent(AccountActivity.this,UserNewPhoneActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.setUserAddress:
                intent=new Intent(AccountActivity.this,UserAreaAvtivity.class);
                startActivity(intent);
                break;
            case R.id.set_PwLogin:
                if(user.getUserPwLogin()!=null&&!user.getUserPwLogin().equals(""))
                    intent=new Intent(AccountActivity.this,UserLoginPwChangeActivity.class);
                else
                    intent=new Intent(AccountActivity.this,UserLoginPwActivity.class);
                startActivity(intent);
                break;
            case R.id.set_PwCover:
                if(user.getUserPwCover()!=null&&!user.getUserPwCover().equals(""))
                    intent=new Intent(AccountActivity.this,UserPayPwChangeActivity.class);
                else
                    intent=new Intent(AccountActivity.this,UserPayPwActivity.class);
                startActivity(intent);
                break;
            case R.id.setIsAvoidPw:
                intent=new Intent(AccountActivity.this,UserAvoidPwActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
