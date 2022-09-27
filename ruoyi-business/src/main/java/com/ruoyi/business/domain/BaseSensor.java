package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 传感器信息对象 base_sensor
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public class BaseSensor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long sensorId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sensorType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sensorUnit;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sensorName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long dataLength;

    public void setSensorId(Long sensorId) 
    {
        this.sensorId = sensorId;
    }

    public Long getSensorId() 
    {
        return sensorId;
    }
    public void setSensorType(String sensorType) 
    {
        this.sensorType = sensorType;
    }

    public String getSensorType() 
    {
        return sensorType;
    }
    public void setSensorUnit(String sensorUnit) 
    {
        this.sensorUnit = sensorUnit;
    }

    public String getSensorUnit() 
    {
        return sensorUnit;
    }
    public void setSensorName(String sensorName) 
    {
        this.sensorName = sensorName;
    }

    public String getSensorName() 
    {
        return sensorName;
    }
    public void setDataLength(Long dataLength) 
    {
        this.dataLength = dataLength;
    }

    public Long getDataLength() 
    {
        return dataLength;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sensorId", getSensorId())
            .append("sensorType", getSensorType())
            .append("sensorUnit", getSensorUnit())
            .append("sensorName", getSensorName())
            .append("dataLength", getDataLength())
            .append("remark", getRemark())
            .toString();
    }
}
