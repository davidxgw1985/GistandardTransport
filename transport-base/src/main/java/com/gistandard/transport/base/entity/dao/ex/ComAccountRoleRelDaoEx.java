package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;

import java.util.List;

/**
 * Created by m on 2016/9/29.
 */
@MyBatisRepository
public interface ComAccountRoleRelDaoEx {
    /**
     * 根据账户ID查询所有记录
     * @param accountId
     * @return
     */
    List<ComAccountRoleRel> queryByAccountId(Integer accountId);

    int batchInsert(List<ComAccountRoleRel> list);

    List<Integer> getUserRoleIdByAccountId(Integer accountId);

    /**
     * 根据账户删除账号角色关联表信息
     *
     * @param accountId
     *            账户ID
     */
    Integer deleteByAccountId(Integer accountId);
}
