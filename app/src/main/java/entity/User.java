package entity;

/**
 * Created by dell2 on 2017/5/24.
 */

public class User {
    /**
     * int userGender --------------用户性别，0--男，1--女
     */
    private String userName;
    private String userTel;
    private String userAddress;
    private String userPwLogin;
    private String userPwCover;
    private int userGender;
    public User(){
        userGender=2;
    }
    public void setUserTel(String userTel){
        this.userTel=userTel;
    }
    public void setUserGender(int userGender){
        this.userGender=userGender;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public void setUserAddress(String userAddress){
        this.userAddress=userAddress;
    }
    public void setUserPwLogin(String userPwLogin){
        this.userPwLogin=userPwLogin;
    }
    public void setUserPwCover(String userPwCover){
        this.userPwCover=userPwCover;
    }
    public String getUserPwCover(){
        return userPwCover;
    }
    public String getUserPwLogin(){
        return userPwLogin;
    }
    public String getUserName(){
        return this.userName;
    }
    public int getUserGender(){
        return this.userGender;
    }
    public String getUserTel(){
        return this.userTel;
    }
    public String getUserAddress(){
        return this.userAddress;
    }

    public String getUserGenderString(){
        String result;
        switch (this.userGender){
            case 0:
                result="男";
                break;
            case 1:
                result="女";
                break;
            default:
                result="";
                break;
        }
        return result;
    }
}
