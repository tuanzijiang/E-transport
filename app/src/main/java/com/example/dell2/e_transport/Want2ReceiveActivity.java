package com.example.dell2.e_transport;

import adapt.MyOrderAdapt;
import adapt.OrderAdapt;
import collector.BaseActivity;
import entity.MyOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
        OrderAdapt OrderAdapt=new OrderAdapt(Want2ReceiveActivity.this,R.layout.viewlist_order,OrderList);
        OrderListView.setAdapter(OrderAdapt);
        srl.setColorSchemeResources(R.color.theme);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);//设置刷新状态，屏蔽下拉事件
                /**（待填）
                 *  刷新函数
                 */
                srl.setRefreshing(false);//停止刷新状态，开启下拉事件
            }
        });
        OrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyOrder OrderItem=OrderList.get(i);
                Intent intent=new Intent(Want2ReceiveActivity.this, OrderInfo.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("Order_item",OrderItem);
                intent.putExtras(bundle);
                startActivity(intent);
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
        MyOrder myOrder=new MyOrder();
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
        OrderList.add(myOrder);
    }
}
