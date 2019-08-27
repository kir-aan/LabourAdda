package com.mvsrecproject.labor;

public class Contractor {
    String name;
    long number;

    public Contractor(String name,long number) {
        this.name = name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
