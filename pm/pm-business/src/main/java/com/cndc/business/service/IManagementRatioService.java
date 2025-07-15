package com.cndc.business.service;

import java.util.List;
import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.domain.vo.ProjectDetailsVo;

/**
 * 管理组比例分配Service接口
 *
 * @author laizhiying
 * @date 2024-12-19
 */
public interface IManagementRatioService
{

    /**
     * 查询管理组比例分配
     *
     * @param id 管理组比例分配主键
     * @return 管理组比例分配
     */
    public ManagementRatio selectManagementRatioById(Long id);

    /**
     * 查询管理组比例分配列表
     *
     * @param managementRatio 管理组比例分配
     * @return 管理组比例分配集合
     */
    public List<ManagementRatio> selectManagementRatioList(ManagementRatio managementRatio);

    /**
     * 新增管理组比例分配
     *
     * @param managementRatio 管理组比例分配
     * @return 结果
     */
    public int insertManagementRatio(ManagementRatio managementRatio);

    /**
     * 修改管理组比例分配
     *
     * @param managementRatio 管理组比例分配
     * @return 结果
     */
    public int updateManagementRatio(ManagementRatio managementRatio);

    /**
     * 批量删除管理组比例分配
     *
     * @param ids 需要删除的管理组比例分配主键集合
     * @return 结果
     */
    public int deleteManagementRatioByIds(Long[] ids);

    /**
     * 删除管理组比例分配信息
     *
     * @param id 管理组比例分配主键
     * @return 结果
     */
    public int deleteManagementRatioById(Long id);



    /**
     * 查询已分配的管理组比例分配列表
     * @param managementRatio
     * @return
     */
    List<ManagementRatio> selectAllocatedList(ManagementRatio managementRatio);

    String getDef2ByPrjId(Long prjId);

}



