package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;

import application.E_Trans_Application;
import collector.BaseActivity;
import entity.User;
import handler.CountDownHandler;

/**
 * Created by wangyan on 2017/5/22.
 */

public class MessageLoginActivity extends BaseActivity implements View.OnClickListener{
    private Drawable background_theme;
    private Drawable background_gray;
    private EditText et_pw;
    private LinearLayout button_verify;
    public static final String BACK_STATE_NOLOGIN="NOLOGIN";
    public static final String BACK_STATE_LOGIN="LOGIN";
    private CountDownHandler cdh=new CountDownHandler(new WeakReference<BaseActivity>(this),"MessageLoginActivity");
    private EditText message_login_et;
    private ImageView header_front_1;
    private TextView header_back_1;
    private TextView verify_button;
    private E_Trans_Application app;
    private int verify_state;/*0--未开始计时 1--已开始计时*/
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_messagelogin);
        init();
    }
    /**
     * 启动本activity
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MessageLoginActivity.class);
        BaseActivity baseActivity=(BaseActivity)context;
        baseActivity.startActivityForResult(intent,1);
    }
    /**
     * 初始化
     */
    public void init(){
        message_login_et=(EditText)findViewById(R.id.message_login_et);
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(TextView)findViewById(R.id.header_back_1);
        verify_button=(TextView)findViewById(R.id.verify_button);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        et_pw=(EditText)findViewById(R.id.et_pw);
        background_gray= ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_no_click,null);
        background_theme=ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_theme,null);

        header_front_1.setOnClickListener(this);
        header_back_1.setOnClickListener(this);
        verify_button.setOnClickListener(this);
        message_login_et.setHorizontallyScrolling(true);
        button_verify.setOnClickListener(this);
        verify_state=0;
    }
    @Override
    public void onClick(View view){
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.header_front_1:
                intent.putExtra("result",BACK_STATE_NOLOGIN);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.header_back_1:
                cdh.sendEmptyMessageDelayed(cdh.STOP,1000);
                PasswordLoginActivity.actionStart(this);
                break;
            case R.id.verify_button:
                if(verify_state==0){
                    if(sendVerify()){
                        cdh.sendEmptyMessageDelayed(cdh.START,1000);
                    }
                }
                break;
            case R.id.button_verify:
                String verify=et_pw.getText().toString();
                if(checkVerify(verify)){
                    if(login()){
                        User user=new User();
                        /**
                         * （待填）
                         */
                        loadUserInfo(user);
                        finish();
                    }
                }
            default:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        switch (requestCode){
            case 1:/*密码登录返还后的处理*/
                if(resultCode==RESULT_OK){
                    String returnData=intent.getDataString();
                }
        }
    }
    public CountDownHandler getCdh(){
        return cdh;
    }
    public TextView getVerify_button(){
        return verify_button;
    }
    public void setVerify_state(int state){
        this.verify_state=state;
    }
    public void loadUserInfo(User user){
        app=(E_Trans_Application)getApplication();
        app.setUser(user);
        app.setLoginState(1);
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

    /**（待填）
     * 发送验证码
     * @return 验证码是否发送成功
     */
    public boolean sendVerify(){
        return true;
    }
    /**（待填）
     *判断验证法是否正确
     * @param verify
     * @return
     */
    public boolean checkVerify(String verify){
        return true;
    }

    /**（待填）
     * 登录，加载用户数据
     * @return 加载是否成功
     */
    public boolean login(){
        return true;
    }
}
