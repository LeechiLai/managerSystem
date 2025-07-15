package com.cndc.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cndc.common.annotation.Excel;
import com.cndc.common.core.domain.BaseEntity;

/**
 * 专业负责人策划对象 tb_subitems_planning
 * 
 * @author laizhiying
 * @date 2024-12-19
 */
public class SubitemsPlanning extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long prjId;

    /** 项目子项ID */
    @Excel(name = "项目子项ID")
    private Long itemId;

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

    /** 设计进度 */
    @Excel(name = "进度1")
    private BigDecimal progress1;
    /** 设计进度 */
    @Excel(name = "进度2")
    private BigDecimal progress2;
    /** 设计进度 */
    @Excel(name = "进度3")
    private BigDecimal progress3;
    /** 设计进度 */
    @Excel(name = "进度4")
    private BigDecimal progress4;
    /** 设计进度 */
    @Excel(name = "进度5")
    private BigDecimal progress5;
    /** 设计进度 */
    @Excel(name = "进度6")
    private BigDecimal progress6;
    /** 设计进度 */
    @Excel(name = "进度7")
    private BigDecimal progress7;
    /** 设计进度 */
    @Excel(name = "进度8")
    private BigDecimal progress8;
    /** 设计进度 */
    @Excel(name = "进度9")
    private BigDecimal progress9;
    /** 设计进度 */
    @Excel(name = "进度10")
    private BigDecimal progress10;
    /** 设计进度 */
    @Excel(name = "进度11")
    private BigDecimal progress11;
    /** 设计进度 */
    @Excel(name = "进度12")
    private BigDecimal progress12;
    /** 设计进度 */
    @Excel(name = "进度13")
    private BigDecimal progress13;
    /** 设计进度 */
    @Excel(name = "进度14")
    private BigDecimal progress14;
    /** 设计进度 */
    @Excel(name = "进度15")
    private BigDecimal progress15;
    /** 设计进度 */
    @Excel(name = "进度16")
    private BigDecimal progress16;
    /** 设计进度 */
    @Excel(name = "进度17")
    private BigDecimal progress17;
    /** 设计进度 */
    @Excel(name = "进度18")
    private BigDecimal progress18;
    /** 设计进度 */
    @Excel(name = "进度19")
    private BigDecimal progress19;
    /** 设计进度 */
    @Excel(name = "进度20")
    private BigDecimal progress20;

    public BigDecimal getProgress1() {
        return progress1;
    }

    public void setProgress1(BigDecimal progress1) {
        this.progress1 = progress1;
    }

    public BigDecimal getProgress2() {
        return progress2;
    }

    public void setProgress2(BigDecimal progress2) {
        this.progress2 = progress2;
    }

    public BigDecimal getProgress3() {
        return progress3;
    }

    public void setProgress3(BigDecimal progress3) {
        this.progress3 = progress3;
    }

    public BigDecimal getProgress4() {
        return progress4;
    }

    public void setProgress4(BigDecimal progress4) {
        this.progress4 = progress4;
    }

    public BigDecimal getProgress5() {
        return progress5;
    }

    public void setProgress5(BigDecimal progress5) {
        this.progress5 = progress5;
    }

    public BigDecimal getProgress6() {
        return progress6;
    }

    public void setProgress6(BigDecimal progress6) {
        this.progress6 = progress6;
    }

    public BigDecimal getProgress7() {
        return progress7;
    }

    public void setProgress7(BigDecimal progress7) {
        this.progress7 = progress7;
    }

    public BigDecimal getProgress8() {
        return progress8;
    }

    public void setProgress8(BigDecimal progress8) {
        this.progress8 = progress8;
    }

    public BigDecimal getProgress9() {
        return progress9;
    }

    public void setProgress9(BigDecimal progress9) {
        this.progress9 = progress9;
    }

    public BigDecimal getProgress10() {
        return progress10;
    }

    public void setProgress10(BigDecimal progress10) {
        this.progress10 = progress10;
    }

    public BigDecimal getProgress11() {
        return progress11;
    }

    public void setProgress11(BigDecimal progress11) {
        this.progress11 = progress11;
    }

    public BigDecimal getProgress12() {
        return progress12;
    }

    public void setProgress12(BigDecimal progress12) {
        this.progress12 = progress12;
    }

    public BigDecimal getProgress13() {
        return progress13;
    }

    public void setProgress13(BigDecimal progress13) {
        this.progress13 = progress13;
    }

    public BigDecimal getProgress14() {
        return progress14;
    }

    public void setProgress14(BigDecimal progress14) {
        this.progress14 = progress14;
    }

    public BigDecimal getProgress15() {
        return progress15;
    }

    public void setProgress15(BigDecimal progress15) {
        this.progress15 = progress15;
    }

    public BigDecimal getProgress16() {
        return progress16;
    }

    public void setProgress16(BigDecimal progress16) {
        this.progress16 = progress16;
    }

    public BigDecimal getProgress17() {
        return progress17;
    }

    public void setProgress17(BigDecimal progress17) {
        this.progress17 = progress17;
    }

    public BigDecimal getProgress18() {
        return progress18;
    }

    public void setProgress18(BigDecimal progress18) {
        this.progress18 = progress18;
    }

    public BigDecimal getProgress19() {
        return progress19;
    }

    public void setProgress19(BigDecimal progress19) {
        this.progress19 = progress19;
    }

    public BigDecimal getProgress20() {
        return progress20;
    }

    public void setProgress20(BigDecimal progress20) {
        this.progress20 = progress20;
    }

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
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
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
        return "SubitemsPlanning{" +
                "id=" + id +
                ", prjId=" + prjId +
                ", itemId=" + itemId +
                ", profession='" + profession + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectNo='" + projectNo + '\'' +
                ", phaseNo='" + phaseNo + '\'' +
                ", itemNo='" + itemNo + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemWeight=" + itemWeight +
                ", proResp='" + proResp + '\'' +
                ", eim='" + eim + '\'' +
                ", overallPlan='" + overallPlan + '\'' +
                ", checker='" + checker + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", pipeReview='" + pipeReview + '\'' +
                ", approval='" + approval + '\'' +
                ", designGuidance='" + designGuidance + '\'' +
                ", design1='" + design1 + '\'' +
                ", design2='" + design2 + '\'' +
                ", design3='" + design3 + '\'' +
                ", design4='" + design4 + '\'' +
                ", design5='" + design5 + '\'' +
                ", design6='" + design6 + '\'' +
                ", design7='" + design7 + '\'' +
                ", design8='" + design8 + '\'' +
                ", design9='" + design9 + '\'' +
                ", design10='" + design10 + '\'' +
                ", design11='" + design11 + '\'' +
                ", design12='" + design12 + '\'' +
                ", design13='" + design13 + '\'' +
                ", design14='" + design14 + '\'' +
                ", design15='" + design15 + '\'' +
                ", design16='" + design16 + '\'' +
                ", design17='" + design17 + '\'' +
                ", design18='" + design18 + '\'' +
                ", design19='" + design19 + '\'' +
                ", design20='" + design20 + '\'' +
                ", procurementSupport='" + procurementSupport + '\'' +
                ", consultant='" + consultant + '\'' +
                ", proRespPct=" + proRespPct +
                ", eimPct=" + eimPct +
                ", overallPlanPct=" + overallPlanPct +
                ", checkerPct=" + checkerPct +
                ", reviewerPct=" + reviewerPct +
                ", pipeReviewPct=" + pipeReviewPct +
                ", approvalPct=" + approvalPct +
                ", designGuidancePct=" + designGuidancePct +
                ", design1Pct=" + design1Pct +
                ", design2Pct=" + design2Pct +
                ", design3Pct=" + design3Pct +
                ", design4Pct=" + design4Pct +
                ", design5Pct=" + design5Pct +
                ", design6Pct=" + design6Pct +
                ", design7Pct=" + design7Pct +
                ", design8Pct=" + design8Pct +
                ", design9Pct=" + design9Pct +
                ", design10Pct=" + design10Pct +
                ", design11Pct=" + design11Pct +
                ", design12Pct=" + design12Pct +
                ", design13Pct=" + design13Pct +
                ", design14Pct=" + design14Pct +
                ", design15Pct=" + design15Pct +
                ", design16Pct=" + design16Pct +
                ", design17Pct=" + design17Pct +
                ", design18Pct=" + design18Pct +
                ", design19Pct=" + design19Pct +
                ", design20Pct=" + design20Pct +
                ", procurementSupportPct=" + procurementSupportPct +
                ", consultantPct=" + consultantPct +
                ", final1Pct=" + final1Pct +
                ", final2Pct=" + final2Pct +
                ", final3Pct=" + final3Pct +
                ", final4Pct=" + final4Pct +
                ", final5Pct=" + final5Pct +
                ", final6Pct=" + final6Pct +
                ", final7Pct=" + final7Pct +
                ", final8Pct=" + final8Pct +
                ", final9Pct=" + final9Pct +
                ", final10Pct=" + final10Pct +
                ", final11Pct=" + final11Pct +
                ", final12Pct=" + final12Pct +
                ", final13Pct=" + final13Pct +
                ", final14Pct=" + final14Pct +
                ", final15Pct=" + final15Pct +
                ", final16Pct=" + final16Pct +
                ", final17Pct=" + final17Pct +
                ", final18Pct=" + final18Pct +
                ", final19Pct=" + final19Pct +
                ", final20Pct=" + final20Pct +
                ", progress1=" + progress1 +
                ", progress2=" + progress2 +
                ", progress3=" + progress3 +
                ", progress4=" + progress4 +
                ", progress5=" + progress5 +
                ", progress6=" + progress6 +
                ", progress7=" + progress7 +
                ", progress8=" + progress8 +
                ", progress9=" + progress9 +
                ", progress10=" + progress10 +
                ", progress11=" + progress11 +
                ", progress12=" + progress12 +
                ", progress13=" + progress13 +
                ", progress14=" + progress14 +
                ", progress15=" + progress15 +
                ", progress16=" + progress16 +
                ", progress17=" + progress17 +
                ", progress18=" + progress18 +
                ", progress19=" + progress19 +
                ", progress20=" + progress20 +
                ", def1='" + def1 + '\'' +
                ", def2='" + def2 + '\'' +
                ", def3='" + def3 + '\'' +
                ", def4='" + def4 + '\'' +
                ", def5='" + def5 + '\'' +
                ", def6='" + def6 + '\'' +
                ", def7='" + def7 + '\'' +
                ", def8='" + def8 + '\'' +
                ", def9='" + def9 + '\'' +
                ", def10='" + def10 + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
