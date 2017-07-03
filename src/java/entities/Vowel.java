/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author jose.becerra
 */
public class Vowel {
    
    private String id;
    private String rut;
    private String dv;
    private String name;
    private String id_census;
    private String status;

    public Vowel() {
    }

    public Vowel(String id, String rut, String dv, String name, String id_census, String status) {
        this.id = id;
        this.rut = rut;
        this.dv = dv;
        this.name = name;
        this.id_census = id_census;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_census() {
        return id_census;
    }

    public void setId_census(String id_census) {
        this.id_census = id_census;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"data\": {" + 
                "\"id\": \"" + id + '"' +
                ",\"name\": \"" + name + '"' +
                ",\"rut\": \"" + rut + '"' +
                ",\"dv\": \"" + dv + '"' +
                ",\"id_census\": \"" + id_census + '"' +
                ",\"status\": \"" + status + '"' +
                "}}";
    }
    
}
