import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class panel extends JFrame implements ActionListener{
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_X_ORIGIN = 100;
	private static final int FRAME_Y_ORIGIN = 100;
	private static final String EMPTY_STRING = "";
	private static final String NEWLINE= System.getProperty("line.separator");
	protected JTextField inputLine, studentIDTF, f_nameTF, m_nameTF, l_nameTF, emphasisTF, statusTF;	
	protected JTable outputPanel;
	private JButton AIButton, studentButton, submit1;
	private JButton ph1, ph2, ph3, ph4, ph5, ph6, ph7;

	public panel() {
		JTabbedPane masterPane = new JTabbedPane();
		JPanel tab1Pane, tab2Pane;
		JPanel controlPanel, scorePanel;
		JPanel addStudentPanel, addAIPanel, addTestPanel, insert1, insert2, insert3;
		JLabel studentIDLabel, f_nameLabel, m_nameLabel, l_nameLabel, emphasisLabel, statusLabel;
				
		setSize (FRAME_WIDTH, FRAME_HEIGHT);
		setTitle ("Computer Science Database");
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

		//**********************
		tab1Pane = new JPanel();
		GridLayout g = new GridLayout(8,6,15,1);
		tab1Pane.setLayout(new GridLayout(3,1));
		masterPane.addTab("Data", tab1Pane);
		
		addStudentPanel = new JPanel();
		addStudentPanel.setBorder(BorderFactory.createTitledBorder("Student"));
		addStudentPanel.setLayout(g);
		tab1Pane.add(addStudentPanel);

		studentIDLabel = new JLabel("ID Number");
		addStudentPanel.add(studentIDLabel);
		
		studentIDTF = new JTextField(10);
		addStudentPanel.add(studentIDTF);

		f_nameLabel = new JLabel("First Name");
		addStudentPanel.add(f_nameLabel);
		f_nameTF = new JTextField(10);
		addStudentPanel.add(f_nameTF);

		m_nameLabel = new JLabel("Middle Initial");
		addStudentPanel.add(m_nameLabel);
		m_nameTF = new JTextField(1);
		addStudentPanel.add(m_nameTF);

		l_nameLabel = new JLabel("Last Name");
		addStudentPanel.add(l_nameLabel);
		l_nameTF = new JTextField(15);
		addStudentPanel.add(l_nameTF);

		emphasisLabel = new JLabel("Emphasis");
		addStudentPanel.add(emphasisLabel);
		emphasisTF = new JTextField(3);
		addStudentPanel.add(emphasisTF);

		statusLabel = new JLabel("Status");
		addStudentPanel.add(statusLabel);
		statusTF = new JTextField(10);
		addStudentPanel.add(statusTF);

		submit1 = new JButton("Submit");
		addStudentPanel.add(submit1);

		insert1 = new JPanel();
		tab1Pane.add(insert1);

		addAIPanel = new JPanel();
		addAIPanel.setBorder(BorderFactory.createTitledBorder("AI"));
		tab1Pane.add(addAIPanel);

		insert2 = new JPanel();
		tab1Pane.add(insert2);

		addTestPanel = new JPanel();
		addTestPanel.setBorder(BorderFactory.createTitledBorder("Test"));
		tab1Pane.add(addTestPanel);
		//*************************
		tab2Pane = new JPanel();
		tab2Pane.setLayout(new BorderLayout(10, 0));
		masterPane.addTab("Queries", tab2Pane);
		inputLine = new JTextField(100);
		tab2Pane.add(inputLine, BorderLayout.NORTH);
		inputLine.addActionListener(this);

		outputPanel = new JTable();
		outputPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		controlPanel = new JPanel();
		controlPanel.setBorder(BorderFactory.createTitledBorder("Queries:"));
		controlPanel.setLayout(new GridLayout(20,1));
		tab2Pane.add(new JScrollPane(outputPanel), BorderLayout.CENTER);
		tab2Pane.add(controlPanel, BorderLayout.EAST);
		
		studentButton = new JButton ("Student");
		studentButton.setToolTipText("Displays Student Table");
		controlPanel.add(studentButton);
		AIButton = new JButton ("AI");
		controlPanel.add(AIButton);
		ph1 = new JButton("ph");
		controlPanel.add(ph1);
		ph2 = new JButton("ph");
		controlPanel.add(ph2);
		ph3 = new JButton("ph");
		controlPanel.add(ph3);
		ph4 = new JButton("ph");
		controlPanel.add(ph4);
		ph5 = new JButton("ph");
		controlPanel.add(ph5);
		ph6 = new JButton("ph");
		controlPanel.add(ph6);
		ph7 = new JButton("ph");
		controlPanel.add(ph7);

		inputLine.addActionListener(this);
		studentButton.addActionListener(this);
		AIButton.addActionListener(this);

		add(masterPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event){
		if (event.getSource() instanceof JTextField){
			try{
					makeConnection(inputLine.getText());
				}
				catch (SQLException ex) {
            		JOptionPane.showMessageDialog ( panel.this ,
                		new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                	JOptionPane.showMessageDialog ( panel.this ,
                		new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
		}
		else if (event.getSource() instanceof JButton) {
			JButton clickedButton = (JButton) event.getSource();
			if (clickedButton == studentButton) {
				try{
					makeConnection("Select * from Student;");
				}
				catch (SQLException ex) {
            		JOptionPane.showMessageDialog ( panel.this ,
                		new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                	JOptionPane.showMessageDialog ( panel.this ,
                		new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
			}
			else if (clickedButton == AIButton) {
				try{
					makeConnection("Select * from AI;");
				}
				catch (SQLException ex) {
            		JOptionPane.showMessageDialog ( panel.this ,
                		new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                	JOptionPane.showMessageDialog ( panel.this ,
                		new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }
		}
	}

	public void makeConnection(String query) throws SQLException, ClassNotFoundException{
		Class.forName(Login.DRIVER_CLASS);
		Connection connection = DriverManager.getConnection("jdbc:mysql://webdev.cs.uwosh.edu:4381/"+Login.DB, Login.USER, Login.PWD);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		outputPanel.setModel(new ResultSetTableModel(resultSet));
	}

	public static void main(String[] args) {
		panel frame = new panel();
		frame.setVisible(true);
	}
}