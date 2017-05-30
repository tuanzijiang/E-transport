package entity;

import java.io.Serializable;

/**
 * Created by dell2 on 2017/5/30.
 */

public class Location implements Serializable {
    private String address;
    private String userName;
    private int gender;
    private String tel;

    public void setAddress(String address){
        this.address=address;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public void setTel(String tel){
        this.tel=tel;
    }
    public void setGender(int gender){this.gender=gender;}

    public String getAddress(){return this.address;}
    public String getUserName(){return this.userName;}
    public String getTel(){return this.tel;}
    public int getGender(){ return this.gender;}
}
