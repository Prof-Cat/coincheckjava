package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Marketdata {
    private String apiKey;
    private String apiSecret;
    public static ExchangeX targetExchange;
    private static final Logger logger = Testslf4j.getLogger(Marketdata.class);

    public Marketdata(String xExchange, Map<String, ExchangeX> theMap) {
        try {
            targetExchange = theMap.get(xExchange);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("Marketdata initialized with " + xExchange);
        } catch (Exception e) {
            logger.info("Marketdata initialization FAILED" + xExchange);
            logger.error(xExchange, e);
        }
    }

    public String getPublicTicker() {
        String url = targetExchange.getBaseUrl() + "/ticker?pair=" + targetExchange.getCoinPair(); 
        String jsonString = Util.requestByPublicUrl(url);
        return jsonString;
    }

    public String getPublicTrades() {
        String url = targetExchange.getBaseUrl() + "/trades?pair=" + targetExchange.getCoinPair(); 
        String jsonString = Util.requestByPublicUrl(url);
        return jsonString;
    }

    public String getPublicOrderBooks() {
        String url = targetExchange.getBaseUrl() + "/order_books?pair=" + targetExchange.getCoinPair(); 
        String jsonString = Util.requestByPublicUrl(url);
        return jsonString;
    }

    public String getPrivateTicker() {
        String url = targetExchange.getBaseUrl() + "/accounts/ticker?pair=" + targetExchange.getCoinPair();
        String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret);
        return jsonString;
    }


}
