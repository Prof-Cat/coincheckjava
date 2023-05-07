package com.metatech.crypto.exchange;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class CryptoCashOrder {

    protected String orderAccount;  // Tag <1>
    protected String clientOrderID; // Tag <11>
    protected Integer curHandInst;  // Tag <21>
    protected CoinCheckOrderType orderType;  // Tag <40>
    protected Double orderQty;   // Tag <38>
    protected Double orderPrice;     // Tag <44>
    protected String currency;  // Tag <15>
    protected String coinID;    // Tag <48>
    protected String coinIDSource;  // Tag <22>
    protected String coin;        // Tag <55>
    protected String timeInForce;      // Tag <59>
    protected LocalDate tradeDate;        // Tag <75>
    protected String coinType;  // Tag <167> 
    protected LocalTime expireTime;  // Tag <126>
    protected String text;   // Tag <58>
    protected String portfolioName;   // Tag <??>

    public CryptoCashOrder(){
        this.clientOrderID = Util.generateNextClOrdID();
        this.coin = "btc";
        this.coinID = "1";
        this.coinIDSource = "CMC";
        this.coinType = "NATIVE";
        this.curHandInst = 1;
        this.currency = "JPY";
        this.expireTime = LocalTime.now();  // TODO: plus 4 hours for example
        this.orderAccount = "";
        this.orderPrice = -1.0;
        this.orderQty = 0.0;
        this.orderType = CoinCheckOrderType.LIMTTBUY;
        this.portfolioName = "my test";
        this.text = "coin check mm";
        this.timeInForce = "DAY";   // TODO: ENUM planned
        this.tradeDate = LocalDate.now();
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
    public void setPrice(double xPrice){
        this.orderPrice = xPrice;
    }     
    public double getPrice(){
        return this.orderPrice;
    }
    public void setCurrency(String xCurrency){
        this.currency = xCurrency;
    }     
    public String getCurrency(){
        return this.currency;
    }
    public void setCoin(String xCoin){
        this.coin = xCoin;
    }     
    public String getCoin(){
        return this.coin;
    }
    public void setTimeInForce(String xTIF){
        this.timeInForce = xTIF;
    } 
    public String getTIF(){
        return this.timeInForce;
    }
    public void setTradeDate( LocalDate xDate){
        this.tradeDate = xDate;
    } 
    public LocalDate getTradeDate(){
        return this.tradeDate;
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
