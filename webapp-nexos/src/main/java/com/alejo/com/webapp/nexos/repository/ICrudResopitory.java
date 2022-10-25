package com.alejo.com.webapp.nexos.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;

/**
 *
 * @author KRIPTO
 */
public interface ICrudResopitory {
    Map<String, String> findAll(String urlEndPoint,String metodoHttp,Optional<JSONObject> bodyResquest);
    
    Map<String, String> findById(Long id,String urlEndPoint,String metodoHttp,Optional<JSONObject> bodyResquest);
    
    Map<String, String> save(String urlEndPoint,String metodoHttp,Optional<JSONObject> bodyResquest);
    
    Map<String, String> update(Long id,String urlEndPoint,String metodoHttp,Optional<JSONObject> bodyResquest);
    
    Map<String, String> delete(Long id,String urlEndPoint,String metodoHttp,Optional<JSONObject> bodyResquest);
}
