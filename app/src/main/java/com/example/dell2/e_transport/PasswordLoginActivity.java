package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import application.E_Trans_Application;
import collector.BaseActivity;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.ResponseHandler;
import entity.User;

/**
 * Created by dell2 on 2017/5/23.
 */

public class PasswordLoginActivity extends BaseActivity implements View.OnClickListener{
    public static final String BACK_STATE_NOLOGIN="NOLOGIN";
    public static final String BACK_STATE_LOGIN="LOGIN";

    //Login_Url
    public static final String LOGIN_URL = "http://118.89.191.184:8080/ETServer/ETHome/LoginServlet";

    private CommonResponse response;
    private TextView title_name;
    private ImageView header_front_1;
    private ImageView header_back_1;
    private EditText et_pw;
    private EditText account;
    private LinearLayout button_verify;
    private E_Trans_Application app;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_passwordlogin);
        init();
    }
    public void init(){
        title_name=(TextView)findViewById(R.id.title_name);
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(ImageView)findViewById(R.id.header_back_1);
        et_pw=(EditText)findViewById(R.id.et_pw);
        account=(EditText)findViewById(R.id.account);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        app=(E_Trans_Application)getApplication();
        button_verify.setOnClickListener(this);
        header_front_1.setOnClickListener(this);
        header_back_1.setVisibility(View.GONE);
        header_front_1.setImageResource(R.drawable.last_white);
        title_name.setText("密码登录");
    }
    public static void actionStart(Context context){
        Intent intent=new Intent(context,PasswordLoginActivity.class);
        MessageLoginActivity messageLoginActivity=(MessageLoginActivity)context;
        messageLoginActivity.startActivityForResult(intent,1);
    }
    public void onClick(View view){
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.header_front_1:
                intent.putExtra("result",BACK_STATE_NOLOGIN);
                setResult(RESULT_CANCELED,intent);
                finish();
                break;
            case R.id.button_verify:
                if(account==null||account.getText().toString().equals("")){
                    Toast.makeText(PasswordLoginActivity.this,"请输入邮箱、手机或者用户名",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(et_pw==null||et_pw.getText().toString().equals("")){
                    Toast.makeText(PasswordLoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(login(account.getText().toString(),et_pw.getText().toString())){
                    User user=new User();
                    //user信息更改
                    //user.setUserName(response.getPropertyMap().get("IDName"));
                    //Log.d("ID",user.getUserName());
                    loadUserInfo(user);
                    intent.putExtra("result",BACK_STATE_LOGIN);
                    setResult(RESULT_OK,intent);
                    //finish();
                }
                else{
                    Toast.makeText(PasswordLoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    public void loadUserInfo(User user){
        app=(E_Trans_Application)getApplication();
        app.setUser(user);
        app.setLoginState(1);
    }
    /**
     * 加载
     */
    public void initInfo(){

    }
    /**
     * 登录函数，加载数据等
     * @param account
     * @param password
     * @return
     */
    public boolean login(String account,String password){
        Log.d("LOGIN",account + "::" +password);
        CommonRequest request = new CommonRequest();
        request.addRequestParam("name",account);
        request.addRequestParam("password",password);
        HttpPostTask myTask = sendHttpPostRequest(LOGIN_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(CommonResponse response) {
                Log.e("S","SSS");
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(PasswordLoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();

                return response;
            }

            @Override
            public CommonResponse fail(String failCode, String failMsg) {
                Log.e("F","FFF");
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(PasswordLoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();

                return null;
            }
        },true);
        //response=myTask.getFinalresponse();
        return true;
    }

}
