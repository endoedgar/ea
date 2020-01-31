package cs544.exercise24_1.sender;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JMSSenderApp {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfigsender.xml");
		BufferedReader reader =
				new BufferedReader(new InputStreamReader(System.in));
		String command;
		JMSSender jmssender = context.getBean("jmsSender", JMSSender.class);
		do {
			System.out.print("Type number, operator or exit: ");
			command = reader.readLine();
			if(command.equalsIgnoreCase("exit"))
				break;
			jmssender.send(command);
		} while(true);
		context.close();
	}
}
