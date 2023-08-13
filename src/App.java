

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Connection connection = null;
      try {

         ConnectionFactory cf = new ActiveMQConnectionFactory();

         connection = cf.createConnection();

         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         Queue queue = session.createQueue("exampleQueue");

         MessageProducer producer = session.createProducer(queue);

         TextMessage message = session.createTextMessage("This is a text message");

         System.out.println("Sent message: " + message.getText());

         producer.send(message);

         MessageConsumer messageConsumer = session.createConsumer(queue);

         connection.start();

         TextMessage messageReceived = (TextMessage) messageConsumer.receive(5000);

         System.out.println("Received message: " + messageReceived.getText());
      } finally {
         if (connection != null) {
            connection.close();
         }
      }
    }
}
