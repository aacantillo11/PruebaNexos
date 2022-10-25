
package com.alejo.com.webapp.nexos.util;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;


/**
 *
 * @author KRIPTO
 */

@RequestScoped
public class AdministradorLog implements Serializable{
    
    private int threadId = 0;
    
   @Inject
    private  WebLogger webLogger;

    public AdministradorLog() {
    }
    
    public AdministradorLog(int threadId){
        this.threadId = threadId;
    }
    
    public synchronized void logInfo(final String mensaje) {
        webLogger.escribirLog("General", "INFO  " + mensaje);
    }

    public synchronized void logError(final String mensaje, final Exception exception) {
        webLogger.escribirLog("General", "ERROR " + mensaje + " ->" + exception);
    }

    public synchronized void logTransaccionInfo(final String mensaje) {
        webLogger.escribirLog("TransactionInfo", "Thread: " + threadId + "  " + mensaje);
    }

    public synchronized void logTransaccionError(final String mensaje, final Exception exception) {
        webLogger.escribirLog("TransactionError", "Thread: " + threadId + "  " + mensaje + " - " + exception);
    }
}
