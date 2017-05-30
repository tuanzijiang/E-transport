package com.example.dell2.e_transport;

import collector.BaseActivity;
import entity.Location;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell2 on 2017/5/30.
 */

public class ChangeAddressActivity extends BaseActivity implements View.OnClickListener {
    private Location location;
    private ImageView header_front_1;
    private ImageView header_back_1;
    private TextView title_name;
    private Drawable blue;
    private Drawable gray;
    private TextView male;
    private TextView female;
    private EditText address_detail;
    private int gender=0;
    private LinearLayout button_verify;
    private EditText name;
    private EditText tel;
    private TextView address;
    private TextView tel_book;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_changeaddress);
        init();
    }
    public void init(){
        header_front_1=(ImageView) findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        title_name=(TextView)findViewById(R.id.title_name);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        name=(EditText)findViewById(R.id.name);
        tel=(EditText)findViewById(R.id.tel);
        tel_book=(TextView)findViewById(R.id.tel_book);
        address=(TextView)findViewById(R.id.address);
        blue= ResourcesCompat.getDrawable(getResources(),R.drawable.border_round_theme,null);
        gray=ResourcesCompat.getDrawable(getResources(),R.drawable.border_round_no_click,null);
        male=(TextView)findViewById(R.id.male);
        female=(TextView)findViewById(R.id.female);
        address_detail=(EditText)findViewById(R.id.address_detail);
        address_detail.setHorizontallyScrolling(true);
        /*响应时间*/
        header_front_1.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        button_verify.setOnClickListener(this);
        address.setOnClickListener(this);
        /*标题栏*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setVisibility(View.GONE);

        Intent intent=this.getIntent();
        String kinds=intent.getExtras().getString("kind");
        if(kinds!=null&&kinds.equals("add")){
            title_name.setText("新增地址");
        }
        else{
            title_name.setText("修改地址");
            location=(Location)intent.getSerializableExtra("locationItem");
            initInfo();
        }
    }
    public void initInfo(){
        name.setText(location.getUserName());
        tel.setText(location.getTel());
        address.setText(location.getDistrict());
        address_detail.setText(location.getAddress());
    }
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.header_front_1:
                finish();
                break;
            case R.id.male:
                gender=0;
                male.setBackground(blue);
                female.setBackground(gray);
                break;
            case R.id.female:
                gender=1;
                male.setBackground(gray);
                female.setBackground(blue);
            case R.id.button_verify:
                if(addAddress()){
                    finish();
                }
                break;
            case R.id.address:
                intent=new Intent(ChangeAddressActivity.this,UserAreaAvtivity.class);
                intent.putExtra("kind","location");
                setResult(RESULT_OK,intent);
                startActivityForResult(intent,1);
                break;
            default:
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            address.setText(data.getStringExtra("address"));
        }
    }
    /**
     * 添加地址到数据库
     * @return 添加是否成功
     */
    public boolean addAddress(){
        String address_name=name.getText().toString();
        String address_tel=tel.getText().toString();
        String address_gender=String.valueOf(gender);
        String address_address=address_detail.getText().toString();
        String address_district=address_detail.getText().toString();
        return true;
    }
}
