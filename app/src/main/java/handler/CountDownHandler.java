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
    public static final int DOING=1;
    public static final int START=2;
    public static final int STOP=3;
    private static final String DOING_INFO="s后重发";
    private static final String RESET_INFO="获取验证码";
    private int init_second=60;
    private int second;
    private WeakReference<BaseActivity> wr;
    private String activityKinds;
    public CountDownHandler(WeakReference<BaseActivity> wr,String activityKinds,int init_second){
        this.wr=wr;
        this.activityKinds=activityKinds;
        this.init_second=init_second;
        this.second=init_second;
    }
    public CountDownHandler(WeakReference<BaseActivity> wr,String activityKinds){
        this.wr=wr;
        this.activityKinds=activityKinds;
        this.init_second=60;
        this.second=60;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (activityKinds){
            case "MessageLoginActivity":
                MessageLoginActivity mla=(MessageLoginActivity)wr.get();
                String textOuter;
                if(mla==null){
                    return;
                }
                if(mla.getCdh().hasMessages(DOING))
                    mla.getCdh().removeMessages(DOING);

                switch (msg.what){
                    case DOING:
                        second--;
                        if(second!=0)
                            mla.getCdh().sendEmptyMessageDelayed(DOING,1000);
                        else{
                            second=60;
                            mla.getCdh().sendEmptyMessageDelayed(STOP,1000);
                        }
                        textOuter=String.valueOf(second)+DOING_INFO;
                        mla.getVerify_button().setText(textOuter);
                        break;
                    case START:
                        mla.getCdh().sendEmptyMessageDelayed(DOING,1000);
                        textOuter=String.valueOf(second)+DOING_INFO;
                        mla.getVerify_button().setText(textOuter);
                        break;
                    case STOP:
                        textOuter=RESET_INFO;
                        mla.getVerify_button().setText(textOuter);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
