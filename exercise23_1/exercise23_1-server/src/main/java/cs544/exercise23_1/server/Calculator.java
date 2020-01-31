package cs544.exercise23_1.server;

public class Calculator {
    private double number1;
    private double number2;
    private char operator;

    public Calculator() {
        clear();
    }

    private void clear() {
        number1 = number2 = 0;
        operator = 0;
    }

    public synchronized double enter(String command) {
        if(command.matches("-?\\d+(\\.\\d+)?")) { // is numeric
            double number = Double.parseDouble(command);
            if(operator != 0) {
                number2 = number;
            } else
                number1 = number;
        } else if(command.charAt(0) == 'C') {
            clear();
        } else if(command.charAt(0) == '=') {
            switch (operator) {
                case '+':
                    number1 += number2;
                    break;
                case '-':
                    number1 -= number2;
                    break;
                case '*':
                    number1 *= number2;
                    break;
                case '/':
                    number1 /= number2;
                    break;
                case '%':
                    number1 %= number2;
                    break;
            }
            operator = 0;
            number2 = 0;
        } else {
            if(operator == 0)
                operator = command.charAt(0);
        }
        return number1;
    }
}
