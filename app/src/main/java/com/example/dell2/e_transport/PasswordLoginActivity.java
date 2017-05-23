package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import collector.BaseActivity;

/**
 * Created by dell2 on 2017/5/23.
 */

public class PasswordLoginActivity extends BaseActivity implements View.OnClickListener{
    public static final String BACK_STATE_NOLOGIN="NOLOGIN";
    public static final String BACK_STATE_LOGIN="LOGIN";
    private TextView title_name;
    private ImageView header_front_1;
    private ImageView header_back_1;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_passwordlogin);
        init();
    }
    public void init(){
        title_name=(TextView)findViewById(R.id.title_name);
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        header_back_1.setVisibility(View.GONE);
        header_front_1.setOnClickListener(this);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("密码登录");
    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,PasswordLoginActivity.class);
        MessageLoginActivity messageLoginActivity=(MessageLoginActivity)context;
        messageLoginActivity.startActivityForResult(intent,1);
    }
    public void onClick(View view){
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.header_front_1:
                intent.putExtra("result",BACK_STATE_NOLOGIN);
                setResult(RESULT_OK,intent);
                finish();
                break;
            default:
                break;
        }
    }
}
