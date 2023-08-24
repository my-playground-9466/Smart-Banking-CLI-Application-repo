import java.util.Scanner;

public class App {

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

        do{

            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD,screen,RESET);

            //System.out.println(CLEAR);
            System.out.printf("%s\n","-".repeat(30));
            System.out.println(" ".repeat((30 - APP_TITLE.length() +7)/2).concat(APP_TITLE));
            System.out.printf("%s\n","-".repeat(30));

            switch(screen){
                case DASHBOARD:

                    System.out.println("\n[1]. Add New Customer");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Print Account Balance");
                    System.out.println("[6]. Delete Account");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter the option continue >");
                    int option = scanner.nextInt();

                    switch(option){
                        case 1: screen = ADD_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = ACCOUNT_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.exit(0);break;
                        default: continue;
                    }
                    break;

                    case ADD_ACCOUNT:
                        String name;
                        double deposit;
                        boolean valid;
                        String status;

                        String accountNumber = String.format("SDB-%05d",(account.length+1));

                        System.out.printf("\tCustomer Account Number :%s\n", accountNumber);

                        do{
                            valid = true;
                            scanner.nextLine();
                            status = validateName("name");
                            name = status;

                        }while(!valid);

                        do{
                            valid = true;
                            System.out.print("Enter the initial deposit amount Rs:");
                            deposit = scanner.nextDouble();
                            scanner.nextLine();

                            if(deposit < 5000){
                                System.out.printf("%sInsufficciant Amount%s\n",COLOR_RED_BOLD,RESET);
                                valid = false;
                                continue;
                            }

                        }while(!valid);

                        String[][] newAccount = new String[account.length+1][3];
                            //System.out.println("mmmmmm");

                            for(int i = 0;i < account.length; i++){
                                newAccount[i] = account[i];
                            }

                            newAccount[newAccount.length-1][0] = accountNumber;
                            newAccount[newAccount.length-1][1] = name;
                            newAccount[newAccount.length-1][2] = deposit + "";

                            account = newAccount;
                            System.out.println();

                            System.out.println(account[0][1]);

                            System.out.println("Account has been created successfully.");
                            System.out.println("Do you want to continue adding (y/n)? ");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break;

                            case DEPOSIT_MONEY:

                            int index = 0;
                            boolean exist = false;
                            double currentBalance = 0;
                            String accountNum;


                        do{
                            valid = true;
                            System.out.println("Enter your Account Number");
                            accountNum = scanner.nextLine().toUpperCase().strip();
                            scanner.nextLine();
                            
                            boolean status1 = validAccountNumber(accountNum);

                            if(status1 == false){
                                 System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                screen = DASHBOARD;
                                break; 

                            }else{

                            for(int i =0;i<account.length;i++){
                                if(account[i][0].equals(accountNum)){
                                    index = i;
                                    exist = true;
                                    System.out.println("Account holder name :"+account[i][1]);
                                    System.out.println("Current balance is :"+account[i][2]);

                                   
                                  //  valid = true;
                                    double amount;

                                    System.out.print("Enter deposit amount : ");
                                    amount = scanner.nextDouble();
                                    scanner.nextLine();


                                    if(amount<500){
                                        System.out.println("Insufficiant amount.Minimum ammount is 500.Do you want to try again (y/n) ?");
                                        if(scanner.nextLine().toUpperCase().strip().equals("Y")) continue;
                                        valid = false;
                                        screen = DASHBOARD;
                                        break;
                                    }else{
                                        currentBalance = Double.valueOf(account[i][2]) + amount;
                                        account[i][2] = currentBalance + "";
                                    }

                                    System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(account[i][2]));
                                    System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);   
                                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                    screen = DASHBOARD;
                                    break;  
                                
                                            
                                }else{
                                    exist = false;
                                    System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                    screen = DASHBOARD;
                                    break; 
                                }  
                            }
                        }
                        

                            }while(!valid);

            }

        }while(true);
        
    }
    public static  String validateName(String input){

        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0;0m"; 

        boolean valid;
        String value;

        inputvalidation:
        do{
            valid = true;
            String inputValue = String.format("Enter your %s: ",input);
            System.out.println(inputValue);
            value = scanner.nextLine();

            if(value.isBlank()){
                valid=false;
                System.out.printf("%scan't be empty%s",COLOR_RED_BOLD,RESET);
                continue;

            }
            for(int i=0;i<value.length();i++){
                if(!Character.isLetter(value.charAt(i)) ||
                Character.isSpaceChar(value.charAt(i))){
                System.out.printf("%sInvalid Type%s",COLOR_RED_BOLD,RESET);
                valid = false;
                continue inputvalidation;
                }

            }

        }while(!valid);

        

        

        return value;
    }

    public static Boolean validAccountNumber(String input){
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0;0m"; 

        boolean valid;
        String value = input;
        boolean valid1;

    
        do{
            valid = true;
            //scanner.nextLine();
            //String inputValue = String.format("Enter your %s: ",input);
            //System.out.println(inputValue);
            //value = scanner.nextLine().toUpperCase().strip();
            //scanner.nextLine();

            if(value.isBlank()){
                valid=false;
                // System.out.printf("%sDo you want to try again 88888? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                // if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                return valid;
                

                //break;
                

            }else if(!value.startsWith("SDB-") || value.length()!=9){
                valid = false;
                System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                //else break;
                

            }else{
                String number = value.substring(4);
                for(int i=0; i<number.length();i++){
                    if(!Character.isDigit(number.charAt(i))){
                        valid = false;
                        System.out.printf("%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                        if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                        //break;

                    }
                }

            }
            
        }while(!valid);

        return valid;
    }
    
}
