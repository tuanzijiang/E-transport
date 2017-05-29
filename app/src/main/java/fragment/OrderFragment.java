package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell2.e_transport.MainActivity;
import com.example.dell2.e_transport.MyOrderInfo;
import com.example.dell2.e_transport.R;

import java.util.ArrayList;
import java.util.List;

import adapt.MyOrderAdapt;
import entity.MyOrder;

/**
 * Created by dell2 on 2017/5/20.
 */

public class OrderFragment extends Fragment {
    private ListView myOrderListView;
    private LinearLayout refresh_info;
    private SwipeRefreshLayout srl;
    private List<MyOrder> myOrderList=new ArrayList<MyOrder>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.order,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
    }
    public void init(){
        myOrderListView=(ListView)getView().findViewById(R.id.myOrdersListView);
        refresh_info=(LinearLayout)getView().findViewById(R.id.refresh_info);
        srl=(SwipeRefreshLayout)getView().findViewById(R.id.srl);
        refresh_info.setVisibility(View.GONE);
        initMyOrder();
        MyOrderAdapt myOrderAdapt=new MyOrderAdapt(getActivity(),R.layout.viewlist_myorder,myOrderList);
        srl.setColorSchemeResources(R.color.theme);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);//设置刷新状态，屏蔽下拉事件
                /**（待填）
                 *  刷新函数
                 */
                srl.setRefreshing(false);//停止刷新状态，开启下拉事件
                //delayGoneRefresh();
            }
        });
        myOrderListView.setAdapter(myOrderAdapt);
        myOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyOrder myOrderItem=myOrderList.get(i);
                Intent intent=new Intent(getActivity(), MyOrderInfo.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("myOrder_item",myOrderItem);
                intent.putExtras(bundle);
                startActivity(intent);
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
        MyOrder myOrder=new MyOrder();
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
    }
}
