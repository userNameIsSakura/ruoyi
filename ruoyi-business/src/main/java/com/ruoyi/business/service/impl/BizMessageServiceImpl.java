package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizMessageMapper;
import com.ruoyi.business.domain.BizMessage;
import com.ruoyi.business.service.IBizMessageService;

/**
 * 业务数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
@Service
public class BizMessageServiceImpl implements IBizMessageService 
{
    @Autowired
    private BizMessageMapper bizMessageMapper;

    /**
     * 查询业务数据
     * 
     * @param messageId 业务数据主键
     * @return 业务数据
     */
    @Override
    public BizMessage selectBizMessageByMessageId(Long messageId)
    {
        return bizMessageMapper.selectBizMessageByMessageId(messageId);
    }

    /**
     * 查询业务数据列表
     * 
     * @param bizMessage 业务数据
     * @return 业务数据
     */
    @Override
    public List<BizMessage> selectBizMessageList(BizMessage bizMessage)
    {
        return bizMessageMapper.selectBizMessageList(bizMessage);
    }

    /**
     * 新增业务数据
     * 
     * @param bizMessage 业务数据
     * @return 结果
     */
    @Override
    public int insertBizMessage(BizMessage bizMessage)
    {
        return bizMessageMapper.insertBizMessage(bizMessage);
    }

    /**
     * 修改业务数据
     * 
     * @param bizMessage 业务数据
     * @return 结果
     */
    @Override
    public int updateBizMessage(BizMessage bizMessage)
    {
        return bizMessageMapper.updateBizMessage(bizMessage);
    }

    /**
     * 批量删除业务数据
     * 
     * @param messageIds 需要删除的业务数据主键
     * @return 结果
     */
    @Override
    public int deleteBizMessageByMessageIds(Long[] messageIds)
    {
        return bizMessageMapper.deleteBizMessageByMessageIds(messageIds);
    }

    /**
     * 删除业务数据信息
     * 
     * @param messageId 业务数据主键
     * @return 结果
     */
    @Override
    public int deleteBizMessageByMessageId(Long messageId)
    {
        return bizMessageMapper.deleteBizMessageByMessageId(messageId);
    }
}
