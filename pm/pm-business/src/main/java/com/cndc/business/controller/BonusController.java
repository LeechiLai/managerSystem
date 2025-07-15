package com.cndc.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cndc.business.domain.BonusItemDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cndc.common.annotation.Log;
import com.cndc.common.core.controller.BaseController;
import com.cndc.common.core.domain.AjaxResult;
import com.cndc.common.enums.BusinessType;
import com.cndc.business.domain.Bonus;
import com.cndc.business.service.IBonusService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 个人奖金Controller
 *
 * @author laizhiying
 * @date 2025-04-07
 */
@RestController
@RequestMapping("/business/bonus")
public class BonusController extends BaseController
{
    @Autowired
    private IBonusService tbBonusService;

    /**
     * 查询个人奖金列表
     */
    @PreAuthorize("@ss.hasPermi('business:bonus:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bonus tbBonus)
    {
        startPage();
        List<Bonus> list = tbBonusService.selectTbBonusList(tbBonus);
        return getDataTable(list);
    }


    @PreAuthorize("@ss.hasPermi('business:bonus:list')")
    @GetMapping("/listByName")
    public List<BonusItemDTO> getBonusList(@RequestParam String name) {
        return tbBonusService.getBonusList(name);
    }
    /**
     * 导出个人奖金列表
     */
    @PreAuthorize("@ss.hasPermi('business:bonus:export')")
    @Log(title = "个人奖金", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bonus tbBonus)
    {
        List<Bonus> list = tbBonusService.selectTbBonusList(tbBonus);
        ExcelUtil<Bonus> util = new ExcelUtil<Bonus>(Bonus.class);
        util.exportExcel(response, list, "个人奖金数据");
    }

    /**
     * 获取个人奖金详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:bonus:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbBonusService.selectTbBonusById(id));
    }

    /**
     * 新增个人奖金
     */
    @PreAuthorize("@ss.hasPermi('business:bonus:add')")
    @Log(title = "个人奖金", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bonus tbBonus)
    {
        return toAjax(tbBonusService.insertTbBonus(tbBonus));
    }

    /**
     * 修改个人奖金
     */
    @PreAuthorize("@ss.hasPermi('business:bonus:edit')")
    @Log(title = "个人奖金", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bonus tbBonus)
    {
        return toAjax(tbBonusService.updateTbBonus(tbBonus));
    }

    /**
     * 删除个人奖金
     */
    @PreAuthorize("@ss.hasPermi('business:bonus:remove')")
    @Log(title = "个人奖金", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbBonusService.deleteTbBonusByIds(ids));
    }




}
