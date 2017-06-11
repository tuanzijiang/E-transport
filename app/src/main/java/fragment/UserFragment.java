package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.dell2.e_transport.MessageLoginActivity;
import com.example.dell2.e_transport.R;
import com.example.dell2.e_transport.UsualLocationActivity;

import application.E_Trans_Application;

/**
 * Created by dell2 on 2017/5/20.
 */


public class UserFragment extends Fragment implements View.OnClickListener{
    private LinearLayout user_login_line;
    private LinearLayout ll_location;
    private TextView userName;
    private E_Trans_Application app;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.user, container, false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        init();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    public void init(){
        /*实例化*/
        user_login_line=(LinearLayout)getView().findViewById(R.id.user_login_line);
        ll_location=(LinearLayout)getView().findViewById(R.id.ll_location);
        userName=(TextView)getView().findViewById(R.id.userName);
        app=(E_Trans_Application)(getActivity().getApplication());
        /*响应事件*/
        user_login_line.setOnClickListener(this);
        ll_location.setOnClickListener(this);
        /*初始化信息*/
        initInfo();
    }
    public void initInfo(){
        if(app.getLoginState()==1)
            if(app.getUser().getUserName()==null||app.getUser().getUserName().equals("")) {
                userName.setText("货急送");
            }
            else {
                userName.setText(app.getUser().getUserName());
            }
        else
            userName.setText("未登录");
    }
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.user_login_line:
                if(app.getLoginState()==0){
                    MessageLoginActivity.actionStart(getActivity());
                }
                break;
            case R.id.ll_location:
                intent=new Intent(getActivity(), UsualLocationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void setLogin(String userName){
        this.userName.setText(userName);
    }
}
