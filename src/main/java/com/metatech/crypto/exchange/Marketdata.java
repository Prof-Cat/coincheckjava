package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.TreeMap;

public class Marketdata {
    private String apiKey;
    private String apiSecret;
    public static ExchangeWrapper targetExchange;
    private static final Logger logger = Testslf4j.getLogger(Marketdata.class);

    public Marketdata(String xExchange, TreeMap<String, String> theMap) {
        try {
            targetExchange =  new ExchangeWrapper(theMap);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("Marketdata initialized with " + xExchange);
        } catch (Exception e) {
            logger.info("Marketdata initialization FAILED" + xExchange);
            logger.error(xExchange, e);
        }
    }

    public String getPublicTicker() {
        String url = targetExchange.tickerEndPoint() + targetExchange.getCoinPair(); 
        String jsonString = Util.requestByPublicUrl(url);
        return jsonString;
    }

    public String getPublicTrades() {
        String url = targetExchange.tradesEndPoint() + targetExchange.getCoinPair(); 
        String jsonString = Util.requestByPublicUrl(url);
        return jsonString;
    }

    public String getPublicOrderBooks() {
        String url = targetExchange.orderBookEndPoint() + targetExchange.getCoinPair(); 
        String jsonString = Util.requestByPublicUrl(url);
        return jsonString;
    }

    // public String getPrivateTicker() {
    //     String url = targetExchange.getBaseUrl() + "/accounts/ticker?pair=" + targetExchange.getCoinPair();
    //     String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret);
    //     return jsonString;
    // }

}
