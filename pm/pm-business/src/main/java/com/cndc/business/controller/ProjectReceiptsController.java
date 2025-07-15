package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cndc.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
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
import com.cndc.business.domain.ProjectReceipts;
import com.cndc.business.service.IProjectReceiptsService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 项目收款记录Controller
 *
 * @author laizhiying
 * @date 2025-04-08
 */
@RestController
@RequestMapping("/business/receipts")
public class ProjectReceiptsController extends BaseController {
    @Autowired
    private IProjectReceiptsService projectReceiptsService;

    /**
     * 查询项目收款记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectReceipts projectReceipts) {
        startPage();
        List<ProjectReceipts> list = projectReceiptsService.selectProjectReceiptsList(projectReceipts);
        return getDataTable(list);
    }

    /**
     * 导出项目收款记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:export')")
    @Log(title = "项目收款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectReceipts projectReceipts) {
        List<ProjectReceipts> list = projectReceiptsService.selectProjectReceiptsList(projectReceipts);
        ExcelUtil<ProjectReceipts> util = new ExcelUtil<ProjectReceipts>(ProjectReceipts.class);
        util.exportExcel(response, list, "项目收款记录数据");
    }

    /**
     * 获取项目收款记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(projectReceiptsService.selectProjectReceiptsById(id));
    }

    /**
     * 根据项目id查询收款记录
     * @param prjId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:query')")
    @GetMapping(value = "getSequence/{prjId}")
    public AjaxResult getReceiptsByPrjId(@PathVariable("prjId") Long prjId) {
        return success(projectReceiptsService.selectProjectReceiptsByPrjId(prjId));
    }

    /**
     * 新增项目收款记录
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:add')")
    @Log(title = "项目收款记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectReceipts projectReceipts) {
        projectReceipts.setCreateTime(DateUtils.getNowDate());
        projectReceipts.setCreateBy(getUsername());
        return toAjax(projectReceiptsService.insertProjectReceipts(projectReceipts));
    }

    /**
     * 修改项目收款记录
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:edit')")
    @Log(title = "项目收款记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectReceipts projectReceipts) {
        projectReceipts.setUpdateTime(DateUtils.getNowDate());
        projectReceipts.setUpdateBy(getUsername());
        return toAjax(projectReceiptsService.updateProjectReceipts(projectReceipts));
    }

    /**
     * 删除项目收款记录
     */
    @PreAuthorize("@ss.hasPermi('business:receipts:remove')")
    @Log(title = "项目收款记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(projectReceiptsService.deleteProjectReceiptsByIds(ids));
    }

    @PostMapping("/latest")
    public List<ProjectReceipts> getLatestReceipts(@RequestBody @Param("projectIds")  List<Long> projectIds) {
        List<ProjectReceipts> list = projectReceiptsService.getLatestReceiptsByProjectIds(projectIds);
        return list;
    }
}
