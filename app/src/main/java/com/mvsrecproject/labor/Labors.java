package com.mvsrecproject.labor;

public class Labors {
    String LabourName;
    String id;
    int LabourAge;
    String LabourSkill;
    String AadharNum;
    String LabourLocation;

    public Labors(){

    }

    public Labors(String id,String labourName, int labourAge, String labourSkill, String aadharNum, String labourLocation) {
        this.id = id;
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
