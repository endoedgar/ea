package cs544.exercise13_1.logging;

public class Logger implements ILogger {
    @Override
    public void log(String logstring) {
        java.util.logging.Logger.getLogger("AppLogger").info(logstring);
    }
}
