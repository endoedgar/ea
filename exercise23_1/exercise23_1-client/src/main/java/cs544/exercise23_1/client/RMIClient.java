package cs544.exercise23_1.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cs544.exercise23_1.server.ICalculator;
import cs544.exercise23_1.server.Person;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RMIClient {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfigclient.xml");
		ICalculator remoteServer = context
				.getBean("helloserver", ICalculator.class);
		BufferedReader reader =
				new BufferedReader(new InputStreamReader(System.in));
		String command;
		StopWatch stopWatch = new StopWatch();
		double time;
		do {
			System.out.print("Type number, operator or exit: ");
			command = reader.readLine();
			if(command.equalsIgnoreCase("exit"))
				break;
			stopWatch.start();
			double result = remoteServer.sendCommand(command);
			stopWatch.stop();
			System.out.println("Receiving result: " + result + " (it took " + stopWatch.getLastTaskTimeMillis() + "ms)");
		} while(true);
	}

}
