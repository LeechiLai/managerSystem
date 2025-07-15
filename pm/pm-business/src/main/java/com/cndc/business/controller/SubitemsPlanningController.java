package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cndc.business.domain.DesignRatio;
import com.cndc.business.domain.ManagementRatio;
import com.cndc.business.service.IDesignRatioService;
import com.cndc.common.utils.DateUtils;
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
import com.cndc.business.domain.SubitemsPlanning;
import com.cndc.business.service.ISubitemsPlanningService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 专业负责人策划Controller
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@RestController
@RequestMapping("/business/SubitemsPlanning")
public class SubitemsPlanningController extends BaseController {
    @Autowired
    private ISubitemsPlanningService subitemsPlanningService;

    @Autowired
    private IDesignRatioService designRatioService;

    /**
     * 查询专业负责人策划列表
     */
    @PreAuthorize("@ss.hasPermi('business:SubitemsPlanning:list')")
    @GetMapping("/list")
    public TableDataInfo list(SubitemsPlanning subitemsPlanning) {
        startPage();
        List<SubitemsPlanning> list = subitemsPlanningService.selectSubitemsPlanningList(subitemsPlanning);
        return getDataTable(list);
    }

    /**
     * 导出专业负责人策划列表
     */
    @PreAuthorize("@ss.hasPermi('business:SubitemsPlanning:export')")
    @Log(title = "专业负责人策划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SubitemsPlanning subitemsPlanning) {
        List<SubitemsPlanning> list = subitemsPlanningService.selectSubitemsPlanningList(subitemsPlanning);
        ExcelUtil<SubitemsPlanning> util = new ExcelUtil<SubitemsPlanning>(SubitemsPlanning.class);
        util.exportExcel(response, list, "专业负责人策划数据");
    }

    /**
     * 获取专业负责人策划详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:SubitemsPlanning:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(subitemsPlanningService.selectSubitemsPlanningById(id));
    }

    /**
     * 新增专业负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:SubitemsPlanning:add')")
    @Log(title = "专业负责人策划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SubitemsPlanning subitemsPlanning) {
        subitemsPlanning.setCreateTime(DateUtils.getNowDate());
        subitemsPlanning.setCreateBy(getUsername());
        return toAjax(subitemsPlanningService.insertSubitemsPlanning(subitemsPlanning));
    }

    /**
     * 修改专业负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:SubitemsPlanning:edit')")
    @Log(title = "专业负责人策划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SubitemsPlanning subitemsPlanning) {
        subitemsPlanning.setUpdateTime(DateUtils.getNowDate());
        subitemsPlanning.setUpdateBy(getUsername());
        return toAjax(subitemsPlanningService.updateSubitemsPlanning(subitemsPlanning));
    }

    /**
     * 删除专业负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:SubitemsPlanning:remove')")
    @Log(title = "专业负责人策划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(subitemsPlanningService.deleteSubitemsPlanningByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('business:designRatio:list')")
    @GetMapping(value = "/design/allocatedList")
    public TableDataInfo allocatedList(DesignRatio designRatio) {
        startPage();
        List<DesignRatio> list = designRatioService.selectAllocatedList(designRatio);
        return getDataTable(list);
    }
}
