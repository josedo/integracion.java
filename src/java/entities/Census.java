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
public class Census {
    
    private String id;
    private String table;
    private String room;
    private String local;
    private String district;
    private String status;

    public Census() {
    }

    public Census(String id, String table, String room, String local, String district, String status) {
        this.id = id;
        this.table = table;
        this.room = room;
        this.local = local;
        this.district = district;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
                ",\"table\": \"" + table + '"' +
                ",\"room\": \"" + room + '"' +
                ",\"local\": \"" + local + '"' +
                ",\"district\": \"" + district + '"' +
                ",\"status\": \"" + status + '"' +
                "}}";
    }
    
}
