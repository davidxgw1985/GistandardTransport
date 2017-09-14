package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: ComStationBean
 * @Date: 2016/6/11
 * @Description: 站点查询返回bean
 */
public class ComStationBean implements Serializable{
    private static final long serialVersionUID = -6475841147957058530L;

    private Integer id;//站点ID
    private Integer accountId;//站点账号ID
    private String acctUsername;//站点账号
    private String customNo;//站点编号
    private String custTtl;//站点简称
    private String custName;//站点名称
    private String custGild;//所属行业
    private String custMan;//企业法人
    private String custMantel;//联系电话
    private String custAdd;//公司地址
    private String custPost;//邮政编码
    private String country;//国家
    private String province;//省
    private String city;//市
    private String county;//区
    private String custTel;//公司电话
    private String custFax;//公司传真
    private String custMail;//电子邮件
    private String flinkMan;//第一联系人
    private String fplace;//第一联系人职务
    private String fworkTel;//第一联系人办公电话
    private String fworkFax;//第一联系人办公传真
    private String fmobile;//第一联系人移动电话
    private String femail;//第一联系人电子邮件
    private String fhometel;//第一联系人家庭电话
    private String slinkman;//第二联系人
    private String splace;//第二联系人职务
    private String sworktel;//第二联系人办公电话
    private String sworkfax;//第二联系人办公传真
    private String smobile;//第二联系人移动电话
    private String semail;//第二联系人电子邮件
    private String shometel;//第二联系人家庭电话
    private BigDecimal staLongitude;//经度
    private BigDecimal staLatitude;//纬度
    private double distance;//距离
    private String headImage;//头像路径
    private String yyzzImage;//营业执照路径
    private String imAccount;
    private String telephone;

    public String getAcctUsername() {
		return acctUsername;
	}

	public void setAcctUsername(String acctUsername) {
		this.acctUsername = acctUsername;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustGild() {
        return custGild;
    }

    public void setCustGild(String custGild) {
        this.custGild = custGild;
    }

    public String getCustMan() {
        return custMan;
    }

    public void setCustMan(String custMan) {
        this.custMan = custMan;
    }

    public String getCustMantel() {
        return custMantel;
    }

    public void setCustMantel(String custMantel) {
        this.custMantel = custMantel;
    }

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getCustPost() {
        return custPost;
    }

    public void setCustPost(String custPost) {
        this.custPost = custPost;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getCustMail() {
        return custMail;
    }

    public void setCustMail(String custMail) {
        this.custMail = custMail;
    }

    public String getFlinkMan() {
        return flinkMan;
    }

    public void setFlinkMan(String flinkMan) {
        this.flinkMan = flinkMan;
    }

    public String getFplace() {
        return fplace;
    }

    public void setFplace(String fplace) {
        this.fplace = fplace;
    }

    public String getFworkTel() {
        return fworkTel;
    }

    public void setFworkTel(String fworkTel) {
        this.fworkTel = fworkTel;
    }

    public String getFworkFax() {
        return fworkFax;
    }

    public void setFworkFax(String fworkFax) {
        this.fworkFax = fworkFax;
    }

    public String getFmobile() {
        return fmobile;
    }

    public void setFmobile(String fmobile) {
        this.fmobile = fmobile;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public String getFhometel() {
        return fhometel;
    }

    public void setFhometel(String fhometel) {
        this.fhometel = fhometel;
    }

    public String getSlinkman() {
        return slinkman;
    }

    public void setSlinkman(String slinkman) {
        this.slinkman = slinkman;
    }

    public String getSplace() {
        return splace;
    }

    public void setSplace(String splace) {
        this.splace = splace;
    }

    public String getSworktel() {
        return sworktel;
    }

    public void setSworktel(String sworktel) {
        this.sworktel = sworktel;
    }

    public String getSworkfax() {
        return sworkfax;
    }

    public void setSworkfax(String sworkfax) {
        this.sworkfax = sworkfax;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getShometel() {
        return shometel;
    }

    public void setShometel(String shometel) {
        this.shometel = shometel;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(BigDecimal staLongitude) {
        this.staLongitude = staLongitude;
    }

    public BigDecimal getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(BigDecimal staLatitude) {
        this.staLatitude = staLatitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getYyzzImage() {
        return yyzzImage;
    }

    public void setYyzzImage(String yyzzImage) {
        this.yyzzImage = yyzzImage;
    }

    public String getImAccount() {
        return imAccount;
    }

    public void setImAccount(String imAccount) {
        this.imAccount = imAccount;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
