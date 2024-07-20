package org.jugnicaragua.mosquittoclient;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ItsRaining {
    private static final String HOME_ID = "498d787c-bf3a-49ef-8f64-fb76bc72678f";

    // HOME_ID + "/window/position"
    // HOME_ID + "/raindrop/status"
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org", MqttClient.generateClientId());
        client.connect();

        client.subscribe(HOME_ID + "/raindrop/status", (topic, message) -> {
            System.out.println("Message: " + new String(message.getPayload()));
            int water = JsonParser.parseString(new String(message.getPayload()))
                    .getAsJsonObject().get("water").getAsInt();

            if (water > 1000) {
                System.out.println("It's Raining");
                client.publish(HOME_ID + "/window/position",
                        "CLOSE".getBytes(),
                        1,
                        false);
            } else {
                System.out.println("It's not Raining");
                client.publish(HOME_ID + "/window/position",
                        "OPEN".getBytes(),
                        1,
                        false);
            }
        });
    }
}
