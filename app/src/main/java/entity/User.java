package entity;

/**
 * Created by dell2 on 2017/5/24.
 */

public class User {
    /**
     * int userGender --------------用户性别，0--男，1--女,2-未设定
     * int isAvoidCover ------------免密支付,0--否，1--是，2-未设定
     */
    private String userName;
    private String userTel;
    private String userEmail;
    private String loginPw;
    private String coverPw;
    private String userAddress;
    private String userPwLogin;
    private String userPwCover;
    private int userGender;
    private int isAvoidCover;
    public User(){
        isAvoidCover=2;
        userGender=2;
    }
    public void setIsAvoidCover(int isAvoidCover){
        this.isAvoidCover=isAvoidCover;
    }
    public void setUserEmail(String userEmail){this.userEmail=userEmail;}
    public void setLoginPw(String loginPw){this.userPwLogin=loginPw;}
    public void setCoverPw(String coverPw){this.userPwCover=coverPw;}
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
    public int getIsAvoidCover(){
        return this.isAvoidCover;
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
    public String getUserEmail(){ return this.userEmail; }
    public String getLoginPw(){ return this.loginPw; }
    public String getCoverPw(){ return this.coverPw; }

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
