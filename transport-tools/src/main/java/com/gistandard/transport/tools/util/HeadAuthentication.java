package com.gistandard.transport.tools.util;
/**   
* @title OfHead.java 
* @package org.wostore.vlep.common.http.head 
* @describe TODO
* @author hzong
* @datetime 2015年11月19日 上午8:41:49 
* @version v1.0   
*/ 

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/** 
* @className  imOFHead 
* @describe  请求认证
* @author  hzong
* @datetime  2015年11月19日 上午8:41:49  
*/
public class HeadAuthentication {
	private HeadAuthentication(){}
	
	/** 
	* @title getTimeStamp 
	* @describe 获取时间
	* @return 事件字符串
	* @author hzong 
	*/ 
	public static String getTimeStamp(){
		return new Date().getTime() + "";
	}

	private static final String tokenEncrypt = "000000";
	/** 
	* @title setHead 
	* @describe 设置IM头部，推荐使用有参数的setIMHead，后期会废弃当前方法
	* @appTag 应用标识,如没有请联系即时通讯开发人员。
	* 应用标识appTag参照表						
	  appName	appTag	 			
	  移动Station	0001	 				
	  独立IM	0002	 			
	  客户下单	0003	 			
	  工作空间	0005	 				
	  GPS	0004	 			
	  司机APP	0006	 				

	* @return 头部请求
	* @author hzong 
	*/ 
	public static Map<String,String> setIMHead(String appTag){
		Map<String,String> m_ss_head = new HashMap<String,String>();
		
		m_ss_head.put("TIMESTAMP", getTimeStamp());
		m_ss_head.put("TOKEN" , tokenEncrypt);
		m_ss_head.put("APPTAG", appTag);
		
		String hashdata = tokenEncrypt+m_ss_head.get("TIMESTAMP");
		m_ss_head.put("HASHDATA", MD5Util.calMd5(hashdata));
		return m_ss_head;
	}

}
