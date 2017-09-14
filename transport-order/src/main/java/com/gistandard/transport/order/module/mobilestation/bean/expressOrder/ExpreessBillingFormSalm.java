package com.gistandard.transport.order.module.mobilestation.bean.expressOrder;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单货物信息
 * @title ExpreessOrderService 
 * @author M.simple  
 * @version 1.0
 * @datetime 2016年7月1日 上午9:42:59
 */
public class ExpreessBillingFormSalm implements Serializable{

    private static final long serialVersionUID = 8444680123894657527L;

    private Integer id;

    private Integer busiBooknoId;

    private Integer ctrlidNo;

    @Length(max=32 , message = "{BillingFormSalm.suppPartno.tooLong}")
    private String suppPartno;

    @Length(max=64 , message = "{BillingFormSalm.suppInvoiceNo.tooLong}")
    private String suppInvoiceNo;

    private String suppProdnameChs;

    private String suppProdnameEng;

    @Length(max=16 , message = "{BillingFormSalm.suppBusinessGr.tooLong}")
    private String suppBusinessGr;

    private Date primStockinnDate;

    @Length(max=32 , message = "{BillingFormSalm.purcPartno.tooLong}")
    private String purcPartno;

    @Length(max=32 , message = "{BillingFormSalm.purcInvoiceNo.tooLong}")
    private String purcInvoiceNo;

    @Length(max=64 , message = "{BillingFormSalm.purcIndentNo.tooLong}")
    private String purcIndentNo;

    private String purcIndentType;

    private String purcInventory;

    private String purcGoodsOwner;

    private String purcProdnameChs;

    private String purcProdnameEng;

    @Length(max=16 , message = "{BillingFormSalm.purcBusinessGr.tooLong}")
    private String purcBusinessGr;

    @Length(max=16 , message = "{BillingFormSalm.purcReportNo.tooLong}")
    private String purcReportNo;

    @Length(max=16 , message = "{BillingFormSalm.purcEndCustomer.tooLong}")
    private String purcEndCustomer;

    @Length(max=16 , message = "{BillingFormSalm.purcEndCustomno.tooLong}")
    private String purcEndCustomno;

    private String thrdPartno;

    @Length(max=16 , message = "{BillingFormSalm.poItemNo.tooLong}")
    private String poItemNo;

    @Length(max=16 , message = "{BillingFormSalm.suppPurcCustomno.tooLong}")
    private String suppPurcCustomno;

    @Length(max=64 , message = "{BillingFormSalm.purcSuppCustname.tooLong}")
    private String purcSuppCustname;

    @Length(max=16 , message = "{BillingFormSalm.tradeTerm.tooLong}")
    private String tradeTerm;

    @Length(max=32 , message = "{BillingFormSalm.tradeSite.tooLong}")
    private String tradeSite;

    private String madeinCounco;

    private String haikGoodsna;

    @Length(max=16 , message = "{BillingFormSalm.hscodeNo.tooLong}")
    private String hscodeNo;

    @Length(max=64 , message = "{BillingFormSalm.hscodeNachs.tooLong}")
    private String hscodeNachs;

    @Length(max=250 , message = "{BillingFormSalm.hscodeSpecs.tooLong}")
    private String hscodeSpecs;

    private Double goodsLength;
    private Double goodsWidth;
    private Double goodsHeight;

    @Max(value= 9999999999999L, message = "{BillingFormSalm.goodsPalletQty.tooLong}")
    private BigDecimal goodsPalletQty;
    @Max(value=9999999999999L, message = "{BillingFormSalm.goodsQty.tooLong}")
    private BigDecimal goodsQty;

    private String goodsQtyUnitCo;

    @Length(max=32 , message = "{BillingFormSalm.goodsPackageBoxno.tooLong}")
    private String goodsPackageBoxno;

    @Max(value=9999999999999L, message = "{BillingFormSalm.goodsPackageQty.tooLong}")
    private BigDecimal goodsPackageQty;

    private String packageUnitCo;

    @Max(value=9999999999999L, message = "{BillingFormSalm.packageUom.tooLong}")
    private BigDecimal packageUom;

    @Max(value=999999999L, message = "{BillingFormSalm.goodsPrice.tooLong}")
    private BigDecimal goodsPrice;

    @Max(value=9999999999999L, message = "{BillingFormSalm.goodsValue.tooLong}")
    private BigDecimal goodsValue;

    private String goodsCurrencyCo;

    @Max(value=9999999999999L, message = "{BillingFormSalm.goodsNetwht.tooLong}")
    private BigDecimal goodsNetwht;

    @Max(value=9999999999999L, message = "{BillingFormSalm.goodsGrosswht.tooLong}")
    private BigDecimal goodsGrosswht;

    private String weightUnitCo;

    @Length(max=32 , message = "{BillingFormSalm.makeNoticeNo.tooLong}")
    private String makeNoticeNo;

    @Length(max=32 , message = "{BillingFormSalm.makeFormNo.tooLong}")
    private String makeFormNo;

    @Max(value=9999999999999L, message = "{BillingFormSalm.makeFormPcs.tooLong}")
    private BigDecimal makeFormPcs;

    @Max(value=9999999999999L, message = "{BillingFormSalm.goodsVolcbm.tooLong}")
    private BigDecimal goodsVolcbm;

    private BigDecimal goodsVolwht;

    private BigDecimal goodsFeewht;

    private String goodsDimendesc;

    @Length(max=64 , message = "{BillingFormSalm.dateCode.tooLong}")
    private String dateCode;

    private String vmiHubidNo;

    private String destnVmiWhno;

    @Length(max=16 , message = "{BillingFormSalm.scheduTraceNo.tooLong}")
    private String scheduTraceNo;

    private String emlId;

    private String gNo;

    private String goodsStatus;

    private String poDtlid;

    private String comesalm0004;

    private String comesalm0005;

    private String comesalm0006;

    private String comesalm0007;

    private String comesalm0008;

    private String comesalm0009;

    private String comesalm0010;

    private String comesalm0013;

    private String comesalm0014;

    private String comesalm0015;

    private String comesalm0018;

    private String comesalm0019;

    private String comesalm0020;

    private String comesalm0023;

    private String comesalm0024;

    private String comesalm0027;

    private String comesalm0029;

    private String comesalm0030;

    private String comesalm0031;

    private String comesalm0039;

    private String comesalm0040;

    private BigDecimal comesalm0050;

    private BigDecimal comesalm0051;

    private BigDecimal comesalm0060;

    private BigDecimal comesalm0063;

    private BigDecimal comesalm0064;

    private BigDecimal comesalm0065;

    private BigDecimal comesalm0066;

    private BigDecimal comesalm0067;

    private BigDecimal comesalm0068;

    private BigDecimal comesalm0069;

    private BigDecimal comesalm0070;

    private BigDecimal comesalm0071;

    private BigDecimal comesalm0072;

    private BigDecimal comesalm0073;

    private BigDecimal comesalm0074;

    private BigDecimal comesalm0075;

    private BigDecimal comesalm0076;

    private BigDecimal comesalm0077;

    private BigDecimal comesalm0078;

    private BigDecimal comesalm0081;

    private BigDecimal comesalm0082;

    private BigDecimal comesalm0083;

    private BigDecimal comesalm0084;

    private Date comesalm0089;

    private Date comesalm0090;

    private Date comesalm0091;

    private Date comesalm0092;

    private Date comesalm0097;

    private Date comesalm0098;

    private Date comesalm0099;

    private Date comesalm0100;

    private Date comesalm0101;

    private Date comesalm0102;

    private String comesalm0120;

    private String declconshand;

    private Date declarDate;

    @Length(max=10 , message = "{BillingFormSalm.jjsGrade.tooLong}")
    private String jjsGrade;

    private BigDecimal predictValue;

    @Length(max=10 , message = "{BillingFormSalm.jjsVer.tooLong}")
    private String jjsVer;

    private BigDecimal predictValueHub;

    private BigDecimal goodsVolume;

    private String goodsVolumeUnit;

    @Length(max=128 , message = "{BillingFormSalm.remark.tooLong}")
    private String remark;

    private String tCode;

    @Length(max=50 , message = "{BillingFormSalm.hubHandbookNo.tooLong}")
    private String hubHandbookNo;

    @Length(max=50 , message = "{BillingFormSalm.hubContractNo.tooLong}")
    private String hubContractNo;

    @Length(max=50 , message = "{BillingFormSalm.hubContractNum.tooLong}")
    private String hubContractNum;

    private String oldSalebookno;

    @Length(max=250 , message = "{BillingFormSalm.comdityIdscr.tooLong}")
    private String comdityIdscr;

    @Length(max=255 , message = "{BillingFormSalm.lotno.tooLong}")
    private String lotno;

    private BigDecimal actualWight;

    private BigDecimal actualVolume;

    private String vPn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiBooknoId() {
        return busiBooknoId;
    }

    public void setBusiBooknoId(Integer busiBooknoId) {
        this.busiBooknoId = busiBooknoId;
    }

    public Integer getCtrlidNo() {
        return ctrlidNo;
    }

    public void setCtrlidNo(Integer ctrlidNo) {
        this.ctrlidNo = ctrlidNo;
    }

    public String getSuppPartno() {
        return suppPartno;
    }

    public void setSuppPartno(String suppPartno) {
        this.suppPartno = suppPartno;
    }

    public String getSuppInvoiceNo() {
        return suppInvoiceNo;
    }

    public void setSuppInvoiceNo(String suppInvoiceNo) {
        this.suppInvoiceNo = suppInvoiceNo;
    }

    public String getSuppProdnameChs() {
        return suppProdnameChs;
    }

    public void setSuppProdnameChs(String suppProdnameChs) {
        this.suppProdnameChs = suppProdnameChs;
    }

    public String getSuppProdnameEng() {
        return suppProdnameEng;
    }

    public void setSuppProdnameEng(String suppProdnameEng) {
        this.suppProdnameEng = suppProdnameEng;
    }

    public String getSuppBusinessGr() {
        return suppBusinessGr;
    }

    public void setSuppBusinessGr(String suppBusinessGr) {
        this.suppBusinessGr = suppBusinessGr;
    }

    public Date getPrimStockinnDate() {
        return primStockinnDate;
    }

    public void setPrimStockinnDate(Date primStockinnDate) {
        this.primStockinnDate = primStockinnDate;
    }

    public String getPurcPartno() {
        return purcPartno;
    }

    public void setPurcPartno(String purcPartno) {
        this.purcPartno = purcPartno;
    }

    public String getPurcInvoiceNo() {
        return purcInvoiceNo;
    }

    public void setPurcInvoiceNo(String purcInvoiceNo) {
        this.purcInvoiceNo = purcInvoiceNo;
    }

    public String getPurcIndentNo() {
        return purcIndentNo;
    }

    public void setPurcIndentNo(String purcIndentNo) {
        this.purcIndentNo = purcIndentNo;
    }

    public String getPurcIndentType() {
        return purcIndentType;
    }

    public void setPurcIndentType(String purcIndentType) {
        this.purcIndentType = purcIndentType;
    }

    public String getPurcInventory() {
        return purcInventory;
    }

    public void setPurcInventory(String purcInventory) {
        this.purcInventory = purcInventory;
    }

    public String getPurcGoodsOwner() {
        return purcGoodsOwner;
    }

    public void setPurcGoodsOwner(String purcGoodsOwner) {
        this.purcGoodsOwner = purcGoodsOwner;
    }

    public String getPurcProdnameChs() {
        return purcProdnameChs;
    }

    public void setPurcProdnameChs(String purcProdnameChs) {
        this.purcProdnameChs = purcProdnameChs;
    }

    public String getPurcProdnameEng() {
        return purcProdnameEng;
    }

    public void setPurcProdnameEng(String purcProdnameEng) {
        this.purcProdnameEng = purcProdnameEng;
    }

    public String getPurcBusinessGr() {
        return purcBusinessGr;
    }

    public void setPurcBusinessGr(String purcBusinessGr) {
        this.purcBusinessGr = purcBusinessGr;
    }

    public String getPurcReportNo() {
        return purcReportNo;
    }

    public void setPurcReportNo(String purcReportNo) {
        this.purcReportNo = purcReportNo;
    }

    public String getPurcEndCustomer() {
        return purcEndCustomer;
    }

    public void setPurcEndCustomer(String purcEndCustomer) {
        this.purcEndCustomer = purcEndCustomer;
    }

    public String getPurcEndCustomno() {
        return purcEndCustomno;
    }

    public void setPurcEndCustomno(String purcEndCustomno) {
        this.purcEndCustomno = purcEndCustomno;
    }

    public String getThrdPartno() {
        return thrdPartno;
    }

    public void setThrdPartno(String thrdPartno) {
        this.thrdPartno = thrdPartno;
    }

    public String getPoItemNo() {
        return poItemNo;
    }

    public void setPoItemNo(String poItemNo) {
        this.poItemNo = poItemNo;
    }

    public String getSuppPurcCustomno() {
        return suppPurcCustomno;
    }

    public void setSuppPurcCustomno(String suppPurcCustomno) {
        this.suppPurcCustomno = suppPurcCustomno;
    }

    public String getPurcSuppCustname() {
        return purcSuppCustname;
    }

    public void setPurcSuppCustname(String purcSuppCustname) {
        this.purcSuppCustname = purcSuppCustname;
    }

    public String getTradeTerm() {
        return tradeTerm;
    }

    public void setTradeTerm(String tradeTerm) {
        this.tradeTerm = tradeTerm;
    }

    public String getTradeSite() {
        return tradeSite;
    }

    public void setTradeSite(String tradeSite) {
        this.tradeSite = tradeSite;
    }

    public String getMadeinCounco() {
        return madeinCounco;
    }

    public void setMadeinCounco(String madeinCounco) {
        this.madeinCounco = madeinCounco;
    }

    public String getHaikGoodsna() {
        return haikGoodsna;
    }

    public void setHaikGoodsna(String haikGoodsna) {
        this.haikGoodsna = haikGoodsna;
    }

    public String getHscodeNo() {
        return hscodeNo;
    }

    public void setHscodeNo(String hscodeNo) {
        this.hscodeNo = hscodeNo;
    }

    public String getHscodeNachs() {
        return hscodeNachs;
    }

    public void setHscodeNachs(String hscodeNachs) {
        this.hscodeNachs = hscodeNachs;
    }

    public String getHscodeSpecs() {
        return hscodeSpecs;
    }

    public void setHscodeSpecs(String hscodeSpecs) {
        this.hscodeSpecs = hscodeSpecs;
    }

    public BigDecimal getGoodsPalletQty() {
        return goodsPalletQty;
    }

    public void setGoodsPalletQty(BigDecimal goodsPalletQty) {
        this.goodsPalletQty = goodsPalletQty;
    }

    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(BigDecimal goodsQty) {
        this.goodsQty = goodsQty;
    }

    public String getGoodsQtyUnitCo() {
        return goodsQtyUnitCo;
    }

    public void setGoodsQtyUnitCo(String goodsQtyUnitCo) {
        this.goodsQtyUnitCo = goodsQtyUnitCo;
    }

    public String getGoodsPackageBoxno() {
        return goodsPackageBoxno;
    }

    public void setGoodsPackageBoxno(String goodsPackageBoxno) {
        this.goodsPackageBoxno = goodsPackageBoxno;
    }

    public BigDecimal getGoodsPackageQty() {
        return goodsPackageQty;
    }

    public void setGoodsPackageQty(BigDecimal goodsPackageQty) {
        this.goodsPackageQty = goodsPackageQty;
    }

    public String getPackageUnitCo() {
        return packageUnitCo;
    }

    public void setPackageUnitCo(String packageUnitCo) {
        this.packageUnitCo = packageUnitCo;
    }

    public BigDecimal getPackageUom() {
        return packageUom;
    }

    public void setPackageUom(BigDecimal packageUom) {
        this.packageUom = packageUom;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getGoodsCurrencyCo() {
        return goodsCurrencyCo;
    }

    public void setGoodsCurrencyCo(String goodsCurrencyCo) {
        this.goodsCurrencyCo = goodsCurrencyCo;
    }

    public BigDecimal getGoodsNetwht() {
        return goodsNetwht;
    }

    public void setGoodsNetwht(BigDecimal goodsNetwht) {
        this.goodsNetwht = goodsNetwht;
    }

    public BigDecimal getGoodsGrosswht() {
        return goodsGrosswht;
    }

    public void setGoodsGrosswht(BigDecimal goodsGrosswht) {
        this.goodsGrosswht = goodsGrosswht;
    }

    public String getWeightUnitCo() {
        return weightUnitCo;
    }

    public void setWeightUnitCo(String weightUnitCo) {
        this.weightUnitCo = weightUnitCo;
    }

    public String getMakeNoticeNo() {
        return makeNoticeNo;
    }

    public void setMakeNoticeNo(String makeNoticeNo) {
        this.makeNoticeNo = makeNoticeNo;
    }

    public String getMakeFormNo() {
        return makeFormNo;
    }

    public void setMakeFormNo(String makeFormNo) {
        this.makeFormNo = makeFormNo;
    }

    public BigDecimal getMakeFormPcs() {
        return makeFormPcs;
    }

    public void setMakeFormPcs(BigDecimal makeFormPcs) {
        this.makeFormPcs = makeFormPcs;
    }

    public BigDecimal getGoodsVolcbm() {
        return goodsVolcbm;
    }

    public void setGoodsVolcbm(BigDecimal goodsVolcbm) {
        this.goodsVolcbm = goodsVolcbm;
    }

    public BigDecimal getGoodsVolwht() {
        return goodsVolwht;
    }

    public void setGoodsVolwht(BigDecimal goodsVolwht) {
        this.goodsVolwht = goodsVolwht;
    }

    public BigDecimal getGoodsFeewht() {
        return goodsFeewht;
    }

    public void setGoodsFeewht(BigDecimal goodsFeewht) {
        this.goodsFeewht = goodsFeewht;
    }

    public String getGoodsDimendesc() {
        return goodsDimendesc;
    }

    public void setGoodsDimendesc(String goodsDimendesc) {
        this.goodsDimendesc = goodsDimendesc;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getVmiHubidNo() {
        return vmiHubidNo;
    }

    public void setVmiHubidNo(String vmiHubidNo) {
        this.vmiHubidNo = vmiHubidNo;
    }

    public String getDestnVmiWhno() {
        return destnVmiWhno;
    }

    public void setDestnVmiWhno(String destnVmiWhno) {
        this.destnVmiWhno = destnVmiWhno;
    }

    public String getScheduTraceNo() {
        return scheduTraceNo;
    }

    public void setScheduTraceNo(String scheduTraceNo) {
        this.scheduTraceNo = scheduTraceNo;
    }

    public String getEmlId() {
        return emlId;
    }

    public void setEmlId(String emlId) {
        this.emlId = emlId;
    }

    public String getgNo() {
        return gNo;
    }

    public void setgNo(String gNo) {
        this.gNo = gNo;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getPoDtlid() {
        return poDtlid;
    }

    public void setPoDtlid(String poDtlid) {
        this.poDtlid = poDtlid;
    }

    public String getComesalm0004() {
        return comesalm0004;
    }

    public void setComesalm0004(String comesalm0004) {
        this.comesalm0004 = comesalm0004;
    }

    public String getComesalm0005() {
        return comesalm0005;
    }

    public void setComesalm0005(String comesalm0005) {
        this.comesalm0005 = comesalm0005;
    }

    public String getComesalm0006() {
        return comesalm0006;
    }

    public void setComesalm0006(String comesalm0006) {
        this.comesalm0006 = comesalm0006;
    }

    public String getComesalm0007() {
        return comesalm0007;
    }

    public void setComesalm0007(String comesalm0007) {
        this.comesalm0007 = comesalm0007;
    }

    public String getComesalm0008() {
        return comesalm0008;
    }

    public void setComesalm0008(String comesalm0008) {
        this.comesalm0008 = comesalm0008;
    }

    public String getComesalm0009() {
        return comesalm0009;
    }

    public void setComesalm0009(String comesalm0009) {
        this.comesalm0009 = comesalm0009;
    }

    public String getComesalm0010() {
        return comesalm0010;
    }

    public void setComesalm0010(String comesalm0010) {
        this.comesalm0010 = comesalm0010;
    }

    public String getComesalm0013() {
        return comesalm0013;
    }

    public void setComesalm0013(String comesalm0013) {
        this.comesalm0013 = comesalm0013;
    }

    public String getComesalm0014() {
        return comesalm0014;
    }

    public void setComesalm0014(String comesalm0014) {
        this.comesalm0014 = comesalm0014;
    }

    public String getComesalm0015() {
        return comesalm0015;
    }

    public void setComesalm0015(String comesalm0015) {
        this.comesalm0015 = comesalm0015;
    }

    public String getComesalm0018() {
        return comesalm0018;
    }

    public void setComesalm0018(String comesalm0018) {
        this.comesalm0018 = comesalm0018;
    }

    public String getComesalm0019() {
        return comesalm0019;
    }

    public void setComesalm0019(String comesalm0019) {
        this.comesalm0019 = comesalm0019;
    }

    public String getComesalm0020() {
        return comesalm0020;
    }

    public void setComesalm0020(String comesalm0020) {
        this.comesalm0020 = comesalm0020;
    }

    public String getComesalm0023() {
        return comesalm0023;
    }

    public void setComesalm0023(String comesalm0023) {
        this.comesalm0023 = comesalm0023;
    }

    public String getComesalm0024() {
        return comesalm0024;
    }

    public void setComesalm0024(String comesalm0024) {
        this.comesalm0024 = comesalm0024;
    }

    public String getComesalm0027() {
        return comesalm0027;
    }

    public void setComesalm0027(String comesalm0027) {
        this.comesalm0027 = comesalm0027;
    }

    public String getComesalm0029() {
        return comesalm0029;
    }

    public void setComesalm0029(String comesalm0029) {
        this.comesalm0029 = comesalm0029;
    }

    public String getComesalm0030() {
        return comesalm0030;
    }

    public void setComesalm0030(String comesalm0030) {
        this.comesalm0030 = comesalm0030;
    }

    public String getComesalm0031() {
        return comesalm0031;
    }

    public void setComesalm0031(String comesalm0031) {
        this.comesalm0031 = comesalm0031;
    }

    public String getComesalm0039() {
        return comesalm0039;
    }

    public void setComesalm0039(String comesalm0039) {
        this.comesalm0039 = comesalm0039;
    }

    public String getComesalm0040() {
        return comesalm0040;
    }

    public void setComesalm0040(String comesalm0040) {
        this.comesalm0040 = comesalm0040;
    }

    public BigDecimal getComesalm0050() {
        return comesalm0050;
    }

    public void setComesalm0050(BigDecimal comesalm0050) {
        this.comesalm0050 = comesalm0050;
    }

    public BigDecimal getComesalm0051() {
        return comesalm0051;
    }

    public void setComesalm0051(BigDecimal comesalm0051) {
        this.comesalm0051 = comesalm0051;
    }

    public BigDecimal getComesalm0060() {
        return comesalm0060;
    }

    public void setComesalm0060(BigDecimal comesalm0060) {
        this.comesalm0060 = comesalm0060;
    }

    public BigDecimal getComesalm0063() {
        return comesalm0063;
    }

    public void setComesalm0063(BigDecimal comesalm0063) {
        this.comesalm0063 = comesalm0063;
    }

    public BigDecimal getComesalm0064() {
        return comesalm0064;
    }

    public void setComesalm0064(BigDecimal comesalm0064) {
        this.comesalm0064 = comesalm0064;
    }

    public BigDecimal getComesalm0065() {
        return comesalm0065;
    }

    public void setComesalm0065(BigDecimal comesalm0065) {
        this.comesalm0065 = comesalm0065;
    }

    public BigDecimal getComesalm0066() {
        return comesalm0066;
    }

    public void setComesalm0066(BigDecimal comesalm0066) {
        this.comesalm0066 = comesalm0066;
    }

    public BigDecimal getComesalm0067() {
        return comesalm0067;
    }

    public void setComesalm0067(BigDecimal comesalm0067) {
        this.comesalm0067 = comesalm0067;
    }

    public BigDecimal getComesalm0068() {
        return comesalm0068;
    }

    public void setComesalm0068(BigDecimal comesalm0068) {
        this.comesalm0068 = comesalm0068;
    }

    public BigDecimal getComesalm0069() {
        return comesalm0069;
    }

    public void setComesalm0069(BigDecimal comesalm0069) {
        this.comesalm0069 = comesalm0069;
    }

    public BigDecimal getComesalm0070() {
        return comesalm0070;
    }

    public void setComesalm0070(BigDecimal comesalm0070) {
        this.comesalm0070 = comesalm0070;
    }

    public BigDecimal getComesalm0071() {
        return comesalm0071;
    }

    public void setComesalm0071(BigDecimal comesalm0071) {
        this.comesalm0071 = comesalm0071;
    }

    public BigDecimal getComesalm0072() {
        return comesalm0072;
    }

    public void setComesalm0072(BigDecimal comesalm0072) {
        this.comesalm0072 = comesalm0072;
    }

    public BigDecimal getComesalm0073() {
        return comesalm0073;
    }

    public void setComesalm0073(BigDecimal comesalm0073) {
        this.comesalm0073 = comesalm0073;
    }

    public BigDecimal getComesalm0074() {
        return comesalm0074;
    }

    public void setComesalm0074(BigDecimal comesalm0074) {
        this.comesalm0074 = comesalm0074;
    }

    public BigDecimal getComesalm0075() {
        return comesalm0075;
    }

    public void setComesalm0075(BigDecimal comesalm0075) {
        this.comesalm0075 = comesalm0075;
    }

    public BigDecimal getComesalm0076() {
        return comesalm0076;
    }

    public void setComesalm0076(BigDecimal comesalm0076) {
        this.comesalm0076 = comesalm0076;
    }

    public BigDecimal getComesalm0077() {
        return comesalm0077;
    }

    public void setComesalm0077(BigDecimal comesalm0077) {
        this.comesalm0077 = comesalm0077;
    }

    public BigDecimal getComesalm0078() {
        return comesalm0078;
    }

    public void setComesalm0078(BigDecimal comesalm0078) {
        this.comesalm0078 = comesalm0078;
    }

    public BigDecimal getComesalm0081() {
        return comesalm0081;
    }

    public void setComesalm0081(BigDecimal comesalm0081) {
        this.comesalm0081 = comesalm0081;
    }

    public BigDecimal getComesalm0082() {
        return comesalm0082;
    }

    public void setComesalm0082(BigDecimal comesalm0082) {
        this.comesalm0082 = comesalm0082;
    }

    public BigDecimal getComesalm0083() {
        return comesalm0083;
    }

    public void setComesalm0083(BigDecimal comesalm0083) {
        this.comesalm0083 = comesalm0083;
    }

    public BigDecimal getComesalm0084() {
        return comesalm0084;
    }

    public void setComesalm0084(BigDecimal comesalm0084) {
        this.comesalm0084 = comesalm0084;
    }

    public Date getComesalm0089() {
        return comesalm0089;
    }

    public void setComesalm0089(Date comesalm0089) {
        this.comesalm0089 = comesalm0089;
    }

    public Date getComesalm0090() {
        return comesalm0090;
    }

    public void setComesalm0090(Date comesalm0090) {
        this.comesalm0090 = comesalm0090;
    }

    public Date getComesalm0091() {
        return comesalm0091;
    }

    public void setComesalm0091(Date comesalm0091) {
        this.comesalm0091 = comesalm0091;
    }

    public Date getComesalm0092() {
        return comesalm0092;
    }

    public void setComesalm0092(Date comesalm0092) {
        this.comesalm0092 = comesalm0092;
    }

    public Date getComesalm0097() {
        return comesalm0097;
    }

    public void setComesalm0097(Date comesalm0097) {
        this.comesalm0097 = comesalm0097;
    }

    public Date getComesalm0098() {
        return comesalm0098;
    }

    public void setComesalm0098(Date comesalm0098) {
        this.comesalm0098 = comesalm0098;
    }

    public Date getComesalm0099() {
        return comesalm0099;
    }

    public void setComesalm0099(Date comesalm0099) {
        this.comesalm0099 = comesalm0099;
    }

    public Date getComesalm0100() {
        return comesalm0100;
    }

    public void setComesalm0100(Date comesalm0100) {
        this.comesalm0100 = comesalm0100;
    }

    public Date getComesalm0101() {
        return comesalm0101;
    }

    public void setComesalm0101(Date comesalm0101) {
        this.comesalm0101 = comesalm0101;
    }

    public Date getComesalm0102() {
        return comesalm0102;
    }

    public void setComesalm0102(Date comesalm0102) {
        this.comesalm0102 = comesalm0102;
    }

    public String getComesalm0120() {
        return comesalm0120;
    }

    public void setComesalm0120(String comesalm0120) {
        this.comesalm0120 = comesalm0120;
    }

    public String getDeclconshand() {
        return declconshand;
    }

    public void setDeclconshand(String declconshand) {
        this.declconshand = declconshand;
    }

    public Date getDeclarDate() {
        return declarDate;
    }

    public void setDeclarDate(Date declarDate) {
        this.declarDate = declarDate;
    }

    public String getJjsGrade() {
        return jjsGrade;
    }

    public void setJjsGrade(String jjsGrade) {
        this.jjsGrade = jjsGrade;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getJjsVer() {
        return jjsVer;
    }

    public void setJjsVer(String jjsVer) {
        this.jjsVer = jjsVer;
    }

    public BigDecimal getPredictValueHub() {
        return predictValueHub;
    }

    public void setPredictValueHub(BigDecimal predictValueHub) {
        this.predictValueHub = predictValueHub;
    }

    public BigDecimal getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(BigDecimal goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsVolumeUnit() {
        return goodsVolumeUnit;
    }

    public void setGoodsVolumeUnit(String goodsVolumeUnit) {
        this.goodsVolumeUnit = goodsVolumeUnit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String getHubHandbookNo() {
        return hubHandbookNo;
    }

    public void setHubHandbookNo(String hubHandbookNo) {
        this.hubHandbookNo = hubHandbookNo;
    }

    public String getHubContractNo() {
        return hubContractNo;
    }

    public void setHubContractNo(String hubContractNo) {
        this.hubContractNo = hubContractNo;
    }

    public String getHubContractNum() {
        return hubContractNum;
    }

    public void setHubContractNum(String hubContractNum) {
        this.hubContractNum = hubContractNum;
    }

    public String getOldSalebookno() {
        return oldSalebookno;
    }

    public void setOldSalebookno(String oldSalebookno) {
        this.oldSalebookno = oldSalebookno;
    }

    public String getComdityIdscr() {
        return comdityIdscr;
    }

    public void setComdityIdscr(String comdityIdscr) {
        this.comdityIdscr = comdityIdscr;
    }

    public String getLotno() {
        return lotno;
    }

    public void setLotno(String lotno) {
        this.lotno = lotno;
    }

    public BigDecimal getActualWight() {
        return actualWight;
    }

    public void setActualWight(BigDecimal actualWight) {
        this.actualWight = actualWight;
    }

    public BigDecimal getActualVolume() {
        return actualVolume;
    }

    public void setActualVolume(BigDecimal actualVolume) {
        this.actualVolume = actualVolume;
    }

    public String getvPn() {
        return vPn;
    }

    public void setvPn(String vPn) {
        this.vPn = vPn;
    }


    public Double getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(Double goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public Double getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(Double goodsLength) {
        this.goodsLength = goodsLength;
    }

    public Double getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(Double goodsWidth) {
        this.goodsWidth = goodsWidth;
    }
}