package com.cndc.business.mapper;

import java.util.List;
import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.domain.vo.ProjectDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 管理组比例分配Mapper接口
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@Mapper
public interface ManagementRatioMapper
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
    public int updateDef1ByPrjId(@Param("prjId") Long prjId, @Param("def1") String def1);
    /**
     * 删除管理组比例分配
     *
     * @param id 管理组比例分配主键
     * @return 结果
     */
    public int deleteManagementRatioById(Long id);

    /**
     * 批量删除管理组比例分配
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManagementRatioByIds(Long[] ids);

    /**
     * 查询已分配的管理组比例分配列表
     * @param managementRatio
     * @return
     */
    List<ManagementRatio> selectAllocatedList(ManagementRatio managementRatio);

    String selectDef2ByPrjId(@Param("prjId") Long prjId);
}
