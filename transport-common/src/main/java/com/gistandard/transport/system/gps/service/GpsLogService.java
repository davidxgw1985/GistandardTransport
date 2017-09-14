package com.gistandard.transport.system.gps.service;

import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;

/**
 * GPS 操作日志service
 */
public interface GpsLogService {

    void sendGpsLogMessage(final GiOrderTraceResynced giOrderTraceResynced);

    /**
     * 延时发送消息
     * @param giOrderTraceResynced
     * @param delay
     */
    void sendGpsLogMessage(final GiOrderTraceResynced giOrderTraceResynced,final long delay);

    /**
     *
     * @param param
     */
    void androidUploadGiLocationBd2(final String param);
}
