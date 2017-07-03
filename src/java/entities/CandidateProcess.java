/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Joe-Xidu
 */
public class CandidateProcess {
    
    private String id;
    private String id_process;
    private String id_candidate;
    private String votes;
    private String status;

    public CandidateProcess() {
    }

    public CandidateProcess(String id, String id_process, String id_candidate, String votes, String status) {
        this.id = id;
        this.id_process = id_process;
        this.id_candidate = id_candidate;
        this.votes = votes;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_process() {
        return id_process;
    }

    public void setId_process(String id_process) {
        this.id_process = id_process;
    }

    public String getId_candidate() {
        return id_candidate;
    }

    public void setId_candidate(String id_candidate) {
        this.id_candidate = id_candidate;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
