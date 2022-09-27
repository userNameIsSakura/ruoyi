package com.ruoyi.business.service.impl;


import com.ruoyi.business.domain.BaseDevice;
import com.ruoyi.business.domain.BaseGateway;
import com.ruoyi.business.domain.BaseSensor;
import com.ruoyi.business.domain.BizMessage;
import com.ruoyi.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Scope("prototype")
public class DataTransServiceImpl implements DataTransService {


    @Autowired
    IBaseSensorService iBaseSensorService;
    @Autowired
    IBaseGatewayService iBaseGatewayService;
    @Autowired
    IBaseDeviceService iBaseDeviceService;
    @Autowired
    IBizMessageService iBizMessageService;


    @Override
    public void insertMes(String mac,String deviceCode,Long startingTimeStamp,Long terminationOfTimestamp,int dataType,int frequency,int totalLengthOfData,byte[] message) {
        BaseSensor baseSensor = iBaseSensorService.selectBaseSensorBySensorType(dataType);

        int dataLength = Math.toIntExact(baseSensor.getDataLength());

        /*数据数目*/
        int number = totalLengthOfData / dataLength;
        int now = 33;

        /*通过mac查gateway id*/
        BaseGateway baseGateway = iBaseGatewayService.selectBaseGatewayByGatewayMac(mac);
        /*通过设备编号查找device id*/
        BaseDevice baseDevice = iBaseDeviceService.selectBaseDeviceByDeviceCode(deviceCode);


        BizMessage bizMessage = new BizMessage(baseGateway.getGatewayId(), baseSensor.getSensorId(), new Date(startingTimeStamp), new Date(terminationOfTimestamp), Long.valueOf(frequency), baseDevice.getDeviceId());

        switch (dataType)
        {
            /*心率 血氧饱和度*/
            case 1,4 -> {

                for (int i = 0; i < number; i++) {
                    bizMessage.setMessageValue(String.valueOf(message[now++]&0xff));
                    iBizMessageService.insertBizMessage(bizMessage);
                    System.out.println("插入成功14");
                }

            }

            /*脉搏波*/
            case 2 -> {
                for (int i = 0; i < number; i++) {
                    bizMessage.setMessageValue(String.valueOf((message[now]&0xff)+((message[now+1]&0xff)*256)));
                    now+=2;
                    iBizMessageService.insertBizMessage(bizMessage);
                    System.out.println("插入成功2");
                }
            }

            /*温度*/
            case 5 -> {
                for (int i = 0; i < number; i++) {
                    bizMessage.setMessageValue(String.valueOf( ((float) ((message[now]&0xff)+((message[now+1]&0xff)*256))/10)));
                    now+=2;
                    iBizMessageService.insertBizMessage(bizMessage);
                    System.out.println("插入成功5");

                }
            }

            /*加速度*/
            case 3 -> {

                for (int i = 0; i < number; i++) {
                    int x = (message[now] & 0xff) + ((message[now + 1] & 0xff) * 256 );
                    int y = (message[now + 2] & 0xff) + ((message[now + 3] & 0xff) * 256 );
                    int z = (message[now + 4] & 0xff) + ((message[now + 5] & 0xff) * 256 );
                    now+=6;
                    bizMessage.setMessageValue("("+String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(z)+")");
                    iBizMessageService.insertBizMessage(bizMessage);
                    System.out.println("插入成功3");

                }


            }
        }


    }
}
