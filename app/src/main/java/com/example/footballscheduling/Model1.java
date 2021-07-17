package com.example.footballscheduling;

public class Model1 {
    String IDNumber, LoanDate, LoanDuration, LoanFrom, LoanTo, PlayerName, key;

    public Model1() {
    }

    public Model1(String IDNumber, String loanDate, String loanDuration, String loanFrom, String loanTo, String playerName, String key) {
        this.IDNumber = IDNumber;
        LoanDate = loanDate;
        LoanDuration = loanDuration;
        LoanFrom = loanFrom;
        LoanTo = loanTo;
        PlayerName = playerName;
        this.key = key;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public void setLoanDate(String loanDate) {
        LoanDate = loanDate;
    }

    public void setLoanDuration(String loanDuration) {
        LoanDuration = loanDuration;
    }

    public void setLoanFrom(String loanFrom) {
        LoanFrom = loanFrom;
    }

    public void setLoanTo(String loanTo) {
        LoanTo = loanTo;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public String getLoanDate() {
        return LoanDate;
    }

    public String getLoanDuration() {
        return LoanDuration;
    }

    public String getLoanFrom() {
        return LoanFrom;
    }

    public String getLoanTo() {
        return LoanTo;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public String getKey() {
        return key;
    }
}