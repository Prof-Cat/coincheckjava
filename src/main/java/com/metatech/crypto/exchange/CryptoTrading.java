package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.util.Properties;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CryptoTrading {

    public static final String helpUsage = "CryptoTrading -DCONFIG=<cfg file name> -DTARGET=<exchange code>\n\n";
 
    private static final Logger logger = Testslf4j.getLogger(CryptoTrading.class);
    public static Configuration targetExchange;
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
            Map<String, Configuration> myConfigMap = Configuration.loadConfig(cfgFile);

            Marketdata execMarketData = new Marketdata(exchCode, myConfigMap);
            // logger.info(api.getTicker());
            for ( int i=0; i<1; i++){
                logger.info("Exchange=" + exchCode + "::Pair=" + execMarketData.targetExchange.coinPair  + "::Type=ticker::" + execMarketData.getPublicTicker());
                logger.info("Exchange=" + exchCode + "::Pair=" + execMarketData.targetExchange.coinPair  + "::Type=execution::" + execMarketData.getPublicTrades());
                logger.info("Exchange=" + exchCode + "::Pair=" + execMarketData.targetExchange.coinPair  + "::Type=orderbook::" + execMarketData.getPublicOrderBooks());
                Thread.sleep(1000);
            }

            ExchangeAccount coinCheckAccount = new ExchangeAccount(exchCode, myConfigMap);
            logger.info(coinCheckAccount.getAccountInfo()); Thread.sleep(300);
            logger.info(coinCheckAccount.getAccountBalance()); Thread.sleep(300);
            logger.info(coinCheckAccount.toString()); Thread.sleep(300);

            LineHandler coinCheckHandler = new LineHandler(exchCode, myConfigMap);
            logger.info(coinCheckHandler.getOpenOrders());
            logger.info(coinCheckHandler.getTransactions());
            logger.info(coinCheckHandler.cancelOrderID("000001"));

            System.exit(0);
        } catch ( Exception e) {
            logger.error(e.toString());
        }
    }

}
