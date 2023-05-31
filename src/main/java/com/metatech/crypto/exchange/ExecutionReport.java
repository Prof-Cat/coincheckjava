package com.metatech.crypto.exchange;

import java.util.Date;

import com.metatech.crypto.exchange.util.TagMap;

public class ExecutionReport {
    protected       char    MsgType;    // 35 Y
    public char getMsgType() { return this.MsgType; }
    public void setMsgType( char xType) {this.MsgType = xType; }
    
    protected       String  OrderID; // 37 Y
    public  String  getOrderID() {
            return this.OrderID;
    }
    public  void    setOrderID ( String xOrderID ) {
            this.OrderID= xOrderID;
    }
    protected       String  SecondaryOrderID; // 198 N
    public  String  getSecondaryOrderID() {
            return this.SecondaryOrderID;
    }
    public  void    setSecondaryOrderID ( String xSecondaryOrderID ) {
            this.SecondaryOrderID= xSecondaryOrderID;
    }
    protected       String  ClOrdID; // 11 N
    public  String  getClOrdID() {
            return this.ClOrdID;
    }
    public  void    setClOrdID ( String xClOrdID ) {
            this.ClOrdID= xClOrdID;
    }
    protected       String  OrigClOrdID; // 41 N
    public  String  getOrigClOrdID() {
            return this.OrigClOrdID;
    }
    public  void    setOrigClOrdID ( String xOrigClOrdID ) {
            this.OrigClOrdID= xOrigClOrdID;
    }
    protected       String  ClientID; // 109 N
    public  String  getClientID() {
            return this.ClientID;
    }
    public  void    setClientID ( String xClientID ) {
            this.ClientID= xClientID;
    }
    protected       String  ExecBroker; // 76 N
    public  String  getExecBroker() {
            return this.ExecBroker;
    }
    public  void    setExecBroker ( String xExecBroker ) {
            this.ExecBroker= xExecBroker;
    }
    protected       String  ListID; // 66 N
    public  String  getListID() {
            return this.ListID;
    }
    public  void    setListID ( String xListID ) {
            this.ListID= xListID;
    }
    protected       String  ExecID; // 17 Y
    public  String  getExecID() {
            return this.ExecID;
    }
    public  void    setExecID ( String xExecID ) {
            this.ExecID= xExecID;
    }
    protected       char    ExecTransType; // 20 Y
    public  char    getExecTransType() {
            return this.ExecTransType;
    }
    public  void    setExecTransType ( char xExecTransType ) {
            this.ExecTransType= xExecTransType;
    }
    protected       String  ExecRefID; // 19 N
    public  String  getExecRefID() {
            return this.ExecRefID;
    }
    public  void    setExecRefID ( String xExecRefID ) {
            this.ExecRefID= xExecRefID;
    }
    protected       char    ExecType; // 150 Y
    public  char    getExecType() {
            return this.ExecType;
    }
    public  void    setExecType ( char xExecType ) {
            this.ExecType= xExecType;
    }

    protected TagMap.OrdStatusEnum OrdStatus;
    public void setOrdStatus(TagMap.OrdStatusEnum xStatus){
        this.OrdStatus = xStatus;
    }; 
    public TagMap.OrdStatusEnum getOrStatus(){
        return this.OrdStatus;
    };   
    protected       Integer OrdRejReason; // 103 N
    public  Integer getOrdRejReason() {
            return this.OrdRejReason;
    }
    public  void    setOrdRejReason ( Integer xOrdRejReason ) {
            this.OrdRejReason= xOrdRejReason;
    }
    protected       Integer ExecRestatementReason; // 378 N
    public  Integer getExecRestatementReason() {
            return this.ExecRestatementReason;
    }
    public  void    setExecRestatementReason ( Integer xExecRestatementReason ) {
            this.ExecRestatementReason= xExecRestatementReason;
    }
    protected       String  Account; // 1 N
    public  String  getAccount() {
            return this.Account;
    }
    public  void    setAccount ( String xAccount ) {
            this.Account= xAccount;
    }
    protected       char    SettlmntTyp; // 63 N
    public  char    getSettlmntTyp() {
            return this.SettlmntTyp;
    }
    public  void    setSettlmntTyp ( char xSettlmntTyp ) {
            this.SettlmntTyp= xSettlmntTyp;
    }
    protected       Date    FutSettDate; // 64 N
    public  Date    getFutSettDate() {
            return this.FutSettDate;
    }
    public  void    setFutSettDate ( Date xFutSettDate ) {
            this.FutSettDate= xFutSettDate;
    }
    protected       String  Symbol; // 55 Y
    public  String  getSymbol() {
            return this.Symbol;
    }
    public  void    setSymbol ( String xSymbol ) {
            this.Symbol= xSymbol;
    }
    protected       String  SymbolSfx; // 65 N
    public  String  getSymbolSfx() {
            return this.SymbolSfx;
    }
    public  void    setSymbolSfx ( String xSymbolSfx ) {
            this.SymbolSfx= xSymbolSfx;
    }
    protected       String  SecurityID; // 48 N
    public  String  getSecurityID() {
            return this.SecurityID;
    }
    public  void    setSecurityID ( String xSecurityID ) {
            this.SecurityID= xSecurityID;
    }
    protected       String  IDSource; // 22 N
    public  String  getIDSource() {
            return this.IDSource;
    }
    public  void    setIDSource ( String xIDSource ) {
            this.IDSource= xIDSource;
    }
    protected       String  SecurityType; // 167 N
    public  String  getSecurityType() {
            return this.SecurityType;
    }
    public  void    setSecurityType ( String xSecurityType ) {
            this.SecurityType= xSecurityType;
    }
    protected       String    MaturityMonthYear; // 200 N
    public  String    getMaturityMonthYear() {
            return this.MaturityMonthYear;
    }
    public  void    setMaturityMonthYear ( String xMaturityMonthYear ) {
            this.MaturityMonthYear= xMaturityMonthYear;
    }
    protected       String    MaturityDay; // 205 N
    public  String    getMaturityDay() {
            return this.MaturityDay;
    }
    public  void    setMaturityDay ( String xMaturityDay ) {
            this.MaturityDay= xMaturityDay;
    }
    protected       Integer PutOrCall; // 201 N
    public  Integer getPutOrCall() {
            return this.PutOrCall;
    }
    public  void    setPutOrCall ( Integer xPutOrCall ) {
            this.PutOrCall= xPutOrCall;
    }
    protected       Double  StrikePrice; // 202 N
    public  Double  getStrikePrice() {
            return this.StrikePrice;
    }
    public  void    setStrikePrice ( Double xStrikePrice ) {
            this.StrikePrice= xStrikePrice;
    }
    protected       char    OptAttribute; // 206 N
    public  char    getOptAttribute() {
            return this.OptAttribute;
    }
    public  void    setOptAttribute ( char xOptAttribute ) {
            this.OptAttribute= xOptAttribute;
    }
    protected       Float   ContractMultiplier; // 231 N
    public  Float   getContractMultiplier() {
            return this.ContractMultiplier;
    }
    public  void    setContractMultiplier ( Float xContractMultiplier ) {
            this.ContractMultiplier= xContractMultiplier;
    }
    protected       Float   CouponRate; // 223 N
    public  Float   getCouponRate() {
            return this.CouponRate;
    }
    public  void    setCouponRate ( Float xCouponRate ) {
            this.CouponRate= xCouponRate;
    }
    protected       String  SecurityExchange; // 207 N
    public  String  getSecurityExchange() {
            return this.SecurityExchange;
    }
    public  void    setSecurityExchange ( String xSecurityExchange ) {
            this.SecurityExchange= xSecurityExchange;
    }
    protected       String  Issuer; // 106 N
    public  String  getIssuer() {
            return this.Issuer;
    }
    public  void    setIssuer ( String xIssuer ) {
            this.Issuer= xIssuer;
    }
    protected       Integer EncodedIssuerLen; // 348 N
    public  Integer getEncodedIssuerLen() {
            return this.EncodedIssuerLen;
    }
    public  void    setEncodedIssuerLen ( Integer xEncodedIssuerLen ) {
            this.EncodedIssuerLen= xEncodedIssuerLen;
    }
    protected       String    EncodedIssuer; // 349 N
    public  String    getEncodedIssuer() {
            return this.EncodedIssuer;
    }
    public  void    setEncodedIssuer ( String xEncodedIssuer ) {
            this.EncodedIssuer= xEncodedIssuer;
    }
    protected       String  SecurityDesc; // 107 N
    public  String  getSecurityDesc() {
            return this.SecurityDesc;
    }
    public  void    setSecurityDesc ( String xSecurityDesc ) {
            this.SecurityDesc= xSecurityDesc;
    }
    protected       Integer EncodedSecurityDescLen; // 350 N
    public  Integer getEncodedSecurityDescLen() {
            return this.EncodedSecurityDescLen;
    }
    public  void    setEncodedSecurityDescLen ( Integer xEncodedSecurityDescLen ) {
            this.EncodedSecurityDescLen= xEncodedSecurityDescLen;
    }
    protected       String    EncodedSecurityDesc; // 351 N
    public  String    getEncodedSecurityDesc() {
            return this.EncodedSecurityDesc;
    }
    public  void    setEncodedSecurityDesc ( String xEncodedSecurityDesc ) {
            this.EncodedSecurityDesc= xEncodedSecurityDesc;
    }
    protected       char    Side; // 54 Y
    public  char    getSide() {
            return this.Side;
    }
    public  void    setSide ( char xSide ) {
            this.Side= xSide;
    }
    protected       Integer OrderQty; // 38 N
    public  Integer getOrderQty() {
            return this.OrderQty;
    }
    public  void    setOrderQty ( Integer xOrderQty ) {
            this.OrderQty= xOrderQty;
    }
    protected       Integer CashOrderQty; // 152 N
    public  Integer getCashOrderQty() {
            return this.CashOrderQty;
    }
    public  void    setCashOrderQty ( Integer xCashOrderQty ) {
            this.CashOrderQty= xCashOrderQty;
    }
    protected       char    OrdType; // 40 N
    public  char    getOrdType() {
            return this.OrdType;
    }
    public  void    setOrdType ( char xOrdType ) {
            this.OrdType= xOrdType;
    }
    protected       Double  Price; // 44 N
    public  Double  getPrice() {
            return this.Price;
    }
    public  void    setPrice ( Double xPrice ) {
            this.Price= xPrice;
    }
    protected       Double  StopPx; // 99 N
    public  Double  getStopPx() {
            return this.StopPx;
    }
    public  void    setStopPx ( Double xStopPx ) {
            this.StopPx= xStopPx;
    }
    protected       Double  PegDifference; // 211 N
    public  Double  getPegDifference() {
            return this.PegDifference;
    }
    public  void    setPegDifference ( Double xPegDifference ) {
            this.PegDifference= xPegDifference;
    }
    protected       char    DiscretionInst; // 388 N
    public  char    getDiscretionInst() {
            return this.DiscretionInst;
    }
    public  void    setDiscretionInst ( char xDiscretionInst ) {
            this.DiscretionInst= xDiscretionInst;
    }
    protected       Double  DiscretionOffset; // 389 N
    public  Double  getDiscretionOffset() {
            return this.DiscretionOffset;
    }
    public  void    setDiscretionOffset ( Double xDiscretionOffset ) {
            this.DiscretionOffset= xDiscretionOffset;
    }
    protected       String  Currency; // 15 N
    public  String  getCurrency() {
            return this.Currency;
    }
    public  void    setCurrency ( String xCurrency ) {
            this.Currency= xCurrency;
    }
    protected       String  ComplianceID; // 376 N
    public  String  getComplianceID() {
            return this.ComplianceID;
    }
    public  void    setComplianceID ( String xComplianceID ) {
            this.ComplianceID= xComplianceID;
    }
    protected       Boolean SolicitedFlag; // 377 N
    public  Boolean getSolicitedFlag() {
            return this.SolicitedFlag;
    }
    public  void    setSolicitedFlag ( Boolean xSolicitedFlag ) {
            this.SolicitedFlag= xSolicitedFlag;
    }
    protected       char    TimeInForce; // 59 N
    public  char    getTimeInForce() {
            return this.TimeInForce;
    }
    public  void    setTimeInForce ( char xTimeInForce ) {
            this.TimeInForce= xTimeInForce;
    }
    protected       Date    EffectiveTime; // 168 N
    public  Date    getEffectiveTime() {
            return this.EffectiveTime;
    }
    public  void    setEffectiveTime ( Date xEffectiveTime ) {
            this.EffectiveTime= xEffectiveTime;
    }
    protected       Date    ExpireDate; // 432 N
    public  Date    getExpireDate() {
            return this.ExpireDate;
    }
    public  void    setExpireDate ( Date xExpireDate ) {
            this.ExpireDate= xExpireDate;
    }
    protected       Date    ExpireTime; // 126 N
    public  Date    getExpireTime() {
            return this.ExpireTime;
    }
    public  void    setExpireTime ( Date xExpireTime ) {
            this.ExpireTime= xExpireTime;
    }
    protected       String    ExecInst; // 18 N
    public  String    getExecInst() {
            return this.ExecInst;
    }
    public  void    setExecInst ( String xExecInst ) {
            this.ExecInst= xExecInst;
    }
    protected       char    Rule80A; // 47 N
    public  char    getRule80A() {
            return this.Rule80A;
    }
    public  void    setRule80A ( char xRule80A ) {
            this.Rule80A= xRule80A;
    }
    protected       Integer LastShares; // 32 N
    public  Integer getLastShares() {
            return this.LastShares;
    }
    public  void    setLastShares ( Integer xLastShares ) {
            this.LastShares= xLastShares;
    }
    protected       Double  LastPx; // 31 N
    public  Double  getLastPx() {
            return this.LastPx;
    }
    public  void    setLastPx ( Double xLastPx ) {
            this.LastPx= xLastPx;
    }
    protected       Double  LastSpotRate; // 194 N
    public  Double  getLastSpotRate() {
            return this.LastSpotRate;
    }
    public  void    setLastSpotRate ( Double xLastSpotRate ) {
            this.LastSpotRate= xLastSpotRate;
    }
    protected       Double  LastForwardPoints; // 195 N
    public  Double  getLastForwardPoints() {
            return this.LastForwardPoints;
    }
    public  void    setLastForwardPoints ( Double xLastForwardPoints ) {
            this.LastForwardPoints= xLastForwardPoints;
    }
    protected       String  LastMkt; // 30 N
    public  String  getLastMkt() {
            return this.LastMkt;
    }
    public  void    setLastMkt ( String xLastMkt ) {
            this.LastMkt= xLastMkt;
    }
    protected       String  TradingSessionID; // 336 N
    public  String  getTradingSessionID() {
            return this.TradingSessionID;
    }
    public  void    setTradingSessionID ( String xTradingSessionID ) {
            this.TradingSessionID= xTradingSessionID;
    }
    protected       char    LastCapacity; // 29 N
    public  char    getLastCapacity() {
            return this.LastCapacity;
    }
    public  void    setLastCapacity ( char xLastCapacity ) {
            this.LastCapacity= xLastCapacity;
    }
    protected       Integer LeavesQty; // 151 Y
    public  Integer getLeavesQty() {
            return this.LeavesQty;
    }
    public  void    setLeavesQty ( Integer xLeavesQty ) {
            this.LeavesQty= xLeavesQty;
    }
    protected       Integer CumQty; // 14 Y
    public  Integer getCumQty() {
            return this.CumQty;
    }
    public  void    setCumQty ( Integer xCumQty ) {
            this.CumQty= xCumQty;
    }
    protected       Double  AvgPx; // 6 Y
    public  Double  getAvgPx() {
            return this.AvgPx;
    }
    public  void    setAvgPx ( Double xAvgPx ) {
            this.AvgPx= xAvgPx;
    }
    protected       Integer DayOrderQty; // 424 N
    public  Integer getDayOrderQty() {
            return this.DayOrderQty;
    }
    public  void    setDayOrderQty ( Integer xDayOrderQty ) {
            this.DayOrderQty= xDayOrderQty;
    }
    protected       Integer DayCumQty; // 425 N
    public  Integer getDayCumQty() {
            return this.DayCumQty;
    }
    public  void    setDayCumQty ( Integer xDayCumQty ) {
            this.DayCumQty= xDayCumQty;
    }
    protected       Double  DayAvgPx; // 426 N
    public  Double  getDayAvgPx() {
            return this.DayAvgPx;
    }
    public  void    setDayAvgPx ( Double xDayAvgPx ) {
            this.DayAvgPx= xDayAvgPx;
    }
    protected       Integer GTBookingInst; // 427 N
    public  Integer getGTBookingInst() {
            return this.GTBookingInst;
    }
    public  void    setGTBookingInst ( Integer xGTBookingInst ) {
            this.GTBookingInst= xGTBookingInst;
    }
    protected       Date    TradeDate; // 75 N
    public  Date    getTradeDate() {
            return this.TradeDate;
    }
    public  void    setTradeDate ( Date xTradeDate ) {
            this.TradeDate= xTradeDate;
    }
    protected       Date    TransactTime; // 60 N
    public  Date    getTransactTime() {
            return this.TransactTime;
    }
    public  void    setTransactTime ( Date xTransactTime ) {
            this.TransactTime= xTransactTime;
    }
    protected       Boolean ReportToExch; // 113 N
    public  Boolean getReportToExch() {
            return this.ReportToExch;
    }
    public  void    setReportToExch ( Boolean xReportToExch ) {
            this.ReportToExch= xReportToExch;
    }
    protected       Double  Commission; // 12 N
    public  Double  getCommission() {
            return this.Commission;
    }
    public  void    setCommission ( Double xCommission ) {
            this.Commission= xCommission;
    }
    protected       char    CommType; // 13 N
    public  char    getCommType() {
            return this.CommType;
    }
    public  void    setCommType ( char xCommType ) {
            this.CommType= xCommType;
    }
    protected       Double  GrossTradeAmt; // 381 N
    public  Double  getGrossTradeAmt() {
            return this.GrossTradeAmt;
    }
    public  void    setGrossTradeAmt ( Double xGrossTradeAmt ) {
            this.GrossTradeAmt= xGrossTradeAmt;
    }
    protected       Double  SettlCurrAmt; // 119 N
    public  Double  getSettlCurrAmt() {
            return this.SettlCurrAmt;
    }
    public  void    setSettlCurrAmt ( Double xSettlCurrAmt ) {
            this.SettlCurrAmt= xSettlCurrAmt;
    }
    protected       String  SettlCurrency; // 120 N
    public  String  getSettlCurrency() {
            return this.SettlCurrency;
    }
    public  void    setSettlCurrency ( String xSettlCurrency ) {
            this.SettlCurrency= xSettlCurrency;
    }
    protected       Float   SettlCurrFxRate; // 155 N
    public  Float   getSettlCurrFxRate() {
            return this.SettlCurrFxRate;
    }
    public  void    setSettlCurrFxRate ( Float xSettlCurrFxRate ) {
            this.SettlCurrFxRate= xSettlCurrFxRate;
    }
    protected       char    SettlCurrFxRateCalc; // 156 N
    public  char    getSettlCurrFxRateCalc() {
            return this.SettlCurrFxRateCalc;
    }
    public  void    setSettlCurrFxRateCalc ( char xSettlCurrFxRateCalc ) {
            this.SettlCurrFxRateCalc= xSettlCurrFxRateCalc;
    }
    protected       char    HandlInst; // 21 N
    public  char    getHandlInst() {
            return this.HandlInst;
    }
    public  void    setHandlInst ( char xHandlInst ) {
            this.HandlInst= xHandlInst;
    }
    protected       Integer MinQty; // 110 N
    public  Integer getMinQty() {
            return this.MinQty;
    }
    public  void    setMinQty ( Integer xMinQty ) {
            this.MinQty= xMinQty;
    }
    protected       Integer MaxFloor; // 111 N
    public  Integer getMaxFloor() {
            return this.MaxFloor;
    }
    public  void    setMaxFloor ( Integer xMaxFloor ) {
            this.MaxFloor= xMaxFloor;
    }
    protected       char    OpenClose; // 77 N
    public  char    getOpenClose() {
            return this.OpenClose;
    }
    public  void    setOpenClose ( char xOpenClose ) {
            this.OpenClose= xOpenClose;
    }
    protected       Integer MaxShow; // 210 N
    public  Integer getMaxShow() {
            return this.MaxShow;
    }
    public  void    setMaxShow ( Integer xMaxShow ) {
            this.MaxShow= xMaxShow;
    }
    protected       String  Text; // 58 N
    public  String  getText() {
            return this.Text;
    }
    public  void    setText ( String xText ) {
            this.Text= xText;
    }
    protected       Integer EncodedTextLen; // 354 N
    public  Integer getEncodedTextLen() {
            return this.EncodedTextLen;
    }
    public  void    setEncodedTextLen ( Integer xEncodedTextLen ) {
            this.EncodedTextLen= xEncodedTextLen;
    }
    protected       String    EncodedText; // 355 N
    public  String    getEncodedText() {
            return this.EncodedText;
    }
    public  void    setEncodedText ( String xEncodedText ) {
            this.EncodedText= xEncodedText;
    }
    protected       Date    FutSettDate2; // 193 N
    public  Date    getFutSettDate2() {
            return this.FutSettDate2;
    }
    public  void    setFutSettDate2 ( Date xFutSettDate2 ) {
            this.FutSettDate2= xFutSettDate2;
    }
    protected       Integer OrderQty2; // 192 N
    public  Integer getOrderQty2() {
            return this.OrderQty2;
    }
    public  void    setOrderQty2 ( Integer xOrderQty2 ) {
            this.OrderQty2= xOrderQty2;
    }
    protected       String  ClearingFirm; // 439 N
    public  String  getClearingFirm() {
            return this.ClearingFirm;
    }
    public  void    setClearingFirm ( String xClearingFirm ) {
            this.ClearingFirm= xClearingFirm;
    }
    protected       String  ClearingAccount; // 440 N
    public  String  getClearingAccount() {
            return this.ClearingAccount;
    }
    public  void    setClearingAccount ( String xClearingAccount ) {
            this.ClearingAccount= xClearingAccount;
    }
    protected       char    MultiLegReportingType; // 442 N
    public  char    getMultiLegReportingType() {
            return this.MultiLegReportingType;
    }
    public  void    setMultiLegReportingType ( char xMultiLegReportingType ) {
            this.MultiLegReportingType= xMultiLegReportingType;
    }
}
