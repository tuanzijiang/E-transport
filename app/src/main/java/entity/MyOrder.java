package entity;

/**
 * Created by dell2 on 2017/5/24.
 */

public class MyOrder {
    /**
     * String orderId---------------订单的主键，唯一确定订单
     * String sendAddress--------发出地
     * String receiveAddress-----接收地
     * int orderState------------订单的状态：0-未接单，1-进行中，2-已接单
     * int orderSend-------------订单的发起状态：0-已接到的订单，1-已发起的订单
     * float price---------------订单的价格
     */
    private String orderId;
    private String sendAddress;
    private String receiveAddress;
    private int orderState;
    private int orderSend;
    private float price;
    public MyOrder(String orderId,String sendAddress,String receiveAddress,int orderState,int orderSend,float price){
        this.orderId=orderId;
        this.sendAddress=sendAddress;
        this.receiveAddress=receiveAddress;
        this.orderState=orderState;
        this.orderSend=orderSend;
        this.price=price;
    }
    public String getOrderSendString(){
        String result="";
        switch (this.orderState){
            case 0:
                result="已接到的订单";
                break;
            case 1:
                result="未接到的订单";
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
    public String getKindImageString(){
        String result;
        String random=String.valueOf(Math.round(Math.random()));
        switch (random){
            case "0":
                result="@drawable/cloth";
                break;
            case "1":
                result="@drawable/gift";
                break;
            default:
                result="@drawable/gift";
                break;
        }
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
}
