package sg.edu.rp.c346.id20022735.avrs;

import java.io.Serializable;

public class License implements Serializable {

    private int id;
    private String name;
    private String number;
    private String membership;
    private String license;
    private String school;
    private String designation;

    public License(String name, String number, String membership, String license, String school,String designation) {
        this.name = name;
        this.number = number;
        this.membership = membership;
        this.license = license;
        this.school = school;
        this.designation = designation;
    }

    public License(int id, String name, String number, String membership, String license, String school,String designation) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.membership = membership;
        this.license = license;
        this.school = school;
        this.designation = designation;
    }

    public int getId() {
        return id;
    }

    public License setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public License setName(String name) {
        this.name = name;
        return this;
    }
    public String getNumber() {return number; }

    public License setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getMembership() {
        return membership;
    }

    public License setMembership(String membership) {
        this.membership = membership;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public License setLicense(String license) {
        this.license = license;
        return this;
    }
    public String getSchool() {
        return school;
    }

    public License setSchool(String school) {
        this.school = school;
        return this;
    }
    public String getDesignation() {
        return designation;
    }

    public License setDesignation(String designation) {
        this.designation = designation;
        return this;
    }
}