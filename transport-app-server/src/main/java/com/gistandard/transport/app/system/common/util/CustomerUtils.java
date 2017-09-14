package com.gistandard.transport.app.system.common.util;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.bean.ComCounty;
import com.gistandard.transport.base.entity.bean.ComProvince;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.system.common.bean.AddressReq;
import com.gistandard.transport.tools.util.HttpClientUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by m on 2016/7/14.
 */
@Component
public class CustomerUtils {
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private ComProvinceService comProvinceService;

    private static Properties config = new Properties();

    @Value("#{generalAcctUrl}")
    private static String url;

    public void setArea(AddressReq address) throws CustomerBizException {
        String areaId = address.getAreaId();
        Map<String, ComCity> comCityMap = comCityService.queryForMap();
        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
        if (comCityMap.containsKey(areaId)) {
            address.setCity(areaId);
            String provinceId = StringUtil.getObjStr(comCityMap.get(areaId).getProvinceId());
            address.setProvince(provinceId);
            address.setCountry(StringUtil.getObjStr(comProvinceMap.get(provinceId).getCountryId()));
        } else if (comCountyMap.containsKey(areaId)) {
            address.setCounty(areaId);
            String cityId = StringUtil.getObjStr(comCountyMap.get(areaId).getCityId());
            address.setCity(cityId);
            String provinceId = StringUtil.getObjStr(comCityMap.get(cityId).getProvinceId());
            address.setProvince(provinceId);
            address.setCountry(StringUtil.getObjStr(comProvinceMap.get(provinceId).getCountryId()));
        } else {
            throw new CustomerBizException("地址请选择城市或区");
        }
    }

    public static <T extends AppBaseRequest> void setAppLoginInfo2Req(T req, SecurityUser<AppLoginInfo> currentUser){
        if (currentUser != null) {
            req.setAppLoginInfo(currentUser.getInfo());
            req.setAccountId(currentUser.getInfo().getAccountId());
            req.setAcctUsername(currentUser.getInfo().getAcctUsername());
        }
    }

    /**
     * 获取giifi账户对应的通用账户
     */
    public String getGeneralAcctFromGFCode(String gFUserFromCode) {
        String reqUrl = url + "/Wallet/getBindGeneralAccountInformation.do";
        Map map = new HashMap();
        map.put("gifiAccount", gFUserFromCode);
        String resStr = HttpClientUtil.jsonPost(reqUrl, new HashMap<String, String>(), map);
        if (resStr != null) {
            BaseResBean res = JSON.parseObject(resStr, BaseResBean.class);
            if (res.getRetCode() == 1) {
                return (String) res.getData();
            }
        }
        return null;
    }
}
