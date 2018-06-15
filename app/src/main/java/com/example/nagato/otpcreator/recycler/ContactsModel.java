package com.example.nagato.otpcreator.recycler;

public class ContactsModel {

    private String FirstName, LastName, PhoneNumber;

    public ContactsModel(String FirstName, String LastName, String PhoneNumber)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PhoneNumber = PhoneNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
