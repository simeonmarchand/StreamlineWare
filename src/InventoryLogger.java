import java.io.IOException;
import java.util.logging.*;

public class InventoryLogger {
    private static Logger logger = Logger.getLogger("InventoryLogger");

    static {
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);

        // set up custom handler and formatter

        try {
            Handler handler = new FileHandler("inventory.log");
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        // set the log level
        logger.setLevel(Level.FINE);
    }

    public static void logInfo(String msg) {
        logger.info(msg);
    }

    public static void logError(String msg) {
        logger.severe(msg);
    }

    // any additional methods go here

    public static void main(String[] args) {
        InventoryLogger.logInfo("This is an info message");
        InventoryLogger.logError("This is an error message");
    }
}
