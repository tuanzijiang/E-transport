package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/25.
 */

public class UserPayPwChangeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private LinearLayout button_verify;
    private EditText et_pw;
    E_Trans_Application app;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_paypasswordchange);
        init();
    }
    private void init(){
        /*实例化*/
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        app=(E_Trans_Application)getApplication();
        et_pw=(EditText)findViewById(R.id.et_pw);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*标题栏*/
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("支付密码");
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.button_verify:
                if(et_pw.getText().toString().equals(app.getUser().getUserPwCover())){
                    Intent intent=new Intent(UserPayPwChangeActivity.this,UserPayPwActivity.class);
                    startActivityForResult(intent,0);
                }
                else{
                    Toast.makeText(UserPayPwChangeActivity.this,"密码有误",Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        switch (requestCode){
            case 0:/*登录返还后的处理*/
                if(resultCode==RESULT_OK){
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
