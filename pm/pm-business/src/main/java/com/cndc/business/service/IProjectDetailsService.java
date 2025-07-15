package com.cndc.business.service;

import java.util.List;

import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.domain.vo.ProjectDetailsVo;

/**
 * 项目负责人策划Service接口
 *
 * @author laizhiying
 * @date 2024-12-18
 */
public interface IProjectDetailsService {
    /**
     * 查询项目负责人策划
     *
     * @param id 项目负责人策划主键
     * @return 项目负责人策划
     */
    public ProjectDetails selectProjectDetailsById(Long id);

    /**
     * 查询项目负责人策划列表
     *
     * @param projectDetails 项目负责人策划
     * @return 项目负责人策划集合
     */
    public List<ProjectDetails> selectProjectDetailsList(ProjectDetails projectDetails);

    /**
     * 新增项目负责人策划
     *
     * @param projectDetails 项目负责人策划
     * @return 结果
     */
    public int insertProjectDetails(ProjectDetails projectDetails);

    /**
     * 修改项目负责人策划
     *
     * @param projectDetails 项目负责人策划
     * @return 结果
     */
    public int updateProjectDetails(ProjectDetails projectDetails);

    /**
     * 批量删除项目负责人策划
     *
     * @param ids 需要删除的项目负责人策划主键集合
     * @return 结果
     */
    public int deleteProjectDetailsByIds(Long[] ids);

    /**
     * 删除项目负责人策划信息
     *
     * @param id 项目负责人策划主键
     * @return 结果
     */
    public int deleteProjectDetailsById(Long id);

    /**
     * 查询项目详情列表VO
     * @param projectDetails
     * @return
     */
    public List<ProjectDetailsVo> selectProjectDetailsVoList(ProjectDetails projectDetails);

    /**
     * 删除项目子项
     * @param ids
     * @return
     */
    int deleteProjectSubitemsByIds(Long[] ids);

}
