package com.verificationapplication.poc.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class FailedVerificationReasons {

    private String firstname;
    private String lastname;
    private int id;
    private List<String> reasons = new ArrayList<>();

    public FailedVerificationReasons(){

    }

    public FailedVerificationReasons(String firstname, String lastname, int id, List<String> reasons) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.reasons = reasons;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons.add(reasons);
    }

    @Override
    public String toString() {
        return "FailedVerificationReasons{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id=" + id +
                ", reasons=" + reasons +
                '}';
    }
}
