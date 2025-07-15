package com.cndc.business.service.impl;

import com.cndc.business.domain.PrjOptStatus;
import com.cndc.business.mapper.PrjOptStatusMapper;
import com.cndc.business.mapper.ProjectDetailsMapper;
import com.cndc.business.mapper.ProjectMapper;
import com.cndc.business.domain.Project;
import com.cndc.common.core.text.Convert;
import com.cndc.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author laizhiying
 * @Create 2025-05-26 16:46
 */
@Service
public class PrjOptStatusSyncService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectDetailsMapper projectDetailsMapper;

    @Resource
    private PrjOptStatusMapper prjOptStatusMapper;

    /**
     * 同步项目运行状态（project + project_details -> prj_opt_status）
     *
     * @param projectId 项目ID
     */
    /*@Transactional
    public void syncPrjOptStatusByProjectId(Long projectId) {
        // 1. 获取项目信息
        Project project = projectMapper.selectProjectById(projectId);
        if (project == null) {
            return;
        }

        // 2. 获取 def1 字段
        String def1 = projectDetailsMapper.selectDef1ByPrjId(projectId);
        BigDecimal progress = Convert.toBigDecimal(def1, BigDecimal.valueOf(0));

        // 3. 构建 PrjOptStatus 对象
        PrjOptStatus status = new PrjOptStatus();
        status.setProjectId(project.getId());
        status.setId(project.getId()); // 假设主键复用 project.id
        status.setProgress(progress); // def1 → progress
        status.setProjectName(project.getProjectName());
        status.setPhaseNo(project.getProjectCode());       // project_code → phase_no
        status.setProjectCode(project.getProjectCode());   // 工程号
        status.setPrjexecStatus(project.getPrjexecStatus()); // 项目执行状态
        status.setRecvRatio(project.getRecvRatio());       // 收款比例
        status.setProjectManager(project.getProjectManager()); // 项目负责人
        status.setDelFlag(project.getDelFlag());         // 删除标志
        status.setDeptId(project.getDeptId());           // 部门ID
        status.setUserId(project.getUserId());             // 用户ID

        // 4. 执行更新或插入
        int rowsUpdated = prjOptStatusMapper.updateByPrjIdSelective(status);
        if (rowsUpdated == 0) {
            status.setCreateTime(DateUtils.getNowDate());
            prjOptStatusMapper.insertPrjOptStatus(status);
        }
    }*/
}
