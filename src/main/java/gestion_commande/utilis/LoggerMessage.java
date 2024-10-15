package gestion_commande.utilis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerMessage {
    private static final Logger logger = LoggerFactory.getLogger(LoggerMessage.class);

    public  LoggerMessage(){

    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }
    public static  void debug(String message){
        logger.debug(message);
        
    }


    public static void error(String message) {
        logger.error(message);
    }


}
