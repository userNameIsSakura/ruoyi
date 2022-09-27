package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BaseGateway;

/**
 * 网关信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-09-21
 */
public interface BaseGatewayMapper
{

    public BaseGateway selectBaseGatewayByGatewayMac(String gatewayMac);
    public BaseGateway selectBaseGatewayByPatientId(int patientId);



    /**
     * 查询网关信息列表
     *
     * @param baseGateway 网关信息
     * @return 网关信息集合
     */
    public List<BaseGateway> selectBaseGatewayList(BaseGateway baseGateway);

    /**
     * 新增网关信息
     *
     * @param baseGateway 网关信息
     * @return 结果
     */
    public int insertBaseGateway(BaseGateway baseGateway);

    /**
     * 修改网关信息
     *
     * @param baseGateway 网关信息
     * @return 结果
     */
    public int updateBaseGateway(BaseGateway baseGateway);

    /**
     * 删除网关信息
     *
     * @param gatewayId 网关信息主键
     * @return 结果
     */
    public int deleteBaseGatewayByGatewayId(Long gatewayId);

    /**
     * 批量删除网关信息
     *
     * @param gatewayIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseGatewayByGatewayIds(Long[] gatewayIds);
}
