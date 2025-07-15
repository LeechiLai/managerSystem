package com.cndc.business.service.impl;

import java.util.List;

import com.cndc.business.domain.vo.ProjectDetailsVo;
import com.cndc.common.annotation.DataScope;
import com.cndc.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.cndc.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.cndc.business.domain.ProjectSubitems;
import com.cndc.business.mapper.ProjectDetailsMapper;
import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.service.IProjectDetailsService;

import javax.annotation.Resource;

/**
 * 项目负责人策划Service业务层处理
 *
 * @author laizhiying
 * @date 2024-12-18
 */
@Service
public class ProjectDetailsServiceImpl implements IProjectDetailsService {
    @Resource
    private ProjectDetailsMapper projectDetailsMapper;

    @Resource
    private PrjOptStatusSyncService prjOptStatusSyncService;

    /**
     * 查询项目负责人策划
     *
     * @param id 项目负责人策划主键
     * @return 项目负责人策划
     */
    @Override
    public ProjectDetails selectProjectDetailsById(Long id) {
        return projectDetailsMapper.selectProjectDetailsById(id);
    }

    /**
     * 查询项目负责人策划列表
     *
     * @param projectDetails 项目负责人策划
     * @return 项目负责人策划
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ProjectDetails> selectProjectDetailsList(ProjectDetails projectDetails) {
        return projectDetailsMapper.selectProjectDetailsList(projectDetails);
    }

    /**
     * 新增项目负责人策划
     *
     * @param projectDetails 项目负责人策划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertProjectDetails(ProjectDetails projectDetails) {
        int rows = projectDetailsMapper.insertProjectDetails(projectDetails);
        insertProjectSubitems(projectDetails);
        return rows;
    }

    /**
     * 修改项目负责人策划
     *
     * @param projectDetails 项目负责人策划
     * @return 结果
     */
    @Transactional
    @Override
    public int updateProjectDetails(ProjectDetails projectDetails) {
        //projectDetails.setUpdateTime(DateUtils.getNowDate());
        projectDetailsMapper.deleteProjectSubitemsByDetailId(projectDetails.getId());
        insertProjectSubitems(projectDetails);
        int result = projectDetailsMapper.updateProjectDetails(projectDetails);
        /*if (result > 0) {
            prjOptStatusSyncService.syncPrjOptStatusByProjectId(projectDetails.getId());
        }*/
        return result;
    }

    /**
     * 批量删除项目负责人策划
     *
     * @param ids 需要删除的项目负责人策划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteProjectDetailsByIds(Long[] ids) {
        projectDetailsMapper.deleteProjectSubitemsByDetailIds(ids);
        return projectDetailsMapper.deleteProjectDetailsByIds(ids);
    }


    /**
     * 删除项目负责人策划信息
     *
     * @param id 项目负责人策划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteProjectDetailsById(Long id) {
        projectDetailsMapper.deleteProjectSubitemsByDetailId(id);
        return projectDetailsMapper.deleteProjectDetailsById(id);
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ProjectDetailsVo> selectProjectDetailsVoList(ProjectDetails projectDetails) {
        return projectDetailsMapper.selectProjectDetailsVoList(projectDetails);
    }

    /**
     * 新增项目子项信息
     *
     * @param projectDetails 项目负责人策划对象
     */
    public void insertProjectSubitems(ProjectDetails projectDetails) {
        List<ProjectSubitems> projectSubitemsList = projectDetails.getProjectSubitemsList();
        Long id = projectDetails.getId();
        if (StringUtils.isNotNull(projectSubitemsList)) {
            List<ProjectSubitems> list = new ArrayList<ProjectSubitems>();
            for (ProjectSubitems projectSubitems : projectSubitemsList) {
                projectSubitems.setDetailId(id);
                list.add(projectSubitems);
            }
            if (list.size() > 0) {
                projectDetailsMapper.batchProjectSubitems(list);
            }
        }
    }

    /**
     * 删除项目子项
     * @param ids
     * @return
     */
    @Override
    public int deleteProjectSubitemsByIds(Long[] ids) {
        return projectDetailsMapper.deleteProjectSubitemsByIds(ids);
    }



}
