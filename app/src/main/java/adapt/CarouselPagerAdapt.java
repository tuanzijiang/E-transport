package adapt;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

/**
 * Created by dell2 on 2017/5/21.
 */

public class CarouselPagerAdapt extends PagerAdapter {
    private ArrayList<View> viewList;
    public CarouselPagerAdapt(ArrayList<View> viewList){
        this.viewList=viewList;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position%=viewList.size();
        if(position<0){
            position+=viewList.size();
        }
        View view=viewList.get(position);

        ViewParent vp=view.getParent();
        if(vp!=null){
            ViewGroup vg=(ViewGroup) vp;
            vg.removeView(view);
        }
        container.addView(view);
        //这里可以增加时间监听
        return view;
    }
}
