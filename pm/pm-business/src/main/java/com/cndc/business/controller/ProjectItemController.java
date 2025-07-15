package com.cndc.business.controller;

import com.cndc.business.domain.ProfessionalAllocationB;
import com.cndc.business.service.IProfessionalAllocationBService;
import com.cndc.common.core.controller.BaseController;
import com.cndc.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author laizhiying
 * @Create 2025-05-28 14:50
 */
@RestController
@RequestMapping("/projectItem")
public class ProjectItemController extends BaseController {

    @Autowired
    private IProfessionalAllocationBService professionalAllocationBService;
    @GetMapping("/assigned/{headId}")
    public TableDataInfo getAssignedProjectItems(@PathVariable("headId") Long headId) {
        startPage();
        List<ProfessionalAllocationB> list = professionalAllocationBService.selectAssignedProjectItemsByHeadId(headId);
        return getDataTable(list);
    }

    @PutMapping("/assign")
    public ResponseEntity<Void> assignProjectItems(@RequestParam Long id,@RequestParam Long prjId,@RequestParam Long headId) {
        professionalAllocationBService.updateHeadIdByPrjId(id,prjId, headId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assign/batch")
    public ResponseEntity<Void> batchAssignProjectItems(@RequestBody List<Long> ids,@RequestParam Long prjId,@RequestParam Long headId) {
        professionalAllocationBService.batchUpdateHeadIdByPrjId(ids, prjId, headId);
        return ResponseEntity.ok().build();
    }

}
