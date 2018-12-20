package com.sanque.marc;


public class Record {
    String name, age, gender;

    public Record() {
    }

    public Record(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}