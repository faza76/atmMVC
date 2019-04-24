package ATMApp;
public class Transfer extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private boolean userAuthenticated = false;
   private final static int CANCELED = 0; // constant for cancel option
    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad,int AccountNumberTujuan) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;}
   @Override
   public void execute() {
       amount = TransferAmount();
       if(amount == CANCELED){
           screen.displayMessage("Canceling transaction...");}
       else{screen.displayMessage("Masukkan No Rek Tujuan : ");
           int accNumber = keypad.getInput();
           //sistem.run2();
           boolean userAuthenticated = bankDatabase.hasilSearchAccount(accNumber);
                if(userAuthenticated){
                    screen.displayMessageLine("Account Ditemukan.");
                    if(super.getBankDatabase().getTotalBalance(super.getAccountNumber())>= amount){
                    super.getBankDatabase().transfer(super.getAccountNumber(), amount);
                    super.getBankDatabase().credit(accNumber, amount);}
                   else{
                    screen.displayMessage("Balance Tidak Cukup");}  
                }else{
                    screen.displayMessageLine(
                      "Account Tidak Ditemukan.");
                    amount = 6;
                    execute();}
       }
   }
   private double TransferAmount() {
      Screen screen = getScreen();
      screen.displayMessage("\nPlease enter Amount For Transfer " + 
         " or 0 to cancel: ");
      int input = keypad.getInput();
      if (input == CANCELED) {return CANCELED;}
      else {
         return (double) input / 100; // return dollar amount
      }
   }
}