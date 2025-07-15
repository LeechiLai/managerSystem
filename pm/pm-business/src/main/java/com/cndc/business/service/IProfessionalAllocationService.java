package com.cndc.business.service;

import java.util.List;
import com.cndc.business.domain.ProfessionalAllocation;

/**
 * 专业负责人策划Service接口
 *
 * @author laizhiying
 * @date 2025-02-17
 */
public interface IProfessionalAllocationService
{
    /**
     * 查询专业负责人策划
     *
     * @param id 专业负责人策划主键
     * @return 专业负责人策划
     */
    public ProfessionalAllocation selectProfessionalAllocationById(Long id);

    /**
     * 查询专业负责人策划列表
     *
     * @param professionalAllocation 专业负责人策划
     * @return 专业负责人策划集合
     */
    public List<ProfessionalAllocation> selectProfessionalAllocationList(ProfessionalAllocation professionalAllocation);

    /**
     * 新增专业负责人策划
     *
     * @param professionalAllocation 专业负责人策划
     * @return 结果
     */
    public int insertProfessionalAllocation(ProfessionalAllocation professionalAllocation);

    /**
     * 修改专业负责人策划
     *
     * @param professionalAllocation 专业负责人策划
     * @return 结果
     */
    public int updateProfessionalAllocation(ProfessionalAllocation professionalAllocation);

    /**
     * 批量删除专业负责人策划
     *
     * @param ids 需要删除的专业负责人策划主键集合
     * @return 结果
     */
    public int deleteProfessionalAllocationByIds(Long[] ids);

    /**
     * 删除专业负责人策划信息
     *
     * @param id 专业负责人策划主键
     * @return 结果
     */
    public int deleteProfessionalAllocationById(Long id);

    /**
     * 在同一个页面同时新增主表和子表
     */
    int addProfessionalAllocation(ProfessionalAllocation professionalAllocation,Long ItemId);

    /**
     * @Author laizhiying
     * 异步查询【专业】字段
     **/
    String getProfessionByHeadId(Long headId);

    /**
     * 更新专业字段
     * @Author laizhiying
     **/
    int updateProfession(Long headId, String profession);
}
