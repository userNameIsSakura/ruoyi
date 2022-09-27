package com.ruoyi.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.business.domain.BaseGateway;
import com.ruoyi.business.service.IBaseGatewayService;
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
import com.ruoyi.business.domain.BizMessage;
import com.ruoyi.business.service.IBizMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务数据Controller
 *
 * @author ruoyi
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/business/message")
public class BizMessageController extends BaseController
{
    @Autowired
    private IBizMessageService bizMessageService;
    @Autowired
    private IBaseGatewayService iBaseGatewayService;



    @GetMapping("/gateWayId/{patientId}")
    public int gateWayId(@PathVariable("patientId") int patientId)
    {

        BaseGateway baseGateway = iBaseGatewayService.selectByPatientId(patientId);
        System.out.println(baseGateway.getGatewayId());
        return baseGateway.getGatewayId().intValue();
    }

    /**
     * 查询业务数据列表
     */
    @PreAuthorize("@ss.hasPermi('business:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizMessage bizMessage)
    {
        startPage();
        List<BizMessage> list = bizMessageService.selectBizMessageList(bizMessage);
        return getDataTable(list);
    }

    /**
     * 导出业务数据列表
     */
    @PreAuthorize("@ss.hasPermi('business:message:export')")
    @Log(title = "业务数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizMessage bizMessage)
    {
        List<BizMessage> list = bizMessageService.selectBizMessageList(bizMessage);
        ExcelUtil<BizMessage> util = new ExcelUtil<BizMessage>(BizMessage.class);
        util.exportExcel(response, list, "业务数据数据");
    }




    /**
     * 获取业务数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:message:query')")
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId)
    {
        return AjaxResult.success(bizMessageService.selectBizMessageByMessageId(messageId));
    }

    /**
     * 新增业务数据
     */
    @PreAuthorize("@ss.hasPermi('business:message:add')")
    @Log(title = "业务数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizMessage bizMessage)
    {
        return toAjax(bizMessageService.insertBizMessage(bizMessage));
    }

    /**
     * 修改业务数据
     */
    @PreAuthorize("@ss.hasPermi('business:message:edit')")
    @Log(title = "业务数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizMessage bizMessage)
    {
        return toAjax(bizMessageService.updateBizMessage(bizMessage));
    }

    /**
     * 删除业务数据
     */
    @PreAuthorize("@ss.hasPermi('business:message:remove')")
    @Log(title = "业务数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds)
    {
        return toAjax(bizMessageService.deleteBizMessageByMessageIds(messageIds));
    }
}
