package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import collector.BaseActivity;

/**
 * Created by wangyan on 2017/5/22.
 */

public class MessageLoginActivity extends BaseActivity implements View.OnClickListener{
    public static final String BACK_STATE_NOLOGIN="NOLOGIN";
    public static final String BACK_STATE_LOGIN="LOGIN";
    private EditText message_login_et;
    private ImageView header_front_1;
    private TextView header_back_1;
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
     * 初始化加载title
     */
    public void init(){
        message_login_et=(EditText)findViewById(R.id.message_login_et);
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(TextView)findViewById(R.id.header_back_1);
        header_front_1.setOnClickListener(this);
        header_back_1.setOnClickListener(this);
        message_login_et.setHorizontallyScrolling(true);
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
                PasswordLoginActivity.actionStart(this);
                break;
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
}
