package org.jugnicaragua.mosquittoclient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ExactPublish {
    private static final String HOME_ID = "498d787c-bf3a-49ef-8f64-fb76bc72678f";

    // HOME_ID + "display/message"
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org", MqttClient.generateClientId());
        client.connect();

        client.publish(HOME_ID + "display/message",
                "Bienvenido!".getBytes(),
                2,
                false);
        System.out.println("Message published");
        client.disconnect();
    }

}
