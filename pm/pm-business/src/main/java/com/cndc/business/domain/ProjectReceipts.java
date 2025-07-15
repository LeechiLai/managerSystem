package com.cndc.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 项目收款记录对象 tb_project_receipts
 *
 * @author laizhiying
 * @date 2025-04-08
 */
public class ProjectReceipts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 项目ID
     */
    @Excel(name = "项目ID")
    private Long prjId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 收款序号
     */
    @Excel(name = "收款序号")
    private Long receiptSequence;

    /**
     * 金额
     */
    @Excel(name = "金额")
    private BigDecimal receiptAmount;

    /** 分包金额 */
    @Excel(name = "分包金额")
    private BigDecimal subcAmtIncTax;

    /**
     * 记录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户ID
     */
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    public Long getPrjId() {
        return prjId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setReceiptSequence(Long receiptSequence) {
        this.receiptSequence = receiptSequence;
    }

    public Long getReceiptSequence() {
        return receiptSequence;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setSubcAmtIncTax(BigDecimal subcAmtIncTax)
    {
        this.subcAmtIncTax = subcAmtIncTax;
    }

    public BigDecimal getSubcAmtIncTax()
    {
        return subcAmtIncTax;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Date getTs() {
        return ts;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("prjId", getPrjId())
                .append("projectName", getProjectName())
                .append("receiptSequence", getReceiptSequence())
                .append("receiptAmount", getReceiptAmount())
                .append("subcAmtIncTax", getSubcAmtIncTax())
                .append("ts", getTs())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("deptId", getDeptId())
                .append("userId", getUserId())
                .toString();
    }
}
