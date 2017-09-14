package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ExpreessFileUploadRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ExpreessFileUploadRecordDaoEx {

    /**
     * 根据订单号和账户查询该订单上传的图片信息
     * @param busiBookNo 订单号
     * @param acctUsername 账户O2ID
     * @return 该订单上传的图片信息列表
     */
    List<String> queryOrderUploadPath(@Param("busiBookNo") String busiBookNo, @Param("acctUsername") String acctUsername);


}