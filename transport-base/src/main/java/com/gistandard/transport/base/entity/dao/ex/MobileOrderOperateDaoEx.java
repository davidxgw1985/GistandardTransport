package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileOrderOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface MobileOrderOperateDaoEx {

    /**
     * 根据订单号 查询订单的派送失败的原因
     *
     * @param busiBookNo
     * @return
     */
    MobileOrderOperate getMobileOrderOperate(@Param("busiBookNo") String busiBookNo, @Param("operType") Integer operType);

}