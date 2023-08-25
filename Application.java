import java.util.Arrays;
import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0;0m";

        final String DASHBOARD = "Banking Management System";
        final String ADD_ACCOUNT = "Add New Customer";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String ACCOUNT_BALANCE = "Account Balance";
        final String DELETE_ACCOUNT = "Remove Account";

        String screen = DASHBOARD;
        String[][] account = new String[0][3];

        do {
            final String APP_TITLE = String.format("%s%s%s", COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.printf("%s\n", "-".repeat(30));
            System.out.println(" ".repeat((30 - APP_TITLE.length() + 7) / 2).concat(APP_TITLE));
            System.out.printf("%s\n", "-".repeat(30));

            switch (screen) {
                case DASHBOARD:

                    System.out.println("\n[1]. Add New Customer");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Print Account Balance");
                    System.out.println("[6]. Delete Account");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter the option continue > ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            screen = ADD_ACCOUNT;
                            break;
                        case 2:
                            screen = DEPOSIT_MONEY;
                            break;
                        case 3:
                            screen = WITHDRAW_MONEY;
                            break;
                        case 4:
                            screen = TRANSFER_MONEY;
                            break;
                        case 5:
                            screen = ACCOUNT_BALANCE;
                            break;
                        case 6:
                            screen = DELETE_ACCOUNT;
                            break;
                        case 7:
                            System.exit(0);
                            break;
                        default:
                            continue;
                    }
                    break;

                case ADD_ACCOUNT:
                    String name;
                    double deposit;
                    boolean valid;
                    String status;

                    String accountNumber = String.format("SDB-%05d", (account.length + 1));

                    System.out.printf("\tCustomer Account Number :%s\n", accountNumber);
                    System.out.println();

                    do {
                        valid = true;
                        status = validateName("name");
                        name = status;

                    } while (!valid);

                    do {
                        valid = true;
                        System.out.print("Enter the initial deposit amount Rs:");
                        deposit = scanner.nextDouble();
                        scanner.nextLine();

                        if (deposit < 5000) {
                            System.out.printf("%sInsufficciant Amount%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }

                    } while (!valid);

                        String[][] newAccount = new String[account.length + 1][3];

                        for (int i = 0; i < account.length; i++) {
                            newAccount[i] = account[i];
                        }

                        newAccount[newAccount.length - 1][0] = accountNumber;
                        newAccount[newAccount.length - 1][1] = name;
                        newAccount[newAccount.length - 1][2] = deposit + "";

                        account = newAccount;
                        System.out.println();

                        System.out.println("Account has been created successfully.");
                        System.out.println("Do you want to continue adding (y/n)? ");
                        if (scanner.nextLine().strip().toUpperCase().equals("Y"))continue;
                        screen = DASHBOARD;
                        break;

                case DEPOSIT_MONEY:
                    double currentBalance = 0;
                    String accountNum;
                    int status2;

                    do {
                        valid = true;
                        System.out.print("Enter your Account Number :");
                        accountNum = scanner.nextLine().toUpperCase().strip();
                    
                        if(validateAccountNumber(accountNum)){

                            status2 = existingAccountNumber(accountNum,account);

                            if(status2 == -1){
                                System.out.printf("%sAccount number is doesn't exist.%s\n", COLOR_RED_BOLD, RESET);
            

                            }else{

                                System.out.println("Account holder name :"+account[status2][1]);
                                System.out.printf("Current balance is Rs: %.2f\n",Double.valueOf(account[status2][2]));

                                double amount;

                                System.out.print("Enter deposit amount : ");
                                amount = scanner.nextDouble();
                                scanner.nextLine();


                                if(amount<500){
                                    System.out.printf("%sInsufficiant amount.Minimum ammount is Rs.500.%s\n",COLOR_RED_BOLD,RESET);
                                    
                                }else{
                                    currentBalance = Double.valueOf(account[status2][2]) + amount;
                                    account[status2][2] = currentBalance + "";
                                    System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(account[status2][2]));
                                }

                            }
                       
                        }

                     } while (!valid);
                            System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            valid = false;
                            screen = DASHBOARD;
                            break; 

                

                case WITHDRAW_MONEY:

                    do{
                            valid = true;
                            System.out.print("Enter your Account Number :");
                            accountNum = scanner.nextLine().toUpperCase().strip();
                        
                            if(validateAccountNumber(accountNum)){

                                status2 = existingAccountNumber(accountNum,account);

                                    if(status2 == -1){
                                        System.out.printf("%sAccount is doesn't exist%s\n", COLOR_RED_BOLD, RESET);
                                        
                                    }else{
                                        System.out.printf("Current Balance is Rs: %.2f\n",Float.valueOf(account[status2][2]));
                                        System.out.print("Enter your withdrawal amount Rs :");
                                        double amount = scanner.nextDouble();
                                        scanner.nextLine();

                                        if(amount<100){
                                            System.out.printf("%sInsufficiant amount.Minimum ammount is Rs.100.%s\n",COLOR_RED_BOLD,RESET);
                                        
                                        }else{

                                            if(Double.valueOf(account[status2][2])<(amount+500)){
                                                System.out.printf("%sDo you have insufficiant account balance.%s\n",COLOR_RED_BOLD,RESET);
                                                
                                            }else{
                                                currentBalance = Double.valueOf(account[status2][2]) - amount;
                                                account[status2][2]= currentBalance + "";

                                                System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(account[status2][2]));
                                                
                                            }
                                        }

                                    }
                            }
                            
                        
                    }while(!valid);
                        
                                System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                screen = DASHBOARD;
                                break;


                
                case TRANSFER_MONEY:
                    int indexFrom = -1;
                    int indexTo = -1;
                    double currentBalanceFrom =0;
                    double currentBalanceTo =0;

                    do{
                            valid = true;
                            System.out.print("Enter your From Account Number :");
                            accountNum = scanner.nextLine().toUpperCase().strip();

                            if(validateAccountNumber(accountNum)){
                                status2 = existingAccountNumber(accountNum,account);

                                    if(status2 == -1){
                                        System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);

                                    }else{
                                        indexFrom = status2;

                                        System.out.print("Enter your To Account Number :");
                                        accountNum = scanner.nextLine().toUpperCase().strip();
                                                                               
                                         if(validateAccountNumber(accountNum)){
                                            int status4 = existingAccountNumber(accountNum,account);

                                            if(status2 == -1){
                                                System.out.printf("%sAccount number doesn't exist%s\n", COLOR_RED_BOLD, RESET);
                                                
                                            }else{
                                               
                                                indexTo = status4;

                                                System.out.printf("Balance of from account Rs:  %.2f\n",Double.valueOf(account[indexFrom][2]));
                                                System.out.printf("Balance of to account Rs:  %.2f\n",Double.valueOf(account[indexTo][2]));
                                                
                                                System.out.print("Enter transfer amount Rs:");
                                                double tranferAmount = scanner.nextDouble();
                                                scanner.nextLine();
                                
                                                if(tranferAmount<100){
                                                    System.out.printf("%sInsufficiant amount.Minimum ammount is Rs.100.%s\n",COLOR_RED_BOLD,RESET);
                                                    valid = false;
                                                    continue;
                                
                                                }else{
                                                    if(Double.valueOf(account[indexFrom][2])<(tranferAmount+500)){
                                                        System.out.printf("%sInsufficiant amount.%s\n",COLOR_RED_BOLD,RESET);
            
                                                    }else{
                                                        currentBalanceFrom = (Double.valueOf(account[indexFrom][2])-tranferAmount) - ((Double.valueOf(account[indexFrom][2])-tranferAmount)*2)/100.0;
                                                        currentBalanceTo = Double.valueOf(account[indexTo][2]) + tranferAmount;
                                
                                                        account[indexFrom][2]=currentBalanceFrom + "";
                                                        account[indexTo][2]=currentBalanceTo + "";
                                
                                                        System.out.println("New current balnace of from account  Rs: "+currentBalanceFrom);
                                                        System.out.println("New current balance of to account : " + currentBalanceTo);
                                
                                                    }
                                                }

                                            }

                                         }
                                    }
                            }
                            

                    }while(!valid);

                            System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break; 





                case ACCOUNT_BALANCE: 
                
                    do{
                            valid = true;
                            System.out.print("Enter your Account Number :");
                            accountNum = scanner.nextLine().toUpperCase().strip();

                            if(validateAccountNumber(accountNum)){
                                status2 = existingAccountNumber(accountNum,account);

                                    if(status2 == -1){
                                        System.out.printf("%sAccount number doesn't exist.(Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                    
                                    }else{
                                        System.out.println("Account holder name :" + account[status2][1]);
                                        System.out.printf("Current Balance is Rs:%.2f\n",Float.valueOf(account[status2][2]));

                                    }
                            }

                    }while(!valid);
                            System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break; 



                case DELETE_ACCOUNT:

                    int index=-1;
                    String[][] newBankAccount = new String[account.length-1][3];
                    
                    do{
                            valid = true;
                            System.out.print("Enter your Account Number :");
                            accountNum = scanner.nextLine().toUpperCase().strip();

                                if(validateAccountNumber(accountNum)){
                                    status2 = existingAccountNumber(accountNum,account);
                                    if(status2 == -1){
                                        System.out.printf("%sAccount number doesn't exist. (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                        
                                    }else{
                                        index = status2;
                                        for(int i=0;i<account.length;i++){
                                                if(index>i){
                                                    newBankAccount[i]=account[i];
                                                }else if(index == i){
                                                        continue;
                                                }else{
                                                    newBankAccount[i-1]=account[i];
                                                }
                                        }
                        
                                        account=newBankAccount;
                        
                                        System.out.println("Account is deleted Successfully");
                                    }
                                }
                                    

                    }while(!valid);
                        System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                        if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                        screen = DASHBOARD;
                        break; 


                }

        } while (true);

    }

    

    public static String validateName(String input) {

        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0;0m";

        boolean valid;
        String value;

        inputvalidation: do {
            valid = true;
            String inputValue = String.format("Enter your %s:", input);
            System.out.print(inputValue);
            value = scanner.nextLine();

            if (value.isBlank()) {
                valid = false;
                System.out.printf("%scan't be empty%s \n", COLOR_RED_BOLD, RESET);
                continue;

            }
            for (int i = 0; i < value.length(); i++) {
                if (!Character.isLetter(value.charAt(i)) ||
                        Character.isSpaceChar(value.charAt(i))) {
                    System.out.printf("%sInvalid Type%s", COLOR_RED_BOLD, RESET);
                    valid = false;
                    continue inputvalidation;
                }

            }

        } while (!valid);

        return value;
    }

    public static Boolean validateAccountNumber(String input) {

        if (input.isBlank()) {
            return false;

        } else if (!input.startsWith("SDB-") || input.length() != 9) {
            return false;

        } else {
            String number = input.substring(4);
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int existingAccountNumber(String input, String[][] customer){
      
        int index = -1;
        for(int i =0;i<customer.length;i++){
            if(customer[i][0].equals(input)){
                index = i;
                return i;
               
            }
        }

        return -1;
    }

}
