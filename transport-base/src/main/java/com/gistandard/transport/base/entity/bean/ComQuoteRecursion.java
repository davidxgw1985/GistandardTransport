package com.gistandard.transport.base.entity.bean;

public class ComQuoteRecursion {
    private Integer id;

    private Integer rootQuoteId;

    private String sonQuoteIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRootQuoteId() {
        return rootQuoteId;
    }

    public void setRootQuoteId(Integer rootQuoteId) {
        this.rootQuoteId = rootQuoteId;
    }

    public String getSonQuoteIds() {
        return sonQuoteIds;
    }

    public void setSonQuoteIds(String sonQuoteIds) {
        this.sonQuoteIds = sonQuoteIds;
    }
}