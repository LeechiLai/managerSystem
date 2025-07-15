package com.cndc.business.service.impl;

import java.util.List;

import com.cndc.common.annotation.DataScope;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.ProjectMapper;
import com.cndc.business.domain.Project;
import com.cndc.business.service.IProjectService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 项目信息Service业务层处理
 *
 * @author laizhiying
 * @date 2024-12-03
 */
@Service
public class ProjectServiceImpl implements IProjectService
{
    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private PrjOptStatusSyncService prjOptStatusSyncService;

    /**
     * 查询项目信息
     *
     * @param id 项目信息主键
     * @return 项目信息
     */
    @Override
    public Project selectProjectById(Long id)
    {
        return projectMapper.selectProjectById(id);
    }

    /**
     * 查询项目信息列表
     *
     * @param project 项目信息
     * @return 项目信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertProject(Project project) {
        int result = projectMapper.insertProject(project);
        /*if (result > 0) {
            prjOptStatusSyncService.syncPrjOptStatusByProjectId(project.getId());
        }*/
        return result;
    }

    /**
     * 修改项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateProject(Project project) {
        int result = projectMapper.updateProject(project);
        /*if (result > 0) {
            prjOptStatusSyncService.syncPrjOptStatusByProjectId(project.getId());
        }*/
        return result;
    }

    /**
     * 批量删除项目信息
     *
     * @param ids 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectByIds(Long[] ids)
    {
        return projectMapper.deleteProjectByIds(ids);
    }

    /**
     * 删除项目信息信息
     *
     * @param id 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long id)
    {
        return projectMapper.deleteProjectById(id);
    }

}
