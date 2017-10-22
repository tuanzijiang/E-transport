package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.E_Trans_Application;
import collector.BaseActivity;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.Constant;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.ResponseHandler;
import entity.Location;
import entity.User;
import fragment.UserFragment;

/**
 * Created by dell2 on 2017/5/23.
 */

public class Want2OrderActivity extends BaseActivity implements View.OnClickListener{
    private E_Trans_Application app;
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private TextView sendAddress;
    private TextView sendUserName;
    private TextView sendTel;
    private TextView receiveAddress;
    private TextView receiveUserName;
    private TextView receiveTel;
    private TextView goods_kind;
    private TextView goods_pic;
    private TextView goods_weight;
    private TextView arriveTime;
    private ImageView yes_box;
    private ImageView box;
    private LinearLayout ll_pre_price;
    private EditText pre_price;
    private EditText serve_price;
    private TextView price;
    private TextView button_verify;
    private String goodsInfo;
    private double pre_price_doc=0;
    private double server_price_doc=0;
    private double price_doc=0;
    private boolean isorder=false;
    private int orderAddressID = -1;
    private int recAddressID = -1;
    private DecimalFormat dff   = new DecimalFormat("######0.00");
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
        sendAddress=(TextView)findViewById(R.id.sendAddress);
        sendUserName=(TextView)findViewById(R.id.sendUserName);
        sendTel=(TextView)findViewById(R.id.sendUserTel);
        receiveAddress=(TextView)findViewById(R.id.receiveAddress);
        receiveTel=(TextView)findViewById(R.id.receiveUserTel);
        receiveUserName=(TextView)findViewById(R.id.receiveUserName);
        goods_kind=(TextView)findViewById(R.id.goods_kind);
        goods_weight=(TextView)findViewById(R.id.goods_weight);
        arriveTime=(TextView)findViewById(R.id.arrive_time);
        box=(ImageView)findViewById(R.id.goods_cheap);
        yes_box=(ImageView)findViewById(R.id.goods_exp);
        ll_pre_price=(LinearLayout)findViewById(R.id.ll_pre_price);
        pre_price=(EditText)findViewById(R.id.pre_price);
        serve_price=(EditText)findViewById(R.id.serve_price);
        price=(TextView)findViewById(R.id.price);
        button_verify=(TextView)findViewById(R.id.button_verify);
        /*响应事件*/
        sendAddress.setOnClickListener(this);
        sendUserName.setOnClickListener(this);
        sendTel.setOnClickListener(this);
        receiveAddress.setOnClickListener(this);
        receiveTel.setOnClickListener(this);
        receiveUserName.setOnClickListener(this);
        box.setOnClickListener(this);
        yes_box.setOnClickListener(this);
        header_front_1.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        pre_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    pre_price_doc=Double.valueOf(pre_price.getText().toString());
                    price_doc=pre_price_doc*0.05+server_price_doc;
                    price.setText("总费用"+dff.format(price_doc)+"元");
                }
            }
        });
        serve_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    server_price_doc=Double.valueOf(serve_price.getText().toString());
                    price_doc=pre_price_doc*0.05+server_price_doc;
                    price.setText("总费用"+dff.format(price_doc)+"元");
                }
            }
        });
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("下单");

        goodsInfo = "贵重物品，请轻拿轻放";
        box.setVisibility(View.GONE);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sendAddress:
            case R.id.sendUserName:
            case R.id.sendUserTel:
                isorder=true;
                Intent mintent = new Intent(Want2OrderActivity.this,UsualLocationActivity.class);
                mintent.putExtra("order","address");
                startActivityForResult(mintent,0);
                break;
            case R.id.receiveAddress:
            case R.id.receiveUserTel:
            case R.id.receiveUserName:
                isorder=false;
                Intent nintent = new Intent(Want2OrderActivity.this,UsualLocationActivity.class);
                nintent.putExtra("order","address");
                startActivityForResult(nintent,0);
                break;
            case R.id.header_front_1:
                finish();
                break;
            case R.id.button_verify:
                launchOrder();
                break;
            case R.id.goods_cheap:
                goodsInfo="贵重物品，请轻拿轻放";
                yes_box.setVisibility(View.VISIBLE);
                box.setVisibility(View.GONE);
                ll_pre_price.setVisibility(View.VISIBLE);
                break;
            case R.id.goods_exp:
                goodsInfo="";
                yes_box.setVisibility(View.GONE);
                box.setVisibility(View.VISIBLE);
                ll_pre_price.setVisibility(View.GONE);
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
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        switch (requestCode){
            case 0:/*登录返还后的处理*/
                if(resultCode==RESULT_OK){
                    Location location = (Location) intent.getSerializableExtra("locationItem");
                    if(isorder) {
                        sendAddress.setText(location.getDistrict()+location.getAddress());
                        sendUserName.setText(location.getUserName());
                        sendTel.setText(location.getTel());
                        orderAddressID = location.getID();
                    }
                    else {
                        receiveAddress.setText(location.getDistrict()+location.getAddress());
                        receiveTel.setText(location.getTel());
                        receiveUserName.setText(location.getUserName());
                        recAddressID = location.getID();
                    }
                }
                break;
            default:
                break;
        }
    }
    /**（待填）
     * 下单，存入数据库
     * @return  下单是否成功
     */
    public boolean launchOrder(){
        server_price_doc=Double.valueOf(serve_price.getText().toString());
        if(goodsInfo!=null&&!goodsInfo.equals("")) {
            pre_price_doc = Double.valueOf(pre_price.getText().toString());
        }
        else
        {
            pre_price_doc=0;
        }
        price_doc=pre_price_doc*0.05+server_price_doc;

        price.setText("总费用"+dff.format(price_doc)+"元");
        String order_sendAddress=sendAddress.getText().toString();
        String order_sendUserName=sendUserName.getText().toString();
        String order_sendUserTel=sendTel.getText().toString();
        String order_receiveAddress=receiveAddress.getText().toString();
        String order_receiverUserName=receiveUserName.getText().toString();
        String order_receiveTel=receiveTel.getText().toString();
        String order_goodsKind=goods_kind.getText().toString();
        String order_goodsWeight=goods_weight.getText().toString();
        String order_arrive_time=arriveTime.getText().toString();
        String order_goods_info=goodsInfo;
        String order_price=String.valueOf(price_doc);
        String order_time = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Log.d("time",df.format(new Date()));// new Date()为获取当前系统时间
        order_time = df.format(new Date());

        CommonRequest request = new CommonRequest();

        app=(E_Trans_Application)getApplication();
        User user = app.getUser();
        request.addRequestParam("orderUserName",user.getUserEmail());
        try {
            String strUTF8 = "";
            strUTF8 = URLEncoder.encode(order_goodsKind,"UTF-8");
            //Log.d("Ts",strUTF8);
            request.addRequestParam("goodType", strUTF8);
        }catch (UnsupportedEncodingException e){
            //Log.d("TAG",e.getMessage());
            e.printStackTrace();
        }
        request.addRequestParam("goodWeight",order_goodsWeight);
        request.addRequestParam("value",order_price);
        request.addRequestParam("orderTime",order_time);
        request.addRequestParam("orderAddressID",orderAddressID+"");
        request.addRequestParam("recAddressID",recAddressID+"");
        request.addRequestParam("lastTime",order_arrive_time);
        try {
            String strUTF8 = "";
            strUTF8 = URLEncoder.encode(order_goods_info,"UTF-8");
            //Log.d("Ts",strUTF8);
            request.addRequestParam("goodInfo", strUTF8);
        }catch (UnsupportedEncodingException e){
            //Log.d("TAG",e.getMessage());
            e.printStackTrace();
        }
        String userName = user.getUserEmail();
        String phoneNumber = user.getUserTel();
        request.addRequestParam("userName",userName);
        request.addRequestParam("phoneNumber",phoneNumber);
        HttpPostTask myTask = sendHttpPostRequest(Constant.ORDER_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(CommonResponse response) {
                //Log.e("SETTING","S");
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(Want2OrderActivity.this,"下单成功",Toast.LENGTH_SHORT).show();
                finish();
                return response;
            }

            @Override
            public CommonResponse fail(String failCode, String failMsg) {
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(Want2OrderActivity.this,"下单失败",Toast.LENGTH_SHORT).show();
                return null;
            }
        },true);


        Intent intent=new Intent(Want2OrderActivity.this,OnLinePayMentActivity.class);
        intent.putExtra("price",String.valueOf(price_doc));
        startActivityForResult(intent,1);

        return true;
    }
}
