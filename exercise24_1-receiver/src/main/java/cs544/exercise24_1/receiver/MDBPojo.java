package cs544.exercise24_1.receiver;

import javax.jms.*;
import cs544.exercise24_1.sender.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class MDBPojo implements MessageListener {
	private Calculator calculator;

	public MDBPojo(Calculator calculator) {
		this.calculator = calculator;
	}

    public synchronized void onMessage(Message message){
    	ObjectMessage objmessage = (ObjectMessage)message;
    	try {
			String command = (String)objmessage.getObject();
			System.out.println(calculator.enter(command));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

