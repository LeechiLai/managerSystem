package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.domain.ProjectReceipts;
import com.cndc.business.service.IProjectReceiptsService;
import com.cndc.common.core.domain.entity.SysUser;
import com.cndc.common.utils.DateUtils;
import com.cndc.common.utils.StringUtils;
import com.cndc.common.utils.spring.SpringUtils;
import com.cndc.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cndc.common.annotation.Log;
import com.cndc.common.core.controller.BaseController;
import com.cndc.common.core.domain.AjaxResult;
import com.cndc.common.enums.BusinessType;
import com.cndc.business.domain.Project;
import com.cndc.business.service.IProjectService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 项目信息Controller
 *
 * @author laizhiying
 * @date 2024-12-03
 */
@RestController
@RequestMapping("/business/project")
public class ProjectController extends BaseController {
    @Autowired
    private IProjectService projectService;

    @Autowired
    private IProjectReceiptsService projectReceiptsService;

    /**
     * 查询项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project) {
        startPage();
        String createBy = getLoginUser().getUsername();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:project:export')")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Project project) {
        List<Project> list = projectService.selectProjectList(project);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        util.exportExcel(response, list, "项目信息数据");
    }

    /**
     * 获取项目信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(projectService.selectProjectById(id));
    }

    /**
     * 新增项目信息
     */
    @PreAuthorize("@ss.hasPermi('business:project:add')")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Project project) {
        project.setCreateTime(DateUtils.getNowDate());
        project.setCreateBy(getUsername());
        //设置登录用户的id和所属部门：做数据权限控制需要这两个字段
        SysUser sysUser = SpringUtils.getBean(ISysUserService.class).selectUserByUserName(getUsername());
        if(StringUtils.isNotNull(sysUser)){
            project.setUserId(sysUser.getUserId());
            project.setDeptId(sysUser.getDeptId());
        }
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目信息
     */
    @PreAuthorize("@ss.hasPermi('business:project:edit')")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Project project) {
        project.setUpdateTime(DateUtils.getNowDate());
        project.setUpdateBy(getUsername());
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目信息
     */
    @PreAuthorize("@ss.hasPermi('business:project:remove')")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(projectService.deleteProjectByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('business:receipts:edit')")
    @GetMapping(value = "/receipted")
    public TableDataInfo receipted(ProjectReceipts receipts) {
        startPage();
        List<ProjectReceipts> list = projectReceiptsService.selectReceipted(receipts);
        return getDataTable(list);
    }
}
