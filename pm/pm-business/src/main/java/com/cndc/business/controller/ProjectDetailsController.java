package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.domain.vo.ProjectDetailsVo;
import com.cndc.business.service.IManagementRatioService;
import com.cndc.common.core.domain.entity.SysUser;
import com.cndc.common.utils.DateUtils;
import com.cndc.common.utils.StringUtils;
import com.cndc.common.utils.spring.SpringUtils;
import com.cndc.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cndc.common.annotation.Log;
import com.cndc.common.core.controller.BaseController;
import com.cndc.common.core.domain.AjaxResult;
import com.cndc.common.enums.BusinessType;
import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.service.IProjectDetailsService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 项目负责人策划Controller
 *
 * @author laizhiying
 * @date 2024-12-18
 */
@RestController
@RequestMapping("/business/projectdetails")
public class ProjectDetailsController extends BaseController {
    @Autowired
    private IProjectDetailsService projectDetailsService;

    @Autowired
    private IManagementRatioService managementRatioService;

    /**
     * 查询项目负责人策划列表
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectDetails projectDetails) {
        startPage();
        //List<ProjectDetails> list = projectDetailsService.selectProjectDetailsList(projectDetails);
        List<ProjectDetailsVo> list = projectDetailsService.selectProjectDetailsVoList(projectDetails);
        return getDataTable(list);
    }

    /**
     * 导出项目负责人策划列表
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:export')")
    @Log(title = "项目负责人策划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectDetails projectDetails) {
        List<ProjectDetails> list = projectDetailsService.selectProjectDetailsList(projectDetails);
        ExcelUtil<ProjectDetails> util = new ExcelUtil<ProjectDetails>(ProjectDetails.class);
        util.exportExcel(response, list, "项目负责人策划数据");
    }

    /**
     * 获取项目负责人策划详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(projectDetailsService.selectProjectDetailsById(id));
    }

    /**
     * 新增项目负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:add')")
    @Log(title = "项目负责人策划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectDetails projectDetails) {
        projectDetails.setCreateTime(DateUtils.getNowDate());
        projectDetails.setCreateBy(getUsername());
        //设置登录用户的id和所属部门：做数据权限控制需要这两个字段
        SysUser sysUser = SpringUtils.getBean(ISysUserService.class).selectUserByUserName(getUsername());
        if (StringUtils.isNotNull(sysUser)) {
            projectDetails.setUserId(sysUser.getUserId());
            projectDetails.setDeptId(sysUser.getDeptId());
        }
        return toAjax(projectDetailsService.insertProjectDetails(projectDetails));
    }

    /**
     * 修改项目负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:edit')")
    @Log(title = "项目负责人策划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectDetails projectDetails) {
        projectDetails.setUpdateTime(DateUtils.getNowDate());
        projectDetails.setUpdateBy(getUsername());
        return toAjax(projectDetailsService.updateProjectDetails(projectDetails));
    }

    /**
     * 删除项目负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:remove')")
    @Log(title = "项目负责人策划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(projectDetailsService.deleteProjectDetailsByIds(ids));
    }

    /**
     * 删除项目负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:projectdetails:remove')")
    @Log(title = "删除项目子项", businessType = BusinessType.DELETE)
    @DeleteMapping("/sub/{sub_ids}")
    public AjaxResult removeSubItems(@PathVariable Long[] sub_ids) {
        return toAjax(projectDetailsService.deleteProjectSubitemsByIds(sub_ids));
    }

    /**
     * 查询已分配的管理组比例分配列表
     * @param managementRatio
     * @return
     */
    @PreAuthorize("@ss.hasPermi('business:managementRatio:list')")
    @GetMapping(value = "/manage/allocatedList")
    public TableDataInfo allocatedList(ManagementRatio managementRatio) {
        startPage();
        List<ManagementRatio> list = managementRatioService.selectAllocatedList(managementRatio);
        return getDataTable(list);
    }


}
