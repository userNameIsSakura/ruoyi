package com.ruoyi.business.mqtt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(MqttConfiguration.PREFIX)
public class MqttConfiguration implements ApplicationRunner {
    @Autowired
    private MqttPushClient mqttPushClient;

    public static final String PREFIX = "mqtt.broker";
    private String[] host;
    private String clientid;
    private String username;
    private String password;
    private String sensorTopic;
    private int qos;
    private int timeout;
    private int keepalive;

    @Bean
    public MqttPushClient getMqttPushClient() {
        return mqttPushClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        connect();
    }

    public void connect() {
        mqttPushClient.connect(host, clientid, username, password, timeout, keepalive, sensorTopic, qos);
    }
}
