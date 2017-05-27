package com.example.dell2.e_transport;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;


import application.E_Trans_Application;
import collector.BaseActivity;
import entity.User;
import fragment.HomePageFragment;
import fragment.OrderFragment;
import fragment.UserFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener{
    final String HOMEPAGETAG="homePageFragmentTag";
    final String ORDERTAG="orderFragmentTag";
    final String USERTAG="userFragmentTag";
    private ImageView alarm;
    private ImageView header_back_1;
    private ImageView setting;
    private HomePageFragment homePageFragment;
    private OrderFragment orderFragment;
    private UserFragment userFragment;
    private TextView title_name;
    private TextView homeTV;
    private TextView orderTV;
    private TextView userTV;
    private ImageView homeIV;
    private ImageView orderIV;
    private ImageView userIV;
    private RelativeLayout homeRelativeLayout;
    private RelativeLayout orderRelativeLayout;
    private RelativeLayout userRelativeLayout;
    private FragmentManager fragmentManager;
    private AssetManager assetManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 连接数据库的,加载用户信息
     */
    public void setUserInfo(){
        E_Trans_Application app=(E_Trans_Application)getApplication();
        User user=new User();
        user.setUserTel("123456789");
        user.setUserName("吗字典");
        user.setUserGender(0);
        user.setUserAddress("上海市 普陀区华东师范大学");
        user.setUserPwLogin("123456");
        app.setLoginState(1);
        app.setUser(user);
    }


    /**
     * 处理从其他activity返回的结果
     */
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        switch (requestCode){
            case 1:/*登录返还后的处理*/
                if(resultCode==RESULT_OK){
                    String returnDate=intent.getStringExtra("result");
                    UserFragment userFragment=(UserFragment)fragmentManager.findFragmentByTag(USERTAG);
                    userFragment.setLogin("未登录");
                }
                break;
            case 2:
                break;
            default:
                break;
        }
    }
    /**
     * 对页面进行初始化：加载响应事件、给私有属性链接实例、初始化碎片、初始化title等
     */
    private void init(){
        /*私有属性实例化*/
        title_name=(TextView)findViewById(R.id.title_name);
        homeTV=(TextView)findViewById(R.id.main_bottom_home_text);
        homeIV=(ImageView)findViewById(R.id.main_bottom_home_image);
        orderTV=(TextView)findViewById(R.id.main_bottom_order_text);
        orderIV=(ImageView)findViewById(R.id.main_bottom_order_image);
        userTV=(TextView)findViewById(R.id.main_bottom_user_text);
        userIV=(ImageView)findViewById(R.id.main_bottom_user_image);
        homeRelativeLayout=(RelativeLayout)findViewById(R.id.main_bottom_home);
        orderRelativeLayout=(RelativeLayout)findViewById(R.id.main_bottom_order);
        userRelativeLayout=(RelativeLayout)findViewById(R.id.main_bottom_user);
        alarm=(ImageView)findViewById(R.id.header_front_1);
        setting=(ImageView)findViewById(R.id.header_back_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        assetManager=getAssets();
        fragmentManager=getSupportFragmentManager();
        /*响应式事件添加*/
        homeRelativeLayout.setOnClickListener(this);
        orderRelativeLayout.setOnClickListener(this);
        userRelativeLayout.setOnClickListener(this);
        header_back_1.setOnClickListener(this);
        /*初始化碎片*/
        setTopFragment(0);
        setTitleName(0);
        setTypeFace();
        setUserInfo();
    }
    /**
     *
     */
    private void setTypeFace(){
        Typeface tf= Typeface.createFromAsset(assetManager, "fonts/msyh.ttf");
        userTV.setTypeface(tf);
        orderTV.setTypeface(tf);
        homeTV.setTypeface(tf);
    }
    /**
     * 设置主页面顶部的标题栏
     * @param index 底部button的序号
     */
    private void setTitleName(int index){
        switch (index){
            case 0:
                alarm.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                title_name.setText(R.string.title_home);
                break;
            case 1:
                alarm.setVisibility(View.GONE);
                setting.setVisibility(View.GONE);
                title_name.setText(R.string.title_order);
                break;
            case 2:
                alarm.setVisibility(View.VISIBLE);
                setting.setVisibility(View.VISIBLE);
                title_name.setText(R.string.title_user);
                break;
            default:
                break;
        }
    }
    /**
     *
     * @param v 触发click的view
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.main_bottom_home:
                setTopFragment(0);
                break;
            case R.id.main_bottom_order:
                setTopFragment(1);
                break;
            case R.id.main_bottom_user:
                setTopFragment(2);
                break;
            case R.id.header_back_1:
                SettingActivity.actionStart(this);
                break;
            default:
                break;
        }
    }
    /**
     *设置显示在顶部的碎片
     * @param index 底部button的序号
     */
    private void setTopFragment(int index){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        clearButtonActive();
        clearFragments(fragmentTransaction);
        switch (index){
            case 0:
                if(homePageFragment==null) {
                    homePageFragment=new HomePageFragment();
                    fragmentTransaction.add(R.id.content, homePageFragment,HOMEPAGETAG);
                }
                else {
                    fragmentTransaction.show(homePageFragment);
                }
                setButtonActive(0);
                setTitleName(0);
                break;
            case 1:
                if(orderFragment==null) {
                    orderFragment=new OrderFragment();
                    fragmentTransaction.add(R.id.content, orderFragment,ORDERTAG);
                }
                else
                    fragmentTransaction.show(orderFragment);
                setButtonActive(1);
                setTitleName(1);
                break;
            case 2:
                if(userFragment==null) {
                    userFragment=new UserFragment();
                    fragmentTransaction.add(R.id.content, userFragment,USERTAG);
                }
                else
                    fragmentTransaction.show(userFragment);
                setButtonActive(2);
                setTitleName(2);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
    /**
     * 激活主页底部的button
     * @param index 底部button的序号
     */
    private void setButtonActive(int index){
        int font_theme=ContextCompat.getColor(MainActivity.this,R.color.theme);
        switch (index){
            case 0:
                homeTV.setTextColor(font_theme);
                homeIV.setImageResource(R.drawable.homepage_active);
                break;
            case 1:
                orderTV.setTextColor(font_theme);
                orderIV.setImageResource(R.drawable.order_active);
                break;
            case 2:
                userTV.setTextColor(font_theme);
                userIV.setImageResource(R.drawable.user_active);
                break;
            default:
                break;
        }
    }
    /**
     * 清除主页底边button的激活状态
     */
    private void clearButtonActive(){
        int font_standard_color=ContextCompat.getColor(MainActivity.this,R.color.font_standard);
        homeTV.setTextColor(font_standard_color);
        homeIV.setImageResource(R.drawable.homepage_inactive);
        orderTV.setTextColor(font_standard_color);
        orderIV.setImageResource(R.drawable.order_inactive);
        userTV.setTextColor(font_standard_color);
        userIV.setImageResource(R.drawable.user_inactive);
    }
    /**
     *清空主页的碎片激活状态
     * @param fragmentTransaction 调用该函数的函数所声明的碎片事务
     */
    private void clearFragments(FragmentTransaction fragmentTransaction){
        if(homePageFragment!=null)
            fragmentTransaction.hide(homePageFragment);
        if(orderFragment!=null)
            fragmentTransaction.hide(orderFragment);
        if(userFragment!=null)
            fragmentTransaction.hide(userFragment);
    }
}
