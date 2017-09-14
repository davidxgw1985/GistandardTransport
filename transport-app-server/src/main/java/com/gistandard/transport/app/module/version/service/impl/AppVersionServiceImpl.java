package com.gistandard.transport.app.module.version.service.impl;

import com.gistandard.transport.app.module.version.bean.CheckVersionParam;
import com.gistandard.transport.app.module.version.bean.CheckVersionResult;
import com.gistandard.transport.app.module.version.bean.VersionInfo;
import com.gistandard.transport.app.module.version.service.AppVersionService;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.AppVersionInfo;
import com.gistandard.transport.base.entity.dao.ex.AppVersionInfoDaoEx;
import com.gistandard.transport.tools.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by yujie on 2016/9/29.
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionInfoDaoEx appVersionInfoDaoEx;
    @Value("#{spring.orderTest}")
    private Boolean orderTest;   //压力测试开关，true 可以进行压测

    @Override
    public CheckVersionResult checkAppVersion(CheckVersionParam checkVersionParam) {
        CheckVersionResult baseResBean = new CheckVersionResult(checkVersionParam);
        if (orderTest != null && !orderTest) {
            if ("100.100.112".equals(checkVersionParam.getVersion())) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("当前不能使用压测版本！");
                return baseResBean;
            }
        }
        AppVersionInfo appVersionInfo = appVersionInfoDaoEx.queryVersionByConditions(checkVersionParam.getOs(), checkVersionParam.getProject());
        VersionInfo versionInfo = new VersionInfo();
        if (!StringUtil.isEmpty(appVersionInfo)) {
            if (checkVersionParam.getVersionCode() != null && checkVersionParam.getVersionCode() != 0 && appVersionInfo.getVersionCode() != null) {
                if (appVersionInfo.getVersionCode() > checkVersionParam.getVersionCode()) {
                    versionInfo.setAction(1);
                    versionInfo.setFilePath(appVersionInfo.getFilePath());
                    versionInfo.setForceUpdate(appVersionInfo.getForceUpdate());
                    versionInfo.setVersion(appVersionInfo.getVersion());
                }
            } else {
                String[] curVersion = checkVersionParam.getVersion().split("\\.");
                String[] serverVersion = appVersionInfo.getVersion().split("\\.");
                int curTemp, serverTemp;
                for (int i = 0; i < curVersion.length; i++) {
                    curTemp = Integer.parseInt(curVersion[i]);//app当前版本
                    serverTemp = Integer.parseInt(serverVersion[i]);//服务端版本
                    if (curTemp < serverTemp) {
                        versionInfo.setAction(1);
                        versionInfo.setFilePath(appVersionInfo.getFilePath());
                        if (serverTemp > curTemp + 1) {//如果当前版本低于服务器超过1个版本，强制更新，否则按照数据库配置是否强制更新字段判断
                            versionInfo.setForceUpdate(1);
                        } else {
                            versionInfo.setForceUpdate(appVersionInfo.getForceUpdate());
                        }
                        versionInfo.setVersion(appVersionInfo.getVersion());
                        break;
                    } else if (curTemp > serverTemp) {
                        versionInfo.setAction(0);
                        break;
                    }
                }
            }
        }
        baseResBean.setData(versionInfo);
        return baseResBean;
    }
}
