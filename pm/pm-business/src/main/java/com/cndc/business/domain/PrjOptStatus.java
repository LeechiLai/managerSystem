package com.cndc.business.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 项目运行情况对象 tb_prj_opt_status
 *
 * @author laizhiying
 * @date 2025-04-07
 */
public class PrjOptStatus extends BaseEntity {
    private static final long serialVersionUID = 1L;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    // 新增字段
    private Long projectId; // 映射 tb_prj_opt_status.prj_id

    /**
     * 项目id
     */
    private Long id;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 阶段号
     */
    @Excel(name = "阶段号")
    private String phaseNo;

    /**
     * 工程号
     */
    @Excel(name = "工程号")
    private String projectCode;

    /**
     * 项目执行状态
     */
    @Excel(name = "项目执行状态")
    private String prjexecStatus;

    /**
     * 收款比例
     */
    @Excel(name = "收款比例")
    private BigDecimal recvRatio;

    /**
     * 综合进度
     */
    @Excel(name = "综合进度")
    private BigDecimal progress;

    /**
     * 项目负责人
     */
    @Excel(name = "项目负责人")
    private String projectManager;

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

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setPhaseNo(String phaseNo) {
        this.phaseNo = phaseNo;
    }

    public String getPhaseNo() {
        return phaseNo;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setPrjexecStatus(String prjexecStatus) {
        this.prjexecStatus = prjexecStatus;
    }

    public String getPrjexecStatus() {
        return prjexecStatus;
    }

    public void setRecvRatio(BigDecimal recvRatio) {
        this.recvRatio = recvRatio;
    }

    public BigDecimal getRecvRatio() {
        return recvRatio;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectManager() {
        return projectManager;
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
                .append("projectId", getProjectId())
                .append("projectName", getProjectName())
                .append("phaseNo", getPhaseNo())
                .append("projectCode", getProjectCode())
                .append("prjexecStatus", getPrjexecStatus())
                .append("recvRatio", getRecvRatio())
                .append("progress", getProgress())
                .append("projectManager", getProjectManager())
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
