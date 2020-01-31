package cs544.exercise23_1.server;

public class CalculatorService implements ICalculator {
	private Calculator calculator;

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public double sendCommand(String command) {
		return calculator.enter(command);
	}
}