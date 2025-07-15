package com.cndc.business.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.domain.ProjectSubitems;
import com.cndc.business.domain.vo.ProjectDetailsVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 项目负责人策划Mapper接口
 *
 * @author laizhiying
 * @date 2024-12-18
 */
@Mapper
public interface ProjectDetailsMapper {
    /**
     * 查询项目负责人策划
     *
     * @param id 项目负责人策划主键
     * @return 项目负责人策划
     */
    public ProjectDetails selectProjectDetailsById(Long id);
    /**
     * 根据项目ID集合批量查询 tb_project_details.def1 字段
     * @param prjIds 项目ID集合
     * @return 项目ID与 def1 的映射关系（key: prjId, value: def1）
     */
    @MapKey("prj_id") // 指定 Map 的 key 字段
    List<Map<String, Object>> selectDef1ByPrjIds(@Param("prjIds") List<Long> prjIds);
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
     * 删除项目负责人策划
     *
     * @param id 项目负责人策划主键
     * @return 结果
     */
    public int deleteProjectDetailsById(Long id);

    /**
     * 批量删除项目负责人策划
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectDetailsByIds(Long[] ids);

    /**
     * 批量删除项目子项
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectSubitemsByDetailIds(Long[] ids);


    /**
     * 批量新增项目子项
     *
     * @param projectSubitemsList 项目子项列表
     * @return 结果
     */
    public int batchProjectSubitems(List<ProjectSubitems> projectSubitemsList);


    /**
     * 通过项目负责人策划主键删除项目子项信息
     *
     * @param id 项目负责人策划ID
     * @return 结果
     */
    public int deleteProjectSubitemsByDetailId(Long id);


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
    public int deleteProjectSubitemsByIds(Long[] ids);
    /**
     * 根据 prj_id 更新 def1 字段
     */
    int updateDef1ByPrjId(@Param("prjId") Long prjId, @Param("def1") BigDecimal def1);

    String selectDef1ByPrjId(Long prjId);
}
