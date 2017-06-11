package handler;

import android.os.Handler;
import android.os.Message;

import com.example.dell2.e_transport.MessageLoginActivity;
import com.example.dell2.e_transport.UserNewPhoneActivity;
import com.example.dell2.e_transport.UserPhoneActivity;

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
        String textOuter;
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
                        mla.setVerify_button_bg(0);
                        break;
                    case STOP:
                        second=60;
                        textOuter=RESET_INFO;
                        mla.getVerify_button().setText(textOuter);
                        mla.setVerify_button_bg(1);
                        break;
                    default:
                        break;
                }
                break;
            case "UserPhoneActivity":
                UserPhoneActivity upa=(UserPhoneActivity)wr.get();
                if(upa==null){
                    return;
                }
                if(upa.getCdh().hasMessages(DOING))
                    upa.getCdh().removeMessages(DOING);
                switch (msg.what){
                    case DOING:
                        second--;
                        if(second!=0)
                            upa.getCdh().sendEmptyMessageDelayed(DOING,1000);
                        else{
                            second=60;
                            upa.getCdh().sendEmptyMessageDelayed(STOP,1000);
                        }
                        textOuter=String.valueOf(second)+DOING_INFO;
                        upa.getVerify_button().setText(textOuter);
                        break;
                    case START:
                        upa.getCdh().sendEmptyMessageDelayed(DOING,1000);
                        textOuter=String.valueOf(second)+DOING_INFO;
                        upa.getVerify_button().setText(textOuter);
                        upa.setVerify_button_bg(0);
                        break;
                    case STOP:
                        second=60;
                        textOuter=RESET_INFO;
                        upa.getVerify_button().setText(textOuter);
                        upa.setVerify_button_bg(1);
                        break;
                    default:
                        break;
                }
                break;
            case "UserNewPhoneActivity":
                UserNewPhoneActivity unpa=(UserNewPhoneActivity)wr.get();
                if(unpa==null){
                    return;
                }
                if(unpa.getCdh().hasMessages(DOING))
                    unpa.getCdh().removeMessages(DOING);
                switch (msg.what){
                    case DOING:
                        second--;
                        if(second!=0)
                            unpa.getCdh().sendEmptyMessageDelayed(DOING,1000);
                        else{
                            second=60;
                            unpa.getCdh().sendEmptyMessageDelayed(STOP,1000);
                        }
                        textOuter=String.valueOf(second)+DOING_INFO;
                        unpa.getVerify_button().setText(textOuter);
                        break;
                    case START:
                        unpa.getCdh().sendEmptyMessageDelayed(DOING,1000);
                        textOuter=String.valueOf(second)+DOING_INFO;
                        unpa.getVerify_button().setText(textOuter);
                        unpa.setVerify_button_bg(0);
                        break;
                    case STOP:
                        second=60;
                        textOuter=RESET_INFO;
                        unpa.getVerify_button().setText(textOuter);
                        unpa.setVerify_button_bg(1);
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
