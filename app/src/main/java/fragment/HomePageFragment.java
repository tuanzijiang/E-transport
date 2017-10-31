package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell2.e_transport.R;
import com.example.dell2.e_transport.Want2OrderActivity;
import com.example.dell2.e_transport.Want2ReceiveActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import adapt.CarouselPagerAdapt;
import application.E_Trans_Application;
import handler.CarouselHandler;

/**
 * Created by dell2 on 2017/5/20.
 */

public class HomePageFragment extends Fragment implements View.OnClickListener{
    private  View view;
    private CarouselHandler carouselHandler=new CarouselHandler(new WeakReference<HomePageFragment>(this));
    private LayoutInflater inflate;
    private ViewPager homepage_carousel;
    private LinearLayout want2order_button;
    private LinearLayout want2receive_button;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        this.inflate=inflater;
        view=inflater.inflate(R.layout.homepage,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCarousel();
        init();
    }
    public void init(){
        want2order_button=(LinearLayout)getView().findViewById(R.id.want2order_button);
        want2receive_button=(LinearLayout)getView().findViewById(R.id.want2receive_button);
        want2order_button.setOnClickListener(this);
        want2receive_button.setOnClickListener(this);
    }
    /**
     * 初始化轮播图
     */
    private void setCarousel(){
        homepage_carousel=(ViewPager)getView().findViewById(R.id.homepage_carousel);
        View view1=inflate.inflate(R.layout.carousel_1,null);
        View view2=inflate.inflate(R.layout.carousel_2,null);
        View view3=inflate.inflate(R.layout.carousel_3,null);
        View view4=inflate.inflate(R.layout.carousel_4,null);
        ArrayList<View> viewList=new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        homepage_carousel.setAdapter(new CarouselPagerAdapt(viewList));
        homepage_carousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                carouselHandler.sendMessage(Message.obtain(carouselHandler,carouselHandler.MSG_PAGE_CHANGED,position,0));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        carouselHandler.sendEmptyMessage(CarouselHandler.MSG_KEEP_CAROUSEL);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        carouselHandler.sendEmptyMessageDelayed(CarouselHandler.MSG_UPDATE_CAROUSEL,CarouselHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        carouselHandler.sendEmptyMessageDelayed(CarouselHandler.MSG_UPDATE_CAROUSEL,CarouselHandler.MSG_DELAY);
    }
    /**
     * 获取私有元素的函数
     * */
    public CarouselHandler getCarouselHandler(){
        return carouselHandler;
    }
    public ViewPager getHomepage_carousel(){
        return homepage_carousel;
    }
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.want2order_button:
                if(((E_Trans_Application)getActivity().getApplication()).getLoginState()==0){
                    Toast.makeText(getContext(),"请您登陆",Toast.LENGTH_SHORT).show();
                }
                else {
                    Want2OrderActivity.actionStart(this.getActivity());
                }
                break;
            case R.id.want2receive_button:
                if(((E_Trans_Application)getActivity().getApplication()).getLoginState()==0){
                    Toast.makeText(getContext(),"请您登陆",Toast.LENGTH_SHORT).show();
                }
                else {
                    intent = new Intent(getActivity(), Want2ReceiveActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
