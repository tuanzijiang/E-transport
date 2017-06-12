package com.example.dell2.e_transport;

import adapt.LocationAdapt;
import adapt.MyOrderAdapt;
import adapt.OrderAdapt;
import application.E_Trans_Application;
import collector.BaseActivity;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.Constant;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.ResponseHandler;
import entity.Location;
import entity.MyOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dell2 on 2017/5/29.
 */

public class Want2ReceiveActivity extends BaseActivity implements View.OnClickListener {
    private List<MyOrder> OrderList=new ArrayList<MyOrder>();
    private ListView OrderListView;
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private SwipeRefreshLayout srl;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_want2receive);
        init();
    }
    public void init()
    {
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        OrderListView=(ListView)findViewById(R.id.OrdersListView);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("接单");
        header_front_1.setOnClickListener(this);
        srl=(SwipeRefreshLayout)findViewById(R.id.srl);
        initMyOrder();
        srl.setColorSchemeResources(R.color.theme);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);//设置刷新状态，屏蔽下拉事件
                initMyOrder();
                srl.setRefreshing(false);//停止刷新状态，开启下拉事件
            }
        });
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
        }
    }
    /**（待填）
     * 初始化order列表，需要将order信息填入myOrderList里面
     */
    public void initMyOrder(){
        /*初始化order列表*/
        final CommonRequest request = new CommonRequest();
        request.addRequestParam("userName",((E_Trans_Application)getApplication()).getUser().getUserEmail());
        HttpPostTask myTask = sendHttpPostRequest(Constant.REQUEST_ORDER_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(final CommonResponse response) {
                Log.e("S","SSS");
                LoadingDialogUtil.cancelLoading();
                ArrayList<HashMap<String, String>> myList = response.getDataList();
                HashMap<String,String> mymap = null;

                final ArrayList<MyOrder> orders=new ArrayList<MyOrder>();
                for(int i = 0;i < myList.size(); i++){
                    mymap = myList.get(i);
                    MyOrder myOrder = null;
                    myOrder = new MyOrder();
                    myOrder.setGoodsInfo(mymap.get("goodsInfo"));
                    myOrder.setGoodsKind(mymap.get("goodsKind"));
                    myOrder.setGoodsWeight(mymap.get("goodsWeight")+"kg");
                    myOrder.setOrderId(mymap.get("OrderID"));
                    myOrder.setOrderTime(mymap.get("orderTime"));
                    myOrder.setTime(mymap.get("time"));
                    myOrder.setOrderState(0);
                    myOrder.setPrice(Float.parseFloat(mymap.get("price")));
                    myOrder.setReceiveAddress(mymap.get("receiveAddress"));
                    myOrder.setReceiveUserName(mymap.get("receiveUserName"));
                    myOrder.setReceiverUserTel(mymap.get("receiveUserTel"));
                    myOrder.setSendAddress(mymap.get("sendAddress"));
                    myOrder.setSendUserName(mymap.get("sendUserName"));
                    myOrder.setSendUserTel(mymap.get("sendUserTel"));

                    if(mymap.get("longitude")!=null&&!mymap.get("longitude").equals("null")) {
                        Log.d("lo",mymap.get("longitude"));
                        myOrder.setLongitude(Float.parseFloat(mymap.get("longitude")));
                    }
                    if(mymap.get("latitude")!=null&&!mymap.get("latitude").equals("null")) {
                        myOrder.setLatitude(Float.parseFloat(mymap.get("latitude")));
                    }
                    orders.add(myOrder);
                }
                OrderAdapt OrderAdapt=new OrderAdapt(Want2ReceiveActivity.this,R.layout.viewlist_order,orders);
                OrderListView.setAdapter(OrderAdapt);
                OrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView launch = (TextView)findViewById(R.id.launch_userName);

                        MyOrder OrderItem=orders.get(i);

                        Intent intent=new Intent(Want2ReceiveActivity.this, OrderInfo.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("Order_item",OrderItem);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                //finish();
                return response;
            }

            @Override
            public CommonResponse fail(String failCode, String failMsg) {
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(Want2ReceiveActivity.this,failMsg,Toast.LENGTH_SHORT).show();
                return null;
            }
        },true);
    }
}
