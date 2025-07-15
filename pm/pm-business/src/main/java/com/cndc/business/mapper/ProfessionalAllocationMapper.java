package com.cndc.business.mapper;

import java.util.List;
import com.cndc.business.domain.ProfessionalAllocation;
import com.cndc.business.domain.ProfessionalAllocationB;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专业负责人策划Mapper接口
 *
 * @author laizhiying
 * @date 2025-02-17
 */
@Mapper
public interface ProfessionalAllocationMapper
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
     * 删除专业负责人策划
     *
     * @param id 专业负责人策划主键
     * @return 结果
     */
    public int deleteProfessionalAllocationById(Long id);

    /**
     * 批量删除专业负责人策划
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProfessionalAllocationByIds(Long[] ids);

    /**
     * 批量删除专业负责人策划子
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProfessionalAllocationBByHeadIds(Long[] ids);

    /**
     * 批量新增专业负责人策划子
     *
     * @param professionalAllocationBList 专业负责人策划子列表
     * @return 结果
     */
    public int batchProfessionalAllocationB(List<ProfessionalAllocationB> professionalAllocationBList);


    /**
     * 通过专业负责人策划主键删除专业负责人策划子信息
     *
     * @param id 专业负责人策划ID
     * @return 结果
     */
    public int deleteProfessionalAllocationBByHeadId(Long id);
    /**
     * 查询所有专业负责人策划
     *
     * @return 专业负责人策划列表
     */
    public List<ProfessionalAllocation> selectAll();

    void updateProfessionalAllocationBHeadIdByItemId(Long headId, Long itemId);

    /**
     * @Author laizhiying
     * 异步查询【专业】字段
     **/
    String getProfessionByHeadId(Long headId);

    /**
     * 更新专业字段
     * @Author laizhiying
     **/
    int updateProfessionById(Long headId, String profession);
}
