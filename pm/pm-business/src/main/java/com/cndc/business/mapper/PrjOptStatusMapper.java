package com.cndc.business.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cndc.business.domain.PrjOptStatus;
import org.apache.ibatis.annotations.Param;

/**
 * 项目运行情况Mapper接口
 *
 * @author laizhiying
 * @date 2025-04-07
 */
public interface PrjOptStatusMapper {
    /**
     * 查询项目运行情况
     *
     * @param id 项目运行情况主键
     * @return 项目运行情况
     */
    public PrjOptStatus selectPrjOptStatusById(Long id);
    int batchUpdateProjectData(@Param("list") List<PrjOptStatus> updateList);
    /**
     * 查询项目运行情况列表
     *
     * @param prjOptStatus 项目运行情况
     * @return 项目运行情况集合
     */
    public List<PrjOptStatus> selectPrjOptStatusList(PrjOptStatus prjOptStatus);


    public int updateDef1ByPrjId(@Param("prjId") Long prjId, @Param("def1") BigDecimal def1);

    /**
     * 新增项目运行情况
     *
     * @param prjOptStatus 项目运行情况
     * @return 结果
     */
    public int insertPrjOptStatus(PrjOptStatus prjOptStatus);

    /**
     * 修改项目运行情况
     *
     * @param prjOptStatus 项目运行情况
     * @return 结果
     */
    public int updatePrjOptStatus(PrjOptStatus prjOptStatus);

    /**
     * 删除项目运行情况
     *
     * @param id 项目运行情况主键
     * @return 结果
     */
    public int deletePrjOptStatusById(Long id);

    /**
     * 批量删除项目运行情况
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePrjOptStatusByIds(Long[] ids);
    int updateByPrjIdSelective(PrjOptStatus prjOptStatus);
}
