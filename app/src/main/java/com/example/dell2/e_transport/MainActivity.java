package com.example.dell2.e_transport;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;

import collector.BaseActivity;
import fragment.HomePageFragment;
import fragment.OrderFragment;
import fragment.UserFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private HomePageFragment homePageFragment;
    private OrderFragment orderFragment;
    private UserFragment userFragment;
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
     * 对页面进行初始化：加载响应事件、给私有属性链接实例、初始化碎片等
     */
    private void init(){
        /*私有属性实例化*/
        homeTV=(TextView)findViewById(R.id.main_bottom_home_text);
        homeIV=(ImageView)findViewById(R.id.main_bottom_home_image);
        orderTV=(TextView)findViewById(R.id.main_bottom_order_text);
        orderIV=(ImageView)findViewById(R.id.main_bottom_order_image);
        userTV=(TextView)findViewById(R.id.main_bottom_user_text);
        userIV=(ImageView)findViewById(R.id.main_bottom_user_image);
        homeRelativeLayout=(RelativeLayout)findViewById(R.id.main_bottom_home);
        orderRelativeLayout=(RelativeLayout)findViewById(R.id.main_bottom_order);
        userRelativeLayout=(RelativeLayout)findViewById(R.id.main_bottom_user);
        fragmentManager=getSupportFragmentManager();
        /*响应式事件添加*/
        homeRelativeLayout.setOnClickListener(this);
        orderRelativeLayout.setOnClickListener(this);
        userRelativeLayout.setOnClickListener(this);
        /*初始化碎片*/
        setTopFragment(0);
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
        clearFraments(fragmentTransaction);
        switch (index){
            case 0:
                if(homePageFragment==null) {
                    homePageFragment=new HomePageFragment();
                    fragmentTransaction.add(R.id.content, homePageFragment);
                }
                else
                    fragmentTransaction.show(homePageFragment);
                setButtonActive(0);
                break;
            case 1:
                if(orderFragment==null) {
                    orderFragment=new OrderFragment();
                    fragmentTransaction.add(R.id.content, orderFragment);
                }
                else
                    fragmentTransaction.show(orderFragment);
                setButtonActive(1);
                break;
            case 2:
                if(userFragment==null) {
                    userFragment=new UserFragment();
                    fragmentTransaction.add(R.id.content, userFragment);
                }
                else
                    fragmentTransaction.show(userFragment);
                setButtonActive(2);
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
    private void clearFraments(FragmentTransaction fragmentTransaction){
        if(homePageFragment!=null)
            fragmentTransaction.hide(homePageFragment);
        if(orderFragment!=null)
            fragmentTransaction.hide(orderFragment);
        if(userFragment!=null)
            fragmentTransaction.hide(userFragment);
    }
}
