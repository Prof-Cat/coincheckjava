package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class CryptoTrading {

    public static final String helpUsage = "CryptoTrading -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(CryptoTrading.class);
    public static ExchangeX targetExchange;
    protected static Marketdata realTimeData;
    protected static Map<String, ExchangeX> myConfigMap;
    protected static LineHandler coinCheckHandler;

    public static void main(String[] args) {
        
        String exchCode = System.getProperty("TARGET");
        String cfgFile = System.getProperty("CONFIG");
        // Properties props = System.getProperties(); props.list(System.out);
                
        if ( cfgFile == null ){
            System.out.print( helpUsage);
            System.exit(-1);
        }
        try {
            // Load the log4j2.xml or log4j2.properties file
            System.setProperty("current.date", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
            logger.info("CONFIG: " + cfgFile);
            initTrading(exchCode,cfgFile);
            
            String myClassPath = System.getProperty("java.class.path");
            logger.info(myClassPath);
            String xCoinPair = realTimeData.targetExchange.getCoinPair();

            // logger.info(api.getTicker());
            for ( int i=0; i<1; i++){
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=ticker::" + realTimeData.getPublicTicker());
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=trades::" + realTimeData.getPublicTrades());
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=orderbook::" + realTimeData.getPublicOrderBooks());
                Thread.sleep(1000);
            }

            ExchangeAccount coinCheckAccount = new ExchangeAccount(exchCode, myConfigMap);
            logger.info(Util.headerString(exchCode, xCoinPair, "AccountInfo") + coinCheckAccount.getAccountInfo()); Thread.sleep(300);
            logger.info(Util.headerString(exchCode, xCoinPair, "AccountBalance") + coinCheckAccount.getAccountBalance()); Thread.sleep(300);
            Thread.sleep(300);

            LineHandler coinCheckHandler = new LineHandler(exchCode, myConfigMap);
            logger.info(Util.headerString(exchCode, xCoinPair, "OpenOrders") + coinCheckHandler.getOpenOrders());
            logger.info(Util.headerString(exchCode, xCoinPair, "Transactions") + coinCheckHandler.getTransactions());

            // Prepare new order from data source
            CryptoCashOrder myNewOrder = new CryptoCashOrder();
            myNewOrder.initSingleOrder(0.20, 3000, realTimeData.targetExchange.coin, realTimeData.targetExchange.currency, CoinCheckOrderType.LIMTTBUY);
            sendNewOrderSingle(myNewOrder);

            // cancel all open orders
            cancelAllOrders(xCoinPair, exchCode);

            logger.info(Util.headerString(exchCode, xCoinPair, "OpenOrders") + coinCheckHandler.getOpenOrders());

            System.exit(0);
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

    public static void initTrading(String exchCode, String cfgFile){
        try {
            myConfigMap = Configuration.loadConfig(cfgFile);
            String myClassPath = System.getProperty("java.class.path");
            logger.info(myClassPath);
    
            realTimeData = new Marketdata(exchCode, myConfigMap);
            // String xCoinPair = realTimeData.targetExchange.coinPair;
            coinCheckHandler = new LineHandler(exchCode, myConfigMap);    
        } catch ( Exception e){
            logger.error(e.toString());
        }
    }

    public static void sendNewOrderSingle(CryptoCashOrder myNewCashOrder){
            // Send new test order
            // will use CryptoOrder class later
            String testOrder = coinCheckHandler.newOrder( myNewCashOrder );

            // assume response is the JSON string
            JSONObject testOrderResponseJson = new JSONObject(testOrder);

            if (testOrderResponseJson.getBoolean("success")) {
                String myOrderID = testOrderResponseJson.getString("id");
                logger.info(Util.headerString(realTimeData.targetExchange.exchangeName, 
                    realTimeData.targetExchange.getCoinPair(), 
                    "CancelOrder") + coinCheckHandler.cancelOrderID(myOrderID));
            } else {
                logger.info(testOrderResponseJson.toString());
            }
    }
    public static void cancelAllOrders(String xCoinPair, String exchCode){
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
            logger.info(openOrderResponseJson.toString());
        }
    }

}
