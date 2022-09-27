package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BaseSensorMapper;
import com.ruoyi.business.domain.BaseSensor;
import com.ruoyi.business.service.IBaseSensorService;

/**
 * 传感器信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class BaseSensorServiceImpl implements IBaseSensorService
{
    @Autowired
    private BaseSensorMapper baseSensorMapper;


    @Override
    public BaseSensor selectBaseSensorBySensorType(int sensorType)
    {
        return baseSensorMapper.selectBaseSensorBySensorType(sensorType);
    }

    /**
     * 查询传感器信息列表
     *
     * @param baseSensor 传感器信息
     * @return 传感器信息
     */
    @Override
    public List<BaseSensor> selectBaseSensorList(BaseSensor baseSensor)
    {
        return baseSensorMapper.selectBaseSensorList(baseSensor);
    }

    /**
     * 新增传感器信息
     *
     * @param baseSensor 传感器信息
     * @return 结果
     */
    @Override
    public int insertBaseSensor(BaseSensor baseSensor)
    {
        return baseSensorMapper.insertBaseSensor(baseSensor);
    }

    /**
     * 修改传感器信息
     *
     * @param baseSensor 传感器信息
     * @return 结果
     */
    @Override
    public int updateBaseSensor(BaseSensor baseSensor)
    {
        return baseSensorMapper.updateBaseSensor(baseSensor);
    }

    /**
     * 批量删除传感器信息
     *
     * @param sensorIds 需要删除的传感器信息主键
     * @return 结果
     */
    @Override
    public int deleteBaseSensorBySensorIds(Long[] sensorIds)
    {
        return baseSensorMapper.deleteBaseSensorBySensorIds(sensorIds);
    }

    /**
     * 删除传感器信息信息
     *
     * @param sensorId 传感器信息主键
     * @return 结果
     */
    @Override
    public int deleteBaseSensorBySensorId(Long sensorId)
    {
        return baseSensorMapper.deleteBaseSensorBySensorId(sensorId);
    }
}
