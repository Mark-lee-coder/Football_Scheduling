package com.example.footballscheduling.models;

public class ModelPlayers {
    String playerName, idNumber, Key;

    public ModelPlayers(String playerName, String IDNumber, String key) {
        this.playerName = playerName;
        this.idNumber = idNumber;
        this.Key = key;
    }

    public ModelPlayers() {
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIDNumber(String IDNumber) {
        this.idNumber = IDNumber;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getIDNumber() {
        return idNumber;
    }

    public String getKey() {
        return Key;
    }
}