import java.util.*;
import java.time.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class demo{
    //public static CustomerList customerList = new CustomerList();
    public static void main(String[] args) {
        homePage();
    }

    
    //Home page Method
    public static void homePage(){
        Scanner input = new Scanner(System.in);
        printMainMenue();
        int option = input.nextInt();

        switch(option){
            case 1: addContacts(option); break;
            case 2: updateContacts(option); break;
            case 3: deleteContacts(option); break;
            case 4: searchContacts(option); break;
            case 5: listContacts(option); break;
            default: System.out.println("\t\tThank you for using iFriend!");
        }
    }

    //Clear Console Method
    public final static void clearConsole() {
        try {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        }
        } catch (final Exception e) {
        e.printStackTrace();
        // Handle any exceptions.
        }
    
    }

    //Main Menue Print Method
    public static void printMainMenue(){
        System.out.println("  \n                /$$ /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$   /$$ /$$$$$$$ ");
        System.out.println("                |_/ | $$_____/| $$_  $$|_  $$/| $$_____/| $$$ | $$| $$_  $$");
        System.out.println("                 /$$| $$      | $$  \\ $$  | $$  | $$      | $$$$| $$| $$  \\ $$");
        System.out.println("                | $$| $$$$$   | $$$$$$$/   | $$  | $$$$$   | $$ $$ $$| $$  | $$");
        System.out.println("                | $$| $$_/    | $$_  $$    | $$  | $$__/   | $$  $$$$| $$  | $$");
        System.out.println("                | $$| $$      | $$  \\ $$  | $$  | $$      | $$\\  $$$| $$  | $$");
        System.out.println("                | $$| $$      | $$  | $$ /$$$$$$| $$$$$$$$| $$ \\  $$| $$$$$$$/");
        System.out.println("                |__/|__/      |__/  |__/|______/|________/|__/  \\__/|_______/   \n\n");
        System.out.println("      _____            _             _          ____                        _              ");
        System.out.println("     / ____|          | |           | |        / __ \\                      (_)             ");
        System.out.println("    | |     ___  _ __ | |_ __ _  __| | ___  | |  | |_ __ __ _  __ _ _ __  _ _______ _ __ ");
        System.out.println("    | |    / _ \\| '_ \\| _/ _` |/ __| __/ __| | |  | | '__/ _` |/ _` | ' \\| |_  / _ \\ '__|");
        System.out.println("    | |__| (_) | | | | || (_| | (__| | \\_ \\ | |__| | | | (_| | (_| | | | | |/ /  __/ |   ");
        System.out.println("     \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|___/  \\____/|_|  \\__, |\\__,_|_| |_|_/___\\___|_|   ");
        System.out.println("                                                           __/ |                           ");
        System.out.println("                                                          |___/     \n                       ");
        System.out.println("===============================================================================================\n\n     ");

        System.out.println("[01] ADD Contacts");
        System.out.println("[02] UPDATE Contacts");
        System.out.println("[03] DELETE Contacts");
        System.out.println("[04] SEARCH Contacts");
        System.out.println("[05] LIST Contacts");
        System.out.println("[06] Exit\n");
        System.out.print("Enter an option to contuniue -> ");
    }
    
    //=====================[05]Methods related to SORT CONTACTS option=======================
    //Printing the list contacts options and take the user input
    public static void listContacts(int option){
        clearConsole();
        sortContactsPrint();
        sortContactOptionSelect();

    } 

    //Header of the sorting table
    public static void listByHeaderPrint(){
        System.out.println("+--------------+----------+----------------+------------+------------+------------+");
        System.out.println("|  Contact ID  |   Name   |  Phone Number  |   Company  |   Salary   |  Birthday  |");
        System.out.println("+--------------+----------+----------------+------------+------------+------------+");
    }

    public static void listByNamePrint(){
        System.out.println("\t\t+---------------------------------------------------+");
        System.out.println("\t\t|               LIST Contact by Name                |");
        System.out.println("\t\t+---------------------------------------------------+");  
    }
    
    public static void sortContactsPrint(){
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                    SORT Contact                   |");
        System.out.println("+---------------------------------------------------+");         
        System.out.println("\n");
        System.out.println("\t[01] Sorting by Name");
        System.out.println("\t[02] Sorting by Salary");
        System.out.println("\t[03] Sorting by Birthday");
        System.out.println("\n");
    }
    
    //Method for taking the user input in the sorting menue
    public static void sortContactOptionSelect(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an option to continue -> ");
        int opS = input.nextInt();

        switch(opS){
            case 1: SortingByName(); break;
            case 2: SortingBySalary(); break;
            case 3: SortingByBirthday(); break;
        }
    }

    //======(01)sortByName============
    public static void SortingByName(){
        clearConsole();
        listByNamePrint();
        listByHeaderPrint();

        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        Customer[] tempCusArray = new Customer[customerList.size()];

        for(int i=0; i < customerList.size(); i++){
            tempCusArray[i] = customerList.get(i).getCopy();
        }

        int n = customerList.size();
        
        // Bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tempCusArray[j].getName().compareTo(tempCusArray[j+1].getName()) > 0) {
                    // Swap Contact
                    Customer temp = tempCusArray[j];
                    tempCusArray[j] = tempCusArray[j+1];
                    tempCusArray[j+1] = temp;

                }
            }
        }

        // Print the sorted array
        for (int i = 0; i < n; i++) {
            Customer c1 = tempCusArray[i];
            System.out.printf("| %-12s | %-8s | %-14s | %-10s | %-10.2f | %-10s |%n", c1.getCustID(), c1.getName(), c1.getTpNumber(),
                    c1.getCompany(), c1.getSalary(), c1.getBirthday());
        }
        System.out.println("+--------------+----------+----------------+------------+------------+------------+");

        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to go to Home Page (Y/N) : ");
        String yn = input.next();
        if (yn.equalsIgnoreCase("Y")){
            clearConsole();
            homePage();
        }else if(yn.equalsIgnoreCase("N")){
            clearConsole();
            homePage();
        }

    }

    //======(02)sortBySalary============
    public static void SortingBySalary(){
        clearConsole();
        listBySalaryPrint();
        listByHeaderPrint();

        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        Customer[] tempCusArray = new Customer[customerList.size()];

        for(int i=0; i < customerList.size(); i++){
            tempCusArray[i] = customerList.get(i).getCopy();
        }

        int n = customerList.size();
        
        // Bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tempCusArray[j].getSalary() > tempCusArray[j + 1].getSalary()) {
                    // Swap Contact
                    Customer temp = tempCusArray[j];
                    tempCusArray[j] = tempCusArray[j+1];
                    tempCusArray[j+1] = temp;
                }
            }
        }

        // Print the sorted array
        for (int i = 0; i < n; i++) {
            System.out.printf("| %-12s | %-8s | %-14s | %-10s | %-10.2f | %-10s |%n", tempCusArray[i].getCustID(), tempCusArray[i].getName(), tempCusArray[i].getTpNumber(),
                    tempCusArray[i].getCompany(), tempCusArray[i].getSalary(), tempCusArray[i].getBirthday());
        }
        System.out.println("+--------------+----------+----------------+------------+------------+------------+");

        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to go to Home Page (Y/N) : ");
        String yn = input.next();
        if (yn.equalsIgnoreCase("Y")){
            clearConsole();
            homePage();
        }else if(yn.equalsIgnoreCase("N")){
            clearConsole();
            homePage();
        }
    }
    
    public static void listBySalaryPrint(){
        System.out.println("\t\t+---------------------------------------------------+");
        System.out.println("\t\t|               LIST Contact by Salary              |");
        System.out.println("\t\t+---------------------------------------------------+");  
    }

    //======(03)sortByBirthday============
    public static void SortingByBirthday(){
        clearConsole();
        listByBirthdayPrint();
        listByHeaderPrint();

        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        Customer[] tempCusArray = new Customer[customerList.size()];

        for(int i=0; i < customerList.size(); i++){
            tempCusArray[i] = customerList.get(i).getCopy();
        }

        int n = customerList.size();
        
        // Bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tempCusArray[j].getBirthday().compareTo(tempCusArray[j+1].getBirthday()) > 0) {
                    // Swap Contact
                    Customer temp = tempCusArray[j];
                    tempCusArray[j] = tempCusArray[j+1];
                    tempCusArray[j+1] = temp;

                }
            }
        }

        // Print the sorted array
        for (int i = 0; i < n; i++) {
            System.out.printf("| %-12s | %-8s | %-14s | %-10s | %-10.2f | %-10s |%n", tempCusArray[i].getCustID(), tempCusArray[i].getName(), tempCusArray[i].getTpNumber(),
                    tempCusArray[i].getCompany(), tempCusArray[i].getSalary(), tempCusArray[i].getBirthday());
        }
        System.out.println("+--------------+----------+----------------+------------+------------+------------+");

        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to go to Home Page (Y/N) : ");
        String yn = input.next();
        if (yn.equalsIgnoreCase("Y")){
            clearConsole();
            homePage();
        }else if(yn.equalsIgnoreCase("N")){
            clearConsole();
            homePage();
        }

    }

    public static void listByBirthdayPrint(){
        System.out.println("\t\t+---------------------------------------------------+");
        System.out.println("\t\t|             LIST Contact by Birthday              |");
        System.out.println("\t\t+---------------------------------------------------+");  
    }
    
    //=====================[04]Methods related to SEARCH CONTACTS option=======================
    
    public static void searchContactsPrint(){
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  SEAECH Contact                   |");
        System.out.println("+---------------------------------------------------+");

    }

    public static void searchContacts(int option){
        Scanner input = new Scanner(System.in);

        L2:do{
            L1:do{
                clearConsole();
                searchContactsPrint();
                System.out.print("Search Contact by Name or Phone Number - ");
                String userInput = input.next();
                
                // Search the contact and store the index of that contact
                int index = searchContacts(userInput);
        
                // Checking the validation of the input
                if (index != -1) {
                    printUserSearchedContact(index);
                    System.out.print("Do you want to Search again (Y/N) : ");
                    String yn = input.next();
                    if (yn.equalsIgnoreCase("Y")){
                        continue L1;
                    }else if (yn.equalsIgnoreCase("N")){
                        clearConsole();
                        homePage();
                        break;
                    }
    
                } else {
                    System.out.println("\t\tNo contact found for " + userInput + " ...");
                    System.out.print("Do you want to Search again (Y/N) : ");
                    String yn = input.next();
                    if (yn.equalsIgnoreCase("Y")) {
                        clearConsole();
                        continue L2;
                    } else if (yn.equalsIgnoreCase("N")) {
                        clearConsole();
                        homePage();
                        break;
                    }
                }
            }while(true);
        }while(true);

    }
    
    //=====================[03]Methods related to DELETE CONTACTS option=======================
    public static void deleteContacts(int option) {
        Scanner input = new Scanner(System.in);
        CustomerList customerList = DBConnection.getInstance().getCustomerList();

        L1:do {
            clearConsole();
            deleteContactsPrint();
            // Take the user input to search the contact
            System.out.print("Search Contact by Name or Phone Number - ");
            String userInput = input.next();

            // Search the contact and store the index of that contact
            int index = searchContacts(userInput);

            // Checking the validation of the input
            if (index != -1) {
                printUserSearchedContact(index);
            } 
            if (index == -1) {
                System.out.println("\t\tNo contact found for " + userInput + " ...");
                System.out.print("Do you want to Search again (Y/N) : ");
                do L2:{
                    String yn = input.next();
                    if (yn.equalsIgnoreCase("Y")) {
                        clearConsole();
                        continue L1;
                    } else if (yn.equalsIgnoreCase("N")) {
                        clearConsole();
                        homePage();
                        break L1;
                    }
                System.out.print("\033[2A");
                System.out.print("\033[0J");
                }while(true);
            }

            System.out.print("Do you want to delete this Contact (Y/N) : ");
            String yn = input.next();
            if (yn.equalsIgnoreCase("Y")) {
                customerList.remove(index);
                System.out.println("\tContact has been deleted successfully...\n");
            L3: do{
                System.out.print("Do you want to delete another Contact (Y/N) : ");
                yn = input.next();
                if (yn.equalsIgnoreCase("Y")) {
                    clearConsole();
                    continue L1;
                } else if (yn.equalsIgnoreCase("N")) {
                    clearConsole();
                    homePage();
                    break L1;
                }
            System.out.print("\033[2A");
            System.out.print("\033[0J");
            }while(true);
            }else{
                clearConsole();
                homePage();
            }
        } while (true);

    }


    public static void deleteContactsPrint() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  DELETE Contact                   |");
        System.out.println("+---------------------------------------------------+");
    }
    
    public static int searchContacts(String userInput) {
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        for(int i=0; i<customerList.size(); i++){
            Customer customer = customerList.get(i);
            if( customer.getName().equalsIgnoreCase(userInput) || customer.getTpNumber().equals(userInput)){
                return i;
            }
        }
        return -1;
    }

    //=====================[02]Methods related to UPDATE option=======================
    
    public static void updateContactsPrint(){
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  UPDATE Contact                   |");
        System.out.println("+---------------------------------------------------+");

    }
    
    public static void updateContacts(int option){
        clearConsole();
        updateContactsPrint();

        String getUserIn = getUserInput();
        //Search and print the user input contact
        printSearchedContact(getUserIn);

        updateContactOptionsPrint();

        //Getting the index of the user serached array
        int index = returnIndex(getUserIn);
        
        updateContactOptionSelect(index);
   
    }

    public static String getUserInput(){
        Scanner input = new Scanner(System.in);            
        System.out.print("Search Contact by Name or Phone Number - ");
        String userInput = input.next();
        return userInput;
    }

    //Take the user input
    //Search the name and store the index in a variable
    //If the index is valid print or else take another input by the user
    public static void printSearchedContact(String getUserIn){
        Scanner input = new Scanner(System.in);
    
            if(returnIndex(getUserIn) != -1){
                printUserSearchedContact(returnIndex(getUserIn));
            }else{
                System.out.println("\t\tNo contact found for " + getUserIn + " ...");
                System.out.print("Do you want to Search again (Y/N) : ");
                String yn = input.next();
                if(yn.equalsIgnoreCase("Y")){
                    updateContacts(returnIndex(getUserIn));
                }else if(yn.equalsIgnoreCase("N")){
                    homePage();
                }
            }         
     }

    public static void printUserSearchedContact(int index){
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        Customer c1 = customerList.get(index);
        System.out.println("\n");
        System.out.println("\tContact ID\t\t: " + c1.getCustID());
        System.out.println("\tName\t\t\t: " + c1.getName());
        System.out.println("\tPhone Number\t\t: " + c1.getTpNumber());
        System.out.println("\tCompany Name\t\t: " + c1.getCompany());
        System.out.println("\tSalary\t\t\t: " + c1.getSalary());
        System.out.println("\tB'Day(YYYY-MM-DD)\t: " + c1.getBirthday());
        System.out.println("\n");
    }

    public static void updateContactOptionsPrint(){
        System.out.println("\tWhat do you want to update...\n");
        System.out.println("\t[01] Name");
        System.out.println("\t[02] Phone Number");
        System.out.println("\t[03] Comapny Name");
        System.out.println("\t[04] Salary");
        System.out.println("\n");
    }

    public static void updateContactOptionSelect(int index){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an option to continue -> ");
        int opS = input.nextInt();

        switch(opS){
            case 1: nameUpdate(index); break;
            case 2: phoneNumberUpdate(index); break;
            case 3: companyNameUpdate(index); break;
            case 4: sarlaryUpdate(index); break;
        }
    }

    public static int returnIndex(String userInput){
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        for(int i=0; i<customerList.size(); i++){
            Customer customer = customerList.get(i);
            if( customer.getName().equalsIgnoreCase(userInput) || customer.getTpNumber().equals(userInput)){
                return i;
            }
        }
        return -1;
    }

    //====(01)Update Name Method======
    public static void nameUpdate(int index){
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        Scanner input = new Scanner(System.in);
        System.out.println("\nUpdate Name");
        System.out.println("===============");
        System.out.print("Input new name - ");
        String getUserInput = input.next();
        customerList.get(index).setName(getUserInput);
        System.out.println("\n");
        System.out.println("\tName has been updated successfully...");
        //updatedNamePrint(ind);
        System.out.println("\n");
        System.out.print("Do you want to update another contact (Y/N) -> ");
        String yn = input.next();
        if(yn.equalsIgnoreCase("Y")){
            clearConsole();
            updateContacts(index);
        }
        if((yn.equalsIgnoreCase("N"))){
            clearConsole();
            homePage();
        }
        
    }

    //====(02)Update Phone Number Method======
    public static void phoneNumberUpdate(int index){
        Scanner input = new Scanner(System.in);
        System.out.println("\nUpdate Phone Number");
        System.out.println("=======================");
        //Add new tp number and validate
        addUpdatedPhoneNumber(index);
        System.out.println("\n");
        System.out.println("\tPhone number has been updated successfully...");        
        System.out.println("\n");
        System.out.print("Do you want to update another contact (Y/N) -> ");
        String yn = input.next();
        if(yn.equalsIgnoreCase("Y")){
            clearConsole();
            updateContacts(index);
        }else if((yn.equalsIgnoreCase("N"))){
            clearConsole();
            homePage();
        }
    }
    

    public static void addUpdatedPhoneNumber(int index){
        Scanner input = new Scanner(System.in);
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        L1:do{
            System.out.print("Enter new Phone Number\t: ");
            String phoneNumber = input.next();
            boolean PNvalid = isValidPhoneNumber(phoneNumber);
    
            if(isValidPhoneNumber(phoneNumber)){
                customerList.get(index).setTpNumber(phoneNumber);
                break;
            }
            L2:do{
                if(!isValidPhoneNumber(phoneNumber)){
                    System.out.println("\t\tInvalid phone number...");
                    System.out.print("Do you want to add phone number again (Y/N) : ");
                    String yn = input.next();
                    if(yn.equalsIgnoreCase("Y")){
                        //Clears 3 rows upward
                        System.out.print("\033[3A");
                        //Clears the user input in the 3rd row
                        System.out.print("\033[0J");
                        //If the user input is "y" the code runes from the start of the do while loop labeled L1
                        continue L1;
                    }else if(yn.equalsIgnoreCase("N")){
                        break L1;
                    }
                    System.out.print("\033[2A");
                    System.out.print("\033[0J");
                }
            }while (true);
        }while(true);
    }

    //====(03)Update the Compnay Name Method======
    public static void companyNameUpdate(int index){
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        Scanner input = new Scanner(System.in);
        System.out.println("\nUpdate the Compnay Name");
        System.out.println("==========================");
        System.out.print("Input new compnay name - ");
        String getUserInput = input.next();
        customerList.get(index).setCompany(getUserInput);
        System.out.println("\n");
        System.out.println("\tComany name has been updated successfully...");
        System.out.println("\n");
        System.out.print("Do you want to update another contact (Y/N) -> ");
        String yn = input.next();
        if(yn.equalsIgnoreCase("Y")){
            clearConsole();
            updateContacts(index);
        }else if((yn.equalsIgnoreCase("N"))){
            clearConsole();
            homePage();
        }
    }


    //====(04)Update the Salary Method======
    public static void sarlaryUpdate(int index){
        Scanner input = new Scanner(System.in);
        System.out.println("\nUpdate the Salary");
        System.out.println("====================");
        //Input sal, validate and update
        addUpdatedSalary(index);
        System.out.println("\n");
        System.out.println("\tSalary has been updated successfully...");        
        System.out.println("\n");
        System.out.print("Do you want to update another contact (Y/N) -> ");
        String yn = input.next();
        if(yn.equalsIgnoreCase("Y")){
            clearConsole();
            updateContacts(index);
        }else if((yn.equalsIgnoreCase("N"))){
            clearConsole();
            homePage();
        }
    }

    public static void addUpdatedSalary(int index){
        L2:do{
            CustomerList customerList = DBConnection.getInstance().getCustomerList();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the updated salary\t: ");
            double sal = input.nextDouble();
            boolean SALval = isSalValid(sal);

            if(isSalValid(sal)){
                customerList.get(index).setSalary(sal);
                break;
            }else{
                System.out.println("\t\tInvalid input...");
                System.out.print("Do you want to update the salary again (Y/N) : ");
                String yn = input.next();
                if(yn.equalsIgnoreCase("Y")){
                    //Clears 3 rows upward
                    System.out.print("\033[3A");
                    //Clears the user input in the 3rd row
                    System.out.print("\033[0J");
                    //If the user input is "y" the code runes from the start of the do while loop labeled L1
                    continue L2;
                }else if(yn.equalsIgnoreCase("N")){
                    break;
                }
            }
        }while(true);
    }
    
    //====================[01]Methods related to ADD CONTACTS option=========================

    //Generate Contact ID ,Extend the array, Print, add id to the Customer object array
    public static String printCID(){
        DBConnection dbConnection = DBConnection.getInstance();
        CustomerList customerList = dbConnection.getCustomerList();
        String id = String.format("C%04d",(customerList.size()+1));
        System.out.println(id);
        System.out.println("=============");
        return id;
    }
    

    public static void addContactsPrint(){
        System.out.println("+---------------------------------------------------+");
		System.out.println("|            ADD Contact to the list                |");
		System.out.println("+---------------------------------------------------+");

    }

    //Input a new name and store it in the name in customer object
    public static String printInputName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Name\t\t\t: ");
        return input.next();
    }

    //Input the phone number and validate
    public static String printInputPhoneNumber(){
        Scanner input = new Scanner(System.in);
        L1:do{
            System.out.print("Phone Number\t\t: ");
            String phoneNumber = input.next();
            boolean PNvalid = isValidPhoneNumber(phoneNumber);
    
            if(isValidPhoneNumber(phoneNumber)){
                return phoneNumber;
            }
            L2:do{
                if(!isValidPhoneNumber(phoneNumber)){
                    System.out.println("\t\tInvalid phone number...");
                    System.out.print("Do you want to add phone number again (Y/N) : ");
                    String yn = input.next();
                    if(yn.equalsIgnoreCase("Y")){
                        //Clears 3 rows upward
                        System.out.print("\033[3A");
                        //Clears the user input in the 3rd row
                        System.out.print("\033[0J");
                        //If the user input is "y" the code runes from the start of the do while loop labeled L1
                        continue L1;
                    }else if(yn.equalsIgnoreCase("N")){
                        break L1;
                    }
                    System.out.print("\033[2A");
                    System.out.print("\033[0J");
                }
            }while (true);
        }while(true);
        return null;
    }

    //Validation for the phone number
    public static boolean isValidPhoneNumber(String phoneNumber){
        // Check if the phone number is null or empty
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        
        // Check if the phone number starts with a zero
        if (phoneNumber.charAt(0) != '0') {
            return false;
        }

        //Check all the numbers are digits
        for(int i=0; i<phoneNumber.length(); i++){
            if(!Character.isDigit(phoneNumber.charAt(i))){
                return false;
            }
        }

        if(phoneNumber.length() != 10){
            return false;
        }
        
        String[] sProviderCode={"70","71","72","74","75","76","77","78","11","36","31","33","38","34","81","54","51","52","66","91","41","47","21","23","24","63","65","67","26","25","27","32","37","55","57","45","35"};
        for(int i=0; i<sProviderCode.length; i++){
            if(phoneNumber.substring(1, 3).equals(sProviderCode[i])){
                return true;
            }
        }

        return false;
    } 
    
    public static String printCompanyName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Compnay\t\t\t: ");
        return input.next();
    }

    //Input the salary and print it after validating
    public static double printSalary(){
        L2:do{
            Scanner input = new Scanner(System.in);
            System.out.print("Salary\t\t\t: ");
            double sal = input.nextDouble();
            boolean SALval = isSalValid(sal);
    
            if(isSalValid(sal)){
                return sal;
            }
            L3:do{
                if(!isSalValid(sal)){
                    System.out.println("\t\tInvalid input...");
                    System.out.print("Do you want to add the salary again (Y/N) : ");
                    String yn = input.next();
                if(yn.equalsIgnoreCase("Y")){
                    //Clears 3 rows upward
                    System.out.print("\033[3A");
                    //Clears the user input in the 3rd row
                    System.out.print("\033[0J");
                    //If the user input is "y" the code runes from the start of the do while loop labeled L1
                    continue L2;
                }else if(yn.equalsIgnoreCase("N")){
                    break L2;
                }
                System.out.print("\033[2A");
                System.out.print("\033[0J");
                }
            }while(true);
        }while(true);
        return 0.0;
    }

    //Validation for the salary
    public static boolean isSalValid(double sal){
        if(sal<0){
            return false;
        }
        return true;
    }

        //Input the birthday and store it after validating
        public static String printBirthday(){
            Scanner input = new Scanner(System.in);
            L2:do{
                System.out.print("B'Day(YYYY-MM-DD)\t: ");
                String bday = input.next();
                boolean BDval = isBdayValid(bday);
                if(isBdayValid(bday)){
                    return bday;
                }
                L3:do{
                    if(!isBdayValid(bday)){
                        System.out.println("\t\tInvalid birthday...");
                        System.out.print("Do you want to add birthday again (Y/N) : ");
                        String yn = input.next();
                        if(yn.equalsIgnoreCase("Y")){
                            System.out.print("\033[3A");
                            System.out.print("\033[0J");
                            continue L2;
                        }else if((yn.equalsIgnoreCase("N"))){
                            clearConsole();
                            //printCustomerList();
                            homePage();
                            break L2;
                        }
                    System.out.print("\033[2A");
                    System.out.print("\033[0J");
                    }
                }while(true);
            }while(true);
            return null;
        }
        
        // Validating the birthday
        public static boolean isBdayValid(String bday) {
            // Isolating the string related to year, month, and day (from the user input)
            String y = bday.substring(0, 4);
            String m = bday.substring(5, 7);
            String d = bday.substring(8, 10);
    
            // Converting the string to integers
            int yy = Integer.parseInt(y);
            int mm = Integer.parseInt(m);
            int dd = Integer.parseInt(d);
    
            // Taking the current year, month, and the day
            LocalDate currentDate = LocalDate.now();
    
            int year = currentDate.getYear();
            int month = currentDate.getMonthValue();
            int day = currentDate.getDayOfMonth();
    
            if(bday.length() != 10 || bday.charAt(4) != '-' || bday.charAt(7) != '-') {
                return false;
            }else if (yy < 1800 || yy > year) {
                return false;
            }else if (mm > 12 || mm < 1) {
                return false;
            }else if (dd >= 31 || dd < 1) {
                return false;
            }else if (mm == 2 && dd == 29 && !isLeapYear(yy)) { // Check for February 29th and non-leap year
                return false;
            }else if (mm == 4 || mm == 6 || mm == 9 || mm == 11) { // Check for months with 30 days
                return dd <= 30;
            }
    
            return true;
        }
    
        // Check if a year is a leap year
        public static boolean isLeapYear(int year) {
            return year % 4 == 0;
        }



    //[01]Add Contacts Option
    public static void addContacts(int option){
        Scanner input = new Scanner(System.in);
        L1:do{
            clearConsole();
            addContactsPrint();
            
            //Generate Contact ID , store it in the CID array and Print
            String id = printCID();
            
            //Input a new name and store it in the name array
            String name = printInputName();
    
            //Input a new phone number
            String phoneNumber = printInputPhoneNumber();
    
            //Input Company name
            String company = printCompanyName();
    
            //Input Salary
            double salary = printSalary();
    
            //Input Birthday
            String bday = printBirthday();  
    
            Customer cust = new Customer(id, name, phoneNumber, company, salary, bday);
            CustomerList customerList = DBConnection.getInstance().getCustomerList();
            customerList.add(cust);
    
            //After adding a new contact
            System.out.println("\tContact has been added successfully..");
            //printCustomerList();
            System.out.print("Do you want to add another Contact (Y/N) : ");
            String yn = input.next();
            if(yn.equalsIgnoreCase("Y")){
                clearConsole();
                continue L1;
            }else if(yn.equalsIgnoreCase("N")){
                clearConsole();
                homePage();
                break;
            }
        }while(true);
    }


}
