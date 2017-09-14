package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@MyBatisRepository
public interface MobileMoudleRelDaoEx {

    List<MobileMoudleRel> queryAllModule(MobileMoudleRel mobileMoudleRel);

    List<MobileMoudleRel> queryMobileMoudleRelByCondition(MobileMoudleRel mobileMoudleRel);

    List<MobileMoudleRel> queryMobileMoudleRelCompanyList(MobileMoudleRel mobileMoudleRel);

    int updateByPrimaryKeySelective(MobileMoudleRel record);

    int updateMobileMoudleRelStatus(MobileMoudleRel record);

    int deleteByPrimaryKeySelective(MobileMoudleRel record);

    int removeStaffRole(MobileMoudleRel record);
}
