package com.cndc.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 项目子项对象 tb_project_subitems
 * 
 * @author laizhiying
 * @date 2024-12-18
 */
public class ProjectSubitems extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目详情子表ID */
    private Long id;

    /** 项目ID */
    private Long prjId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目详情ID */
    private Long detailId;

    /** 子项编号 */
    @Excel(name = "子项编号")
    private String itemNo;

    /** 子项名称 */
    @Excel(name = "子项名称")
    private String itemName;

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
    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setItemNo(String itemNo) 
    {
        this.itemNo = itemNo;
    }

    public String getItemNo() 
    {
        return itemNo;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
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
            .append("prjId", getPrjId())
            .append("projectName", getProjectName())
            .append("detailId", getDetailId())
            .append("itemNo", getItemNo())
            .append("itemName", getItemName())
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
