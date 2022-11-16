package com.example.footballscheduling.constructors;

public class Teams {
    String TeamName, key;

    public Teams() {
    }

    public Teams(String teamName, String key) {
        TeamName = teamName;
        this.key = key;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTeamName() {
        return TeamName;
    }

    public String getKey() {
        return key;
    }
}
