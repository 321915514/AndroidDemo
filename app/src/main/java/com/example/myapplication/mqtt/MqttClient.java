package com.example.myapplication.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 //  * The default keep alive interval in seconds if one is not specified
 //	 */
//
//	public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
//	/**
//	 * The default connection timeout in seconds if one is not specified
//	 */
//	public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
//	/**
//     * The default max inflight if one is not specified
//     */
//    public static final int MAX_INFLIGHT_DEFAULT = 10;
//	/**
//	 * The default clean session setting if one is not specified
//	 */
//	public static final boolean CLEAN_SESSION_DEFAULT = true;
//	/**
//	 * The default MqttVersion is 3.1.1 first, dropping back to 3.1 if that fails
//	 */
//	public static final int MQTT_VERSION_DEFAULT = 0;
//	/**
//	 * Mqtt Version 3.1
//	 */
//	public static final int MQTT_VERSION_3_1 = 3;
//	/**
//	 * Mqtt Version 3.1.1
//	 */
//	public static final int MQTT_VERSION_3_1_1 = 4;
//
//	protected static final int URI_TYPE_TCP = 0;
//	protected static final int URI_TYPE_SSL = 1;
//	protected static final int URI_TYPE_LOCAL = 2;
//	protected static final int URI_TYPE_WS = 3;
//	protected static final int URI_TYPE_WSS = 4;
 //
public class MqttClient {
    private static org.eclipse.paho.client.mqttv3.MqttClient mqttClient = null;
    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;
    static {
        init("123");
    }
    public static void init(String clientID) {
        // 初始化
        mqttConnectOptions = new MqttConnectOptions();
        // 初始化连接对象
        //mqttClient
        if (mqttConnectOptions != null) {
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(30);
            memoryPersistence = new MemoryPersistence();
            if (null != memoryPersistence && null != clientID) {
                try {
                    mqttClient = new org.eclipse.paho.client.mqttv3.MqttClient("tcp://127.0.0.1:1883", clientID, memoryPersistence);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("TAG", "mqttConnectOptions is null" + mqttConnectOptions);
            }
            if (null != mqttClient) {
                if (!mqttClient.isConnected()) {
                    MqttRecieveCallback mqttRecieveCallback = new MqttRecieveCallback();
                    mqttClient.setCallback(mqttRecieveCallback);
                    try {
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
    public void publishMessage(String pubTopic,String message,int Qos){
        if (mqttClient.isConnected()) {
            Log.e("TAG", mqttClient.getClientId());
            MqttMessage mqttMessage = new MqttMessage();
            //	private boolean mutable = true;
            //	private byte[] payload;
            //	private int qos = 1;
            //	private boolean retained = false;
            //	private boolean dup = false;
            //	private int messageId;
            mqttMessage.setQos(Qos);
            mqttMessage.setPayload(message.getBytes());
            MqttTopic topic = mqttClient.getTopic(pubTopic);
            try {
                topic.publish(mqttMessage);
                Log.e("TAG","发送消息success");
                // topic 发送消息
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
    public void subTopic(String topic){
        try {
            mqttClient.subscribe(topic,1);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
