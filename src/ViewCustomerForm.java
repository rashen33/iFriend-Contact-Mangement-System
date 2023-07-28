import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
class ViewCustomerForm extends JFrame{
	private JTable customerTable;
	private DefaultTableModel dtm;
	private JLabel titleLabel;
	private JButton btnReload;	
	private JButton btnSortBySalary;
	private JButton btnSortByName;
	private JButton btnSortByBday;

	ViewCustomerForm(){
		setSize(800,500);
		setTitle("View Customer Details");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		
		titleLabel=new JLabel("View Customer Details");
		titleLabel.setFont(new Font("",1,35)); 
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add("North",titleLabel);
		getContentPane().setBackground(new Color(51, 199, 255));

		
		String[] columnNames={"Contact ID","Name","Contact No","Comapany","Salary","Birthday"};
		dtm=new DefaultTableModel(columnNames,0);
		customerTable=new JTable(dtm);
		
		JScrollPane tablePane=new JScrollPane(customerTable);
		add("Center",tablePane);
		
		//--------------------Reload Button-----------------------------------
        
        btnReload=new JButton("Reload");
		btnReload.setFont(new Font("",1,20));
        btnReload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Customer[] customerArray = CustomerController.getAllCustomer();
				dtm.setRowCount(0);
				for (int i = 0; i < customerArray.length; i++){
					Customer customer=customerArray[i];
					Object[] rowData={customer.getCustID(),customer.getName(), customer.getTpNumber(),customer.getCompany(),customer.getSalary(),customer.getBirthday()};
					dtm.addRow(rowData);	
				}
				
			}
		});
		
		
			//---------------Sort By Name Button--------------------------------

		btnSortByName=new JButton("Sort by Name");
		btnSortByName.setFont(new Font("",1,20));
		btnSortByName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Customer[] customerArray=CustomerController.getAllCustomerSortByName();
				dtm.setRowCount(0);
				for (int i = 0; i < customerArray.length; i++){
					Customer customer=customerArray[i];
					Object[] rowData={customer.getCustID(),customer.getName(), customer.getTpNumber(),customer.getCompany(),customer.getSalary(),customer.getBirthday()};
					dtm.addRow(rowData);	
				}					
			}
		});
		
			//------------------Sort By Salary Button----------------------------

		btnSortBySalary=new JButton("Sort by Salary");
		btnSortBySalary.setFont(new Font("",1,20));
		btnSortBySalary.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Customer[] customerArray=CustomerController.getAllCustomerSortBySalary();
				dtm.setRowCount(0);
				for (int i = 0; i < customerArray.length; i++){
					Customer customer=customerArray[i];
					Object[] rowData={customer.getCustID(),customer.getName(), customer.getTpNumber(),customer.getCompany(),customer.getSalary(),customer.getBirthday()};
					dtm.addRow(rowData);	
				}					
			}
		});

			//----------------Sort By Bday Button----------------------------------
		btnSortByBday=new JButton("Sort by Birthday");
		btnSortByBday.setFont(new Font("",1,20));
		btnSortByBday.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Customer[] customerArray=CustomerController.getAllCustomerSortByBday();
				dtm.setRowCount(0);
				for (int i = 0; i < customerArray.length; i++){
					Customer customer=customerArray[i];
					Object[] rowData={customer.getCustID(),customer.getName(), customer.getTpNumber(),customer.getCompany(),customer.getSalary(),customer.getBirthday()};
					dtm.addRow(rowData);	
				}					
			}
		});

		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnReload);
		buttonPanel.add(btnSortByName);
		buttonPanel.add(btnSortBySalary);
		buttonPanel.add(btnSortByBday);
		add("South",buttonPanel);
	}	

	// public static void main(String[] args) {
	// 	new ViewCustomerForm().setVisible(true);
	// }
}
