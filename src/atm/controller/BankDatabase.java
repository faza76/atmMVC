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
public class BankDatabase {
   private Account[] accounts; // array of Accounts
   
   public BankDatabase() {
      accounts = new Account[3]; // just 3 accounts for testing
      accounts[0] = new Account(1234, 4321, 1000.0, 1200.0,0);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0,0);
      accounts[2] = new Account(8763, 5678, 200.0, 200.0,0);
   }
   
   //Langkah 1
   //buat ngebandingin user account yang di input sama account yang ada di array
   
   public Account getAccount(int accountNumber) {
      for(Account ac : accounts){
          if(ac.getAccountNumber() == accountNumber){
              return ac;
          }
      }
      return null;
   } 

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);
      // if account exists, return result of Account method validatePIN
       
      if (userAccount != null) {
          return userAccount.validatePIN(userPIN);
      }
      else {
          return false; // account number not found, so return false
      }
   }
   
   public boolean hasilSearchAccount(int userAccountNumber){
       Account userAccount = getAccount(userAccountNumber);
       if (userAccount != null) {
          System.out.println("Data Akun Ditemukan");
          return true;
      }
      else {
          return false; // account number not found, so return false
      }
   }

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 
   
   public void transfer(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).transfer(amount);} 
} 
