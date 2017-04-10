package com.ntq.baseMgr.po;

import lombok.Getter;

public class JobSeekerResumeDeliveryWithBLOBs extends JobSeekerResumeDelivery {
    @Getter
    private String feedbackContent;
    @Getter
    private String notPassReason;


    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }


    public void setNotPassReason(String notPassReason) {
        this.notPassReason = notPassReason == null ? null : notPassReason.trim();
    }
}