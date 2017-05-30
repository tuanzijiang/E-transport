package adapt;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell2.e_transport.R;

import java.util.ArrayList;
import java.util.List;

import entity.Location;
import entity.MyOrder;

/**
 * Created by dell2 on 2017/5/30.
 */

public class LocationAdapt extends ArrayAdapter<Location> {
    private int resourceId;
    public LocationAdapt(@NonNull Context context, @LayoutRes int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Location location=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        }else{
            view=convertView;
        }
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView address=(TextView)view.findViewById(R.id.address);
        TextView tel=(TextView)view.findViewById(R.id.tel);
        name.setText(location.getUserName());
        address.setText(location.getAddress());
        tel.setText(location.getTel());
        return view;
    }
}
