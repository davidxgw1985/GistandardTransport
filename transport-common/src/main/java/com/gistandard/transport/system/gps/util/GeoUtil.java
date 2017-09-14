package com.gistandard.transport.system.gps.util;


import com.gistandard.transport.base.bean.gps.GiPoint;

/**
 * 地理空间工具
 *
 * @author YouGaozhan
 */
public class GeoUtil {

    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param lng1 第1点经度
     * @param lat1 第1点纬度
     * @param lng2 第2点经度
     * @param lat2 第2点纬度
     * @return
     */
    public static double calcDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static double calcDistance(GiPoint point1, GiPoint point2) {
        if (point1 == null || point2 == null) return 0;
        if (point1.getLongitude() == point2.getLongitude() && point1.getLatitude() == point2.getLatitude()) return 0;

        return calcDistance(point1.getLongitude(), point1.getLatitude(), point2.getLongitude(), point2.getLatitude());
    }

    /**
     * 根据经纬度和距离返回一个矩形范围
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @param distance
     *            距离(单位为米)
     * @return [lng1,lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */
    public static double[] getRectangle(double lng, double lat, long distance) {
        float delta = 111000;
        if (lng != 0 && lat != 0) {
            double lng1 = lng - distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lng2 = lng + distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[] { lng1, lat1, lng2, lat2 };
        } else {
            // TODO ZHCH 等于0时的计算公式
            double lng1 = lng - distance / delta;
            double lng2 = lng + distance / delta;
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[] { lng1, lat1, lng2, lat2 };
        }
    }
}
