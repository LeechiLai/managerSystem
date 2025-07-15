package com.cndc.business.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.cndc.common.annotation.DataScope;
import com.cndc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.DesignRatioMapper;
import com.cndc.business.domain.DesignRatio;
import com.cndc.business.service.IDesignRatioService;

import javax.annotation.Resource;

/**
 * 设计组比例分配Service业务层处理
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@Service
public class DesignRatioServiceImpl implements IDesignRatioService
{
    @Resource
    private DesignRatioMapper designRatioMapper;
    @Override
    public BigDecimal getProjPct(Long prjId, String profession) {
        return designRatioMapper.selectProjPct(prjId, profession);
    }
    /**
     * 查询设计组比例分配
     *
     * @param id 设计组比例分配主键
     * @return 设计组比例分配
     */
    @Override
    public DesignRatio selectDesignRatioById(Long id)
    {
        return designRatioMapper.selectDesignRatioById(id);
    }

    /**
     * 查询设计组比例分配列表
     *
     * @param designRatio 设计组比例分配
     * @return 设计组比例分配
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<DesignRatio> selectDesignRatioList(DesignRatio designRatio)
    {
        designRatioMapper.setSqlMode(); // 先执行设置模式
       designRatioMapper.updateDesignRatioPct(); // 先执行更新
       return designRatioMapper.selectDesignRatioList(designRatio);
    }

    /**
     * 新增设计组比例分配
     *
     * @param designRatio 设计组比例分配
     * @return 结果
     */
    @Override
    public int insertDesignRatio(DesignRatio designRatio,int projectDesignPct)
    {
        //designRatio.setCreateTime(DateUtils.getNowDate());
        designRatioMapper.insertDesignRatio(designRatio);
        designRatioMapper.updateManagementRatioDef2(designRatio.getPrjId(),projectDesignPct);
        return 1;
    }

    /**
     * 修改设计组比例分配
     *
     * @param designRatio 设计组比例分配
     * @return 结果
     */
    @Override
    public int updateDesignRatio(DesignRatio designRatio,int projectDesignPct)
    {
        designRatioMapper.updateDesignRatio(designRatio);
        designRatioMapper.updateManagementRatioDef2(designRatio.getPrjId(),projectDesignPct);
        return 1;
    }

    /**
     * 批量删除设计组比例分配
     *
     * @param ids 需要删除的设计组比例分配主键
     * @return 结果
     */
    @Override
    public int deleteDesignRatioByIds(Long[] ids)
    {
        return designRatioMapper.deleteDesignRatioByIds(ids);
    }

    /**
     * 删除设计组比例分配信息
     *
     * @param id 设计组比例分配主键
     * @return 结果
     */
    @Override
    public int deleteDesignRatioById(Long id)
    {
        return designRatioMapper.deleteDesignRatioById(id);
    }

    /**
     * 查询已分配的设计比例数据
     * @param designRatio
     * @return
     */
    @Override
    public List<DesignRatio> selectAllocatedList(DesignRatio designRatio) {
        return designRatioMapper.selectAllocatedList(designRatio);
    }
}
