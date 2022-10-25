package com.alejo.com.webapp.nexos.service;


import com.alejo.com.webapp.nexos.domain.Departamento;

import com.alejo.com.webapp.nexos.repository.ICrudResopitory;
import com.alejo.com.webapp.nexos.util.AdministradorLog;
import com.alejo.com.webapp.nexos.util.AdminitradorPropiedad;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author KRIPTO
 */
@Stateless
public class DepartamentoServiceImpl implements ICrudService<Departamento>, Serializable {

    @Inject
    private ICrudResopitory resopitory;

   @Inject
    private AdminitradorPropiedad adminitradorPropiedad;
    
    @Inject
    private AdministradorLog administradorLog;
    private String URL_DEPARTAMENTO;
    
    @PostConstruct
    public void  init(){
        this.URL_DEPARTAMENTO = adminitradorPropiedad.leerPropiedad("URL_API_DEPARTAMENTO");
    }
    
  
    
    @Override
    public List<Departamento> findAll() {
        List<Departamento> departamentos = new ArrayList<>();
        JSONObject data = new JSONObject();
        
        Map<String, String> response = resopitory.findAll(URL_DEPARTAMENTO, "GET", Optional.of(data));
        try {
            
            if (Integer.parseInt(response.get("httpCode")) == 200) {
                String bodyRest = response.get("bodyRest");
                
                JSONArray jsonArrayDepartamentos = new JSONArray(bodyRest);
                for (int i = 0; i < jsonArrayDepartamentos.length(); i++) {
                    String nombre = jsonArrayDepartamentos.getJSONObject(i).getString("nombre");
                    String codigo = jsonArrayDepartamentos.getJSONObject(i).getString("codigo");
                    Long id = jsonArrayDepartamentos.getJSONObject(i).getLong("id");
                    String fechaHoraModificacion = jsonArrayDepartamentos.getJSONObject(i).getString("fechaHoraModificacion");
                    String fechaHoraCreacio = jsonArrayDepartamentos.getJSONObject(i).getString("fechaHoraCreacio");
                    departamentos.add(new Departamento(id, nombre, codigo, fechaHoraCreacio, fechaHoraModificacion));

                }
            }
        } catch (Exception ex) {
            administradorLog.logError("DepartamentoServiceImpl/findAll() se ha presentando un problema"
                    + " listando los departamentos ", ex);
        }
        return departamentos;
    }

    
    @Override
    public Optional<Departamento> findById(Long id) {
        Optional<Departamento> optionalDepartamento = Optional.empty();
        JSONObject data = new JSONObject();
        Map<String, String> response = resopitory.findById(id, URL_DEPARTAMENTO, "GET", Optional.of(data));
        try {

            if (Integer.parseInt(response.get("httpCode")) == 200) {
                String bodyRest = response.get("bodyRest");
                JSONObject jsonDepartamento = new JSONObject(bodyRest);
                String nombre = jsonDepartamento.getString("nombre");
                String codigo = jsonDepartamento.getString("codigo");
                id = jsonDepartamento.getLong("id");
                String fechaHoraModificacion = jsonDepartamento.getString("fechaHoraModificacion");
                String fechaHoraCreacio = jsonDepartamento.getString("fechaHoraCreacio");
                optionalDepartamento = Optional.of(new Departamento(id, nombre, codigo, fechaHoraCreacio, fechaHoraModificacion));
            }
        } catch (Exception ex) {
            administradorLog.logError("DepartamentoServiceImpl/findById() se ha presentando un problema"
                    + " recuperando el departamento ", ex);
        }
        return optionalDepartamento;
    }

    @Override
    public int save(Departamento departamento) {

        JSONObject data = new JSONObject();
        data.put("nombre", departamento.getNombre());
        data.put("codigo", "" + departamento.getCodigo());
        Map<String, String> response = resopitory.save(URL_DEPARTAMENTO,"POST", Optional.of(data));
        int httpCode = Integer.parseInt(response.get("httpCode"));

        return httpCode;
    }

    @Override
    public int delete(Long id) {

        JSONObject data = new JSONObject();

        Map<String, String> response = resopitory.delete(id, URL_DEPARTAMENTO, "DELETE", Optional.of(data));
        int httpCode = Integer.parseInt(response.get("httpCode"));

        return httpCode;
    }

    @Override
    public int update(Departamento departamento) {
        JSONObject data = new JSONObject();
        data.put("id", "" + departamento.getId());
        data.put("nombre", departamento.getNombre());
        data.put("codigo", "" + departamento.getCodigo());
        Map<String, String> response = resopitory.update(departamento.getId(),URL_DEPARTAMENTO,
                "PUT", Optional.of(data));
        int httpCode = Integer.parseInt(response.get("httpCode"));

        return httpCode;
    }

}
