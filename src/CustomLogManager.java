import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.io.IOException;

public class CustomLogManager {
    public static void configureLogger() {
        LogManager logManager = LogManager.getLogManager();
        Logger rootLogger = Logger.getLogger("");

        // Remove default handlers
        rootLogger.setUseParentHandlers(false);

        // Add console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        rootLogger.addHandler(consoleHandler);

        // Add file handler
        try {
            FileHandler fileHandler = new FileHandler("customlog.log");
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        configureLogger();
        Logger logger = Logger.getLogger("CustomLogger");
        logger.info("Custom log message.");
    }
}
