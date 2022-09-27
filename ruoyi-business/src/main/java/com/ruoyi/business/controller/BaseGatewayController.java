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
import com.ruoyi.business.domain.BaseGateway;
import com.ruoyi.business.service.IBaseGatewayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 网关信息Controller
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/business/gateway")
public class BaseGatewayController extends BaseController
{
    @Autowired
    private IBaseGatewayService baseGatewayService;

    /**
     * 查询网关信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:gateway:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseGateway baseGateway)
    {
        startPage();
        List<BaseGateway> list = baseGatewayService.selectBaseGatewayList(baseGateway);
        return getDataTable(list);
    }

    /**
     * 导出网关信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:gateway:export')")
    @Log(title = "网关信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseGateway baseGateway)
    {
        List<BaseGateway> list = baseGatewayService.selectBaseGatewayList(baseGateway);
        ExcelUtil<BaseGateway> util = new ExcelUtil<BaseGateway>(BaseGateway.class);
        util.exportExcel(response, list, "网关信息数据");
    }

/*
    */
/**
     * 获取网关信息详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('business:gateway:query')")
    @GetMapping(value = "/{gatewayId}")
    public AjaxResult getInfo(@PathVariable("gatewayId") Long gatewayId)
    {
        return AjaxResult.success(baseGatewayService.selectBaseGatewayByGatewayId(gatewayId));
    }
*/

    /**
     * 新增网关信息
     */
    @PreAuthorize("@ss.hasPermi('business:gateway:add')")
    @Log(title = "网关信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseGateway baseGateway)
    {
        return toAjax(baseGatewayService.insertBaseGateway(baseGateway));
    }

    /**
     * 修改网关信息
     */
    @PreAuthorize("@ss.hasPermi('business:gateway:edit')")
    @Log(title = "网关信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseGateway baseGateway)
    {
        return toAjax(baseGatewayService.updateBaseGateway(baseGateway));
    }

    /**
     * 删除网关信息
     */
    @PreAuthorize("@ss.hasPermi('business:gateway:remove')")
    @Log(title = "网关信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{gatewayIds}")
    public AjaxResult remove(@PathVariable Long[] gatewayIds)
    {
        return toAjax(baseGatewayService.deleteBaseGatewayByGatewayIds(gatewayIds));
    }
}
