package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/25.
 */

public class UserAreaAvtivity extends BaseActivity implements View.OnClickListener {
    private ImageView back_button;
    private E_Trans_Application app;
    private ImageView search_button;
    private EditText location;
    private RelativeLayout button_verify;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_usermap);
        init();
    }
    private void init(){
        /*实例化*/
        button_verify=(RelativeLayout)findViewById(R.id.button_verify);
        back_button=(ImageView)findViewById(R.id.back_button);
        search_button=(ImageView)findViewById(R.id.search_button);
        location=(EditText)findViewById(R.id.location);
        app=(E_Trans_Application)getApplication();
        /*响应事件*/
        back_button.setOnClickListener(this);
        search_button.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*初始化定位*/
        initLocation();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.search_button:
                if(location.getText().toString()!=null&&!location.getText().toString().equals(""))
                    search(location.getText().toString());
                else
                    Toast.makeText(UserAreaAvtivity.this,"地址不能为空",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_verify:
                if(location.getText().toString()!=null&&!location.getText().toString().equals("")) {
                    if(this.getIntent().getExtras().getString("kind")==null){
                        if(setUserLocation(location.getText().toString())){
                            app.getUser().setUserAddress(location.getText().toString());
                            finish();
                        }
                    }
                    else{
                        Intent intent=new Intent();
                        intent.putExtra("address",location.getText().toString());
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }
                else
                    Toast.makeText(UserAreaAvtivity.this,"地址不能为空",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**（待填）
     * 在地图上显示用户搜索的地址
     * @param location 用户的输入
     */
    public void search(String location){

    }

    /**（待填）
     * 将用户的地址放进数据库里面
     * @param location 用户地址
     */
    public boolean setUserLocation(String location){
        return true;
    }
    /**（待填）
     *  初始化地图中心点的位置
     */
    public void initLocation(){
    }
}
