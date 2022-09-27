package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BaseDevice;

/**
 * 设备信息Service接口
 *
 * @author ruoyi
 * @date 2022-09-21
 */
public interface IBaseDeviceService
{

    /**
     * 查询设备由网关设备id
     *
     * @param gatewayId 网关id
     * @return {@link BaseDevice}
     */
    public BaseDevice selectBaseDeviceByGatewayId(Long gatewayId);


    public BaseDevice selectBaseDeviceByDeviceCode(String deviceCode);


    /**
     * 查询设备信息列表
     *
     * @param baseDevice 设备信息
     * @return 设备信息集合
     */
    public List<BaseDevice> selectBaseDeviceList(BaseDevice baseDevice);

    /**
     * 新增设备信息
     *
     * @param baseDevice 设备信息
     * @return 结果
     */
    public int insertBaseDevice(BaseDevice baseDevice);

    /**
     * 修改设备信息
     *
     * @param baseDevice 设备信息
     * @return 结果
     */
    public int updateBaseDevice(BaseDevice baseDevice);

    /**
     * 批量删除设备信息
     *
     * @param deviceIds 需要删除的设备信息主键集合
     * @return 结果
     */
    public int deleteBaseDeviceByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备信息信息
     *
     * @param deviceId 设备信息主键
     * @return 结果
     */
    public int deleteBaseDeviceByDeviceId(Long deviceId);
}
