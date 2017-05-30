package com.example.dell2.e_transport;

import collector.BaseActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell2 on 2017/5/30.
 */

public class ChangeAddressActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private Drawable blue;
    private Drawable gray;
    private TextView male;
    private TextView female;
    private EditText address_detail;
    private int gender=0;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_changeaddress);
        init();
    }
    public void init(){
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        blue= ResourcesCompat.getDrawable(getResources(),R.drawable.border_round_theme,null);
        gray=ResourcesCompat.getDrawable(getResources(),R.drawable.border_round_no_click,null);
        male=(TextView)findViewById(R.id.male);
        female=(TextView)findViewById(R.id.female);
        address_detail=(EditText)findViewById(R.id.address_detail);
        address_detail.setHorizontallyScrolling(true);
        /*响应时间*/
        header_front_1.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("新增地址");
    }
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.male:
                gender=0;
                male.setBackground(blue);
                female.setBackground(gray);
                break;
            case R.id.female:
                gender=1;
                male.setBackground(gray);
                female.setBackground(blue);
            default:
                break;
        }
    }
}
