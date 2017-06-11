package com.example.dell2.e_transport;

import adapt.LocationAdapt;
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
import entity.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dell2 on 2017/5/30.
 */

public class UsualLocationActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private ListView lv_location;
    private LinearLayout add_address;
    private E_Trans_Application app;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
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

    }
    public void initInfo(){
        /*Location location=new Location();
        locations.add(location);
        locations.add(location);
        locations.add(location);*/
        app = (E_Trans_Application)getApplication();
        User user = app.getUser();
        final CommonRequest request = new CommonRequest();
        request.setRequestCode("requestAddress");
        request.addRequestParam("userName",user.getUserEmail());
        request.addRequestParam("phoneNumber",user.getUserTel());
        HttpPostTask myTask = sendHttpPostRequest(Constant.REQUEST_ADDRESS_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(CommonResponse response) {
                Log.e("S","SSS");
                LoadingDialogUtil.cancelLoading();
                ArrayList<HashMap<String, String>> myList = response.getDataList();

                ArrayList<Location> locations=new ArrayList<Location>();
                for(int i = 0;i < myList.size(); i++){
                    HashMap<String,String> mymap = myList.get(i);
                    Location location = new Location();
                    location.setUserName(mymap.get("contactName"));
                    location.setAddress(mymap.get("addressDetail"));
                    location.setDistrict(mymap.get("addressLocation"));
                    location.setGender(mymap.get("contactGender").equals("male")?0:1);
                    location.setTel(mymap.get("contactPhone"));
                    location.setID(Integer.parseInt(mymap.get("addressID")));
                    Log.d("location",location.toString());
                    locations.add(location);
                }

                final ArrayList<Location> locationss=locations;
                Log.d("co",locations.size()+"");
                LocationAdapt locationAdapt=new LocationAdapt(UsualLocationActivity.this,R.layout.viewlist_location,locations);
                lv_location.setAdapter(locationAdapt);
                lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Location LocationItem=locationss.get(i);
                        Intent intent=new Intent(UsualLocationActivity.this,ChangeAddressActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("locationItem",LocationItem);
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
                Toast.makeText(UsualLocationActivity.this,failMsg,Toast.LENGTH_SHORT).show();
                return null;
            }
        },true);
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
