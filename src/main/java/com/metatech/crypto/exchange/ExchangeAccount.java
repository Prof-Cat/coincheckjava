package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.util.WebUtil;

import org.slf4j.Logger;

import java.util.TreeMap;

public class ExchangeAccount {
    private String apiKey;
    private String apiSecret;
    public static ExchangeWrapper targetExchange;
    private static final Logger logger = Testslf4j.getLogger(ExchangeAccount.class);

    public ExchangeAccount(String xExchange, TreeMap<String, String> theMap) {
        try {
            targetExchange = new ExchangeWrapper(theMap);
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
            String url = targetExchange.accountBallanceEndPoint();
            logger.info(url);
            String jsonString = WebUtil.requestByUrlWithHeader(url, apiKey, apiSecret);
            return jsonString;
        }
    
        // GET /api/accounts
        public String getAccountInfo() {
            String url = targetExchange.accountInfoEndPoint();
            String jsonString = WebUtil.requestByUrlWithHeader(url, apiKey, apiSecret);
            return jsonString;
        }
    
}
