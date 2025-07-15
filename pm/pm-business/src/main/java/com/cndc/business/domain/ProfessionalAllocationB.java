package com.cndc.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 专业负责人策划子表对象 tb_professional_allocation_b
 *
 * @author laizhiying
 * @date 2025-02-24
 */
public class ProfessionalAllocationB extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    // 新增字段
    /** 专业负责人进度 */
    private BigDecimal proRespProgress;

    /** 专业负责人最终百分比 */
    private BigDecimal proRespFinalPct;

    /** 专业负责人最大进度金额 */
    private BigDecimal proRespMaxProgressAmount;

    /** 专业负责人已发行金额 */
    private BigDecimal proRespIssuedAmount;

    /** 专业EIM进度 */
    private BigDecimal eimProgress;

    /** 专业EIM最终百分比 */
    private BigDecimal eimFinalPct;

    /** 专业EIM最大进度金额 */
    private BigDecimal eimMaxProgressAmount;

    /** 专业EIM已发行金额 */
    private BigDecimal eimIssuedAmount;

    /** 总体方案进度 */
    private BigDecimal overallPlanProgress;

    /** 总体方案最终百分比 */
    private BigDecimal overallPlanFinalPct;

    /** 总体方案最大进度金额 */
    private BigDecimal overallPlanMaxProgressAmount;

    /** 总体方案已发行金额 */
    private BigDecimal overallPlanIssuedAmount;

    /** 校核进度 */
    private BigDecimal checkerProgress;

    /** 校核最终百分比 */
    private BigDecimal checkerFinalPct;

    /** 校核最大进度金额 */
    private BigDecimal checkerMaxProgressAmount;

    /** 校核已发行金额 */
    private BigDecimal checkerIssuedAmount;

    /** 审核进度 */
    private BigDecimal reviewerProgress;

    /** 审核最终百分比 */
    private BigDecimal reviewerFinalPct;

    /** 审核最大进度金额 */
    private BigDecimal reviewerMaxProgressAmount;

    /** 审核已发行金额 */
    private BigDecimal reviewerIssuedAmount;

    /** 压力管道审核进度 */
    private BigDecimal pipeReviewProgress;

    /** 压力管道审核最终百分比 */
    private BigDecimal pipeReviewFinalPct;

    /** 压力管道审核最大进度金额 */
    private BigDecimal pipeReviewMaxProgressAmount;

    /** 压力管道审核已发行金额 */
    private BigDecimal pipeReviewIssuedAmount;

    /** 审定进度 */
    private BigDecimal approvalProgress;

    /** 审定最终百分比 */
    private BigDecimal approvalFinalPct;

    /** 审定最大进度金额 */
    private BigDecimal approvalMaxProgressAmount;

    /** 审定已发行金额 */
    private BigDecimal approvalIssuedAmount;

    /** 设计指导进度 */
    private BigDecimal designGuidanceProgress;

    /** 设计指导最终百分比 */
    private BigDecimal designGuidanceFinalPct;

    /** 设计指导最大进度金额 */
    private BigDecimal designGuidanceMaxProgressAmount;

    /** 设计指导已发行金额 */
    private BigDecimal designGuidanceIssuedAmount;

    /** 顾问进度 */
    private BigDecimal consultantProgress;

    /** 顾问最终百分比 */
    private BigDecimal consultantFinalPct;

    /** 顾问最大进度金额 */
    private BigDecimal consultantMaxProgressAmount;

    /** 顾问已发行金额 */
    private BigDecimal consultantIssuedAmount;

    /** 采购支持进度 */
    private BigDecimal procurementSupportProgress;

    /** 采购支持最终百分比 */
    private BigDecimal procurementSupportFinalPct;

    /** 采购支持最大进度金额 */
    private BigDecimal procurementSupportMaxProgressAmount;

    /** 采购支持已发行金额 */
    private BigDecimal procurementSupportIssuedAmount;

    /** 设计1最大进度金额 */
    private BigDecimal design1MaxProgressAmount;

    /** 设计1已发行金额 */
    private BigDecimal design1IssuedAmount;

    /** 设计2最大进度金额 */
    private BigDecimal design2MaxProgressAmount;

    /** 设计2已发行金额 */
    private BigDecimal design2IssuedAmount;

    /** 设计3最大进度金额 */
    private BigDecimal design3MaxProgressAmount;

    /** 设计3已发行金额 */
    private BigDecimal design3IssuedAmount;

    /** 设计4最大进度金额 */
    private BigDecimal design4MaxProgressAmount;

    /** 设计4已发行金额 */
    private BigDecimal design4IssuedAmount;

    /** 设计5最大进度金额 */
    private BigDecimal design5MaxProgressAmount;

    /** 设计5已发行金额 */
    private BigDecimal design5IssuedAmount;

    /** 设计6最大进度金额 */
    private BigDecimal design6MaxProgressAmount;

    /** 设计6已发行金额 */
    private BigDecimal design6IssuedAmount;

    /** 设计7最大进度金额 */
    private BigDecimal design7MaxProgressAmount;

    /** 设计7已发行金额 */
    private BigDecimal design7IssuedAmount;

    /** 设计8最大进度金额 */
    private BigDecimal design8MaxProgressAmount;

    /** 设计8已发行金额 */
    private BigDecimal design8IssuedAmount;

    /** 设计9最大进度金额 */
    private BigDecimal design9MaxProgressAmount;

    /** 设计9已发行金额 */
    private BigDecimal design9IssuedAmount;

    /** 设计10最大进度金额 */
    private BigDecimal design10MaxProgressAmount;

    /** 设计10已发行金额 */
    private BigDecimal design10IssuedAmount;

    /** 设计11最大进度金额 */
    private BigDecimal design11MaxProgressAmount;

    /** 设计11已发行金额 */
    private BigDecimal design11IssuedAmount;

    /** 设计12最大进度金额 */
    private BigDecimal design12MaxProgressAmount;

    /** 设计12已发行金额 */
    private BigDecimal design12IssuedAmount;

    /** 设计13最大进度金额 */
    private BigDecimal design13MaxProgressAmount;

    /** 设计13已发行金额 */
    private BigDecimal design13IssuedAmount;

    /** 设计14最大进度金额 */
    private BigDecimal design14MaxProgressAmount;

    /** 设计14已发行金额 */
    private BigDecimal design14IssuedAmount;

    /** 设计15最大进度金额 */
    private BigDecimal design15MaxProgressAmount;

    /** 设计15已发行金额 */
    private BigDecimal design15IssuedAmount;

    /** 设计16最大进度金额 */
    private BigDecimal design16MaxProgressAmount;

    /** 设计16已发行金额 */
    private BigDecimal design16IssuedAmount;

    /** 设计17最大进度金额 */
    private BigDecimal design17MaxProgressAmount;

    /** 设计17已发行金额 */
    private BigDecimal design17IssuedAmount;

    /** 设计18最大进度金额 */
    private BigDecimal design18MaxProgressAmount;

    /** 设计18已发行金额 */
    private BigDecimal design18IssuedAmount;

    /** 设计19最大进度金额 */
    private BigDecimal design19MaxProgressAmount;

    /** 设计19已发行金额 */
    private BigDecimal design19IssuedAmount;

    /** 设计20最大进度金额 */
    private BigDecimal design20MaxProgressAmount;

    /** 设计20已发行金额 */
    private BigDecimal design20IssuedAmount;
    /** 主键 */
    private Long id;

    /** 专业负责人策划主表ID */
    private Long headId;

    /** 项目ID */
    private Long prjId;

    /** 子项名称 */
    @Excel(name = "项目名称")
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /** 项目子项ID */
    private Long itemId;

    /** 子项编号 */
    @Excel(name = "子项编号")
    private String itemNo;

    /** 子项名称 */
    @Excel(name = "子项名称")
    private String itemName;

    /** 子项权重 */
    @Excel(name = "子项权重")
    private BigDecimal itemWeight;

    /** 专业负责人 */
    @Excel(name = "专业负责人")
    private String proResp;

    /** EIM */
    @Excel(name = "EIM")
    private String eim;

    /** 总体方案 */
    @Excel(name = "总体方案")
    private String overallPlan;

    /** 校核 */
    @Excel(name = "校核")
    private String checker;

    /** 审核 */
    @Excel(name = "审核")
    private String reviewer;

    /** 压力管道审核 */
    @Excel(name = "压力管道审核")
    private String pipeReview;

    /** 审定 */
    @Excel(name = "审定")
    private String approval;

    /** 设计指导 */
    @Excel(name = "设计指导")
    private String designGuidance;

    /** 设计1 */
    @Excel(name = "设计1")
    private String design1;

    /** 设计2 */
    @Excel(name = "设计2")
    private String design2;

    /** 设计3 */
    @Excel(name = "设计3")
    private String design3;

    /** 设计4 */
    @Excel(name = "设计4")
    private String design4;

    /** 设计5 */
    @Excel(name = "设计5")
    private String design5;

    /** 设计6 */
    @Excel(name = "设计6")
    private String design6;

    /** 设计7 */
    @Excel(name = "设计7")
    private String design7;

    /** 设计8 */
    @Excel(name = "设计8")
    private String design8;

    /** 设计9 */
    @Excel(name = "设计9")
    private String design9;

    /** 设计10 */
    @Excel(name = "设计10")
    private String design10;

    /** 设计11 */
    @Excel(name = "设计11")
    private String design11;

    /** 设计12 */
    @Excel(name = "设计12")
    private String design12;

    /** 设计13 */
    @Excel(name = "设计13")
    private String design13;

    /** 设计14 */
    @Excel(name = "设计14")
    private String design14;

    /** 设计15 */
    @Excel(name = "设计15")
    private String design15;

    /** 设计16 */
    @Excel(name = "设计16")
    private String design16;

    /** 设计17 */
    @Excel(name = "设计17")
    private String design17;

    /** 设计18 */
    @Excel(name = "设计18")
    private String design18;

    /** 设计19 */
    @Excel(name = "设计19")
    private String design19;

    /** 设计20 */
    @Excel(name = "设计20")
    private String design20;

    /** 采购支持 */
    @Excel(name = "采购支持")
    private String procurementSupport;

    /** 顾问 */
    @Excel(name = "顾问")
    private String consultant;

    /** 专业负责人 */
    @Excel(name = "专业负责人")
    private BigDecimal proRespPct;

    /** EIM */
    @Excel(name = "EIM")
    private BigDecimal eimPct;

    /** 总体方案 */
    @Excel(name = "总体方案")
    private BigDecimal overallPlanPct;

    /** 校核 */
    @Excel(name = "校核")
    private BigDecimal checkerPct;

    /** 审核 */
    @Excel(name = "审核")
    private BigDecimal reviewerPct;

    /** 压力管道审核 */
    @Excel(name = "压力管道审核")
    private BigDecimal pipeReviewPct;

    /** 审定 */
    @Excel(name = "审定")
    private BigDecimal approvalPct;

    /** 设计指导 */
    @Excel(name = "设计指导")
    private BigDecimal designGuidancePct;

    /** 设计1比例 */
    @Excel(name = "设计1比例")
    private BigDecimal design1Pct;

    /** 设计2比例 */
    @Excel(name = "设计2比例")
    private BigDecimal design2Pct;

    /** 设计3比例 */
    @Excel(name = "设计3比例")
    private BigDecimal design3Pct;

    /** 设计4比例 */
    @Excel(name = "设计4比例")
    private BigDecimal design4Pct;

    /** 设计5比例 */
    @Excel(name = "设计5比例")
    private BigDecimal design5Pct;

    /** 设计6比例 */
    @Excel(name = "设计6比例")
    private BigDecimal design6Pct;

    /** 设计7比例 */
    @Excel(name = "设计7比例")
    private BigDecimal design7Pct;

    /** 设计8比例 */
    @Excel(name = "设计8比例")
    private BigDecimal design8Pct;

    /** 设计9比例 */
    @Excel(name = "设计9比例")
    private BigDecimal design9Pct;

    /** 设计10比例 */
    @Excel(name = "设计10比例")
    private BigDecimal design10Pct;

    /** 设计11比例 */
    @Excel(name = "设计11比例")
    private BigDecimal design11Pct;

    /** 设计12比例 */
    @Excel(name = "设计12比例")
    private BigDecimal design12Pct;

    /** 设计13比例 */
    @Excel(name = "设计13比例")
    private BigDecimal design13Pct;

    /** 设计14比例 */
    @Excel(name = "设计14比例")
    private BigDecimal design14Pct;

    /** 设计15比例 */
    @Excel(name = "设计15比例")
    private BigDecimal design15Pct;

    /** 设计16比例 */
    @Excel(name = "设计16比例")
    private BigDecimal design16Pct;

    /** 设计17比例 */
    @Excel(name = "设计17比例")
    private BigDecimal design17Pct;

    /** 设计18比例 */
    @Excel(name = "设计18比例")
    private BigDecimal design18Pct;

    /** 设计19比例 */
    @Excel(name = "设计19比例")
    private BigDecimal design19Pct;

    /** 设计20比例 */
    @Excel(name = "设计20比例")
    private BigDecimal design20Pct;

    /** 采购支持 */
    @Excel(name = "采购支持")
    private BigDecimal procurementSupportPct;

    /** 顾问 */
    @Excel(name = "顾问")
    private BigDecimal consultantPct;

    /** 设计1最终比例 */
    @Excel(name = "设计1最终比例")
    private BigDecimal final1Pct;

    /** 设计2最终比例 */
    @Excel(name = "设计2最终比例")
    private BigDecimal final2Pct;

    /** 设计3最终比例 */
    @Excel(name = "设计3最终比例")
    private BigDecimal final3Pct;

    /** 设计4最终比例 */
    @Excel(name = "设计4最终比例")
    private BigDecimal final4Pct;

    /** 设计5最终比例 */
    @Excel(name = "设计5最终比例")
    private BigDecimal final5Pct;

    /** 设计6最终比例 */
    @Excel(name = "设计6最终比例")
    private BigDecimal final6Pct;

    /** 设计7最终比例 */
    @Excel(name = "设计7最终比例")
    private BigDecimal final7Pct;

    /** 设计8最终比例 */
    @Excel(name = "设计8最终比例")
    private BigDecimal final8Pct;

    /** 设计9最终比例 */
    @Excel(name = "设计9最终比例")
    private BigDecimal final9Pct;

    /** 设计10最终比例 */
    @Excel(name = "设计10最终比例")
    private BigDecimal final10Pct;

    /** 设计11最终比例 */
    @Excel(name = "设计11最终比例")
    private BigDecimal final11Pct;

    /** 设计12最终比例 */
    @Excel(name = "设计12最终比例")
    private BigDecimal final12Pct;

    /** 设计13最终比例 */
    @Excel(name = "设计13最终比例")
    private BigDecimal final13Pct;

    /** 设计14最终比例 */
    @Excel(name = "设计14最终比例")
    private BigDecimal final14Pct;

    /** 设计15最终比例 */
    @Excel(name = "设计15最终比例")
    private BigDecimal final15Pct;

    /** 设计16最终比例 */
    @Excel(name = "设计16最终比例")
    private BigDecimal final16Pct;

    /** 设计17最终比例 */
    @Excel(name = "设计17最终比例")
    private BigDecimal final17Pct;

    /** 设计18最终比例 */
    @Excel(name = "设计18最终比例")
    private BigDecimal final18Pct;

    /** 设计19最终比例 */
    @Excel(name = "设计19最终比例")
    private BigDecimal final19Pct;

    /** 设计20最终比例 */
    @Excel(name = "设计20最终比例")
    private BigDecimal final20Pct;

    /** 设计1进度 */
    @Excel(name = "设计1进度")
    private BigDecimal progress1;

    /** 设计2进度 */
    @Excel(name = "设计2进度")
    private BigDecimal progress2;

    /** 设计3进度 */
    @Excel(name = "设计3进度")
    private BigDecimal progress3;

    /** 设计4进度 */
    @Excel(name = "设计4进度")
    private BigDecimal progress4;

    /** 设计5进度 */
    @Excel(name = "设计5进度")
    private BigDecimal progress5;

    /** 设计6进度 */
    @Excel(name = "设计6进度")
    private BigDecimal progress6;

    /** 设计7进度 */
    @Excel(name = "设计7进度")
    private BigDecimal progress7;

    /** 设计8进度 */
    @Excel(name = "设计8进度")
    private BigDecimal progress8;

    /** 设计9进度 */
    @Excel(name = "设计9进度")
    private BigDecimal progress9;

    /** 设计10进度 */
    @Excel(name = "设计10进度")
    private BigDecimal progress10;

    /** 设计11进度 */
    @Excel(name = "设计11进度")
    private BigDecimal progress11;

    /** 设计12进度 */
    @Excel(name = "设计12进度")
    private BigDecimal progress12;

    /** 设计13进度 */
    @Excel(name = "设计13进度")
    private BigDecimal progress13;

    /** 设计14进度 */
    @Excel(name = "设计14进度")
    private BigDecimal progress14;

    /** 设计15进度 */
    @Excel(name = "设计15进度")
    private BigDecimal progress15;

    /** 设计16进度 */
    @Excel(name = "设计16进度")
    private BigDecimal progress16;

    /** 设计17进度 */
    @Excel(name = "设计17进度")
    private BigDecimal progress17;

    /** 设计18进度 */
    @Excel(name = "设计18进度")
    private BigDecimal progress18;

    /** 设计19进度 */
    @Excel(name = "设计19进度")
    private BigDecimal progress19;

    /** 设计20进度 */
    @Excel(name = "设计20进度")
    private BigDecimal progress20;

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

    /** 部门ID */
    private Long deptId;

    /** 用户ID */
    private Long userId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setHeadId(Long headId)
    {
        this.headId = headId;
    }

    public Long getHeadId()
    {
        return headId;
    }
    public void setPrjId(Long prjId)
    {
        this.prjId = prjId;
    }

    public Long getPrjId()
    {
        return prjId;
    }
    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public Long getItemId()
    {
        return itemId;
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
    public void setItemWeight(BigDecimal itemWeight)
    {
        this.itemWeight = itemWeight;
    }

    public BigDecimal getItemWeight()
    {
        return itemWeight;
    }
    public void setProResp(String proResp)
    {
        this.proResp = proResp;
    }

    public String getProResp()
    {
        return proResp;
    }
    public void setEim(String eim)
    {
        this.eim = eim;
    }

    public String getEim()
    {
        return eim;
    }
    public void setOverallPlan(String overallPlan)
    {
        this.overallPlan = overallPlan;
    }

    public String getOverallPlan()
    {
        return overallPlan;
    }
    public void setChecker(String checker)
    {
        this.checker = checker;
    }

    public String getChecker()
    {
        return checker;
    }
    public void setReviewer(String reviewer)
    {
        this.reviewer = reviewer;
    }

    public String getReviewer()
    {
        return reviewer;
    }
    public void setPipeReview(String pipeReview)
    {
        this.pipeReview = pipeReview;
    }

    public String getPipeReview()
    {
        return pipeReview;
    }
    public void setApproval(String approval)
    {
        this.approval = approval;
    }

    public String getApproval()
    {
        return approval;
    }
    public void setDesignGuidance(String designGuidance)
    {
        this.designGuidance = designGuidance;
    }

    public String getDesignGuidance()
    {
        return designGuidance;
    }
    public void setDesign1(String design1)
    {
        this.design1 = design1;
    }

    public String getDesign1()
    {
        return design1;
    }
    public void setDesign2(String design2)
    {
        this.design2 = design2;
    }

    public String getDesign2()
    {
        return design2;
    }
    public void setDesign3(String design3)
    {
        this.design3 = design3;
    }

    public String getDesign3()
    {
        return design3;
    }
    public void setDesign4(String design4)
    {
        this.design4 = design4;
    }

    public String getDesign4()
    {
        return design4;
    }
    public void setDesign5(String design5)
    {
        this.design5 = design5;
    }

    public String getDesign5()
    {
        return design5;
    }
    public void setDesign6(String design6)
    {
        this.design6 = design6;
    }

    public String getDesign6()
    {
        return design6;
    }
    public void setDesign7(String design7)
    {
        this.design7 = design7;
    }

    public String getDesign7()
    {
        return design7;
    }
    public void setDesign8(String design8)
    {
        this.design8 = design8;
    }

    public String getDesign8()
    {
        return design8;
    }
    public void setDesign9(String design9)
    {
        this.design9 = design9;
    }

    public String getDesign9()
    {
        return design9;
    }
    public void setDesign10(String design10)
    {
        this.design10 = design10;
    }

    public String getDesign10()
    {
        return design10;
    }
    public void setDesign11(String design11)
    {
        this.design11 = design11;
    }

    public String getDesign11()
    {
        return design11;
    }
    public void setDesign12(String design12)
    {
        this.design12 = design12;
    }

    public String getDesign12()
    {
        return design12;
    }
    public void setDesign13(String design13)
    {
        this.design13 = design13;
    }

    public String getDesign13()
    {
        return design13;
    }
    public void setDesign14(String design14)
    {
        this.design14 = design14;
    }

    public String getDesign14()
    {
        return design14;
    }
    public void setDesign15(String design15)
    {
        this.design15 = design15;
    }

    public String getDesign15()
    {
        return design15;
    }
    public void setDesign16(String design16)
    {
        this.design16 = design16;
    }

    public String getDesign16()
    {
        return design16;
    }
    public void setDesign17(String design17)
    {
        this.design17 = design17;
    }

    public String getDesign17()
    {
        return design17;
    }
    public void setDesign18(String design18)
    {
        this.design18 = design18;
    }

    public String getDesign18()
    {
        return design18;
    }
    public void setDesign19(String design19)
    {
        this.design19 = design19;
    }

    public String getDesign19()
    {
        return design19;
    }
    public void setDesign20(String design20)
    {
        this.design20 = design20;
    }

    public String getDesign20()
    {
        return design20;
    }
    public void setProcurementSupport(String procurementSupport)
    {
        this.procurementSupport = procurementSupport;
    }

    public String getProcurementSupport()
    {
        return procurementSupport;
    }
    public void setConsultant(String consultant)
    {
        this.consultant = consultant;
    }

    public String getConsultant()
    {
        return consultant;
    }
    public void setProRespPct(BigDecimal proRespPct)
    {
        this.proRespPct = proRespPct;
    }

    public BigDecimal getProRespPct()
    {
        return proRespPct;
    }
    public void setEimPct(BigDecimal eimPct)
    {
        this.eimPct = eimPct;
    }

    public BigDecimal getEimPct()
    {
        return eimPct;
    }
    public void setOverallPlanPct(BigDecimal overallPlanPct)
    {
        this.overallPlanPct = overallPlanPct;
    }

    public BigDecimal getOverallPlanPct()
    {
        return overallPlanPct;
    }
    public void setCheckerPct(BigDecimal checkerPct)
    {
        this.checkerPct = checkerPct;
    }

    public BigDecimal getCheckerPct()
    {
        return checkerPct;
    }
    public void setReviewerPct(BigDecimal reviewerPct)
    {
        this.reviewerPct = reviewerPct;
    }

    public BigDecimal getReviewerPct()
    {
        return reviewerPct;
    }
    public void setPipeReviewPct(BigDecimal pipeReviewPct)
    {
        this.pipeReviewPct = pipeReviewPct;
    }

    public BigDecimal getPipeReviewPct()
    {
        return pipeReviewPct;
    }
    public void setApprovalPct(BigDecimal approvalPct)
    {
        this.approvalPct = approvalPct;
    }

    public BigDecimal getApprovalPct()
    {
        return approvalPct;
    }
    public void setDesignGuidancePct(BigDecimal designGuidancePct)
    {
        this.designGuidancePct = designGuidancePct;
    }

    public BigDecimal getDesignGuidancePct()
    {
        return designGuidancePct;
    }
    public void setDesign1Pct(BigDecimal design1Pct)
    {
        this.design1Pct = design1Pct;
    }

    public BigDecimal getDesign1Pct()
    {
        return design1Pct;
    }
    public void setDesign2Pct(BigDecimal design2Pct)
    {
        this.design2Pct = design2Pct;
    }

    public BigDecimal getDesign2Pct()
    {
        return design2Pct;
    }
    public void setDesign3Pct(BigDecimal design3Pct)
    {
        this.design3Pct = design3Pct;
    }

    public BigDecimal getDesign3Pct()
    {
        return design3Pct;
    }
    public void setDesign4Pct(BigDecimal design4Pct)
    {
        this.design4Pct = design4Pct;
    }

    public BigDecimal getDesign4Pct()
    {
        return design4Pct;
    }
    public void setDesign5Pct(BigDecimal design5Pct)
    {
        this.design5Pct = design5Pct;
    }

    public BigDecimal getDesign5Pct()
    {
        return design5Pct;
    }
    public void setDesign6Pct(BigDecimal design6Pct)
    {
        this.design6Pct = design6Pct;
    }

    public BigDecimal getDesign6Pct()
    {
        return design6Pct;
    }
    public void setDesign7Pct(BigDecimal design7Pct)
    {
        this.design7Pct = design7Pct;
    }

    public BigDecimal getDesign7Pct()
    {
        return design7Pct;
    }
    public void setDesign8Pct(BigDecimal design8Pct)
    {
        this.design8Pct = design8Pct;
    }

    public BigDecimal getDesign8Pct()
    {
        return design8Pct;
    }
    public void setDesign9Pct(BigDecimal design9Pct)
    {
        this.design9Pct = design9Pct;
    }

    public BigDecimal getDesign9Pct()
    {
        return design9Pct;
    }
    public void setDesign10Pct(BigDecimal design10Pct)
    {
        this.design10Pct = design10Pct;
    }

    public BigDecimal getDesign10Pct()
    {
        return design10Pct;
    }
    public void setDesign11Pct(BigDecimal design11Pct)
    {
        this.design11Pct = design11Pct;
    }

    public BigDecimal getDesign11Pct()
    {
        return design11Pct;
    }
    public void setDesign12Pct(BigDecimal design12Pct)
    {
        this.design12Pct = design12Pct;
    }

    public BigDecimal getDesign12Pct()
    {
        return design12Pct;
    }
    public void setDesign13Pct(BigDecimal design13Pct)
    {
        this.design13Pct = design13Pct;
    }

    public BigDecimal getDesign13Pct()
    {
        return design13Pct;
    }
    public void setDesign14Pct(BigDecimal design14Pct)
    {
        this.design14Pct = design14Pct;
    }

    public BigDecimal getDesign14Pct()
    {
        return design14Pct;
    }
    public void setDesign15Pct(BigDecimal design15Pct)
    {
        this.design15Pct = design15Pct;
    }

    public BigDecimal getDesign15Pct()
    {
        return design15Pct;
    }
    public void setDesign16Pct(BigDecimal design16Pct)
    {
        this.design16Pct = design16Pct;
    }

    public BigDecimal getDesign16Pct()
    {
        return design16Pct;
    }
    public void setDesign17Pct(BigDecimal design17Pct)
    {
        this.design17Pct = design17Pct;
    }

    public BigDecimal getDesign17Pct()
    {
        return design17Pct;
    }
    public void setDesign18Pct(BigDecimal design18Pct)
    {
        this.design18Pct = design18Pct;
    }

    public BigDecimal getDesign18Pct()
    {
        return design18Pct;
    }
    public void setDesign19Pct(BigDecimal design19Pct)
    {
        this.design19Pct = design19Pct;
    }

    public BigDecimal getDesign19Pct()
    {
        return design19Pct;
    }
    public void setDesign20Pct(BigDecimal design20Pct)
    {
        this.design20Pct = design20Pct;
    }

    public BigDecimal getDesign20Pct()
    {
        return design20Pct;
    }
    public void setProcurementSupportPct(BigDecimal procurementSupportPct)
    {
        this.procurementSupportPct = procurementSupportPct;
    }

    public BigDecimal getProcurementSupportPct()
    {
        return procurementSupportPct;
    }
    public void setConsultantPct(BigDecimal consultantPct)
    {
        this.consultantPct = consultantPct;
    }

    public BigDecimal getConsultantPct()
    {
        return consultantPct;
    }
    public void setFinal1Pct(BigDecimal final1Pct)
    {
        this.final1Pct = final1Pct;
    }

    public BigDecimal getFinal1Pct()
    {
        return final1Pct;
    }
    public void setFinal2Pct(BigDecimal final2Pct)
    {
        this.final2Pct = final2Pct;
    }

    public BigDecimal getFinal2Pct()
    {
        return final2Pct;
    }
    public void setFinal3Pct(BigDecimal final3Pct)
    {
        this.final3Pct = final3Pct;
    }

    public BigDecimal getFinal3Pct()
    {
        return final3Pct;
    }
    public void setFinal4Pct(BigDecimal final4Pct)
    {
        this.final4Pct = final4Pct;
    }

    public BigDecimal getFinal4Pct()
    {
        return final4Pct;
    }
    public void setFinal5Pct(BigDecimal final5Pct)
    {
        this.final5Pct = final5Pct;
    }

    public BigDecimal getFinal5Pct()
    {
        return final5Pct;
    }
    public void setFinal6Pct(BigDecimal final6Pct)
    {
        this.final6Pct = final6Pct;
    }

    public BigDecimal getFinal6Pct()
    {
        return final6Pct;
    }
    public void setFinal7Pct(BigDecimal final7Pct)
    {
        this.final7Pct = final7Pct;
    }

    public BigDecimal getFinal7Pct()
    {
        return final7Pct;
    }
    public void setFinal8Pct(BigDecimal final8Pct)
    {
        this.final8Pct = final8Pct;
    }

    public BigDecimal getFinal8Pct()
    {
        return final8Pct;
    }
    public void setFinal9Pct(BigDecimal final9Pct)
    {
        this.final9Pct = final9Pct;
    }

    public BigDecimal getFinal9Pct()
    {
        return final9Pct;
    }
    public void setFinal10Pct(BigDecimal final10Pct)
    {
        this.final10Pct = final10Pct;
    }

    public BigDecimal getFinal10Pct()
    {
        return final10Pct;
    }
    public void setFinal11Pct(BigDecimal final11Pct)
    {
        this.final11Pct = final11Pct;
    }

    public BigDecimal getFinal11Pct()
    {
        return final11Pct;
    }
    public void setFinal12Pct(BigDecimal final12Pct)
    {
        this.final12Pct = final12Pct;
    }

    public BigDecimal getFinal12Pct()
    {
        return final12Pct;
    }
    public void setFinal13Pct(BigDecimal final13Pct)
    {
        this.final13Pct = final13Pct;
    }

    public BigDecimal getFinal13Pct()
    {
        return final13Pct;
    }
    public void setFinal14Pct(BigDecimal final14Pct)
    {
        this.final14Pct = final14Pct;
    }

    public BigDecimal getFinal14Pct()
    {
        return final14Pct;
    }
    public void setFinal15Pct(BigDecimal final15Pct)
    {
        this.final15Pct = final15Pct;
    }

    public BigDecimal getFinal15Pct()
    {
        return final15Pct;
    }
    public void setFinal16Pct(BigDecimal final16Pct)
    {
        this.final16Pct = final16Pct;
    }

    public BigDecimal getFinal16Pct()
    {
        return final16Pct;
    }
    public void setFinal17Pct(BigDecimal final17Pct)
    {
        this.final17Pct = final17Pct;
    }

    public BigDecimal getFinal17Pct()
    {
        return final17Pct;
    }
    public void setFinal18Pct(BigDecimal final18Pct)
    {
        this.final18Pct = final18Pct;
    }

    public BigDecimal getFinal18Pct()
    {
        return final18Pct;
    }
    public void setFinal19Pct(BigDecimal final19Pct)
    {
        this.final19Pct = final19Pct;
    }

    public BigDecimal getFinal19Pct()
    {
        return final19Pct;
    }
    public void setFinal20Pct(BigDecimal final20Pct)
    {
        this.final20Pct = final20Pct;
    }

    public BigDecimal getFinal20Pct()
    {
        return final20Pct;
    }
    public void setProgress1(BigDecimal progress1)
    {
        this.progress1 = progress1;
    }

    public BigDecimal getProgress1()
    {
        return progress1;
    }
    public void setProgress2(BigDecimal progress2)
    {
        this.progress2 = progress2;
    }

    public BigDecimal getProgress2()
    {
        return progress2;
    }
    public void setProgress3(BigDecimal progress3)
    {
        this.progress3 = progress3;
    }

    public BigDecimal getProgress3()
    {
        return progress3;
    }
    public void setProgress4(BigDecimal progress4)
    {
        this.progress4 = progress4;
    }

    public BigDecimal getProgress4()
    {
        return progress4;
    }
    public void setProgress5(BigDecimal progress5)
    {
        this.progress5 = progress5;
    }

    public BigDecimal getProgress5()
    {
        return progress5;
    }
    public void setProgress6(BigDecimal progress6)
    {
        this.progress6 = progress6;
    }

    public BigDecimal getProgress6()
    {
        return progress6;
    }
    public void setProgress7(BigDecimal progress7)
    {
        this.progress7 = progress7;
    }

    public BigDecimal getProgress7()
    {
        return progress7;
    }
    public void setProgress8(BigDecimal progress8)
    {
        this.progress8 = progress8;
    }

    public BigDecimal getProgress8()
    {
        return progress8;
    }
    public void setProgress9(BigDecimal progress9)
    {
        this.progress9 = progress9;
    }

    public BigDecimal getProgress9()
    {
        return progress9;
    }
    public void setProgress10(BigDecimal progress10)
    {
        this.progress10 = progress10;
    }

    public BigDecimal getProgress10()
    {
        return progress10;
    }
    public void setProgress11(BigDecimal progress11)
    {
        this.progress11 = progress11;
    }

    public BigDecimal getProgress11()
    {
        return progress11;
    }
    public void setProgress12(BigDecimal progress12)
    {
        this.progress12 = progress12;
    }

    public BigDecimal getProgress12()
    {
        return progress12;
    }
    public void setProgress13(BigDecimal progress13)
    {
        this.progress13 = progress13;
    }

    public BigDecimal getProgress13()
    {
        return progress13;
    }
    public void setProgress14(BigDecimal progress14)
    {
        this.progress14 = progress14;
    }

    public BigDecimal getProgress14()
    {
        return progress14;
    }
    public void setProgress15(BigDecimal progress15)
    {
        this.progress15 = progress15;
    }

    public BigDecimal getProgress15()
    {
        return progress15;
    }
    public void setProgress16(BigDecimal progress16)
    {
        this.progress16 = progress16;
    }

    public BigDecimal getProgress16()
    {
        return progress16;
    }
    public void setProgress17(BigDecimal progress17)
    {
        this.progress17 = progress17;
    }

    public BigDecimal getProgress17()
    {
        return progress17;
    }
    public void setProgress18(BigDecimal progress18)
    {
        this.progress18 = progress18;
    }

    public BigDecimal getProgress18()
    {
        return progress18;
    }
    public void setProgress19(BigDecimal progress19)
    {
        this.progress19 = progress19;
    }

    public BigDecimal getProgress19()
    {
        return progress19;
    }
    public void setProgress20(BigDecimal progress20)
    {
        this.progress20 = progress20;
    }

    public BigDecimal getProgress20()
    {
        return progress20;
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
    // Getter 和 Setter 方法

    public BigDecimal getProRespProgress() {
        return proRespProgress;
    }

    public void setProRespProgress(BigDecimal proRespProgress) {
        this.proRespProgress = proRespProgress;
    }

    public BigDecimal getProRespFinalPct() {
        return proRespFinalPct;
    }

    public void setProRespFinalPct(BigDecimal proRespFinalPct) {
        this.proRespFinalPct = proRespFinalPct;
    }

    public BigDecimal getProRespMaxProgressAmount() {
        return proRespMaxProgressAmount;
    }

    public void setProRespMaxProgressAmount(BigDecimal proRespMaxProgressAmount) {
        this.proRespMaxProgressAmount = proRespMaxProgressAmount;
    }

    public BigDecimal getProRespIssuedAmount() {
        return proRespIssuedAmount;
    }

    public void setProRespIssuedAmount(BigDecimal proRespIssuedAmount) {
        this.proRespIssuedAmount = proRespIssuedAmount;
    }

    public BigDecimal getEimProgress() {
        return eimProgress;
    }

    public void setEimProgress(BigDecimal eimProgress) {
        this.eimProgress = eimProgress;
    }

    public BigDecimal getEimFinalPct() {
        return eimFinalPct;
    }

    public void setEimFinalPct(BigDecimal eimFinalPct) {
        this.eimFinalPct = eimFinalPct;
    }

    public BigDecimal getEimMaxProgressAmount() {
        return eimMaxProgressAmount;
    }

    public void setEimMaxProgressAmount(BigDecimal eimMaxProgressAmount) {
        this.eimMaxProgressAmount = eimMaxProgressAmount;
    }

    public BigDecimal getEimIssuedAmount() {
        return eimIssuedAmount;
    }

    public void setEimIssuedAmount(BigDecimal eimIssuedAmount) {
        this.eimIssuedAmount = eimIssuedAmount;
    }

    public BigDecimal getOverallPlanProgress() {
        return overallPlanProgress;
    }

    public void setOverallPlanProgress(BigDecimal overallPlanProgress) {
        this.overallPlanProgress = overallPlanProgress;
    }

    public BigDecimal getOverallPlanFinalPct() {
        return overallPlanFinalPct;
    }

    public void setOverallPlanFinalPct(BigDecimal overallPlanFinalPct) {
        this.overallPlanFinalPct = overallPlanFinalPct;
    }

    public BigDecimal getOverallPlanMaxProgressAmount() {
        return overallPlanMaxProgressAmount;
    }

    public void setOverallPlanMaxProgressAmount(BigDecimal overallPlanMaxProgressAmount) {
        this.overallPlanMaxProgressAmount = overallPlanMaxProgressAmount;
    }

    public BigDecimal getOverallPlanIssuedAmount() {
        return overallPlanIssuedAmount;
    }

    public void setOverallPlanIssuedAmount(BigDecimal overallPlanIssuedAmount) {
        this.overallPlanIssuedAmount = overallPlanIssuedAmount;
    }

    public BigDecimal getCheckerProgress() {
        return checkerProgress;
    }

    public void setCheckerProgress(BigDecimal checkerProgress) {
        this.checkerProgress = checkerProgress;
    }

    public BigDecimal getCheckerFinalPct() {
        return checkerFinalPct;
    }

    public void setCheckerFinalPct(BigDecimal checkerFinalPct) {
        this.checkerFinalPct = checkerFinalPct;
    }

    public BigDecimal getCheckerMaxProgressAmount() {
        return checkerMaxProgressAmount;
    }

    public void setCheckerMaxProgressAmount(BigDecimal checkerMaxProgressAmount) {
        this.checkerMaxProgressAmount = checkerMaxProgressAmount;
    }

    public BigDecimal getCheckerIssuedAmount() {
        return checkerIssuedAmount;
    }

    public void setCheckerIssuedAmount(BigDecimal checkerIssuedAmount) {
        this.checkerIssuedAmount = checkerIssuedAmount;
    }

    public BigDecimal getReviewerProgress() {
        return reviewerProgress;
    }

    public void setReviewerProgress(BigDecimal reviewerProgress) {
        this.reviewerProgress = reviewerProgress;
    }

    public BigDecimal getReviewerFinalPct() {
        return reviewerFinalPct;
    }

    public void setReviewerFinalPct(BigDecimal reviewerFinalPct) {
        this.reviewerFinalPct = reviewerFinalPct;
    }

    public BigDecimal getReviewerMaxProgressAmount() {
        return reviewerMaxProgressAmount;
    }

    public void setReviewerMaxProgressAmount(BigDecimal reviewerMaxProgressAmount) {
        this.reviewerMaxProgressAmount = reviewerMaxProgressAmount;
    }

    public BigDecimal getReviewerIssuedAmount() {
        return reviewerIssuedAmount;
    }

    public void setReviewerIssuedAmount(BigDecimal reviewerIssuedAmount) {
        this.reviewerIssuedAmount = reviewerIssuedAmount;
    }

    public BigDecimal getPipeReviewProgress() {
        return pipeReviewProgress;
    }

    public void setPipeReviewProgress(BigDecimal pipeReviewProgress) {
        this.pipeReviewProgress = pipeReviewProgress;
    }

    public BigDecimal getPipeReviewFinalPct() {
        return pipeReviewFinalPct;
    }

    public void setPipeReviewFinalPct(BigDecimal pipeReviewFinalPct) {
        this.pipeReviewFinalPct = pipeReviewFinalPct;
    }

    public BigDecimal getPipeReviewMaxProgressAmount() {
        return pipeReviewMaxProgressAmount;
    }

    public void setPipeReviewMaxProgressAmount(BigDecimal pipeReviewMaxProgressAmount) {
        this.pipeReviewMaxProgressAmount = pipeReviewMaxProgressAmount;
    }

    public BigDecimal getPipeReviewIssuedAmount() {
        return pipeReviewIssuedAmount;
    }

    public void setPipeReviewIssuedAmount(BigDecimal pipeReviewIssuedAmount) {
        this.pipeReviewIssuedAmount = pipeReviewIssuedAmount;
    }

    public BigDecimal getApprovalProgress() {
        return approvalProgress;
    }

    public void setApprovalProgress(BigDecimal approvalProgress) {
        this.approvalProgress = approvalProgress;
    }

    public BigDecimal getApprovalFinalPct() {
        return approvalFinalPct;
    }

    public void setApprovalFinalPct(BigDecimal approvalFinalPct) {
        this.approvalFinalPct = approvalFinalPct;
    }

    public BigDecimal getApprovalMaxProgressAmount() {
        return approvalMaxProgressAmount;
    }

    public void setApprovalMaxProgressAmount(BigDecimal approvalMaxProgressAmount) {
        this.approvalMaxProgressAmount = approvalMaxProgressAmount;
    }

    public BigDecimal getApprovalIssuedAmount() {
        return approvalIssuedAmount;
    }

    public void setApprovalIssuedAmount(BigDecimal approvalIssuedAmount) {
        this.approvalIssuedAmount = approvalIssuedAmount;
    }

    public BigDecimal getDesignGuidanceProgress() {
        return designGuidanceProgress;
    }

    public void setDesignGuidanceProgress(BigDecimal designGuidanceProgress) {
        this.designGuidanceProgress = designGuidanceProgress;
    }

    public BigDecimal getDesignGuidanceFinalPct() {
        return designGuidanceFinalPct;
    }

    public void setDesignGuidanceFinalPct(BigDecimal designGuidanceFinalPct) {
        this.designGuidanceFinalPct = designGuidanceFinalPct;
    }

    public BigDecimal getDesignGuidanceMaxProgressAmount() {
        return designGuidanceMaxProgressAmount;
    }

    public void setDesignGuidanceMaxProgressAmount(BigDecimal designGuidanceMaxProgressAmount) {
        this.designGuidanceMaxProgressAmount = designGuidanceMaxProgressAmount;
    }

    public BigDecimal getDesignGuidanceIssuedAmount() {
        return designGuidanceIssuedAmount;
    }

    public void setDesignGuidanceIssuedAmount(BigDecimal designGuidanceIssuedAmount) {
        this.designGuidanceIssuedAmount = designGuidanceIssuedAmount;
    }

    public BigDecimal getConsultantProgress() {
        return consultantProgress;
    }

    public void setConsultantProgress(BigDecimal consultantProgress) {
        this.consultantProgress = consultantProgress;
    }

    public BigDecimal getConsultantFinalPct() {
        return consultantFinalPct;
    }

    public void setConsultantFinalPct(BigDecimal consultantFinalPct) {
        this.consultantFinalPct = consultantFinalPct;
    }

    public BigDecimal getConsultantMaxProgressAmount() {
        return consultantMaxProgressAmount;
    }

    public void setConsultantMaxProgressAmount(BigDecimal consultantMaxProgressAmount) {
        this.consultantMaxProgressAmount = consultantMaxProgressAmount;
    }

    public BigDecimal getConsultantIssuedAmount() {
        return consultantIssuedAmount;
    }

    public void setConsultantIssuedAmount(BigDecimal consultantIssuedAmount) {
        this.consultantIssuedAmount = consultantIssuedAmount;
    }

    public BigDecimal getProcurementSupportProgress() {
        return procurementSupportProgress;
    }

    public void setProcurementSupportProgress(BigDecimal procurementSupportProgress) {
        this.procurementSupportProgress = procurementSupportProgress;
    }

    public BigDecimal getProcurementSupportFinalPct() {
        return procurementSupportFinalPct;
    }

    public void setProcurementSupportFinalPct(BigDecimal procurementSupportFinalPct) {
        this.procurementSupportFinalPct = procurementSupportFinalPct;
    }

    public BigDecimal getProcurementSupportMaxProgressAmount() {
        return procurementSupportMaxProgressAmount;
    }

    public void setProcurementSupportMaxProgressAmount(BigDecimal procurementSupportMaxProgressAmount) {
        this.procurementSupportMaxProgressAmount = procurementSupportMaxProgressAmount;
    }

    public BigDecimal getProcurementSupportIssuedAmount() {
        return procurementSupportIssuedAmount;
    }

    public void setProcurementSupportIssuedAmount(BigDecimal procurementSupportIssuedAmount) {
        this.procurementSupportIssuedAmount = procurementSupportIssuedAmount;
    }

    public BigDecimal getDesign1MaxProgressAmount() {
        return design1MaxProgressAmount;
    }

    public void setDesign1MaxProgressAmount(BigDecimal design1MaxProgressAmount) {
        this.design1MaxProgressAmount = design1MaxProgressAmount;
    }

    public BigDecimal getDesign1IssuedAmount() {
        return design1IssuedAmount;
    }

    public void setDesign1IssuedAmount(BigDecimal design1IssuedAmount) {
        this.design1IssuedAmount = design1IssuedAmount;
    }

    public BigDecimal getDesign2MaxProgressAmount() {
        return design2MaxProgressAmount;
    }

    public void setDesign2MaxProgressAmount(BigDecimal design2MaxProgressAmount) {
        this.design2MaxProgressAmount = design2MaxProgressAmount;
    }

    public BigDecimal getDesign2IssuedAmount() {
        return design2IssuedAmount;
    }

    public void setDesign2IssuedAmount(BigDecimal design2IssuedAmount) {
        this.design2IssuedAmount = design2IssuedAmount;
    }

    public BigDecimal getDesign3MaxProgressAmount() {
        return design3MaxProgressAmount;
    }

    public void setDesign3MaxProgressAmount(BigDecimal design3MaxProgressAmount) {
        this.design3MaxProgressAmount = design3MaxProgressAmount;
    }

    public BigDecimal getDesign3IssuedAmount() {
        return design3IssuedAmount;
    }

    public void setDesign3IssuedAmount(BigDecimal design3IssuedAmount) {
        this.design3IssuedAmount = design3IssuedAmount;
    }
    public BigDecimal getDesign4MaxProgressAmount() {
        return design4MaxProgressAmount;
    }

    public void setDesign4MaxProgressAmount(BigDecimal design4MaxProgressAmount) {
        this.design4MaxProgressAmount = design4MaxProgressAmount;
    }

    public BigDecimal getDesign4IssuedAmount() {
        return design4IssuedAmount;
    }

    public void setDesign4IssuedAmount(BigDecimal design4IssuedAmount) {
        this.design4IssuedAmount = design4IssuedAmount;
    }

    public BigDecimal getDesign5MaxProgressAmount() {
        return design5MaxProgressAmount;
    }

    public void setDesign5MaxProgressAmount(BigDecimal design5MaxProgressAmount) {
        this.design5MaxProgressAmount = design5MaxProgressAmount;
    }

    public BigDecimal getDesign5IssuedAmount() {
        return design5IssuedAmount;
    }

    public void setDesign5IssuedAmount(BigDecimal design5IssuedAmount) {
        this.design5IssuedAmount = design5IssuedAmount;
    }

    public BigDecimal getDesign6MaxProgressAmount() {
        return design6MaxProgressAmount;
    }

    public void setDesign6MaxProgressAmount(BigDecimal design6MaxProgressAmount) {
        this.design6MaxProgressAmount = design6MaxProgressAmount;
    }

    public BigDecimal getDesign6IssuedAmount() {
        return design6IssuedAmount;
    }

    public void setDesign6IssuedAmount(BigDecimal design6IssuedAmount) {
        this.design6IssuedAmount = design6IssuedAmount;
    }

    public BigDecimal getDesign7MaxProgressAmount() {
        return design7MaxProgressAmount;
    }

    public void setDesign7MaxProgressAmount(BigDecimal design7MaxProgressAmount) {
        this.design7MaxProgressAmount = design7MaxProgressAmount;
    }

    public BigDecimal getDesign7IssuedAmount() {
        return design7IssuedAmount;
    }

    public void setDesign7IssuedAmount(BigDecimal design7IssuedAmount) {
        this.design7IssuedAmount = design7IssuedAmount;
    }

    public BigDecimal getDesign8MaxProgressAmount() {
        return design8MaxProgressAmount;
    }

    public void setDesign8MaxProgressAmount(BigDecimal design8MaxProgressAmount) {
        this.design8MaxProgressAmount = design8MaxProgressAmount;
    }

    public BigDecimal getDesign8IssuedAmount() {
        return design8IssuedAmount;
    }

    public void setDesign8IssuedAmount(BigDecimal design8IssuedAmount) {
        this.design8IssuedAmount = design8IssuedAmount;
    }

    public BigDecimal getDesign9MaxProgressAmount() {
        return design9MaxProgressAmount;
    }

    public void setDesign9MaxProgressAmount(BigDecimal design9MaxProgressAmount) {
        this.design9MaxProgressAmount = design9MaxProgressAmount;
    }

    public BigDecimal getDesign9IssuedAmount() {
        return design9IssuedAmount;
    }

    public void setDesign9IssuedAmount(BigDecimal design9IssuedAmount) {
        this.design9IssuedAmount = design9IssuedAmount;
    }

    public BigDecimal getDesign10MaxProgressAmount() {
        return design10MaxProgressAmount;
    }

    public void setDesign10MaxProgressAmount(BigDecimal design10MaxProgressAmount) {
        this.design10MaxProgressAmount = design10MaxProgressAmount;
    }

    public BigDecimal getDesign10IssuedAmount() {
        return design10IssuedAmount;
    }

    public void setDesign10IssuedAmount(BigDecimal design10IssuedAmount) {
        this.design10IssuedAmount = design10IssuedAmount;
    }

    public BigDecimal getDesign11MaxProgressAmount() {
        return design11MaxProgressAmount;
    }

    public void setDesign11MaxProgressAmount(BigDecimal design11MaxProgressAmount) {
        this.design11MaxProgressAmount = design11MaxProgressAmount;
    }

    public BigDecimal getDesign11IssuedAmount() {
        return design11IssuedAmount;
    }

    public void setDesign11IssuedAmount(BigDecimal design11IssuedAmount) {
        this.design11IssuedAmount = design11IssuedAmount;
    }

    public BigDecimal getDesign12MaxProgressAmount() {
        return design12MaxProgressAmount;
    }

    public void setDesign12MaxProgressAmount(BigDecimal design12MaxProgressAmount) {
        this.design12MaxProgressAmount = design12MaxProgressAmount;
    }

    public BigDecimal getDesign12IssuedAmount() {
        return design12IssuedAmount;
    }

    public void setDesign12IssuedAmount(BigDecimal design12IssuedAmount) {
        this.design12IssuedAmount = design12IssuedAmount;
    }

    public BigDecimal getDesign13MaxProgressAmount() {
        return design13MaxProgressAmount;
    }

    public void setDesign13MaxProgressAmount(BigDecimal design13MaxProgressAmount) {
        this.design13MaxProgressAmount = design13MaxProgressAmount;
    }

    public BigDecimal getDesign13IssuedAmount() {
        return design13IssuedAmount;
    }

    public void setDesign13IssuedAmount(BigDecimal design13IssuedAmount) {
        this.design13IssuedAmount = design13IssuedAmount;
    }

    public BigDecimal getDesign14MaxProgressAmount() {
        return design14MaxProgressAmount;
    }

    public void setDesign14MaxProgressAmount(BigDecimal design14MaxProgressAmount) {
        this.design14MaxProgressAmount = design14MaxProgressAmount;
    }

    public BigDecimal getDesign14IssuedAmount() {
        return design14IssuedAmount;
    }

    public void setDesign14IssuedAmount(BigDecimal design14IssuedAmount) {
        this.design14IssuedAmount = design14IssuedAmount;
    }

    public BigDecimal getDesign15MaxProgressAmount() {
        return design15MaxProgressAmount;
    }

    public void setDesign15MaxProgressAmount(BigDecimal design15MaxProgressAmount) {
        this.design15MaxProgressAmount = design15MaxProgressAmount;
    }

    public BigDecimal getDesign15IssuedAmount() {
        return design15IssuedAmount;
    }

    public void setDesign15IssuedAmount(BigDecimal design15IssuedAmount) {
        this.design15IssuedAmount = design15IssuedAmount;
    }

    public BigDecimal getDesign16MaxProgressAmount() {
        return design16MaxProgressAmount;
    }

    public void setDesign16MaxProgressAmount(BigDecimal design16MaxProgressAmount) {
        this.design16MaxProgressAmount = design16MaxProgressAmount;
    }

    public BigDecimal getDesign16IssuedAmount() {
        return design16IssuedAmount;
    }

    public void setDesign16IssuedAmount(BigDecimal design16IssuedAmount) {
        this.design16IssuedAmount = design16IssuedAmount;
    }

    public BigDecimal getDesign17MaxProgressAmount() {
        return design17MaxProgressAmount;
    }

    public void setDesign17MaxProgressAmount(BigDecimal design17MaxProgressAmount) {
        this.design17MaxProgressAmount = design17MaxProgressAmount;
    }

    public BigDecimal getDesign17IssuedAmount() {
        return design17IssuedAmount;
    }

    public void setDesign17IssuedAmount(BigDecimal design17IssuedAmount) {
        this.design17IssuedAmount = design17IssuedAmount;
    }

    public BigDecimal getDesign18MaxProgressAmount() {
        return design18MaxProgressAmount;
    }

    public void setDesign18MaxProgressAmount(BigDecimal design18MaxProgressAmount) {
        this.design18MaxProgressAmount = design18MaxProgressAmount;
    }

    public BigDecimal getDesign18IssuedAmount() {
        return design18IssuedAmount;
    }

    public void setDesign18IssuedAmount(BigDecimal design18IssuedAmount) {
        this.design18IssuedAmount = design18IssuedAmount;
    }

    public BigDecimal getDesign19MaxProgressAmount() {
        return design19MaxProgressAmount;
    }

    public void setDesign19MaxProgressAmount(BigDecimal design19MaxProgressAmount) {
        this.design19MaxProgressAmount = design19MaxProgressAmount;
    }

    public BigDecimal getDesign19IssuedAmount() {
        return design19IssuedAmount;
    }

    public void setDesign19IssuedAmount(BigDecimal design19IssuedAmount) {
        this.design19IssuedAmount = design19IssuedAmount;
    }

    public BigDecimal getDesign20MaxProgressAmount() {
        return design20MaxProgressAmount;
    }

    public void setDesign20MaxProgressAmount(BigDecimal design20MaxProgressAmount) {
        this.design20MaxProgressAmount = design20MaxProgressAmount;
    }

    public BigDecimal getDesign20IssuedAmount() {
        return design20IssuedAmount;
    }

    public void setDesign20IssuedAmount(BigDecimal design20IssuedAmount) {
        this.design20IssuedAmount = design20IssuedAmount;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("headId", getHeadId())
                .append("prjId", getPrjId())
                .append("projectName",getProjectName())
                .append("itemId", getItemId())
                .append("itemNo", getItemNo())
                .append("itemName", getItemName())
                .append("itemWeight", getItemWeight())
                .append("proResp", getProResp())
                .append("eim", getEim())
                .append("overallPlan", getOverallPlan())
                .append("checker", getChecker())
                .append("reviewer", getReviewer())
                .append("pipeReview", getPipeReview())
                .append("approval", getApproval())
                .append("designGuidance", getDesignGuidance())
                .append("design1", getDesign1())
                .append("design2", getDesign2())
                .append("design3", getDesign3())
                .append("design4", getDesign4())
                .append("design5", getDesign5())
                .append("design6", getDesign6())
                .append("design7", getDesign7())
                .append("design8", getDesign8())
                .append("design9", getDesign9())
                .append("design10", getDesign10())
                .append("design11", getDesign11())
                .append("design12", getDesign12())
                .append("design13", getDesign13())
                .append("design14", getDesign14())
                .append("design15", getDesign15())
                .append("design16", getDesign16())
                .append("design17", getDesign17())
                .append("design18", getDesign18())
                .append("design19", getDesign19())
                .append("design20", getDesign20())
                .append("procurementSupport", getProcurementSupport())
                .append("consultant", getConsultant())
                .append("proRespPct", getProRespPct())
                .append("eimPct", getEimPct())
                .append("overallPlanPct", getOverallPlanPct())
                .append("checkerPct", getCheckerPct())
                .append("reviewerPct", getReviewerPct())
                .append("pipeReviewPct", getPipeReviewPct())
                .append("approvalPct", getApprovalPct())
                .append("designGuidancePct", getDesignGuidancePct())
                .append("design1Pct", getDesign1Pct())
                .append("design2Pct", getDesign2Pct())
                .append("design3Pct", getDesign3Pct())
                .append("design4Pct", getDesign4Pct())
                .append("design5Pct", getDesign5Pct())
                .append("design6Pct", getDesign6Pct())
                .append("design7Pct", getDesign7Pct())
                .append("design8Pct", getDesign8Pct())
                .append("design9Pct", getDesign9Pct())
                .append("design10Pct", getDesign10Pct())
                .append("design11Pct", getDesign11Pct())
                .append("design12Pct", getDesign12Pct())
                .append("design13Pct", getDesign13Pct())
                .append("design14Pct", getDesign14Pct())
                .append("design15Pct", getDesign15Pct())
                .append("design16Pct", getDesign16Pct())
                .append("design17Pct", getDesign17Pct())
                .append("design18Pct", getDesign18Pct())
                .append("design19Pct", getDesign19Pct())
                .append("design20Pct", getDesign20Pct())
                .append("procurementSupportPct", getProcurementSupportPct())
                .append("consultantPct", getConsultantPct())
                .append("final1Pct", getFinal1Pct())
                .append("final2Pct", getFinal2Pct())
                .append("final3Pct", getFinal3Pct())
                .append("final4Pct", getFinal4Pct())
                .append("final5Pct", getFinal5Pct())
                .append("final6Pct", getFinal6Pct())
                .append("final7Pct", getFinal7Pct())
                .append("final8Pct", getFinal8Pct())
                .append("final9Pct", getFinal9Pct())
                .append("final10Pct", getFinal10Pct())
                .append("final11Pct", getFinal11Pct())
                .append("final12Pct", getFinal12Pct())
                .append("final13Pct", getFinal13Pct())
                .append("final14Pct", getFinal14Pct())
                .append("final15Pct", getFinal15Pct())
                .append("final16Pct", getFinal16Pct())
                .append("final17Pct", getFinal17Pct())
                .append("final18Pct", getFinal18Pct())
                .append("final19Pct", getFinal19Pct())
                .append("final20Pct", getFinal20Pct())
                .append("progress1", getProgress1())
                .append("progress2", getProgress2())
                .append("progress3", getProgress3())
                .append("progress4", getProgress4())
                .append("progress5", getProgress5())
                .append("progress6", getProgress6())
                .append("progress7", getProgress7())
                .append("progress8", getProgress8())
                .append("progress9", getProgress9())
                .append("progress10", getProgress10())
                .append("progress11", getProgress11())
                .append("progress12", getProgress12())
                .append("progress13", getProgress13())
                .append("progress14", getProgress14())
                .append("progress15", getProgress15())
                .append("progress16", getProgress16())
                .append("progress17", getProgress17())
                .append("progress18", getProgress18())
                .append("progress19", getProgress19())
                .append("progress20", getProgress20())
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
                // 新增字段
                .append("proRespProgress", getProRespProgress())
                .append("proRespFinalPct", getProRespFinalPct())
                .append("proRespMaxProgressAmount", getProRespMaxProgressAmount())
                .append("proRespIssuedAmount", getProRespIssuedAmount())
                .append("eimProgress", getEimProgress())
                .append("eimFinalPct", getEimFinalPct())
                .append("eimMaxProgressAmount", getEimMaxProgressAmount())
                .append("eimIssuedAmount", getEimIssuedAmount())
                .append("overallPlanProgress", getOverallPlanProgress())
                .append("overallPlanFinalPct", getOverallPlanFinalPct())
                .append("overallPlanMaxProgressAmount", getOverallPlanMaxProgressAmount())
                .append("overallPlanIssuedAmount", getOverallPlanIssuedAmount())
                .append("checkerProgress", getCheckerProgress())
                .append("checkerFinalPct", getCheckerFinalPct())
                .append("checkerMaxProgressAmount", getCheckerMaxProgressAmount())
                .append("checkerIssuedAmount", getCheckerIssuedAmount())
                .append("reviewerProgress", getReviewerProgress())
                .append("reviewerFinalPct", getReviewerFinalPct())
                .append("reviewerMaxProgressAmount", getReviewerMaxProgressAmount())
                .append("reviewerIssuedAmount", getReviewerIssuedAmount())
                .append("pipeReviewProgress", getPipeReviewProgress())
                .append("pipeReviewFinalPct", getPipeReviewFinalPct())
                .append("pipeReviewMaxProgressAmount", getPipeReviewMaxProgressAmount())
                .append("pipeReviewIssuedAmount", getPipeReviewIssuedAmount())
                .append("approvalProgress", getApprovalProgress())
                .append("approvalFinalPct", getApprovalFinalPct())
                .append("approvalMaxProgressAmount", getApprovalMaxProgressAmount())
                .append("approvalIssuedAmount", getApprovalIssuedAmount())
                .append("designGuidanceProgress", getDesignGuidanceProgress())
                .append("designGuidanceFinalPct", getDesignGuidanceFinalPct())
                .append("designGuidanceMaxProgressAmount", getDesignGuidanceMaxProgressAmount())
                .append("designGuidanceIssuedAmount", getDesignGuidanceIssuedAmount())
                .append("consultantProgress", getConsultantProgress())
                .append("consultantFinalPct", getConsultantFinalPct())
                .append("consultantMaxProgressAmount", getConsultantMaxProgressAmount())
                .append("consultantIssuedAmount", getConsultantIssuedAmount())
                .append("procurementSupportProgress", getProcurementSupportProgress())
                .append("procurementSupportFinalPct", getProcurementSupportFinalPct())
                .append("procurementSupportMaxProgressAmount", getProcurementSupportMaxProgressAmount())
                .append("procurementSupportIssuedAmount", getProcurementSupportIssuedAmount())
                .append("design1MaxProgressAmount", getDesign1MaxProgressAmount())
                .append("design1IssuedAmount", getDesign1IssuedAmount())
                .append("design2MaxProgressAmount", getDesign2MaxProgressAmount())
                .append("design2IssuedAmount", getDesign2IssuedAmount())
                .append("design3MaxProgressAmount", getDesign3MaxProgressAmount())
                .append("design3IssuedAmount", getDesign3IssuedAmount())
                .append("design4MaxProgressAmount", getDesign4MaxProgressAmount())
                .append("design4IssuedAmount", getDesign4IssuedAmount())
                .append("design5MaxProgressAmount", getDesign5MaxProgressAmount())
                .append("design5IssuedAmount", getDesign5IssuedAmount())
                .append("design6MaxProgressAmount", getDesign6MaxProgressAmount())
                .append("design6IssuedAmount", getDesign6IssuedAmount())
                .append("design7MaxProgressAmount", getDesign7MaxProgressAmount())
                .append("design7IssuedAmount", getDesign7IssuedAmount())
                .append("design8MaxProgressAmount", getDesign8MaxProgressAmount())
                .append("design8IssuedAmount", getDesign8IssuedAmount())
                .append("design9MaxProgressAmount", getDesign9MaxProgressAmount())
                .append("design9IssuedAmount", getDesign9IssuedAmount())
                .append("design10MaxProgressAmount", getDesign10MaxProgressAmount())
                .append("design10IssuedAmount", getDesign10IssuedAmount())
                .append("design11MaxProgressAmount", getDesign11MaxProgressAmount())
                .append("design11IssuedAmount", getDesign11IssuedAmount())
                .append("design12MaxProgressAmount", getDesign12MaxProgressAmount())
                .append("design12IssuedAmount", getDesign12IssuedAmount())
                .append("design13MaxProgressAmount", getDesign13MaxProgressAmount())
                .append("design13IssuedAmount", getDesign13IssuedAmount())
                .append("design14MaxProgressAmount", getDesign14MaxProgressAmount())
                .append("design14IssuedAmount", getDesign14IssuedAmount())
                .append("design15MaxProgressAmount", getDesign15MaxProgressAmount())
                .append("design15IssuedAmount", getDesign15IssuedAmount())
                .append("design16MaxProgressAmount", getDesign16MaxProgressAmount())
                .append("design16IssuedAmount", getDesign16IssuedAmount())
                .append("design17MaxProgressAmount", getDesign17MaxProgressAmount())
                .append("design17IssuedAmount", getDesign17IssuedAmount())
                .append("design18MaxProgressAmount", getDesign18MaxProgressAmount())
                .append("design18IssuedAmount", getDesign18IssuedAmount())
                .append("design19MaxProgressAmount", getDesign19MaxProgressAmount())
                .append("design19IssuedAmount", getDesign19IssuedAmount())
                .append("design20MaxProgressAmount", getDesign20MaxProgressAmount())
                .append("design20IssuedAmount", getDesign20IssuedAmount())
                .toString();
    }

}
