package com.cndc.business.service;

import java.util.List;

import com.cndc.business.domain.ProjectReceipts;

/**
 * 项目收款记录Service接口
 *
 * @author laizhiying
 * @date 2025-04-08
 */
public interface IProjectReceiptsService {
    /**
     * 查询项目收款记录
     *
     * @param id 项目收款记录主键
     * @return 项目收款记录
     */
    public ProjectReceipts selectProjectReceiptsById(Long id);

    /**
     * 查询项目收款记录列表
     *
     * @param projectReceipts 项目收款记录
     * @return 项目收款记录集合
     */
    public List<ProjectReceipts> selectProjectReceiptsList(ProjectReceipts projectReceipts);

    /**
     * 新增项目收款记录
     *
     * @param projectReceipts 项目收款记录
     * @return 结果
     */
    public int insertProjectReceipts(ProjectReceipts projectReceipts);

    /**
     * 修改项目收款记录
     *
     * @param projectReceipts 项目收款记录
     * @return 结果
     */
    public int updateProjectReceipts(ProjectReceipts projectReceipts);

    /**
     * 批量删除项目收款记录
     *
     * @param ids 需要删除的项目收款记录主键集合
     * @return 结果
     */
    public int deleteProjectReceiptsByIds(Long[] ids);

    /**
     * 删除项目收款记录信息
     *
     * @param id 项目收款记录主键
     * @return 结果
     */
    public int deleteProjectReceiptsById(Long id);

    /**
     * 根据项目id查询项目收款记录
     * @param prjId
     * @return
     */
    public ProjectReceipts selectProjectReceiptsByPrjId(Long prjId);

    /**
     * 查询项目收款记录
     * @param receipts
     * @return
     */
    List<ProjectReceipts> selectReceipted(ProjectReceipts receipts);

    /**
     * 根据项目id查询项目收款记录
     * @param projectIds
     * @return
     */
    List<ProjectReceipts> getLatestReceiptsByProjectIds(List<Long> projectIds);
}
