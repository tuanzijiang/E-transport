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
    private String district;
    private int ID;

    public Location(){
        /*测试数据*/
       /* district="上海 普陀区";
        address="华东师范大学(中山北路校区) 3663号 5舍432B";
        userName="章琪";
        gender=0;
        tel="15317315332";
        ID=-1;*/
        /*测试数据*/
        district= null;
        address=null;
        userName=null;
        gender=0;
        tel=null;
        ID=-1;
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
    public void setDistrict(String district){this.district=district;}
    public void setID(int id){
        this.ID=id;
    }

    public String getAddress(){return this.address;}
    public String getUserName(){return this.userName;}
    public String getTel(){return this.tel;}
    public int getGender(){ return this.gender;}
    public String getDistrict(){ return this.district;}
    public int getID(){
        return this.ID;
    }

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
