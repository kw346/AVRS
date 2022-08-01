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

    public License(String name, String number, String membership, String license, String school, String designation) {
        this.name = name;
        this.number = number;
        this.membership = membership;
        this.license = license;
        this.school = school;
        this.designation = designation;
    }

    public License(int id, String name, String number, String membership, String license, String school, String designation) {
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

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String setNumber(String number) {
        this.number = number;
        return number;
    }

    public String getMembership() {
        return membership;
    }

    public String setMembership(String membership) {
        this.membership = membership;
        return membership;
    }

    public String getLicense() {
        return license;
    }

    public String setLicense(String license) {
        this.license = license;
        return license;
    }

    public String getSchool() {
        return school;
    }

    public String setSchool(String school) {
        this.school = school;
        return school;
    }

    public String getDesignation() {
        return designation;
    }

    public String setDesignation(String designation) {
        this.designation = designation;
        return designation;
    }
}