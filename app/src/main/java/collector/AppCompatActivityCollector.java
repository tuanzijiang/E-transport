package collector;

import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by dell2 on 2017/5/20.
 */

public class AppCompatActivityCollector {
    public static List<AppCompatActivity> appCompatActivities=new ArrayList<AppCompatActivity>();
    public static void addActivity(AppCompatActivity appCompatActivity){
        appCompatActivities.add(appCompatActivity);
    }
    public static void removeActivity(AppCompatActivity appCompatActivity){
        appCompatActivities.remove(appCompatActivity);
    }
    public static void finishAll(){
        for(AppCompatActivity appCompatActivity:appCompatActivities){
            if(!appCompatActivity.isFinishing()){
                appCompatActivity.finish();
            }
        }
    }
}
