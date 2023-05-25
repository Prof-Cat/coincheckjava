package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.TagMap.ExchangeAccessEnum;
import com.metatech.crypto.exchange.util.WebUtil;

import org.slf4j.Logger;
import java.util.TreeMap;

public class LineHandler {
    private String apiKey;
    private String apiSecret;
    public ExchangeWrapper targetExchange;
    private static final Logger logger = Testslf4j.getLogger(LineHandler.class);

    public LineHandler(String xExchange, TreeMap<String, String>theMap) {
        try {
            targetExchange = new ExchangeWrapper(theMap);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("LineHandler initialized with " + targetExchange.getExchangeName());
        } catch (Exception e) {
            logger.info("LineHandler initialization FAILED" + theMap.get(ExchangeAccessEnum.EXHANGENAME.getValue()));
            logger.error(xExchange, e);
        }
    }

        // GET /api/exchange/orders/opens
        public String getOpenOrders() {
            String url = targetExchange.opensEndPoint();
            String jsonString = WebUtil.requestByUrlWithHeader(url, apiKey, apiSecret);
            return jsonString;
        }
    
        // GET /api/exchange/orders/transactions_pagination
        public String getTransactionPages() {
            String url = targetExchange.pagedTxsEndPoint();
            String jsonString = WebUtil.requestByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
    
        public String newOrder( OrderPortfolio myNewCashOrder ){
            String jsonString;
            // order price check, and qty check, etc should pass before reaching here
            String url = targetExchange.orderEndPoint();

            String bodyNewOrder = myNewCashOrder.buildPostBody();
            logger.info(WebUtil.headerString(targetExchange.getExchangeName(), targetExchange.getCoinPair(), ExchangeAccessEnum.NEWORDER.getValue()) + bodyNewOrder);
            jsonString = WebUtil.postByUrlWithHeader(url, apiKey, apiSecret, bodyNewOrder);
            return jsonString;
        }

        // Cancel <id> : DELETE /api/exchange/orders/<id>
        public String cancelOrderID( String xId) {
            String url = targetExchange.orderEndPoint() + xId;
            String jsonString = WebUtil.deleteByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }

        // Cancel <id> : DELETE /api/exchange/orders/<id>
        public String cancelOrderID( Long xId) {
            String url = targetExchange.orderEndPoint() + Long.toString(xId);
            String jsonString = WebUtil.deleteByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
        
        // Request <id> cancel status : GET /api/exchange/orders/cancel_status?id=[id]
        public String getCancelStatus( String xId) {
            String url = targetExchange.cancelStatusEndPoint() + xId;
            String jsonString = WebUtil.requestByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
        
        // GET /api/exchange/orders/transactions
        public String getTransactions() {
            String url = targetExchange.transactionEndPoint();
            String jsonString = WebUtil.requestByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }
    
}
