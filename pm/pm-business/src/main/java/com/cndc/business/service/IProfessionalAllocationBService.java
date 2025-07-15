package com.cndc.business.service;

import java.util.List;
import java.util.Map;

import com.cndc.business.domain.ProfessionalAllocation;
import com.cndc.business.domain.ProfessionalAllocationB;

/**
 * 专业负责人策划子表Service接口
 *
 * @author laizhiying
 * @date 2025-02-24
 */
public interface IProfessionalAllocationBService {
    /**
     * 查询专业负责人策划子表
     *
     * @param id 专业负责人策划子表主键
     * @return 专业负责人策划子表
     */
    public ProfessionalAllocationB selectProfessionalAllocationBById(Long id);

    /**
     * 查询专业负责人策划子表列表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 专业负责人策划子表集合
     */
    public List<ProfessionalAllocationB> selectProfessionalAllocationBList(ProfessionalAllocationB professionalAllocationB);

    /**
     * 新增专业负责人策划子表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 结果
     */
    public int insertProfessionalAllocationB(ProfessionalAllocationB professionalAllocationB);

        // 新增查询方法
    List<ProfessionalAllocationB> selectByPrjIdAndHeadId(Long prjId,  String profession);

    /**
     * 修改专业负责人策划子表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 结果
     */
    public int updateProfessionalAllocationB(ProfessionalAllocationB professionalAllocationB);

    /**
     * 批量删除专业负责人策划子表
     *
     * @param ids 需要删除的专业负责人策划子表主键集合
     * @return 结果
     */
    public int deleteProfessionalAllocationBByIds(Long[] ids);

    /**
     * 删除专业负责人策划子表信息
     *
     * @param id 专业负责人策划子表主键
     * @return 结果
     */
    public int deleteProfessionalAllocationBById(Long id);

    List<ProfessionalAllocationB> selectBody(ProfessionalAllocation professionalAllocation);

    /**
     * 获取专业负责人的名字列表
     *
     * @param query 查询条件
     * @return 名字列表
     */
    List<String> getProRespNames(Map<String, Object> query);
    /**
     * 更新所有专业的综合完成度
     */
    void updateProfessionCompletion();


    List<ProfessionalAllocationB> selectAssignedProjectItemsByHeadId(Long headId);

    void updateHeadIdByPrjId(Long id, Long prjId, Long headId);

    void batchUpdateHeadIdByPrjId(List<Long> ids, Long prjId, Long headId);

    /**
     * 项目子项跳转专业策划
     * @Author laizhiying
     * @return
     **/
    List<ProfessionalAllocationB> getProjectItemsByPrjId(Long prjId,Long itemId);
}
