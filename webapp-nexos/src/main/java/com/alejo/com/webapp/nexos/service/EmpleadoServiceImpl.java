package com.alejo.com.webapp.nexos.service;


import com.alejo.com.webapp.nexos.domain.Empleado;
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
public class EmpleadoServiceImpl implements ICrudService<Empleado>, Serializable {

    @Inject
    private ICrudResopitory resopitory;
    
    @Inject
    private AdminitradorPropiedad adminitradorPropiedad;
    
    @Inject
    private AdministradorLog administradorLog;
    
    private String URL_EMPLEADO;
    
    @PostConstruct
    public void init(){
        this.URL_EMPLEADO = adminitradorPropiedad.leerPropiedad("URL_API_EMPLEADO");
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<>();
        JSONObject data = new JSONObject();
        Map<String, String> response = resopitory.findAll(URL_EMPLEADO, "GET", Optional.of(data));
        try {

            if (Integer.parseInt(response.get("httpCode")) == 200) {
                String bodyRest = response.get("bodyRest");
                bodyRest = bodyRest.replace("null", "\" \"");
                
                JSONArray jsonArrayDepartamentos = new JSONArray(bodyRest);
                for (int i = 0; i < jsonArrayDepartamentos.length(); i++) {
                    String nombres = jsonArrayDepartamentos.getJSONObject(i).getString("nombres");
                    String apellidos = jsonArrayDepartamentos.getJSONObject(i).getString("apellidos");
                    String ciudad = jsonArrayDepartamentos.getJSONObject(i).getString("ciudad");
                    String direccion = jsonArrayDepartamentos.getJSONObject(i).getString("direccion");
                    String tipoDocumento = jsonArrayDepartamentos.getJSONObject(i).getString("tipoDocumento");
                    String numeroDocumento = jsonArrayDepartamentos.getJSONObject(i).getString("numeroDocumento");
                    String correo = jsonArrayDepartamentos.getJSONObject(i).getString("correo");
                    String telefono = jsonArrayDepartamentos.getJSONObject(i).getString("telefono");
                    Long id = jsonArrayDepartamentos.getJSONObject(i).getLong("id");
                    String fechaHoraModificacion = jsonArrayDepartamentos.getJSONObject(i).getString("fechaHoraModificacion");
                    String fechaHoraCreacio = jsonArrayDepartamentos.getJSONObject(i).getString("fechaHoraCreacio");

                    empleados.add(new Empleado(id, tipoDocumento, numeroDocumento, nombres, apellidos, ciudad, direccion, correo, telefono, fechaHoraCreacio, fechaHoraModificacion));

                }
            }
        } catch (Exception ex) {
            administradorLog.logError("EmpleadoServiceImpl/findAll() se ha presentando un problema"
                    + "listando los departamentos ", ex);
        }
        return empleados;
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        Optional<Empleado> optionalEmpleado = Optional.empty();
        JSONObject data = new JSONObject();
        Map<String, String> response = resopitory.findById(id, URL_EMPLEADO, "GET", Optional.of(data));
        try {

            if (Integer.parseInt(response.get("httpCode")) == 200) {
                String bodyRest = response.get("bodyRest");
                bodyRest = bodyRest.replace("null", "\" \"");
                JSONObject jsonEmpleado = new JSONObject(bodyRest);
                String nombres = jsonEmpleado.getString("nombres");
                String apellidos = jsonEmpleado.getString("apellidos");
                String ciudad = jsonEmpleado.getString("ciudad");
                String direccion = jsonEmpleado.getString("direccion");
                String tipoDocumento = jsonEmpleado.getString("tipoDocumento");
                String numeroDocumento = jsonEmpleado.getString("numeroDocumento");
                String correo = jsonEmpleado.getString("correo");
                String telefono = jsonEmpleado.getString("telefono");
                id = jsonEmpleado.getLong("id");
                String fechaHoraModificacion = jsonEmpleado.getString("fechaHoraModificacion");
                String fechaHoraCreacio = jsonEmpleado.getString("fechaHoraCreacio");

                optionalEmpleado = Optional.of(new Empleado(id, tipoDocumento, numeroDocumento, nombres, apellidos, ciudad, direccion, correo, telefono, fechaHoraCreacio, fechaHoraModificacion));
            }
        } catch (Exception ex) {
            administradorLog.logError("EmpleadoServiceImpl/findById() se ha presentando un problema"
                    + "recuperando el departamento ", ex);
        }
        return optionalEmpleado;
    }

    @Override
    public int save(Empleado empleado) {

        JSONObject data = new JSONObject();
        //data.put("id", "" + empleado.getId());
        data.put("nombres", empleado.getNombres());
        data.put("apellidos", "" + empleado.getApellidos());
        data.put("tipoDocumento", "" + empleado.getTipoDocumento());
        data.put("numeroDocumento", "" + empleado.getNumeroDocumento());
        data.put("ciudad", "" + empleado.getCiudad());
        data.put("telefono", "" + empleado.getTelefono());
        data.put("correo", "" + empleado.getCorreo());
        data.put("direccion", "" + empleado.getDireccion());
        data.put("departamento", new JSONObject().put("id",empleado.getDepartamento().getId()));
        Map<String, String> response = resopitory.save(URL_EMPLEADO,"POST", Optional.of(data));
        int httpCode = Integer.parseInt(response.get("httpCode"));

        return httpCode;
    }

    @Override
    public int delete(Long id) {

        JSONObject data = new JSONObject();

        Map<String, String> response = resopitory.delete(id, URL_EMPLEADO,"DELETE", Optional.of(data));
        int httpCode = Integer.parseInt(response.get("httpCode"));

        return httpCode;
    }

    @Override
    public int update(Empleado empleado) {
        JSONObject data = new JSONObject();
        data.put("id", "" + empleado.getId());
        data.put("nombres", empleado.getNombres());
        data.put("apellidos", "" + empleado.getApellidos());
        data.put("tipoDocumento", "" + empleado.getTipoDocumento());
        data.put("numeroDocumento", "" + empleado.getNumeroDocumento());
        data.put("ciudad", "" + empleado.getCiudad());
        data.put("telefono", "" + empleado.getTelefono());
        data.put("correo", "" + empleado.getCorreo());
        data.put("direccion", "" + empleado.getDireccion());
        data.put("departamento", new JSONObject().put("id",empleado.getDepartamento().getId()));
        Map<String, String> response = resopitory.update(empleado.getId(), URL_EMPLEADO, "PUT", Optional.of(data));
        int httpCode = Integer.parseInt(response.get("httpCode"));

        return httpCode;
    }

}
