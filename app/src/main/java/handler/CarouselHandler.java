package handler;

import collector.BaseActivity;
import fragment.HomePageFragment;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by dell2 on 2017/5/21.
 */

public class CarouselHandler extends Handler {
    public static final int MSG_UPDATE_CAROUSEL=1;/*请求更新显示轮播图*/
    public static final int MSG_KEEP_CAROUSEL=2;/*请求暂停轮播图*/
    public static final int MSG_AGAIN_CAROUSEL=3;/*请求恢复轮播*/
    public static final int MSG_PAGE_CHANGED=4;/*记录最新的页号*/
    public static final long MSG_DELAY=3000;/*轮播时间*/

    private WeakReference<HomePageFragment> weakReference;
    private int currentItem=0;

    public CarouselHandler(WeakReference<HomePageFragment> weakReference){
        this.weakReference=weakReference;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        HomePageFragment homePageFragment=weakReference.get();
        if(homePageFragment==null){
            return;
        }
        if(homePageFragment.getCarouselHandler().hasMessages(MSG_UPDATE_CAROUSEL)){
            homePageFragment.getCarouselHandler().removeMessages(MSG_UPDATE_CAROUSEL);
        }
        switch (msg.what){
            case MSG_UPDATE_CAROUSEL:
                currentItem++;
                homePageFragment.getHomepage_carousel().setCurrentItem(currentItem);
                homePageFragment.getCarouselHandler().sendEmptyMessageDelayed(MSG_UPDATE_CAROUSEL,MSG_DELAY);
                break;
            case MSG_KEEP_CAROUSEL:
                break;
            case MSG_AGAIN_CAROUSEL:
                homePageFragment.getCarouselHandler().sendEmptyMessageDelayed(MSG_UPDATE_CAROUSEL,MSG_DELAY);
                break;
            case MSG_PAGE_CHANGED:
                currentItem=msg.arg1;
            default:
                break;
        }
    }
}