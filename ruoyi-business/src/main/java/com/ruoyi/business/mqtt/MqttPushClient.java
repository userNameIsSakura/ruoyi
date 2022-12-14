package com.ruoyi.business.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.Map;

@Component
public class MqttPushClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttPushClient.class);

    @Autowired
    private PushCallback pushCallback;

    private static MqttClient client;

    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    private static MqttConnectOptions options = new MqttConnectOptions();
    private static String mqtt_topic;
    private static int mqtt_qos;

    public static MqttClient getClient() {
        return client;
    }

    public static void setClient(MqttClient client) {
        MqttPushClient.client = client;
    }

    public void connect(String[] host, String clientID, String username, String password, int timeout, int keepalive, String topic, int qos) {

        try {
            String ip;
            Map<String, String> map = System.getenv();
            try {
                InetAddress addr;
                addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            } catch (Exception ex) {
                ip = "127.0.0.1";
            }
            if (client == null)
                client = new MqttClient(host[0], clientID + ip, new MemoryPersistence());
            mqtt_topic = topic;
            mqtt_qos = qos;
            if (client.isConnected())
                return;
            options.setServerURIs(host);
            options.setCleanSession(false);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(timeout);
            options.setKeepAliveInterval(keepalive);
            options.setMaxInflight(1000);
            MqttPushClient.setClient(client);

            try {
                client.setCallback(pushCallback);
                IMqttToken token = client.connectWithResult(options);
                token.waitForCompletion();
                System.out.println("MQTT SERVER ?????????!");
                client.subscribe(mqtt_topic, mqtt_qos);
                System.out.println("???????????????"+mqtt_topic);
            } catch (Exception e) {
                System.out.println("MQTT SERVER ????????????!");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ????????????
     * @throws Exception
     */
    public static void reConnect() throws Exception {
        if (null != client) {
            try {
                IMqttToken token = client.connectWithResult(options);
                token.waitForCompletion();
                client.subscribe(mqtt_topic, mqtt_qos);
                System.out.println("MQTT SERVER ????????????!");
            } catch (Exception e) {
                System.out.println("MQTT SERVER ????????????!");
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    public void disConnect() throws Exception {
        System.out.println("??????MQTT");
        client.disconnect();
        System.out.println("???????????????......");
        pushCallback.getCachedThreadPool().shutdown();
        System.out.println("???????????????......OK");
        client = null;
    }

    /**
     * ???????????????qos???0???????????????
     *
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic, byte[] pushMessage) {
        publish(0, false, topic, pushMessage);
    }

    /**
     * ??????
     *
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos, boolean retained, String topic, byte[] pushMessage) {
        try {
            if (!client.isConnected())
                client.connect();
            MqttMessage message = new MqttMessage();
            message.setQos(qos);
            message.setRetained(retained);
            message.setPayload(pushMessage);
            MqttTopic mTopic = MqttPushClient.getClient().getTopic(topic);
            if (null == mTopic) {
                LOGGER.error("topic not exist");
            }
            MqttDeliveryToken token;
            try {
                token = mTopic.publish(message);
                token.waitForCompletion();
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } catch (MqttException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * ?????????????????????qos?????????0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * ??????????????????
     *
     * @param topic
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            //EMQ???????????? ??????$queue/   ??????????????? topic
            //MqttPushClient.getClient().subscribe("$queue/" + topic, qos);
            MqttPushClient.getClient().subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
