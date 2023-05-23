package com.metatech.crypto.exchange;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.TagMap.BaseCurrency;
import com.metatech.crypto.exchange.TagMap.ExDestination;

public class MarketDataSnapShot {
    private static final Logger logger = Testslf4j.getLogger(Configuration.class);
    protected ExDestination exchangeName;
    protected BaseCurrency currency;
    protected String coin;
    protected double lastPrice;
    protected double bestBid;
    protected double bestAsk;
    protected double highPrice;
    protected double lowPrice;
    protected double volume;
    protected long timestamp;

    public MarketDataSnapShot(ExDestination xExchange, BaseCurrency xCurrency, String xCoin){
        this.exchangeName = xExchange;
        this.currency = xCurrency;
        this.coin = xCoin;
    }

    public void setLastSnapShot(String xResult){
        try {
            JSONObject jsonResult = new JSONObject(xResult);
            switch (this.exchangeName){
                case COINCHECK:
                    this.bestAsk = jsonResult.getDouble("ask");
                    this.bestBid = jsonResult.getDouble("bid");
                    this.lastPrice = jsonResult.getDouble("last");
                    this.highPrice = jsonResult.getDouble("high");
                    this.lowPrice = jsonResult.getDouble("low");
                    this.volume = jsonResult.getDouble("volume");
                    this.timestamp = jsonResult.getLong("timestamp");
                    break;
                case ZAIF:
                    break;
                default:
                    logger.error("Unknown target exchange " + xResult);
            }
            if ( this.exchangeName.equals(ExDestination.COINCHECK)){
 
            }
        } catch (JSONException je){
            logger.error(xResult, je);
        }
    }

    public void record2DB(){
        if ( this.dataCompleted() ){
            //this.writeToDB();
        }
    }

    private boolean dataCompleted(){
        return true;
    }
}
