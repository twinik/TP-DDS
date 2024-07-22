package ar.edu.utn.frba.dds.brokers;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscribeExample {
  private static class SimpleMessageReceptor implements IMqttMessageListener {
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
      System.out.println(mqttMessage.toString());
    }
  }

  public static void main(String[] args) throws InterruptedException {
    SimpleMessageReceptor receptor = new SimpleMessageReceptor();
    BrokerSubscriber brokerSubscriber = new BrokerSubscriber("dds-grupo07-ma-ma", "tcp://broker.hivemq.com:1883", "cliente", receptor);
    brokerSubscriber.listen();
  }
}
