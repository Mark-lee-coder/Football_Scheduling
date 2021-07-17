package com.example.footballscheduling;

public class TransferLoan {
    public String PlayerName,  IDNumber, LoanFrom, LoanTo, LoanDuration, LoanDate;

    public TransferLoan(String playerName, String IDNumber, String loanFrom, String loanTo, String loanDuration, String loanDate) {
        PlayerName = playerName;
        this.IDNumber = IDNumber;
        LoanFrom = loanFrom;
        LoanTo = loanTo;
        LoanDuration = loanDuration;
        LoanDate = loanDate;
    }

    public TransferLoan() {
    }
}
