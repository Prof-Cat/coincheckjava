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
    private TreeMap<String, String> fixDataType2MySQLDataTypeMap;
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
        this.fixDataType2MySQLDataTypeMap = new TreeMap<>();
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

        javaStringBuilder.append("\npublic class " + myMessage.getName() + " { \n");
        dbCreateTableStringBuilder.append("\nuse mtcrypto;\nDROP TABLE IFEXISTS " + myMessage.getName() + ";\n\n");
        dbCreateTableStringBuilder.append("CREATE TABLE " + myMessage.getName() + "( \n");

        List<MsgField> msgFields = myMessage.getFields();
        if( msgFields != null) {
            for ( MsgField xField : msgFields){
                Field yField = this.fixFieldDictionary.get(xField.getName());
                javaStringBuilder.append("\tprotected\t" + yField.getType() + "\t" + xField.getName() + "; // " + yField.getNumber() + " " + xField.getRequired() + "\n");
                javaStringBuilder.append("\tpublic\t" + yField.getType() + "\tget" + xField.getName() + "() {\n");
                javaStringBuilder.append("\t\treturn this." + xField.getName() + ";\n\t}\n");
                javaStringBuilder.append("\tpublic\tvoid\tset" + xField.getName() + " ( " + yField.getType() + " x" + xField.getName() + " ) {\n");
                javaStringBuilder.append("\t\tthis." + xField.getName() + "= x" + xField.getName() + ";\n\t}\n");

                dbCreateTableStringBuilder.append("\t" + xField.getName() + "\t" + yField.getType());
                if( xField.getRequired().equals("Y")) {
                    dbCreateTableStringBuilder.append("\tNOT");
                }
                dbCreateTableStringBuilder.append("\tNULL,\n");
            }
            javaStringBuilder.append("\t}");
            dbCreateTableStringBuilder.append("\t);\n");

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
                        String enumValue = value.getEnumValue();
                        String description = value.getDescription();
                        System.out.println("Value Enum: " + enumValue);
                        System.out.println("Value Description: " + description);
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

            myConvertor.javaClassCode("8");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
