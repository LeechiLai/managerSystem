package com.cndc.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.cndc.business.domain.BatchAddProfessionalAllocationDTO;
import com.cndc.business.domain.DesignRatio;
import com.cndc.business.domain.ProfessionalAllocationB;
import com.cndc.business.service.IDesignRatioService;
import com.cndc.business.service.IManagementRatioService;
import com.cndc.business.service.IProfessionalAllocationBService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.cndc.common.annotation.Log;
import com.cndc.common.core.controller.BaseController;
import com.cndc.common.core.domain.AjaxResult;
import com.cndc.common.enums.BusinessType;
import com.cndc.business.domain.ProfessionalAllocation;
import com.cndc.business.service.IProfessionalAllocationService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 专业负责人策划Controller
 *
 * @author laizhiying
 * @date 2025-02-17
 */
@RestController
@RequestMapping("/business/ProfessionalAllocation")
public class ProfessionalAllocationController extends BaseController
{
    @Autowired
    private IProfessionalAllocationService professionalAllocationService;

    @Autowired
    private IProfessionalAllocationBService professionalAllocationBService;

    @Autowired
    private IDesignRatioService designRatioService;

    /**
     * 查询专业负责人策划列表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProfessionalAllocation professionalAllocation)
    {
        startPage();
        List<ProfessionalAllocation> list = professionalAllocationService.selectProfessionalAllocationList(professionalAllocation);
        return getDataTable(list);
    }

    /**
     * 导出专业负责人策划列表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:export')")
    @Log(title = "专业负责人策划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProfessionalAllocation professionalAllocation)
    {
        List<ProfessionalAllocation> list = professionalAllocationService.selectProfessionalAllocationList(professionalAllocation);
        ExcelUtil<ProfessionalAllocation> util = new ExcelUtil<ProfessionalAllocation>(ProfessionalAllocation.class);
        util.exportExcel(response, list, "专业负责人策划数据");
    }

    /**
     * 获取专业负责人策划详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(professionalAllocationService.selectProfessionalAllocationById(id));
    }

    /**
     * 新增专业负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:add')")
    @Transactional(rollbackFor = Exception.class)
    @Log(title = "专业负责人策划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProfessionalAllocation professionalAllocation) {
        //return toAjax(professionalAllocationService.insertProfessionalAllocation(professionalAllocation));
        professionalAllocationService.insertProfessionalAllocation(professionalAllocation);

        // 构造返回数据，包含影响行数和插入后的 id
        Map<String, Object> result = new HashMap<>();
        result.put("id", professionalAllocation.getId());
        result.put("rowsAffected", professionalAllocation.getId() != null ? 1 : 0); // 根据实际情况调整影响行数的获取方式

        return AjaxResult.success(result);
    }

    /**
     * 修改专业负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:edit')")
    @Log(title = "专业负责人策划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProfessionalAllocation professionalAllocation)
    {
        return toAjax(professionalAllocationService.updateProfessionalAllocation(professionalAllocation));
    }

    /**
     * 删除专业负责人策划
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:remove')")
    @Log(title = "专业负责人策划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(professionalAllocationService.deleteProfessionalAllocationByIds(ids));
    }

    /**
     * 跳转到专业负责人策划页面（表体）
     * @param professionalAllocation
     * @return
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:list')")
    @GetMapping(value = "/edit/ProfessionalAllocationB")
    public TableDataInfo editProfessionalBAllocation(ProfessionalAllocation professionalAllocation) {
        startPage();
        List<ProfessionalAllocationB> list = professionalAllocationBService.selectBody(professionalAllocation);
        return getDataTable(list);
    }


    /**
     * 查询专业负责人策划列表
     * @param designRatio
     * @return
     */
    @PreAuthorize("@ss.hasPermi('business:designRatio:list')")
    @GetMapping(value = "/design/allocatedList")
    public TableDataInfo allocatedList(DesignRatio designRatio) {
        startPage();
        List<DesignRatio> list = designRatioService.selectAllocatedList(designRatio);
        return getDataTable(list);
    }

    @Autowired
    private IManagementRatioService managementRatioService;
    @PreAuthorize("@ss.hasPermi('business:designRatio:list')")
    @GetMapping(value = "/design/designRatioInAProject")
    public AjaxResult designRatioInAProject(@RequestParam("prjId") Long prjId) {
        // 调用 Service 层获取 def2
        String def2String = managementRatioService.getDef2ByPrjId(prjId);
        // 如果没有找到对应的 def2 值，返回错误信息
        if (def2String == null) {
            return AjaxResult.error("未找到对应的 def2 值");
        }
        float def2 = Float.parseFloat(def2String);
        // 查询数据库或其他逻辑
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("def2", def2);


        // 返回成功结果
        return AjaxResult.success(resultMap);
    }


    /**
     * 在同一个页面同时新增主表和子表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:add')")
    @Transactional(rollbackFor = Exception.class)
    @Log(title = "专业负责人策划", businessType = BusinessType.INSERT)
    @PostMapping(value = "/batchAdd")
    public AjaxResult batchAdd(@RequestBody BatchAddProfessionalAllocationDTO dto) {
        ProfessionalAllocation professionalAllocation = dto.getProfessionalAllocation();
        Long itemId = dto.getItemId();

        return toAjax(professionalAllocationService.addProfessionalAllocation(professionalAllocation, itemId));
    }


    /**
     * @Author laizhiying
     * 异步查询【专业】字段
     **/

    @GetMapping("/getProfessionalByHeadId/{headId}")
    public AjaxResult getProfessionalByHeadId(@PathVariable("headId") Long headId) {
        String profession = professionalAllocationService.getProfessionByHeadId(headId);
        return AjaxResult.success("profession", profession);
    }

    /**
     * 更新专业字段
     * @Author laizhiying
     **/
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocation:edit')")
    @Log(title = "专业负责人策划", businessType = BusinessType.UPDATE)
    @PutMapping("/updateProfession")
    public AjaxResult updateProfession(@RequestParam Long headId, @RequestParam String profession) {
        return toAjax(professionalAllocationService.updateProfession(headId, profession));
    }
}
