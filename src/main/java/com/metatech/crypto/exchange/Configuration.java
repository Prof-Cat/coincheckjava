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
    private static final Map<String, Configuration> CONFIGMAP = new HashMap<>();
    private static final Logger logger = Testslf4j.getLogger(Configuration.class);

    private static String curConfigFile;
    private final String apiKey;
    private final String apiSecret;
    private final String baseUrl;
    private final String exchangeName;

    private Configuration(String xApiKey, String xApiSecret, String xBaseUrl, String xName){
        this.apiKey = xApiKey;
        this.apiSecret = xApiSecret;
        this.baseUrl = xBaseUrl;
        this.exchangeName = xName;
    }
    
    public String getApiKey(){
        return this.apiKey;
    }

    public String getSecret(){
        return this.apiSecret;
    }

    public String getUrl(){
        return this.baseUrl;
    }

    public String getExchangeName(){
        return this.exchangeName;
    }

    public static synchronized Map<String, Configuration> loadConfig(String configFile) throws IOException {
        if (configFile == null) {
            configFile = MY_CONFIG_FILE;
        }

        if( curConfigFile != null && curConfigFile == configFile) {
            return CONFIGMAP;
        }

        curConfigFile = configFile;

        File myFile = new File(configFile);
        if (!myFile.exists()){
            throw new IOException("Config file not found: " + configFile, null);
        }

        try (FileInputStream fis = new FileInputStream(myFile)) {
            // Create a DocumentBuilderFactory instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Create a DocumentBuilder instance
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parse the XML file and create a Document instance
            Document doc = builder.parse(fis);

            Element root = doc.getDocumentElement();

            // Get the database information
            // Element database = (Element) root.getElementsByTagName("database").item(0);
            // String host = database.getElementsByTagName("host").item(0).getTextContent();
            // String port = database.getElementsByTagName("port").item(0).getTextContent();
            // String username = database.getElementsByTagName("username").item(0).getTextContent();
            // String password = database.getElementsByTagName("password").item(0).getTextContent();

            // Get the exchange information
            Element exchanges = (Element) root.getElementsByTagName("coincheck").item(0);
            String baseUrl = exchanges.getElementsByTagName("base_url").item(0).getTextContent();
            String apiKey = exchanges.getElementsByTagName("api_key").item(0).getTextContent();
            String apiSecret = exchanges.getElementsByTagName("api_secret").item(0).getTextContent();
            String exchangeName = exchanges.getElementsByTagName("exch_name").item(0).getTextContent();
            
            logger.info("Exchange Name: " + exchangeName);
            logger.info("Base URL: " + baseUrl);
            logger.info("API Key: " + apiKey);
            logger.info("API Secret: " + apiSecret);
            logger.info("==============================");
            Configuration xConfig = new Configuration(apiKey, apiSecret, baseUrl, exchangeName);
            CONFIGMAP.put(exchangeName, xConfig);   

            // Get the exchange information
            exchanges = (Element) root.getElementsByTagName("zaif").item(0);
            baseUrl = exchanges.getElementsByTagName("base_url").item(0).getTextContent();
            apiKey = exchanges.getElementsByTagName("api_key").item(0).getTextContent();
            apiSecret = exchanges.getElementsByTagName("api_secret").item(0).getTextContent();
            exchangeName = exchanges.getElementsByTagName("exch_name").item(0).getTextContent();
            logger.info("Exchange Name: " + exchangeName);
            logger.info("Base URL: " + baseUrl);
            logger.info("API Key: " + apiKey);
            logger.info("API Secret: " + apiSecret);
            logger.info("================================");
            xConfig = new Configuration(apiKey, apiSecret, baseUrl, exchangeName);
            CONFIGMAP.put(exchangeName, xConfig);  
            // NodeList exchangeList = exchanges.getElementsByTagName("*");
            // System.out.println(exchangeList.getLength());
            // for (int i = 0; i < exchangeList.getLength(); i++) {
            //     Element exchange = (Element) exchangeList.item(i);
            //     String baseUrl = exchange.getElementsByTagName("base_url").item(0).getTextContent();
            //     String apiKey = exchange.getElementsByTagName("api_key").item(0).getTextContent();
            //     String apiSecret = exchange.getElementsByTagName("api_secret").item(0).getTextContent();
            //     String exchangeName = exchange.getElementsByTagName("exch_name").item(0).getTextContent();
            //     System.out.println("Exchange Name: " + exchangeName);
            //     System.out.println("Base URL: " + baseUrl);
            //     System.out.println("API Key: " + apiKey);
            //     System.out.println("API Secret: " + apiSecret);
            //     System.out.println("");
            //     Configuration xConfig = new Configuration(apiKey, apiSecret, baseUrl, exchangeName);
            //     CONFIGMAP.put(exchangeName, xConfig);     
            // }
            return CONFIGMAP;
        }
        catch ( Exception e) {
            // TODO: handle exceptions here
            return CONFIGMAP;
        }
    }
}
