package org.jugnicaragua.mosquittoclient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class RetainPublish {
    private static final String HOME_ID = "498d787c-bf3a-49ef-8f64-fb76bc72678f";

    // HOME_ID + "/fan/power"
    //"     JCONF         Dominicana   "
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org", MqttClient.generateClientId());
        client.connect();

        client.publish(HOME_ID + "display/message",
                ("     JCONF         Dominicana   ").getBytes(),
                1,
                true);

        System.out.println("Message published");
        client.disconnect();
    }

}
