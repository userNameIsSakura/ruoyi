package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizMessage;

/**
 * 业务数据Service接口
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public interface IBizMessageService 
{
    /**
     * 查询业务数据
     * 
     * @param messageId 业务数据主键
     * @return 业务数据
     */
    public BizMessage selectBizMessageByMessageId(Long messageId);

    /**
     * 查询业务数据列表
     * 
     * @param bizMessage 业务数据
     * @return 业务数据集合
     */
    public List<BizMessage> selectBizMessageList(BizMessage bizMessage);

    /**
     * 新增业务数据
     * 
     * @param bizMessage 业务数据
     * @return 结果
     */
    public int insertBizMessage(BizMessage bizMessage);

    /**
     * 修改业务数据
     * 
     * @param bizMessage 业务数据
     * @return 结果
     */
    public int updateBizMessage(BizMessage bizMessage);

    /**
     * 批量删除业务数据
     * 
     * @param messageIds 需要删除的业务数据主键集合
     * @return 结果
     */
    public int deleteBizMessageByMessageIds(Long[] messageIds);

    /**
     * 删除业务数据信息
     * 
     * @param messageId 业务数据主键
     * @return 结果
     */
    public int deleteBizMessageByMessageId(Long messageId);
}
