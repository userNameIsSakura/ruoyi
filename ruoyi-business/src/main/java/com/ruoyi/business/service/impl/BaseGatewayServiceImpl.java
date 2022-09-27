package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BaseGatewayMapper;
import com.ruoyi.business.domain.BaseGateway;
import com.ruoyi.business.service.IBaseGatewayService;

/**
 * 网关信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class BaseGatewayServiceImpl implements IBaseGatewayService
{
    @Autowired
    private BaseGatewayMapper baseGatewayMapper;


    @Override
    public BaseGateway selectBaseGatewayByGatewayMac(String gatewayMac)
    {
        return baseGatewayMapper.selectBaseGatewayByGatewayMac(gatewayMac);
    }

    @Override
    public BaseGateway selectByPatientId(int patientId) {
        return baseGatewayMapper.selectBaseGatewayByPatientId(patientId);
    }

    /**
     * 查询网关信息列表
     *
     * @param baseGateway 网关信息
     * @return 网关信息
     */
    @Override
    public List<BaseGateway> selectBaseGatewayList(BaseGateway baseGateway)
    {
        return baseGatewayMapper.selectBaseGatewayList(baseGateway);
    }

    /**
     * 新增网关信息
     *
     * @param baseGateway 网关信息
     * @return 结果
     */
    @Override
    public int insertBaseGateway(BaseGateway baseGateway)
    {
        return baseGatewayMapper.insertBaseGateway(baseGateway);
    }

    /**
     * 修改网关信息
     *
     * @param baseGateway 网关信息
     * @return 结果
     */
    @Override
    public int updateBaseGateway(BaseGateway baseGateway)
    {
        return baseGatewayMapper.updateBaseGateway(baseGateway);
    }

    /**
     * 批量删除网关信息
     *
     * @param gatewayIds 需要删除的网关信息主键
     * @return 结果
     */
    @Override
    public int deleteBaseGatewayByGatewayIds(Long[] gatewayIds)
    {
        return baseGatewayMapper.deleteBaseGatewayByGatewayIds(gatewayIds);
    }

    /**
     * 删除网关信息信息
     *
     * @param gatewayId 网关信息主键
     * @return 结果
     */
    @Override
    public int deleteBaseGatewayByGatewayId(Long gatewayId)
    {
        return baseGatewayMapper.deleteBaseGatewayByGatewayId(gatewayId);
    }
}
