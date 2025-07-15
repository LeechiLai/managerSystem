package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.service.IManagementRatioService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 管理组比例分配Controller
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@RestController
@RequestMapping("/business/ManagementRatio")
public class ManagementRatioController extends BaseController {
    @Autowired
    private IManagementRatioService managementRatioService;

    /**
     * 查询管理组比例分配列表
     */
    @PreAuthorize("@ss.hasPermi('business:ManagementRatio:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManagementRatio managementRatio) {
        startPage();
        List<ManagementRatio> list = managementRatioService.selectManagementRatioList(managementRatio);
        return getDataTable(list);
    }

    /**
     * 导出管理组比例分配列表
     */
    @PreAuthorize("@ss.hasPermi('business:ManagementRatio:export')")
    @Log(title = "管理组比例分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManagementRatio managementRatio) {
        List<ManagementRatio> list = managementRatioService.selectManagementRatioList(managementRatio);
        ExcelUtil<ManagementRatio> util = new ExcelUtil<ManagementRatio>(ManagementRatio.class);
        util.exportExcel(response, list, "管理组比例分配数据");
    }

    /**
     * 获取管理组比例分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:ManagementRatio:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(managementRatioService.selectManagementRatioById(id));
    }

    /**
     * 新增管理组比例分配
     */
    @PreAuthorize("@ss.hasPermi('business:ManagementRatio:add')")
    @Log(title = "管理组比例分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManagementRatio managementRatio) {
        managementRatio.setCreateTime(DateUtils.getNowDate());
        managementRatio.setCreateBy(getUsername());
        //设置登录用户的id和所属部门：做数据权限控制需要这两个字段
        SysUser sysUser = SpringUtils.getBean(ISysUserService.class).selectUserByUserName(getUsername());
        if(StringUtils.isNotNull(sysUser)){
            managementRatio.setUserId(sysUser.getUserId());
            managementRatio.setDeptId(sysUser.getDeptId());
        }
        return toAjax(managementRatioService.insertManagementRatio(managementRatio));
    }

    /**
     * 修改管理组比例分配
     */
    @PreAuthorize("@ss.hasPermi('business:ManagementRatio:edit')")
    @Log(title = "管理组比例分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManagementRatio managementRatio) {
        managementRatio.setUpdateTime(DateUtils.getNowDate());
        managementRatio.setUpdateBy(getUsername());
        return toAjax(managementRatioService.updateManagementRatio(managementRatio));
    }

    /**
     * 删除管理组比例分配
     */
    @PreAuthorize("@ss.hasPermi('business:ManagementRatio:remove')")
    @Log(title = "管理组比例分配", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(managementRatioService.deleteManagementRatioByIds(ids));
    }
}
