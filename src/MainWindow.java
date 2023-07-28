import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {
    private JLabel titleLabel;
    private JLabel titleLabelsub;
    private JLabel imagLabel;
    private JLabel homePagelbl;
    
    private JButton addContBtn;
    private JButton updateContBtn;
    private JButton searchContBtn;
    private JButton delContBtn;
    private JButton viewContBtn;
    private JButton exitBtn;
    
    private JSeparator sep;
    
    private ImageIcon titlePic;

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel sidePanel;

    private AddCustomerForm addCustomerForm;
    private UpdateCustomerForm updateCustomerForm;
    private SearchCustomerForm searchCustomerForm;
    private DeleteCustomerForm deleteCustomerForm;
    private ViewCustomerForm viewCustomerForm;


    public MainWindow() {
        setSize(570, 550);
        setTitle("Main Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //----------------------Top Panel Contains the main title and the sub title-----------
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("iFRIEND");
        titleLabel.setFont(new Font("", 1, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.setBackground(new Color(51, 199, 255));

        titleLabelsub = new JLabel("Contact Manager");
        titleLabelsub.setFont(new Font("", 1, 25));
        titleLabelsub.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabelsub, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        //----------------------Bottom Panel Contains the image----------
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        String filePath = "E:/ICEM/Object Oriented Programming/Assignments/cw_with_swing/imgs/pic.png";
        titlePic = new ImageIcon(filePath);
        Image image = titlePic.getImage().getScaledInstance(300, 440, Image.SCALE_SMOOTH);
        titlePic = new ImageIcon(image);
        //titlePic = new JLabel(imageIcon);
        titlePic = new ImageIcon(image);

        imagLabel = new JLabel(titlePic);
        bottomPanel.add(imagLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.WEST);

        //-----------------------Side Panel--------------------------------
        sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        sidePanel.setBackground(new Color(51, 199, 255));

        homePagelbl = new JLabel("Homepage");
        homePagelbl.setFont(new Font("", 1, 25));
        homePagelbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePagelbl.setBorder(new EmptyBorder(10, 0, 10, 0));

        sep = new JSeparator();

        //------------------------Add Contact--------------------------------

        addContBtn = new JButton("   Add Contact   ");
        addContBtn.setFont(new Font("", 1, 20));
        addContBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(addCustomerForm==null){
                    addCustomerForm=new AddCustomerForm();
                }
                addCustomerForm.setVisible(true);
            }
        });
        addContBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addContBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, addContBtn.getPreferredSize().height)); // Set maximum width

        //-----------------------Update Contact-------------------------------

        updateContBtn = new JButton("   Update Contact   ");
        updateContBtn.setFont(new Font("", 1, 20));
        updateContBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(updateCustomerForm==null){
                    updateCustomerForm=new UpdateCustomerForm();
                }
                updateCustomerForm.setVisible(true);
            }
        });

        updateContBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateContBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, updateContBtn.getPreferredSize().height)); // Set maximum width

        //---------------------Search Button-------------------------

        searchContBtn = new JButton("   Search Contact   ");
        searchContBtn.setFont(new Font("", 1, 20));
        searchContBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(searchCustomerForm==null){
                    searchCustomerForm=new SearchCustomerForm();
                }
                searchCustomerForm.setVisible(true);
            }
        });
        searchContBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchContBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchContBtn.getPreferredSize().height)); // Set maximum width

        //---------------------Delete Button-------------------------

        delContBtn = new JButton("   Delete Contact   ");
        delContBtn.setFont(new Font("", 1, 20));
        delContBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(deleteCustomerForm==null){
                    deleteCustomerForm=new DeleteCustomerForm();
                }
                deleteCustomerForm.setVisible(true);
            }
        });
        delContBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        delContBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, delContBtn.getPreferredSize().height)); // Set maximum width

        //-----------------------View Button---------------------------

        viewContBtn = new JButton("   View Contact   ");
        viewContBtn.setFont(new Font("", 1, 20));
        viewContBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(viewCustomerForm==null){
                    viewCustomerForm=new ViewCustomerForm();
                }
                viewCustomerForm.setVisible(true);
            }
        });
        viewContBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewContBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, viewContBtn.getPreferredSize().height)); // Set maximum width

        //----------------------------Exit Button-----------------------

        exitBtn = new JButton("   Exit   ");
        exitBtn.setFont(new Font("", 1, 20));
        exitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                System.exit(0);
            }
        });
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, exitBtn.getPreferredSize().height)); // Set maximum width

        sidePanel.add(homePagelbl);
        sidePanel.add(addContBtn);
        sidePanel.add(updateContBtn);
        sidePanel.add(searchContBtn);
        sidePanel.add(delContBtn);
        sidePanel.add(viewContBtn);
        sidePanel.add(exitBtn);
        add(sidePanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
}
