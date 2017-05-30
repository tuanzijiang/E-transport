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

    public Location(){
        /*测试数据*/
        address="华东师范大学(中山北路校区) 3663号 5舍432B";
        userName="章琪";
        gender=0;
        tel="15317315332";
        /*测试数据*/
    }

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

    public String getGenderString(){
        switch (gender){
            case 0:
                return "男";
            case 1:
                return "女";
            default:
                return "未知";
        }
    }
}
