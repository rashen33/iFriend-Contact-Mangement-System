import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteCustomerForm extends JFrame {
    private JLabel titleLabel;
    private JLabel contID;
    private JLabel name;
    private JLabel contactNumber;
    private JLabel company;
    private JLabel salary;
    private JLabel bday;

    private JTextField tfSearch;

    private JLabel tfcontID;
    private JLabel tfName;
    private JLabel tfContactNumber;
    private JLabel tfCompany;
    private JLabel tfSalary;
    private JLabel tfBday;

    private JButton delete;
    private JButton cancel;
    private JButton homePage;
    private JButton btnSearch;

    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel searchPanelLeft;
    private JPanel searchPanelRight;


    DeleteCustomerForm() {
        setSize(600, 500);
        setTitle("Add Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(51, 199, 255));

        //--------------------North with the Main JLabel-------------
        
        titleLabel = new JLabel("Delete Contact Form");
        titleLabel.setFont(new Font("", 1, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add("North", titleLabel);

            //-------------------Search Panel LEFT and RIGHT-------------------

        searchPanelLeft = new JPanel();
        searchPanelLeft.setLayout(new FlowLayout());
        
        tfSearch = new JTextField(16);
        tfSearch.setFont(new Font("", 1, 20));
        JPanel tfSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String name=tfSearch.getText();
				Customer customer=CustomerController.searchCustomer(name);
				if(customer!=null){
					//id = (customer.getCustID());
                    tfcontID.setText(customer.getCustID());
                    tfName.setText(customer.getName());
					tfContactNumber.setText(customer.getTpNumber());
					tfCompany.setText(customer.getCompany());
                    tfSalary.setText(""+customer.getSalary());					
                    tfBday.setText(customer.getBirthday());
				}else{
					JOptionPane.showMessageDialog(null,"Contact not Found!");
				}
			}
		});
        searchPanelLeft.add(tfSearchPanel);

        searchPanelRight = new JPanel();
        searchPanelRight.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String name=tfSearch.getText();
				Customer customer=CustomerController.searchCustomer(name);
				if(customer!=null){
                    tfcontID.setText(customer.getCustID());
                    tfName.setText(customer.getName());
					tfContactNumber.setText(customer.getTpNumber());
					tfCompany.setText(customer.getCompany());
                    tfSalary.setText(""+customer.getSalary());					
                    tfBday.setText(customer.getBirthday());
				}else{
					JOptionPane.showMessageDialog(null,"Contact not Found!");
				}
			}
		});
        btnSearch.setFont(new Font("",1,20));
        tfSearchPanel.add(tfSearch);
        searchPanelRight.add(btnSearch);

        //--------------------Center Panel with the Labels-------------
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 2));

        centerPanel.add(searchPanelLeft);
        centerPanel.add(searchPanelRight);

        contID = new JLabel("Contact ID");
        contID.setFont(new Font("",1,25));
        tfcontID = new JLabel();
        tfcontID.setFont(new Font("",1,25));
        centerPanel.add(contID);
        centerPanel.add(tfcontID);

        name = new JLabel("Name");
        name.setFont(new Font("",1,30));
        tfName = new JLabel();
        tfName.setFont(new Font("",1,25));
        centerPanel.add(name);
        centerPanel.add(tfName);

        contactNumber = new JLabel("Contact Number");
        contactNumber.setFont(new Font("",1,30));
        tfContactNumber = new JLabel();
        tfContactNumber.setFont(new Font("",1,25));
        centerPanel.add(contactNumber);
        centerPanel.add(tfContactNumber);

        company = new JLabel("Company");
        company.setFont(new Font("",1,30));
        tfCompany = new JLabel();
        tfCompany.setFont(new Font("",1,25));
        centerPanel.add(company);
        centerPanel.add(tfCompany);

        salary = new JLabel("Salary");
        salary.setFont(new Font("",1,30));
        tfSalary = new JLabel();
        tfSalary.setFont(new Font("",1,25));
        centerPanel.add(salary);
        centerPanel.add(tfSalary);

        bday = new JLabel("Birthday");
        bday.setFont(new Font("",1,30));
        tfBday = new JLabel();
        tfBday.setFont(new Font("",1,25));
        centerPanel.add(bday);
        centerPanel.add(tfBday);

        add("Center", centerPanel);

        //------------South Panel with the buttons------------        

        southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        delete = new JButton("Delete");
        delete.setFont(new Font("", 1, 20));
        delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
                //CustomerList customerList=DBConnection.getInstance().getCustomerList();
                String id1 = tfcontID.getText();
                String name1 = tfName.getText();
                String coontactNumber1 = tfContactNumber.getText();
                String company1 = tfCompany.getText();
                double salary1 = Double.parseDouble(tfSalary.getText());
                String bday1 = tfBday.getText();

				Customer customer = new Customer(id1, name1, coontactNumber1, company1, salary1, bday1);
				boolean isAdded=CustomerController.deleteCustomer(customer);
				if(isAdded){
					JOptionPane.showMessageDialog(null,"Contact Number Deleted Successfully!");
				}else{
                JOptionPane.showMessageDialog(null,"Contact Number Delete Fail!");

                }
            }
		});
        gbc.gridx = 0;
        gbc.gridy = 0;
        southPanel.add(delete, gbc);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("", 1, 20));
        cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
                dispose();
			}
		});
        gbc.gridx = 1;
        gbc.gridy = 0;
        southPanel.add(cancel, gbc);

        homePage = new JButton("Back To HomePage");
        homePage.setFont(new Font("", 1, 20));
        homePage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new MainWindow().setVisible(true);
			}
		});
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        southPanel.add(homePage, gbc);

        add("South", southPanel);
    }

    // public static void main(String[] args) {
    //     new DeleteCustomerForm().setVisible(true);
    // }
}
