package com.ntq.baseMgr.po;

import lombok.Getter;

public class CompanyPositionInfosWithBLOBs extends CompanyPositionInfos {
    /*职位描述*/
    @Getter
    private String positionDesc;
    /*职位要求*/
    @Getter
    private String positionRequirements;


    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc == null ? null : positionDesc.trim();
    }


    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements == null ? null : positionRequirements.trim();
    }
}