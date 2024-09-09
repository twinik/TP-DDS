package ar.edu.utn.frba.dds.brokers;

import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@AllArgsConstructor
public class BrokerSubscriber {
    private String topic;
    private String broker;
    private String clientId;
    private IMqttMessageListener receptor;

    public void listen() {
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(this.broker, this.clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setAutomaticReconnect(true);
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval(0);

            sampleClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("Mqtt Connection Lost!");
//          System.out.println(throwable.toString());
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });

            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            System.out.println("Subscribe to topic");
            sampleClient.subscribe(this.topic, this.receptor);

            System.out.println("Right! We are subscribed");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
