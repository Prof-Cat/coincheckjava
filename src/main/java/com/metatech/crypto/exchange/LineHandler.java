package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.TagMap.ExchangeEnum;

import org.slf4j.Logger;
import java.util.Map;
import java.util.TreeMap;

public class LineHandler {
    private String apiKey;
    private String apiSecret;
    public ExchangeX targetExchange;
    private static final Logger logger = Testslf4j.getLogger(LineHandler.class);

    public LineHandler(String xExchange, TreeMap<String, String>theMap) {
        try {
            targetExchange = new ExchangeX(theMap);
            apiKey = targetExchange.getApiKey();
            apiSecret = targetExchange.getSecret();
            logger.info("LineHandler initialized with " + targetExchange.getExchangeName());
        } catch (Exception e) {
            logger.info("LineHandler initialization FAILED" + theMap.get(ExchangeEnum.EXHANGENAME.getValue()));
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
    
        // Send new order via exchenga/orders
        // HTTP REQUEST :: POST /api/exchange/orders
        // PARAMETERS
        // *pair取引ペア。現在は btc_jpy, etc_jpy, lsk_jpy, mona_jpy, omg_jpy, plt_jpy, fnct_jpyが利用可能です。
        // *order_type注文方法
        // rate注文のレート。（例）28000
        // amount注文での量。（例）0.1
        // market_buy_amount成行買で利用する日本円の金額。（例）10000
        // stop_loss_rate逆指値レート ( 逆指値とは？ )
        // time_in_force注文有効期間(optional)。
        // "good_til_cancelled"(キャンセルされるまで有効。デフォルト) 
        // あるいは "post_only"( postonlyとは？ )が指定できます。
        public String newOrder( double xPrice, double xQty, String xCoinPair, CoinCheckOrderType xOrderType ){
            String jsonString;
            // order price check, and qty check, etc should pass before reaching here
            String url = targetExchange.getBaseUrl() + "exchange/orders";
            // String bodyNewOrder = "{\"rate\":" + xPrice + ",\"amount\":" + xQty + ",\"order_type\":\"" + xOrderType.getValue() + "\",\"pair\":\"" + xCoinPair + "\"}";
            // String bodyNewOrder = "{rate=" + xPrice + "&amount=" + xQty + "&order_type=" + xOrderType.getValue() + "&pair=" + xCoinPair + "}";
            String bodyNewOrder = "rate=" + xPrice + "&amount=" + xQty + "&order_type=" + xOrderType.getValue() + "&pair=" + xCoinPair;
            logger.info(Util.headerString(targetExchange.getExchangeName(), xCoinPair, "newOrder") + bodyNewOrder);
            jsonString = Util.postByUrlWithHeader(url, apiKey, apiSecret, bodyNewOrder);
            return jsonString;
        }

        public String newOrder( CryptoCashOrder myNewCashOrder ){
            String jsonString;
            // order price check, and qty check, etc should pass before reaching here
            String url = targetExchange.getBaseUrl() + "exchange/orders";

            String bodyNewOrder = myNewCashOrder.buildPostBody();
            logger.info(Util.headerString(targetExchange.getExchangeName(), targetExchange.getCoinPair(), "newOrder") + bodyNewOrder);
            jsonString = Util.postByUrlWithHeader(url, apiKey, apiSecret, bodyNewOrder);
            return jsonString;
        }

        // Cancel <id> : DELETE /api/exchange/orders/<id>
        public String cancelOrderID( String xId) {
            String url = targetExchange.getBaseUrl() + "exchange/orders/" + xId;
            String jsonString = Util.deleteByUrlWithHeader(url, apiKey, apiSecret); // requestByUrlWithHeader(url);
            return jsonString;
        }

        // Cancel <id> : DELETE /api/exchange/orders/<id>
        public String cancelOrderID( Long xId) {
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
