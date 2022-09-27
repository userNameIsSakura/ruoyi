package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 业务数据对象 biz_message
 *
 * @author ruoyi
 * @date 2022-09-22
 */
public class BizMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息id */
    private Long messageId;

    public BizMessage(Long messageId, Long gatewayId, Long sensorId, Date messageStart, Date messageEnd, String messageValue, Long messageFrequency, Long deviceId) {
        this.messageId = messageId;
        this.gatewayId = gatewayId;
        this.sensorId = sensorId;
        this.messageStart = messageStart;
        this.messageEnd = messageEnd;
        this.messageValue = messageValue;
        this.messageFrequency = messageFrequency;
        this.deviceId = deviceId;
    }

    public BizMessage() {
    }

    public BizMessage(Long gatewayId, Long sensorId, Date messageStart, Date messageEnd, Long messageFrequency, Long deviceId) {
        this.gatewayId = gatewayId;
        this.sensorId = sensorId;
        this.messageStart = messageStart;
        this.messageEnd = messageEnd;
        this.messageFrequency = messageFrequency;
        this.deviceId = deviceId;
    }

    /** 网关id */
    @Excel(name = "网关id")
    private Long gatewayId;

    /** 传感器id */
    @Excel(name = "传感器id")
    private Long sensorId;

    /** 起始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "起始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date messageStart;

    /** 终止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "终止时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date messageEnd;

    /** 监测数据 */
    @Excel(name = "监测数据")
    private String messageValue;

    /** 数据频率 */
    @Excel(name = "数据频率")
    private Long messageFrequency;

    /** 设备id */
    @Excel(name = "设备id")
    private Long deviceId;

    public void setMessageId(Long messageId)
    {
        this.messageId = messageId;
    }

    public Long getMessageId()
    {
        return messageId;
    }
    public void setGatewayId(Long gatewayId)
    {
        this.gatewayId = gatewayId;
    }

    public Long getGatewayId()
    {
        return gatewayId;
    }
    public void setSensorId(Long sensorId)
    {
        this.sensorId = sensorId;
    }

    public Long getSensorId()
    {
        return sensorId;
    }
    public void setMessageStart(Date messageStart)
    {
        this.messageStart = messageStart;
    }

    public Date getMessageStart()
    {
        return messageStart;
    }
    public void setMessageEnd(Date messageEnd)
    {
        this.messageEnd = messageEnd;
    }

    public Date getMessageEnd()
    {
        return messageEnd;
    }
    public void setMessageValue(String messageValue)
    {
        this.messageValue = messageValue;
    }

    public String getMessageValue()
    {
        return messageValue;
    }
    public void setMessageFrequency(Long messageFrequency)
    {
        this.messageFrequency = messageFrequency;
    }

    public Long getMessageFrequency()
    {
        return messageFrequency;
    }
    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("gatewayId", getGatewayId())
            .append("sensorId", getSensorId())
            .append("messageStart", getMessageStart())
            .append("messageEnd", getMessageEnd())
            .append("messageValue", getMessageValue())
            .append("messageFrequency", getMessageFrequency())
            .append("remark", getRemark())
            .append("deviceId", getDeviceId())
            .toString();
    }
}
