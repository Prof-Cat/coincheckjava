package com.metatech.crypto.exchange;

import java.util.TreeMap;
import org.slf4j.Logger;
import com.metatech.JavaCat.Testslf4j;
import com.metatech.crypto.exchange.util.TagMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TradingDatabase {
    private TreeMap<String, String> tradeDBAttributesTreeMap;
    private static final Logger logger = Testslf4j.getLogger(TradingDatabase.class);
    private Connection myConnection;

    public TradingDatabase(){
        this.tradeDBAttributesTreeMap = new TreeMap<>();
    }

    public void init(String xDB, String xHost, String xPort, String xUser, String xPwd){
        this.tradeDBAttributesTreeMap.put(TagMap.TRADEDBUSERNAME,xDB);
        this.tradeDBAttributesTreeMap.put(TagMap.TRADEDBHOST,xHost);
        this.tradeDBAttributesTreeMap.put(TagMap.TRADEDBPORT,xPort);
        this.tradeDBAttributesTreeMap.put(TagMap.TRADEDBUSERNAME,xUser);
        this.tradeDBAttributesTreeMap.put(TagMap.TRADEDBPASSWORD,xPwd);
    }
    
    public void addDBAttribute(String xKey, String xAttributeString){
        if (tradeDBAttributesTreeMap.containsKey(xKey) ) {
            logger.info(xKey + " is already exists");
        } else {
            tradeDBAttributesTreeMap.put(xKey, xAttributeString);
            logger.info("adding access point of " + xKey + "::" + xAttributeString);
        }
    }

    public String getDataBaseAttribute(String xAttribute){
        return this.tradeDBAttributesTreeMap.get(xAttribute);
    }

    public void connect() throws SQLException {
        myConnection = DriverManager.getConnection(
            "jdbc:mysql://" + this.tradeDBAttributesTreeMap.get(TagMap.TRADEDBHOST) + ":" +
            this.tradeDBAttributesTreeMap.get(TagMap.TRADEDBPORT) + "/" +
            this.tradeDBAttributesTreeMap.get(TagMap.TRADEDBUSERNAME), 
            this.tradeDBAttributesTreeMap.get(TagMap.TRADEDBUSERNAME), 
            this.tradeDBAttributesTreeMap.get(TagMap.TRADEDBPASSWORD));
        try{
            this.connect();
        } catch (SQLException e) {
            logger.error("Cannot connect to database::" + e.getStackTrace());
        }
    }

    public void disconnect() throws SQLException {
        if (myConnection != null && !myConnection.isClosed()) {
            myConnection.close();
        }
    }

    public boolean isConnected(){
        try {
            return !myConnection.isClosed();
        } catch ( Exception e) {
            logger.error("Database connection error::", e);
            return false;
        }
    }

    public void addNewOrder(){

    }
    public void updateOrder(){

    }
    public void addExecutionReport(){

    }
    public ResultSet getActiveOrderList(String xDate) {
        ResultSet xResultSet;
        try{
            Statement xStatement = this.myConnection.createStatement();
            xResultSet = xStatement.executeQuery("SELECT * from OrderSingle where TheDate=YYYYMM"); // TODO
            return xResultSet;
        } catch (SQLException e){
            logger.error("cannot get active order list::", e);
            return null;
        }
    }

}
