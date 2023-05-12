package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class CryptoTrading {

    public static final String helpUsage = "CryptoTrading -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(CryptoTrading.class);
    public static ExchangeX targetExchange;
    protected static ExchangeAccount coinCheckAccount;
    protected static Marketdata coinCheckRealTimeData;
    protected static LineHandler coinCheckHandler;
    protected static LineHandler zaifHandler;

    public static void main(String[] args) {
        
        String exchCode = System.getProperty("TARGET");
        String cfgFile = System.getProperty("CONFIG");
                
        if ( cfgFile == null ){
            System.out.print( helpUsage);
            System.exit(-1);
        }
        try {
            System.setProperty("current.date", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
            logger.info("CONFIG: " + cfgFile);
            initTrading(exchCode,cfgFile);
            
            String xCoinPair = coinCheckRealTimeData.targetExchange.getCoinPair();

            // logger.info(api.getTicker());
            for ( int i=0; i<1; i++){
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=ticker::" + coinCheckRealTimeData.getPublicTicker());
                logger.info("================");
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=trades::" + coinCheckRealTimeData.getPublicTrades());
                logger.info("================");
                logger.info("Exchange=" + exchCode + "::Pair=" + xCoinPair  + "::Type=orderbook::" + coinCheckRealTimeData.getPublicOrderBooks());
                logger.info("================");
                Thread.sleep(1000);
            }


            logger.info(Util.headerString(exchCode, xCoinPair, "AccountInfo") + coinCheckAccount.getAccountInfo()); Thread.sleep(300);
            logger.info("================");
            logger.info(Util.headerString(exchCode, xCoinPair, "AccountBalance") + coinCheckAccount.getAccountBalance()); Thread.sleep(300);
            logger.info("================");
            Thread.sleep(300);

            logger.info(Util.headerString(exchCode, xCoinPair, "OpenOrders") + coinCheckHandler.getOpenOrders());
            logger.info("================");
            logger.info(Util.headerString(exchCode, xCoinPair, "Transactions") + coinCheckHandler.getTransactions());
            logger.info("================");

            // Prepare new order from data source
            CryptoCashOrder myNewOrder = new CryptoCashOrder();
            myNewOrder.initSingleOrder(0.20, 3000, coinCheckRealTimeData.targetExchange.getCoin(), coinCheckRealTimeData.targetExchange.getCurrency(), CoinCheckOrderType.LIMTTBUY);
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
            Configuration.loadConfig(cfgFile);

            coinCheckRealTimeData = new Marketdata(exchCode, Configuration.primaryExchAttributeTreeMap);
            coinCheckHandler = new LineHandler(exchCode, Configuration.primaryExchAttributeTreeMap);   
            coinCheckAccount = new ExchangeAccount(exchCode, Configuration.primaryExchAttributeTreeMap ); 
            zaifHandler = new LineHandler(exchCode, Configuration.secondaryAttributeTreeMap);   

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
                logger.info(Util.headerString(coinCheckRealTimeData.targetExchange.getExchangeName(), 
                coinCheckRealTimeData.targetExchange.getCoinPair(), 
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
