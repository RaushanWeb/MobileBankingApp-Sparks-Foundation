package com.example.mobilebanking;

public class DataSet
{
    private String Name;
    private String Email;
    private int CurrentBalance;
    public DataSet(String Name,String Email,int CurrentBalance)
    {
        this.Name = Name;
        this.Email = Email;
        this.CurrentBalance = CurrentBalance;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public int getCurrentBalance() {
        return CurrentBalance;
    }

    public void addBalaance(int CurrentBalance)
    {
        this.CurrentBalance += CurrentBalance;
    }
    public void subBalance (int CurrentBalance)
    {
        this.CurrentBalance -= CurrentBalance;
    }
    public boolean isTransactionPossible(int CurrentBalance){
        return (this.CurrentBalance>=CurrentBalance);
    }

}
