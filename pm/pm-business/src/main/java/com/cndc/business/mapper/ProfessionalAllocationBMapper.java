package com.cndc.business.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cndc.business.domain.ProfessionalAllocation;
import com.cndc.business.domain.ProfessionalAllocationB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 专业负责人策划子表Mapper接口
 *
 * @author laizhiying
 * @date 2025-02-24
 */
@Mapper
public interface ProfessionalAllocationBMapper {
    /**
     * 查询专业负责人策划子表
     *
     * @param id 专业负责人策划子表主键
     * @return 专业负责人策划子表
     */
    public ProfessionalAllocationB selectProfessionalAllocationBById(Long id);
    // 新增查询方法
    List<ProfessionalAllocationB> selectByPrjIdAndHeadId(@Param("prjId") Long prjId, @Param("profession") String profession);

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

    /**
     * 修改专业负责人策划子表
     *
     * @param professionalAllocationB 专业负责人策划子表
     * @return 结果
     */
    public int updateProfessionalAllocationB(ProfessionalAllocationB professionalAllocationB);

    /**
     * 删除专业负责人策划子表
     *
     * @param id 专业负责人策划子表主键
     * @return 结果
     */
    public int deleteProfessionalAllocationBById(Long id);

    /**
     * 批量删除专业负责人策划子表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProfessionalAllocationBByIds(Long[] ids);

    List<ProfessionalAllocationB> selectBody(ProfessionalAllocation professionalAllocation);

    /**
     * 查询专业负责人的名字列表
     *
     * @param query 查询条件
     * @return 名字列表
     */
    List<String> selectProRespNames(@Param("query") Map<String, Object> query);
    /**
     * 根据 head_id 求和所有 final_pct 字段
     */
    BigDecimal sumFinalPctByHeadId(@Param("headId") Long headId);

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
