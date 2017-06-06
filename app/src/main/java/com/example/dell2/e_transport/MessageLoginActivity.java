package com.example.dell2.e_transport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import application.E_Trans_Application;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import collector.BaseActivity;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.Constant;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.ResponseHandler;
import entity.User;
import handler.CountDownHandler;

/**
 * Created by wangyan on 2017/5/22.
 */

public class MessageLoginActivity extends BaseActivity implements View.OnClickListener{
    private Drawable background_theme;
    private Drawable background_gray;
    private EditText et_pw;
    private LinearLayout button_verify;
    public static final String BACK_STATE_NOLOGIN="NOLOGIN";
    public static final String BACK_STATE_LOGIN="LOGIN";
    private CountDownHandler cdh=new CountDownHandler(new WeakReference<BaseActivity>(this),"MessageLoginActivity");
    private EditText message_login_et;
    private ImageView header_front_1;
    private TextView header_back_1;
    private TextView verify_button;
    private E_Trans_Application app;
    private int verify_state;/*0--未开始计时 1--已开始计时*/

    private EventHandler eventHandler;
    private String strPhoneNumber;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_messagelogin);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
    /**
     * 启动本activity
     */
    public static void actionStart(Context context){
        Intent intent=new Intent(context,MessageLoginActivity.class);
        BaseActivity baseActivity=(BaseActivity)context;
        baseActivity.startActivityForResult(intent,1);
    }
    /**
     * 初始化
     */
    public void init(){
        message_login_et=(EditText)findViewById(R.id.message_login_et);
        header_front_1=(ImageView)findViewById(R.id.header_front_1);
        header_back_1=(TextView)findViewById(R.id.header_back_1);
        verify_button=(TextView)findViewById(R.id.verify_button);
        button_verify=(LinearLayout)findViewById(R.id.button_verify);
        et_pw=(EditText)findViewById(R.id.et_pw);
        background_gray= ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_no_click,null);
        background_theme=ResourcesCompat.getDrawable(getResources(),R.drawable.border_radius_theme,null);

        header_front_1.setOnClickListener(this);
        header_back_1.setOnClickListener(this);
        verify_button.setOnClickListener(this);
        message_login_et.setHorizontallyScrolling(true);
        button_verify.setOnClickListener(this);
        verify_state=0;


        SMSSDK.initSDK(this, "1e6ea2840b7c3", "67fbcafac5d69c190212cff1a8a02e37");

        eventHandler = new EventHandler() {

            /**
             * 在操作之后被触发
             *
             * @param event  参数1
             * @param result 参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败
             * @param data   事件操作的结果
             */

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);

    }
    @Override
    public void onClick(View view){
        Intent intent=new Intent();

        switch (view.getId()){
            case R.id.header_front_1:
                intent.putExtra("result",BACK_STATE_NOLOGIN);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.header_back_1:
                cdh.sendEmptyMessageDelayed(cdh.STOP,1000);
                PasswordLoginActivity.actionStart(this);
                break;
            case R.id.verify_button:
                if(verify_state==0){
                    strPhoneNumber = message_login_et.getText().toString();
                    SMSSDK.getVerificationCode("86", strPhoneNumber);
                    cdh.sendEmptyMessageDelayed(cdh.START,1000);
                }
                break;
            case R.id.button_verify:
                String verify=et_pw.getText().toString();
                if(verify!=null&&verify.length()==4) {
                    LoadingDialogUtil.showLoadingDialog(this);
                    SMSSDK.submitVerificationCode("86", strPhoneNumber, verify);
                }
            default:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        switch (requestCode){
            case 1:/*密码登录返还后的处理*/
                if(resultCode==RESULT_OK){
                    finish();
                }
        }
    }
    public CountDownHandler getCdh(){
        return cdh;
    }
    public TextView getVerify_button(){
        return verify_button;
    }
    public void setVerify_state(int state){
        this.verify_state=state;
    }
    public void loadUserInfo(User user){
        app=(E_Trans_Application)getApplication();
        app.setUser(user);
        app.setLoginState(1);
    }
    /*0--灰色，1--蓝色*/
    public void setVerify_button_bg(int bg){
        switch (bg){
            case 0:
                verify_button.setBackground(background_gray);
                break;
            default:
                verify_button.setBackground(background_theme);
                break;
        }
    }

    /**（待填）
     * 发送验证码
     * @return 验证码是否发送成功
     */
    public boolean sendVerify(){
        return true;
    }
    /**（待填）
     *判断验证法是否正确
     * @param verify
     * @return
     */
    public boolean checkVerify(String verify){
        return true;
    }
    /**（待填）
     * 登录，加载用户数据or注册，新的用户
     * （手机号码已经存在，就加载原有记录，手机号码不存在，创建新的记录）
     * @return 加载是否成功
     */
    public boolean login(){
        return true;
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e("phone", "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(MessageLoginActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d("phone", "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d("phone", "submit code successful");
                            LoadingDialogUtil.cancelLoading();
                            User user=new User();
                            CommonRequest request = new CommonRequest();

                            request.setRequestCode("phoneNumber");
                            request.addRequestParam("phoneNumber",strPhoneNumber);

                            HttpPostTask myTask = sendHttpPostRequest(Constant.LOGIN_URL, request, new ResponseHandler() {
                                @Override
                                public CommonResponse success(CommonResponse response) {
                                    Log.e("S","SSS");
                                    LoadingDialogUtil.cancelLoading();
                                    User user=new User();
                                    //user信息更改
                                    user.setUserName(response.getPropertyMap().get("IDName"));
                                    user.setUserAddress(response.getPropertyMap().get("location"));
                                    Log.d("GE",response.getPropertyMap().get("gender")+" ");
                                    user.setUserGender(response.getPropertyMap().get("gender").equals("female")?1:0);
                                    user.setUserTel(response.getPropertyMap().get("phoneNumber"));
                                    user.setCoverPw(response.getPropertyMap().get("payPassword"));
                                    user.setLoginPw(response.getPropertyMap().get("password"));
                                    Toast.makeText(MessageLoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                                    loadUserInfo(user);
                                    //intent.putExtra("result",BACK_STATE_LOGIN);
                                    //setResult(RESULT_OK,intent);
                                    finish();

                                    return response;
                                }

                                @Override
                                public CommonResponse fail(String failCode, String failMsg) {
                                    LoadingDialogUtil.cancelLoading();
                                    return null;
                                }
                            },true);

                        } else {
                            LoadingDialogUtil.cancelLoading();
                            Toast.makeText(MessageLoginActivity.this, "请重新验证", Toast.LENGTH_SHORT).show();
                            Log.d("phone", data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            Log.e("phone", "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(MessageLoginActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };
}
