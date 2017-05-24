package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dell2.e_transport.MainActivity;
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
        initMyOrder();
        MyOrderAdapt myOrderAdapt=new MyOrderAdapt(getActivity(),R.layout.viewlist_myorder,myOrderList);
        myOrderListView.setAdapter(myOrderAdapt);
        myOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyOrder myOrderItem=myOrderList.get(i);
            }
        });
    }
    public void initMyOrder(){
        /*初始化order列表*/
        MyOrder myOrder=new MyOrder("123456","华东师范大学（中北校区）","华东师范大学（闵行校区）",0,0,20);
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
        myOrderList.add(myOrder);
    }
}
