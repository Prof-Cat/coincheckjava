package com.metatech.crypto.exchange;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.metatech.crypto.exchange.TagMap.BaseCurrency;
import com.metatech.crypto.exchange.TagMap.ExDestination;

public class PortfolioOrder {

    protected String orderAccount;  // Tag <1>
    protected String clientOrderID; // Tag <11>
    protected Integer curHandInst;  // Tag <21>
    protected CoinCheckOrderType orderType;  // Tag <40>
    protected Double orderQty;   // Tag <38>
    protected TagMap.OrderStatusEnum orderStatus;  // Tag <39>
    protected Double orderPrice;     // Tag <44>
    protected TagMap.BaseCurrency currency;  // Tag <15>
    protected String coinID;    // Tag <48>
    protected String coinIDSource;  // Tag <22>
    protected String coin;        // Tag <55>
    protected TagMap.TimeInForceEnum timeInForce;      // Tag <59>
    protected LocalDate theDate;        // Tag <75>
    protected LocalTime transactTime; // Tag <60>
    protected String marginType; // Tag <77>
    protected TagMap.ExDestination exDestination; //Tag <100>
    protected LocalTime effectTime; // Tag <168>
    protected String coinType;  // Tag <167> 
    protected LocalTime expireTime;  // Tag <126>
    protected String text;   // Tag <58>
    protected String portfolioName;   // Tag <??>

    public PortfolioOrder(){
        this.clientOrderID = Util.generateNextClOrdID();
        this.coin = "btc";
        this.coinID = "1";
        this.coinIDSource = "CMC";
        this.coinType = "NATIVE";
        this.curHandInst = 1;
        this.currency = BaseCurrency.JPY;
        this.expireTime = LocalTime.now();  // TODO: plus 4 hours for example
        this.orderAccount = "";
        this.orderPrice = -1.0;
        this.orderQty = 0.0;
        this.orderStatus = TagMap.OrderStatusEnum.LOCAL;
        this.orderType = CoinCheckOrderType.LIMTTBUY;
        this.portfolioName = "my test";
        this.text = "coin check mm";
        this.timeInForce = TagMap.TimeInForceEnum.DAY;   // TODO: ENUM planned
        this.theDate = LocalDate.now();
    }

    public void initSingleOrder(double xPrice, double xQty, String xCoin, 
        String xCurrency, CoinCheckOrderType xOrderType ){
        this.orderPrice = xPrice;
        this.orderQty = xQty;
        this.orderType = xOrderType;
        this.coin = xCoin;
        this.currency = TagMap.BaseCurrency.fromValue(xCurrency);

        // validation - limit check & sanity check

        // Assign order ID

        //

    }

    public void initSingleOrder(double xPrice, double xQty, String xCoinPair, CoinCheckOrderType xOrderType ){
        String xMyPair[] = xCoinPair.split("_");
        this.initSingleOrder(xPrice, xQty, xMyPair[0], xMyPair[1], xOrderType);
    }

    public String buildPostBody(){
        // String bodyNewOrder = "{\"rate\":" + xPrice + ",\"amount\":" + xQty + ",\"order_type\":\"" + xOrderType.getValue() + "\",\"pair\":\"" + xCoinPair + "\"}";
        // String bodyNewOrder = "{rate=" + xPrice + "&amount=" + xQty + "&order_type=" + xOrderType.getValue() + "&pair=" + xCoinPair + "}";
        // String bodyNewOrder = "rate=" + xPrice + "&amount=" + xQty + "&order_type=" + xOrderType.getValue() + "&pair=" + xCoinPair;
        String myPostBody = "rate=" + this.orderPrice + "&amount=" + this.orderQty + "&order_type=" + this.orderType.getValue() + "&pair=" + this.getCoinPair();
        return myPostBody;
    }

    public String getCoinPair(){
        return this.coin + "_" + this.currency;
    }

    public void setClOrdID(String xID){
        this.clientOrderID = xID;
    }
    public String getClOrdID(){
        return this.clientOrderID;
    }
    public void setAccount( String xAccount){
        this.orderAccount = xAccount;
    }  
    public String getAccount(){
        return this.orderAccount;
    }    
    public void setHandInst( Integer xInst){
        this.curHandInst = xInst;
    } 
    public Integer getHandInst(){
        return this.curHandInst;
    }
    public void setOrderType(CoinCheckOrderType xType){
        this.orderType = xType;
    }    
    public CoinCheckOrderType getOrderType(){
        return this.orderType;
    }
    public void setQty(double xQty){
        this.orderQty = xQty;
    };   
    public double getQty(){
        return this.orderQty;
    }
    public void setOrderStatus(TagMap.OrderStatusEnum xStatus){
        this.orderStatus = xStatus;
    }; 
    public TagMap.OrderStatusEnum getOrderStatus(){
        return this.orderStatus;
    };   
    public void setPrice(double xPrice){
        this.orderPrice = xPrice;
    }     
    public double getPrice(){
        return this.orderPrice;
    }
    public void setCurrency(BaseCurrency xCurrency){
        this.currency = xCurrency;
    }     
    public BaseCurrency getCurrency(){
        return this.currency;
    }
    public void setCoin(String xCoin){
        this.coin = xCoin;
    }     
    public String getCoin(){
        return this.coin;
    }
    public void setTimeInForce(TagMap.TimeInForceEnum xTIF){
        this.timeInForce = xTIF;
    } 
    public TagMap.TimeInForceEnum getTIF(){
        return this.timeInForce;
    }
    public void setTradeDate( LocalDate xDate){
        this.theDate = xDate;
    } 
    public LocalDate getTradeDate(){
        return this.theDate;
    }
    public void setExpireTime( LocalTime xTime){
        this.expireTime = xTime;
    }
    public LocalTime getExpireTime(){
        return this.expireTime;
    }
    public void setCoinType(String xCoinType){
        this.coinType = xCoinType;
    }
    public String getCoinType(){
        return this.coinType;
    }
    public void setText(String xText){
        this.text = xText;
    }
    public String getText(){
        return this.text;
    }
    public void setPortfolioName(String xName){
        this.portfolioName = xName;
    }
    public String getPortfolioName(){
        return this.portfolioName;
    }   
}
