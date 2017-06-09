package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * Created by dell2 on 2017/5/25.
 */

public class UserAreaAvtivity extends BaseActivity implements View.OnClickListener{

    //old*******************
    private ImageView back_button;
    private E_Trans_Application app;
    private ImageView search_button;
    private EditText location;
    private RelativeLayout button_verify;
   private MapView mMapView;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_usermap);
        mMapView = (MapView) findViewById(R.id.mymap);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写

        aMap = mMapView.getMap();

        Log.e("myamap",String.valueOf(aMap));


            aMap.setTrafficEnabled(true);// 显示实时交通状况
            //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式

        init();
    }
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
//    private void Location() {
//    }
protected void onResume() {
    super.onResume();
    //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
    mMapView.onResume();
}
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    private void init(){

//        if (aMap == null) {
//            aMap = mMapView.getMap();
//        }
        /*实例化*/
        button_verify=(RelativeLayout)findViewById(R.id.button_verify);
        back_button=(ImageView)findViewById(R.id.back_button);
        search_button=(ImageView)findViewById(R.id.search_button);
        location=(EditText)findViewById(R.id.location);
        app=(E_Trans_Application)getApplication();
        /*响应事件*/
        back_button.setOnClickListener(this);
        search_button.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*初始化定位*/
        initLocation();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.search_button:
                if(location.getText().toString()!=null&&!location.getText().toString().equals(""))
                    search(location.getText().toString());
                else
                    Toast.makeText(UserAreaAvtivity.this,"地址不能为空",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_verify:
                if(location.getText().toString()!=null&&!location.getText().toString().equals("")) {
                    if(this.getIntent().getExtras().getString("kind")==null){
                        if(setUserLocation(location.getText().toString())){
                            app.getUser().setUserAddress(location.getText().toString());
                            finish();
                        }
                    }
                    else{
                        Intent intent=new Intent();
                        intent.putExtra("address",location.getText().toString());
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }
                else
                    Toast.makeText(UserAreaAvtivity.this,"地址不能为空",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**（待填）
     * 在地图上显示用户搜索的地址
     * @param location 用户的输入
     */
    public void search(String location){

    }

    /**（待填）
     * 将用户的地址放进数据库里面
     * @param location 用户地址
     */
    public boolean setUserLocation(String location){
        return true;
    }
    /**（待填）
     *  初始化地图中心点的位置
     */
    public void initLocation(){
    }

}
