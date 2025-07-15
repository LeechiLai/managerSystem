package com.cndc.business.service.impl;

import java.util.List;
import com.cndc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.SubitemsPlanningMapper;
import com.cndc.business.domain.SubitemsPlanning;
import com.cndc.business.service.ISubitemsPlanningService;

import javax.annotation.Resource;

/**
 * 专业负责人策划Service业务层处理
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@Service
public class SubitemsPlanningServiceImpl implements ISubitemsPlanningService
{
    @Resource
    private SubitemsPlanningMapper subitemsPlanningMapper;

    /**
     * 查询专业负责人策划
     *
     * @param id 专业负责人策划主键
     * @return 专业负责人策划
     */
    @Override
    public SubitemsPlanning selectSubitemsPlanningById(Long id)
    {
        return subitemsPlanningMapper.selectSubitemsPlanningById(id);
    }

    /**
     * 查询专业负责人策划列表
     *
     * @param subitemsPlanning 专业负责人策划
     * @return 专业负责人策划
     */
    @Override
    public List<SubitemsPlanning> selectSubitemsPlanningList(SubitemsPlanning subitemsPlanning)
    {
        // 修改SQL模式
        subitemsPlanningMapper.modifySqlMode();

        // 执行更新操作
        subitemsPlanningMapper.updateSubitemsPlanningWithDesignRatio();
        return subitemsPlanningMapper.selectSubitemsPlanningList(subitemsPlanning);
    }

    /**
     * 新增专业负责人策划
     *
     * @param subitemsPlanning 专业负责人策划
     * @return 结果
     */
    @Override
    public int insertSubitemsPlanning(SubitemsPlanning subitemsPlanning)
    {
        //subitemsPlanning.setCreateTime(DateUtils.getNowDate());
        return subitemsPlanningMapper.insertSubitemsPlanning(subitemsPlanning);
    }

    /**
     * 修改专业负责人策划
     *
     * @param subitemsPlanning 专业负责人策划
     * @return 结果
     */
    @Override
    public int updateSubitemsPlanning(SubitemsPlanning subitemsPlanning)
    {
        //subitemsPlanning.setUpdateTime(DateUtils.getNowDate());
        return subitemsPlanningMapper.updateSubitemsPlanning(subitemsPlanning);
    }

    /**
     * 批量删除专业负责人策划
     *
     * @param ids 需要删除的专业负责人策划主键
     * @return 结果
     */
    @Override
    public int deleteSubitemsPlanningByIds(Long[] ids)
    {
        return subitemsPlanningMapper.deleteSubitemsPlanningByIds(ids);
    }

    /**
     * 删除专业负责人策划信息
     *
     * @param id 专业负责人策划主键
     * @return 结果
     */
    @Override
    public int deleteSubitemsPlanningById(Long id)
    {
        return subitemsPlanningMapper.deleteSubitemsPlanningById(id);
    }
}
