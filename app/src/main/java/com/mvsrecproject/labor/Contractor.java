package com.mvsrecproject.labor;

public class Contractor {
    String UID;
    String name;
    String id;
    int ContractorAge;
    String AadharNum;
    String phoneNum;
    String ContractorLocation;

    public Contractor(String name,String number) {

        this.name = name;
        this.phoneNum=number;
    }

    public Contractor(String id,String name,String phoneNum, int contractorAge, String aadharNum, String contractorLocation) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.ContractorAge = contractorAge;
        this.AadharNum = aadharNum;
        this.ContractorLocation = contractorLocation;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUID() {
        return UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getContractorAge() {
        return ContractorAge;
    }

    public String getAadharNum() {
        return AadharNum;
    }

    public String getContractorLocation() {
        return ContractorLocation;
    }
}
