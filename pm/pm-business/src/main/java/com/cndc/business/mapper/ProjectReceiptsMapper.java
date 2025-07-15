package com.cndc.business.mapper;

import java.util.List;

import com.cndc.business.domain.ProjectReceipts;
import org.apache.ibatis.annotations.Param;

/**
 * 项目收款记录Mapper接口
 *
 * @author laizhiying
 * @date 2025-04-08
 */
public interface ProjectReceiptsMapper {
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
     * 删除项目收款记录
     *
     * @param id 项目收款记录主键
     * @return 结果
     */
    public int deleteProjectReceiptsById(Long id);

    /**
     * 批量删除项目收款记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectReceiptsByIds(Long[] ids);

    /**
     * 根据项目id查询项目收款记录
     * @param prjId
     * @return
     */
    ProjectReceipts selectProjectReceiptsByPrjId(Long prjId);


    /**
     * 查询项目收款记录
     * @param receipts
     * @return
     */
    List<ProjectReceipts> selectReceiptedByPrjId(ProjectReceipts receipts);

    List<ProjectReceipts> getLatestReceiptsByProjectIds( @Param("projectIds") List<Long> projectIds);
}
