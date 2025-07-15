package com.cndc.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 项目信息对象 project
 *
 * @author laizhiying
 * @date 2024-12-03
 */
public class Project extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long id;

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

    /** 计入年月 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "计入年月", width = 30, dateFormat = "yyyy-MM")
    private Date yearmonth;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 行业大类 */
    @Excel(name = "行业大类")
    private String industryCategory;

    /** 行业细分 */
    @Excel(name = "行业细分")
    private String industrySubcategory;

    /** 地域 */
    @Excel(name = "地域")
    private String region;

    /** 项目规模 */
    @Excel(name = "项目规模")
    private String projectScale;

    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractSxsj;

    /** 合同状态 */
    @Excel(name = "合同状态")
    private String contractStatus;

    /** 执行状态 */
    @Excel(name = "执行状态")
    private String prjexecStatus;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private String department;

    /** 结转原部门 */
    @Excel(name = "结转原部门")
    private String originalDepartment;

    /** 工程号 */
    @Excel(name = "工程号")
    private String projectCode;

    /** 项目阶段 */
    @Excel(name = "项目阶段")
    private String projectPhase;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractNumber;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 建设单位 */
    @Excel(name = "建设单位")
    private String constructionUnit;

    /** 项目内容 */
    @Excel(name = "项目内容")
    private String projectScope;

    /** 总投资) */
    @Excel(name = "总投资)")
    private BigDecimal totalInvestment;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private BigDecimal amtIncTax;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private BigDecimal amtExcTax;

    /** 阶段合同额 */
    @Excel(name = "阶段合同额")
    private BigDecimal stageAmtIncTa;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String projectManager;

    /** 合同洽谈负责人 */
    @Excel(name = "合同洽谈负责人")
    private String negResp;

    /** 行业总监 */
    @Excel(name = "行业总监")
    private String industryDirector;

    /** 执行负责人 */
    @Excel(name = "执行负责人")
    private String execResp;

    /** 持证负责人 */
    @Excel(name = "持证负责人")
    private String certResp;

    /** EIM负责人 */
    @Excel(name = "EIM负责人")
    private String eimResp;

    /** 分包金额 */
    @Excel(name = "分包金额")
    private BigDecimal subcAmtIncTax;

    /** 项目已收款 */
    @Excel(name = "项目已收款")
    private BigDecimal recvdAmt;

    /** 项目未收款 */
    @Excel(name = "项目未收款")
    private BigDecimal unrecdAmt;

    /** 已收款比例  */
    @Excel(name = "已收款比例 ")
    private BigDecimal recvRatio;

    /** 以前年度收款 */
    @Excel(name = "以前年度收款")
    private BigDecimal prevYrRecv;

    /** 本年度收款 */
    @Excel(name = "本年度收款")
    private BigDecimal currYrRecv;

    /** 收费备注 */
    @Excel(name = "收费备注")
    private String recvreMarks;

    /** 自定义字段1 */
    private String def1;

    /** 自定义字段2 */
    private String def2;

    /** 自定义字段3 */
    private String def3;

    /** 自定义字段4 */
    private String def4;

    /** 自定义字段5 */
    private String def5;

    /** 自定义字段6 */
    private String def6;

    /** 自定义字段7 */
    private String def7;

    /** 自定义字段8 */
    private String def8;

    /** 自定义字段9 */
    private String def9;

    /** 自定义字段10 */
    private String def10;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setYearmonth(Date yearmonth)
    {
        this.yearmonth = yearmonth;
    }

    public Date getYearmonth()
    {
        return yearmonth;
    }
    public void setBusinessType(String businessType)
    {
        this.businessType = businessType;
    }

    public String getBusinessType()
    {
        return businessType;
    }
    public void setIndustryCategory(String industryCategory)
    {
        this.industryCategory = industryCategory;
    }

    public String getIndustryCategory()
    {
        return industryCategory;
    }
    public void setIndustrySubcategory(String industrySubcategory)
    {
        this.industrySubcategory = industrySubcategory;
    }

    public String getIndustrySubcategory()
    {
        return industrySubcategory;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegion()
    {
        return region;
    }
    public void setProjectScale(String projectScale)
    {
        this.projectScale = projectScale;
    }

    public String getProjectScale()
    {
        return projectScale;
    }
    public void setContractSxsj(Date contractSxsj)
    {
        this.contractSxsj = contractSxsj;
    }

    public Date getContractSxsj()
    {
        return contractSxsj;
    }
    public void setContractStatus(String contractStatus)
    {
        this.contractStatus = contractStatus;
    }

    public String getContractStatus()
    {
        return contractStatus;
    }
    public void setPrjexecStatus(String prjexecStatus)
    {
        this.prjexecStatus = prjexecStatus;
    }

    public String getPrjexecStatus()
    {
        return prjexecStatus;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getDepartment()
    {
        return department;
    }
    public void setOriginalDepartment(String originalDepartment)
    {
        this.originalDepartment = originalDepartment;
    }

    public String getOriginalDepartment()
    {
        return originalDepartment;
    }
    public void setProjectCode(String projectCode)
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode()
    {
        return projectCode;
    }
    public void setProjectPhase(String projectPhase)
    {
        this.projectPhase = projectPhase;
    }

    public String getProjectPhase()
    {
        return projectPhase;
    }
    public void setContractNumber(String contractNumber)
    {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber()
    {
        return contractNumber;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setConstructionUnit(String constructionUnit)
    {
        this.constructionUnit = constructionUnit;
    }

    public String getConstructionUnit()
    {
        return constructionUnit;
    }
    public void setProjectScope(String projectScope)
    {
        this.projectScope = projectScope;
    }

    public String getProjectScope()
    {
        return projectScope;
    }
    public void setTotalInvestment(BigDecimal totalInvestment)
    {
        this.totalInvestment = totalInvestment;
    }

    public BigDecimal getTotalInvestment()
    {
        return totalInvestment;
    }
    public void setAmtIncTax(BigDecimal amtIncTax)
    {
        this.amtIncTax = amtIncTax;
    }

    public BigDecimal getAmtIncTax()
    {
        return amtIncTax;
    }
    public void setAmtExcTax(BigDecimal amtExcTax)
    {
        this.amtExcTax = amtExcTax;
    }

    public BigDecimal getAmtExcTax()
    {
        return amtExcTax;
    }
    public void setStageAmtIncTa(BigDecimal stageAmtIncTa)
    {
        this.stageAmtIncTa = stageAmtIncTa;
    }

    public BigDecimal getStageAmtIncTa()
    {
        return stageAmtIncTa;
    }
    public void setProjectManager(String projectManager)
    {
        this.projectManager = projectManager;
    }

    public String getProjectManager()
    {
        return projectManager;
    }
    public void setNegResp(String negResp)
    {
        this.negResp = negResp;
    }

    public String getNegResp()
    {
        return negResp;
    }
    public void setIndustryDirector(String industryDirector)
    {
        this.industryDirector = industryDirector;
    }

    public String getIndustryDirector()
    {
        return industryDirector;
    }
    public void setExecResp(String execResp)
    {
        this.execResp = execResp;
    }

    public String getExecResp()
    {
        return execResp;
    }
    public void setCertResp(String certResp)
    {
        this.certResp = certResp;
    }

    public String getCertResp()
    {
        return certResp;
    }
    public void setEimResp(String eimResp)
    {
        this.eimResp = eimResp;
    }

    public String getEimResp()
    {
        return eimResp;
    }
    public void setSubcAmtIncTax(BigDecimal subcAmtIncTax)
    {
        this.subcAmtIncTax = subcAmtIncTax;
    }

    public BigDecimal getSubcAmtIncTax()
    {
        return subcAmtIncTax;
    }
    public void setRecvdAmt(BigDecimal recvdAmt)
    {
        this.recvdAmt = recvdAmt;
    }

    public BigDecimal getRecvdAmt()
    {
        return recvdAmt;
    }
    public void setUnrecdAmt(BigDecimal unrecdAmt)
    {
        this.unrecdAmt = unrecdAmt;
    }

    public BigDecimal getUnrecdAmt()
    {
        return unrecdAmt;
    }
    public void setRecvRatio(BigDecimal recvRatio)
    {
        this.recvRatio = recvRatio;
    }

    public BigDecimal getRecvRatio()
    {
        return recvRatio;
    }
    public void setPrevYrRecv(BigDecimal prevYrRecv)
    {
        this.prevYrRecv = prevYrRecv;
    }

    public BigDecimal getPrevYrRecv()
    {
        return prevYrRecv;
    }
    public void setCurrYrRecv(BigDecimal currYrRecv)
    {
        this.currYrRecv = currYrRecv;
    }

    public BigDecimal getCurrYrRecv()
    {
        return currYrRecv;
    }
    public void setRecvreMarks(String recvreMarks)
    {
        this.recvreMarks = recvreMarks;
    }

    public String getRecvreMarks()
    {
        return recvreMarks;
    }
    public void setDef1(String def1)
    {
        this.def1 = def1;
    }

    public String getDef1()
    {
        return def1;
    }
    public void setDef2(String def2)
    {
        this.def2 = def2;
    }

    public String getDef2()
    {
        return def2;
    }
    public void setDef3(String def3)
    {
        this.def3 = def3;
    }

    public String getDef3()
    {
        return def3;
    }
    public void setDef4(String def4)
    {
        this.def4 = def4;
    }

    public String getDef4()
    {
        return def4;
    }
    public void setDef5(String def5)
    {
        this.def5 = def5;
    }

    public String getDef5()
    {
        return def5;
    }
    public void setDef6(String def6)
    {
        this.def6 = def6;
    }

    public String getDef6()
    {
        return def6;
    }
    public void setDef7(String def7)
    {
        this.def7 = def7;
    }

    public String getDef7()
    {
        return def7;
    }
    public void setDef8(String def8)
    {
        this.def8 = def8;
    }

    public String getDef8()
    {
        return def8;
    }
    public void setDef9(String def9)
    {
        this.def9 = def9;
    }

    public String getDef9()
    {
        return def9;
    }
    public void setDef10(String def10)
    {
        this.def10 = def10;
    }

    public String getDef10()
    {
        return def10;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("yearmonth", getYearmonth())
            .append("businessType", getBusinessType())
            .append("industryCategory", getIndustryCategory())
            .append("industrySubcategory", getIndustrySubcategory())
            .append("region", getRegion())
            .append("projectScale", getProjectScale())
            .append("contractSxsj", getContractSxsj())
            .append("contractStatus", getContractStatus())
            .append("prjexecStatus", getPrjexecStatus())
            .append("department", getDepartment())
            .append("originalDepartment", getOriginalDepartment())
            .append("projectCode", getProjectCode())
            .append("projectPhase", getProjectPhase())
            .append("contractNumber", getContractNumber())
            .append("projectName", getProjectName())
            .append("constructionUnit", getConstructionUnit())
            .append("projectScope", getProjectScope())
            .append("totalInvestment", getTotalInvestment())
            .append("amtIncTax", getAmtIncTax())
            .append("amtExcTax", getAmtExcTax())
            .append("stageAmtIncTa", getStageAmtIncTa())
            .append("projectManager", getProjectManager())
            .append("negResp", getNegResp())
            .append("industryDirector", getIndustryDirector())
            .append("execResp", getExecResp())
            .append("certResp", getCertResp())
            .append("eimResp", getEimResp())
            .append("subcAmtIncTax", getSubcAmtIncTax())
            .append("recvdAmt", getRecvdAmt())
            .append("unrecdAmt", getUnrecdAmt())
            .append("recvRatio", getRecvRatio())
            .append("prevYrRecv", getPrevYrRecv())
            .append("currYrRecv", getCurrYrRecv())
            .append("recvreMarks", getRecvreMarks())
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
            .toString();
    }
}
