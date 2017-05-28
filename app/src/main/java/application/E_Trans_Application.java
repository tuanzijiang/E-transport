package application;

import android.app.Application;

import entity.User;

/**
 * Created by dell2 on 2017/5/24.
 */

public class E_Trans_Application extends Application {
    /**
     * int loginState----------------登录状态：0-未登录，1-登录
     */
    private int loginState;
    private User user;
    public void setLoginState(int loginState){
        this.loginState=loginState;
    }
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return this.user;
    }
    public int getLoginState(){
        return this.loginState;
    }
    /*退出操作*/
    public void exit(){
        loginState=0;
        user=new User();
    }
}
