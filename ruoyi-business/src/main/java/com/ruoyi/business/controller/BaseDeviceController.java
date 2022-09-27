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
import com.ruoyi.business.domain.BaseDevice;
import com.ruoyi.business.service.IBaseDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备信息Controller
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/business/device")
public class BaseDeviceController extends BaseController
{
    @Autowired
    private IBaseDeviceService baseDeviceService;

    /**
     * 查询设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseDevice baseDevice)
    {
        startPage();
        List<BaseDevice> list = baseDeviceService.selectBaseDeviceList(baseDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:device:export')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseDevice baseDevice)
    {
        List<BaseDevice> list = baseDeviceService.selectBaseDeviceList(baseDevice);
        ExcelUtil<BaseDevice> util = new ExcelUtil<BaseDevice>(BaseDevice.class);
        util.exportExcel(response, list, "设备信息数据");
    }

/*    *//**
     * 获取设备信息详细信息
     *//*
    @PreAuthorize("@ss.hasPermi('business:device:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return AjaxResult.success(baseDeviceService.selectBaseDeviceByDeviceId(deviceId));
    }*/

    /**
     * 新增设备信息
     */
    @PreAuthorize("@ss.hasPermi('business:device:add')")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseDevice baseDevice)
    {
        return toAjax(baseDeviceService.insertBaseDevice(baseDevice));
    }

    /**
     * 修改设备信息
     */
    @PreAuthorize("@ss.hasPermi('business:device:edit')")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseDevice baseDevice)
    {
        return toAjax(baseDeviceService.updateBaseDevice(baseDevice));
    }

    /**
     * 删除设备信息
     */
    @PreAuthorize("@ss.hasPermi('business:device:remove')")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds)
    {
        return toAjax(baseDeviceService.deleteBaseDeviceByDeviceIds(deviceIds));
    }
}
