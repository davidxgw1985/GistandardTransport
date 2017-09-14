package com.gistandard.transport.webdubbo.auditAccount.service;


import com.gistandard.transport.webdubbo.auditAccount.bean.*;

import java.util.List;

/**
 * 帐号审核服务
 */
public interface AuditAccountService {

	/**
	 * 根据查询条件查询审核列表
	 * @param auditAccountParaBean
	 * @return
	 */
	ServiceResultBean<TableResultBean> queryAuditAccountList(AuditAccountParaBean auditAccountParaBean);

	/**
	 * 根据帐号和申请单id查询对应的详细信息
	 * @param o2Id
	 * @param requestId
	 * @return
	 */
	ServiceResultBean<AuditDetailInfo> queryAuditAccountDetail(String o2Id, Integer requestId);

	/**
	 * 审核后，调用该接口做后置处理
	 * @param auditAccountBean
	 * @return
	 */
	ServiceResultBean<Boolean> auditAccount(AuditAccountBean auditAccountBean);

	/**
	 * 根据帐号查询最新的一条申请记录
	 * @param o2Id
	 * @return
	 */
	ServiceResultBean<AccountRequestBean> queryLastAccountRequest(String o2Id);

	/**
	 * 根据所有帐号类型
	 * @return
	 */
	ServiceResultBean<List<AccountCategoryBean>> queryAccountCategory();

	/**
	 * 更新申请单的状态
	 * @return
	 */
	ServiceResultBean<Boolean> changeRequestState(ChangeAccountRequestBean changeAccountRequestBean);

	/**
	 * 查询升级时使用到的单位枚举值
	 * @return
	 */
	ServiceResultBean<UnitBean> queryAllUnit();

	/**
	 * 添加申请单
	 * @return
	 */
	ServiceResultBean<Integer> addRequest(AddRequestBean addRequestBean);

	/**
	 * 查询所有的商业中心的帐号信息
	 * @param telCode
	 * @return
	 */
	ServiceResultBean<List<BusinessCenterAccountInfo>> queryAllBusinessCenterAccount(String telCode);

	/**
	 * 上传附件
	 * @param bizAttachmentInfo
	 * @return
	 */
	ServiceResultBean<BizAttachmentInfo> fileUpload(BizAttachmentInfo bizAttachmentInfo);
}
