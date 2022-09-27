package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 网关信息对象 base_gateway
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public class BaseGateway extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long gatewayId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long patientId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String gatewayMac;

    public void setGatewayId(Long gatewayId) 
    {
        this.gatewayId = gatewayId;
    }

    public Long getGatewayId() 
    {
        return gatewayId;
    }
    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setGatewayMac(String gatewayMac) 
    {
        this.gatewayMac = gatewayMac;
    }

    public String getGatewayMac() 
    {
        return gatewayMac;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gatewayId", getGatewayId())
            .append("patientId", getPatientId())
            .append("remark", getRemark())
            .append("gatewayMac", getGatewayMac())
            .toString();
    }
}
