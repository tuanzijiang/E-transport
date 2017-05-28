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

public class UserPayPwActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private E_Trans_Application app;
    private EditText et_pw;
    private EditText et_pw_again;
    private LinearLayout button_verify;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_paypassword);
        init();
    }
    private void init(){
        /*实例化*/
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        app=(E_Trans_Application)getApplication();
        et_pw=(EditText)findViewById(R.id.et_pw);
        et_pw_again=(EditText)findViewById(R.id.et_pw_again);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*标题*/
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("支付密码");
    }
    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.button_verify:
                if(et_pw==null||et_pw.getText().toString().equals("")){
                    Toast.makeText(UserPayPwActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(et_pw.getText().toString().length()<6){
                    Toast.makeText(UserPayPwActivity.this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(!et_pw_again.getText().toString().equals(et_pw.getText().toString())){
                    Toast.makeText(UserPayPwActivity.this,"两次密码输入不同",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(setUserCoverPw(et_pw.getText().toString())){
                    app.getUser().setUserPwCover(et_pw.getText().toString());
                    intent=new Intent();
                    intent.putExtra("result","true");
                    setResult(RESULT_OK);
                    finish();
                }
            default:
                break;
        }
    }
    /**（待填）
     * 设置支付密码，邮箱注意检查是否和数据库里面的重复
     * @return 修改结果
     */
    public boolean setUserCoverPw(String pw){
        return true;
    }
}
