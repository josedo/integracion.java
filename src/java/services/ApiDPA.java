/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jose.becerra
 */
public class ApiDPA {
    
    private final String urlRegions;
    private String urlDistricts;
    private String urlProvinces;

    public ApiDPA() throws MalformedURLException {
        this.urlRegions = "https://apis.digital.gob.cl/dpa/regiones";
    }
    
    public Map getRegions() throws URISyntaxException, IOException, HttpException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getConnectionStream(this.urlRegions)));
        String output = reader.readLine();
        Map<String, String> list = new HashMap<>();
        JSONArray array = new JSONArray(output);
        
        for (int i = 0; i < array.length(); i++) {
            JSONObject row = array.getJSONObject(i);
            list.put(row.getString("codigo"), row.getString("nombre"));
        }
        
        return list;
    }
    
    public Map getProvinces(String region) throws URISyntaxException, IOException, HttpException {
        this.urlProvinces = String.format("https://apis.digital.gob.cl/dpa/regiones/%s/provincias", region);
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getConnectionStream(this.urlProvinces)));
        String output = reader.readLine();
        Map<String, String> list = new HashMap<>();
        JSONArray array = new JSONArray(output);
        
        for (int i = 0; i < array.length(); i++) {
            JSONObject row = array.getJSONObject(i);
            list.put(row.getString("codigo"), row.getString("nombre"));
        }
        
        return list;
    }
    
    public Map getDistricts(String province) throws URISyntaxException, IOException, HttpException {
        this.urlDistricts = String.format("https://apis.digital.gob.cl/dpa/provincias/%s/comunas", province);
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getConnectionStream(this.urlDistricts)));
        String output = reader.readLine();
        Map<String, String> list = new HashMap<>();
        JSONArray array = new JSONArray(output);
        
        for (int i = 0; i < array.length(); i++) {
            JSONObject row = array.getJSONObject(i);
            list.put(row.getString("codigo"), row.getString("nombre"));
        }
        
        return list;
    }
    
    private InputStream getConnectionStream(String fullUrl) throws URISyntaxException, IOException, HttpException{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(fullUrl);
        HttpResponse response = client.execute(request);
        InputStream is = response.getEntity().getContent();

        return is;
    }
}
