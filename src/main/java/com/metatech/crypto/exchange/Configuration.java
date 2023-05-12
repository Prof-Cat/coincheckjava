package com.metatech.crypto.exchange;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class Configuration {
    private static final String MY_CONFIG_FILE = "exchange.cnf.xml";

    private static final Logger logger = Testslf4j.getLogger(Configuration.class);
    private static String curConfigFile;

    public static final TreeMap<String, String> cmcEndPointsTreeMap = new TreeMap<>();
    public static final TreeMap<String, String> cmcBaseAttributesTreeMap = new TreeMap<>();
    public static final TreeMap<String, String> databaseAttributeTreeMap = new TreeMap<>();
    public static final TreeMap<String, String> primaryExchAttributeTreeMap = new TreeMap<>();
    public static final TreeMap<String, String> secondaryAttributeTreeMap = new TreeMap<>();


    public static synchronized void loadConfig(String configFile) throws IOException {
        if (configFile == null) {
            configFile = MY_CONFIG_FILE;
        }

        if( curConfigFile != null && curConfigFile == configFile) {
            logger.info(" config file already added: " + curConfigFile);
            return;
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
            processExchange( root);
        }
        catch ( Exception e) {
            logger.error(configFile, e);
        }
    }

    private static void processDatabase( Element xRoot){
        Element database = (Element) xRoot.getElementsByTagName("database").item(0);

        // Parse the XML and add key-value pairs to the maps
        NodeList databaseAttributeList = database.getElementsByTagName("database_attributes");
        for (int i = 0; i < databaseAttributeList.getLength(); i++) {
            Element xAttribute = (Element) databaseAttributeList.item(i);
            databaseAttributeTreeMap.put(xAttribute.getAttribute("key"), xAttribute.getTextContent());
        }
        logger.info("==============================");
        //tradingDatabase.init();
    }

    private static void processCoinMarketCap( Element xRoot ){
        Element static_data_cmc = (Element) xRoot.getElementsByTagName("coinmarketcap").item(0);
        
        // Parse the XML and add key-value pairs to the maps
        NodeList attributesList = static_data_cmc.getElementsByTagName("cmc_attributes");
        for (int i = 0; i < attributesList.getLength(); i++) {
            Element xAttribute = (Element) attributesList.item(i);
            //coinMarketCap.addBaseAttributes(xAttribute.getAttribute("key"), xAttribute.getTextContent());
            cmcBaseAttributesTreeMap.put(xAttribute.getAttribute("key"), xAttribute.getTextContent());
        }
        logger.info("==============================");

        // Parse the XML and add key-value pairs to the maps
        NodeList endPointsList = static_data_cmc.getElementsByTagName("cmc_endpoints");
        for (int i = 0; i < endPointsList.getLength(); i++) {
            Element xEndPoint = (Element) endPointsList.item(i);
            //coinMarketCap.addEndPoints(xEndPoint.getAttribute("key"), xEndPoint.getTextContent());
            cmcEndPointsTreeMap.put(xEndPoint.getAttribute("key"), xEndPoint.getTextContent());
        }
        logger.info("==============================");
    
    }

    private static void processExchange( Element xRoot ){
        // Get the exchange information
        Element exchanges = (Element) xRoot.getElementsByTagName("exchanges").item(0);
        
        // Parse the XML and add key-value pairs to the maps
        NodeList attributesList = exchanges.getElementsByTagName("coincheck_attributes");
        for (int i = 0; i < attributesList.getLength(); i++) {
            Element xAttribute = (Element) attributesList.item(i);
            //coinMarketCap.addBaseAttributes(xAttribute.getAttribute("key"), xAttribute.getTextContent());
            primaryExchAttributeTreeMap.put(xAttribute.getAttribute("key"), xAttribute.getTextContent());
            logger.info("primary added key" + xAttribute.getAttribute("key") + " with value " + xAttribute.getTextContent());
        }
        logger.info("==============================");

        NodeList attributesList2 = exchanges.getElementsByTagName("zaif_attributes");
        for (int i = 0; i < attributesList2.getLength(); i++) {
            Element xAttribute = (Element) attributesList2.item(i);
            //coinMarketCap.addBaseAttributes(xAttribute.getAttribute("key"), xAttribute.getTextContent());
            secondaryAttributeTreeMap.put(xAttribute.getAttribute("key"), xAttribute.getTextContent());
            logger.info("seocndary added key" + xAttribute.getAttribute("key") + " with value " + xAttribute.getTextContent());
        }
        logger.info("==============================");
    }
}