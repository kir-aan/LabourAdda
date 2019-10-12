package com.mvsrecproject.labor;

import java.io.Serializable;

public class LaborAdapter implements Serializable {

    private String laborName;
    private int laborAge;

    private boolean active;

    public LaborAdapter(String laborName, int laborAge)  {
        this.laborName= laborName;
        this.laborAge = laborAge;
        this.active= false;
    }


    public int getlaborAge() {
        return laborAge;
    }

    public void setLaborAge(int laborAge) {
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
