package com.example.dell2.e_transport;

import adapt.MyOrderAdapt;
import adapt.OrderAdapt;
import collector.BaseActivity;
import entity.MyOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell2 on 2017/5/29.
 */

public class Want2ReceiveActivity extends BaseActivity implements View.OnClickListener {
    private List<MyOrder> OrderList=new ArrayList<MyOrder>();
    private ListView OrderListView;
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
        OrderListView=(ListView)findViewById(R.id.OrdersListView);
        initMyOrder();
        OrderAdapt OrderAdapt=new OrderAdapt(Want2ReceiveActivity.this,R.layout.viewlist_order,OrderList);
        OrderListView.setAdapter(OrderAdapt);
    }
    public void onClick(View view){

    }
    /**（待填）
     * 初始化order列表，需要将order信息填入myOrderList里面
     */
    public void initMyOrder(){
        /*初始化order列表*/
        MyOrder myOrder=new MyOrder();
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
    }
}
