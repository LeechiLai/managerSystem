package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.cndc.business.domain.PrjOptStatus;
import com.cndc.business.service.IPrjOptStatusService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 项目运行情况Controller
 *
 * @author laizhiying
 * @date 2025-04-07
 */
@RestController
@RequestMapping("/business/PrjOptStatus")
public class PrjOptStatusController extends BaseController {
    @Autowired
    private IPrjOptStatusService prjOptStatusService;

    /**
     * 查询项目运行情况列表
     */
    @PreAuthorize("@ss.hasPermi('business:PrjOptStatus:list')")
    @GetMapping("/list")
    public TableDataInfo list(PrjOptStatus prjOptStatus) {
        startPage();
        List<PrjOptStatus> list = prjOptStatusService.selectPrjOptStatusList(prjOptStatus);
        return getDataTable(list);
    }

    /**
     * 导出项目运行情况列表
     */
    @PreAuthorize("@ss.hasPermi('business:PrjOptStatus:export')")
    @Log(title = "项目运行情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PrjOptStatus prjOptStatus) {
        List<PrjOptStatus> list = prjOptStatusService.selectPrjOptStatusList(prjOptStatus);
        ExcelUtil<PrjOptStatus> util = new ExcelUtil<PrjOptStatus>(PrjOptStatus.class);
        util.exportExcel(response, list, "项目运行情况数据");
    }

    /**
     * 获取项目运行情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:PrjOptStatus:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(prjOptStatusService.selectPrjOptStatusById(id));
    }

    /**
     * 新增项目运行情况
     */
    @PreAuthorize("@ss.hasPermi('business:PrjOptStatus:add')")
    @Log(title = "项目运行情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PrjOptStatus prjOptStatus) {
        return toAjax(prjOptStatusService.insertPrjOptStatus(prjOptStatus));
    }

    /**
     * 修改项目运行情况
     */
    @PreAuthorize("@ss.hasPermi('business:PrjOptStatus:edit')")
    @Log(title = "项目运行情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PrjOptStatus prjOptStatus) {
        return toAjax(prjOptStatusService.updatePrjOptStatus(prjOptStatus));
    }

    /**
     * 删除项目运行情况
     */
    @PreAuthorize("@ss.hasPermi('business:PrjOptStatus:remove')")
    @Log(title = "项目运行情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(prjOptStatusService.deletePrjOptStatusByIds(ids));
    }
}
