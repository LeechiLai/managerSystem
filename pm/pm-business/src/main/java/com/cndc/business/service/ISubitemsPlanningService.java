package com.cndc.business.service;

import java.util.List;
import com.cndc.business.domain.SubitemsPlanning;

/**
 * 专业负责人策划Service接口
 * 
 * @author laizhiying
 * @date 2024-12-19
 */
public interface ISubitemsPlanningService 
{
    /**
     * 查询专业负责人策划
     * 
     * @param id 专业负责人策划主键
     * @return 专业负责人策划
     */
    public SubitemsPlanning selectSubitemsPlanningById(Long id);

    /**
     * 查询专业负责人策划列表
     * 
     * @param subitemsPlanning 专业负责人策划
     * @return 专业负责人策划集合
     */
    public List<SubitemsPlanning> selectSubitemsPlanningList(SubitemsPlanning subitemsPlanning);

    /**
     * 新增专业负责人策划
     * 
     * @param subitemsPlanning 专业负责人策划
     * @return 结果
     */
    public int insertSubitemsPlanning(SubitemsPlanning subitemsPlanning);

    /**
     * 修改专业负责人策划
     * 
     * @param subitemsPlanning 专业负责人策划
     * @return 结果
     */
    public int updateSubitemsPlanning(SubitemsPlanning subitemsPlanning);

    /**
     * 批量删除专业负责人策划
     * 
     * @param ids 需要删除的专业负责人策划主键集合
     * @return 结果
     */
    public int deleteSubitemsPlanningByIds(Long[] ids);

    /**
     * 删除专业负责人策划信息
     * 
     * @param id 专业负责人策划主键
     * @return 结果
     */
    public int deleteSubitemsPlanningById(Long id);
}
