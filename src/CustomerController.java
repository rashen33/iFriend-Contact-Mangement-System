import java.time.LocalDate;
import java.util.*;

public class CustomerController{
	public static boolean addCustomer(Customer customer){
		CustomerList customerList=DBConnection.getInstance().getCustomerList();
		customerList.add(customer);
		return true;
	}
	public static Customer[] getAllCustomer(){
		CustomerList customerList=DBConnection.getInstance().getCustomerList();
		return customerList.toArray();
	}

    public static Customer searchCustomer(String name){
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            if (name.equalsIgnoreCase(customer.getName()) || name.equals(customer.getTpNumber())) {
                return customer;
            }
        }
        return null;
    }

	public static Customer[] getAllCustomerSortBySalary(){
		CustomerList customerList=DBConnection.getInstance().getCustomerList();
		Customer[] customerArray=customerList.toArray();
		for (int i = 1; i < customerArray.length; i++){
			for(int j=0; j<customerArray.length-i; j++){
				if(customerArray[j].getSalary()>customerArray[j+1].getSalary()){
					Customer tempCustomer=customerArray[j];
					customerArray[j]=customerArray[j+1];
					customerArray[j+1]=tempCustomer;
				}
			}
		}
		return customerArray;
	}

    public static Customer[] getAllCustomerSortByName(){
		CustomerList customerList=DBConnection.getInstance().getCustomerList();
		Customer[] customerArray=customerList.toArray();
        int n = customerList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (customerArray[j].getName().compareTo(customerArray[j+1].getName()) > 0) {
                    // Swap Contact
                    Customer temp = customerArray[j];
                    customerArray[j] = customerArray[j+1];
                    customerArray[j+1] = temp;

                }
            }
        }
        return customerArray;
    }

    public static Customer[] getAllCustomerSortByBday(){
		CustomerList customerList=DBConnection.getInstance().getCustomerList();
		Customer[] customerArray=customerList.toArray();        
        int n = customerList.size();
        
        // Bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (customerArray[j].getBirthday().compareTo(customerArray[j+1].getBirthday()) > 0) {
                    // Swap Contact
                    Customer temp = customerArray[j];
                    customerArray[j] = customerArray[j+1];
                    customerArray[j+1] = temp;
                }
            }
        }    
        return customerArray;
    }    
    
    
    public static boolean updateCustomer(Customer customer) {
        CustomerList customerList = DBConnection.getInstance().getCustomerList();
        for (int i = 0; i < customerList.size(); i++) {
            Customer existingCustomer = customerList.get(i);
            if (existingCustomer.getCustID().equals(customer.getCustID())) {
                customerList.remove(i);
                customerList.add(i, customer);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteCustomer(Customer customer){
        CustomerList customerList=DBConnection.getInstance().getCustomerList();
        int index = customerList.indexOf(customer);
        customerList.remove(index);
        return true;
	} 

    public static String lastId(){
		CustomerList customerList=DBConnection.getInstance().getCustomerList();
		String[] idArray=new String[customerList.size()];
		for (int i = 0; i < customerList.size(); i++){
			idArray[i]=customerList.get(i).getCustID();
		}
		Arrays.sort(idArray);	
		return idArray.length== 0 ? null : idArray[idArray.length-1];
	}

        //--------------------Validation for the phone number
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

        //--------------Validation for the salary
    public static boolean isSalValid(double sal){
        if(sal<0){
            return false;
        }
        return true;
    }
    
        //----------------Validating the birthday
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

}
 