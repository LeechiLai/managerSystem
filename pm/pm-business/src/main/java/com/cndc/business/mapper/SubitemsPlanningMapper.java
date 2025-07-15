package com.cndc.business.mapper;

import java.util.List;

import com.cndc.business.domain.DesignRatio;
import com.cndc.business.domain.SubitemsPlanning;

/**
 * 专业负责人策划Mapper接口
 *
 * @author laizhiying
 * @date 2024-12-19
 */
public interface SubitemsPlanningMapper
{
    /**
     * 查询专业负责人策划
     *
     * @param id 专业负责人策划主键
     * @return 专业负责人策划
     */
    public SubitemsPlanning selectSubitemsPlanningById(Long id);


       void modifySqlMode();

       void updateSubitemsPlanningWithDesignRatio();


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
     * 删除专业负责人策划
     *
     * @param id 专业负责人策划主键
     * @return 结果
     */
    public int deleteSubitemsPlanningById(Long id);

    /**
     * 批量删除专业负责人策划
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubitemsPlanningByIds(Long[] ids);

}
