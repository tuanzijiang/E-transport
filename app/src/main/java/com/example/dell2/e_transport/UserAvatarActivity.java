package com.example.dell2.e_transport;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.widget.Button;

import application.E_Trans_Application;
import collector.BaseActivity;

/**
 * Created by wangyan on 2017/6/6.
 */

public class UserAvatarActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    private Dialog dialog;
    private View inflate;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_avatar);
        init();
    }
    private void init(){
        /*实例化*/
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        /*初始化标题*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setImageResource(R.drawable.more);
        title_name.setText("头像");
         /*响应事件*/
        header_front_1.setOnClickListener(this);
        header_back_1.setOnClickListener(this);
        /*信息初始化*/
        initInfo();
    }

    public void show(View view){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(this).inflate(R.layout.activity_photo_popup, null);
        choosePhoto = (Button) inflate.findViewById(R.id.choosePhoto);
        takePhoto = (Button) inflate.findViewById(R.id.takePhoto);
        cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        choosePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_front_1:
            finish();
                break;
            case R.id.header_back_1:
                show(view);
            break;
            case R.id.takePhoto:
                Toast.makeText(this,"点击了拍照",Toast.LENGTH_SHORT).show();
                break;
            case R.id.choosePhoto:
                Toast.makeText(this,"点击了选择照片",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
            default:
                break;
        }
    }
    /**（待填）
     * 设置头像加载
     */
    public void initInfo(){

    }

}
