package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.dell2.e_transport.MessageLoginActivity;
import com.example.dell2.e_transport.R;

/**
 * Created by dell2 on 2017/5/20.
 */


public class UserFragment extends Fragment implements View.OnClickListener{
    private LinearLayout user_login_line;
    private TextView userName;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.user, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    public void init(){
        user_login_line=(LinearLayout)getView().findViewById(R.id.user_login_line);
        userName=(TextView)getView().findViewById(R.id.userName);
        user_login_line.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.user_login_line:
                MessageLoginActivity.actionStart(getActivity());
                break;
            default:
                break;
        }
    }
    public void setLogin(String userName){
        this.userName.setText(userName);
    }
}
