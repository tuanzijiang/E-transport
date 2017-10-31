package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell2.e_transport.MainActivity;
import com.example.dell2.e_transport.MyOrderInfo;
import com.example.dell2.e_transport.OrderInfo;
import com.example.dell2.e_transport.R;
import com.example.dell2.e_transport.Want2ReceiveActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapt.MyOrderAdapt;
import adapt.OrderAdapt;
import application.E_Trans_Application;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.Constant;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.ResponseHandler;
import entity.MyOrder;

/**
 * Created by dell2 on 2017/5/20.
 */

public class OrderFragment extends Fragment {
    private ListView myOrderListView;
    private LinearLayout refresh_info;
    private SwipeRefreshLayout srl;
    private List<MyOrder> myOrderList=new ArrayList<MyOrder>();
    private String name;
    private E_Trans_Application app;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.order,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        name =((E_Trans_Application) getActivity().getApplication()).getUser().getUserEmail();
        init();
    }
    public void init(){
        myOrderListView=(ListView)getView().findViewById(R.id.myOrdersListView);
        refresh_info=(LinearLayout)getView().findViewById(R.id.refresh_info);
        srl=(SwipeRefreshLayout)getView().findViewById(R.id.srl);
        refresh_info.setVisibility(View.GONE);
        initMyOrder();
        srl.setColorSchemeResources(R.color.theme);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);//设置刷新状态，屏蔽下拉事件
                initMyOrder();
                srl.setRefreshing(false);//停止刷新状态，开启下拉事件
                //delayGoneRefresh();
            }
        });

    }
    public void delayGoneRefresh(){
        refresh_info.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh_info.setVisibility(View.GONE);
            }
        },1000);
    }
    /**（待填）
     * 初始化order列表，需要将order信息填入myOrderList里面
     */
    public void initMyOrder(){
        /*初始化order列表*/
        final CommonRequest request = new CommonRequest();
        request.setRequestCode("myorder");
        request.addRequestParam("userName",name);
        HttpPostTask myTask = ((MainActivity)getActivity()).sendHttpPostRequest(Constant.REQUEST_ORDER_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(final CommonResponse response) {
                Log.e("S","SSS");
                LoadingDialogUtil.cancelLoading();
                ArrayList<HashMap<String, String>> myList = response.getDataList();

                final ArrayList<MyOrder> orders=new ArrayList<MyOrder>();
                for(int i = 0;i < myList.size(); i++){
                    HashMap<String,String> mymap = myList.get(i);
                    MyOrder myOrder = null;
                    myOrder = new MyOrder();
                    myOrder.setGoodsInfo(mymap.get("goodsInfo"));
                    myOrder.setGoodsKind(mymap.get("goodsKind"));
                    myOrder.setGoodsWeight(mymap.get("goodsWeight")+"kg");
                    myOrder.setOrderId(mymap.get("OrderID"));
                    myOrder.setOrderTime(mymap.get("orderTime"));
                    myOrder.setTime(mymap.get("time"));
                    myOrder.setOrderState(mymap.get("orderState").equals("0")?0:1);
                    myOrder.setOrderSend(mymap.get("orderSend").equals("0")?0:1);
                    myOrder.setPrice(Float.parseFloat(mymap.get("price")));
                    myOrder.setReceiveAddress(mymap.get("receiveAddress"));
                    myOrder.setReceiveUserName(mymap.get("receiveUserName"));
                    myOrder.setReceiverUserTel(mymap.get("receiveUserTel"));
                    myOrder.setSendAddress(mymap.get("sendAddress"));
                    myOrder.setSendUserName(mymap.get("sendUserName"));
                    myOrder.setSendUserTel(mymap.get("sendUserTel"));
                    myOrder.setLaunchMan(mymap.get("LaunchMan"));
                    myOrder.setLaunchManTel(mymap.get("LaunchManTel"));
                    myOrder.setLaunchManGender(mymap.get("LaunchManGender").equals("female")?0:1);
                    myOrder.setPostMan(mymap.get("PostMan"));
                    myOrder.setPostManTel(mymap.get("PostManTel"));
                    myOrder.setPostManGender(mymap.get("PostManGender").equals("female")?0:1);
                    if(mymap.get("longitude")!=null&&!mymap.get("longitude").equals("null")) {
                        Log.d("lo",mymap.get("longitude"));
                        myOrder.setLongitude(Float.parseFloat(mymap.get("longitude")));
                    }
                    if(mymap.get("latitude")!=null&&!mymap.get("latitude").equals("null")) {
                        myOrder.setLatitude(Float.parseFloat(mymap.get("latitude")));
                    }
                    orders.add(myOrder);
                }
                MyOrderAdapt myOrderAdapt=new MyOrderAdapt(getActivity(),R.layout.viewlist_myorder,orders);
                myOrderListView.setAdapter(myOrderAdapt);
                myOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MyOrder myOrderItem=orders.get(i);
                        Intent intent=new Intent(getActivity(), MyOrderInfo.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("myOrder_item",myOrderItem);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                return response;
            }

            @Override
            public CommonResponse fail(String failCode, String failMsg) {
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(getActivity(),failMsg,Toast.LENGTH_SHORT).show();
                return null;
            }
        },true);
    }
}
