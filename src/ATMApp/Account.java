/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMApp;

/**
 *
 * @author Azukhrufy
 */
public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private int point;

   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance, int poin) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      //Langkah 2
      //Menambahkan Contructor untuk availableBalance dan Total Balance
      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
      point = poin;
   }

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      }
      else {
         return true;
      }
   } 

   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   } 
   public int getPoint(){
       return point;
   }
   
   public void poinplus(int amount){
       point += amount; 
   }
   
   public void pakaipoin(int amount){
       if(amount <= point){
           point -= amount;
       }
       
   }
   public void credit(double amount) {
     totalBalance += amount;
   }
   public void debit(double amount) {
       
       if(amount <= availableBalance){
            availableBalance -= amount;
            totalBalance -= amount;
       }
   }
   public void transfer(double amount){
       if(amount <= totalBalance) totalBalance -= amount; }

   public int getAccountNumber() {
      return accountNumber;  
   }
} 
