package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/28.
 */

public class UserAvoidPwActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private ImageView iv_yes;
    private LinearLayout ll_yes;
    private E_Trans_Application app;
    private int isAvoid;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_avoidpassword);
        init();
    }
    public void init(){
        /*实例化*/
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        iv_yes=(ImageView)findViewById(R.id.iv_yes);
        ll_yes=(LinearLayout)findViewById(R.id.ll_yes);
        app=(E_Trans_Application)getApplication();
        /*信息初始化*/
        initInfo();
        /*事件监听*/
        header_front_1.setOnClickListener(this);
        ll_yes.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("用户名");
    }
    public void initInfo(){
        if(app.getUser().getIsAvoidCover()==1){
            iv_yes.setVisibility(View.VISIBLE);
            isAvoid=1;
        }
        else{
            iv_yes.setVisibility(View.GONE);
            isAvoid=0;
        }
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.ll_yes:
                if(isAvoid==1){
                    iv_yes.setVisibility(View.GONE);
                    if(setIsAvoidPw(0)){
                        isAvoid=0;
                        app.getUser().setIsAvoidCover(0);
                    }
                }
                else{
                    iv_yes.setVisibility(View.VISIBLE);
                    if(setIsAvoidPw(1)){
                        isAvoid=1;
                        app.getUser().setIsAvoidCover(1);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**（待填）
     * 设置免密支付的函数
     * 参数：int userIsAvoidPw(需要免密支付)
     * 返回值：是否设置成功
     */
    public boolean setIsAvoidPw(int userIsAvoidPw){
        return true;
    }

}
