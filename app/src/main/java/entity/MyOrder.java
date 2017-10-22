package entity;

import com.example.dell2.e_transport.R;

import java.io.Serializable;

/**
 * Created by dell2 on 2017/5/24.
 */

public class MyOrder implements Serializable{
    /**
     * String orderId---------------订单的主键，唯一确定订单
     * String sendAddress--------发出地
     * String receiveAddress-----接收地
     * int orderState------------订单的状态：0-未接单，1-进行中，2-已接单
     * int orderSend-------------订单的发起状态：0-已接到的订单，1-已发起的订单
     * float price---------------订单的价格
     * User launchMan------------发起人信息
     * User postMan--------------送货人信息
     * User sendMan--------------寄出人信息
     * User receiveMan-----------收件人信息
     * String coverKind----------支付方式
     * String time---------------送达时间段
     * String orderTime----------下单时间
     * String goodsKind----------物品种类
     * String goodsWeight--------物品重量
     * String goodsInfo----------物品备注
     * String goodsPic-----------物品照片
     */
    private String orderId;
    private String sendAddress;
    private String receiveAddress;
    private int orderState;
    private int orderSend;
    private float price;
    private String time;
    private String postMan;
    private String launchMan;
    private String postManTel;
    private String launchManTel;
    private int postManGender;
    private int launchManGender;
    private String sendUserName;
    private String sendUserTel;
    private String receiveUserName;
    private String receiverUserTel;
    private String goodsKind;
    private String coverKind;
    private String orderTime;
    private String goodsWeight;
    private String goodsInfo;
    private String goodsPic;
    private float longitude;
    private float latitude;
    public MyOrder(){
        /*测试用的数据*/
        orderId="123456789";
        sendAddress="上海市普陀区";
        receiveAddress="上海市静安区";
        orderSend=1;
        orderState=0;
        price=(float)20.5;
        time="14:30-15:30";
        goodsKind="图书";
        coverKind="在线支付";
        orderTime="2016-5-13 19:30";
        goodsWeight="3kg";
        goodsInfo="贵重易碎物品";
        sendUserName="章琪";
        sendUserTel="123456";
        receiveUserName="章琪";
        receiverUserTel="456798";
        launchMan="";
        launchManGender=0;
        launchManTel="";
        postMan="";
        postManGender=0;
        postManTel="";
        /*测试用的数据*/
    }
    public MyOrder(String orderId,String sendAddress,String receiveAddress,int orderState,int orderSend,float price){
        this.orderId=orderId;
        this.sendAddress=sendAddress;
        this.receiveAddress=receiveAddress;
        this.orderState=orderState;
        this.orderSend=orderSend;
        this.price=price;
    }

    public void setSendUserName(String sendUserName){this.sendUserName=sendUserName;}
    public void setSendUserTel(String sendUserTel){this.sendUserTel=sendUserTel;}
    public void setReceiveUserName(String receiveUserName){this.receiveUserName=receiveUserName;}
    public void setReceiverUserTel(String receiverUserTel){this.receiverUserTel=receiverUserTel;}
    public void setLaunchMan(String launchMan){
        this.launchMan=launchMan;
    }
    public void setGoodsPic(String goodsPic){
        this.goodsPic=goodsPic;
    }
    public void setGoodsInfo(String goodsInfo){
        this.goodsInfo=goodsInfo;
    }
    public void setGoodsWeight(String goodsWeight){
        this.goodsWeight=goodsWeight;
    }
    public void setOrderTime(String orderTime){
        this.orderTime=orderTime;
    }
    public void setCoverKind(String coverKind){
        this.coverKind=coverKind;
    }
    public void setGoodsKind(String goodsKind){
        this.goodsKind=goodsKind;
    }
    public void setPostMan(String postMan){ this.postMan=postMan;}
    public void setLaunchManTel(String launchManTel){
        this.launchManTel=launchManTel;
    }
    public void setPostManTel(String postManTel){ this.postManTel=postManTel;}
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
    public void setSendAddress(String sendAddress){
        this.sendAddress=sendAddress;
    }
    public void setReceiveAddress(String receiveAddress){this.receiveAddress=receiveAddress;}
    public void setOrderState(int orderState){this.orderState=orderState;}
    public void setOrderSend(int orderSend){this.orderSend=orderSend;}
    public void setPrice(float price){this.price=price;}
    public void setTime(String time){
        this.time=time;
    }

    public String getOrderSendString(){
        String result="";
        switch (this.orderSend){
            case 0:
                result="已接到的订单:";
                break;
            case 1:
                result="已发出的订单:";
                break;
            default:
                result="";
                break;
        }
        return result;
    }
    public String getOrderStateString(){
        String result="";
        switch (this.orderState){
            case 0:
                result="未接单";
                break;
            case 1:
                result="进行中";
                break;
            case 2:
                result="已完成";
                break;
            default:
                result="";
                break;
        }
        return result;
    }
    public String getPriceString(){
        String result=String.valueOf(price);
        return result;
    }
    public int getKindImageString(){
        int result;
        result= R.drawable.cloth;
        return result;
    }
    public String getorderID(){
        return orderId;
    }
    public String getSendAddress(){
        return sendAddress;
    }
    public String getReceiveAddress(){
        return receiveAddress;
    }

    public String getTime(){ return time;}
    public String getOrderId(){
        return orderId;
    }
    public String getGoodsKind(){ return goodsKind;}
    public String getCoverKind(){ return coverKind;}
    public String getOrderTime(){ return orderTime;}
    public String getGoodsWeight(){ return goodsWeight;}
    public String getGoodsInfo(){ return goodsInfo;}
    public String getGoodsPic(){ return goodsPic;}
    public int getOrderState(){ return orderState;}
    public int getOrderSend(){ return orderSend;}
    public String getLaunchMan(){
        return launchMan;
    }
    public String getPostMan(){ return postMan;}
    public String getPostManTel(){
        return postManTel;
    }
    public String getLaunchManTel(){
        return launchManTel;
    }
    public String getSendUserName(){ return sendUserName;}
    public String getSendUserTel(){ return sendUserTel;}
    public String getReceiveUserName(){return receiveUserName;}
    public String getReceiverUserTel(){ return receiverUserTel;}

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getPostManGender() {
        return postManGender;
    }

    public void setPostManGender(int postManGender) {
        this.postManGender = postManGender;
    }

    public int getLaunchManGender() {
        return launchManGender;
    }

    public void setLaunchManGender(int launchManGender) {
        this.launchManGender = launchManGender;
    }
}
