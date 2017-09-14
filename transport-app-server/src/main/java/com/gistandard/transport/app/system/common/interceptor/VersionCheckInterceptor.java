package com.gistandard.transport.app.system.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.version.bean.CheckVersionParam;
import com.gistandard.transport.app.module.version.bean.CheckVersionResult;
import com.gistandard.transport.app.module.version.service.AppVersionService;
import com.gistandard.transport.base.define.MobileStationRetCode;
import com.gistandard.transport.base.entity.bean.AppVersionInfo;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.wrapper.BodyReaderHttpServletRequestWrapper;
import com.gistandard.transport.system.utils.HttpHelper;
import com.gistandard.transport.tools.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by m on 2017/4/19.
 */
public class VersionCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String version = request.getHeader("version");
        String versionCode = request.getHeader("versionCode");
        String os = request.getHeader("os");
        String project = request.getHeader("project");
        if (StringUtils.isEmpty(version) || StringUtils.isEmpty(project) || StringUtils.isEmpty(os)) {
            return super.preHandle(request, response, handler);
        }
        AppVersionService appVersionService = (AppVersionService) SpringContextUtil.getBean("appVersionServiceImpl");
        CheckVersionParam checkVersionParam = new CheckVersionParam();
        checkVersionParam.setOs(os);
        checkVersionParam.setProject(project);
        checkVersionParam.setVersion(version);
        if (versionCode != null) {
            checkVersionParam.setVersionCode(Integer.parseInt(versionCode));
        }
        CheckVersionResult checkVersionResult = appVersionService.checkAppVersion(checkVersionParam);
        if (checkVersionResult != null && checkVersionResult.getRetCode() == 0) {
            throw new MobileStationBizException(MobileStationRetCode.VERSION_TEST_ERROR);
        }
        if (checkVersionResult.getData() != null && checkVersionResult.getData().getForceUpdate() != 0) {
            throw new MobileStationBizException(MobileStationRetCode.VERSION_UPDATE_ERROR);
        }
        return super.preHandle(request, response, handler);
    }
}
