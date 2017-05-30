package com.example.dell2.e_transport;

import adapt.LocationAdapt;
import collector.BaseActivity;
import entity.Location;
import entity.MyOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell2 on 2017/5/30.
 */

public class UsualLocationActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private ArrayList<Location> locations=new ArrayList<Location>();
    private ListView lv_location;
    private LinearLayout add_address;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_location);
        init();
    }
    public void init(){
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        lv_location=(ListView)findViewById(R.id.lv_location);
        add_address=(LinearLayout)findViewById(R.id.add_address);

        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);
        title_name.setText("常用地址");

        header_front_1.setOnClickListener(this);
        add_address.setOnClickListener(this);

        initInfo();
        LocationAdapt locationAdapt=new LocationAdapt(UsualLocationActivity.this,R.layout.viewlist_location,locations);
        lv_location.setAdapter(locationAdapt);
        lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Location LocationItem=locations.get(i);
                Intent intent=new Intent(UsualLocationActivity.this,ChangeAddressActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("locationItem",LocationItem);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void initInfo(){
        Location location=new Location();
        locations.add(location);
        locations.add(location);
        locations.add(location);
    }
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.add_address:
                intent=new Intent(UsualLocationActivity.this,ChangeAddressActivity.class);
                intent.putExtra("kind","add");
                startActivity(intent);
                break;
            case R.id.header_front_1:
                finish();
                break;
            default:
                break;
        }
    }
}
