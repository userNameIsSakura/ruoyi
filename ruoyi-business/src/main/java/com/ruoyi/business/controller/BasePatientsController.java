package com.ruoyi.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.BasePatients;
import com.ruoyi.business.service.IBasePatientsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 病人列表Controller
 *
 * @author ruoyi
 * @date 2022-09-25
 */
@RestController
@RequestMapping("/business/patients")
public class BasePatientsController extends BaseController
{
    @Autowired
    private IBasePatientsService basePatientsService;

    /**
     * 查询病人列表列表
     */
    @PreAuthorize("@ss.hasPermi('business:patients:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasePatients basePatients)
    {
        startPage();
        basePatients.setLoginId(SecurityUtils.getUserId().intValue());
        List<BasePatients> list = basePatientsService.selectBasePatientsList(basePatients);
        return getDataTable(list);
    }

    /**
     * 导出病人列表列表
     */
    @PreAuthorize("@ss.hasPermi('business:patients:export')")
    @Log(title = "病人列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BasePatients basePatients)
    {
        List<BasePatients> list = basePatientsService.selectBasePatientsList(basePatients);
        ExcelUtil<BasePatients> util = new ExcelUtil<BasePatients>(BasePatients.class);
        util.exportExcel(response, list, "病人列表数据");
    }

    /**
     * 获取病人列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:patients:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(basePatientsService.selectBasePatientsByPatientId(patientId));
    }

    /**
     * 新增病人列表
     */
    @PreAuthorize("@ss.hasPermi('business:patients:add')")
    @Log(title = "病人列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePatients basePatients)
    {
        return toAjax(basePatientsService.insertBasePatients(basePatients));
    }

    /**
     * 修改病人列表
     */
    @PreAuthorize("@ss.hasPermi('business:patients:edit')")
    @Log(title = "病人列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePatients basePatients)
    {
        return toAjax(basePatientsService.updateBasePatients(basePatients));
    }

    /**
     * 删除病人列表
     */
    @PreAuthorize("@ss.hasPermi('business:patients:remove')")
    @Log(title = "病人列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(basePatientsService.deleteBasePatientsByPatientIds(patientIds));
    }
}
