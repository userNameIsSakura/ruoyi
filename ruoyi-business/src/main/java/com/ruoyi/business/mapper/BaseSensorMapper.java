package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BaseSensor;

/**
 * 传感器信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-09-21
 */
public interface BaseSensorMapper
{

    public BaseSensor selectBaseSensorBySensorType(int sensorType);

    /**
     * 查询传感器信息列表
     *
     * @param baseSensor 传感器信息
     * @return 传感器信息集合
     */
    public List<BaseSensor> selectBaseSensorList(BaseSensor baseSensor);

    /**
     * 新增传感器信息
     *
     * @param baseSensor 传感器信息
     * @return 结果
     */
    public int insertBaseSensor(BaseSensor baseSensor);

    /**
     * 修改传感器信息
     *
     * @param baseSensor 传感器信息
     * @return 结果
     */
    public int updateBaseSensor(BaseSensor baseSensor);

    /**
     * 删除传感器信息
     *
     * @param sensorId 传感器信息主键
     * @return 结果
     */
    public int deleteBaseSensorBySensorId(Long sensorId);

    /**
     * 批量删除传感器信息
     *
     * @param sensorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseSensorBySensorIds(Long[] sensorIds);
}
