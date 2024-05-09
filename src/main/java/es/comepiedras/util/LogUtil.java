package es.comepiedras.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Logger;

public class LogUtil {
    public static final String USUARIO_FILE = "user";
    public static final String SALA_FILE = "sala";
    public static final String PATH = "D:/Desarrollo/logs/poc/";

    public static Logger createLoggerUser(String name) throws IOException {
        return createLogger(name, USUARIO_FILE);
    }

    public static Logger createLoggerSala(String name) throws IOException {
        return createLogger(name, SALA_FILE);
    }

    @org.jetbrains.annotations.NotNull
    protected static Logger createLogger(String name, String suffix) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date = sdf.format(new Date());
        String fileName = suffix + "-" + name + "-" + date + ".log";

        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5p %c{1}:%L - %m%n");
        FileAppender appender = new FileAppender(layout, PATH + fileName, false);
        Logger logger = Logger.getLogger(name);
        logger.addAppender(appender);
        logger.setAdditivity(false);

        return logger;
    }

    public static void writeTraces(Logger logger) {
        Random random = new Random();
        int numTraces = random.nextInt(5) + 2;

        for (int i = 0; i < numTraces; i++) {
            if (random.nextBoolean()) {
                logger.info("This is an info message: " + i);
            } else {
                logger.debug("This is a debug message: " + i);
            }
        }
    }
}
