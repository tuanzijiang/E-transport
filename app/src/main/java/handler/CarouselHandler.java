package handler;

import android.os.Handler;

/**
 * Created by dell2 on 2017/5/21.
 */

public class CarouselHandler extends Handler {
    protected static final int MSG_UPDATE_CAROUSEL=0;/*请求更新显示轮播图*/
    protected static final int MSG_KEEP_CAROUSEL=1;/*请求暂停轮播图*/
    protected static final int MSG_AGAIN_CAROUSEL=2;/*请求恢复轮播*/
    protected static final int MSG_PAGE_CHANGED=4;/*记录最新的页号*/
    protected static final long MSG_DELAY=3000;/*轮播时间*/

}