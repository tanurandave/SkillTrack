package com.skilltrack.model;

public class Skill {
    private String name;
    private int proficiency;

    public Skill() {
    }

    public Skill(String name, int proficiency) {
        this.name = name;
        this.proficiency = proficiency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
}
