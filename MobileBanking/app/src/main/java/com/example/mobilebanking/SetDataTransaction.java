package com.example.mobilebanking;

public class SetDataTransaction
{
    private String SenderName;
    private String ReciverName;
    private int  Balance;
    public SetDataTransaction(String senderName, String reciverName, int balance)
    {
        SenderName = senderName;
        ReciverName = reciverName;
        Balance = balance;
    }

    public String getSenderName() {
        return SenderName;
    }

    public String getReciverName() {
        return ReciverName;
    }

    public int getBalance() {
        return Balance;
    }

}
