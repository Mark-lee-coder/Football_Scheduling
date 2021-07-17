package com.example.footballscheduling;

public class TransferPermanent {
    public String PlayerName,  IDNumber, TransferFees, TransferFrom, TransferTo, TransferDate;

    public TransferPermanent(String playerName, String IDNumber, String transferFees, String transferFrom, String transferTo, String transferDate) {
        PlayerName = playerName;
        this.IDNumber = IDNumber;
        TransferFees = transferFees;
        TransferFrom = transferFrom;
        TransferTo = transferTo;
        TransferDate = transferDate;
    }

    public TransferPermanent() {
    }
}