package com.gistandard.transport.gps.service.impl;

import com.gistandard.transport.base.entity.dao.MobileGpsInsertInfoDao;
import com.gistandard.transport.base.entity.dao.ex.MobileGpsInsertInfoDaoEx;
import com.gistandard.transport.gps.service.GpsService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: xgw
 * @ClassName: GpsServiceImpl
 * @Date: 2017/3/10
 * @Description:
 */
@Service
public class GpsServiceImpl implements GpsService {

    @Value("#{spring.ak}")
    private String ak;
    //    private String ak = "Exg7C3OEnzl8SnXzECeWyTUhN5YbePmM";
    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(GpsServiceImpl.class);

    @Autowired
    MobileGpsInsertInfoDao mobileGpsInsertInfoDao;
    @Autowired
    MobileGpsInsertInfoDaoEx mobileGpsInsertInfoDaoEx;

    /**
     * 百度地图
     * 根据地址查询经纬度
     *
     * @param addr
     * @return
     */
    @Override
    public GiPoint getGiPointByAddress(String addr) {
        //先根据地址到表中查询是否有位置相同记录，如果有直接采用
        GiPoint giPoint = new GiPoint();
        String lat = "";
        String lng = "";
        try {
            //进行转码
            String address = java.net.URLEncoder.encode(addr, ENCODING);
            String url = String.format("http://api.map.baidu.com/geocoder/v2/?ak="
                    + ak + "&output=json&address=%s", address);
            URL myURL = new URL(url);
            URLConnection httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), ENCODING);
                BufferedReader br = new BufferedReader(insr);
                String data = br.readLine();
                logger.info("getGiPointByAddress data{}", data);
                if (data != null && data.indexOf("\"lat\":") > 0) {
                    lat = data.substring(data.indexOf("\"lat\":")
                            + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("\"lng\":")
                            + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                }
                insr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        giPoint.setLatitude(new Double(lat));
        giPoint.setLongitude(new Double(lng));
        return giPoint;
    }

    /**
     * 百度地图
     * 根据关键字检索获取经纬度
     *
     * @param addr
     * @return
     */
    @Override
    public GiPoint getGiPointByCityAddress(String city, String addr) {
        //先根据地址到表中查询是否有位置相同记录，如果有直接采用
        GiPoint giPoint = new GiPoint();
        try {
            //进行转码api.map.baidu.com/place/v2/search?query=百度大厦&region=深圳&city_limit=true&output=json&ak={您的密钥}

            String address = java.net.URLEncoder.encode(addr, ENCODING);
            String cityCode = java.net.URLEncoder.encode(city, ENCODING);
            String url = String.format("http://api.map.baidu.com/place/v2/search?query=%s&page_size=1&page_num=0&scope=1&city_limit=true&region=%s&output=json&ak="
                    + ak, address, cityCode);
            logger.info("getGiPointByCityAddress url{}", url);
            URL myURL = new URL(url);
            URLConnection httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), ENCODING);
                BufferedReader br = new BufferedReader(insr);
                StringBuffer sb = new StringBuffer();
                String read;
                while ((read = br.readLine()) != null) {
                    sb.append(read.trim());
                }
                String data = sb.toString();
                logger.info("getGiPointByCityAddress data{}", data);
                if (data != null && data.indexOf("\"lat\":") > 0) {
                    String lat = data.substring(data.indexOf("\"lat\":")
                            + ("\"lat\":").length(), data.indexOf(",\"lng\""));
                    String lng = data.substring(data.indexOf("\"lng\":")
                            + ("\"lng\":").length(), data.indexOf("},\"address\""));
                    giPoint.setLatitude(new Double(lat));
                    giPoint.setLongitude(new Double(lng));
                }
                insr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return giPoint;
    }

    /**
     * 百度地图
     * 先根据地址获取中心点，再根据中心点附近找地址
     *
     * @param addr
     * @return
     */
    @Override
    public GiPoint getGiPointByNearBy(String addr, String detailAddr) {
        //先根据地址到表中查询是否有位置相同记录，如果有直接采用
        GiPoint giPoint = new GiPoint();
        //获取坐标，可能是区的中心点
        GiPoint centerPoint = getGiPointByAddress(addr + detailAddr);
        try {
            //进行转码api.map.baidu.com/place/v2/search?query=百度大厦&region=深圳&city_limit=true&output=json&ak={您的密钥}

            String address = java.net.URLEncoder.encode(addr, ENCODING);
            String url = String.format("http://api.map.baidu.com/place/v2/search?query=%s&location=%s,%s&radius=10000&page_size=1&page_num=0&scope=1&output=json&ak="
                    + ak, address, centerPoint.getLatitude(), centerPoint.getLongitude());
            logger.info("getGiPointByNearBy url{}", url);
            URL myURL = new URL(url);
            URLConnection httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), ENCODING);
                BufferedReader br = new BufferedReader(insr);
                StringBuffer sb = new StringBuffer();
                String read;
                while ((read = br.readLine()) != null) {
                    sb.append(read.trim());
                }
                String data = sb.toString();
                logger.info("getGiPointByNearBy data{}", data);
                if (data != null && data.indexOf("\"lat\":") > 0) {
                    String lat = data.substring(data.indexOf("\"lat\":")
                            + ("\"lat\":").length(), data.indexOf(",\"lng\""));
                    String lng = data.substring(data.indexOf("\"lng\":")
                            + ("\"lng\":").length(), data.indexOf("},\"address\""));
                    giPoint.setLatitude(new Double(lat));
                    giPoint.setLongitude(new Double(lng));
                } else {
                    giPoint.setLatitude(centerPoint.getLatitude());
                    giPoint.setLongitude(centerPoint.getLongitude());
                }
                insr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return giPoint;
    }

    public static void main(String[] args) throws IOException {
        GpsServiceImpl gpsService = new GpsServiceImpl();
//        GiPoint giPoint = gpsService.getGiPointByAddress("江苏省南京市雨花台区大数据产业基地4栋102室");
//        GiPoint giPoint = gpsService.getGiPointByCityAddress("南京市", "江苏省南京市雨花台区大数据产业基地4栋102室");
        GiPoint giPoint = gpsService.getGiPointByNearBy("江苏省南京市建邺区江东中路80号", "冠珠陶瓷");
        String aa = "江苏省南京市建邺区江东中路80号冠珠陶瓷";
        String vv = "冠珠陶瓷";
        System.out.println(aa.substring(0, aa.lastIndexOf(vv)));
        System.out.println(giPoint.getLatitude() + "-----" + giPoint.getLongitude());
    }
}
