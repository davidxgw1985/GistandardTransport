package com.gistandard.transport.base.define;


/**
 * Created by shenzhijun on 2016/5/27.
 */
public enum QuoteItem {
    OZG(1, "中港两地运输","OZG",1,0,1),
    OHUB(2, "站点服务","OHUB",1,0,2),
    OBDTP(3, "同城运输","OBDTP",1,0,1),
    OWLS(4, "仓储服务","OWLS",1,0,2),
    OCTYS(5, "国内转关运输","OCTYS",1,0,1),
    OCC(6, "海关申报/商检申报/海关场地","OCTYS",1,0,3),
    ZG(7, "中港两地运输","ZG",2,0,1),
    OCTPH(8, "国内普货运输","OCTPH",1,1,4),
    OZGKJ(9, "中港跨境运输","OZGKJ",1,0,1),
    OAIR(10, "空运","OAIR",1,0,1),
    OSEA(11, "海运","OSEA",1,0,1),
    OEXP(12, "快递","OEXP",1,2,4),
    BAO(13, "代理订单服务","BAO",2,0,2),
    IC(14, "保险理赔","IC",2,0,2),
    ZGKJ(15, "中港跨境运输","ZGKJ",2,0,1),
    CTYS(16, "国内长途运输","CTYS",2,1,1),
    BDTP(17, "本地提派运输","BDTP",2,0,4),
    HUB(18, "站点服务","HUB",2,0,2),
    CC(19, "海关申报/商检申报/海关场站","CC",2,0,3),
    AIR(20, "空运","AIR",2,0,1),
    SEA(21, "海运","SEA",2,0,1),
    EXP(22, "快递","EXP",2,2,1),
    WLS(23, "仓储","WLS",2,0,2),
    OHKBDTP(24, "香港本地运输","OHKBDTP",1,0,1),
    OGDBDTP(25, "广东省内运输","OGDBDTP",1,0,1),
    INHUB(28, "入库站点服务","INHUB",2,0,2),
    OUTHUB(29, "出库站点服务","OUTHUB",2,0,2),
    OTCEXP(30, "同城快递","OTCEXP",1,2,6),
    OGNEXP(31, "国内快递","OGNEXP",1,2,5),
    OGJEXP(32, "国际快递","OGJEXP",1,2,5),
    OTCYS(33, "同城运输","OTCYS",1,1,7),
    OGNLLYS(34, "国内陆路运输","OGNLLYS",1,1,7),
    OZGLDYS(35, "中港两地运输","OZGLDYS",1,1,7),

    OTCYSEX(37, "同城运输","OTCYSEX",1,1,14),
    OTCKD(38, "同城快递","OTCKD",1,2,14),
    ITCYS(39, "同城运输","ITCYS",2,1,14),
    IQJ(40, "取件","IQJ",2,1,14),
    IPJ(41, "派件","IPJ",2,1,14);

    private Integer id;
    private String itemName;
    private String itemCode;
    private Integer itemCategory;
    private Integer itemType;
    private Integer itemRule;

    QuoteItem(Integer id,String itemName,String itemCode,
              Integer itemCategory,Integer itemType,Integer itemRule){
        this.id = id;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.itemRule = itemRule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getItemRule() {
        return itemRule;
    }

    public void setItemRule(Integer itemRule) {
        this.itemRule = itemRule;
    }
}
