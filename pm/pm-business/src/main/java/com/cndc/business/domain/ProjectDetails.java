package com.cndc.business.domain;

import java.util.List;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 项目负责人策划对象 tb_project_details
 *
 * @author laizhiying
 * @date 2024-12-18
 */
public class ProjectDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 项目详情ID
     */
    private Long id;

    /**
     * 项目ID
     */
    private Long prjId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /**
     * 项目执行状态
     */
    @Excel(name = "项目执行状态")
    private String prjexecStatus;

    /**
     * 设计成果归档
     */
    @Excel(name = "设计成果归档")
    private String designRes;

    /**
     * 立项批复归档
     */
    @Excel(name = "立项批复归档")
    private String estabAppr;

    /**
     * 可研批复归档
     */
    @Excel(name = "可研批复归档")
    private String feasAppr;

    /**
     * 初设批复归档
     */
    @Excel(name = "初设批复归档")
    private String prelimAppr;

    /**
     * 审图通过归档
     */
    @Excel(name = "审图通过归档")
    private String reviewPass;

    /**
     * 主体验收归档
     */
    @Excel(name = "主体验收归档")
    private String subjAccept;

    /**
     * 消防验收归档
     */
    @Excel(name = "消防验收归档")
    private String fireAccept;

    /**
     * 竣工验收归档
     */
    @Excel(name = "竣工验收归档")
    private String finAccept;

    /**
     * 自定义字段1
     */
    @Excel(name = "自定义字段1")
    private String def1;

    /**
     * 自定义字段2
     */
    @Excel(name = "自定义字段2")
    private String def2;

    /**
     * 自定义字段3
     */
    @Excel(name = "自定义字段3")
    private String def3;

    /**
     * 自定义字段4
     */
    @Excel(name = "自定义字段4")
    private String def4;

    /**
     * 自定义字段5
     */
    @Excel(name = "自定义字段5")
    private String def5;

    /**
     * 自定义字段6
     */
    @Excel(name = "自定义字段6")
    private String def6;

    /**
     * 自定义字段7
     */
    @Excel(name = "自定义字段7")
    private String def7;

    /**
     * 自定义字段8
     */
    @Excel(name = "自定义字段8")
    private String def8;

    /**
     * 自定义字段9
     */
    @Excel(name = "自定义字段9")
    private String def9;

    /**
     * 自定义字段10
     */
    @Excel(name = "自定义字段10")
    private String def10;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 项目子项信息
     */
    private List<ProjectSubitems> projectSubitemsList;

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

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setPrjexecStatus(String prjexecStatus) {
        this.prjexecStatus = prjexecStatus;
    }

    public String getPrjexecStatus() {
        return prjexecStatus;
    }

    public void setDesignRes(String designRes) {
        this.designRes = designRes;
    }

    public String getDesignRes() {
        return designRes;
    }

    public void setEstabAppr(String estabAppr) {
        this.estabAppr = estabAppr;
    }

    public String getEstabAppr() {
        return estabAppr;
    }

    public void setFeasAppr(String feasAppr) {
        this.feasAppr = feasAppr;
    }

    public String getFeasAppr() {
        return feasAppr;
    }

    public void setPrelimAppr(String prelimAppr) {
        this.prelimAppr = prelimAppr;
    }

    public String getPrelimAppr() {
        return prelimAppr;
    }

    public void setReviewPass(String reviewPass) {
        this.reviewPass = reviewPass;
    }

    public String getReviewPass() {
        return reviewPass;
    }

    public void setSubjAccept(String subjAccept) {
        this.subjAccept = subjAccept;
    }

    public String getSubjAccept() {
        return subjAccept;
    }

    public void setFireAccept(String fireAccept) {
        this.fireAccept = fireAccept;
    }

    public String getFireAccept() {
        return fireAccept;
    }

    public void setFinAccept(String finAccept) {
        this.finAccept = finAccept;
    }

    public String getFinAccept() {
        return finAccept;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef1() {
        return def1;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef2() {
        return def2;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef3() {
        return def3;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }

    public String getDef4() {
        return def4;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public String getDef5() {
        return def5;
    }

    public void setDef6(String def6) {
        this.def6 = def6;
    }

    public String getDef6() {
        return def6;
    }

    public void setDef7(String def7) {
        this.def7 = def7;
    }

    public String getDef7() {
        return def7;
    }

    public void setDef8(String def8) {
        this.def8 = def8;
    }

    public String getDef8() {
        return def8;
    }

    public void setDef9(String def9) {
        this.def9 = def9;
    }

    public String getDef9() {
        return def9;
    }

    public void setDef10(String def10) {
        this.def10 = def10;
    }

    public String getDef10() {
        return def10;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public List<ProjectSubitems> getProjectSubitemsList() {
        return projectSubitemsList;
    }

    public void setProjectSubitemsList(List<ProjectSubitems> projectSubitemsList) {
        this.projectSubitemsList = projectSubitemsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("prjId", getPrjId())
                .append("projectName", getProjectName())
                .append("updateDate", getUpdateDate())
                .append("prjexecStatus", getPrjexecStatus())
                .append("designRes", getDesignRes())
                .append("estabAppr", getEstabAppr())
                .append("feasAppr", getFeasAppr())
                .append("prelimAppr", getPrelimAppr())
                .append("reviewPass", getReviewPass())
                .append("subjAccept", getSubjAccept())
                .append("fireAccept", getFireAccept())
                .append("finAccept", getFinAccept())
                .append("def1", getDef1())
                .append("def2", getDef2())
                .append("def3", getDef3())
                .append("def4", getDef4())
                .append("def5", getDef5())
                .append("def6", getDef6())
                .append("def7", getDef7())
                .append("def8", getDef8())
                .append("def9", getDef9())
                .append("def10", getDef10())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("projectSubitemsList", getProjectSubitemsList())
                .toString();
    }
}
