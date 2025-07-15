package com.cndc.business.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.cndc.common.annotation.Log;
import com.cndc.common.core.controller.BaseController;
import com.cndc.common.core.domain.AjaxResult;
import com.cndc.common.enums.BusinessType;
import com.cndc.business.domain.ProfessionalAllocationB;
import com.cndc.business.service.IProfessionalAllocationBService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 专业负责人策划子表Controller
 *
 * @author laizhiying
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/business/ProfessionalAllocationB")
public class ProfessionalAllocationBController extends BaseController
{
    @Autowired
    private IProfessionalAllocationBService professionalAllocationBService;

    /**
     * 查询专业负责人策划子表列表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProfessionalAllocationB professionalAllocationB)
    {
        startPage();
        List<ProfessionalAllocationB> list = professionalAllocationBService.selectProfessionalAllocationBList(professionalAllocationB);
        return getDataTable(list);
    }

    /**
     * 导出专业负责人策划子表列表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:export')")
    @Log(title = "专业负责人策划子表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProfessionalAllocationB professionalAllocationB)
    {
        List<ProfessionalAllocationB> list = professionalAllocationBService.selectProfessionalAllocationBList(professionalAllocationB);
        ExcelUtil<ProfessionalAllocationB> util = new ExcelUtil<ProfessionalAllocationB>(ProfessionalAllocationB.class);
        util.exportExcel(response, list, "专业负责人策划子表数据");
    }

    /**
     * 获取专业负责人策划子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(professionalAllocationBService.selectProfessionalAllocationBById(id));
    }

    /**
     * 新增专业负责人策划子表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:add')")
    @Transactional(rollbackFor = Exception.class)
    @Log(title = "专业负责人策划子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProfessionalAllocationB professionalAllocationB)
    {
        // 调用 Service 层新增数据
        professionalAllocationBService.insertProfessionalAllocationB(professionalAllocationB);

        // 更新专业综合完成度
        professionalAllocationBService.updateProfessionCompletion();

        return AjaxResult.success("新增成功");
        //return toAjax(professionalAllocationBService.insertProfessionalAllocationB(professionalAllocationB));
    }

    /**
     * 修改专业负责人策划子表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:edit')")
    @Log(title = "专业负责人策划子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProfessionalAllocationB professionalAllocationB)
    {
        // 调用 Service 层修改数据
        professionalAllocationBService.updateProfessionalAllocationB(professionalAllocationB);

        // 更新专业综合完成度
        professionalAllocationBService.updateProfessionCompletion();

        return AjaxResult.success("修改成功");
        //return toAjax(professionalAllocationBService.updateProfessionalAllocationB(professionalAllocationB));
    }

    /**
     * 删除专业负责人策划子表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:remove')")
    @Log(title = "专业负责人策划子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(professionalAllocationBService.deleteProfessionalAllocationBByIds(ids));
    }


    /**
     * 获取专业负责人的名字列表
     */
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:list')")
    @GetMapping("/proRespNames")
    public AjaxResult getProRespNames(@RequestParam Map<String, Object> query) {
        List<String> proRespNames = professionalAllocationBService.getProRespNames(query);
        return success(proRespNames);
    }





        // 新增查询接口
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:list')")
    @GetMapping("/listByProject")
    public TableDataInfo listByProject(@RequestParam Long prjId, @RequestParam String profession) {
        startPage();
        List<ProfessionalAllocationB> list = professionalAllocationBService.selectByPrjIdAndHeadId(prjId, profession);
        return getDataTable(list);
    }

    /**
     * 项目子项跳转专业策划
     * @Author laizhiying
     * @return
     **/
    @PreAuthorize("@ss.hasPermi('business:ProfessionalAllocationB:list')")
    @GetMapping("/getProjectItems")
    public TableDataInfo getProjectItems(
            @RequestParam Long prjId,
            @RequestParam Long itemId) {
        startPage();
        List<ProfessionalAllocationB> list = professionalAllocationBService.getProjectItemsByPrjId(prjId,itemId);
        return getDataTable(list);
    }

}
