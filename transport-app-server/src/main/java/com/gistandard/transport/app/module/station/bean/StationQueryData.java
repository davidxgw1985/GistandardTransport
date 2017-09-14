package com.gistandard.transport.app.module.station.bean;

import com.gistandard.transport.system.common.courier.bean.Courier;
import com.gistandard.transport.system.common.station.bean.Station;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
public class StationQueryData implements Serializable {
    private List<Station> Station;
    private List<Courier> Courier;

    public List<com.gistandard.transport.system.common.station.bean.Station> getStation() {
        return Station;
    }

    public void setStation(List<com.gistandard.transport.system.common.station.bean.Station> station) {
        Station = station;
    }

    public List<com.gistandard.transport.system.common.courier.bean.Courier> getCourier() {
        return Courier;
    }

    public void setCourier(List<com.gistandard.transport.system.common.courier.bean.Courier> courier) {
        Courier = courier;
    }
}
