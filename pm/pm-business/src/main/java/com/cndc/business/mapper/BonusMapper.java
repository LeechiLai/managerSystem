package com.cndc.business.mapper;

import java.util.List;

import com.cndc.business.domain.Bonus;
import com.cndc.business.domain.BonusItemDTO;

/**
 * 个人奖金Mapper接口
 *
 * @author laizhiying
 * @date 2025-04-07
 */
public interface BonusMapper {

    public List<BonusItemDTO> getBonusList(String name);

    /**
     * 查询个人奖金
     *
     * @param id 个人奖金主键
     * @return 个人奖金
     */
    public Bonus selectTbBonusById(Long id);

    /**
     * 查询个人奖金列表
     *
     * @param tbBonus 个人奖金
     * @return 个人奖金集合
     */
    public List<Bonus> selectTbBonusList(Bonus tbBonus);

    /**
     * 新增个人奖金
     *
     * @param tbBonus 个人奖金
     * @return 结果
     */
    public int insertTbBonus(Bonus tbBonus);

    /**
     * 修改个人奖金
     *
     * @param tbBonus 个人奖金
     * @return 结果
     */
    public int updateTbBonus(Bonus tbBonus);

    /**
     * 删除个人奖金
     *
     * @param id 个人奖金主键
     * @return 结果
     */
    public int deleteTbBonusById(Long id);

    /**
     * 批量删除个人奖金
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbBonusByIds(Long[] ids);
}
