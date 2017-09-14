package com.gistandard.transport.app.module.version.service;

import com.gistandard.transport.app.module.version.bean.CheckVersionParam;
import com.gistandard.transport.app.module.version.bean.CheckVersionResult;

/**
 * Created by yujie on 2016/9/29.
 */
public interface AppVersionService {

    CheckVersionResult checkAppVersion(CheckVersionParam checkVersionParam);
}
