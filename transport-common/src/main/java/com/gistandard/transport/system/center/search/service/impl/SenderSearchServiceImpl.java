package com.gistandard.transport.system.center.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.bean.ComCounty;
import com.gistandard.transport.base.entity.bean.ComProvince;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.system.center.search.bean.Direction;
import com.gistandard.transport.system.center.search.bean.QueryDistanceByLatAndLonResult;
import com.gistandard.transport.system.center.search.service.SenderSearchService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/10/7.
 */
@Service
public class SenderSearchServiceImpl implements SenderSearchService {
    private static final Logger logger = LoggerFactory.getLogger(SenderSearchServiceImpl.class);

    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;

    @Value("#{spring.ak}")
    private String ak;
    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    @Override
    public QueryDistanceByLatAndLonResult queryDistanceByLatAndLon(String fromCode, String arriveCode, BigDecimal fromLat, BigDecimal fromLon, BigDecimal arriveLat, BigDecimal arriveLon) {
        logger.info("fromCode={},arriveCode={},fromLat={},fromLon={},arriveLat={},arriveLon={}", fromCode, arriveCode, fromLat, fromLon, arriveLat, arriveLon);
        QueryDistanceByLatAndLonResult res = new QueryDistanceByLatAndLonResult();
        String result = null;
        try {
            if (fromLat != null && fromLon != null && arriveLat != null && arriveLon != null) {
                result = post("http://api.map.baidu.com/direction/v1?mode=driving&origin=" + fromLat + "," + fromLon + "&destination="
                        + arriveLat + "," + arriveLon + "&origin_region="
                        + URLEncoder.encode(queryCityName(fromCode), ENCODING)
                        + "&destination_region="
                        + URLEncoder.encode(queryCityName(arriveCode), ENCODING)
                        + "&output=json&ak=" + ak, null);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        logger.info("result={}", result);
        if (null != result) {
            Direction jsonObject = JSON.parseObject(result, Direction.class);
//            logger.info("result={}", JSONObject.toJSONString(result));
            if (null != jsonObject && null != jsonObject.getResult() && null != jsonObject.getResult().getRoutes() && null != jsonObject.getResult().getRoutes()[0].getDistance()) {
                res.setRetCode(SystemDefine.SUCCESS);
                res.setData(jsonObject.getResult().getRoutes()[0].getDistance().divide(new BigDecimal(1000)));
                logger.info("result distance={}", jsonObject.getResult().getRoutes()[0].getDistance().divide(new BigDecimal(1000)));
            } else {
                res.setRetCode(SystemDefine.FAILURE);
            }
        } else {
            res.setRetCode(SystemDefine.FAILURE);
        }
        return res;
    }

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    private String queryCityName(String cityCode) {
        String cityName = "";
        ComCity tCtiy = comCityService.queryForMap().get(cityCode);
        if (null != tCtiy) {
            cityName = tCtiy.getName();
        } else {
            ComCounty tCounty = comCountyService.queryForMap().get(cityCode);
            if (null != tCounty) {
                tCtiy = comCityService.queryForMap().get(tCounty.getCityId());
                cityName = tCtiy.getName();
            } else {
                ComProvince comProvince = comProvinceService.queryForMap().get(cityCode);
                if (comProvince != null) {
                    cityName = comProvince.getProvinceName();

                }
            }
        }
        return cityName;
    }
}
