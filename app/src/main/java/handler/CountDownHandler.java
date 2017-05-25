package handler;

import android.os.Handler;
import android.os.Message;

import com.example.dell2.e_transport.MessageLoginActivity;

import java.lang.ref.WeakReference;

import collector.BaseActivity;

/**
 * Created by dell2 on 2017/5/25.
 */

public class CountDownHandler extends Handler {
    private static final int OVER=0;
    private static final int DOING=1;
    private static final int START=2;
    private int init_second=60;
    private WeakReference<BaseActivity> wr;
    private String activityKinds;
    public CountDownHandler(WeakReference<BaseActivity> wr,String activityKinds,int init_second){
        this.wr=wr;
        this.activityKinds=activityKinds;
        this.init_second=init_second;
    }
    public CountDownHandler(WeakReference<BaseActivity> wr,String activityKinds){
        this.wr=wr;
        this.activityKinds=activityKinds;
        this.init_second=60;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (activityKinds){
            case "MessageLoginActivity":
                MessageLoginActivity mla=(MessageLoginActivity)wr.get();
                if(mla==null){
                    return;
                }
                if(mla.getCdh().hasMessages(DOING))
                    mla.getCdh().removeMessages(DOING);
                switch (msg.what){
                    case DOING:

                }
                break;
            default:
                break;
        }
    }
}
