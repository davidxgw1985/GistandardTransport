package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class FeedbackInfo {
    private Integer id;

    private String feedbackContent;

    private Integer feedbackSystem;

    private Date feedbackDate;

    private Integer feedbackUser;

    private Integer feedbackStatus;

    private Integer processUser;

    private Date processDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Integer getFeedbackSystem() {
        return feedbackSystem;
    }

    public void setFeedbackSystem(Integer feedbackSystem) {
        this.feedbackSystem = feedbackSystem;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Integer getFeedbackUser() {
        return feedbackUser;
    }

    public void setFeedbackUser(Integer feedbackUser) {
        this.feedbackUser = feedbackUser;
    }

    public Integer getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(Integer feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Integer getProcessUser() {
        return processUser;
    }

    public void setProcessUser(Integer processUser) {
        this.processUser = processUser;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }
}