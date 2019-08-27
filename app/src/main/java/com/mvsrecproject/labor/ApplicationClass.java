package com.mvsrecproject.labor;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Contractor> contractors;

    @Override
    public void onCreate() {
        super.onCreate();

        contractors=new ArrayList<Contractor>();
        contractors.add(new Contractor("Contractor1",9087654321L));
        contractors.add(new Contractor("Contractor2",8907632189L));
        contractors.add(new Contractor("Contractor3",7854321908L));
        contractors.add(new Contractor("Contractor4",7854321908L));
        contractors.add(new Contractor("Contractor5",1234567800L));
        contractors.add(new Contractor("Contractor6",1234567800L));
        contractors.add(new Contractor("Contractor7",1234567800L));
        contractors.add(new Contractor("Contractor8",1234567800L));
        contractors.add(new Contractor("Contractor9",1234567800L));
        contractors.add(new Contractor("Contractor10",1234567800L));
        contractors.add(new Contractor("Contractor11",1234567800L));
        contractors.add(new Contractor("Contractor12",1234567800L));
        contractors.add(new Contractor("Contractor13",1234567800L));
        contractors.add(new Contractor("Contractor14",1234567800L));

    }
}
