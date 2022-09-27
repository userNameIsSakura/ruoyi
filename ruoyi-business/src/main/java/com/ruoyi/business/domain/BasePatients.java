package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病人列表对象 base_patients
 *
 * @author ruoyi
 * @date 2022-09-25
 */
public class BasePatients extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /*登录id*/
    private int loginId;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    /** 病人id */
    private Long patientId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 生日日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateofbirth;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phonenumber;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    public void setPatientId(Long patientId)
    {
        this.patientId = patientId;
    }

    public Long getPatientId()
    {
        return patientId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
    public void setDateofbirth(Date dateofbirth)
    {
        this.dateofbirth = dateofbirth;
    }

    public Date getDateofbirth()
    {
        return dateofbirth;
    }
    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("patientId", getPatientId())
            .append("name", getName())
            .append("gender", getGender())
            .append("dateofbirth", getDateofbirth())
            .append("phonenumber", getPhonenumber())
            .append("address", getAddress())
            .append("remark", getRemark())
            .toString();
    }
}
