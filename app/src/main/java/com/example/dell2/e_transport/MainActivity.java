package com.example.dell2.e_transport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import collector.BaseActivity;
import android.util.Log;
import android.view.Window;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
    }

}
