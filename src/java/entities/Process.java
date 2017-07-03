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
public class Process {
    
    private String id;
    private String name;
    private String status;

    public Process() {
    }

    public Process(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ",\"status\": \"" + status + '"' +
                "}}";
    }
    
}
