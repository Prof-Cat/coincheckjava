package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineHandler {
    private String apiKey;
    private String apiSecret;
    public static Configuration targetExchange;
    private static final Logger logger = Testslf4j.getLogger(LineHandler.class);

    public LineHandler(String xExchange, Map<String, Configuration> theMap) {
        try {
            targetExchange = theMap.get(xExchange);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("LineHandler initialized with " + xExchange);
        } catch (Exception e) {
            logger.info("LineHandler initialization FAILED" + xExchange);
            logger.error(xExchange, e);
        }
    }

        // GET /api/exchange/orders/opens
        public String getOpenOrders() {
            String url = targetExchange.getBaseUrl() + "exchange/orders/opens";
            String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret);
            return jsonString;
        }
    
        // GET /api/exchange/orders/transactions_pagination
        public String getTransactionPages() {
            String url = targetExchange.getBaseUrl() + "exchange/orders/transactions_pagination";
            String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
    
        // Cancel <id> : DELETE /api/exchange/orders/<id>
        public String cancelOrderID( String xId) {
            String url = targetExchange.getBaseUrl() + "exchange/orders/" + xId;
            String jsonString = Util.deleteByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }

        // Request <id> cancel status : GET /api/exchange/orders/cancel_status?id=[id]
        public String getCancelStatus( String xId) {
            String url = targetExchange.getBaseUrl() + "exchange/orders/cancel_status?" + xId;
            String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
        
        // GET /api/exchange/orders/transactions
        public String getTransactions() {
            String url = targetExchange.getBaseUrl() + "exchange/orders/transactions";
            String jsonString = Util.requestByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
    
}
