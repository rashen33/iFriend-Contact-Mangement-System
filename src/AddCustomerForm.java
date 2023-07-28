import javax.sound.midi.ControllerEventListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddCustomerForm extends JFrame {
    private JLabel titleLabel;
    private JLabel contIDN;
    private JLabel contID;
    private JLabel name;
    private JLabel contactNumber;
    private JLabel company;
    private JLabel salary;
    private JLabel bday;

    //private JTextField tfContIDN;
    private JTextField tfName;
    private JTextField tfContactNumber;
    private JTextField tfCompany;
    private JTextField tfSalary;
    private JTextField tfBday;

    private JButton Add;
    private JButton cancel;
    private JButton homePage;

    private JPanel centerPanel;
    private JPanel southPanel;

    AddCustomerForm() {
        setSize(500, 500);
        setTitle("Add Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(51, 199, 255));

        titleLabel = new JLabel("Add Contact Form");
        titleLabel.setFont(new Font("", 1, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add("North", titleLabel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 2));

 
        //CustomerList customerList=DBConnection.getInstance().getCustomerList();
        //String cid = String.format("C%04d",(customerList.size()+1));
        contIDN = new JLabel();
        contIDN.setFont(new Font("", 1, 20));
        contIDN.setHorizontalAlignment(JLabel.LEFT);
        contID = new JLabel("Contact ID");
        contID.setFont(new Font("", 1, 20));
        centerPanel.add(contID);
        centerPanel.add(contIDN);

        name = new JLabel("Name");
        name.setFont(new Font("", 1, 20));
        tfName = new JTextField(16);
        tfName.setFont(new Font("", 1, 20));
        JPanel tfNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfNamePanel.setFont(new Font("", 1, 20)); 
        tfNamePanel.add(tfName);
        centerPanel.add(name);
        centerPanel.add(tfNamePanel);

        contactNumber = new JLabel("Contact Number");
        contactNumber.setFont(new Font("", 1, 20));
        tfContactNumber = new JTextField(16);
        tfContactNumber.setFont(new Font("", 1, 20)); 
        JPanel tfContctNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfContctNumberPanel.add(tfContactNumber);
        centerPanel.add(contactNumber);
        centerPanel.add(tfContctNumberPanel);

        company = new JLabel("Company");
        company.setFont(new Font("", 1, 20));
        tfCompany = new JTextField(16);
        tfCompany.setFont(new Font("", 1, 20));
        JPanel tfCompanyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfCompanyPanel.add(tfCompany);
        centerPanel.add(company);
        centerPanel.add(tfCompanyPanel);

        salary = new JLabel("Salary");
        salary.setFont(new Font("", 1, 20));
        tfSalary = new JTextField(16);
        tfSalary.setFont(new Font("", 1, 20));
        JPanel tfSalaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfSalaryPanel.add(tfSalary);
        centerPanel.add(salary);
        centerPanel.add(tfSalaryPanel);

        bday = new JLabel("Birthday (YYYY-MM-DD)");
        bday.setFont(new Font("", 1, 20));
        tfBday = new JTextField(16);
        tfBday.setFont(new Font("", 1, 20));
        JPanel tfBdayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfBdayPanel.add(tfBday);
        centerPanel.add(bday);
        centerPanel.add(tfBdayPanel);

        add("Center", centerPanel);

        southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

            //--------------------ADD Button---------------------

        Add = new JButton("Add Contact");
        Add.setFont(new Font("", 1, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        southPanel.add(Add, gbc);
        Add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
                String id1 = contIDN.getText();
                String name1 = tfName.getText();
                String coontactNumber1 = tfContactNumber.getText();
                String company1 = tfCompany.getText();
                double salary1 = Double.parseDouble(tfSalary.getText());
                String bday1 = tfBday.getText();
                
                Customer customer = new Customer(id1, name1, coontactNumber1, company1, salary1, bday1);
                if(!CustomerController.isValidPhoneNumber(coontactNumber1)){
                    JOptionPane.showMessageDialog(null, "Invalid Phone Number!", "Alert", JOptionPane.ERROR_MESSAGE);
                }else if(!CustomerController.isSalValid(salary1)){
                    JOptionPane.showMessageDialog(null, "Salary Invalid!", "Alert", JOptionPane.ERROR_MESSAGE);
                }else if(!CustomerController.isBdayValid(bday1)){
                    JOptionPane.showMessageDialog(null, "Invalid Birthday!", "Alert", JOptionPane.ERROR_MESSAGE);
                }else{
                    boolean isAdded=CustomerController.addCustomer(customer);
                    if(isAdded){
                        JOptionPane.showMessageDialog(null,"Contact Number Added Successfully!");
                        int op=JOptionPane.showConfirmDialog(null, "Do you want to add another customer?");
                        if(op==JOptionPane.YES_OPTION){
                            generateId();
                            tfName.setText("");
                            tfContactNumber.setText("");
                            tfCompany.setText("");
                            tfSalary.setText("");
                            tfBday.setText("");

                        }else{
                            dispose();
                        }
                    }
                }
            }
		});
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
        
        generateId();

    }

    private void generateId(){
        String lastId=CustomerController.lastId();
        if(lastId==null){
            contIDN.setText("C0001");
        }else{
            String s=lastId.substring(1);
            int x=Integer.parseInt(s);
            lastId=String.format("C%04d",++x);
            contIDN.setText(lastId); 
        }
	}

    public static void main(String[] args) {
        new AddCustomerForm().setVisible(true);
    }
}
