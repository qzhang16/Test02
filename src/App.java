


import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        //     Connection connection = null;
        //   try {

        //      ConnectionFactory cf = new ActiveMQConnectionFactory();

        //      connection = cf.createConnection();

        //      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //      Queue queue = session.createQueue("exampleQueue");

        //      MessageProducer producer = session.createProducer(queue);

        //      TextMessage message = session.createTextMessage("This is a text message");

        //      System.out.println("Sent message: " + message.getText());

        //      producer.send(message);

        //      MessageConsumer messageConsumer = session.createConsumer(queue);

        //      connection.start();

        //      TextMessage messageReceived = (TextMessage) messageConsumer.receive(5000);

        //      System.out.println("Received message: " + messageReceived.getText());
        //   } finally {
        //       if (connection != null) {
        //           connection.close();
        //       }

        //   }

InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("queue/queue01");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()){
			jmsContext.createProducer().send(queue,"Arise Awake and stop not till the goal is reached");
			String messageReceived = jmsContext.createConsumer(queue).receiveBody(String.class);
			System.out.println(messageReceived);
		}

    }
}
