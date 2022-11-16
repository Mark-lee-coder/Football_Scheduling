package com.example.footballscheduling.constructors;

public class TeamFixture {
    String TeamName, Key;

    public TeamFixture() {
    }

    public TeamFixture(String teamName, String key) {
        TeamName = teamName;
        Key = key;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getTeamName() {
        return TeamName;
    }

    public String getKey() {
        return Key;
    }
}
