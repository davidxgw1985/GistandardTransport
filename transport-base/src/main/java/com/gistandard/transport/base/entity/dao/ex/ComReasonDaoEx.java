package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComReason;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@MyBatisRepository
public interface ComReasonDaoEx {

    List<ComReason> getReasonByCondtion(@Param("dicType") Integer dicType, @Param("characterType") Integer characterType);
}
