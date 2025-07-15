package com.cndc.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 设计组比例分配对象 tb_design_ratio
 *
 * @author laizhiying
 * @date 2024-12-19
 */
public class DesignRatio extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
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

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long prjId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 专业 */
    @Excel(name = "专业")
    private String profession;

    /** 专业负责人 */
    @Excel(name = "专业负责人")
    private String proLeader;

    /** 设计组内部比例 */
    @Excel(name = "设计组内部比例")
    private BigDecimal internalPct;

    /** 设计组占总项目比例 */
    @Excel(name = "设计组占总项目比例")
    private BigDecimal projPct;

    /** 自定义字段1 */
    @Excel(name = "自定义字段1")
    private String def1;

    /** 自定义字段2 */
    @Excel(name = "自定义字段2")
    private String def2;

    /** 自定义字段3 */
    @Excel(name = "自定义字段3")
    private String def3;

    /** 自定义字段4 */
    @Excel(name = "自定义字段4")
    private String def4;

    /** 自定义字段5 */
    @Excel(name = "自定义字段5")
    private String def5;

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
    public void setPrjId(Long prjId)
    {
        this.prjId = prjId;
    }

    public Long getPrjId()
    {
        return prjId;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public String getProfession()
    {
        return profession;
    }
    public void setProLeader(String proLeader)
    {
        this.proLeader = proLeader;
    }

    public String getProLeader()
    {
        return proLeader;
    }
    public void setInternalPct(BigDecimal internalPct)
    {
        this.internalPct = internalPct;
    }

    public BigDecimal getInternalPct()
    {
        return internalPct;
    }
    public void setProjPct(BigDecimal projPct)
    {
        this.projPct = projPct;
    }

    public BigDecimal getProjPct()
    {
        return projPct;
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
            .append("prjId", getPrjId())
            .append("projectName", getProjectName())
            .append("profession", getProfession())
            .append("proLeader", getProLeader())
            .append("internalPct", getInternalPct())
            .append("projPct", getProjPct())
            .append("def1", getDef1())
            .append("def2", getDef2())
            .append("def3", getDef3())
            .append("def4", getDef4())
            .append("def5", getDef5())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
