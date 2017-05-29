package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import collector.BaseActivity;

/**
 * Created by dell2 on 2017/5/23.
 */

public class Want2OrderActivity extends BaseActivity implements View.OnClickListener{
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_want2order);
        init();
    }
    public void init()
    {
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("下单");
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            default:
                break;
        }
    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,Want2OrderActivity.class);
        MainActivity mainActivity=(MainActivity)context;
        mainActivity.startActivityForResult(intent,2);
    }
}
