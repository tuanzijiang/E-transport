package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;
import fragment.UserFragment;
import handler.CountDownHandler;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by dell2 on 2017/5/28.
 */

public class UserNewPhoneActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private LinearLayout button_verify;
    private EditText et_verify;
    private EditText message_login_et;
    private TextView verify_button;
    private Drawable background_theme;
    private Drawable background_gray;
    private ImageView header_back_1;
    private TextView title_name;
    private E_Trans_Application app;
    private CountDownHandler cdh=new CountDownHandler(new WeakReference<BaseActivity>(this),"UserNewPhoneActivity");
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_userphonenew);
        init();
    }
    private void init(){
        /*实例化*/
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        background_gray= ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_no_click,null);
        background_theme=ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_theme,null);
        app=(E_Trans_Application)getApplication();
        verify_button=(TextView)findViewById(R.id.verify_button);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        message_login_et=(EditText)findViewById(R.id.message_login_et);
        et_verify=(EditText)findViewById(R.id.et_verify);
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        verify_button.setOnClickListener(this);
        /*标题栏*/
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("新的手机");
    }
    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.header_front_1:
                intent=new Intent();
                intent.putExtra("result","false");
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.verify_button:
                if(message_login_et.length()!=11){
                    Toast.makeText(UserNewPhoneActivity.this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
                    break;
                }
                cdh.sendEmptyMessageDelayed(CountDownHandler.START,1000);
                if(sendVerify(message_login_et.getText().toString())){
                    Toast.makeText(UserNewPhoneActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UserNewPhoneActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_verify:
                if(verify(et_verify.getText().toString())){
                    if(setUserTel(message_login_et.getText().toString())){
                        app.getUser().setUserTel(message_login_et.getText().toString());
                        Toast.makeText(UserNewPhoneActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                        intent=new Intent();
                        intent.putExtra("result","true");
                        setResult(RESULT_OK);
                        finish();
                    }
                    else{
                        Toast.makeText(UserNewPhoneActivity.this,"设置失败",Toast.LENGTH_SHORT).show();
                    }
                }
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

    /**（待填  ）
     * 在数据库和本地文件里面设置电话信息
     * @param tel 用户新的电话
     * @return 设置是否成功
     */
    public boolean setUserTel(String tel){
        return true;
    }
    /**（待填）
     * 验证用户验证码是否正确
     * @param et_verify 用户输入的验证
     * @return 验证结果
     */
    public boolean verify(String et_verify){
        if(et_verify.equals(""))
            return true;
        else{
            Toast.makeText(UserNewPhoneActivity.this,"验证码错误",Toast.LENGTH_SHORT);
            return false;
        }
    }

    /**（待填）
     * 发送验证码
     * @param tel 需要发送验证码的手机号
     * @return 验证码是否发送成功
     */
    public boolean sendVerify(String tel){
        return true;
    }
}
