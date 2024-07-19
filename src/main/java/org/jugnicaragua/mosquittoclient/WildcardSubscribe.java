package org.jugnicaragua.mosquittoclient;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;

public class WildcardSubscribe {
    private static final String HOME_ID = "498d787c-bf3a-49ef-8f64-fb76bc72678f";

    //HOME_ID + "/temp_hum/temperature/status"
    //HOME_ID + "/temp_hum/humidity/status"
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org", MqttClient.generateClientId());
        client.connect();

//        client.subscribe(HOME_ID + "/temp_hum/temperature/status", (topic, message) -> {
//            System.out.println("Message: " + new String(message.getPayload()));
//        });
//
//        client.subscribe(HOME_ID + "/temp_hum/humidity/status", (topic, message) -> {
//            System.out.println("Message: " + new String(message.getPayload()));
//        });

//        String[] topics = {
//                HOME_ID + "/temp_hum/temperature/status",
//                HOME_ID + "/temp_hum/humidity/status"
//        };
//        client.subscribe(topics);
//        client.setCallback(new Callback());

//        client.subscribe(HOME_ID + "/+/status", (topic, message) -> {
//            System.out.println("Message: " + new String(message.getPayload()));
//        });

        client.subscribe(HOME_ID + "/temp_hum/#",(topic, message) -> {
            System.out.println("Message: " + new String(message.getPayload()));
        });
    }

    private void processMessage(String topic, MqttMessage message) {
        System.out.println("Message: " + new String(message.getPayload()));
    }

}

class Callback implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message: " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Delivery complete");
    }
}