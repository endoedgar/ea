package cs544.exercise24_2.receiver;

import javax.jms.*;

public class TaxService implements MessageListener {
    public synchronized void onMessage(Message message){
    	ObjectMessage objmessage = (ObjectMessage)message;
    	try {
			String messageString = (String)objmessage.getObject();
			System.out.println("Received JMS Message: " + messageString);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

