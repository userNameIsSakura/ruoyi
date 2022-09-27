package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BasePatients;

/**
 * 病人列表Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-25
 */
public interface BasePatientsMapper 
{
    /**
     * 查询病人列表
     * 
     * @param patientId 病人列表主键
     * @return 病人列表
     */
    public BasePatients selectBasePatientsByPatientId(Long patientId);

    /**
     * 查询病人列表列表
     * 
     * @param basePatients 病人列表
     * @return 病人列表集合
     */
    public List<BasePatients> selectBasePatientsList(BasePatients basePatients);

    /**
     * 新增病人列表
     * 
     * @param basePatients 病人列表
     * @return 结果
     */
    public int insertBasePatients(BasePatients basePatients);

    /**
     * 修改病人列表
     * 
     * @param basePatients 病人列表
     * @return 结果
     */
    public int updateBasePatients(BasePatients basePatients);

    /**
     * 删除病人列表
     * 
     * @param patientId 病人列表主键
     * @return 结果
     */
    public int deleteBasePatientsByPatientId(Long patientId);

    /**
     * 批量删除病人列表
     * 
     * @param patientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBasePatientsByPatientIds(Long[] patientIds);
}
