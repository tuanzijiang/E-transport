package com.example.dell2.e_transport;

import application.E_Trans_Application;
import collector.BaseActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import java.util.ArrayList;
import java.util.List;

import entity.User;
import overlay.PoiOverlay;
import util.AMapUtil;
import util.ToastUtil;

/**
 * Created by dell2 on 2017/5/25.
 */

public class UserAreaAvtivity extends BaseActivity implements View.OnClickListener ,
        PoiSearch.OnPoiSearchListener ,Inputtips.InputtipsListener , TextWatcher{

    //old*******************
    private ImageView back_button;
    private E_Trans_Application app;
    private ImageView search_button;
    private EditText location;
    private RelativeLayout button_verify;
    private MapView mMapView = null;
    private AMap aMap;
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private AutoCompleteTextView searchText;// 输入搜索关键字
    private String keyWord = "";// 要输入的poi搜索关键字
    private ProgressDialog progDialog = null;// 搜索时进度条
    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private String  etDistrict, etAddress;//区，地址
    double etLng, etLat;//横纵
    private String preAc;
    GeocodeSearch search;
    int m=0;
    int j=0;
    LatLonPoint currLatLonll=new LatLonPoint(31.227901,121.4029);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aaa", "dddd");
        preAc = null;
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_usermap);
        mMapView = (MapView) findViewById(R.id.mymap);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        search = new GeocodeSearch(this);
        search.setOnGeocodeSearchListener(new OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                etDistrict=addr.getProvince()+addr.getDistrict()+addr.getBuilding();
                etAddress=addr.getFormatAddress();
                Log.d("etDistrict",etDistrict);
                if(preAc.equals("setting")){
                    Log.d("area","setting");
                    User myuser = app.getUser();
                    myuser.setUserAddress(etDistrict);
                    Intent intent = getIntent();
                    intent.putExtra("address",etDistrict);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else {
                    Intent intent = getIntent();
                    intent.putExtra("Daddress", etDistrict);
                    Log.d("latlng", String.valueOf(etLng));
                    Log.d("Daddress",etDistrict);
                    intent.putExtra("longitude",String.valueOf(etLng));
                    intent.putExtra("latitude",String.valueOf(etLat));
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                GeocodeAddress addr = geocodeResult.getGeocodeAddressList().get(0);
                LatLonPoint latlng = addr.getLatLonPoint();
                etLat=latlng.getLatitude();
                etLng=latlng.getLongitude();
                setUseretLatLng(etLat,etLng);

            }
        });
        init();
    }


    /**
     * 点击搜索按钮
     */
    public void searchButton() {
        keyWord = AMapUtil.checkEditText(searchText);
        if ("".equals(keyWord)) {
            ToastUtil.show(UserAreaAvtivity.this, "请输入搜索关键字");
            return;
        } else {
            doSearchQuery();
        }
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

    private void init() {

        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
        /*实例化*/
        button_verify = (RelativeLayout) findViewById(R.id.button_verify);
        back_button = (ImageView) findViewById(R.id.back_button);
        search_button = (ImageView) findViewById(R.id.search_button);
        location = (EditText) findViewById(R.id.location);
        app = (E_Trans_Application) getApplication();
        /*响应事件*/
        back_button.setOnClickListener(this);
        search_button.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        /*初始化定位*/
//        initLocation();

        // 创建GeocodeSearch对象

    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，
        // 定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        setupLocationStyle();

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        ImageView searButton = (ImageView) findViewById(R.id.search_button);//sezrch
        searButton.setOnClickListener(this);
        searchText = (AutoCompleteTextView) findViewById(R.id.location);
        searchText.addTextChangedListener(this);// 添加文本输入框监听事件
    }

    /**
     * 设置自定义定位蓝点
     */
    private void setupLocationStyle() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(STROKE_COLOR);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + keyWord);
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        showProgressDialog();// 显示进度框
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
        String a=query.getQueryString();
        String b=query.getCity();
    }





    /**
     * （待填）
     * 在地图上显示用户搜索的地址
     *
     * @param location 用户的输入
     */
    public void search(String location) {

    }

    /**
     * （待填）
     * 将用户的地址放进数据库里面
     *
     * @param location 用户地址
     */

    public boolean setUserLocation(String location) {
        String address = keyWord.trim();
        GeocodeQuery mquery = new GeocodeQuery(address, "");
        // 根据地理名称执行异步解析
        search.getFromLocationNameAsyn(mquery);

        return true;
    }

    public boolean setUseretLatLng(double Lat,double Lng)
    {
        search.getFromLocationAsyn(new RegeocodeQuery(
                new LatLonPoint(Lat, Lng)
                , 20 // 区域半径
                , GeocodeSearch.GPS));
        return true;
    }

    /**
     * （待填）
     * 初始化地图中心点的位置
     */
//    public void initLocation() {
//    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        dissmissProgressDialog();// 隐藏对话框
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                    if (poiItems != null && poiItems.size() > 0) {
                        aMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        ToastUtil.show(UserAreaAvtivity.this,
                                R.string.no_result);
                    }
                }
            } else {
                ToastUtil.show(UserAreaAvtivity.this,
                        R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(UserAreaAvtivity.this, infomation);

    }

    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
            List<String> listString = new ArrayList<String>();
            for (int i = 0; i < tipList.size(); i++) {
                listString.add(tipList.get(i).getName());
            }
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.route_input, listString);
            searchText.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showerror(this, rCode);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        if (!AMapUtil.IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, "");
            Inputtips inputTips = new Inputtips(UserAreaAvtivity.this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.search_button:
                if (location.getText().toString() != null && !location.getText().toString().equals(""))
                {
//                    search(location.getText().toString());
                    searchButton();
                }
                else
                    Toast.makeText(UserAreaAvtivity.this, "地址不能为空", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_verify:
                if (keyWord != null && !keyWord.equals("")) {
                    Log.d("aaaaaa","bbbb");
                    preAc = this.getIntent().getExtras().getString("kind");
                    setUserLocation(keyWord);
//                    if (this.getIntent().getExtras().getString("kind") == null) {
//
//                        if (setUserLocation(location.getText().toString())) {
//                            app.getUser().setUserAddress(location.getText().toString());
//                            finish();
//                        }
//                    }
                } else
                    Toast.makeText(UserAreaAvtivity.this, "地址不能为空", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


public void distancecount(double lat,double lng){

    LatLonPoint deslatlngllp=new LatLonPoint(lng,lat);
    LatLng deslatlng=AMapUtil.convertToLatLng(deslatlngllp);
    LatLng currLatLon=AMapUtil.convertToLatLng(currLatLonll);
   float distance = AMapUtils.calculateLineDistance(currLatLon,deslatlng);
    }

}
