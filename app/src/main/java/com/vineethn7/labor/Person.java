package com.vineethn7.labor;

public class Person
{
    boolean checked;
    String name;
    String age;

    public Person(String name,String age,boolean checked)
    {
        this.name = name;
        this.age = age;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean isChecked) {
        this.checked = checked;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }
}
