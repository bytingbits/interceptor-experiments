package com.apitrial.apitrial.model;

public class TransactionRequest //create a class to represent the request body for a transaction
                                //every request (to the transaction endpoint - which i've named "pay") will have this body
{
    private String username; //field 1: username 
    private int amount;      //field 2: amount

//Getters and Setters:
//These methods allow us to: 
// - access the fields of the class (getter)
// - modify the fields of the class (setter)
public int getAmount() {
    return amount;
}

public void setAmount(int amount) {
    this.amount = amount;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}
}