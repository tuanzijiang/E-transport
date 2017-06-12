package collector;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import collector.AppCompatActivityCollector;
/**
 * Created by dell2 on 2017/5/20.
 */

public class BaseActivity extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Log.d("ActivityName",getClass().getSimpleName());
        AppCompatActivityCollector.addActivity(this);
    }
    protected void onDestroy(){
        super.onDestroy();
        AppCompatActivityCollector.removeActivity(this);
    }
    protected HttpPostTask sendHttpPostRequest(String url, CommonRequest request, ResponseHandler responseHandler, boolean showLoadingDialog) {
        HttpPostTask temp = new HttpPostTask(request, mHandler, responseHandler);
        temp.execute(url);
        if(showLoadingDialog) {
            Log.d("Lo","Loading");
            Toast.makeText(BaseActivity.this,"Loading", Toast.LENGTH_SHORT);
            LoadingDialogUtil.showLoadingDialog(BaseActivity.this);
        }
        //Log.d("TESTkkk",temp.getFinalresponse().getPropertyMap().get("IDName"));
        return temp;
    }

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == Constant.HANDLER_HTTP_SEND_FAIL) {
                Log.d("SEND_FAIL",msg.obj.toString());
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(BaseActivity.this, "请求发送失败，请重试", Toast.LENGTH_SHORT).show();
            } else if (msg.what == Constant.HANDLER_HTTP_RECEIVE_FAIL) {
                Log.d("RECEIVE_FAIL",msg.obj.toString());

                LoadingDialogUtil.cancelLoading();
                Toast.makeText(BaseActivity.this, "请求接受失败，请重试", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
