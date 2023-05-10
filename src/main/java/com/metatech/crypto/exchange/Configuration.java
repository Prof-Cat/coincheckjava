package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class Configuration {
    private static final String MY_CONFIG_FILE = "exchange.cnf.xml";
    private static final Map<String, ExchangeX> pairExchanges = new HashMap<>();
    private static final TradingDatabase tradingDatabase = new TradingDatabase();
    private static final StaticdataCMC coinMarketCap = new StaticdataCMC();
    private static final Logger logger = Testslf4j.getLogger(Configuration.class);
    private static String curConfigFile;

    public static synchronized Map<String, ExchangeX> loadConfig(String configFile) throws IOException {
        if (configFile == null) {
            configFile = MY_CONFIG_FILE;
        }

        if( curConfigFile != null && curConfigFile == configFile) {
            return pairExchanges;
        }

        curConfigFile = configFile;

        File myFile = new File(configFile);
        if (!myFile.exists()){
            throw new IOException("Config file not found: " + configFile, null);
        }

        try (FileInputStream fis = new FileInputStream(myFile)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fis);
            Element root = doc.getDocumentElement();

            processDatabase( root );
            processCoinMarketCap(root);
            processExchange("coincheck", root);
            processExchange("zaif",root);

            return pairExchanges;
        }
        catch ( Exception e) {
            // TODO: handle exceptions here
            return pairExchanges;
        }
    }

    public Map<String, ExchangeX> getPairExchangeMap(){
        return Configuration.pairExchanges;
    }

    public TradingDatabase getTradingDatabase() {
        return Configuration.tradingDatabase;
    }

    public static StaticdataCMC geStaticdataCMC(){
        return Configuration.coinMarketCap;
    }

    private static void processDatabase( Element xRoot){
        Element database = (Element) xRoot.getElementsByTagName("database").item(0);
        String host = database.getElementsByTagName("host").item(0).getTextContent();
        String port = database.getElementsByTagName("port").item(0).getTextContent();
        String username = database.getElementsByTagName("username").item(0).getTextContent();
        String password = database.getElementsByTagName("password").item(0).getTextContent();
        tradingDatabase.init("mySQL", host, port, username, password);
    }

    private static void processCoinMarketCap( Element xRoot ){
        // <base_url>https://pro-api.coinmarketcap.com/</base_url>
        // <static_info>v2/cryptocurrency/info</static_info>
        // <static_map>v1/cryptocurrency/map</static_map>
        // <apiHeader>X-CMC_PRO_API_KEY</apiHeader>
        // <api_key>api_key</api_key>
        // <base_currency>jpy</base_currency>
        // <target_symbol>bitcoin</target_symbol>
        // Get static data source information
        Element static_data_cmc = (Element) xRoot.getElementsByTagName("coinmarketcap").item(0);
        coinMarketCap.setBaseUrl(static_data_cmc.getElementsByTagName("base_url").item(0).getTextContent());
        coinMarketCap.setApiKey(static_data_cmc.getElementsByTagName("api_key").item(0).getTextContent());
        String static_map = static_data_cmc.getElementsByTagName("static_map").item(0).getTextContent();
        coinMarketCap.setApiHeader( static_data_cmc.getElementsByTagName("apiHeader").item(0).getTextContent());
        String base_currency = static_data_cmc.getElementsByTagName("base_currency").item(0).getTextContent();
        String target_symbol = static_data_cmc.getElementsByTagName("target_symbol").item(0).getTextContent();
        
        logger.info("Coin Pair:" + static_map);
        logger.info("==============================");
    
    }

    private static void processExchange( String xExchange, Element xRoot ){
        // Get the exchange information
        Element exchanges = (Element) xRoot.getElementsByTagName(xExchange).item(0);
        String baseUrl = exchanges.getElementsByTagName("base_url").item(0).getTextContent();
        String apiKey = exchanges.getElementsByTagName("api_key").item(0).getTextContent();
        String apiSecret = exchanges.getElementsByTagName("api_secret").item(0).getTextContent();
        String exchangeName = exchanges.getElementsByTagName("exch_name").item(0).getTextContent();
        String xCoin = exchanges.getElementsByTagName("coin").item(0).getTextContent();
        String xCurrency = exchanges.getElementsByTagName("currency").item(0).getTextContent();
        logger.info("Exchange Name: " + exchangeName);
        logger.info("Base URL: " + baseUrl);
        logger.info("================================");
        ExchangeX yExchange = new ExchangeX(apiKey, apiSecret, baseUrl, exchangeName, xCoin, xCurrency);
        pairExchanges.put(exchangeName, yExchange); 
    }
}