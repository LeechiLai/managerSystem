package com.cndc.business.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cndc.business.domain.ProfessionalAllocation;
import com.cndc.business.mapper.DesignRatioMapper;
import com.cndc.business.mapper.ProfessionalAllocationMapper;
import com.cndc.business.mapper.ProjectDetailsMapper;
import com.cndc.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.ProfessionalAllocationBMapper;
import com.cndc.business.domain.ProfessionalAllocationB;
import com.cndc.business.service.IProfessionalAllocationBService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 专业负责人策划子表Service业务层处理
 *
 * @author laizhiying
 * @date 2025-02-24
 */
@Service
public class ProfessionalAllocationBServiceImpl implements IProfessionalAllocationBService {
    @Autowired
    private ProfessionalAllocationMapper professionalAllocationMapper;

    @Autowired
    private ProfessionalAllocationBMapper professionalAllocationBMapper;

    @Autowired
    private DesignRatioMapper designRatioMapper;
    // 在类的成员变量中添加 projectDetailsMapper 的声明和注入
    @Autowired
    private ProjectDetailsMapper projectDetailsMapper;

    /**
     * 查询专业负责人策划子表
     *
     * @param id 专业负责人策划子表主键
     * @return 专业负责人策划子表
     */
    @Override
    public ProfessionalAllocationB selectProfessionalAllocationBById(Long id) {
        ProfessionalAllocationB a=professionalAllocationBMapper.selectProfessionalAllocationBById(id);
        return a;
    }

    /**
     * 查询专业负责人策划子表列表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 专业负责人策划子表
     */
    @Override
    public List<ProfessionalAllocationB> selectProfessionalAllocationBList(ProfessionalAllocationB professionalAllocationB) {
        return professionalAllocationBMapper.selectProfessionalAllocationBList(professionalAllocationB);
    }
    @Override
    public List<ProfessionalAllocationB> selectByPrjIdAndHeadId(Long prjId,  String profession) {
        return professionalAllocationBMapper.selectByPrjIdAndHeadId(prjId, profession);
    }
    /**
     * 新增专业负责人策划子表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertProfessionalAllocationB(ProfessionalAllocationB professionalAllocationB) {
        professionalAllocationB.setCreateTime(DateUtils.getNowDate());
        return professionalAllocationBMapper.insertProfessionalAllocationB(professionalAllocationB);
    }

    /**
     * 修改专业负责人策划子表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 结果
     */
    @Override
    public int updateProfessionalAllocationB(ProfessionalAllocationB professionalAllocationB) {
        professionalAllocationB.setUpdateTime(DateUtils.getNowDate());
        return professionalAllocationBMapper.updateProfessionalAllocationB(professionalAllocationB);
    }

    /**
     * 批量删除专业负责人策划子表
     *
     * @param ids 需要删除的专业负责人策划子表主键
     * @return 结果
     */
    @Override
    public int deleteProfessionalAllocationBByIds(Long[] ids) {
        return professionalAllocationBMapper.deleteProfessionalAllocationBByIds(ids);
    }

    /**
     * 删除专业负责人策划子表信息
     *
     * @param id 专业负责人策划子表主键
     * @return 结果
     */
    @Override
    public int deleteProfessionalAllocationBById(Long id) {
        return professionalAllocationBMapper.deleteProfessionalAllocationBById(id);
    }

    @Override
    public List<ProfessionalAllocationB> selectBody(ProfessionalAllocation professionalAllocation) {
        return professionalAllocationBMapper.selectBody(professionalAllocation);
    }

    @Override
    public List<String> getProRespNames(Map<String, Object> query) {
        return professionalAllocationBMapper.selectProRespNames(query);
    }

    /**
     * 更新所有专业的综合完成度
     */
    @Override
    public void updateProfessionCompletion() {
        // 获取所有专业负责人策划主表记录
        List<ProfessionalAllocation> professionalAllocations = professionalAllocationMapper.selectAll();
        long PrjId =-1;
        for (ProfessionalAllocation allocation : professionalAllocations) {
            Long headId = allocation.getId();
            String profession = allocation.getProfession();

            // 获取子表中对应的所有 final_pct 字段总和
            BigDecimal totalFinalPct = professionalAllocationBMapper.sumFinalPctByHeadId(headId);
            PrjId=allocation.getPrjId();
            // 更新 tb_design_ratio 表中对应专业的 def1 字段
            designRatioMapper.updateDef1ByPrjIdAndProfession(PrjId, profession, totalFinalPct);


        }

        // 获取 tb_design_ratio 表中对应 prj_id 和 profession 的 def1 总和
        BigDecimal totalDef1 = designRatioMapper.sumDef1ByPrjIdAndProfession(PrjId);

        // 更新 tb_project_details 表中对应 prj_id 的 def1 字段
        projectDetailsMapper.updateDef1ByPrjId(PrjId, totalDef1);

    }


    @Override
    public List<ProfessionalAllocationB> selectAssignedProjectItemsByHeadId(Long headId) {
        return professionalAllocationBMapper.selectAssignedProjectItemsByHeadId(headId);
    }

    @Override
    public void updateHeadIdByPrjId(Long id, Long prjId, Long headId) {
        professionalAllocationBMapper.updateHeadIdByPrjId(id,prjId, headId);
    }

    @Override
    public void batchUpdateHeadIdByPrjId(@Param("ids")List<Long> ids,  @Param("prjId")Long prjId,  @Param("headId")Long headId) {
        professionalAllocationBMapper.batchUpdateHeadIdByPrjId(ids, prjId, headId);
    }

    /**
     * 项目子项跳转专业策划
     * @Author laizhiying
     * @return
     **/
    @Override
    public List<ProfessionalAllocationB> getProjectItemsByPrjId(Long prjId,Long itemId) {
        return professionalAllocationBMapper.getProjectItemsByPrjId(prjId,itemId);
    }
}
