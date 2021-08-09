package com.example.footballscheduling;

public class ModelPlayers1 {
    String playerName, IDNumber, Key;

    public ModelPlayers1(String playerName, String IDNumber, String key) {
        this.playerName = playerName;
        this.IDNumber = IDNumber;
        Key = key;
    }

    public ModelPlayers1() {
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public String getKey() {
        return Key;
    }
}