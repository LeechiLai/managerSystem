package com.cndc.business.service.impl;

import java.util.List;

import com.cndc.business.domain.BonusItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.BonusMapper;
import com.cndc.business.domain.Bonus;
import com.cndc.business.service.IBonusService;

import javax.annotation.Resource;

/**
 * 个人奖金Service业务层处理
 *
 * @author laizhiying
 * @date 2025-04-07
 */
@Service
public class BonusServiceImpl implements IBonusService {
    @Resource
    private BonusMapper tbBonusMapper;
    // BonusService.java



    @Override
    public List<BonusItemDTO> getBonusList(String name) {
        List<BonusItemDTO> a1=tbBonusMapper.getBonusList(name);
        return a1;
    }

    /**
     * 查询个人奖金
     *
     * @param id 个人奖金主键
     * @return 个人奖金
     */
    @Override
    public Bonus selectTbBonusById(Long id) {
        return tbBonusMapper.selectTbBonusById(id);
    }

    /**
     * 查询个人奖金列表
     *
     * @param tbBonus 个人奖金
     * @return 个人奖金
     */
    @Override
    public List<Bonus> selectTbBonusList(Bonus tbBonus) {
        return tbBonusMapper.selectTbBonusList(tbBonus);
    }

    /**
     * 新增个人奖金
     *
     * @param tbBonus 个人奖金
     * @return 结果
     */
    @Override
    public int insertTbBonus(Bonus tbBonus) {
        return tbBonusMapper.insertTbBonus(tbBonus);
    }

    /**
     * 修改个人奖金
     *
     * @param tbBonus 个人奖金
     * @return 结果
     */
    @Override
    public int updateTbBonus(Bonus tbBonus) {
        return tbBonusMapper.updateTbBonus(tbBonus);
    }

    /**
     * 批量删除个人奖金
     *
     * @param ids 需要删除的个人奖金主键
     * @return 结果
     */
    @Override
    public int deleteTbBonusByIds(Long[] ids) {
        return tbBonusMapper.deleteTbBonusByIds(ids);
    }

    /**
     * 删除个人奖金信息
     *
     * @param id 个人奖金主键
     * @return 结果
     */
    @Override
    public int deleteTbBonusById(Long id) {
        return tbBonusMapper.deleteTbBonusById(id);
    }
}
