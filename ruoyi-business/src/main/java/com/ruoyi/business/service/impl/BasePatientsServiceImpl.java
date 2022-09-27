package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BasePatientsMapper;
import com.ruoyi.business.domain.BasePatients;
import com.ruoyi.business.service.IBasePatientsService;

/**
 * 病人列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-25
 */
@Service
public class BasePatientsServiceImpl implements IBasePatientsService 
{
    @Autowired
    private BasePatientsMapper basePatientsMapper;

    /**
     * 查询病人列表
     * 
     * @param patientId 病人列表主键
     * @return 病人列表
     */
    @Override
    public BasePatients selectBasePatientsByPatientId(Long patientId)
    {
        return basePatientsMapper.selectBasePatientsByPatientId(patientId);
    }

    /**
     * 查询病人列表列表
     * 
     * @param basePatients 病人列表
     * @return 病人列表
     */
    @Override
    public List<BasePatients> selectBasePatientsList(BasePatients basePatients)
    {
        return basePatientsMapper.selectBasePatientsList(basePatients);
    }

    /**
     * 新增病人列表
     * 
     * @param basePatients 病人列表
     * @return 结果
     */
    @Override
    public int insertBasePatients(BasePatients basePatients)
    {
        return basePatientsMapper.insertBasePatients(basePatients);
    }

    /**
     * 修改病人列表
     * 
     * @param basePatients 病人列表
     * @return 结果
     */
    @Override
    public int updateBasePatients(BasePatients basePatients)
    {
        return basePatientsMapper.updateBasePatients(basePatients);
    }

    /**
     * 批量删除病人列表
     * 
     * @param patientIds 需要删除的病人列表主键
     * @return 结果
     */
    @Override
    public int deleteBasePatientsByPatientIds(Long[] patientIds)
    {
        return basePatientsMapper.deleteBasePatientsByPatientIds(patientIds);
    }

    /**
     * 删除病人列表信息
     * 
     * @param patientId 病人列表主键
     * @return 结果
     */
    @Override
    public int deleteBasePatientsByPatientId(Long patientId)
    {
        return basePatientsMapper.deleteBasePatientsByPatientId(patientId);
    }
}
