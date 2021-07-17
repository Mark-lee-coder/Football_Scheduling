package com.example.footballscheduling;

public class Model {
    String IDNumber, PlayerName, TransferDate, TransferFees, TransferFrom, TransferTo, key;

    public Model(String IDNumber, String playerName, String transferDate, String transferFees, String transferFrom, String transferTo, String key) {
        this.IDNumber = IDNumber;
        PlayerName = playerName;
        TransferDate = transferDate;
        TransferFees = transferFees;
        TransferFrom = transferFrom;
        TransferTo = transferTo;
        this.key = key;
    }

    public Model() {
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public void setTransferDate(String transferDate) {
        TransferDate = transferDate;
    }

    public void setTransferFees(String transferFees) {
        TransferFees = transferFees;
    }

    public void setTransferFrom(String transferFrom) {
        TransferFrom = transferFrom;
    }

    public void setTransferTo(String transferTo) {
        TransferTo = transferTo;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public String getTransferDate() {
        return TransferDate;
    }

    public String getTransferFees() {
        return TransferFees;
    }

    public String getTransferFrom() {
        return TransferFrom;
    }

    public String getTransferTo() {
        return TransferTo;
    }

    public String getKey() {
        return key;
    }
}