package com.cndc.business.service.impl;

import java.util.List;

import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.domain.vo.ProjectDetailsVo;
import com.cndc.common.annotation.DataScope;
import com.cndc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.ManagementRatioMapper;
import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.service.IManagementRatioService;

import javax.annotation.Resource;

/**
 * 管理组比例分配Service业务层处理
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@Service
public class ManagementRatioServiceImpl implements IManagementRatioService {
    @Resource
    private ManagementRatioMapper managementRatioMapper;

    /**
     * 查询管理组比例分配
     *
     * @param id 管理组比例分配主键
     * @return 管理组比例分配
     */
    @Override
    public ManagementRatio selectManagementRatioById(Long id) {
        return managementRatioMapper.selectManagementRatioById(id);
    }

    /**
     * 查询管理组比例分配列表
     *
     * @param managementRatio 管理组比例分配
     * @return 管理组比例分配
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ManagementRatio> selectManagementRatioList(ManagementRatio managementRatio) {
        return managementRatioMapper.selectManagementRatioList(managementRatio);
    }

    /**
     * 新增管理组比例分配
     *
     * @param managementRatio 管理组比例分配
     * @return 结果
     */
    @Override
    public int insertManagementRatio(ManagementRatio managementRatio) {
        return managementRatioMapper.insertManagementRatio(managementRatio);
    }

    /**
     * 修改管理组比例分配
     *
     * @param managementRatio 管理组比例分配
     * @return 结果
     */
    @Override
    public int updateManagementRatio(ManagementRatio managementRatio) {
        managementRatioMapper.updateManagementRatio(managementRatio);


        managementRatioMapper.updateManagementRatio(managementRatio);


        Long prjId = managementRatio.getPrjId();
        String def1 = managementRatio.getDef1();


        if (prjId != null && def1 != null) {
            managementRatioMapper.updateDef1ByPrjId(prjId, def1);
        }
        return 1;
    }

    /**
     * 批量删除管理组比例分配
     *
     * @param ids 需要删除的管理组比例分配主键
     * @return 结果
     */
    @Override
    public int deleteManagementRatioByIds(Long[] ids) {
        return managementRatioMapper.deleteManagementRatioByIds(ids);
    }

    /**
     * 删除管理组比例分配信息
     *
     * @param id 管理组比例分配主键
     * @return 结果
     */
    @Override
    public int deleteManagementRatioById(Long id) {
        return managementRatioMapper.deleteManagementRatioById(id);
    }

    /**
     * 查询已分配的管理组比例分配列表
     * @param managementRatio
     * @return
     */
    @Override
    //@DataScope(deptAlias = "d", userAlias = "u")
    public List<ManagementRatio> selectAllocatedList(ManagementRatio managementRatio) {
        return managementRatioMapper.selectAllocatedList(managementRatio);
    }




    @Override
    public String getDef2ByPrjId(Long prjId) {
        return managementRatioMapper.selectDef2ByPrjId(prjId);
    }

}
