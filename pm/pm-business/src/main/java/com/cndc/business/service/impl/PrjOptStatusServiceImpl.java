package com.cndc.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cndc.business.domain.Project;
import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.mapper.ProjectDetailsMapper;
import com.cndc.business.mapper.ProjectMapper;
import com.cndc.common.core.text.Convert;
import com.cndc.common.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cndc.business.mapper.PrjOptStatusMapper;

import com.cndc.business.domain.PrjOptStatus;
import com.cndc.business.service.IPrjOptStatusService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 项目运行情况Service业务层处理
 *
 * @author laizhiying
 * @date 2025-04-07
 */
@Service
public class PrjOptStatusServiceImpl implements IPrjOptStatusService {
    @Resource
    private PrjOptStatusMapper prjOptStatusMapper;
    @Resource
    private ProjectMapper projectMapper;
    // 新增缺失的Mapper注入
    @Resource
    private ProjectDetailsMapper projectDetailsMapper; // 假设接口名为ProjectDetailsMapper
    /**
     * 查询项目运行情况
     *
     * @param id 项目运行情况主键
     * @return 项目运行情况
     */
    @Override
    public PrjOptStatus selectPrjOptStatusById(Long id) {
        return prjOptStatusMapper.selectPrjOptStatusById(id);
    }

    /**
     * 查询项目运行情况列表
     *
     * @param prjOptStatus 项目运行情况
     * @return 项目运行情况
     */
    @Override
    public List<PrjOptStatus> selectPrjOptStatusList(PrjOptStatus prjOptStatus) {
        // 先同步最新数据
        //syncProjectDataToOptStatus();
        //全选数据返回。
        return prjOptStatusMapper.selectPrjOptStatusList(prjOptStatus);
    }
    /**
     * 同步project和project_details数据到tb_prj_opt_status
     */
    @Transactional
    public void syncProjectDataToOptStatus() {
        // 1. 查询所有有效项目（未删除）
        List<Project> projects = projectMapper.selectAllProjects();


        // 2. 提取项目ID集合
        List<Long> prjIds = projects.stream()
                .map(Project::getId)
                .collect(Collectors.toList());


        // 3. 批量查询 tb_project_details.def1 并转换为 Map
        // Service 层调用
        List<Map<String, Object>> resultList = projectDetailsMapper.selectDef1ByPrjIds(prjIds);

        Map<Long, String> rawMap = resultList.stream()
                .filter(map -> map.containsKey("prj_id") && map.containsKey("def1"))
                .collect(Collectors.toMap(
                        map -> ((Number) map.get("prj_id")).longValue(), // ✅ 安全处理数值类型
                        map -> (String) map.get("def1")
                ));

        Map<Long, BigDecimal> def1Map = rawMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Convert.toBigDecimal(e.getValue(), BigDecimal.ZERO)
                ));

        // 4. 构建批量更新实体
        List<PrjOptStatus> updateList = new ArrayList<>();

        for (Project project : projects) {
            PrjOptStatus updateEntity = new PrjOptStatus();

            // 必填字段
            updateEntity.setProjectId(project.getId());
            updateEntity.setId(project.getId()); // 假设主键直接复用 project.id
            updateEntity.setProgress(def1Map.getOrDefault(project.getId(), BigDecimal.valueOf(0))); // def1 -> progress
            // 核心字段映射
            updateEntity.setProjectName(project.getProjectName());
            updateEntity.setPhaseNo(project.getProjectCode());       // project_code -> phase_no
            updateEntity.setProjectCode(project.getProjectCode());   // 工程号
            updateEntity.setPrjexecStatus(project.getPrjexecStatus());      // 项目执行状态
            updateEntity.setRecvRatio(project.getRecvRatio());       // 收款比例
            updateEntity.setProjectManager(project.getProjectManager()); // 项目负责人
            updateEntity.setDelFlag(project.getDelFlag());           // 删除标志
            updateEntity.setDeptId(project.getDeptId());             // 部门ID
            updateEntity.setUserId(project.getUserId());             // 用户ID

            updateList.add(updateEntity);
        }

        // 5. 执行批量更新
        if (!updateList.isEmpty()) {

            for (PrjOptStatus project : updateList) {
                int rowsUpdated = prjOptStatusMapper.updateByPrjIdSelective(project);
                if (rowsUpdated == 0) {
                    prjOptStatusMapper.insertPrjOptStatus(project);
                }
            }
        }
    }
    /**
     * 新增项目运行情况
     *
     * @param prjOptStatus 项目运行情况
     * @return 结果
     */
    @Override
    public int insertPrjOptStatus(PrjOptStatus prjOptStatus) {
        prjOptStatus.setCreateTime(DateUtils.getNowDate());
        return prjOptStatusMapper.insertPrjOptStatus(prjOptStatus);
    }

    /**
     * 修改项目运行情况
     *
     * @param prjOptStatus 项目运行情况
     * @return 结果
     */
    @Override
    public int updatePrjOptStatus(PrjOptStatus prjOptStatus) {
        prjOptStatus.setUpdateTime(DateUtils.getNowDate());
        // 同步更新 tb_project_details.def1
        if (prjOptStatus.getProgress() != null && prjOptStatus.getProjectId() != null) {

            projectDetailsMapper.updateDef1ByPrjId(prjOptStatus.getProjectId(), prjOptStatus.getProgress());
        }
        return prjOptStatusMapper.updatePrjOptStatus(prjOptStatus);
    }

    /**
     * 批量删除项目运行情况
     *
     * @param ids 需要删除的项目运行情况主键
     * @return 结果
     */
    @Override
    public int deletePrjOptStatusByIds(Long[] ids) {
        return prjOptStatusMapper.deletePrjOptStatusByIds(ids);
    }

    /**
     * 删除项目运行情况信息
     *
     * @param id 项目运行情况主键
     * @return 结果
     */
    @Override
    public int deletePrjOptStatusById(Long id) {
        return prjOptStatusMapper.deletePrjOptStatusById(id);
    }
}
