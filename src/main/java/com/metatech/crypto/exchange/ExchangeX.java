package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

public class ExchangeX {
    private final String apiKey;
    private final String apiSecret;
    private final String baseUrl;
    public final String exchangeName;
    public final String coin;
    public final String currency;
    private static final Logger logger = Testslf4j.getLogger(ExchangeX.class);

    public ExchangeX(String xApiKey, String xApiSecret, String xBaseUrl, String xName, String xCoin, String xCurrency){
        this.apiKey = xApiKey;
        this.apiSecret = xApiSecret;
        this.baseUrl = xBaseUrl;
        this.exchangeName = xName;
        this.coin = xCoin;
        this.currency = xCurrency;
        logger.info("Exchange " + xName + " initialized with base url:" + xBaseUrl);
    }
    
    public String getApiKey(){ return this.apiKey;}
    public String getSecret(){return this.apiSecret;    }
    public String getBaseUrl(){  return this.baseUrl; }
    public String getExchangeName(){ return this.exchangeName; }
    public String getCoinPair(){ return this.coin + "_" + this.currency; }
    public String getCoin(){ return this.coin;}
    public String getCurrency(){ return this.currency;}
}
