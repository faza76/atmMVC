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
public class ATM {
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
   private DepositSlot depositSlot;
   private int AccountNumberTujuan;
   private BankDatabase bankDatabase; // account information database

   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int DEPOSIT = 3;
   private static final int TRANSFER = 4;
   private static final int EXIT = 5;

   // no-argument ATM constructor initializes instance variables
   public ATM() {
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 1; // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      bankDatabase = new BankDatabase(); // create acct info database
   }

   // start ATM 
   public void run() {
      // welcome and authenticate user; perform transactions
      while (true) {
         // loop while user is not yet authenticated
         while (!userAuthenticated) {
            screen.displayMessageLine("\nWelcome!");       
            authenticateUser(); // authenticate user
         }
         
         performTransactions(); // user is now authenticated
         userAuthenticated = false; // reset before next ATM session
         currentAccountNumber = 0; // reset before next ATM session
         screen.displayMessageLine("\nThank you! Goodbye!");
      }
   }
   
   public void run2(){
       searchUser();
   }

   // attempts to authenticate user against database
   private void authenticateUser() {
      screen.displayMessage("\nPlease enter your account number: ");
      int accountNumber = keypad.getInput(); // input account number
      screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
      int pin = keypad.getInput(); // input PIN
      
      // set userAuthenticated to boolean value returned by database
      userAuthenticated = 
         bankDatabase.authenticateUser(accountNumber, pin);
      
      // check whether authentication succeeded
      if (userAuthenticated) {
         currentAccountNumber = accountNumber; // save user's account #
      } 
      else {
         screen.displayMessageLine(
            "Invalid account number or PIN. Please try again.");
      } 
   } 
   
   private void searchUser(){
       screen.displayMessage("\nPlease enter account number Tujuan: ");
      int accountNumber = keypad.getInput();
      
      userAuthenticated = 
         bankDatabase.hasilSearchAccount(accountNumber);
      if(userAuthenticated){
          screen.displayMessageLine(
            "Account Ditemukan.");
      }else{
          screen.displayMessageLine(
            "Account Tidak Ditemukan.");
      }
   }

   // display the main menu and perform transactions
   private void performTransactions() {
      // local variable to store transaction currently being processed
      Transaction currentTransaction = null;
      
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while (!userExited) {
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenu();

         // decide how to proceed based on user's menu selection
         switch (mainMenuSelection) {
            // user chose to perform one of three transaction types
            case BALANCE_INQUIRY:         

               // initialize as new object of chosen type
               currentTransaction = 
                  createTransaction(mainMenuSelection);

               currentTransaction.execute(); // execute transaction
               break; 
            
               //Langkah 3 Menambahkan case untuk menu kedua
               //dengan format isi seperti case yang pertama
            case WITHDRAWAL:
                //create transaction masih belum dibuat casenya sehingga tambahkan dulu
                currentTransaction = 
                  createTransaction(mainMenuSelection);

               currentTransaction.execute();
            break;
            
            case DEPOSIT:
                //create transaction masih belum dibuat casenya sehingga tambahkan dulu
                currentTransaction = 
                  createTransaction(mainMenuSelection);

               currentTransaction.execute();
            break;
            case TRANSFER:
                //create transaction masih belum dibuat casenya sehingga tambahkan dulu
                currentTransaction = 
                  createTransaction(mainMenuSelection);

               currentTransaction.execute();
            break;
            case EXIT: // user chose to terminate session
               screen.displayMessageLine("\nExiting the system...");
               userExited = true; // this ATM session should end
               break;
            default: // 
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      } 
   } 

   // display the main menu and return an input selection
   private int displayMainMenu() {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View my balance");
      screen.displayMessageLine("2 - Withdraw cash");
      screen.displayMessageLine("3 - Deposit funds");
      screen.displayMessageLine("4 - Transfer");
      screen.displayMessageLine("5 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput(); // return user's selection
   } 
         
   private Transaction createTransaction(int type) {
      Transaction temp = null; 
          
      switch (type) {
         case BALANCE_INQUIRY: 
            temp = new BalanceInquiry(
               currentAccountNumber, screen, bankDatabase);
            break;
         //langkah 4 
         //menambahkan withdrawal dan menginstansiasikan temp sebagai withdrawal.
         case WITHDRAWAL:
             temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
             break;
         
         case DEPOSIT:
             temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad,depositSlot);
             break;
             
         case TRANSFER:
             temp = new Transfer(currentAccountNumber, screen, bankDatabase, keypad, AccountNumberTujuan);
             break;
      }

      return temp;
   } 
}
