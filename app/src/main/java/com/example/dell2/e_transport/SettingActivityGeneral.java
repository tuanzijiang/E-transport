package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/28.
 */

public class SettingActivityGeneral extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private LinearLayout ll_screen;
    private ImageView iv_screen;
    private LinearLayout clear;
    private LinearLayout quality;
    private TextView receive;
    private TextView pic_size;
    private int receive_doc;
    private int cut_screen_doc;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_setting_general);
        init();
    }
    private void init(){
        /*实例化*/

        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        ll_screen=(LinearLayout)findViewById(R.id.ll_screen);
        iv_screen=(ImageView)findViewById(R.id.im_screen);
        clear=(LinearLayout)findViewById(R.id.clearImage);
        quality=(LinearLayout)findViewById(R.id.quality);
        receive=(TextView)findViewById(R.id.receive);
        pic_size=(TextView)findViewById(R.id.pic_size);
        /*响应事件*/
        header_front_1.setOnClickListener(this);
        ll_screen.setOnClickListener(this);
        clear.setOnClickListener(this);
        quality.setOnClickListener(this);
        /*初始化标题*/
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("通用");
        /*信息初始化*/
        initInfo();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.clearImage:
                if(clearImage()){
                    setPic_size("0MB");
                }
                break;
            case R.id.quality:
                if(receive_doc==0){
                    WifiReceive(1);
                    setDownLoad(1);
                }
                else{
                    WifiReceive(0);
                    setDownLoad(0);
                }
                break;
            case R.id.ll_screen:
                if(cut_screen_doc==0){
                    setCutScreen(1);
                    CutScreen(1);
                }
                else{
                    setCutScreen(0);
                    CutScreen(0);
                }
            default:
                break;
        }
    }
    public void setPic_size(String size){
        pic_size.setText(size);
    }
    public void setDownLoad(int downLoad){
        if(downLoad==0){
            receive.setText("不接收");
            receive_doc=0;
        }
        else{
            receive.setText("接收");
            receive_doc=1;
        }
    }
    public void setCutScreen(int cut_screen){
        if(cut_screen==0){
            iv_screen.setVisibility(View.GONE);
            cut_screen_doc=0;
        }
        else{
            iv_screen.setVisibility(View.VISIBLE);
            cut_screen_doc=1;
        }
    }
    /**（待填）
     * 设置图片缓存大小，是否wifi才下载图片等。并且需要记录到本地，打开应用以后自动加载
     */
    public void initInfo(){
        setPic_size("123MB");
        setDownLoad(1);
        setCutScreen(0);
    }

    /**（待填）
     * 清除图片缓存的函数，并且需要记录到本地，打开应用以后自动加载
     */
    public boolean clearImage(){
        return true;
    }

    /**(待填)
     * 是否在wifi条件下接受图片，需要记录到本地
     * 0---不接收，1---接受
     * @param receive
     */
    public void WifiReceive(int receive){
    }
    /**(待填)
     *摇一摇截图设置，需要记录到本地
     * 0---可以，1---不可以
     */
    public void CutScreen(int cutScreen){}
}
