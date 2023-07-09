package com.metatech.crypto.exchange.util;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;
import java.util.TreeMap;

import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

public class FixClassConvertor {

    private static final Logger logger = Testslf4j.getLogger(FixClassConvertor.class);
    private TreeMap<String, Message> fixMessageDictionary;
    private TreeMap<String, Field> fixFieldDictionary;
    private TreeMap<String, String> fixDataType2JavaDataTypeMap;
    private TreeMap<String, String> fixDataType2MySqlDataTypeMap;
    private File xmlFile;
    private String xmlFileName = "/Users/jamesqu/github/coincheckjava/src/main/java/com/metatech/crypto/exchange/resources/FIX42.xml";
    private Fix42 fix42Dictionary;
    private List<Field> fixFields;
    private List<Message> fixMessages;
    private String javaClassString;
    private String dbCreateTableString;

    public FixClassConvertor(){
        this.fixMessageDictionary = new TreeMap<>();
        this.fixFieldDictionary = new TreeMap<>();
        this.fixDataType2JavaDataTypeMap = new TreeMap<>();
        this.fixDataType2MySqlDataTypeMap = new TreeMap<>();
        xmlFile = new File(xmlFileName);

        this.fixDataType2JavaDataTypeMap.put("STRING","String");
        this.fixDataType2JavaDataTypeMap.put("CHAR","char");
		this.fixDataType2JavaDataTypeMap.put("INT","Integer");
		this.fixDataType2JavaDataTypeMap.put("LENGTH","Integer");
		this.fixDataType2JavaDataTypeMap.put("QTY","Integer");
		this.fixDataType2JavaDataTypeMap.put("BOOLEAN","Boolean");
		this.fixDataType2JavaDataTypeMap.put("FLOAT","Float");
		this.fixDataType2JavaDataTypeMap.put("PRICE","Double");
		this.fixDataType2JavaDataTypeMap.put("PRICEOFFSET","Double");
		this.fixDataType2JavaDataTypeMap.put("AMT","Double");
		this.fixDataType2JavaDataTypeMap.put("PERCENTAGE","Double");
		this.fixDataType2JavaDataTypeMap.put("SEQNUM","Integer");
		this.fixDataType2JavaDataTypeMap.put("NUMINGROUP","Integer");
		this.fixDataType2JavaDataTypeMap.put("CURRENCY","String");
		this.fixDataType2JavaDataTypeMap.put("EXCHANGE","String");
		this.fixDataType2JavaDataTypeMap.put("UTCTIMESTAMP","Date");
		this.fixDataType2JavaDataTypeMap.put("UTCTIMEONLY","Date");
		this.fixDataType2JavaDataTypeMap.put("UTCDATEONLY","Date");
		this.fixDataType2JavaDataTypeMap.put("LOCALMKTDATE","Date");
		this.fixDataType2JavaDataTypeMap.put("TZTIMEONLY","Date");
		this.fixDataType2JavaDataTypeMap.put("TZTIMESTAMP","Date");
		this.fixDataType2JavaDataTypeMap.put("MONTHYEAR","String");
        this.fixDataType2JavaDataTypeMap.put("DATA","String");
        this.fixDataType2JavaDataTypeMap.put("MULTIPLEVALUESTRING","String");
        this.fixDataType2JavaDataTypeMap.put("DAYOFMONTH","String");

		this.fixDataType2MySqlDataTypeMap.put("STRING","VARCHAR");
		this.fixDataType2MySqlDataTypeMap.put("CHAR","CHAR");
		this.fixDataType2MySqlDataTypeMap.put("INT","INT");
		this.fixDataType2MySqlDataTypeMap.put("LENGTH","INT");
		this.fixDataType2MySqlDataTypeMap.put("QTY","INT");
		this.fixDataType2MySqlDataTypeMap.put("BOOLEAN","INT");
		this.fixDataType2MySqlDataTypeMap.put("FLOAT","DOUBLE");
		this.fixDataType2MySqlDataTypeMap.put("PRICE","DOUBLE");
		this.fixDataType2MySqlDataTypeMap.put("PRICEOFFSET","DOUBLE");
		this.fixDataType2MySqlDataTypeMap.put("AMT","DOUBLE");
		this.fixDataType2MySqlDataTypeMap.put("PERCENTAGE","DOUBLE");
		this.fixDataType2MySqlDataTypeMap.put("SEQNUM","INT");
		this.fixDataType2MySqlDataTypeMap.put("NUMINGROUP","INT");
		this.fixDataType2MySqlDataTypeMap.put("CURRENCY","VARCHAR");
		this.fixDataType2MySqlDataTypeMap.put("EXCHANGE","VARCHAR");
		this.fixDataType2MySqlDataTypeMap.put("UTCTIMESTAMP","DATETIME");
		this.fixDataType2MySqlDataTypeMap.put("UTCTIMEONLY","TIME");
		this.fixDataType2MySqlDataTypeMap.put("UTCDATEONLY","DATE");
		this.fixDataType2MySqlDataTypeMap.put("LOCALMKTDATE","DATE");
		this.fixDataType2MySqlDataTypeMap.put("TZTIMEONLY","TIME");
		this.fixDataType2MySqlDataTypeMap.put("TZTIMESTAMP","DATETIME");
		this.fixDataType2MySqlDataTypeMap.put("MONTHYEAR","VARCHAR");
        this.fixDataType2MySqlDataTypeMap.put("DATA","VARCHAR");
        this.fixDataType2MySqlDataTypeMap.put("MULTIPLEVALUESTRING","VARCHAR");
        this.fixDataType2MySqlDataTypeMap.put("DAYOFMONTH","VARCHAR");
    }

    public String getJavaDataType(String fixDataType){
        return this.fixDataType2JavaDataTypeMap.get(fixDataType);
    }
    public String getMySQLDataType(String fixDataType){
        return this.fixDataType2MySqlDataTypeMap.get(fixDataType);
    }

    public void init(){
        try {
            JAXBContext fixContext = JAXBContext.newInstance(Fix42.class);
            Unmarshaller fixUnmarshaller = fixContext.createUnmarshaller();
            fix42Dictionary = (Fix42) fixUnmarshaller.unmarshal(xmlFile);
            fixFields = fix42Dictionary.getFields();
            fixMessages = fix42Dictionary.getMessages();    
            for (Field field : fixFields ) {
                fixFieldDictionary.put(field.getName(), field);
            }
            for (Message message : fixMessages) {
                fixMessageDictionary.put(message.getMsgtype(), message);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String javaClassCode(String msgType){
        Message myMessage = this.fixMessageDictionary.get(msgType);
        StringBuilder javaStringBuilder = new StringBuilder();
        StringBuilder dbCreateTableStringBuilder = new StringBuilder();

        logger.info("Fix message : " + myMessage.getName() + " msgType " + msgType + "  categaory " + myMessage.getMsgcat());

        javaStringBuilder.append("\npublic class " + myMessage.getName() + " { \n\tprotected char MsgType; // 35 \n\tpublic char getMsgType() { return this.MsgType; }\n\tpublic void setMsgType( chat xType ) { this.MsgType = xType;}\n\n");
        dbCreateTableStringBuilder.append("\nuse mtcrypto;\nDROP TABLE IFEXISTS " + myMessage.getName() + ";\n\n");
        dbCreateTableStringBuilder.append("CREATE TABLE " + myMessage.getName() + "( \n\tMsgType\tCHAR NOT NULL,\n\tTheDate VARCHAR NOT NULL,\n");

        List<MsgField> msgFields = myMessage.getFields();
        if( msgFields != null) {
            for ( MsgField xField : msgFields){
                Field yField = this.fixFieldDictionary.get(xField.getName());
                javaStringBuilder.append("\tprotected\t" + this.getJavaDataType(yField.getType()) + "\t" + xField.getName() + "; // " + yField.getNumber() + " " + xField.getRequired() + "\n");
                javaStringBuilder.append("\tpublic\t" + this.getJavaDataType(yField.getType()) + "\tget" + xField.getName() + "() {\n");
                javaStringBuilder.append("\t\treturn this." + xField.getName() + ";\n\t}\n");
                javaStringBuilder.append("\tpublic\tvoid\tset" + xField.getName() + " ( " + this.getJavaDataType(yField.getType()) + " x" + xField.getName() + " ) {\n");
                javaStringBuilder.append("\t\tthis." + xField.getName() + "= x" + xField.getName() + ";\n\t}\n");

                dbCreateTableStringBuilder.append("\t" + xField.getName() + "\t" + this.getMySQLDataType(yField.getType()));
                if( xField.getRequired().equals("Y")) {
                    dbCreateTableStringBuilder.append("\tNOT");
                }
                dbCreateTableStringBuilder.append("\tNULL,\n");
            }
            javaStringBuilder.append("}");
            dbCreateTableStringBuilder.append("\n\n\tPRIMARY KEY ( XXX, TheDate);\n )\n");

            this.javaClassString = javaStringBuilder.toString();
            logger.info(this.javaClassString);
            this.dbCreateTableString = dbCreateTableStringBuilder.toString();
            logger.info(this.dbCreateTableString);
        }

        return "java code";
    }
    public static void main(String[] args) {
        try {
            FixClassConvertor myConvertor = new FixClassConvertor();
            myConvertor.init();

            for (Field field : myConvertor.fixFields ) {
                List<Value> values = field.getValues();

                System.out.println("Field Number: " + field.getNumber());
                System.out.println("Field Name: " + field.getName());
                System.out.println("Field Type: " + field.getType());

                if (values != null) {
                    for (Value value : values) {
                        String enumValue = value.getEnumX();
                        String description = value.getDescription();
                        // Java static data definition
                        if( enumValue.length() > 1){
                            logger.info("public static String " + field.getName().toUpperCase() + enumValue + "= \"" + enumValue + "\";");
                        } else {
                            logger.info("public static char " + field.getName().toUpperCase() + enumValue + "= \"" + enumValue + "\";");
                        }
                        logger.info("public static String " + field.getName().toUpperCase() + enumValue + "NAME = \"" + description + "\";");
                    }
                }

                System.out.println("-------------------");
            }

            System.out.println("=============================");
            for ( Message xMessage : myConvertor.fixMessages ){
                String msgType = xMessage.getMsgtype();
                String msgName = xMessage.getName();
                logger.info("Fix message : " + msgName + " msgType " + msgType + "  categaory " + xMessage.getMsgcat());
                List<MsgField> msgFields = xMessage.getFields();

                if( msgFields != null) {
                    for ( MsgField xField : msgFields){
                        Field yField = myConvertor.fixFieldDictionary.get(xField.getName());
                        logger.info(" <" + xField.getRequired() + "> " + xField.getName() + " " + yField.getNumber() + " " + yField.getType());
                    }
                }
            }


// FIX42.xml:    <message name="MarketDataRequest" msgtype="V" msgcat="app">
// FIX42.xml:    <message name="MarketDataSnapshotFullRefresh" msgtype="W" msgcat="app">
            //myConvertor.javaClassCode("W");
// FIX42.xml:    <message name="MarketDataIncrementalRefresh" msgtype="X" msgcat="app">
// FIX42.xml:    <message name="MarketDataRequestReject" msgtype="Y" msgcat="app">
// FIX42.xml:    <message name="SecurityDefinitionRequest" msgtype="c" msgcat="app">
// FIX42.xml:    <message name="SecurityDefinition" msgtype="d" msgcat="app">
// FIX42.xml:    <message name="SecurityStatusRequest" msgtype="e" msgcat="app">
// FIX42.xml:    <message name="SecurityStatus" msgtype="f" msgcat="app">
// FIX42.xml:    <message name="TradingSessionStatusRequest" msgtype="g" msgcat="app">
// FIX42.xml:    <message name="TradingSessionStatus" msgtype="h" msgcat="app">

// FIX42.xml:    <message name="NewOrderSingle" msgtype="D" msgcat="app">
// FIX42.xml:    <message name="OrderCancelReplaceRequest" msgtype="G" msgcat="app">
// FIX42.xml:    <message name="OrderCancelRequest" msgtype="F" msgcat="app">
            //myConvertor.javaClassCode("G");
// FIX42.xml:    <message name="ExecutionReport" msgtype="8" msgcat="app">
            //myConvertor.javaClassCode("8");

// FIX42.xml:    <message name="DontKnowTrade" msgtype="Q" msgcat="app">
            //myConvertor.javaClassCode("Q");

// FIX42.xml:    <message name="OrderCancelReject" msgtype="9" msgcat="app">
            //myConvertor.javaClassCode("9");
// FIX42.xml:    <message name="OrderStatusRequest" msgtype="H" msgcat="app">


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
