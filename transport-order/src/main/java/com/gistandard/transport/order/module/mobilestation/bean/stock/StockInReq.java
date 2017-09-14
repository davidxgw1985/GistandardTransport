package com.gistandard.transport.order.module.mobilestation.bean.stock;

/**
 * @author: xgw
 * @ClassName: StockInReq
 * @Date: 2016/2/1
 * @Description: 入库、取件请求Bean
 */
public class StockInReq extends StockBaseReq {
    private static final long serialVersionUID = 1668850717130095325L;

    private String scanBusiBookNo;//扫描得到的订单号或派车单号
    private String boxNum;//货物板号、箱号
    private Integer roleId;
    private Integer scanOrderFrom;//扫描订单来源，1来源于批量盲扫入库，null或0 来源于单个入库

    public String getScanBusiBookNo() {
        return scanBusiBookNo;
    }

    public void setScanBusiBookNo(String scanBusiBookNo) {
        this.scanBusiBookNo = scanBusiBookNo;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getScanOrderFrom() {
        return scanOrderFrom;
    }

    public void setScanOrderFrom(Integer scanOrderFrom) {
        this.scanOrderFrom = scanOrderFrom;
    }
}
