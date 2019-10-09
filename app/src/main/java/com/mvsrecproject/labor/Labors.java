package com.mvsrecproject.labor;

public class Labors {
    String LabourName;
    String id;
    int LabourAge;
    String LabourSkill;
    String AadharNum;
    String LabourLocation;
    String phoneNum;
    boolean availabilityStatus=false;
    public Labors(){

    }

    public Labors(String id, String labourName, String phoneNum , int labourAge, String labourSkill, String aadharNum, String labourLocation) {
        this.id = id;
        this.LabourName = labourName;
        this.phoneNum = phoneNum;
        this.LabourAge = labourAge;
        this.LabourSkill = labourSkill;
        this.AadharNum = aadharNum;
        this.LabourLocation = labourLocation;
    }

    public String getLabourName() {
        return LabourName;
    }

    public int getLabourAge() {
        return LabourAge;
    }

    public String getLabourSkill() {
        return LabourSkill;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAadharNum() {
        return AadharNum;
    }

    public String getLabourLocation() {
        return LabourLocation;
    }

    public boolean isAvailabilityStatus() { return availabilityStatus; }
}
