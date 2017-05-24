package application;

import android.app.Application;

import entity.User;

/**
 * Created by dell2 on 2017/5/24.
 */

public class E_Trans_Application extends Application {
    private String loginState;
    private User user;
    public void setLoginState(String loginState){
        this.loginState=loginState;
    }
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return this.user;
    }
    public String getLoginState(){
        return this.loginState;
    }
}
