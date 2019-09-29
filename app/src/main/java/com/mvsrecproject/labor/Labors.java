package com.mvsrecproject.labor;

public class Labors {
    String LabourName;
    int LabourAge;
    String LabourSkill;
    String AadharNum;
    String LabourLocation;

    public Labors(){

    }

    public Labors(String labourName, int labourAge, String labourSkill, String aadharNum, String labourLocation) {
        this.LabourName = labourName;
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

    public String getAadharNum() {
        return AadharNum;
    }

    public String getLabourLocation() {
        return LabourLocation;
    }
}
