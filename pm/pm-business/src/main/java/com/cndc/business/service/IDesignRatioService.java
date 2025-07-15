package com.cndc.business.service;

import java.math.BigDecimal;
import java.util.List;
import com.cndc.business.domain.DesignRatio;

/**
 * 设计组比例分配Service接口
 *
 * @author laizhiying
 * @date 2024-12-19
 */
public interface IDesignRatioService
{
    /**
     * 查询设计组比例分配中的 proj_pct 字段
     */
    public BigDecimal getProjPct(Long prjId, String profession);

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

    /**
     * 新增设计组比例分配
     *
     * @param designRatio 设计组比例分配
     * @return 结果
     */
    public int insertDesignRatio(DesignRatio designRatio,int projectDesignPct);

    /**
     * 修改设计组比例分配
     *
     * @param designRatio 设计组比例分配
     * @return 结果
     */
    public int updateDesignRatio(DesignRatio designRatio,int projectDesignPct);

    /**
     * 批量删除设计组比例分配
     *
     * @param ids 需要删除的设计组比例分配主键集合
     * @return 结果
     */
    public int deleteDesignRatioByIds(Long[] ids);

    /**
     * 删除设计组比例分配信息
     *
     * @param id 设计组比例分配主键
     * @return 结果
     */
    public int deleteDesignRatioById(Long id);

    /**
     * 查询已分配的设计比例数据
     * @param designRatio
     * @return
     */
    List<DesignRatio> selectAllocatedList(DesignRatio designRatio);
}
