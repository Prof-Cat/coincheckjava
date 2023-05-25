package com.metatech.crypto.exchange.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;
import com.metatech.JavaCat.Testslf4j;
import org.slf4j.Logger;

// @XmlRootElement(name = "fix")
@XmlRootElement(name = "messages")
// @XmlElementWrapper(name = "messages")
@XmlAccessorType(XmlAccessType.FIELD)
public class FixMessages {
    private List<FixMessage> messageList;

    @XmlElement(name = "message")
    public List<FixMessage> getmessageList() {
        return messageList;
    }

    public void setmessageList(List<FixMessage> messageList) {
        this.messageList = messageList;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class FixMessage {
    @XmlAttribute
    private String msgtype;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String msgcat;

    @XmlElement(name = "field")
    private List<MsgField> fieldList;

    // Getters and setters for the fields

    public String getMsgType() {
        return this.msgtype;
    }

    public void setMsgType(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MsgField> getFields() {
        return fieldList;
    }

    public void setFields(List<MsgField> fieldList) {
        this.fieldList = fieldList;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class MsgField {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String required;

    // Getters and setters for the fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}
