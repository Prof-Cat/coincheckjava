package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;


public class CryptoTrading {

    public static final String helpUsage = "CryptoTrading -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(CryptoTrading.class);
    public static ExchangeX targetExchange;
    public static void main(String[] args) {
        
        String exchCode = System.getProperty("TARGET");
        String cfgFile = System.getProperty("CONFIG");

        Properties props = System.getProperties();
        props.list(System.out);
        
        //Map<String, String> env = System.getenv();
        //for (String key : env.keySet()) {
        //    System.out.println(key + ": " + env.get(key));
        //}
        
        if ( cfgFile == null ){
            System.out.print( helpUsage);
            System.exit(-1);
        }
        try {
            // Load the log4j2.xml or log4j2.properties file
            System.setProperty("current.date", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));

            logger.info("CONFIG: " + cfgFile);
            Map<String, ExchangeX> myConfigMap = Configuration.loadConfig(cfgFile);
            
            String myClassPath = System.getProperty("java.class.path");
            logger.info(myClassPath);

            Marketdata execMarketData = new Marketdata(exchCode, myConfigMap);
            String xCoinPair = execMarketData.targetExchange.coinPair;

            // logger.info(api.getTicker());
            for ( int i=0; i<1; i++){
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=ticker::" + execMarketData.getPublicTicker());
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=trades::" + execMarketData.getPublicTrades());
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=orderbook::" + execMarketData.getPublicOrderBooks());
                Thread.sleep(1000);
            }

            ExchangeAccount coinCheckAccount = new ExchangeAccount(exchCode, myConfigMap);
            logger.info(Util.headerString(exchCode, xCoinPair, "AccountInfo") + coinCheckAccount.getAccountInfo()); Thread.sleep(300);
            logger.info(Util.headerString(exchCode, xCoinPair, "AccountBalance") + coinCheckAccount.getAccountBalance()); Thread.sleep(300);
            Thread.sleep(300);

            LineHandler coinCheckHandler = new LineHandler(exchCode, myConfigMap);
            logger.info(Util.headerString(exchCode, xCoinPair, "OpenOrders") + coinCheckHandler.getOpenOrders());
            logger.info(Util.headerString(exchCode, xCoinPair, "Transactions") + coinCheckHandler.getTransactions());
            // logger.info(Util.headerString(exchCode, xCoinPair, "CancelOrder") + coinCheckHandler.cancelOrderID("000001"));

            // Send new test order
            // will use CryptoOrder class later
            String testOrder = coinCheckHandler.newOrder(0.20, 3000, xCoinPair, CoinCheckOrderType.LIMTTBUY);

            // assume response is the JSON string
            JSONObject testOrderResponseJson = new JSONObject(testOrder);

            if (testOrderResponseJson.getBoolean("success")) {
                String myOrderID = testOrderResponseJson.getString("id");
                logger.info(Util.headerString(exchCode, xCoinPair, "CancelOrder") + coinCheckHandler.cancelOrderID(myOrderID));
            } else {
                logger.info(testOrderResponseJson.toString());
            }

            // cancel all open orders
            String openOrdersJson = coinCheckHandler.getOpenOrders();
            JSONObject openOrderResponseJson = new JSONObject(openOrdersJson);
            if (openOrderResponseJson.getBoolean("success")) {
                JSONArray ordersJson = openOrderResponseJson.getJSONArray("orders");
                for (int i = 0; i < ordersJson.length(); i++) {
                    JSONObject orderJson = ordersJson.getJSONObject(i);
                    Long orderId = (Long) orderJson.get("id");
                    logger.info(Util.headerString(exchCode, xCoinPair, "CancelOrder") + coinCheckHandler.cancelOrderID(orderId));
                }
            } else {
                logger.info(testOrderResponseJson.toString());
            }

            logger.info(Util.headerString(exchCode, xCoinPair, "OpenOrders") + coinCheckHandler.getOpenOrders());

            // ObjectMapper testOrderMapper = new ObjectMapper();
            // Map<String, Object> testOrderMap = testOrderMapper.readValue(testOrder, Map.class);

            // if( (boolean) testOrderMap.get("success") ) {
            //     String myOrderID = (String) testOrderMap.get("id");
            //     logger.info(Util.headerString(exchCode, xCoinPair, "CancelOrder") + coinCheckHandler.cancelOrderID(myOrderID));
            // } else {
            //     logger.info(testOrderMap.toString());
            // }
            // Map<String, Object> openOrderMap = testOrderMapper.readValue(openOrdersJson, Map.class);
            // if( (boolean) testOrderMap.get("success") ) {
            //     String myOrderID = (String) testOrderMap.get("id");
            //     logger.info(Util.headerString(exchCode, xCoinPair, "CancelOrder") + coinCheckHandler.cancelOrderID(myOrderID));
            // } else {
            //     logger.info(testOrderMap.toString());
            // }

            System.exit(0);
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

}
