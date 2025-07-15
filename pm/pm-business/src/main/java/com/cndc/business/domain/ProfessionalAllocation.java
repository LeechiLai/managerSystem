package com.cndc.business.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 专业负责人策划对象 tb_professional_allocation
 * 
 * @author laizhiying
 * @date 2025-02-17
 */
public class ProfessionalAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long prjId;

    /** 专业 */
    @Excel(name = "专业")
    private String profession;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目号 */
    @Excel(name = "项目号")
    private String projectNo;

    /** 阶段号 */
    @Excel(name = "阶段号")
    private String phaseNo;

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

    /** 自定义字段6 */
    @Excel(name = "自定义字段6")
    private String def6;

    /** 自定义字段7 */
    @Excel(name = "自定义字段7")
    private String def7;

    /** 自定义字段8 */
    @Excel(name = "自定义字段8")
    private String def8;

    /** 自定义字段9 */
    @Excel(name = "自定义字段9")
    private String def9;

    /** 自定义字段10 */
    @Excel(name = "自定义字段10")
    private String def10;

    /** 删除标志（0代表存在 2代表删除） */
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,2=代表删除")
    private String delFlag;

    /** 部门ID */
    private Long deptId;

    /** 用户ID */
    private Long userId;

    /** 专业负责人策划子信息 */
    private List<ProfessionalAllocationB> professionalAllocationBList;

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
    public void setProfession(String profession) 
    {
        this.profession = profession;
    }

    public String getProfession() 
    {
        return profession;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectNo(String projectNo) 
    {
        this.projectNo = projectNo;
    }

    public String getProjectNo() 
    {
        return projectNo;
    }
    public void setPhaseNo(String phaseNo) 
    {
        this.phaseNo = phaseNo;
    }

    public String getPhaseNo() 
    {
        return phaseNo;
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
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public List<ProfessionalAllocationB> getProfessionalAllocationBList()
    {
        return professionalAllocationBList;
    }

    public void setProfessionalAllocationBList(List<ProfessionalAllocationB> professionalAllocationBList)
    {
        this.professionalAllocationBList = professionalAllocationBList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("prjId", getPrjId())
            .append("profession", getProfession())
            .append("projectName", getProjectName())
            .append("projectNo", getProjectNo())
            .append("phaseNo", getPhaseNo())
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
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("professionalAllocationBList", getProfessionalAllocationBList())
            .toString();
    }
}
