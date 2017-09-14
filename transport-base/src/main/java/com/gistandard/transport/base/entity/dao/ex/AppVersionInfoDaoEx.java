package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.AppVersionInfo;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface AppVersionInfoDaoEx {
    AppVersionInfo queryVersionByConditions(@Param("os") String os,@Param("project")String project);
}