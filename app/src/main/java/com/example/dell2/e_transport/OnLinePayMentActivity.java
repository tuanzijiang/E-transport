package com.example.dell2.e_transport;

import collector.BaseActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/30.
 */

public class OnLinePayMentActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private Drawable green;
    private Drawable gray;
    private ImageView ali_yes;
    private ImageView wx_yes;
    private TextView price;
    private TextView countDownTime;
    private LinearLayout button_verify;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_onlinepayment);
        init();
    }
    public void init(){
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        green=ResourcesCompat.getDrawable(getResources(),R.drawable.select_green,null);
        gray=ResourcesCompat.getDrawable(getResources(),R.drawable.select_gray,null);
        ali_yes=(ImageView)findViewById(R.id.ali_yes);
        wx_yes=(ImageView)findViewById(R.id.wx_yes);
        price=(TextView)findViewById(R.id.price);
        countDownTime=(TextView)findViewById(R.id.countDownTime);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        /*响应时间*/
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        ali_yes.setOnClickListener(this);
        wx_yes.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("在线支付");
        /*信息初始化*/
        price.setText("￥"+getIntent().getStringExtra("price"));
        setCountDownTime("15:00");
        /*倒计时*/
        countDown();
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.ali_yes:
                wx_yes.setImageResource(R.drawable.select_gray);
                ali_yes.setImageResource(R.drawable.select_green);
                break;
            case R.id.wx_yes:
                wx_yes.setImageResource(R.drawable.select_green);
                ali_yes.setImageResource(R.drawable.select_gray);
                break;
            case R.id.button_verify:
                if(verify()){
                    Intent intent=new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }
    public void setCountDownTime(String time){
        countDownTime.setText(time);
    }
    /**（待填）
     *倒计时
     */
    public void countDown(){

    }

    public boolean verify(){
        return true;
    }
}
