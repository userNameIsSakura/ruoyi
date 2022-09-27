package com.ruoyi.business.mqtt;

import com.ruoyi.business.process.DataReception;
import com.ruoyi.business.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class PushCallback implements MqttCallback {
    //根据cpu核心数创建线程池线程数
    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public ExecutorService getCachedThreadPool() {
        return cachedThreadPool;
    }

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        try {
            System.out.println(cause.getMessage());
            System.out.println(Arrays.toString(cause.getCause().getStackTrace()));
            log.error("连接断开，重连");
            MqttPushClient.reConnect();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info(token+"成功交付");
    }

    // 消息到达
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        byte[] payload = message.getPayload();

        if((payload[0] & 0xff) == 0x55)
            System.out.println("数据包到达");
        // 通过 applicationContext 创建对象
        DataReception reception= SpringContextUtils.getBean(DataReception.class);
        reception.setMessage(payload);

        cachedThreadPool.submit(reception);
    }

}
