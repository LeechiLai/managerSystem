package com.cndc.business.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.cndc.business.domain.DesignRatio;
import com.cndc.business.service.IDesignRatioService;
import com.cndc.common.utils.poi.ExcelUtil;
import com.cndc.common.core.page.TableDataInfo;

/**
 * 设计组比例分配Controller
 *
 * @author laizhiying
 * @date 2024-12-19
 */
@RestController
@RequestMapping("/business/DesignRatio")
public class DesignRatioController extends BaseController {
    @Autowired
    private IDesignRatioService designRatioService;

    /**
     * 查询设计组比例分配列表
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:list')")
    @GetMapping("/list")
    public TableDataInfo list(DesignRatio designRatio) {
        startPage();
        List<DesignRatio> list = designRatioService.selectDesignRatioList(designRatio);
        return getDataTable(list);
    }

    /**
     * 导出设计组比例分配列表
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:export')")
    @Log(title = "设计组比例分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DesignRatio designRatio) {
        List<DesignRatio> list = designRatioService.selectDesignRatioList(designRatio);
        ExcelUtil<DesignRatio> util = new ExcelUtil<DesignRatio>(DesignRatio.class);
        util.exportExcel(response, list, "设计组比例分配数据");
    }

    /**
     * 获取设计组比例分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(designRatioService.selectDesignRatioById(id));
    }

    /**
     * 新增设计组比例分配
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:add')")
    @Log(title = "设计组比例分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DesignRatio designRatio,@RequestParam int projectDesignPct) {
        designRatio.setCreateTime(DateUtils.getNowDate());
        designRatio.setCreateBy(getUsername());
        //设置登录用户的id和所属部门：做数据权限控制需要这两个字段
        SysUser sysUser = SpringUtils.getBean(ISysUserService.class).selectUserByUserName(getUsername());
        if(StringUtils.isNotNull(sysUser)){
            designRatio.setUserId(sysUser.getUserId());
            designRatio.setDeptId(sysUser.getDeptId());
        }
        return toAjax(designRatioService.insertDesignRatio(designRatio,projectDesignPct));
    }

    /**
     * 修改设计组比例分配
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:edit')")
    @Log(title = "设计组比例分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DesignRatio designRatio,@RequestParam int projectDesignPct) {
        designRatio.setUpdateTime(DateUtils.getNowDate());
        designRatio.setUpdateBy(getUsername());
        return toAjax(designRatioService.updateDesignRatio(designRatio,projectDesignPct));

    }

    /**
     * 删除设计组比例分配
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:remove')")
    @Log(title = "设计组比例分配", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(designRatioService.deleteDesignRatioByIds(ids));
    }

    /**
     * 查询设计组比例分配中的 proj_pct 字段
     */
    @PreAuthorize("@ss.hasPermi('business:DesignRatio:query')")
    @GetMapping("/projPct")
    public AjaxResult getProjPct(@RequestParam Long prjId,
                                 @RequestParam String profession) {
        BigDecimal projPct = designRatioService.getProjPct(prjId, profession);
        return success(projPct);
    }
}
