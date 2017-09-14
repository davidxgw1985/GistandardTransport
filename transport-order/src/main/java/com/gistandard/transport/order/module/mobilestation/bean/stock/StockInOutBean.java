package com.gistandard.transport.order.module.mobilestation.bean.stock;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: StockInOutBean
 * @Date: 2016/5/4
 * @Description: 出入库返回Bean
 */
public class StockInOutBean implements Serializable{
    private static final long serialVersionUID = -3119891607205721323L;

    private Boolean stockFlag = false;//库存标识，入库时，true表示入库完成，可以发车；出库是表示出库完成，可以送达确认

    public Boolean isStockFlag() {
        return stockFlag;
    }

    public void setStockFlag(Boolean stockFlag) {
        this.stockFlag = stockFlag;
    }
}
