package com.gistandard.transport.app.module.recommendRegister.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/8/18.
 */
public class RecommendInfo implements Serializable {

    private String recommendUid;

    private Integer recommendFrom;

    public String getRecommendUid() {
        return recommendUid;
    }

    public void setRecommendUid(String recommendUid) {
        this.recommendUid = recommendUid;
    }

    public Integer getRecommendFrom() {
        return recommendFrom;
    }

    public void setRecommendFrom(Integer recommendFrom) {
        this.recommendFrom = recommendFrom;
    }
}
