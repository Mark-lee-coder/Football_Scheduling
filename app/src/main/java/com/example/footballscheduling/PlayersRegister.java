package com.example.footballscheduling;

public class PlayersRegister {
    String PlayerName, IdNumber;

    public PlayersRegister(String playerName, String idNumber) {
        PlayerName = playerName;
        IdNumber = idNumber;
    }

    public PlayersRegister() {
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }
}