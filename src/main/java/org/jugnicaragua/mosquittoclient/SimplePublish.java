package org.jugnicaragua.mosquittoclient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class SimplePublish {
    private static final String HOME_ID = "498d787c-bf3a-49ef-8f64-fb76bc72678f";

    // HOME_ID + "/fan/power"
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org", MqttClient.generateClientId());
        client.connect();

        client.publish(HOME_ID + "/fan/power",
                "ON".getBytes(),
                1,
                false);
        System.out.println("Message published");
        client.disconnect();
    }

}
