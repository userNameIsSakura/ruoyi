package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BaseDeviceMapper;
import com.ruoyi.business.domain.BaseDevice;
import com.ruoyi.business.service.IBaseDeviceService;

/**
 * 设备信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class BaseDeviceServiceImpl implements IBaseDeviceService
{
    @Autowired
    private BaseDeviceMapper baseDeviceMapper;


    @Override
    public BaseDevice selectBaseDeviceByGatewayId(Long gatewayId)
    {
        return baseDeviceMapper.selectBaseDeviceByGatewayId(gatewayId);
    }

    @Override
    public BaseDevice selectBaseDeviceByDeviceCode(String deviceCode) {
        return baseDeviceMapper.selectBaseDeviceByDeviceCode(deviceCode);
    }

    /**
     * 查询设备信息列表
     *
     * @param baseDevice 设备信息
     * @return 设备信息
     */
    @Override
    public List<BaseDevice> selectBaseDeviceList(BaseDevice baseDevice)
    {
        return baseDeviceMapper.selectBaseDeviceList(baseDevice);
    }

    /**
     * 新增设备信息
     *
     * @param baseDevice 设备信息
     * @return 结果
     */
    @Override
    public int insertBaseDevice(BaseDevice baseDevice)
    {
        return baseDeviceMapper.insertBaseDevice(baseDevice);
    }

    /**
     * 修改设备信息
     *
     * @param baseDevice 设备信息
     * @return 结果
     */
    @Override
    public int updateBaseDevice(BaseDevice baseDevice)
    {
        return baseDeviceMapper.updateBaseDevice(baseDevice);
    }

    /**
     * 批量删除设备信息
     *
     * @param deviceIds 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteBaseDeviceByDeviceIds(Long[] deviceIds)
    {
        return baseDeviceMapper.deleteBaseDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除设备信息信息
     *
     * @param deviceId 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteBaseDeviceByDeviceId(Long deviceId)
    {
        return baseDeviceMapper.deleteBaseDeviceByDeviceId(deviceId);
    }
}
