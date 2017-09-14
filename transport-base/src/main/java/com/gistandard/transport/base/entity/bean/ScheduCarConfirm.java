package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ScheduCarConfirm extends ScheduCarConfirmKey {
    private static final long serialVersionUID = 565671825884133899L;
    private Boolean isUse;

    private Date modifyTime;

    private Integer boxId;

    private Integer bracketId;

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getBracketId() {
        return bracketId;
    }

    public void setBracketId(Integer bracketId) {
        this.bracketId = bracketId;
    }
}