package com.cndc.business.service.impl;

import java.util.List;

import com.cndc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.ProjectReceiptsMapper;
import com.cndc.business.domain.ProjectReceipts;
import com.cndc.business.service.IProjectReceiptsService;

import javax.annotation.Resource;

/**
 * 项目收款记录Service业务层处理
 *
 * @author laizhiying
 * @date 2025-04-08
 */
@Service
public class ProjectReceiptsServiceImpl implements IProjectReceiptsService {
    @Resource
    private ProjectReceiptsMapper projectReceiptsMapper;

    /**
     * 查询项目收款记录
     *
     * @param id 项目收款记录主键
     * @return 项目收款记录
     */
    @Override
    public ProjectReceipts selectProjectReceiptsById(Long id) {
        return projectReceiptsMapper.selectProjectReceiptsById(id);
    }

    /**
     * 查询项目收款记录列表
     *
     * @param projectReceipts 项目收款记录
     * @return 项目收款记录
     */
    @Override
    public List<ProjectReceipts> selectProjectReceiptsList(ProjectReceipts projectReceipts) {
        return projectReceiptsMapper.selectProjectReceiptsList(projectReceipts);
    }

    /**
     * 新增项目收款记录
     *
     * @param projectReceipts 项目收款记录
     * @return 结果
     */
    @Override
    public int insertProjectReceipts(ProjectReceipts projectReceipts) {
        projectReceipts.setCreateTime(DateUtils.getNowDate());
        return projectReceiptsMapper.insertProjectReceipts(projectReceipts);
    }

    /**
     * 修改项目收款记录
     *
     * @param projectReceipts 项目收款记录
     * @return 结果
     */
    @Override
    public int updateProjectReceipts(ProjectReceipts projectReceipts) {
        projectReceipts.setUpdateTime(DateUtils.getNowDate());
        return projectReceiptsMapper.updateProjectReceipts(projectReceipts);
    }

    /**
     * 批量删除项目收款记录
     *
     * @param ids 需要删除的项目收款记录主键
     * @return 结果
     */
    @Override
    public int deleteProjectReceiptsByIds(Long[] ids) {
        return projectReceiptsMapper.deleteProjectReceiptsByIds(ids);
    }

    /**
     * 删除项目收款记录信息
     *
     * @param id 项目收款记录主键
     * @return 结果
     */
    @Override
    public int deleteProjectReceiptsById(Long id) {
        return projectReceiptsMapper.deleteProjectReceiptsById(id);
    }

    /**
     * 根据项目id查询项目收款记录
     *
     * @param prjId 项目id
     * @return 项目收款记录
     */
    @Override
    public ProjectReceipts selectProjectReceiptsByPrjId(Long prjId) {
        return projectReceiptsMapper.selectProjectReceiptsByPrjId(prjId);
    }

    /**
     * 查询已收款记录
     * @param receipts
     * @return
     */
    @Override
    public List<ProjectReceipts> selectReceipted(ProjectReceipts receipts) {
        return projectReceiptsMapper.selectReceiptedByPrjId(receipts);
    }

    /**
     * 批量查询项目收款记录
     * @param projectIds
     * @return
     */
    @Override
    public List<ProjectReceipts> getLatestReceiptsByProjectIds(List<Long> projectIds) {
        return projectReceiptsMapper.getLatestReceiptsByProjectIds(projectIds);
    }


}
