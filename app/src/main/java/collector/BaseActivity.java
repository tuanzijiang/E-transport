package collector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
}
