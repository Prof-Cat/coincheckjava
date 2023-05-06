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

public class ExchangeAccount {
    private String apiKey;
    private String apiSecret;
    public static ExchangeX targetExchange;
    private static final Logger logger = Testslf4j.getLogger(ExchangeAccount.class);

    public ExchangeAccount(String xExchange, Map<String, ExchangeX> theMap) {
        try {
            targetExchange = theMap.get(xExchange);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("ExchangeAccount initialized with " + xExchange);
        } catch (Exception e) {
            logger.info("ExchangeAccount initialization FAILED" + xExchange);
            logger.error(xExchange, e);
        }
    }
        // GET /api/accounts/balance
        public String getAccountBalance() {
            String url = targetExchange.getBaseUrl() + "accounts/balance";
            logger.info(url);
            String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret);
            return jsonString;
        }
    
        // GET /api/accounts
        public String getAccountInfo() {
            String url = targetExchange.getBaseUrl() + "accounts";
            String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret);
            return jsonString;
        }
    
}
