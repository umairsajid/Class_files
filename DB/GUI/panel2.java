import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
class panel2 extends JFrame implements ActionListener{
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_X_ORIGIN = 100;
	private static final int FRAME_Y_ORIGIN = 100;
	protected JTextField inputLine, studentIDTF, f_nameTF, m_nameTF, l_nameTF, emphasisTF, statusTF;	
	protected JTable outputPanel;
	private JButton AIButton, studentButton, submit1;
	private JButton q1Button,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10;

	public panel2() {
		setFont(new Font("Ubuntu", Font.PLAIN, 12));
		setForeground(Color.BLACK);
		JTabbedPane masterPane = new JTabbedPane();
		JPanel tab1Pane, tab2Pane;
		JPanel controlPanel;
		JPanel addStudentPanel, addAIPanel, addTestPanel;
		JLabel studentIDLabel, f_nameLabel, m_nameLabel, l_nameLabel, emphasisLabel, statusLabel;
				
		setSize (FRAME_WIDTH, FRAME_HEIGHT);
		setTitle ("Computer Science Database");
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

		//**********************
		tab1Pane = new JPanel();
		tab1Pane.setLayout(new GridLayout(3,1));
		masterPane.addTab("Data", tab1Pane);
		
		addStudentPanel = new JPanel();
		addStudentPanel.setForeground(Color.BLACK);
		addStudentPanel.setBorder(BorderFactory.createTitledBorder("Student"));
		tab1Pane.add(addStudentPanel);
		addStudentPanel.setLayout(null);

		studentIDLabel = new JLabel("ID Number");
		studentIDLabel.setBounds(15, 33, 90, 19);
		addStudentPanel.add(studentIDLabel);
		
		studentIDTF = new JTextField(10);
		studentIDTF.setForeground(Color.BLACK);
		studentIDTF.setBounds(112, 33, 73, 19);
		addStudentPanel.add(studentIDTF);

		f_nameLabel = new JLabel("First Name");
		f_nameLabel.setBounds(258, 33, 101, 19);
		addStudentPanel.add(f_nameLabel);
		f_nameTF = new JTextField(10);
		f_nameTF.setBounds(377, 33, 114, 19);
		addStudentPanel.add(f_nameTF);

		m_nameLabel = new JLabel("Middle Initial");
		m_nameLabel.setBounds(532, 33, 101, 19);
		addStudentPanel.add(m_nameLabel);
		m_nameTF = new JTextField(1);
		m_nameTF.setBounds(651, 33, 25, 19);
		addStudentPanel.add(m_nameTF);

		l_nameLabel = new JLabel("Last Name");
		l_nameLabel.setBounds(15, 82, 90, 19);
		addStudentPanel.add(l_nameLabel);
		l_nameTF = new JTextField(15);
		l_nameTF.setBounds(112, 82, 114, 19);
		addStudentPanel.add(l_nameTF);

		emphasisLabel = new JLabel("Emphasis");
		emphasisLabel.setBounds(258, 82, 95, 19);
		addStudentPanel.add(emphasisLabel);
		emphasisTF = new JTextField(3);
		emphasisTF.setBounds(377, 82, 37, 19);
		addStudentPanel.add(emphasisTF);

		statusLabel = new JLabel("Status");
		statusLabel.setBounds(532, 82, 90, 19);
		addStudentPanel.add(statusLabel);
		statusTF = new JTextField(10);
		statusTF.setBounds(651, 82, 73, 19);
		addStudentPanel.add(statusTF);

		submit1 = new JButton("Submit");
		submit1.setBounds(359, 129, 90, 21);
		addStudentPanel.add(submit1);

		addAIPanel = new JPanel();
		addAIPanel.setForeground(Color.BLACK);
		addAIPanel.setBorder(BorderFactory.createTitledBorder("AI"));
		tab1Pane.add(addAIPanel);
		addAIPanel.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 32, 70, 15);
		addAIPanel.add(lblId);

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
		AIButton.setToolTipText("Displays AI Table");
		controlPanel.add(AIButton);

		q1Button = new JButton("Q1");
		q1Button.setToolTipText("Displays avg. Score spec. crit. for spec. Seme.");
		controlPanel.add(q1Button);
		// Q2 = new JButton("Q2");
		// Q2.setToolTipText("Displays avg. Score all crit. for spec. Seme.");
		// controlPanel.add(Q2);
		// Q3 = new JButton("Q3");
		// Q3.setToolTipText("Displays avg. Scores for all crit. for spec. CDAI");
		// controlPanel.add(Q3);
		// Q4 = new JButton("Q4");
		// Q4.setToolTipText("Displays avg. Scores over all crit. for spec. CDAI");
		// controlPanel.add(Q4);
		// Q5 = new JButton("Q5");
		// Q5.setToolTipText("Displays avg. Scores over all crit. for spec. CDAI");
		// controlPanel.add(Q5);
		// Q6 = new JButton("Q6");
		// Q6.setToolTipText("Displays highest avg score");
		// controlPanel.add(Q6);
		// Q7 = new JButton("Q7");
		// Q7.setToolTipText("Displays lowest avg score");
		// controlPanel.add(Q7);
		// Q8 = new JButton("Q8");
		// Q8.setToolTipText("Displays avg., high, low by emphasis");
		// controlPanel.add(Q8);
		// Q9 = new JButton("Q9");
		// Q9.setToolTipText("Displays all data for spec. Student");
		// controlPanel.add(Q9);
		// Q10 = new JButton("Q10");
		// Q10.setToolTipText("Displays avg., high, low for spec. Student");
		// controlPanel.add(Q10);

		inputLine.addActionListener(this);
		studentButton.addActionListener(this);
		AIButton.addActionListener(this);
		q1Button.addActionListener(this);

		getContentPane().add(masterPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event){
		if (event.getSource() instanceof JTextField){
			try{
					makeConnection(inputLine.getText());
				}
				catch (SQLException ex) {
            		JOptionPane.showMessageDialog ( panel2.this ,
                		new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                	JOptionPane.showMessageDialog ( panel2.this ,
                		new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
		}		
		else if (event.getSource() instanceof JButton) {
			JButton clickedButton = (JButton) event.getSource();
			if (clickedButton == studentButton) {
				try{
					System.out.println("stu pushed");
					makeConnection("Select * from Student;");
				}
				catch (SQLException ex) {
            		JOptionPane.showMessageDialog ( panel2.this ,
                		new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                	JOptionPane.showMessageDialog ( panel2.this ,
                		new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
			}
			else if (clickedButton == AIButton) {
				try{
					makeConnection("Select * from AI;");
				}
				catch (SQLException ex) {
            		JOptionPane.showMessageDialog ( panel2.this ,
                		new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                	JOptionPane.showMessageDialog ( panel2.this ,
                		new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }

            else if (clickedButton == q1Button) {
                try{
                    System.out.println("q1 pushed");
                    makeConnection("SELECT DISTINCT crit_id AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY crit_id;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }


            
            else if (clickedButton == Q2) {
                try{
                    makeConnection("SELECT DISTINCT id AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY id;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }

            else if (clickedButton == Q3) {
                try{
                    makeConnection("SELECT  DISTINCT SUBSTRING(id, 1, 2) AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY SUBSTRING(id, 1, 2);");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }

            else if (clickedButton == Q4) {
                try{
                    makeConnection("SELECT DISTINCT id AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY id;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }      
            
            
            else if (clickedButton == Q5) {
                try{
                    makeConnection("SELECT  DISTINCT SUBSTRING(id, 1, 2) AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY SUBSTRING(id, 1, 2);");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }
            
            
            
            
            
            else if (clickedButton == Q6) {
                try{
                    makeConnection("SELECT  DISTINCT crit_id AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY crit_id ORDER BY 2 DESC LIMIT 15;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }
            
            else if (clickedButton == Q7) {
                try{
                    makeConnection("SELECT  DISTINCT crit_id AS CDAI, AVG(score) AS Score FROM `Result` GROUP BY crit_id ORDER BY 2 ASC LIMIT 4;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }
            
            else if (clickedButton == Q8) {
                try{
                    makeConnection("SELECT DISTINCT Student.emphasis AS Emphasis, Max(Result.score) AS Max_Score, AVG(Result.score) AS Avg_Score, MIN(Result.score) AS Min_Score FROM Student INNER JOIN Result ON Student.uid = Result.uid GROUP BY emphasis;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }
            
            else if (clickedButton == Q9) {
                try{
                    makeConnection("SELECT DISTINCT Student.f_name, Student.l_name, Result.crit_id, Result.score FROM Student INNER JOIN Result ON Student.uid = Result.uid;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    c.getClass().getName () + ": ",c.getMessage()});
                }
            }
            
            else if (clickedButton == Q10) {
                try{
                    makeConnection("SELECT Student.l_name, Student.f_name, Result.crit_id, Max(Result.score) AS Max_Score, AVG(Result.score) AS Avg_Score, MIN(Result.score) AS Min_Score FROM Student INNER JOIN Result ON Student.uid = Result.uid GROUP BY l_name;");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog ( panel2.this ,
                        new String [] { // Display a 2- line message
                    ex.getClass().getName () + ": ",ex.getMessage()});
                }
                catch (ClassNotFoundException c){
                    JOptionPane.showMessageDialog ( panel2.this ,
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
		panel2 frame = new panel2();
		frame.setVisible(true);
	}
}