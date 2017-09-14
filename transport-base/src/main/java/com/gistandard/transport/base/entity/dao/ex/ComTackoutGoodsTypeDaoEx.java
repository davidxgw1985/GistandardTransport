package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComTackoutGoodsType;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@MyBatisRepository
public interface ComTackoutGoodsTypeDaoEx {
    List<ComTackoutGoodsType> selectAll();
}
