class Customer {
    private String customerID;
    private String name;
    private String tpNumber;
    private String company;
    private double salary;
    private String birthday;

    
    Customer(String customerID, String name, String tpNumber, String company, double salary, String birthday){
        this.customerID = customerID;
        this.name = name;
        this.tpNumber = tpNumber;
        this.company = company;
        this.salary = salary;
        this.birthday = birthday;
    }

    public boolean equals(Customer customer){
		return this.customerID.equals(customer.customerID);
	}

    public Customer getCopy(){
        return new Customer(customerID,name,tpNumber,company,salary,birthday);
    }

    public String getCustID(){
        return customerID;
    }

    public String getName(){
        return name;
    }

    public String getTpNumber(){
        return tpNumber;
    }

    public String getCompany(){
        return company;
    }

    public double getSalary(){
        return salary;
    }

    public String getBirthday(){
        return birthday;
    }

    public String setCusId(){
        return this.customerID;
    }
    
    public void setName(String name){
        this.name = name; 
    }

    public void setTpNumber(String tpNumber){
        this.tpNumber = tpNumber;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String toString(){
		return customerID + "-" + name + "-" + tpNumber + "-" + company + "-" + salary + "-" + birthday;
	}
}
