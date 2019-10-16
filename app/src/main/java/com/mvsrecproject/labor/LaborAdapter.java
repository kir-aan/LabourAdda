package com.mvsrecproject.labor;

import java.io.Serializable;

public class LaborAdapter implements Serializable {

    private String laborName;
    private String laborAge;
    private String labourUID;
    private boolean active;

    public String getLabourUID() {
        return labourUID;
    }

    public LaborAdapter(String labourUID, String laborName, String laborAge)  {
        this.labourUID = labourUID;
        this.laborName= laborName;
        this.laborAge = laborAge;
        this.active= false;
    }


    public String getlaborAge() {
        return laborAge;
    }

    public void setLaborAge(String laborAge) {
        this.laborAge = laborAge;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.laborName +" ("+ this.laborAge+")";
    }

}
