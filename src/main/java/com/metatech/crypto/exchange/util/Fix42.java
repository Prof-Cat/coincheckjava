package com.metatech.crypto.exchange.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;
import com.metatech.crypto.exchange.*;

@XmlRootElement(name = "fix")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fix42 {
    @XmlAttribute
    private int major;

    @XmlAttribute
    private int minor;

    private Header header;
    private Trailer trailer;

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private List<Message> messages;

    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field")
    private List<Field> fields;

    // Getters and setters for the fields

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class Header {
    @XmlElement(name = "field")
    private List<Field> fields;

    // Getters and setters for the fields

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class Trailer {
    @XmlElement(name = "field")
    private List<Field> fields;

    // Getters and setters for the fields

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class Message {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String msgtype;

    @XmlAttribute
    private String msgcat;

    @XmlElement(name = "field")
    private List<Field> fields;

    // Getters and setters for the fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgcat() {
        return msgcat;
    }

    public void setMsgcat(String msgcat) {
        this.msgcat = msgcat;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class Field {
    @XmlAttribute
    private int number;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String type;

    @XmlElementWrapper(name = "value")
    @XmlElement(name = "value")
    private List<Value> values;

    // Getters and setters for the fields

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class Value {
    @XmlAttribute
    private String enumValue;

    @XmlAttribute
    private String description;

    // Getters and setters for the fields

    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
