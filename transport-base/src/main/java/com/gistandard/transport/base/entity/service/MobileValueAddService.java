package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.MobileValueAdd;

import java.util.Map;

/**
 * 增值服务业务接口
 * @author 员伟
 */
public interface MobileValueAddService {

    Map<Integer, MobileValueAdd> queryForMap();
}
