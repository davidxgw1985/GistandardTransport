
package com.gistandard.transport.system.webservice.client.gps;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>rptOrderTrace complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="rptOrderTrace"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acceptOrder_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrder_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="busiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="deliveryToPod_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="districtFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverMiWaFrom_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW1MiTo_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW1W2From_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W1To_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW2W3From_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="driverW3W2To_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="gicbFenceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="isNotAccept3Mins" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isNotPickup30Mins" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isNotPod3Hours" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isNotPod4Hours" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isNotW3Attime" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="miStationFrom_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationFrom_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="miStationTo_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderStatusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pickupOrder_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="podConfirmed_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pointFrom" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="popOrdered_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provinceFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationFrom_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w1StationTo_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationFrom_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w2StationTo_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_LoginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_LoginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayRentalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayRentalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayRentalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_PayUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_UserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_UserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="w3Station_tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rptOrderTrace", propOrder = {
    "acceptOrderLoginCode",
    "acceptOrderLoginName",
    "acceptOrderLoginTel",
    "acceptOrderPayAmount",
    "acceptOrderPayCurrencyCode",
    "acceptOrderPayTime",
    "acceptOrderPayUserCode",
    "acceptOrderPayUserName",
    "acceptOrderPayUserTel",
    "acceptOrderUserCode",
    "acceptOrderUserName",
    "acceptOrderUserTel",
    "acceptOrderTsActualFrom",
    "acceptOrderTsActualTo",
    "amount",
    "busiNo",
    "cityFrom",
    "currencyCode",
    "deliveryToPodLoginCode",
    "deliveryToPodLoginName",
    "deliveryToPodLoginTel",
    "deliveryToPodPayAmount",
    "deliveryToPodPayCurrencyCode",
    "deliveryToPodPayTime",
    "deliveryToPodPayUserCode",
    "deliveryToPodPayUserName",
    "deliveryToPodPayUserTel",
    "deliveryToPodUserCode",
    "deliveryToPodUserName",
    "deliveryToPodUserTel",
    "deliveryToPodTsActualFrom",
    "deliveryToPodTsActualTo",
    "districtFrom",
    "driverMiWaFromLoginCode",
    "driverMiWaFromLoginName",
    "driverMiWaFromLoginTel",
    "driverMiWaFromPayAmount",
    "driverMiWaFromPayCurrencyCode",
    "driverMiWaFromPayTime",
    "driverMiWaFromPayUserCode",
    "driverMiWaFromPayUserName",
    "driverMiWaFromPayUserTel",
    "driverMiWaFromUserCode",
    "driverMiWaFromUserName",
    "driverMiWaFromUserTel",
    "driverMiWaFromTsActualFrom",
    "driverMiWaFromTsActualTo",
    "driverW1MiToLoginCode",
    "driverW1MiToLoginName",
    "driverW1MiToLoginTel",
    "driverW1MiToPayAmount",
    "driverW1MiToPayCurrencyCode",
    "driverW1MiToPayTime",
    "driverW1MiToPayUserCode",
    "driverW1MiToPayUserName",
    "driverW1MiToPayUserTel",
    "driverW1MiToUserCode",
    "driverW1MiToUserName",
    "driverW1MiToUserTel",
    "driverW1MiToTsActualFrom",
    "driverW1MiToTsActualTo",
    "driverW1W2FromLoginCode",
    "driverW1W2FromLoginName",
    "driverW1W2FromLoginTel",
    "driverW1W2FromPayAmount",
    "driverW1W2FromPayCurrencyCode",
    "driverW1W2FromPayTime",
    "driverW1W2FromPayUserCode",
    "driverW1W2FromPayUserName",
    "driverW1W2FromPayUserTel",
    "driverW1W2FromUserCode",
    "driverW1W2FromUserName",
    "driverW1W2FromUserTel",
    "driverW1W2FromTsActualFrom",
    "driverW1W2FromTsActualTo",
    "driverW2W1ToLoginCode",
    "driverW2W1ToLoginName",
    "driverW2W1ToLoginTel",
    "driverW2W1ToPayAmount",
    "driverW2W1ToPayCurrencyCode",
    "driverW2W1ToPayTime",
    "driverW2W1ToPayUserCode",
    "driverW2W1ToPayUserName",
    "driverW2W1ToPayUserTel",
    "driverW2W1ToUserCode",
    "driverW2W1ToUserName",
    "driverW2W1ToUserTel",
    "driverW2W1ToTsActualFrom",
    "driverW2W1ToTsActualTo",
    "driverW2W3FromLoginCode",
    "driverW2W3FromLoginName",
    "driverW2W3FromLoginTel",
    "driverW2W3FromPayAmount",
    "driverW2W3FromPayCurrencyCode",
    "driverW2W3FromPayTime",
    "driverW2W3FromPayUserCode",
    "driverW2W3FromPayUserName",
    "driverW2W3FromPayUserTel",
    "driverW2W3FromUserCode",
    "driverW2W3FromUserName",
    "driverW2W3FromUserTel",
    "driverW2W3FromTsActualFrom",
    "driverW2W3FromTsActualTo",
    "driverW3W2ToLoginCode",
    "driverW3W2ToLoginName",
    "driverW3W2ToLoginTel",
    "driverW3W2ToPayAmount",
    "driverW3W2ToPayCurrencyCode",
    "driverW3W2ToPayTime",
    "driverW3W2ToPayUserCode",
    "driverW3W2ToPayUserName",
    "driverW3W2ToPayUserTel",
    "driverW3W2ToUserCode",
    "driverW3W2ToUserName",
    "driverW3W2ToUserTel",
    "driverW3W2ToTsActualFrom",
    "driverW3W2ToTsActualTo",
    "gicbFenceName",
    "goodsName",
    "goodsType",
    "grossWeight",
    "isNotAccept3Mins",
    "isNotPickup30Mins",
    "isNotPod3Hours",
    "isNotPod4Hours",
    "isNotW3Attime",
    "miStationFromLoginCode",
    "miStationFromLoginName",
    "miStationFromLoginTel",
    "miStationFromPayAmount",
    "miStationFromPayCurrencyCode",
    "miStationFromPayRentalAmount",
    "miStationFromPayRentalCurrencyCode",
    "miStationFromPayRentalTime",
    "miStationFromPayTime",
    "miStationFromPayUserCode",
    "miStationFromPayUserName",
    "miStationFromPayUserTel",
    "miStationFromUserCode",
    "miStationFromUserName",
    "miStationFromUserTel",
    "miStationFromTsActualFrom",
    "miStationFromTsActualTo",
    "miStationToLoginCode",
    "miStationToLoginName",
    "miStationToLoginTel",
    "miStationToPayAmount",
    "miStationToPayCurrencyCode",
    "miStationToPayRentalAmount",
    "miStationToPayRentalCurrencyCode",
    "miStationToPayRentalTime",
    "miStationToPayTime",
    "miStationToPayUserCode",
    "miStationToPayUserName",
    "miStationToPayUserTel",
    "miStationToUserCode",
    "miStationToUserName",
    "miStationToUserTel",
    "miStationToTsActualFrom",
    "miStationToTsActualTo",
    "orderStatus",
    "orderStatusDesc",
    "payStatus",
    "pickupOrderLoginCode",
    "pickupOrderLoginName",
    "pickupOrderLoginTel",
    "pickupOrderPayAmount",
    "pickupOrderPayCurrencyCode",
    "pickupOrderPayTime",
    "pickupOrderPayUserCode",
    "pickupOrderPayUserName",
    "pickupOrderPayUserTel",
    "pickupOrderUserCode",
    "pickupOrderUserName",
    "pickupOrderUserTel",
    "pickupOrderTsActualFrom",
    "pickupOrderTsActualTo",
    "podConfirmedLoginCode",
    "podConfirmedLoginName",
    "podConfirmedLoginTel",
    "podConfirmedPayAmount",
    "podConfirmedPayCurrencyCode",
    "podConfirmedPayTime",
    "podConfirmedPayUserCode",
    "podConfirmedPayUserName",
    "podConfirmedPayUserTel",
    "podConfirmedUserCode",
    "podConfirmedUserName",
    "podConfirmedUserTel",
    "podConfirmedTsActualFrom",
    "podConfirmedTsActualTo",
    "pointFrom",
    "popOrderedLoginCode",
    "popOrderedLoginName",
    "popOrderedLoginTel",
    "popOrderedPayAmount",
    "popOrderedPayCurrencyCode",
    "popOrderedPayTime",
    "popOrderedPayUserCode",
    "popOrderedPayUserName",
    "popOrderedPayUserTel",
    "popOrderedUserCode",
    "popOrderedUserName",
    "popOrderedUserTel",
    "popOrderedTsActualFrom",
    "popOrderedTsActualTo",
    "productType",
    "provinceFrom",
    "w1StationFromLoginCode",
    "w1StationFromLoginName",
    "w1StationFromLoginTel",
    "w1StationFromPayAmount",
    "w1StationFromPayCurrencyCode",
    "w1StationFromPayRentalAmount",
    "w1StationFromPayRentalCurrencyCode",
    "w1StationFromPayRentalTime",
    "w1StationFromPayTime",
    "w1StationFromPayUserCode",
    "w1StationFromPayUserName",
    "w1StationFromPayUserTel",
    "w1StationFromUserCode",
    "w1StationFromUserName",
    "w1StationFromUserTel",
    "w1StationFromTsActualFrom",
    "w1StationFromTsActualTo",
    "w1StationToLoginCode",
    "w1StationToLoginName",
    "w1StationToLoginTel",
    "w1StationToPayAmount",
    "w1StationToPayCurrencyCode",
    "w1StationToPayRentalAmount",
    "w1StationToPayRentalCurrencyCode",
    "w1StationToPayRentalTime",
    "w1StationToPayTime",
    "w1StationToPayUserCode",
    "w1StationToPayUserName",
    "w1StationToPayUserTel",
    "w1StationToUserCode",
    "w1StationToUserName",
    "w1StationToUserTel",
    "w1StationToTsActualFrom",
    "w1StationToTsActualTo",
    "w2StationFromLoginCode",
    "w2StationFromLoginName",
    "w2StationFromLoginTel",
    "w2StationFromPayAmount",
    "w2StationFromPayCurrencyCode",
    "w2StationFromPayRentalAmount",
    "w2StationFromPayRentalCurrencyCode",
    "w2StationFromPayRentalTime",
    "w2StationFromPayTime",
    "w2StationFromPayUserCode",
    "w2StationFromPayUserName",
    "w2StationFromPayUserTel",
    "w2StationFromUserCode",
    "w2StationFromUserName",
    "w2StationFromUserTel",
    "w2StationFromTsActualFrom",
    "w2StationFromTsActualTo",
    "w2StationToLoginCode",
    "w2StationToLoginName",
    "w2StationToLoginTel",
    "w2StationToPayAmount",
    "w2StationToPayCurrencyCode",
    "w2StationToPayRentalAmount",
    "w2StationToPayRentalCurrencyCode",
    "w2StationToPayRentalTime",
    "w2StationToPayTime",
    "w2StationToPayUserCode",
    "w2StationToPayUserName",
    "w2StationToPayUserTel",
    "w2StationToUserCode",
    "w2StationToUserName",
    "w2StationToUserTel",
    "w2StationToTsActualFrom",
    "w2StationToTsActualTo",
    "w3StationLoginCode",
    "w3StationLoginName",
    "w3StationLoginTel",
    "w3StationPayAmount",
    "w3StationPayCurrencyCode",
    "w3StationPayRentalAmount",
    "w3StationPayRentalCurrencyCode",
    "w3StationPayRentalTime",
    "w3StationPayTime",
    "w3StationPayUserCode",
    "w3StationPayUserName",
    "w3StationPayUserTel",
    "w3StationUserCode",
    "w3StationUserName",
    "w3StationUserTel",
    "w3StationTsActualFrom",
    "w3StationTsActualTo"
})
public class RptOrderTrace {

    @XmlElement(name = "acceptOrder_LoginCode")
    protected String acceptOrderLoginCode;
    @XmlElement(name = "acceptOrder_LoginName")
    protected String acceptOrderLoginName;
    @XmlElement(name = "acceptOrder_LoginTel")
    protected String acceptOrderLoginTel;
    @XmlElement(name = "acceptOrder_PayAmount")
    protected Double acceptOrderPayAmount;
    @XmlElement(name = "acceptOrder_PayCurrencyCode")
    protected String acceptOrderPayCurrencyCode;
    @XmlElement(name = "acceptOrder_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date acceptOrderPayTime;
    @XmlElement(name = "acceptOrder_PayUserCode")
    protected String acceptOrderPayUserCode;
    @XmlElement(name = "acceptOrder_PayUserName")
    protected String acceptOrderPayUserName;
    @XmlElement(name = "acceptOrder_PayUserTel")
    protected String acceptOrderPayUserTel;
    @XmlElement(name = "acceptOrder_UserCode")
    protected String acceptOrderUserCode;
    @XmlElement(name = "acceptOrder_UserName")
    protected String acceptOrderUserName;
    @XmlElement(name = "acceptOrder_UserTel")
    protected String acceptOrderUserTel;
    @XmlElement(name = "acceptOrder_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date acceptOrderTsActualFrom;
    @XmlElement(name = "acceptOrder_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date acceptOrderTsActualTo;
    protected Double amount;
    protected String busiNo;
    protected String cityFrom;
    protected String currencyCode;
    @XmlElement(name = "deliveryToPod_LoginCode")
    protected String deliveryToPodLoginCode;
    @XmlElement(name = "deliveryToPod_LoginName")
    protected String deliveryToPodLoginName;
    @XmlElement(name = "deliveryToPod_LoginTel")
    protected String deliveryToPodLoginTel;
    @XmlElement(name = "deliveryToPod_PayAmount")
    protected Double deliveryToPodPayAmount;
    @XmlElement(name = "deliveryToPod_PayCurrencyCode")
    protected String deliveryToPodPayCurrencyCode;
    @XmlElement(name = "deliveryToPod_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date deliveryToPodPayTime;
    @XmlElement(name = "deliveryToPod_PayUserCode")
    protected String deliveryToPodPayUserCode;
    @XmlElement(name = "deliveryToPod_PayUserName")
    protected String deliveryToPodPayUserName;
    @XmlElement(name = "deliveryToPod_PayUserTel")
    protected String deliveryToPodPayUserTel;
    @XmlElement(name = "deliveryToPod_UserCode")
    protected String deliveryToPodUserCode;
    @XmlElement(name = "deliveryToPod_UserName")
    protected String deliveryToPodUserName;
    @XmlElement(name = "deliveryToPod_UserTel")
    protected String deliveryToPodUserTel;
    @XmlElement(name = "deliveryToPod_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date deliveryToPodTsActualFrom;
    @XmlElement(name = "deliveryToPod_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date deliveryToPodTsActualTo;
    protected String districtFrom;
    @XmlElement(name = "driverMiWaFrom_LoginCode")
    protected String driverMiWaFromLoginCode;
    @XmlElement(name = "driverMiWaFrom_LoginName")
    protected String driverMiWaFromLoginName;
    @XmlElement(name = "driverMiWaFrom_LoginTel")
    protected String driverMiWaFromLoginTel;
    @XmlElement(name = "driverMiWaFrom_PayAmount")
    protected Double driverMiWaFromPayAmount;
    @XmlElement(name = "driverMiWaFrom_PayCurrencyCode")
    protected String driverMiWaFromPayCurrencyCode;
    @XmlElement(name = "driverMiWaFrom_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverMiWaFromPayTime;
    @XmlElement(name = "driverMiWaFrom_PayUserCode")
    protected String driverMiWaFromPayUserCode;
    @XmlElement(name = "driverMiWaFrom_PayUserName")
    protected String driverMiWaFromPayUserName;
    @XmlElement(name = "driverMiWaFrom_PayUserTel")
    protected String driverMiWaFromPayUserTel;
    @XmlElement(name = "driverMiWaFrom_UserCode")
    protected String driverMiWaFromUserCode;
    @XmlElement(name = "driverMiWaFrom_UserName")
    protected String driverMiWaFromUserName;
    @XmlElement(name = "driverMiWaFrom_UserTel")
    protected String driverMiWaFromUserTel;
    @XmlElement(name = "driverMiWaFrom_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverMiWaFromTsActualFrom;
    @XmlElement(name = "driverMiWaFrom_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverMiWaFromTsActualTo;
    @XmlElement(name = "driverW1MiTo_LoginCode")
    protected String driverW1MiToLoginCode;
    @XmlElement(name = "driverW1MiTo_LoginName")
    protected String driverW1MiToLoginName;
    @XmlElement(name = "driverW1MiTo_LoginTel")
    protected String driverW1MiToLoginTel;
    @XmlElement(name = "driverW1MiTo_PayAmount")
    protected Double driverW1MiToPayAmount;
    @XmlElement(name = "driverW1MiTo_PayCurrencyCode")
    protected String driverW1MiToPayCurrencyCode;
    @XmlElement(name = "driverW1MiTo_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW1MiToPayTime;
    @XmlElement(name = "driverW1MiTo_PayUserCode")
    protected String driverW1MiToPayUserCode;
    @XmlElement(name = "driverW1MiTo_PayUserName")
    protected String driverW1MiToPayUserName;
    @XmlElement(name = "driverW1MiTo_PayUserTel")
    protected String driverW1MiToPayUserTel;
    @XmlElement(name = "driverW1MiTo_UserCode")
    protected String driverW1MiToUserCode;
    @XmlElement(name = "driverW1MiTo_UserName")
    protected String driverW1MiToUserName;
    @XmlElement(name = "driverW1MiTo_UserTel")
    protected String driverW1MiToUserTel;
    @XmlElement(name = "driverW1MiTo_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW1MiToTsActualFrom;
    @XmlElement(name = "driverW1MiTo_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW1MiToTsActualTo;
    @XmlElement(name = "driverW1W2From_LoginCode")
    protected String driverW1W2FromLoginCode;
    @XmlElement(name = "driverW1W2From_LoginName")
    protected String driverW1W2FromLoginName;
    @XmlElement(name = "driverW1W2From_LoginTel")
    protected String driverW1W2FromLoginTel;
    @XmlElement(name = "driverW1W2From_PayAmount")
    protected Double driverW1W2FromPayAmount;
    @XmlElement(name = "driverW1W2From_PayCurrencyCode")
    protected String driverW1W2FromPayCurrencyCode;
    @XmlElement(name = "driverW1W2From_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW1W2FromPayTime;
    @XmlElement(name = "driverW1W2From_PayUserCode")
    protected String driverW1W2FromPayUserCode;
    @XmlElement(name = "driverW1W2From_PayUserName")
    protected String driverW1W2FromPayUserName;
    @XmlElement(name = "driverW1W2From_PayUserTel")
    protected String driverW1W2FromPayUserTel;
    @XmlElement(name = "driverW1W2From_UserCode")
    protected String driverW1W2FromUserCode;
    @XmlElement(name = "driverW1W2From_UserName")
    protected String driverW1W2FromUserName;
    @XmlElement(name = "driverW1W2From_UserTel")
    protected String driverW1W2FromUserTel;
    @XmlElement(name = "driverW1W2From_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW1W2FromTsActualFrom;
    @XmlElement(name = "driverW1W2From_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW1W2FromTsActualTo;
    @XmlElement(name = "driverW2W1To_LoginCode")
    protected String driverW2W1ToLoginCode;
    @XmlElement(name = "driverW2W1To_LoginName")
    protected String driverW2W1ToLoginName;
    @XmlElement(name = "driverW2W1To_LoginTel")
    protected String driverW2W1ToLoginTel;
    @XmlElement(name = "driverW2W1To_PayAmount")
    protected Double driverW2W1ToPayAmount;
    @XmlElement(name = "driverW2W1To_PayCurrencyCode")
    protected String driverW2W1ToPayCurrencyCode;
    @XmlElement(name = "driverW2W1To_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW2W1ToPayTime;
    @XmlElement(name = "driverW2W1To_PayUserCode")
    protected String driverW2W1ToPayUserCode;
    @XmlElement(name = "driverW2W1To_PayUserName")
    protected String driverW2W1ToPayUserName;
    @XmlElement(name = "driverW2W1To_PayUserTel")
    protected String driverW2W1ToPayUserTel;
    @XmlElement(name = "driverW2W1To_UserCode")
    protected String driverW2W1ToUserCode;
    @XmlElement(name = "driverW2W1To_UserName")
    protected String driverW2W1ToUserName;
    @XmlElement(name = "driverW2W1To_UserTel")
    protected String driverW2W1ToUserTel;
    @XmlElement(name = "driverW2W1To_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW2W1ToTsActualFrom;
    @XmlElement(name = "driverW2W1To_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW2W1ToTsActualTo;
    @XmlElement(name = "driverW2W3From_LoginCode")
    protected String driverW2W3FromLoginCode;
    @XmlElement(name = "driverW2W3From_LoginName")
    protected String driverW2W3FromLoginName;
    @XmlElement(name = "driverW2W3From_LoginTel")
    protected String driverW2W3FromLoginTel;
    @XmlElement(name = "driverW2W3From_PayAmount")
    protected Double driverW2W3FromPayAmount;
    @XmlElement(name = "driverW2W3From_PayCurrencyCode")
    protected String driverW2W3FromPayCurrencyCode;
    @XmlElement(name = "driverW2W3From_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW2W3FromPayTime;
    @XmlElement(name = "driverW2W3From_PayUserCode")
    protected String driverW2W3FromPayUserCode;
    @XmlElement(name = "driverW2W3From_PayUserName")
    protected String driverW2W3FromPayUserName;
    @XmlElement(name = "driverW2W3From_PayUserTel")
    protected String driverW2W3FromPayUserTel;
    @XmlElement(name = "driverW2W3From_UserCode")
    protected String driverW2W3FromUserCode;
    @XmlElement(name = "driverW2W3From_UserName")
    protected String driverW2W3FromUserName;
    @XmlElement(name = "driverW2W3From_UserTel")
    protected String driverW2W3FromUserTel;
    @XmlElement(name = "driverW2W3From_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW2W3FromTsActualFrom;
    @XmlElement(name = "driverW2W3From_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW2W3FromTsActualTo;
    @XmlElement(name = "driverW3W2To_LoginCode")
    protected String driverW3W2ToLoginCode;
    @XmlElement(name = "driverW3W2To_LoginName")
    protected String driverW3W2ToLoginName;
    @XmlElement(name = "driverW3W2To_LoginTel")
    protected String driverW3W2ToLoginTel;
    @XmlElement(name = "driverW3W2To_PayAmount")
    protected Double driverW3W2ToPayAmount;
    @XmlElement(name = "driverW3W2To_PayCurrencyCode")
    protected String driverW3W2ToPayCurrencyCode;
    @XmlElement(name = "driverW3W2To_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW3W2ToPayTime;
    @XmlElement(name = "driverW3W2To_PayUserCode")
    protected String driverW3W2ToPayUserCode;
    @XmlElement(name = "driverW3W2To_PayUserName")
    protected String driverW3W2ToPayUserName;
    @XmlElement(name = "driverW3W2To_PayUserTel")
    protected String driverW3W2ToPayUserTel;
    @XmlElement(name = "driverW3W2To_UserCode")
    protected String driverW3W2ToUserCode;
    @XmlElement(name = "driverW3W2To_UserName")
    protected String driverW3W2ToUserName;
    @XmlElement(name = "driverW3W2To_UserTel")
    protected String driverW3W2ToUserTel;
    @XmlElement(name = "driverW3W2To_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW3W2ToTsActualFrom;
    @XmlElement(name = "driverW3W2To_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date driverW3W2ToTsActualTo;
    protected String gicbFenceName;
    protected String goodsName;
    protected String goodsType;
    protected Double grossWeight;
    protected boolean isNotAccept3Mins;
    protected boolean isNotPickup30Mins;
    protected boolean isNotPod3Hours;
    protected boolean isNotPod4Hours;
    protected boolean isNotW3Attime;
    @XmlElement(name = "miStationFrom_LoginCode")
    protected String miStationFromLoginCode;
    @XmlElement(name = "miStationFrom_LoginName")
    protected String miStationFromLoginName;
    @XmlElement(name = "miStationFrom_LoginTel")
    protected String miStationFromLoginTel;
    @XmlElement(name = "miStationFrom_PayAmount")
    protected Double miStationFromPayAmount;
    @XmlElement(name = "miStationFrom_PayCurrencyCode")
    protected String miStationFromPayCurrencyCode;
    @XmlElement(name = "miStationFrom_PayRentalAmount")
    protected Double miStationFromPayRentalAmount;
    @XmlElement(name = "miStationFrom_PayRentalCurrencyCode")
    protected String miStationFromPayRentalCurrencyCode;
    @XmlElement(name = "miStationFrom_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationFromPayRentalTime;
    @XmlElement(name = "miStationFrom_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationFromPayTime;
    @XmlElement(name = "miStationFrom_PayUserCode")
    protected String miStationFromPayUserCode;
    @XmlElement(name = "miStationFrom_PayUserName")
    protected String miStationFromPayUserName;
    @XmlElement(name = "miStationFrom_PayUserTel")
    protected String miStationFromPayUserTel;
    @XmlElement(name = "miStationFrom_UserCode")
    protected String miStationFromUserCode;
    @XmlElement(name = "miStationFrom_UserName")
    protected String miStationFromUserName;
    @XmlElement(name = "miStationFrom_UserTel")
    protected String miStationFromUserTel;
    @XmlElement(name = "miStationFrom_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationFromTsActualFrom;
    @XmlElement(name = "miStationFrom_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationFromTsActualTo;
    @XmlElement(name = "miStationTo_LoginCode")
    protected String miStationToLoginCode;
    @XmlElement(name = "miStationTo_LoginName")
    protected String miStationToLoginName;
    @XmlElement(name = "miStationTo_LoginTel")
    protected String miStationToLoginTel;
    @XmlElement(name = "miStationTo_PayAmount")
    protected Double miStationToPayAmount;
    @XmlElement(name = "miStationTo_PayCurrencyCode")
    protected String miStationToPayCurrencyCode;
    @XmlElement(name = "miStationTo_PayRentalAmount")
    protected Double miStationToPayRentalAmount;
    @XmlElement(name = "miStationTo_PayRentalCurrencyCode")
    protected String miStationToPayRentalCurrencyCode;
    @XmlElement(name = "miStationTo_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationToPayRentalTime;
    @XmlElement(name = "miStationTo_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationToPayTime;
    @XmlElement(name = "miStationTo_PayUserCode")
    protected String miStationToPayUserCode;
    @XmlElement(name = "miStationTo_PayUserName")
    protected String miStationToPayUserName;
    @XmlElement(name = "miStationTo_PayUserTel")
    protected String miStationToPayUserTel;
    @XmlElement(name = "miStationTo_UserCode")
    protected String miStationToUserCode;
    @XmlElement(name = "miStationTo_UserName")
    protected String miStationToUserName;
    @XmlElement(name = "miStationTo_UserTel")
    protected String miStationToUserTel;
    @XmlElement(name = "miStationTo_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationToTsActualFrom;
    @XmlElement(name = "miStationTo_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date miStationToTsActualTo;
    protected String orderStatus;
    protected String orderStatusDesc;
    protected String payStatus;
    @XmlElement(name = "pickupOrder_LoginCode")
    protected String pickupOrderLoginCode;
    @XmlElement(name = "pickupOrder_LoginName")
    protected String pickupOrderLoginName;
    @XmlElement(name = "pickupOrder_LoginTel")
    protected String pickupOrderLoginTel;
    @XmlElement(name = "pickupOrder_PayAmount")
    protected Double pickupOrderPayAmount;
    @XmlElement(name = "pickupOrder_PayCurrencyCode")
    protected String pickupOrderPayCurrencyCode;
    @XmlElement(name = "pickupOrder_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date pickupOrderPayTime;
    @XmlElement(name = "pickupOrder_PayUserCode")
    protected String pickupOrderPayUserCode;
    @XmlElement(name = "pickupOrder_PayUserName")
    protected String pickupOrderPayUserName;
    @XmlElement(name = "pickupOrder_PayUserTel")
    protected String pickupOrderPayUserTel;
    @XmlElement(name = "pickupOrder_UserCode")
    protected String pickupOrderUserCode;
    @XmlElement(name = "pickupOrder_UserName")
    protected String pickupOrderUserName;
    @XmlElement(name = "pickupOrder_UserTel")
    protected String pickupOrderUserTel;
    @XmlElement(name = "pickupOrder_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date pickupOrderTsActualFrom;
    @XmlElement(name = "pickupOrder_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date pickupOrderTsActualTo;
    @XmlElement(name = "podConfirmed_LoginCode")
    protected String podConfirmedLoginCode;
    @XmlElement(name = "podConfirmed_LoginName")
    protected String podConfirmedLoginName;
    @XmlElement(name = "podConfirmed_LoginTel")
    protected String podConfirmedLoginTel;
    @XmlElement(name = "podConfirmed_PayAmount")
    protected Double podConfirmedPayAmount;
    @XmlElement(name = "podConfirmed_PayCurrencyCode")
    protected String podConfirmedPayCurrencyCode;
    @XmlElement(name = "podConfirmed_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date podConfirmedPayTime;
    @XmlElement(name = "podConfirmed_PayUserCode")
    protected String podConfirmedPayUserCode;
    @XmlElement(name = "podConfirmed_PayUserName")
    protected String podConfirmedPayUserName;
    @XmlElement(name = "podConfirmed_PayUserTel")
    protected String podConfirmedPayUserTel;
    @XmlElement(name = "podConfirmed_UserCode")
    protected String podConfirmedUserCode;
    @XmlElement(name = "podConfirmed_UserName")
    protected String podConfirmedUserName;
    @XmlElement(name = "podConfirmed_UserTel")
    protected String podConfirmedUserTel;
    @XmlElement(name = "podConfirmed_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date podConfirmedTsActualFrom;
    @XmlElement(name = "podConfirmed_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date podConfirmedTsActualTo;
    protected GiPoint pointFrom;
    @XmlElement(name = "popOrdered_LoginCode")
    protected String popOrderedLoginCode;
    @XmlElement(name = "popOrdered_LoginName")
    protected String popOrderedLoginName;
    @XmlElement(name = "popOrdered_LoginTel")
    protected String popOrderedLoginTel;
    @XmlElement(name = "popOrdered_PayAmount")
    protected Double popOrderedPayAmount;
    @XmlElement(name = "popOrdered_PayCurrencyCode")
    protected String popOrderedPayCurrencyCode;
    @XmlElement(name = "popOrdered_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date popOrderedPayTime;
    @XmlElement(name = "popOrdered_PayUserCode")
    protected String popOrderedPayUserCode;
    @XmlElement(name = "popOrdered_PayUserName")
    protected String popOrderedPayUserName;
    @XmlElement(name = "popOrdered_PayUserTel")
    protected String popOrderedPayUserTel;
    @XmlElement(name = "popOrdered_UserCode")
    protected String popOrderedUserCode;
    @XmlElement(name = "popOrdered_UserName")
    protected String popOrderedUserName;
    @XmlElement(name = "popOrdered_UserTel")
    protected String popOrderedUserTel;
    @XmlElement(name = "popOrdered_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date popOrderedTsActualFrom;
    @XmlElement(name = "popOrdered_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date popOrderedTsActualTo;
    protected String productType;
    protected String provinceFrom;
    @XmlElement(name = "w1StationFrom_LoginCode")
    protected String w1StationFromLoginCode;
    @XmlElement(name = "w1StationFrom_LoginName")
    protected String w1StationFromLoginName;
    @XmlElement(name = "w1StationFrom_LoginTel")
    protected String w1StationFromLoginTel;
    @XmlElement(name = "w1StationFrom_PayAmount")
    protected Double w1StationFromPayAmount;
    @XmlElement(name = "w1StationFrom_PayCurrencyCode")
    protected String w1StationFromPayCurrencyCode;
    @XmlElement(name = "w1StationFrom_PayRentalAmount")
    protected Double w1StationFromPayRentalAmount;
    @XmlElement(name = "w1StationFrom_PayRentalCurrencyCode")
    protected String w1StationFromPayRentalCurrencyCode;
    @XmlElement(name = "w1StationFrom_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationFromPayRentalTime;
    @XmlElement(name = "w1StationFrom_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationFromPayTime;
    @XmlElement(name = "w1StationFrom_PayUserCode")
    protected String w1StationFromPayUserCode;
    @XmlElement(name = "w1StationFrom_PayUserName")
    protected String w1StationFromPayUserName;
    @XmlElement(name = "w1StationFrom_PayUserTel")
    protected String w1StationFromPayUserTel;
    @XmlElement(name = "w1StationFrom_UserCode")
    protected String w1StationFromUserCode;
    @XmlElement(name = "w1StationFrom_UserName")
    protected String w1StationFromUserName;
    @XmlElement(name = "w1StationFrom_UserTel")
    protected String w1StationFromUserTel;
    @XmlElement(name = "w1StationFrom_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationFromTsActualFrom;
    @XmlElement(name = "w1StationFrom_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationFromTsActualTo;
    @XmlElement(name = "w1StationTo_LoginCode")
    protected String w1StationToLoginCode;
    @XmlElement(name = "w1StationTo_LoginName")
    protected String w1StationToLoginName;
    @XmlElement(name = "w1StationTo_LoginTel")
    protected String w1StationToLoginTel;
    @XmlElement(name = "w1StationTo_PayAmount")
    protected Double w1StationToPayAmount;
    @XmlElement(name = "w1StationTo_PayCurrencyCode")
    protected String w1StationToPayCurrencyCode;
    @XmlElement(name = "w1StationTo_PayRentalAmount")
    protected Double w1StationToPayRentalAmount;
    @XmlElement(name = "w1StationTo_PayRentalCurrencyCode")
    protected String w1StationToPayRentalCurrencyCode;
    @XmlElement(name = "w1StationTo_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationToPayRentalTime;
    @XmlElement(name = "w1StationTo_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationToPayTime;
    @XmlElement(name = "w1StationTo_PayUserCode")
    protected String w1StationToPayUserCode;
    @XmlElement(name = "w1StationTo_PayUserName")
    protected String w1StationToPayUserName;
    @XmlElement(name = "w1StationTo_PayUserTel")
    protected String w1StationToPayUserTel;
    @XmlElement(name = "w1StationTo_UserCode")
    protected String w1StationToUserCode;
    @XmlElement(name = "w1StationTo_UserName")
    protected String w1StationToUserName;
    @XmlElement(name = "w1StationTo_UserTel")
    protected String w1StationToUserTel;
    @XmlElement(name = "w1StationTo_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationToTsActualFrom;
    @XmlElement(name = "w1StationTo_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w1StationToTsActualTo;
    @XmlElement(name = "w2StationFrom_LoginCode")
    protected String w2StationFromLoginCode;
    @XmlElement(name = "w2StationFrom_LoginName")
    protected String w2StationFromLoginName;
    @XmlElement(name = "w2StationFrom_LoginTel")
    protected String w2StationFromLoginTel;
    @XmlElement(name = "w2StationFrom_PayAmount")
    protected Double w2StationFromPayAmount;
    @XmlElement(name = "w2StationFrom_PayCurrencyCode")
    protected String w2StationFromPayCurrencyCode;
    @XmlElement(name = "w2StationFrom_PayRentalAmount")
    protected Double w2StationFromPayRentalAmount;
    @XmlElement(name = "w2StationFrom_PayRentalCurrencyCode")
    protected String w2StationFromPayRentalCurrencyCode;
    @XmlElement(name = "w2StationFrom_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationFromPayRentalTime;
    @XmlElement(name = "w2StationFrom_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationFromPayTime;
    @XmlElement(name = "w2StationFrom_PayUserCode")
    protected String w2StationFromPayUserCode;
    @XmlElement(name = "w2StationFrom_PayUserName")
    protected String w2StationFromPayUserName;
    @XmlElement(name = "w2StationFrom_PayUserTel")
    protected String w2StationFromPayUserTel;
    @XmlElement(name = "w2StationFrom_UserCode")
    protected String w2StationFromUserCode;
    @XmlElement(name = "w2StationFrom_UserName")
    protected String w2StationFromUserName;
    @XmlElement(name = "w2StationFrom_UserTel")
    protected String w2StationFromUserTel;
    @XmlElement(name = "w2StationFrom_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationFromTsActualFrom;
    @XmlElement(name = "w2StationFrom_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationFromTsActualTo;
    @XmlElement(name = "w2StationTo_LoginCode")
    protected String w2StationToLoginCode;
    @XmlElement(name = "w2StationTo_LoginName")
    protected String w2StationToLoginName;
    @XmlElement(name = "w2StationTo_LoginTel")
    protected String w2StationToLoginTel;
    @XmlElement(name = "w2StationTo_PayAmount")
    protected Double w2StationToPayAmount;
    @XmlElement(name = "w2StationTo_PayCurrencyCode")
    protected String w2StationToPayCurrencyCode;
    @XmlElement(name = "w2StationTo_PayRentalAmount")
    protected Double w2StationToPayRentalAmount;
    @XmlElement(name = "w2StationTo_PayRentalCurrencyCode")
    protected String w2StationToPayRentalCurrencyCode;
    @XmlElement(name = "w2StationTo_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationToPayRentalTime;
    @XmlElement(name = "w2StationTo_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationToPayTime;
    @XmlElement(name = "w2StationTo_PayUserCode")
    protected String w2StationToPayUserCode;
    @XmlElement(name = "w2StationTo_PayUserName")
    protected String w2StationToPayUserName;
    @XmlElement(name = "w2StationTo_PayUserTel")
    protected String w2StationToPayUserTel;
    @XmlElement(name = "w2StationTo_UserCode")
    protected String w2StationToUserCode;
    @XmlElement(name = "w2StationTo_UserName")
    protected String w2StationToUserName;
    @XmlElement(name = "w2StationTo_UserTel")
    protected String w2StationToUserTel;
    @XmlElement(name = "w2StationTo_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationToTsActualFrom;
    @XmlElement(name = "w2StationTo_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w2StationToTsActualTo;
    @XmlElement(name = "w3Station_LoginCode")
    protected String w3StationLoginCode;
    @XmlElement(name = "w3Station_LoginName")
    protected String w3StationLoginName;
    @XmlElement(name = "w3Station_LoginTel")
    protected String w3StationLoginTel;
    @XmlElement(name = "w3Station_PayAmount")
    protected Double w3StationPayAmount;
    @XmlElement(name = "w3Station_PayCurrencyCode")
    protected String w3StationPayCurrencyCode;
    @XmlElement(name = "w3Station_PayRentalAmount")
    protected Double w3StationPayRentalAmount;
    @XmlElement(name = "w3Station_PayRentalCurrencyCode")
    protected String w3StationPayRentalCurrencyCode;
    @XmlElement(name = "w3Station_PayRentalTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w3StationPayRentalTime;
    @XmlElement(name = "w3Station_PayTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w3StationPayTime;
    @XmlElement(name = "w3Station_PayUserCode")
    protected String w3StationPayUserCode;
    @XmlElement(name = "w3Station_PayUserName")
    protected String w3StationPayUserName;
    @XmlElement(name = "w3Station_PayUserTel")
    protected String w3StationPayUserTel;
    @XmlElement(name = "w3Station_UserCode")
    protected String w3StationUserCode;
    @XmlElement(name = "w3Station_UserName")
    protected String w3StationUserName;
    @XmlElement(name = "w3Station_UserTel")
    protected String w3StationUserTel;
    @XmlElement(name = "w3Station_tsActualFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w3StationTsActualFrom;
    @XmlElement(name = "w3Station_tsActualTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date w3StationTsActualTo;

    /**
     * 获取acceptOrderLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderLoginCode() {
        return acceptOrderLoginCode;
    }

    /**
     * 设置acceptOrderLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderLoginCode(String value) {
        this.acceptOrderLoginCode = value;
    }

    /**
     * 获取acceptOrderLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderLoginName() {
        return acceptOrderLoginName;
    }

    /**
     * 设置acceptOrderLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderLoginName(String value) {
        this.acceptOrderLoginName = value;
    }

    /**
     * 获取acceptOrderLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderLoginTel() {
        return acceptOrderLoginTel;
    }

    /**
     * 设置acceptOrderLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderLoginTel(String value) {
        this.acceptOrderLoginTel = value;
    }

    /**
     * 获取acceptOrderPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAcceptOrderPayAmount() {
        return acceptOrderPayAmount;
    }

    /**
     * 设置acceptOrderPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAcceptOrderPayAmount(Double value) {
        this.acceptOrderPayAmount = value;
    }

    /**
     * 获取acceptOrderPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderPayCurrencyCode() {
        return acceptOrderPayCurrencyCode;
    }

    /**
     * 设置acceptOrderPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderPayCurrencyCode(String value) {
        this.acceptOrderPayCurrencyCode = value;
    }

    /**
     * 获取acceptOrderPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAcceptOrderPayTime() {
        return acceptOrderPayTime;
    }

    /**
     * 设置acceptOrderPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderPayTime(Date value) {
        this.acceptOrderPayTime = value;
    }

    /**
     * 获取acceptOrderPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderPayUserCode() {
        return acceptOrderPayUserCode;
    }

    /**
     * 设置acceptOrderPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderPayUserCode(String value) {
        this.acceptOrderPayUserCode = value;
    }

    /**
     * 获取acceptOrderPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderPayUserName() {
        return acceptOrderPayUserName;
    }

    /**
     * 设置acceptOrderPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderPayUserName(String value) {
        this.acceptOrderPayUserName = value;
    }

    /**
     * 获取acceptOrderPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderPayUserTel() {
        return acceptOrderPayUserTel;
    }

    /**
     * 设置acceptOrderPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderPayUserTel(String value) {
        this.acceptOrderPayUserTel = value;
    }

    /**
     * 获取acceptOrderUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderUserCode() {
        return acceptOrderUserCode;
    }

    /**
     * 设置acceptOrderUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderUserCode(String value) {
        this.acceptOrderUserCode = value;
    }

    /**
     * 获取acceptOrderUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderUserName() {
        return acceptOrderUserName;
    }

    /**
     * 设置acceptOrderUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderUserName(String value) {
        this.acceptOrderUserName = value;
    }

    /**
     * 获取acceptOrderUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrderUserTel() {
        return acceptOrderUserTel;
    }

    /**
     * 设置acceptOrderUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderUserTel(String value) {
        this.acceptOrderUserTel = value;
    }

    /**
     * 获取acceptOrderTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAcceptOrderTsActualFrom() {
        return acceptOrderTsActualFrom;
    }

    /**
     * 设置acceptOrderTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderTsActualFrom(Date value) {
        this.acceptOrderTsActualFrom = value;
    }

    /**
     * 获取acceptOrderTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAcceptOrderTsActualTo() {
        return acceptOrderTsActualTo;
    }

    /**
     * 设置acceptOrderTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrderTsActualTo(Date value) {
        this.acceptOrderTsActualTo = value;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * 获取busiNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置busiNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiNo(String value) {
        this.busiNo = value;
    }

    /**
     * 获取cityFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityFrom() {
        return cityFrom;
    }

    /**
     * 设置cityFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityFrom(String value) {
        this.cityFrom = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取deliveryToPodLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodLoginCode() {
        return deliveryToPodLoginCode;
    }

    /**
     * 设置deliveryToPodLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodLoginCode(String value) {
        this.deliveryToPodLoginCode = value;
    }

    /**
     * 获取deliveryToPodLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodLoginName() {
        return deliveryToPodLoginName;
    }

    /**
     * 设置deliveryToPodLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodLoginName(String value) {
        this.deliveryToPodLoginName = value;
    }

    /**
     * 获取deliveryToPodLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodLoginTel() {
        return deliveryToPodLoginTel;
    }

    /**
     * 设置deliveryToPodLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodLoginTel(String value) {
        this.deliveryToPodLoginTel = value;
    }

    /**
     * 获取deliveryToPodPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDeliveryToPodPayAmount() {
        return deliveryToPodPayAmount;
    }

    /**
     * 设置deliveryToPodPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDeliveryToPodPayAmount(Double value) {
        this.deliveryToPodPayAmount = value;
    }

    /**
     * 获取deliveryToPodPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodPayCurrencyCode() {
        return deliveryToPodPayCurrencyCode;
    }

    /**
     * 设置deliveryToPodPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodPayCurrencyCode(String value) {
        this.deliveryToPodPayCurrencyCode = value;
    }

    /**
     * 获取deliveryToPodPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDeliveryToPodPayTime() {
        return deliveryToPodPayTime;
    }

    /**
     * 设置deliveryToPodPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodPayTime(Date value) {
        this.deliveryToPodPayTime = value;
    }

    /**
     * 获取deliveryToPodPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodPayUserCode() {
        return deliveryToPodPayUserCode;
    }

    /**
     * 设置deliveryToPodPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodPayUserCode(String value) {
        this.deliveryToPodPayUserCode = value;
    }

    /**
     * 获取deliveryToPodPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodPayUserName() {
        return deliveryToPodPayUserName;
    }

    /**
     * 设置deliveryToPodPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodPayUserName(String value) {
        this.deliveryToPodPayUserName = value;
    }

    /**
     * 获取deliveryToPodPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodPayUserTel() {
        return deliveryToPodPayUserTel;
    }

    /**
     * 设置deliveryToPodPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodPayUserTel(String value) {
        this.deliveryToPodPayUserTel = value;
    }

    /**
     * 获取deliveryToPodUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodUserCode() {
        return deliveryToPodUserCode;
    }

    /**
     * 设置deliveryToPodUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodUserCode(String value) {
        this.deliveryToPodUserCode = value;
    }

    /**
     * 获取deliveryToPodUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodUserName() {
        return deliveryToPodUserName;
    }

    /**
     * 设置deliveryToPodUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodUserName(String value) {
        this.deliveryToPodUserName = value;
    }

    /**
     * 获取deliveryToPodUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryToPodUserTel() {
        return deliveryToPodUserTel;
    }

    /**
     * 设置deliveryToPodUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodUserTel(String value) {
        this.deliveryToPodUserTel = value;
    }

    /**
     * 获取deliveryToPodTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDeliveryToPodTsActualFrom() {
        return deliveryToPodTsActualFrom;
    }

    /**
     * 设置deliveryToPodTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodTsActualFrom(Date value) {
        this.deliveryToPodTsActualFrom = value;
    }

    /**
     * 获取deliveryToPodTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDeliveryToPodTsActualTo() {
        return deliveryToPodTsActualTo;
    }

    /**
     * 设置deliveryToPodTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryToPodTsActualTo(Date value) {
        this.deliveryToPodTsActualTo = value;
    }

    /**
     * 获取districtFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictFrom() {
        return districtFrom;
    }

    /**
     * 设置districtFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictFrom(String value) {
        this.districtFrom = value;
    }

    /**
     * 获取driverMiWaFromLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromLoginCode() {
        return driverMiWaFromLoginCode;
    }

    /**
     * 设置driverMiWaFromLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromLoginCode(String value) {
        this.driverMiWaFromLoginCode = value;
    }

    /**
     * 获取driverMiWaFromLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromLoginName() {
        return driverMiWaFromLoginName;
    }

    /**
     * 设置driverMiWaFromLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromLoginName(String value) {
        this.driverMiWaFromLoginName = value;
    }

    /**
     * 获取driverMiWaFromLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromLoginTel() {
        return driverMiWaFromLoginTel;
    }

    /**
     * 设置driverMiWaFromLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromLoginTel(String value) {
        this.driverMiWaFromLoginTel = value;
    }

    /**
     * 获取driverMiWaFromPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDriverMiWaFromPayAmount() {
        return driverMiWaFromPayAmount;
    }

    /**
     * 设置driverMiWaFromPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDriverMiWaFromPayAmount(Double value) {
        this.driverMiWaFromPayAmount = value;
    }

    /**
     * 获取driverMiWaFromPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromPayCurrencyCode() {
        return driverMiWaFromPayCurrencyCode;
    }

    /**
     * 设置driverMiWaFromPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromPayCurrencyCode(String value) {
        this.driverMiWaFromPayCurrencyCode = value;
    }

    /**
     * 获取driverMiWaFromPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverMiWaFromPayTime() {
        return driverMiWaFromPayTime;
    }

    /**
     * 设置driverMiWaFromPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromPayTime(Date value) {
        this.driverMiWaFromPayTime = value;
    }

    /**
     * 获取driverMiWaFromPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromPayUserCode() {
        return driverMiWaFromPayUserCode;
    }

    /**
     * 设置driverMiWaFromPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromPayUserCode(String value) {
        this.driverMiWaFromPayUserCode = value;
    }

    /**
     * 获取driverMiWaFromPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromPayUserName() {
        return driverMiWaFromPayUserName;
    }

    /**
     * 设置driverMiWaFromPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromPayUserName(String value) {
        this.driverMiWaFromPayUserName = value;
    }

    /**
     * 获取driverMiWaFromPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromPayUserTel() {
        return driverMiWaFromPayUserTel;
    }

    /**
     * 设置driverMiWaFromPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromPayUserTel(String value) {
        this.driverMiWaFromPayUserTel = value;
    }

    /**
     * 获取driverMiWaFromUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromUserCode() {
        return driverMiWaFromUserCode;
    }

    /**
     * 设置driverMiWaFromUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromUserCode(String value) {
        this.driverMiWaFromUserCode = value;
    }

    /**
     * 获取driverMiWaFromUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromUserName() {
        return driverMiWaFromUserName;
    }

    /**
     * 设置driverMiWaFromUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromUserName(String value) {
        this.driverMiWaFromUserName = value;
    }

    /**
     * 获取driverMiWaFromUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverMiWaFromUserTel() {
        return driverMiWaFromUserTel;
    }

    /**
     * 设置driverMiWaFromUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromUserTel(String value) {
        this.driverMiWaFromUserTel = value;
    }

    /**
     * 获取driverMiWaFromTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverMiWaFromTsActualFrom() {
        return driverMiWaFromTsActualFrom;
    }

    /**
     * 设置driverMiWaFromTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromTsActualFrom(Date value) {
        this.driverMiWaFromTsActualFrom = value;
    }

    /**
     * 获取driverMiWaFromTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverMiWaFromTsActualTo() {
        return driverMiWaFromTsActualTo;
    }

    /**
     * 设置driverMiWaFromTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverMiWaFromTsActualTo(Date value) {
        this.driverMiWaFromTsActualTo = value;
    }

    /**
     * 获取driverW1MiToLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToLoginCode() {
        return driverW1MiToLoginCode;
    }

    /**
     * 设置driverW1MiToLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToLoginCode(String value) {
        this.driverW1MiToLoginCode = value;
    }

    /**
     * 获取driverW1MiToLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToLoginName() {
        return driverW1MiToLoginName;
    }

    /**
     * 设置driverW1MiToLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToLoginName(String value) {
        this.driverW1MiToLoginName = value;
    }

    /**
     * 获取driverW1MiToLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToLoginTel() {
        return driverW1MiToLoginTel;
    }

    /**
     * 设置driverW1MiToLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToLoginTel(String value) {
        this.driverW1MiToLoginTel = value;
    }

    /**
     * 获取driverW1MiToPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDriverW1MiToPayAmount() {
        return driverW1MiToPayAmount;
    }

    /**
     * 设置driverW1MiToPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDriverW1MiToPayAmount(Double value) {
        this.driverW1MiToPayAmount = value;
    }

    /**
     * 获取driverW1MiToPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToPayCurrencyCode() {
        return driverW1MiToPayCurrencyCode;
    }

    /**
     * 设置driverW1MiToPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToPayCurrencyCode(String value) {
        this.driverW1MiToPayCurrencyCode = value;
    }

    /**
     * 获取driverW1MiToPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW1MiToPayTime() {
        return driverW1MiToPayTime;
    }

    /**
     * 设置driverW1MiToPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToPayTime(Date value) {
        this.driverW1MiToPayTime = value;
    }

    /**
     * 获取driverW1MiToPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToPayUserCode() {
        return driverW1MiToPayUserCode;
    }

    /**
     * 设置driverW1MiToPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToPayUserCode(String value) {
        this.driverW1MiToPayUserCode = value;
    }

    /**
     * 获取driverW1MiToPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToPayUserName() {
        return driverW1MiToPayUserName;
    }

    /**
     * 设置driverW1MiToPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToPayUserName(String value) {
        this.driverW1MiToPayUserName = value;
    }

    /**
     * 获取driverW1MiToPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToPayUserTel() {
        return driverW1MiToPayUserTel;
    }

    /**
     * 设置driverW1MiToPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToPayUserTel(String value) {
        this.driverW1MiToPayUserTel = value;
    }

    /**
     * 获取driverW1MiToUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToUserCode() {
        return driverW1MiToUserCode;
    }

    /**
     * 设置driverW1MiToUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToUserCode(String value) {
        this.driverW1MiToUserCode = value;
    }

    /**
     * 获取driverW1MiToUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToUserName() {
        return driverW1MiToUserName;
    }

    /**
     * 设置driverW1MiToUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToUserName(String value) {
        this.driverW1MiToUserName = value;
    }

    /**
     * 获取driverW1MiToUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1MiToUserTel() {
        return driverW1MiToUserTel;
    }

    /**
     * 设置driverW1MiToUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToUserTel(String value) {
        this.driverW1MiToUserTel = value;
    }

    /**
     * 获取driverW1MiToTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW1MiToTsActualFrom() {
        return driverW1MiToTsActualFrom;
    }

    /**
     * 设置driverW1MiToTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToTsActualFrom(Date value) {
        this.driverW1MiToTsActualFrom = value;
    }

    /**
     * 获取driverW1MiToTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW1MiToTsActualTo() {
        return driverW1MiToTsActualTo;
    }

    /**
     * 设置driverW1MiToTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1MiToTsActualTo(Date value) {
        this.driverW1MiToTsActualTo = value;
    }

    /**
     * 获取driverW1W2FromLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromLoginCode() {
        return driverW1W2FromLoginCode;
    }

    /**
     * 设置driverW1W2FromLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromLoginCode(String value) {
        this.driverW1W2FromLoginCode = value;
    }

    /**
     * 获取driverW1W2FromLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromLoginName() {
        return driverW1W2FromLoginName;
    }

    /**
     * 设置driverW1W2FromLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromLoginName(String value) {
        this.driverW1W2FromLoginName = value;
    }

    /**
     * 获取driverW1W2FromLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromLoginTel() {
        return driverW1W2FromLoginTel;
    }

    /**
     * 设置driverW1W2FromLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromLoginTel(String value) {
        this.driverW1W2FromLoginTel = value;
    }

    /**
     * 获取driverW1W2FromPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDriverW1W2FromPayAmount() {
        return driverW1W2FromPayAmount;
    }

    /**
     * 设置driverW1W2FromPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDriverW1W2FromPayAmount(Double value) {
        this.driverW1W2FromPayAmount = value;
    }

    /**
     * 获取driverW1W2FromPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromPayCurrencyCode() {
        return driverW1W2FromPayCurrencyCode;
    }

    /**
     * 设置driverW1W2FromPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromPayCurrencyCode(String value) {
        this.driverW1W2FromPayCurrencyCode = value;
    }

    /**
     * 获取driverW1W2FromPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW1W2FromPayTime() {
        return driverW1W2FromPayTime;
    }

    /**
     * 设置driverW1W2FromPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromPayTime(Date value) {
        this.driverW1W2FromPayTime = value;
    }

    /**
     * 获取driverW1W2FromPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromPayUserCode() {
        return driverW1W2FromPayUserCode;
    }

    /**
     * 设置driverW1W2FromPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromPayUserCode(String value) {
        this.driverW1W2FromPayUserCode = value;
    }

    /**
     * 获取driverW1W2FromPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromPayUserName() {
        return driverW1W2FromPayUserName;
    }

    /**
     * 设置driverW1W2FromPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromPayUserName(String value) {
        this.driverW1W2FromPayUserName = value;
    }

    /**
     * 获取driverW1W2FromPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromPayUserTel() {
        return driverW1W2FromPayUserTel;
    }

    /**
     * 设置driverW1W2FromPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromPayUserTel(String value) {
        this.driverW1W2FromPayUserTel = value;
    }

    /**
     * 获取driverW1W2FromUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromUserCode() {
        return driverW1W2FromUserCode;
    }

    /**
     * 设置driverW1W2FromUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromUserCode(String value) {
        this.driverW1W2FromUserCode = value;
    }

    /**
     * 获取driverW1W2FromUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromUserName() {
        return driverW1W2FromUserName;
    }

    /**
     * 设置driverW1W2FromUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromUserName(String value) {
        this.driverW1W2FromUserName = value;
    }

    /**
     * 获取driverW1W2FromUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW1W2FromUserTel() {
        return driverW1W2FromUserTel;
    }

    /**
     * 设置driverW1W2FromUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromUserTel(String value) {
        this.driverW1W2FromUserTel = value;
    }

    /**
     * 获取driverW1W2FromTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW1W2FromTsActualFrom() {
        return driverW1W2FromTsActualFrom;
    }

    /**
     * 设置driverW1W2FromTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromTsActualFrom(Date value) {
        this.driverW1W2FromTsActualFrom = value;
    }

    /**
     * 获取driverW1W2FromTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW1W2FromTsActualTo() {
        return driverW1W2FromTsActualTo;
    }

    /**
     * 设置driverW1W2FromTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW1W2FromTsActualTo(Date value) {
        this.driverW1W2FromTsActualTo = value;
    }

    /**
     * 获取driverW2W1ToLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToLoginCode() {
        return driverW2W1ToLoginCode;
    }

    /**
     * 设置driverW2W1ToLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToLoginCode(String value) {
        this.driverW2W1ToLoginCode = value;
    }

    /**
     * 获取driverW2W1ToLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToLoginName() {
        return driverW2W1ToLoginName;
    }

    /**
     * 设置driverW2W1ToLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToLoginName(String value) {
        this.driverW2W1ToLoginName = value;
    }

    /**
     * 获取driverW2W1ToLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToLoginTel() {
        return driverW2W1ToLoginTel;
    }

    /**
     * 设置driverW2W1ToLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToLoginTel(String value) {
        this.driverW2W1ToLoginTel = value;
    }

    /**
     * 获取driverW2W1ToPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDriverW2W1ToPayAmount() {
        return driverW2W1ToPayAmount;
    }

    /**
     * 设置driverW2W1ToPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDriverW2W1ToPayAmount(Double value) {
        this.driverW2W1ToPayAmount = value;
    }

    /**
     * 获取driverW2W1ToPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToPayCurrencyCode() {
        return driverW2W1ToPayCurrencyCode;
    }

    /**
     * 设置driverW2W1ToPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToPayCurrencyCode(String value) {
        this.driverW2W1ToPayCurrencyCode = value;
    }

    /**
     * 获取driverW2W1ToPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW2W1ToPayTime() {
        return driverW2W1ToPayTime;
    }

    /**
     * 设置driverW2W1ToPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToPayTime(Date value) {
        this.driverW2W1ToPayTime = value;
    }

    /**
     * 获取driverW2W1ToPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToPayUserCode() {
        return driverW2W1ToPayUserCode;
    }

    /**
     * 设置driverW2W1ToPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToPayUserCode(String value) {
        this.driverW2W1ToPayUserCode = value;
    }

    /**
     * 获取driverW2W1ToPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToPayUserName() {
        return driverW2W1ToPayUserName;
    }

    /**
     * 设置driverW2W1ToPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToPayUserName(String value) {
        this.driverW2W1ToPayUserName = value;
    }

    /**
     * 获取driverW2W1ToPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToPayUserTel() {
        return driverW2W1ToPayUserTel;
    }

    /**
     * 设置driverW2W1ToPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToPayUserTel(String value) {
        this.driverW2W1ToPayUserTel = value;
    }

    /**
     * 获取driverW2W1ToUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToUserCode() {
        return driverW2W1ToUserCode;
    }

    /**
     * 设置driverW2W1ToUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToUserCode(String value) {
        this.driverW2W1ToUserCode = value;
    }

    /**
     * 获取driverW2W1ToUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToUserName() {
        return driverW2W1ToUserName;
    }

    /**
     * 设置driverW2W1ToUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToUserName(String value) {
        this.driverW2W1ToUserName = value;
    }

    /**
     * 获取driverW2W1ToUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W1ToUserTel() {
        return driverW2W1ToUserTel;
    }

    /**
     * 设置driverW2W1ToUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToUserTel(String value) {
        this.driverW2W1ToUserTel = value;
    }

    /**
     * 获取driverW2W1ToTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW2W1ToTsActualFrom() {
        return driverW2W1ToTsActualFrom;
    }

    /**
     * 设置driverW2W1ToTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToTsActualFrom(Date value) {
        this.driverW2W1ToTsActualFrom = value;
    }

    /**
     * 获取driverW2W1ToTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW2W1ToTsActualTo() {
        return driverW2W1ToTsActualTo;
    }

    /**
     * 设置driverW2W1ToTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W1ToTsActualTo(Date value) {
        this.driverW2W1ToTsActualTo = value;
    }

    /**
     * 获取driverW2W3FromLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromLoginCode() {
        return driverW2W3FromLoginCode;
    }

    /**
     * 设置driverW2W3FromLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromLoginCode(String value) {
        this.driverW2W3FromLoginCode = value;
    }

    /**
     * 获取driverW2W3FromLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromLoginName() {
        return driverW2W3FromLoginName;
    }

    /**
     * 设置driverW2W3FromLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromLoginName(String value) {
        this.driverW2W3FromLoginName = value;
    }

    /**
     * 获取driverW2W3FromLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromLoginTel() {
        return driverW2W3FromLoginTel;
    }

    /**
     * 设置driverW2W3FromLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromLoginTel(String value) {
        this.driverW2W3FromLoginTel = value;
    }

    /**
     * 获取driverW2W3FromPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDriverW2W3FromPayAmount() {
        return driverW2W3FromPayAmount;
    }

    /**
     * 设置driverW2W3FromPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDriverW2W3FromPayAmount(Double value) {
        this.driverW2W3FromPayAmount = value;
    }

    /**
     * 获取driverW2W3FromPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromPayCurrencyCode() {
        return driverW2W3FromPayCurrencyCode;
    }

    /**
     * 设置driverW2W3FromPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromPayCurrencyCode(String value) {
        this.driverW2W3FromPayCurrencyCode = value;
    }

    /**
     * 获取driverW2W3FromPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW2W3FromPayTime() {
        return driverW2W3FromPayTime;
    }

    /**
     * 设置driverW2W3FromPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromPayTime(Date value) {
        this.driverW2W3FromPayTime = value;
    }

    /**
     * 获取driverW2W3FromPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromPayUserCode() {
        return driverW2W3FromPayUserCode;
    }

    /**
     * 设置driverW2W3FromPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromPayUserCode(String value) {
        this.driverW2W3FromPayUserCode = value;
    }

    /**
     * 获取driverW2W3FromPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromPayUserName() {
        return driverW2W3FromPayUserName;
    }

    /**
     * 设置driverW2W3FromPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromPayUserName(String value) {
        this.driverW2W3FromPayUserName = value;
    }

    /**
     * 获取driverW2W3FromPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromPayUserTel() {
        return driverW2W3FromPayUserTel;
    }

    /**
     * 设置driverW2W3FromPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromPayUserTel(String value) {
        this.driverW2W3FromPayUserTel = value;
    }

    /**
     * 获取driverW2W3FromUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromUserCode() {
        return driverW2W3FromUserCode;
    }

    /**
     * 设置driverW2W3FromUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromUserCode(String value) {
        this.driverW2W3FromUserCode = value;
    }

    /**
     * 获取driverW2W3FromUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromUserName() {
        return driverW2W3FromUserName;
    }

    /**
     * 设置driverW2W3FromUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromUserName(String value) {
        this.driverW2W3FromUserName = value;
    }

    /**
     * 获取driverW2W3FromUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW2W3FromUserTel() {
        return driverW2W3FromUserTel;
    }

    /**
     * 设置driverW2W3FromUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromUserTel(String value) {
        this.driverW2W3FromUserTel = value;
    }

    /**
     * 获取driverW2W3FromTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW2W3FromTsActualFrom() {
        return driverW2W3FromTsActualFrom;
    }

    /**
     * 设置driverW2W3FromTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromTsActualFrom(Date value) {
        this.driverW2W3FromTsActualFrom = value;
    }

    /**
     * 获取driverW2W3FromTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW2W3FromTsActualTo() {
        return driverW2W3FromTsActualTo;
    }

    /**
     * 设置driverW2W3FromTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW2W3FromTsActualTo(Date value) {
        this.driverW2W3FromTsActualTo = value;
    }

    /**
     * 获取driverW3W2ToLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToLoginCode() {
        return driverW3W2ToLoginCode;
    }

    /**
     * 设置driverW3W2ToLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToLoginCode(String value) {
        this.driverW3W2ToLoginCode = value;
    }

    /**
     * 获取driverW3W2ToLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToLoginName() {
        return driverW3W2ToLoginName;
    }

    /**
     * 设置driverW3W2ToLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToLoginName(String value) {
        this.driverW3W2ToLoginName = value;
    }

    /**
     * 获取driverW3W2ToLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToLoginTel() {
        return driverW3W2ToLoginTel;
    }

    /**
     * 设置driverW3W2ToLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToLoginTel(String value) {
        this.driverW3W2ToLoginTel = value;
    }

    /**
     * 获取driverW3W2ToPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDriverW3W2ToPayAmount() {
        return driverW3W2ToPayAmount;
    }

    /**
     * 设置driverW3W2ToPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDriverW3W2ToPayAmount(Double value) {
        this.driverW3W2ToPayAmount = value;
    }

    /**
     * 获取driverW3W2ToPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToPayCurrencyCode() {
        return driverW3W2ToPayCurrencyCode;
    }

    /**
     * 设置driverW3W2ToPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToPayCurrencyCode(String value) {
        this.driverW3W2ToPayCurrencyCode = value;
    }

    /**
     * 获取driverW3W2ToPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW3W2ToPayTime() {
        return driverW3W2ToPayTime;
    }

    /**
     * 设置driverW3W2ToPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToPayTime(Date value) {
        this.driverW3W2ToPayTime = value;
    }

    /**
     * 获取driverW3W2ToPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToPayUserCode() {
        return driverW3W2ToPayUserCode;
    }

    /**
     * 设置driverW3W2ToPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToPayUserCode(String value) {
        this.driverW3W2ToPayUserCode = value;
    }

    /**
     * 获取driverW3W2ToPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToPayUserName() {
        return driverW3W2ToPayUserName;
    }

    /**
     * 设置driverW3W2ToPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToPayUserName(String value) {
        this.driverW3W2ToPayUserName = value;
    }

    /**
     * 获取driverW3W2ToPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToPayUserTel() {
        return driverW3W2ToPayUserTel;
    }

    /**
     * 设置driverW3W2ToPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToPayUserTel(String value) {
        this.driverW3W2ToPayUserTel = value;
    }

    /**
     * 获取driverW3W2ToUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToUserCode() {
        return driverW3W2ToUserCode;
    }

    /**
     * 设置driverW3W2ToUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToUserCode(String value) {
        this.driverW3W2ToUserCode = value;
    }

    /**
     * 获取driverW3W2ToUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToUserName() {
        return driverW3W2ToUserName;
    }

    /**
     * 设置driverW3W2ToUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToUserName(String value) {
        this.driverW3W2ToUserName = value;
    }

    /**
     * 获取driverW3W2ToUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverW3W2ToUserTel() {
        return driverW3W2ToUserTel;
    }

    /**
     * 设置driverW3W2ToUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToUserTel(String value) {
        this.driverW3W2ToUserTel = value;
    }

    /**
     * 获取driverW3W2ToTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW3W2ToTsActualFrom() {
        return driverW3W2ToTsActualFrom;
    }

    /**
     * 设置driverW3W2ToTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToTsActualFrom(Date value) {
        this.driverW3W2ToTsActualFrom = value;
    }

    /**
     * 获取driverW3W2ToTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDriverW3W2ToTsActualTo() {
        return driverW3W2ToTsActualTo;
    }

    /**
     * 设置driverW3W2ToTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverW3W2ToTsActualTo(Date value) {
        this.driverW3W2ToTsActualTo = value;
    }

    /**
     * 获取gicbFenceName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGicbFenceName() {
        return gicbFenceName;
    }

    /**
     * 设置gicbFenceName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGicbFenceName(String value) {
        this.gicbFenceName = value;
    }

    /**
     * 获取goodsName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置goodsName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsName(String value) {
        this.goodsName = value;
    }

    /**
     * 获取goodsType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置goodsType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsType(String value) {
        this.goodsType = value;
    }

    /**
     * 获取grossWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGrossWeight() {
        return grossWeight;
    }

    /**
     * 设置grossWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGrossWeight(Double value) {
        this.grossWeight = value;
    }

    /**
     * 获取isNotAccept3Mins属性的值。
     * 
     */
    public boolean isIsNotAccept3Mins() {
        return isNotAccept3Mins;
    }

    /**
     * 设置isNotAccept3Mins属性的值。
     * 
     */
    public void setIsNotAccept3Mins(boolean value) {
        this.isNotAccept3Mins = value;
    }

    /**
     * 获取isNotPickup30Mins属性的值。
     * 
     */
    public boolean isIsNotPickup30Mins() {
        return isNotPickup30Mins;
    }

    /**
     * 设置isNotPickup30Mins属性的值。
     * 
     */
    public void setIsNotPickup30Mins(boolean value) {
        this.isNotPickup30Mins = value;
    }

    /**
     * 获取isNotPod3Hours属性的值。
     * 
     */
    public boolean isIsNotPod3Hours() {
        return isNotPod3Hours;
    }

    /**
     * 设置isNotPod3Hours属性的值。
     * 
     */
    public void setIsNotPod3Hours(boolean value) {
        this.isNotPod3Hours = value;
    }

    /**
     * 获取isNotPod4Hours属性的值。
     * 
     */
    public boolean isIsNotPod4Hours() {
        return isNotPod4Hours;
    }

    /**
     * 设置isNotPod4Hours属性的值。
     * 
     */
    public void setIsNotPod4Hours(boolean value) {
        this.isNotPod4Hours = value;
    }

    /**
     * 获取isNotW3Attime属性的值。
     * 
     */
    public boolean isIsNotW3Attime() {
        return isNotW3Attime;
    }

    /**
     * 设置isNotW3Attime属性的值。
     * 
     */
    public void setIsNotW3Attime(boolean value) {
        this.isNotW3Attime = value;
    }

    /**
     * 获取miStationFromLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromLoginCode() {
        return miStationFromLoginCode;
    }

    /**
     * 设置miStationFromLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromLoginCode(String value) {
        this.miStationFromLoginCode = value;
    }

    /**
     * 获取miStationFromLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromLoginName() {
        return miStationFromLoginName;
    }

    /**
     * 设置miStationFromLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromLoginName(String value) {
        this.miStationFromLoginName = value;
    }

    /**
     * 获取miStationFromLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromLoginTel() {
        return miStationFromLoginTel;
    }

    /**
     * 设置miStationFromLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromLoginTel(String value) {
        this.miStationFromLoginTel = value;
    }

    /**
     * 获取miStationFromPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMiStationFromPayAmount() {
        return miStationFromPayAmount;
    }

    /**
     * 设置miStationFromPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMiStationFromPayAmount(Double value) {
        this.miStationFromPayAmount = value;
    }

    /**
     * 获取miStationFromPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromPayCurrencyCode() {
        return miStationFromPayCurrencyCode;
    }

    /**
     * 设置miStationFromPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayCurrencyCode(String value) {
        this.miStationFromPayCurrencyCode = value;
    }

    /**
     * 获取miStationFromPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMiStationFromPayRentalAmount() {
        return miStationFromPayRentalAmount;
    }

    /**
     * 设置miStationFromPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMiStationFromPayRentalAmount(Double value) {
        this.miStationFromPayRentalAmount = value;
    }

    /**
     * 获取miStationFromPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromPayRentalCurrencyCode() {
        return miStationFromPayRentalCurrencyCode;
    }

    /**
     * 设置miStationFromPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayRentalCurrencyCode(String value) {
        this.miStationFromPayRentalCurrencyCode = value;
    }

    /**
     * 获取miStationFromPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationFromPayRentalTime() {
        return miStationFromPayRentalTime;
    }

    /**
     * 设置miStationFromPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayRentalTime(Date value) {
        this.miStationFromPayRentalTime = value;
    }

    /**
     * 获取miStationFromPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationFromPayTime() {
        return miStationFromPayTime;
    }

    /**
     * 设置miStationFromPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayTime(Date value) {
        this.miStationFromPayTime = value;
    }

    /**
     * 获取miStationFromPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromPayUserCode() {
        return miStationFromPayUserCode;
    }

    /**
     * 设置miStationFromPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayUserCode(String value) {
        this.miStationFromPayUserCode = value;
    }

    /**
     * 获取miStationFromPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromPayUserName() {
        return miStationFromPayUserName;
    }

    /**
     * 设置miStationFromPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayUserName(String value) {
        this.miStationFromPayUserName = value;
    }

    /**
     * 获取miStationFromPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromPayUserTel() {
        return miStationFromPayUserTel;
    }

    /**
     * 设置miStationFromPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromPayUserTel(String value) {
        this.miStationFromPayUserTel = value;
    }

    /**
     * 获取miStationFromUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromUserCode() {
        return miStationFromUserCode;
    }

    /**
     * 设置miStationFromUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromUserCode(String value) {
        this.miStationFromUserCode = value;
    }

    /**
     * 获取miStationFromUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromUserName() {
        return miStationFromUserName;
    }

    /**
     * 设置miStationFromUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromUserName(String value) {
        this.miStationFromUserName = value;
    }

    /**
     * 获取miStationFromUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationFromUserTel() {
        return miStationFromUserTel;
    }

    /**
     * 设置miStationFromUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromUserTel(String value) {
        this.miStationFromUserTel = value;
    }

    /**
     * 获取miStationFromTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationFromTsActualFrom() {
        return miStationFromTsActualFrom;
    }

    /**
     * 设置miStationFromTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromTsActualFrom(Date value) {
        this.miStationFromTsActualFrom = value;
    }

    /**
     * 获取miStationFromTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationFromTsActualTo() {
        return miStationFromTsActualTo;
    }

    /**
     * 设置miStationFromTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationFromTsActualTo(Date value) {
        this.miStationFromTsActualTo = value;
    }

    /**
     * 获取miStationToLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToLoginCode() {
        return miStationToLoginCode;
    }

    /**
     * 设置miStationToLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToLoginCode(String value) {
        this.miStationToLoginCode = value;
    }

    /**
     * 获取miStationToLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToLoginName() {
        return miStationToLoginName;
    }

    /**
     * 设置miStationToLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToLoginName(String value) {
        this.miStationToLoginName = value;
    }

    /**
     * 获取miStationToLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToLoginTel() {
        return miStationToLoginTel;
    }

    /**
     * 设置miStationToLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToLoginTel(String value) {
        this.miStationToLoginTel = value;
    }

    /**
     * 获取miStationToPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMiStationToPayAmount() {
        return miStationToPayAmount;
    }

    /**
     * 设置miStationToPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMiStationToPayAmount(Double value) {
        this.miStationToPayAmount = value;
    }

    /**
     * 获取miStationToPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToPayCurrencyCode() {
        return miStationToPayCurrencyCode;
    }

    /**
     * 设置miStationToPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayCurrencyCode(String value) {
        this.miStationToPayCurrencyCode = value;
    }

    /**
     * 获取miStationToPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMiStationToPayRentalAmount() {
        return miStationToPayRentalAmount;
    }

    /**
     * 设置miStationToPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMiStationToPayRentalAmount(Double value) {
        this.miStationToPayRentalAmount = value;
    }

    /**
     * 获取miStationToPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToPayRentalCurrencyCode() {
        return miStationToPayRentalCurrencyCode;
    }

    /**
     * 设置miStationToPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayRentalCurrencyCode(String value) {
        this.miStationToPayRentalCurrencyCode = value;
    }

    /**
     * 获取miStationToPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationToPayRentalTime() {
        return miStationToPayRentalTime;
    }

    /**
     * 设置miStationToPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayRentalTime(Date value) {
        this.miStationToPayRentalTime = value;
    }

    /**
     * 获取miStationToPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationToPayTime() {
        return miStationToPayTime;
    }

    /**
     * 设置miStationToPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayTime(Date value) {
        this.miStationToPayTime = value;
    }

    /**
     * 获取miStationToPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToPayUserCode() {
        return miStationToPayUserCode;
    }

    /**
     * 设置miStationToPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayUserCode(String value) {
        this.miStationToPayUserCode = value;
    }

    /**
     * 获取miStationToPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToPayUserName() {
        return miStationToPayUserName;
    }

    /**
     * 设置miStationToPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayUserName(String value) {
        this.miStationToPayUserName = value;
    }

    /**
     * 获取miStationToPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToPayUserTel() {
        return miStationToPayUserTel;
    }

    /**
     * 设置miStationToPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToPayUserTel(String value) {
        this.miStationToPayUserTel = value;
    }

    /**
     * 获取miStationToUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToUserCode() {
        return miStationToUserCode;
    }

    /**
     * 设置miStationToUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToUserCode(String value) {
        this.miStationToUserCode = value;
    }

    /**
     * 获取miStationToUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToUserName() {
        return miStationToUserName;
    }

    /**
     * 设置miStationToUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToUserName(String value) {
        this.miStationToUserName = value;
    }

    /**
     * 获取miStationToUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStationToUserTel() {
        return miStationToUserTel;
    }

    /**
     * 设置miStationToUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToUserTel(String value) {
        this.miStationToUserTel = value;
    }

    /**
     * 获取miStationToTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationToTsActualFrom() {
        return miStationToTsActualFrom;
    }

    /**
     * 设置miStationToTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToTsActualFrom(Date value) {
        this.miStationToTsActualFrom = value;
    }

    /**
     * 获取miStationToTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMiStationToTsActualTo() {
        return miStationToTsActualTo;
    }

    /**
     * 设置miStationToTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStationToTsActualTo(Date value) {
        this.miStationToTsActualTo = value;
    }

    /**
     * 获取orderStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置orderStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatus(String value) {
        this.orderStatus = value;
    }

    /**
     * 获取orderStatusDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    /**
     * 设置orderStatusDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatusDesc(String value) {
        this.orderStatusDesc = value;
    }

    /**
     * 获取payStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置payStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayStatus(String value) {
        this.payStatus = value;
    }

    /**
     * 获取pickupOrderLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderLoginCode() {
        return pickupOrderLoginCode;
    }

    /**
     * 设置pickupOrderLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderLoginCode(String value) {
        this.pickupOrderLoginCode = value;
    }

    /**
     * 获取pickupOrderLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderLoginName() {
        return pickupOrderLoginName;
    }

    /**
     * 设置pickupOrderLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderLoginName(String value) {
        this.pickupOrderLoginName = value;
    }

    /**
     * 获取pickupOrderLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderLoginTel() {
        return pickupOrderLoginTel;
    }

    /**
     * 设置pickupOrderLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderLoginTel(String value) {
        this.pickupOrderLoginTel = value;
    }

    /**
     * 获取pickupOrderPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPickupOrderPayAmount() {
        return pickupOrderPayAmount;
    }

    /**
     * 设置pickupOrderPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPickupOrderPayAmount(Double value) {
        this.pickupOrderPayAmount = value;
    }

    /**
     * 获取pickupOrderPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderPayCurrencyCode() {
        return pickupOrderPayCurrencyCode;
    }

    /**
     * 设置pickupOrderPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderPayCurrencyCode(String value) {
        this.pickupOrderPayCurrencyCode = value;
    }

    /**
     * 获取pickupOrderPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPickupOrderPayTime() {
        return pickupOrderPayTime;
    }

    /**
     * 设置pickupOrderPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderPayTime(Date value) {
        this.pickupOrderPayTime = value;
    }

    /**
     * 获取pickupOrderPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderPayUserCode() {
        return pickupOrderPayUserCode;
    }

    /**
     * 设置pickupOrderPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderPayUserCode(String value) {
        this.pickupOrderPayUserCode = value;
    }

    /**
     * 获取pickupOrderPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderPayUserName() {
        return pickupOrderPayUserName;
    }

    /**
     * 设置pickupOrderPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderPayUserName(String value) {
        this.pickupOrderPayUserName = value;
    }

    /**
     * 获取pickupOrderPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderPayUserTel() {
        return pickupOrderPayUserTel;
    }

    /**
     * 设置pickupOrderPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderPayUserTel(String value) {
        this.pickupOrderPayUserTel = value;
    }

    /**
     * 获取pickupOrderUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderUserCode() {
        return pickupOrderUserCode;
    }

    /**
     * 设置pickupOrderUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderUserCode(String value) {
        this.pickupOrderUserCode = value;
    }

    /**
     * 获取pickupOrderUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderUserName() {
        return pickupOrderUserName;
    }

    /**
     * 设置pickupOrderUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderUserName(String value) {
        this.pickupOrderUserName = value;
    }

    /**
     * 获取pickupOrderUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupOrderUserTel() {
        return pickupOrderUserTel;
    }

    /**
     * 设置pickupOrderUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderUserTel(String value) {
        this.pickupOrderUserTel = value;
    }

    /**
     * 获取pickupOrderTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPickupOrderTsActualFrom() {
        return pickupOrderTsActualFrom;
    }

    /**
     * 设置pickupOrderTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderTsActualFrom(Date value) {
        this.pickupOrderTsActualFrom = value;
    }

    /**
     * 获取pickupOrderTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPickupOrderTsActualTo() {
        return pickupOrderTsActualTo;
    }

    /**
     * 设置pickupOrderTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupOrderTsActualTo(Date value) {
        this.pickupOrderTsActualTo = value;
    }

    /**
     * 获取podConfirmedLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedLoginCode() {
        return podConfirmedLoginCode;
    }

    /**
     * 设置podConfirmedLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedLoginCode(String value) {
        this.podConfirmedLoginCode = value;
    }

    /**
     * 获取podConfirmedLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedLoginName() {
        return podConfirmedLoginName;
    }

    /**
     * 设置podConfirmedLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedLoginName(String value) {
        this.podConfirmedLoginName = value;
    }

    /**
     * 获取podConfirmedLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedLoginTel() {
        return podConfirmedLoginTel;
    }

    /**
     * 设置podConfirmedLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedLoginTel(String value) {
        this.podConfirmedLoginTel = value;
    }

    /**
     * 获取podConfirmedPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPodConfirmedPayAmount() {
        return podConfirmedPayAmount;
    }

    /**
     * 设置podConfirmedPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPodConfirmedPayAmount(Double value) {
        this.podConfirmedPayAmount = value;
    }

    /**
     * 获取podConfirmedPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedPayCurrencyCode() {
        return podConfirmedPayCurrencyCode;
    }

    /**
     * 设置podConfirmedPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedPayCurrencyCode(String value) {
        this.podConfirmedPayCurrencyCode = value;
    }

    /**
     * 获取podConfirmedPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPodConfirmedPayTime() {
        return podConfirmedPayTime;
    }

    /**
     * 设置podConfirmedPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedPayTime(Date value) {
        this.podConfirmedPayTime = value;
    }

    /**
     * 获取podConfirmedPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedPayUserCode() {
        return podConfirmedPayUserCode;
    }

    /**
     * 设置podConfirmedPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedPayUserCode(String value) {
        this.podConfirmedPayUserCode = value;
    }

    /**
     * 获取podConfirmedPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedPayUserName() {
        return podConfirmedPayUserName;
    }

    /**
     * 设置podConfirmedPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedPayUserName(String value) {
        this.podConfirmedPayUserName = value;
    }

    /**
     * 获取podConfirmedPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedPayUserTel() {
        return podConfirmedPayUserTel;
    }

    /**
     * 设置podConfirmedPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedPayUserTel(String value) {
        this.podConfirmedPayUserTel = value;
    }

    /**
     * 获取podConfirmedUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedUserCode() {
        return podConfirmedUserCode;
    }

    /**
     * 设置podConfirmedUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedUserCode(String value) {
        this.podConfirmedUserCode = value;
    }

    /**
     * 获取podConfirmedUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedUserName() {
        return podConfirmedUserName;
    }

    /**
     * 设置podConfirmedUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedUserName(String value) {
        this.podConfirmedUserName = value;
    }

    /**
     * 获取podConfirmedUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodConfirmedUserTel() {
        return podConfirmedUserTel;
    }

    /**
     * 设置podConfirmedUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedUserTel(String value) {
        this.podConfirmedUserTel = value;
    }

    /**
     * 获取podConfirmedTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPodConfirmedTsActualFrom() {
        return podConfirmedTsActualFrom;
    }

    /**
     * 设置podConfirmedTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedTsActualFrom(Date value) {
        this.podConfirmedTsActualFrom = value;
    }

    /**
     * 获取podConfirmedTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPodConfirmedTsActualTo() {
        return podConfirmedTsActualTo;
    }

    /**
     * 设置podConfirmedTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodConfirmedTsActualTo(Date value) {
        this.podConfirmedTsActualTo = value;
    }

    /**
     * 获取pointFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getPointFrom() {
        return pointFrom;
    }

    /**
     * 设置pointFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setPointFrom(GiPoint value) {
        this.pointFrom = value;
    }

    /**
     * 获取popOrderedLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedLoginCode() {
        return popOrderedLoginCode;
    }

    /**
     * 设置popOrderedLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedLoginCode(String value) {
        this.popOrderedLoginCode = value;
    }

    /**
     * 获取popOrderedLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedLoginName() {
        return popOrderedLoginName;
    }

    /**
     * 设置popOrderedLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedLoginName(String value) {
        this.popOrderedLoginName = value;
    }

    /**
     * 获取popOrderedLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedLoginTel() {
        return popOrderedLoginTel;
    }

    /**
     * 设置popOrderedLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedLoginTel(String value) {
        this.popOrderedLoginTel = value;
    }

    /**
     * 获取popOrderedPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPopOrderedPayAmount() {
        return popOrderedPayAmount;
    }

    /**
     * 设置popOrderedPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPopOrderedPayAmount(Double value) {
        this.popOrderedPayAmount = value;
    }

    /**
     * 获取popOrderedPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedPayCurrencyCode() {
        return popOrderedPayCurrencyCode;
    }

    /**
     * 设置popOrderedPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedPayCurrencyCode(String value) {
        this.popOrderedPayCurrencyCode = value;
    }

    /**
     * 获取popOrderedPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPopOrderedPayTime() {
        return popOrderedPayTime;
    }

    /**
     * 设置popOrderedPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedPayTime(Date value) {
        this.popOrderedPayTime = value;
    }

    /**
     * 获取popOrderedPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedPayUserCode() {
        return popOrderedPayUserCode;
    }

    /**
     * 设置popOrderedPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedPayUserCode(String value) {
        this.popOrderedPayUserCode = value;
    }

    /**
     * 获取popOrderedPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedPayUserName() {
        return popOrderedPayUserName;
    }

    /**
     * 设置popOrderedPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedPayUserName(String value) {
        this.popOrderedPayUserName = value;
    }

    /**
     * 获取popOrderedPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedPayUserTel() {
        return popOrderedPayUserTel;
    }

    /**
     * 设置popOrderedPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedPayUserTel(String value) {
        this.popOrderedPayUserTel = value;
    }

    /**
     * 获取popOrderedUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedUserCode() {
        return popOrderedUserCode;
    }

    /**
     * 设置popOrderedUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedUserCode(String value) {
        this.popOrderedUserCode = value;
    }

    /**
     * 获取popOrderedUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedUserName() {
        return popOrderedUserName;
    }

    /**
     * 设置popOrderedUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedUserName(String value) {
        this.popOrderedUserName = value;
    }

    /**
     * 获取popOrderedUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopOrderedUserTel() {
        return popOrderedUserTel;
    }

    /**
     * 设置popOrderedUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedUserTel(String value) {
        this.popOrderedUserTel = value;
    }

    /**
     * 获取popOrderedTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPopOrderedTsActualFrom() {
        return popOrderedTsActualFrom;
    }

    /**
     * 设置popOrderedTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedTsActualFrom(Date value) {
        this.popOrderedTsActualFrom = value;
    }

    /**
     * 获取popOrderedTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPopOrderedTsActualTo() {
        return popOrderedTsActualTo;
    }

    /**
     * 设置popOrderedTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopOrderedTsActualTo(Date value) {
        this.popOrderedTsActualTo = value;
    }

    /**
     * 获取productType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置productType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * 获取provinceFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceFrom() {
        return provinceFrom;
    }

    /**
     * 设置provinceFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceFrom(String value) {
        this.provinceFrom = value;
    }

    /**
     * 获取w1StationFromLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromLoginCode() {
        return w1StationFromLoginCode;
    }

    /**
     * 设置w1StationFromLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromLoginCode(String value) {
        this.w1StationFromLoginCode = value;
    }

    /**
     * 获取w1StationFromLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromLoginName() {
        return w1StationFromLoginName;
    }

    /**
     * 设置w1StationFromLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromLoginName(String value) {
        this.w1StationFromLoginName = value;
    }

    /**
     * 获取w1StationFromLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromLoginTel() {
        return w1StationFromLoginTel;
    }

    /**
     * 设置w1StationFromLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromLoginTel(String value) {
        this.w1StationFromLoginTel = value;
    }

    /**
     * 获取w1StationFromPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW1StationFromPayAmount() {
        return w1StationFromPayAmount;
    }

    /**
     * 设置w1StationFromPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW1StationFromPayAmount(Double value) {
        this.w1StationFromPayAmount = value;
    }

    /**
     * 获取w1StationFromPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromPayCurrencyCode() {
        return w1StationFromPayCurrencyCode;
    }

    /**
     * 设置w1StationFromPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayCurrencyCode(String value) {
        this.w1StationFromPayCurrencyCode = value;
    }

    /**
     * 获取w1StationFromPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW1StationFromPayRentalAmount() {
        return w1StationFromPayRentalAmount;
    }

    /**
     * 设置w1StationFromPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW1StationFromPayRentalAmount(Double value) {
        this.w1StationFromPayRentalAmount = value;
    }

    /**
     * 获取w1StationFromPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromPayRentalCurrencyCode() {
        return w1StationFromPayRentalCurrencyCode;
    }

    /**
     * 设置w1StationFromPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayRentalCurrencyCode(String value) {
        this.w1StationFromPayRentalCurrencyCode = value;
    }

    /**
     * 获取w1StationFromPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationFromPayRentalTime() {
        return w1StationFromPayRentalTime;
    }

    /**
     * 设置w1StationFromPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayRentalTime(Date value) {
        this.w1StationFromPayRentalTime = value;
    }

    /**
     * 获取w1StationFromPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationFromPayTime() {
        return w1StationFromPayTime;
    }

    /**
     * 设置w1StationFromPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayTime(Date value) {
        this.w1StationFromPayTime = value;
    }

    /**
     * 获取w1StationFromPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromPayUserCode() {
        return w1StationFromPayUserCode;
    }

    /**
     * 设置w1StationFromPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayUserCode(String value) {
        this.w1StationFromPayUserCode = value;
    }

    /**
     * 获取w1StationFromPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromPayUserName() {
        return w1StationFromPayUserName;
    }

    /**
     * 设置w1StationFromPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayUserName(String value) {
        this.w1StationFromPayUserName = value;
    }

    /**
     * 获取w1StationFromPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromPayUserTel() {
        return w1StationFromPayUserTel;
    }

    /**
     * 设置w1StationFromPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromPayUserTel(String value) {
        this.w1StationFromPayUserTel = value;
    }

    /**
     * 获取w1StationFromUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromUserCode() {
        return w1StationFromUserCode;
    }

    /**
     * 设置w1StationFromUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromUserCode(String value) {
        this.w1StationFromUserCode = value;
    }

    /**
     * 获取w1StationFromUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromUserName() {
        return w1StationFromUserName;
    }

    /**
     * 设置w1StationFromUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromUserName(String value) {
        this.w1StationFromUserName = value;
    }

    /**
     * 获取w1StationFromUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationFromUserTel() {
        return w1StationFromUserTel;
    }

    /**
     * 设置w1StationFromUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromUserTel(String value) {
        this.w1StationFromUserTel = value;
    }

    /**
     * 获取w1StationFromTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationFromTsActualFrom() {
        return w1StationFromTsActualFrom;
    }

    /**
     * 设置w1StationFromTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromTsActualFrom(Date value) {
        this.w1StationFromTsActualFrom = value;
    }

    /**
     * 获取w1StationFromTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationFromTsActualTo() {
        return w1StationFromTsActualTo;
    }

    /**
     * 设置w1StationFromTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationFromTsActualTo(Date value) {
        this.w1StationFromTsActualTo = value;
    }

    /**
     * 获取w1StationToLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToLoginCode() {
        return w1StationToLoginCode;
    }

    /**
     * 设置w1StationToLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToLoginCode(String value) {
        this.w1StationToLoginCode = value;
    }

    /**
     * 获取w1StationToLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToLoginName() {
        return w1StationToLoginName;
    }

    /**
     * 设置w1StationToLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToLoginName(String value) {
        this.w1StationToLoginName = value;
    }

    /**
     * 获取w1StationToLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToLoginTel() {
        return w1StationToLoginTel;
    }

    /**
     * 设置w1StationToLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToLoginTel(String value) {
        this.w1StationToLoginTel = value;
    }

    /**
     * 获取w1StationToPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW1StationToPayAmount() {
        return w1StationToPayAmount;
    }

    /**
     * 设置w1StationToPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW1StationToPayAmount(Double value) {
        this.w1StationToPayAmount = value;
    }

    /**
     * 获取w1StationToPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToPayCurrencyCode() {
        return w1StationToPayCurrencyCode;
    }

    /**
     * 设置w1StationToPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayCurrencyCode(String value) {
        this.w1StationToPayCurrencyCode = value;
    }

    /**
     * 获取w1StationToPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW1StationToPayRentalAmount() {
        return w1StationToPayRentalAmount;
    }

    /**
     * 设置w1StationToPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW1StationToPayRentalAmount(Double value) {
        this.w1StationToPayRentalAmount = value;
    }

    /**
     * 获取w1StationToPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToPayRentalCurrencyCode() {
        return w1StationToPayRentalCurrencyCode;
    }

    /**
     * 设置w1StationToPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayRentalCurrencyCode(String value) {
        this.w1StationToPayRentalCurrencyCode = value;
    }

    /**
     * 获取w1StationToPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationToPayRentalTime() {
        return w1StationToPayRentalTime;
    }

    /**
     * 设置w1StationToPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayRentalTime(Date value) {
        this.w1StationToPayRentalTime = value;
    }

    /**
     * 获取w1StationToPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationToPayTime() {
        return w1StationToPayTime;
    }

    /**
     * 设置w1StationToPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayTime(Date value) {
        this.w1StationToPayTime = value;
    }

    /**
     * 获取w1StationToPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToPayUserCode() {
        return w1StationToPayUserCode;
    }

    /**
     * 设置w1StationToPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayUserCode(String value) {
        this.w1StationToPayUserCode = value;
    }

    /**
     * 获取w1StationToPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToPayUserName() {
        return w1StationToPayUserName;
    }

    /**
     * 设置w1StationToPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayUserName(String value) {
        this.w1StationToPayUserName = value;
    }

    /**
     * 获取w1StationToPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToPayUserTel() {
        return w1StationToPayUserTel;
    }

    /**
     * 设置w1StationToPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToPayUserTel(String value) {
        this.w1StationToPayUserTel = value;
    }

    /**
     * 获取w1StationToUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToUserCode() {
        return w1StationToUserCode;
    }

    /**
     * 设置w1StationToUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToUserCode(String value) {
        this.w1StationToUserCode = value;
    }

    /**
     * 获取w1StationToUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToUserName() {
        return w1StationToUserName;
    }

    /**
     * 设置w1StationToUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToUserName(String value) {
        this.w1StationToUserName = value;
    }

    /**
     * 获取w1StationToUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW1StationToUserTel() {
        return w1StationToUserTel;
    }

    /**
     * 设置w1StationToUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToUserTel(String value) {
        this.w1StationToUserTel = value;
    }

    /**
     * 获取w1StationToTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationToTsActualFrom() {
        return w1StationToTsActualFrom;
    }

    /**
     * 设置w1StationToTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToTsActualFrom(Date value) {
        this.w1StationToTsActualFrom = value;
    }

    /**
     * 获取w1StationToTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW1StationToTsActualTo() {
        return w1StationToTsActualTo;
    }

    /**
     * 设置w1StationToTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW1StationToTsActualTo(Date value) {
        this.w1StationToTsActualTo = value;
    }

    /**
     * 获取w2StationFromLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromLoginCode() {
        return w2StationFromLoginCode;
    }

    /**
     * 设置w2StationFromLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromLoginCode(String value) {
        this.w2StationFromLoginCode = value;
    }

    /**
     * 获取w2StationFromLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromLoginName() {
        return w2StationFromLoginName;
    }

    /**
     * 设置w2StationFromLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromLoginName(String value) {
        this.w2StationFromLoginName = value;
    }

    /**
     * 获取w2StationFromLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromLoginTel() {
        return w2StationFromLoginTel;
    }

    /**
     * 设置w2StationFromLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromLoginTel(String value) {
        this.w2StationFromLoginTel = value;
    }

    /**
     * 获取w2StationFromPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW2StationFromPayAmount() {
        return w2StationFromPayAmount;
    }

    /**
     * 设置w2StationFromPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW2StationFromPayAmount(Double value) {
        this.w2StationFromPayAmount = value;
    }

    /**
     * 获取w2StationFromPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromPayCurrencyCode() {
        return w2StationFromPayCurrencyCode;
    }

    /**
     * 设置w2StationFromPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayCurrencyCode(String value) {
        this.w2StationFromPayCurrencyCode = value;
    }

    /**
     * 获取w2StationFromPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW2StationFromPayRentalAmount() {
        return w2StationFromPayRentalAmount;
    }

    /**
     * 设置w2StationFromPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW2StationFromPayRentalAmount(Double value) {
        this.w2StationFromPayRentalAmount = value;
    }

    /**
     * 获取w2StationFromPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromPayRentalCurrencyCode() {
        return w2StationFromPayRentalCurrencyCode;
    }

    /**
     * 设置w2StationFromPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayRentalCurrencyCode(String value) {
        this.w2StationFromPayRentalCurrencyCode = value;
    }

    /**
     * 获取w2StationFromPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationFromPayRentalTime() {
        return w2StationFromPayRentalTime;
    }

    /**
     * 设置w2StationFromPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayRentalTime(Date value) {
        this.w2StationFromPayRentalTime = value;
    }

    /**
     * 获取w2StationFromPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationFromPayTime() {
        return w2StationFromPayTime;
    }

    /**
     * 设置w2StationFromPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayTime(Date value) {
        this.w2StationFromPayTime = value;
    }

    /**
     * 获取w2StationFromPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromPayUserCode() {
        return w2StationFromPayUserCode;
    }

    /**
     * 设置w2StationFromPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayUserCode(String value) {
        this.w2StationFromPayUserCode = value;
    }

    /**
     * 获取w2StationFromPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromPayUserName() {
        return w2StationFromPayUserName;
    }

    /**
     * 设置w2StationFromPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayUserName(String value) {
        this.w2StationFromPayUserName = value;
    }

    /**
     * 获取w2StationFromPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromPayUserTel() {
        return w2StationFromPayUserTel;
    }

    /**
     * 设置w2StationFromPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromPayUserTel(String value) {
        this.w2StationFromPayUserTel = value;
    }

    /**
     * 获取w2StationFromUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromUserCode() {
        return w2StationFromUserCode;
    }

    /**
     * 设置w2StationFromUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromUserCode(String value) {
        this.w2StationFromUserCode = value;
    }

    /**
     * 获取w2StationFromUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromUserName() {
        return w2StationFromUserName;
    }

    /**
     * 设置w2StationFromUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromUserName(String value) {
        this.w2StationFromUserName = value;
    }

    /**
     * 获取w2StationFromUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationFromUserTel() {
        return w2StationFromUserTel;
    }

    /**
     * 设置w2StationFromUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromUserTel(String value) {
        this.w2StationFromUserTel = value;
    }

    /**
     * 获取w2StationFromTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationFromTsActualFrom() {
        return w2StationFromTsActualFrom;
    }

    /**
     * 设置w2StationFromTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromTsActualFrom(Date value) {
        this.w2StationFromTsActualFrom = value;
    }

    /**
     * 获取w2StationFromTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationFromTsActualTo() {
        return w2StationFromTsActualTo;
    }

    /**
     * 设置w2StationFromTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationFromTsActualTo(Date value) {
        this.w2StationFromTsActualTo = value;
    }

    /**
     * 获取w2StationToLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToLoginCode() {
        return w2StationToLoginCode;
    }

    /**
     * 设置w2StationToLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToLoginCode(String value) {
        this.w2StationToLoginCode = value;
    }

    /**
     * 获取w2StationToLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToLoginName() {
        return w2StationToLoginName;
    }

    /**
     * 设置w2StationToLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToLoginName(String value) {
        this.w2StationToLoginName = value;
    }

    /**
     * 获取w2StationToLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToLoginTel() {
        return w2StationToLoginTel;
    }

    /**
     * 设置w2StationToLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToLoginTel(String value) {
        this.w2StationToLoginTel = value;
    }

    /**
     * 获取w2StationToPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW2StationToPayAmount() {
        return w2StationToPayAmount;
    }

    /**
     * 设置w2StationToPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW2StationToPayAmount(Double value) {
        this.w2StationToPayAmount = value;
    }

    /**
     * 获取w2StationToPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToPayCurrencyCode() {
        return w2StationToPayCurrencyCode;
    }

    /**
     * 设置w2StationToPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayCurrencyCode(String value) {
        this.w2StationToPayCurrencyCode = value;
    }

    /**
     * 获取w2StationToPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW2StationToPayRentalAmount() {
        return w2StationToPayRentalAmount;
    }

    /**
     * 设置w2StationToPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW2StationToPayRentalAmount(Double value) {
        this.w2StationToPayRentalAmount = value;
    }

    /**
     * 获取w2StationToPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToPayRentalCurrencyCode() {
        return w2StationToPayRentalCurrencyCode;
    }

    /**
     * 设置w2StationToPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayRentalCurrencyCode(String value) {
        this.w2StationToPayRentalCurrencyCode = value;
    }

    /**
     * 获取w2StationToPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationToPayRentalTime() {
        return w2StationToPayRentalTime;
    }

    /**
     * 设置w2StationToPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayRentalTime(Date value) {
        this.w2StationToPayRentalTime = value;
    }

    /**
     * 获取w2StationToPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationToPayTime() {
        return w2StationToPayTime;
    }

    /**
     * 设置w2StationToPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayTime(Date value) {
        this.w2StationToPayTime = value;
    }

    /**
     * 获取w2StationToPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToPayUserCode() {
        return w2StationToPayUserCode;
    }

    /**
     * 设置w2StationToPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayUserCode(String value) {
        this.w2StationToPayUserCode = value;
    }

    /**
     * 获取w2StationToPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToPayUserName() {
        return w2StationToPayUserName;
    }

    /**
     * 设置w2StationToPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayUserName(String value) {
        this.w2StationToPayUserName = value;
    }

    /**
     * 获取w2StationToPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToPayUserTel() {
        return w2StationToPayUserTel;
    }

    /**
     * 设置w2StationToPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToPayUserTel(String value) {
        this.w2StationToPayUserTel = value;
    }

    /**
     * 获取w2StationToUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToUserCode() {
        return w2StationToUserCode;
    }

    /**
     * 设置w2StationToUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToUserCode(String value) {
        this.w2StationToUserCode = value;
    }

    /**
     * 获取w2StationToUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToUserName() {
        return w2StationToUserName;
    }

    /**
     * 设置w2StationToUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToUserName(String value) {
        this.w2StationToUserName = value;
    }

    /**
     * 获取w2StationToUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW2StationToUserTel() {
        return w2StationToUserTel;
    }

    /**
     * 设置w2StationToUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToUserTel(String value) {
        this.w2StationToUserTel = value;
    }

    /**
     * 获取w2StationToTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationToTsActualFrom() {
        return w2StationToTsActualFrom;
    }

    /**
     * 设置w2StationToTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToTsActualFrom(Date value) {
        this.w2StationToTsActualFrom = value;
    }

    /**
     * 获取w2StationToTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW2StationToTsActualTo() {
        return w2StationToTsActualTo;
    }

    /**
     * 设置w2StationToTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW2StationToTsActualTo(Date value) {
        this.w2StationToTsActualTo = value;
    }

    /**
     * 获取w3StationLoginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationLoginCode() {
        return w3StationLoginCode;
    }

    /**
     * 设置w3StationLoginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationLoginCode(String value) {
        this.w3StationLoginCode = value;
    }

    /**
     * 获取w3StationLoginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationLoginName() {
        return w3StationLoginName;
    }

    /**
     * 设置w3StationLoginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationLoginName(String value) {
        this.w3StationLoginName = value;
    }

    /**
     * 获取w3StationLoginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationLoginTel() {
        return w3StationLoginTel;
    }

    /**
     * 设置w3StationLoginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationLoginTel(String value) {
        this.w3StationLoginTel = value;
    }

    /**
     * 获取w3StationPayAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW3StationPayAmount() {
        return w3StationPayAmount;
    }

    /**
     * 设置w3StationPayAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW3StationPayAmount(Double value) {
        this.w3StationPayAmount = value;
    }

    /**
     * 获取w3StationPayCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationPayCurrencyCode() {
        return w3StationPayCurrencyCode;
    }

    /**
     * 设置w3StationPayCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayCurrencyCode(String value) {
        this.w3StationPayCurrencyCode = value;
    }

    /**
     * 获取w3StationPayRentalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getW3StationPayRentalAmount() {
        return w3StationPayRentalAmount;
    }

    /**
     * 设置w3StationPayRentalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setW3StationPayRentalAmount(Double value) {
        this.w3StationPayRentalAmount = value;
    }

    /**
     * 获取w3StationPayRentalCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationPayRentalCurrencyCode() {
        return w3StationPayRentalCurrencyCode;
    }

    /**
     * 设置w3StationPayRentalCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayRentalCurrencyCode(String value) {
        this.w3StationPayRentalCurrencyCode = value;
    }

    /**
     * 获取w3StationPayRentalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW3StationPayRentalTime() {
        return w3StationPayRentalTime;
    }

    /**
     * 设置w3StationPayRentalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayRentalTime(Date value) {
        this.w3StationPayRentalTime = value;
    }

    /**
     * 获取w3StationPayTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW3StationPayTime() {
        return w3StationPayTime;
    }

    /**
     * 设置w3StationPayTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayTime(Date value) {
        this.w3StationPayTime = value;
    }

    /**
     * 获取w3StationPayUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationPayUserCode() {
        return w3StationPayUserCode;
    }

    /**
     * 设置w3StationPayUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayUserCode(String value) {
        this.w3StationPayUserCode = value;
    }

    /**
     * 获取w3StationPayUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationPayUserName() {
        return w3StationPayUserName;
    }

    /**
     * 设置w3StationPayUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayUserName(String value) {
        this.w3StationPayUserName = value;
    }

    /**
     * 获取w3StationPayUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationPayUserTel() {
        return w3StationPayUserTel;
    }

    /**
     * 设置w3StationPayUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationPayUserTel(String value) {
        this.w3StationPayUserTel = value;
    }

    /**
     * 获取w3StationUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationUserCode() {
        return w3StationUserCode;
    }

    /**
     * 设置w3StationUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationUserCode(String value) {
        this.w3StationUserCode = value;
    }

    /**
     * 获取w3StationUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationUserName() {
        return w3StationUserName;
    }

    /**
     * 设置w3StationUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationUserName(String value) {
        this.w3StationUserName = value;
    }

    /**
     * 获取w3StationUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getW3StationUserTel() {
        return w3StationUserTel;
    }

    /**
     * 设置w3StationUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationUserTel(String value) {
        this.w3StationUserTel = value;
    }

    /**
     * 获取w3StationTsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW3StationTsActualFrom() {
        return w3StationTsActualFrom;
    }

    /**
     * 设置w3StationTsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationTsActualFrom(Date value) {
        this.w3StationTsActualFrom = value;
    }

    /**
     * 获取w3StationTsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getW3StationTsActualTo() {
        return w3StationTsActualTo;
    }

    /**
     * 设置w3StationTsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setW3StationTsActualTo(Date value) {
        this.w3StationTsActualTo = value;
    }

}
