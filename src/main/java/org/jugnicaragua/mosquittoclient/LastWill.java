package org.jugnicaragua.mosquittoclient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class LastWill {
    private static final String HOME_ID = "498d787c-bf3a-49ef-8f64-fb76bc72678f";

    // Code a bip in the buzzer
    public static void main(String[] args) throws MqttException, InterruptedException {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org", MqttClient.generateClientId());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setWill(HOME_ID + "/buzzer/play", "long-bip".getBytes(), 1, false);

        client.connect(options);

        while (true) {
            client.publish(HOME_ID + "/buzzer/play",
                    "bip".getBytes(),
                    1,
                    false);
            System.out.println("Message published");
            Thread.sleep(1000); // Wait for 1 second
        }
    }

}
