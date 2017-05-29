package adapt;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell2.e_transport.R;

import org.w3c.dom.Text;

import java.util.List;

import entity.MyOrder;

/**
 * Created by dell2 on 2017/5/24.
 */

public class MyOrderAdapt extends ArrayAdapter<MyOrder> {
    private int resourceId;
    public MyOrderAdapt(@NonNull Context context, @LayoutRes int resource, @NonNull List<MyOrder> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MyOrder myOrder=getItem(position);
        View view;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,null);
        }else{
            view=convertView;
        }
        ImageView kindImage=(ImageView)view.findViewById(R.id.kindImage);
        TextView sendAddress=(TextView)view.findViewById(R.id.sendAddress);
        TextView receiveAddress=(TextView)view.findViewById(R.id.receiveAddress);
        TextView price=(TextView)view.findViewById(R.id.price);
        TextView orderSend=(TextView)view.findViewById(R.id.orderSend);
        kindImage.setImageResource(myOrder.getKindImageString());
        sendAddress.setText(myOrder.getSendAddress());
        receiveAddress.setText(myOrder.getReceiveAddress());
        price.setText(myOrder.getPriceString());
        orderSend.setText(myOrder.getOrderSendString());
        return view;
    }
}
