package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 账号关联附件拓展DAO
 * 
 * @author ShengHao
 * 
 *         2016-2-29上午9:03:15
 */
@MyBatisRepository
public interface BizAttachmentDaoEx {

	/**
	 * 清空当前账户对应的所有的关联附件信息
	 * 
	 * @param acctId
	 *            账户主键
	 */
	Integer deleteByAcctId(Integer acctId);

	/**
	 * 查询时间段内的冗余附件列表
	 */
	List<BizAttachment> selectUnUseAttachment(Date startDate, Date endDate);

	/**
	 * 删除时间段内的冗余附件列表
	 */
	Integer deleteUnUseAttachment(Date startDate, Date endDate);

	/**
	 * 根据账户Id查询附件
	 * 
	 * @param acctId
	 * @return
	 */
	List<BizAttachment> queryAttachByAcctId(Integer acctId);

	int deleteAttach(@Param("acctId") Integer acctId, @Param("fileType") Integer fileType);
	
	List<BizAttachment> queryAttchList(@Param("acctId") Integer acctId, @Param("fileType") Integer fileType);

	/**
	 * 批量更新附件的上传人员
	 *
	 * @param acctId
	 * @param attachIdList
	 * @return
	 */
	int batchUpdateUploadUser(@Param("acctId") int acctId, @Param("attachIdList") List<Integer> attachIdList);

	/**
	 * 根据ID批量查询附件
	 *
	 * @param idList
	 * @return
	 */
	List<BizAttachment> queryAttachByIds(List<Integer> idList);

	List<BizAttachment> querybyAttachRelId(Integer attachRelId);

	List<BizAttachment> querybyAcctId(Integer acctId);

	List<BizAttachment> querybyConditions(BizAttachment bizAttachment);

	int updateAttachRelIdbyId(BizAttachment record);

	int querybyAttachRelIdCount(Integer attachRelId);

	/**
	 * 根据关联表的字段查询附件
	 * <p>Title: getbyRelCond</p>
	 * <p>Description: </p>
	 * @param relaId
	 * @param tTab
	 * @return
	 */
	List<BizAttachment> getbyRelCond(@Param(value = "relaId") Integer relaId,@Param(value = "tTab") String tTab);
}
