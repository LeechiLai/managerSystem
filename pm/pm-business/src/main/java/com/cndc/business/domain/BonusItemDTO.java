package com.cndc.business.domain;

import java.math.BigDecimal;

public class BonusItemDTO {
    public BonusItemDTO(String projectName, BigDecimal bonusAmount) {
        this.projectName = projectName;
        this.bonusAmount = bonusAmount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    private String projectName;
    private BigDecimal bonusAmount;
}
