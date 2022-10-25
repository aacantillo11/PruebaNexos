package com.alejo.com.webapp.nexos.util;



import jakarta.enterprise.context.RequestScoped;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KRIPTO
 */
@RequestScoped
public class WebLogger implements Serializable{

  
    private  final String LOGS_PATH = "logWeb" + File.separator;

    
    public WebLogger() {
    }
   
    
    private  void existeArchivo() {
        try {
            File folderFile = new File(LOGS_PATH);
            if (!folderFile.exists()) {
                folderFile.mkdirs();
                
            }
        } catch (Exception ex) {
            Logger.getLogger(WebLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void escribirLog(String logName, String mensaje) {
        String logPath = LOGS_PATH + obtenerFecha()+ "_" + logName + ".txt";
        existeArchivo();
        try ( FileWriter fileWriter = new FileWriter(logPath, true);  
                BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            bufferWriter.write(obtenerHora() + " " + mensaje);
            bufferWriter.newLine();
           
        } catch (Exception ex) {
            Logger.getLogger(WebLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private  String obtenerFecha() {
        final Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    private  String obtenerHora() {
        final Time currentTime = new Time(new Date().getTime());
        return currentTime.toString();
    }
    
    
}
