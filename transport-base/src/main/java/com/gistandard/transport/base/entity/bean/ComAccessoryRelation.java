package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class ComAccessoryRelation implements Serializable{
    private static final long serialVersionUID = -6987426196780654368L;
    private Integer id;

    private Integer relaId;

    private String tTab;

    private Integer attachId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelaId() {
        return relaId;
    }

    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    public String gettTab() {
        return tTab;
    }

    public void settTab(String tTab) {
        this.tTab = tTab;
    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }
}