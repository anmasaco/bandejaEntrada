package com.bandeja.entrada.entities;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Company UT SUNGEMINI � ZABALA II
 *
 * @project : SiriEjbClient
 * @author : Camilo Cruz
 * @created : 15/10/2010
 *
 *          Clase necesaria para generar trazas desde los servicios o metodos en
 *          toda la aplicaci�n
 *
 */
public class IntegrationLogger implements Serializable {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    private static final long serialVersionUID = 1L;
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    // ------ public ------

    // ------ protected ------

    // ------ private ------

    private static IntegrationLogger myLogger;
    private static String logConf = "";
    private static Logger logger = Logger.getLogger("Siri");

    // -----------------------------------------------------------------
    // Costructor
    // -----------------------------------------------------------------
    private IntegrationLogger(String logfile) {
        logConf = logfile;
        System.out.println("ConfigureAndWatch!!!");
        PropertyConfigurator.configureAndWatch(logConf);
        IntegrationLogger.setLogger(Logger.getLogger("Siri"));
    }

    // -----------------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------------

    /**
     * Sigleton: Retorna la instancia del manejador de logger, si no existe la
     * crea
     *
     * @return La instancia el logger
     * @param Archivo
     *            en donde se le generar� la traza
     *
     */
    public static IntegrationLogger getInstance(String logfile) {
        //if (myLogger == null) {
        myLogger = new IntegrationLogger(logfile);
        //}
        return myLogger;
    }

    /**
     * Sigleton: Retorna la instancia del manejador de logger, si no existe la
     * crea
     *
     * @return La instancia el logger
     */
    public static IntegrationLogger getInstance() {
        if (myLogger == null) {
            System.out.println("Warning llamando a la instancia del Log4j se encontro el valor nulo!s");
            myLogger = new IntegrationLogger(logConf);
        }
        return myLogger;
    }

    /**
     * M�todo para adicionar linea de informacion
     *
     * @param log
     *            : Mensaje de log
     *
     */
    public void info(String log) {
        if (logger.isInfoEnabled()) {
            logger.info(log + " \n");
        }
    }

    /**
     * M�todo para adicionar linea de depuracion
     *
     * @param log
     *            : Mensaje de log
     */
    public void debug(String log) {
        if (logger.isDebugEnabled()) {
            logger.debug(log + " \n");
        }

    }

    /**
     * M�todo para adicionar linea de warn
     *
     * @param log
     *            : Mensaje de log
     */
    public void warn(String log) {
        logger.warn(log + " \n");

    }

    /**
     * M�todo para adicionar linea de error
     *
     * @param log
     *            : Mensaje de log
     */
    public void error(String log) {
        logger.error(log + " \n");
    }

    /**
     * M�todo para adicionar linea de error, incluyendo el StackTrace
     *
     * @param log
     *            : Mensaje de log
     */
    public void error(String log, Exception _e) {
        StringBuffer msg = new StringBuffer();
        StackTraceElement stack[] = _e.getStackTrace();
        msg.append(_e.toString());
        for (int i = 0; i < stack.length; i++) {
            msg.append("\t at " + stack[i].toString());
        }
        // imprime mensaje
        logger.error(log + " \n");
        // imprime excepcion
        logger.error(msg + " \n");

    }

    /**
     * M�todo para adicionar linea de error fatal
     *
     * @param log
     *            Mensaje de log
     */
    public void fatal(String log) {
        logger.fatal(log + " \n");
    }

    /**
     * Destructor de la instancia del logger, lo pone en null
     *
     */
    public static void destroy() {
        if (myLogger != null) {
            myLogger = null;
        }
    }

    // -----------------------------------------------------------------
    // Protected Methods
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Private Methods
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // get/set Methods
    // -----------------------------------------------------------------

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        IntegrationLogger.logger = logger;
    }

}
