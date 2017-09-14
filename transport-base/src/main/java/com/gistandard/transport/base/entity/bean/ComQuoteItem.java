package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class ComQuoteItem implements Serializable{
    private static final long serialVersionUID = 781123818606703618L;
    private Integer id;

    private String itemName;

    private String itemCode;

    private Integer itemCategory;

    private Integer itemType;

    private Integer itemStatus;

    private Integer itemRule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getItemRule() {
        return itemRule;
    }

    public void setItemRule(Integer itemRule) {
        this.itemRule = itemRule;
    }
}