/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashMap;
import java.util.Map;
import services.ApiDPA;

/**
 *
 * @author jose.becerra
 */
public class Utilities {
    
    public static Map getRegions(){
        Map<String, String> list = new HashMap<>();
        ApiDPA apiDPA;
        try {
            apiDPA = new ApiDPA();
            list = apiDPA.getRegions();
        } catch (Exception e) {
        }
        return list;
    }
    
    public static Map getProvinces(String region){
        Map<String, String> list = new HashMap<>();
        ApiDPA apiDPA;
        try {
            apiDPA = new ApiDPA();
            list = apiDPA.getProvinces(region);
        } catch (Exception e) {
        }
        return list;
    }
    
    public static Map getDistricts(String province){
        Map<String, String> list = new HashMap<>();
        ApiDPA apiDPA;
        try {
            apiDPA = new ApiDPA();
            list = apiDPA.getDistricts(province);
        } catch (Exception e) {
        }
        return list;
    }
}
