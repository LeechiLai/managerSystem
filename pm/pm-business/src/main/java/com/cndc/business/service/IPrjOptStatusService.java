package com.cndc.business.service;

import java.util.List;

import com.cndc.business.domain.PrjOptStatus;

/**
 * 项目运行情况Service接口
 *
 * @author laizhiying
 * @date 2025-04-07
 */
public interface IPrjOptStatusService {
    /**
     * 查询项目运行情况
     *
     * @param id 项目运行情况主键
     * @return 项目运行情况
     */
    public PrjOptStatus selectPrjOptStatusById(Long id);

    /**
     * 查询项目运行情况列表
     *
     * @param prjOptStatus 项目运行情况
     * @return 项目运行情况集合
     */
    public List<PrjOptStatus> selectPrjOptStatusList(PrjOptStatus prjOptStatus);

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
     * 批量删除项目运行情况
     *
     * @param ids 需要删除的项目运行情况主键集合
     * @return 结果
     */
    public int deletePrjOptStatusByIds(Long[] ids);

    /**
     * 删除项目运行情况信息
     *
     * @param id 项目运行情况主键
     * @return 结果
     */
    public int deletePrjOptStatusById(Long id);
}
