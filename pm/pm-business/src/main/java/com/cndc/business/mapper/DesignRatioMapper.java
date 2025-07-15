package com.cndc.business.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.cndc.business.domain.DesignRatio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 设计组比例分配Mapper接口
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@Mapper

public interface DesignRatioMapper
{
    /**
     * 查询设计组比例分配中的 proj_pct 字段
     */
    BigDecimal selectProjPct(@Param("prjId") Long prjId,
                             @Param("profession") String profession);
    /**
     * 查询设计组比例分配
     *
     * @param id 设计组比例分配主键
     * @return 设计组比例分配
     */
    public DesignRatio selectDesignRatioById(Long id);

    /**
     * 查询设计组比例分配列表
     *
     * @param designRatio 设计组比例分配
     * @return 设计组比例分配集合
     */
    public List<DesignRatio> selectDesignRatioList(DesignRatio designRatio);
    void setSqlMode();
    void updateDesignRatioPct();
    /**
     * 新增设计组比例分配
     *
     * @param designRatio 设计组比例分配
     * @return 结果
     */
    public int insertDesignRatio(DesignRatio designRatio);

    /**
     * 修改设计组比例分配
     *
     * @param designRatio 设计组比例分配
     * @return 结果
     */
    public int updateDesignRatio(DesignRatio designRatio);
    public int  updateManagementRatioDef2(long prjId,int projectDesignPct);
    /**
     * 删除设计组比例分配
     *
     * @param id 设计组比例分配主键
     * @return 结果
     */
    public int deleteDesignRatioById(Long id);

    /**
     * 批量删除设计组比例分配
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDesignRatioByIds(Long[] ids);

    /**
     * 查询已分配的设计比例数据
     * @param designRatio
     * @return
     */
    List<DesignRatio> selectAllocatedList(DesignRatio designRatio);
    /**
     * 根据 prj_id 和 profession 更新 def1 字段
     */
    int updateDef1ByPrjIdAndProfession(@Param("prjId") Long prjId,
                                       @Param("profession") String profession,
                                       @Param("def1") BigDecimal def1);
    /**
     * 根据 prj_id 和 profession 查询 def1 的总和
     */
    BigDecimal sumDef1ByPrjIdAndProfession(@Param("prjId") Long prjId);

}
