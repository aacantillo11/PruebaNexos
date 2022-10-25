package com.alejo.com.webapp.nexos.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ResourceBundle;



/**
 *
 * @author Alejandro Cantillo
 */
@ApplicationScoped
public class AdminitradorPropiedad implements Serializable{

    @Inject
    private AdministradorLog administradorLog;
    private static final String ARCHIVO_PROPIEDADES = "webapp-nexos";

    public String leerPropiedad(final String propiedad) {
        String value = "";
        try {
            value = ResourceBundle.getBundle(ARCHIVO_PROPIEDADES).getString(propiedad);
            
        } catch (Exception e) {
            administradorLog.logError("AdminitradorPropiedad/leerPropiedad() no se pudo leer la propiedad" + propiedad, e);
        }
     
        return value;
    }
}
