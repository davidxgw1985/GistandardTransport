package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;

import java.io.Serializable;
import java.util.List;

/**
 * MobileStation 3.0 I单子订单参数
* @className  MobileScheduOrderBean 
* @describe  TODO
* @author  M.simple 
* @datetime  2016年6月13日 下午5:52:28
* @version MobileStation 3.0
 */
public class MobileScheduSubOrderBean extends MobileScheduSubOrder implements Serializable{
	
	/** 
	* @fieldName serialVersionUID
	* @describe TODO
	* @fieldType long
	*/ 
	private static final long serialVersionUID = 570991188547219657L;
	
    
    /**
     * 获取类型 
     * recordOrderType=1 货物类型直接关联订单，无子订单
     * recordOrderType=0 该信息为null,获取类型在mobileScheduOrderList
     */
    private List<MobileGoodsDtl> mobileGoodDtlList;

	private String docno;

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public List<MobileGoodsDtl> getMobileGoodDtlList() {
		return mobileGoodDtlList;
	}


	public void setMobileGoodDtlList(List<MobileGoodsDtl> mobileGoodDtlList) {
		this.mobileGoodDtlList = mobileGoodDtlList;
	} 

}
