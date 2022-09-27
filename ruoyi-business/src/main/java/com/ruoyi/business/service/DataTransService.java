package com.ruoyi.business.service;

import java.util.Date;

/*
* 与数据库的连接
* */
public interface DataTransService {

    public void insertMes(String mac,String deviceCode,Long startingTimeStamp,Long terminationOfTimestamp,int dataType,int frequency,int totalLengthOfData,byte[] message);

}
