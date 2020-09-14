package com.example.myapplication;

import com.example.myapplication.mqtt.MqttClient;
import com.example.myapplication.mqtt.MyMqttRecieveMessage;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    MqttClient client = new MqttClient();
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @org.junit.Test
    public void mqttTest(){
        while (true){
            try {
                Thread.sleep(3000);
                client.publishMessage("world/1234","hello",1);
                System.out.println("qqq");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    @org.junit.Test
    public void recievetest(){

        MyMqttRecieveMessage.recieve("world/1234");
        System.out.println("recieve");
        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}