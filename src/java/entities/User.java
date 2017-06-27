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
public class User {
    
    private String id;
    private String login;
    private String password;
    private String profile;
    private String name;
    private String rut;
    private String dv;
    private String status;

    public User() {
    }

    public User(String id, String login, String profile, String name, String rut, String dv, String status) {
        this.id = id;
        this.login = login;
        this.profile = profile;
        this.name = name;
        this.rut = rut;
        this.dv = dv;
        this.status = status;
    }

    public User(String id, String login, String password, String profile, String name, String rut, String dv, String status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.profile = profile;
        this.name = name;
        this.rut = rut;
        this.dv = dv;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "{\"data\": {" + 
                "\"id\": \"" + id + '"' + 
                ",\"login\": \"" + login + '"' +
                ",\"profile\": \"" + profile + '"' +
                ",\"name\": \"" + name + '"' +
                ",\"rut\": \"" + rut + '"' +
                ",\"dv\": \"" + dv + '"' +
                ",\"status\": \"" + status + '"' +
                "}}";
    }
    
    
}
