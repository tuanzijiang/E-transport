package com.example.dell2.e_transport;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import application.E_Trans_Application;
import collector.BaseActivity;

/**
 * Created by wangyan on 2017/5/23.
 */

public class UserNameActivity extends BaseActivity implements View.OnClickListener{
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private LinearLayout button_verify;
    private EditText edit_username;
    private E_Trans_Application app;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_username);
        init();
    }
    public void init(){
        /*实例化*/
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        edit_username=(EditText)findViewById(R.id.edit_username);
        app=(E_Trans_Application)getApplication();
        /*信息初始化*/
        edit_username.setText(app.getUser().getUserName());
        /*事件监听*/
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("用户名");
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.button_verify:
                String userNameString=edit_username.getText().toString();
                if(userNameString==null||userNameString.length()==0) {
                    Toast.makeText(UserNameActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(setUserNameString(userNameString)){
                        app.getUser().setUserName(userNameString);
                    }
                    else{
                        Toast.makeText(UserNameActivity.this,"设置失败",Toast.LENGTH_SHORT);
                    }
                }
            default:
                break;
        }
    }
    /**
     * 设置用户名的函数
     * 参数：String userNameString(需要设置的用户名)
     * 返回值：是否设置成功
     */
    public boolean setUserNameString(String userNameString){
        Toast.makeText(UserNameActivity.this,userNameString,Toast.LENGTH_SHORT).show();
        return true;
    }
}
