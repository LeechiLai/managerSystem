package com.cndc.business.service.impl;

import java.util.List;
import com.cndc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.cndc.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.cndc.business.domain.ProfessionalAllocationB;
import com.cndc.business.mapper.ProfessionalAllocationMapper;
import com.cndc.business.domain.ProfessionalAllocation;
import com.cndc.business.service.IProfessionalAllocationService;

/**
 * 专业负责人策划Service业务层处理
 *
 * @author laizhiying
 * @date 2025-02-17
 */
@Service
public class ProfessionalAllocationServiceImpl implements IProfessionalAllocationService
{
    @Autowired
    private ProfessionalAllocationMapper professionalAllocationMapper;

    /**
     * 查询专业负责人策划
     *
     * @param id 专业负责人策划主键
     * @return 专业负责人策划
     */
    @Override
    public ProfessionalAllocation selectProfessionalAllocationById(Long id)
    {
        return professionalAllocationMapper.selectProfessionalAllocationById(id);
    }

    /**
     * 查询专业负责人策划列表
     *
     * @param professionalAllocation 专业负责人策划
     * @return 专业负责人策划
     */
    @Override
    public List<ProfessionalAllocation> selectProfessionalAllocationList(ProfessionalAllocation professionalAllocation)
    {
        return professionalAllocationMapper.selectProfessionalAllocationList(professionalAllocation);
    }

    /**
     * 新增专业负责人策划
     *
     * @param professionalAllocation 专业负责人策划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertProfessionalAllocation(ProfessionalAllocation professionalAllocation)
    {
        professionalAllocation.setCreateTime(DateUtils.getNowDate());
        int rows = professionalAllocationMapper.insertProfessionalAllocation(professionalAllocation);
        insertProfessionalAllocationB(professionalAllocation);
        return rows;
    }

    /**
     * 在同一个页面同时新增主表和子表
     */
    @Transactional
    @Override
    public int addProfessionalAllocation(ProfessionalAllocation professionalAllocation,Long itemId) {
        professionalAllocation.setCreateTime(DateUtils.getNowDate());
        int rows = professionalAllocationMapper.insertProfessionalAllocation(professionalAllocation);

        // 获取刚插入主表的 id
        Long headId = professionalAllocation.getId();

        // 更新子表的 head_id
        if (headId != null && itemId != null) {
            professionalAllocationMapper.updateProfessionalAllocationBHeadIdByItemId(headId, itemId);
        }

        return rows;
    }

    /**
     * @Author laizhiying
     * 异步查询【专业】字段
     **/
    @Override
    public String getProfessionByHeadId(Long headId) {
        return professionalAllocationMapper.getProfessionByHeadId(headId);
    }

    /**
     * 更新专业字段
     * @Author laizhiying
     **/
    @Override
    public int updateProfession(Long headId, String profession) {
        return professionalAllocationMapper.updateProfessionById(headId, profession);
    }

    /**
     * 修改专业负责人策划
     *
     * @param professionalAllocation 专业负责人策划
     * @return 结果
     */
    @Transactional
    @Override
    public int updateProfessionalAllocation(ProfessionalAllocation professionalAllocation)
    {
        professionalAllocation.setUpdateTime(DateUtils.getNowDate());
        professionalAllocationMapper.deleteProfessionalAllocationBByHeadId(professionalAllocation.getId());
        insertProfessionalAllocationB(professionalAllocation);
        return professionalAllocationMapper.updateProfessionalAllocation(professionalAllocation);
    }

    /**
     * 批量删除专业负责人策划
     *
     * @param ids 需要删除的专业负责人策划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteProfessionalAllocationByIds(Long[] ids)
    {
        professionalAllocationMapper.deleteProfessionalAllocationBByHeadIds(ids);
        return professionalAllocationMapper.deleteProfessionalAllocationByIds(ids);
    }

    /**
     * 删除专业负责人策划信息
     *
     * @param id 专业负责人策划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteProfessionalAllocationById(Long id)
    {
        professionalAllocationMapper.deleteProfessionalAllocationBByHeadId(id);
        return professionalAllocationMapper.deleteProfessionalAllocationById(id);
    }


    /**
     * 新增专业负责人策划子信息
     *
     * @param professionalAllocation 专业负责人策划对象
     */
    public void insertProfessionalAllocationB(ProfessionalAllocation professionalAllocation)
    {
        List<ProfessionalAllocationB> professionalAllocationBList = professionalAllocation.getProfessionalAllocationBList();
        Long id = professionalAllocation.getId();
        if (StringUtils.isNotNull(professionalAllocationBList))
        {
            List<ProfessionalAllocationB> list = new ArrayList<ProfessionalAllocationB>();
            for (ProfessionalAllocationB professionalAllocationB : professionalAllocationBList)
            {
                professionalAllocationB.setHeadId(id);
                list.add(professionalAllocationB);
            }
            if (list.size() > 0)
            {
                professionalAllocationMapper.batchProfessionalAllocationB(list);
            }
        }
    }


}
