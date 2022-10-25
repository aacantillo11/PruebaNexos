package com.alejo.com.webapp.nexos.repository;

import com.alejo.com.webapp.nexos.util.AdministradorLog;
import com.alejo.com.webapp.nexos.util.AdminitradorPropiedad;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KRIPTO
 */
@RequestScoped
public class CrudRepositoryImpl implements ICrudResopitory, Serializable {

    @Inject
    private AdminitradorPropiedad adminitradorPropiedad;

    @Inject
    private AdministradorLog administradorLog;

    private int TIMEOUT;
    private String USR_AUTH_REST;
    private String PASS_AUTH_REST;
    private String AUTH_REST;

    @PostConstruct
    public void init() {
        this.TIMEOUT = Integer.parseInt(adminitradorPropiedad.leerPropiedad("API_TIMEOUT"));
        this.USR_AUTH_REST = adminitradorPropiedad.leerPropiedad("USR_AUTH_API");
        this.PASS_AUTH_REST = adminitradorPropiedad.leerPropiedad("PASS_AUTH_API");
        this.AUTH_REST = "Basic " + new String(Base64.getEncoder().encode((USR_AUTH_REST + ":" + PASS_AUTH_REST).getBytes(StandardCharsets.UTF_8)));

    }

    
    @Override
    public Map<String, String> findAll(String urlEndPoint, String metodoHttp, Optional<JSONObject> bodyResquest) {
        Map<String, String> response = new HashMap<>();
        try {

            response = ejecutarMetodoHttp(urlEndPoint, metodoHttp, bodyResquest);

        } catch (Exception ex) {
            administradorLog.logError("CrudRepositoryImpl/findAll se ha presentando un problema"
                    + " listando los departamentos ", ex);
        }
        return response;
    }

    @Override
    public Map<String, String> findById(Long id, String urlEndPoint, String metodoHttp, Optional<JSONObject> bodyResquest) {
        Map<String, String> response = new HashMap<>();
        try {

            response = ejecutarMetodoHttp(urlEndPoint + "/" + id, metodoHttp, bodyResquest);

        } catch (Exception ex) {
            administradorLog.logError("CrudRepositoryImpl/findById() se ha presentando un problema"
                    + " recuperando el departamento ", ex);
        }
        return response;
    }

    @Override
    public Map<String, String> save(String urlEndPoint, String metodoHttp, Optional<JSONObject> bodyResquest) {
        Map<String, String> response = new HashMap<>();
        try {

            response = ejecutarMetodoHttp(urlEndPoint, metodoHttp, bodyResquest);
        } catch (Exception ex) {
            administradorLog.logError("CrudRepositoryImpl/save() se ha presentando un problema"
                    + " guardando el departamento ", ex);
        }
        return response;
    }

    @Override
    public Map<String, String> update(Long id, String urlEndPoint, String metodoHttp, Optional<JSONObject> bodyResquest) {
        Map<String, String> response = new HashMap<>();
        try {

            response = ejecutarMetodoHttp(urlEndPoint + "/" + id, metodoHttp, bodyResquest);
        } catch (Exception ex) {
            administradorLog.logError("CrudRepositoryImpl/update() se ha presentando un problema"
                    + " actualizando el departamento ", ex);
        }
        return response;
    }

    @Override
    public Map<String, String> delete(Long id, String urlEndPoint, String metodoHttp, Optional<JSONObject> bodyResquest) {
        Map<String, String> response = new HashMap<>();
        try {

            response = ejecutarMetodoHttp(urlEndPoint + "/" + id, metodoHttp, bodyResquest);

        } catch (Exception ex) {
            administradorLog.logError("CrudRepositoryImpl/delete() se ha presentando un problema"
                    + " eliminando el departamento ", ex);
        }
        return response;
    }

    private Map<String, String> ejecutarMetodoHttp(String uri, String httpMetodo, Optional<JSONObject> bodyResquest) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> response = new HashMap<>();
        try {

            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMetodo);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", AUTH_REST);
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setAllowUserInteraction(false);

            if (httpMetodo.equals("PUT") || httpMetodo.equals("POST") && bodyResquest.isPresent()) {

                try ( OutputStream wr = connection.getOutputStream();  Writer writer = new OutputStreamWriter(wr, "UTF-8")) {
                    writer.write(bodyResquest.get().toString());
                }

            }
            int httpResult = connection.getResponseCode();

            if (httpResult == HttpURLConnection.HTTP_OK) {
                try ( BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                }
            }

            response.put("httpCode", "" + httpResult);
            response.put("bodyRest", sb.toString());

        } catch (IOException | JSONException e) {
            administradorLog.logError("Util/ejecutarMetodoHttp() se ha presentando un problema"
                    + " al consumir la API_NEXOS ", e);
            response.put("httpCode", "" + 500);
        }
        return response;
    }

}
