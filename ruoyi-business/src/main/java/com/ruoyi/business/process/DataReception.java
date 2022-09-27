package com.ruoyi.business.process;

import com.ruoyi.business.service.IBaseSensorService;
import com.ruoyi.business.mqtt.MqttPushClient;
import com.ruoyi.business.service.impl.DataTransServiceImpl;
import com.ruoyi.business.util.SpringContextUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program: mqtt
 * @description: 解析报文
 * @author: fzw
 * @create: 2022-05-18 16:31
 **/
@Data
@Slf4j
@Component
@Scope("prototype")
public class DataReception implements Runnable{
    @Autowired
    MqttPushClient mqttPushClient;
    @Autowired
    IBaseSensorService iBaseSensorService;
    @Value("${mqtt.broker.sensorTopic}")
    String topic;


    private byte[] message;

    @Override
    public void run() {

        byte function = message[0];

        switch (function) {
            //解析数据包
            case (byte)(0x55 & 0xFF):{
//                String mac = turnMacString(message[1],message[2],message[3],message[4],message[5],message[6]);
                String mac = turnMacString(Arrays.copyOfRange(message,1,7));
                Long startingTimeStamp = Long.valueOf((message[7] & 0xff) * 256 * 256 * 256 + (message[8] & 0xff) * 256 * 256 + (message[9] & 0xff) * 256 +(message[10] & 0xff)) * 1000l;
                Long terminationOfTimestamp = Long.valueOf((message[11] & 0xff) * 256 * 256 * 256 + (message[12] & 0xff) * 256 * 256 + (message[13] & 0xff) * 256 +(message[14] & 0xff) ) * 1000l;
//                String deviceCode = turnString(message[16],message[17],message[18],message[19],message[20],message[21],message[22],message[23],message[24],message[25],message[26],message[27],message[28]);
                String deviceCode = turnString(Arrays.copyOfRange(message,16,29));
                int dataType = message[29] & 0xff;
                int dataFrequency = (message[30] & 0xff);
                int totalLengthOfData = (message[31] & 0xff) * 256 + (message[32] & 0xff);

                DataTransServiceImpl bean = SpringContextUtils.getBean(DataTransServiceImpl.class);
                bean.insertMes(mac,deviceCode,startingTimeStamp,terminationOfTimestamp,dataType,dataFrequency,totalLengthOfData,message);


                break;
            }
        }
    }


    private String turnMacString(byte... n)
    {

        StringBuilder mac = new StringBuilder();
        for (int i = 0; i < n.length; i++) {
            mac.append(Integer.toHexString(n[i] & 0xff)).append(":");
        }
        return mac.toString().substring(0,mac.toString().length()-1);
    }

    public static String turnString(byte... tem)
    {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < tem.length; i++) {
            ans.append(Integer.toHexString(tem[i] & 0xff));
        }
        return ans.toString();
    }


}

