package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.Constant;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.ResponseHandler;
import entity.MyOrder;
import entity.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class OrderInfo extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private MyOrder myOrder;
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
    private TextView sendName;
    private LinearLayout button_verify;
    private E_Trans_Application app;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_order_details);
        init();
    }
    public void init(){
        /*实例化*/
        Intent intent=this.getIntent();
        myOrder=(MyOrder)intent.getSerializableExtra("Order_item");
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        send_address=(TextView)findViewById(R.id.send_address);
        receive_address=(TextView)findViewById(R.id.receive_address);
        arrive_time=(TextView)findViewById(R.id.arrive_time);
        serve_price=(TextView)findViewById(R.id.serve_price);
        goods_kind=(TextView)findViewById(R.id.goods_kind);
        goods_weight=(TextView)findViewById(R.id.goods_weight);
        goods_info=(TextView)findViewById(R.id.goods_info);
        sendName=(TextView)findViewById(R.id.sendName);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        /*信息初始化*/
        initInfo();
        /*事件监听*/
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("订单详情");
    }

    /***
     * 信息初始化
     */
    public void initInfo(){
        setSend();
        setReceive();
        setOther();
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.button_verify:
                order();
                break;
            default:
                break;
        }
    }
    public void setSend(){
        send_address.setText(myOrder.getSendAddress());
    }
    public void setReceive(){
        receive_address.setText(myOrder.getReceiveAddress());
    }
    public void setOther(){
        sendName.setText(myOrder.getSendUserName()+"先生");
        arrive_time.setText(myOrder.getTime());
        serve_price.setText("￥"+myOrder.getPriceString());
        goods_kind.setText(myOrder.getGoodsKind());
        goods_info.setText(myOrder.getGoodsInfo());
        goods_weight.setText(myOrder.getGoodsWeight());
    }

    /**（待填）
     * 下单
     * @return 下单是否成功
     */
    public boolean order(){
        CommonRequest request = new CommonRequest();

        app = (E_Trans_Application)getApplication();
        request.setRequestCode("pick");
        request.addRequestParam("userName", app.getUser().getUserEmail());
        request.addRequestParam("orderID",myOrder.getorderID());
        HttpPostTask myTask = sendHttpPostRequest(Constant.ORDER_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(CommonResponse response) {
                Log.e("SEuTTING","S");
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(OrderInfo.this,"接单成功",Toast.LENGTH_SHORT).show();
                finish();
                return response;
            }

            @Override
            public CommonResponse fail(String failCode, String failMsg) {
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(OrderInfo.this,"接单失败",Toast.LENGTH_SHORT).show();
                return null;
            }
        },true);
        return true;
    }
}
