package com.ruoyi.business.controller;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.BaseSensor;
import com.ruoyi.business.service.IBaseSensorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 传感器信息Controller
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/business/sensor")
public class BaseSensorController extends BaseController
{
    @Autowired
    private IBaseSensorService baseSensorService;

    /**
     * 查询传感器信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:sensor:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseSensor baseSensor)
    {
        startPage();
        List<BaseSensor> list = baseSensorService.selectBaseSensorList(baseSensor);
        return getDataTable(list);
    }

    /**
     * 导出传感器信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:sensor:export')")
    @Log(title = "传感器信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseSensor baseSensor)
    {
        List<BaseSensor> list = baseSensorService.selectBaseSensorList(baseSensor);
        ExcelUtil<BaseSensor> util = new ExcelUtil<BaseSensor>(BaseSensor.class);
        util.exportExcel(response, list, "传感器信息数据");
    }

    /**
     * 新增传感器信息
     */
    @PreAuthorize("@ss.hasPermi('business:sensor:add')")
    @Log(title = "传感器信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSensor baseSensor)
    {
        return toAjax(baseSensorService.insertBaseSensor(baseSensor));
    }

    /**
     * 修改传感器信息
     */
    @PreAuthorize("@ss.hasPermi('business:sensor:edit')")
    @Log(title = "传感器信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSensor baseSensor)
    {
        return toAjax(baseSensorService.updateBaseSensor(baseSensor));
    }

    /**
     * 删除传感器信息
     */
    @PreAuthorize("@ss.hasPermi('business:sensor:remove')")
    @Log(title = "传感器信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sensorIds}")
    public AjaxResult remove(@PathVariable Long[] sensorIds)
    {
        return toAjax(baseSensorService.deleteBaseSensorBySensorIds(sensorIds));
    }
}
