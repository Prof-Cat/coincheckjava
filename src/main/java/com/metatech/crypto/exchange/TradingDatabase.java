package com.metatech.crypto.exchange;

public class TradingDatabase {
    // Get the database information
    // Element database = (Element) root.getElementsByTagName("database").item(0);
    // String host = database.getElementsByTagName("host").item(0).getTextContent();
    // String port = database.getElementsByTagName("port").item(0).getTextContent();
    // String username = database.getElementsByTagName("username").item(0).getTextContent();
    // String password = database.getElementsByTagName("password").item(0).getTextContent();
    private String myDatabase;
    private String myDBHost;
    private String myDBPort;
    private String myDBUserName;
    private String myDBPassword;

    public void TradingDatabase(){
        this.myDatabase = "";
        this.myDBHost ="";
        this.myDBPassword = "";
        this.myDBUserName = "";
        this.myDBPort = "";
    }

    public void init(String xDB, String xHost, String xPort, String xUser, String xPwd){
        this.myDatabase = xDB;
        this.myDBHost = xHost;
        this.myDBPort = xPort;
        this.myDBUserName = xUser;
        this.myDBPassword = xPwd;
    }

    public String getDatabase() { return this.myDatabase;}
    public void setDatabase( String xDB) { this.myDatabase = xDB; }
    public String getDBHost() { return this.myDBHost; }
    public void setDBHost( String xDBHost ) { this.myDBHost = xDBHost; }
    public String getDBPort() { return this.myDBPort; }
    public void setDBPort( String xDBPort ) { this.myDBPort = xDBPort; }
    
    public String getDBUserName () { return this.myDBUserName; }
    public void setDBUser( String xDBUser ) { this.myDBUserName = xDBUser; }
    public String getDBPassword() { return this.myDBPassword; }
    public void setDBPassword( String xDBPassword ) { this.myDBPassword = xDBPassword; }

}
