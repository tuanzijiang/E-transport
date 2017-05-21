package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;

import com.example.dell2.e_transport.R;

import java.util.ArrayList;

import adapt.CarouselPagerAdapt;

/**
 * Created by dell2 on 2017/5/20.
 */

public class HomePageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.homepage,container,false);
//        ViewPager homepage_carousel=(ViewPager)findViewById();
        View view1=inflater.inflate(R.layout.carousel_1,null);
        View view2=inflater.inflate(R.layout.carousel_2,null);
        View view3=inflater.inflate(R.layout.carousel_3,null);
        View view4=inflater.inflate(R.layout.carousel_4,null);
        ArrayList<View> viewList=new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
//        homepage_carousel.setAdapter(new CarouselPagerAdapt(viewList));
        return view;
    }
}
