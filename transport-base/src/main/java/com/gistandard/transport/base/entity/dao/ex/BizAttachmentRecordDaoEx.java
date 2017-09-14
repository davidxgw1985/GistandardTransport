package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.BizAttachmentRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface BizAttachmentRecordDaoEx {

    /**
     * 根据上传者查询
     * @param upLoadUser
     * @return
     */
    List<BizAttachmentRecord> queryByUploadUser(Integer upLoadUser);

    /**
     * 根据上传者删除
     * @param upLoadUser
     * @return
     */
    Integer deleteByUploadUser(Integer upLoadUser);

    /**
     * 根据上传者和类型删除
     * @param acctId
     * @param fileType
     * @return
     */
    Integer deleteByAcctIdAndFileType(@Param("acctId") Integer acctId, @Param("fileType") Integer fileType);

	/**
	 * 查询修改的附件信息
	 * 
	 * @param acctId
	 * @param fileType
	 * @return
	 */
	List<BizAttachmentRecord> queryRecordAttch(@Param("acctId") Integer acctId, @Param("fileType") Integer fileType);
}