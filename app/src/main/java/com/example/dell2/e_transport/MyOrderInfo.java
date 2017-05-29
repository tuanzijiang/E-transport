package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;
import entity.MyOrder;
import entity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by dell2 on 2017/5/29.
 */

public class MyOrderInfo extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private MyOrder myOrder;
    private TextView distance;
    private TextView wait_info;
    private TextView launch_userName;
    private TextView postMan_userName;
    private LinearLayout launch_button;
    private LinearLayout postman_button;
    private TextView send_userName;
    private TextView receive_userName;
    private TextView send_tel;
    private TextView receive_tel;
    private TextView send_address;
    private TextView receive_address;
    private TextView arrive_time;
    private TextView serve_price;
    private TextView goods_kind;
    private ImageView goods_pic;
    private TextView goods_weight;
    private TextView goods_info;
    private TextView order_ID;
    private TextView order_cover_kind;
    private TextView order_time;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_order_info1);
        init();
    }
    public void init(){
        /*实例化*/
        Intent intent=this.getIntent();
        myOrder=(MyOrder)intent.getSerializableExtra("myOrder_item");
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        distance=(TextView)findViewById(R.id.distance);
        wait_info=(TextView)findViewById(R.id.wait_info);
        launch_userName=(TextView)findViewById(R.id.launch_userName);
        postMan_userName=(TextView)findViewById(R.id.postMan_userName);
        launch_button=(LinearLayout)findViewById(R.id.launch_button);
        postman_button=(LinearLayout)findViewById(R.id.postman_button);
        send_address=(TextView)findViewById(R.id.send_address);
        send_userName=(TextView)findViewById(R.id.send_userName);
        send_tel=(TextView)findViewById(R.id.send_tel);
        receive_userName=(TextView)findViewById(R.id.receive_userName);
        receive_tel=(TextView)findViewById(R.id.receive_tel);
        receive_address=(TextView)findViewById(R.id.receive_address);
        arrive_time=(TextView)findViewById(R.id.arrive_time);
        serve_price=(TextView)findViewById(R.id.serve_price);
        goods_kind=(TextView)findViewById(R.id.goods_kind);
        goods_weight=(TextView)findViewById(R.id.goods_weight);
        goods_info=(TextView)findViewById(R.id.goods_info);
        goods_pic=(ImageView)findViewById(R.id.goods_pic);
        order_ID=(TextView)findViewById(R.id.order_ID);
        order_cover_kind=(TextView)findViewById(R.id.order_cover_kind);
        order_time=(TextView)findViewById(R.id.order_time);
        /*信息初始化*/
        initInfo();
        /*事件监听*/
        header_front_1.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("订单详情");
    }

    /***
     * 信息初始化
     */
    public void initInfo(){
        setSubTitle();
        setLaunch();
        setPost();
        setSend();
        setReceive();
        setOther();

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.launch_button:
                call(myOrder.getLaunchMan().getUserTel());
                break;
            case R.id.postman_button:
                call(myOrder.getPostMan().getUserTel());
                break;
            default:
                break;
        }
    }
    public void setSubTitle(){
        String str1="距离目的地还有";
        String str1_extra="请及时配送";
        String str2="配送者距目的地还有";
        String str2_extra="请耐心等候";
        switch(myOrder.getOrderSend()){
            case 0:
                distance.setText(str1+getDistance());
                wait_info.setText(str1_extra);
                break;
            case 1:
                distance.setText(str2+getDistance());
                wait_info.setText(str2_extra);
                break;
            default:
                break;
        }
    }
    public void setLaunch(){
        String str1=myOrder.getLaunchMan().getUserName().substring(0,1);
        switch (myOrder.getLaunchMan().getUserGender()){
            case 0:
                str1+="先生";
                break;
            case 1:
                str1+="女士";
                break;
            default:
                str1=myOrder.getLaunchMan().getUserName();
                break;
        }
        String str2="自己";
        switch(myOrder.getOrderSend()){
            case 0:
                launch_userName.setText(str1);
                launch_button.setOnClickListener(this);
                break;
            case 1:
                launch_userName.setText(str2);
                launch_button.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
    public void setPost(){
        String str1=myOrder.getPostMan().getUserName().substring(0,1);
        switch (myOrder.getPostMan().getUserGender()){
            case 0:
                str1+="小哥";
                break;
            case 1:
                str1+="小姐";
                break;
            default:
                str1=myOrder.getLaunchMan().getUserName();
                break;
        }
        String str2="自己";
        switch(myOrder.getOrderSend()){
            case 0:
                postMan_userName.setText(str2);
                postman_button.setVisibility(View.GONE);
                break;
            case 1:
                postMan_userName.setText(str1);
                postman_button.setOnClickListener(this);
                break;
            default:
                break;
        }
    }
    public void setSend(){
        User sendUser=myOrder.getSendMan();
        send_userName.setText(sendUser.getUserName());
        send_tel.setText(sendUser.getUserTel());
        send_address.setText(myOrder.getSendAddress());
    }
    public void setReceive(){
        User receiveUser=myOrder.getReceiveMan();
        receive_userName.setText(receiveUser.getUserName());
        receive_tel.setText(receiveUser.getUserTel());
        receive_address.setText(myOrder.getReceiveAddress());
    }
    public void setOther(){
        arrive_time.setText(myOrder.getTime());
        serve_price.setText(myOrder.getPriceString());
        goods_kind.setText(myOrder.getGoodsKind());
        goods_info.setText(myOrder.getGoodsInfo());
        goods_weight.setText(myOrder.getGoodsWeight());
        order_ID.setText(myOrder.getOrderId());
        order_cover_kind.setText(myOrder.getCoverKind());
        order_time.setText(myOrder.getOrderTime());
    }
    /**（待填）
     *
     * @return 配送者离目的地的距离
     */
    public String getDistance(){
        return "6km";
    }

    /**（待填）
     * 调动系统的电话功能，打电话
     * @param tel 手机号
     */
    public void call(String tel){
        Toast.makeText(MyOrderInfo.this,tel,Toast.LENGTH_SHORT).show();
    }
}
