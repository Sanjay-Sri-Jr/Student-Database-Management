package studentmanagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;

public class StudentManagementSystemJr extends JFrame implements ActionListener {
    JLabel title,StudentName,StudentId,Grade,DOB,Gender,Contact,Email;
	JTextField jname,jid,jgrade,jdob,jcontact,jemail;
	JRadioButton maleradio,femaleradio;
	JButton addstud,reset,deleterecord,search;
	ButtonGroup genderGroup;
    JTextField searchfield;
	JTable studentTable;
    DefaultTableModel tableModel;
	public StudentManagementSystemJr() {
		setTitle("Student Management System");
		setSize(1000,600);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.decode("#F8FFE5"));
		//getContentPane().setForeground(Color.decode("#2BCOE4"));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JLabel();
		title.setText("Student Management System");
		title.setBounds(250, 10, 700, 50);
		title.setFont(new Font("MONOSPACED",Font.BOLD,30));
		
		StudentName=new JLabel("Student Name");
		StudentName.setBounds(50,80, 150, 30);
		
		StudentId=new JLabel("Register No");
		StudentId.setBounds(50, 120, 150, 30);
		
		Grade=new JLabel("CGPA");
		Grade.setBounds(50, 160, 150, 30);
		
		DOB=new JLabel("Date of Birth");
		DOB.setBounds(50, 200, 150, 30);
		
		Gender=new JLabel("Gender");
		Gender.setBounds(50, 240, 150, 30);
		
		Contact=new JLabel("Contact");
		Contact.setBounds(50, 280, 150, 30);
		
		Email=new JLabel("Email");
		Email.setBounds(50, 320, 150, 30);
		
		jname=new JTextField();
		jname.setBounds(200, 80, 200, 30);
		
		jid=new JTextField();
		jid.setBounds(200, 120, 200, 30);
		
		jgrade=new JTextField();
		jgrade.setBounds(200, 160, 200, 30);
		
		jdob=new JTextField();
		jdob.setBounds(200, 200, 200, 30);
		
		maleradio = new JRadioButton("Male");
        maleradio.setBounds(200, 240, 80, 30);

        femaleradio = new JRadioButton("Female");
        femaleradio.setBounds(290, 240, 100, 30);
		
		genderGroup=new ButtonGroup();
		genderGroup.add(maleradio);
		genderGroup.add(femaleradio);
		
		jcontact=new JTextField();
		jcontact.setBounds(200, 280, 200, 30);
		
		jemail=new JTextField();
		jemail.setBounds(200, 320, 200, 30);
		
		addstud = new JButton("Add Student");
        addstud.setBounds(650, 150, 150, 30);

        reset = new JButton("Reset");
        reset.setBounds(650, 200, 150, 30);

        deleterecord = new JButton("Delete Record");
        deleterecord.setBounds(650, 250, 150, 30);

        searchfield = new JTextField();
        searchfield.setBounds(50, 360, 300, 30);

        search = new JButton("Search by ID");
        search.setBounds(360, 360, 150, 30);
        
        add(title);
        add(StudentName);
        add(StudentId);
        add(Grade);
        add(DOB);
        add(Gender);
        add(Contact);
        add(Email);
        add(jname);
        add(jid);
        add(jgrade);
        add(jdob);
        add(femaleradio);
        add(maleradio );
        add(jcontact);
        add(jemail);
        add(addstud);
        add(reset);
        add(deleterecord);
        add(searchfield);
        add(search);

		String[] columnName={"Student Name","Register NO","Grade","Date of Birth","Email","Contact","Gender"};
		tableModel=new DefaultTableModel(columnName,0);

		studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 400, 860, 150);
        add(scrollPane);


        Color textColor = Color.decode("#06D6A0"); 
        Color inputBg = Color.decode("#F8FFE5");   
        
        
        JLabel[] labels = { title, StudentName, StudentId, Grade, DOB, Gender, Contact, Email };
        for (JLabel label : labels) {
        	label.setForeground(textColor);
        }
        
        
        JTextField[] textFields = { jname, jid, jgrade, jdob, jcontact, jemail, searchfield };
        for (JTextField tf : textFields) {
        	tf.setForeground(textColor);
        	tf.setBackground(inputBg); 
        }
        
        JRadioButton[] radios = { maleradio, femaleradio };
        for (JRadioButton radio : radios) {
        	radio.setForeground(textColor);
        	radio.setBackground(inputBg);
        }
        
        JButton[] buttons = { addstud, reset, deleterecord, search };
        for (JButton btn : buttons) {
        	btn.setForeground(textColor);
        }
        
        studentTable.setForeground(textColor);
        studentTable.setBackground(Color.WHITE);
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setSelectionBackground(Color.decode("#2C3E50")); 
        
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setForeground(textColor);
        tableHeader.setBackground(Color.decode("#DDEEFF"));
        

		addstud.addActionListener(this);
		reset.addActionListener(this);
		deleterecord.addActionListener(this);
		search.addActionListener(this);
		
		
		loadStudentsFromData();

	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==addstud){
			String name=jname.getText();
			String id=jid.getText();
			String grade=jgrade.getText();
			String contact=jcontact.getText();
			String email=jemail.getText();
			String dob=jdob.getText();
			String gender=maleradio.isSelected()? "Male":"Female";
		
			if(name.isEmpty() || id.isEmpty() || grade.isEmpty() || contact.isEmpty() || email.isEmpty() || dob.isEmpty()){
				JOptionPane.showMessageDialog(this, "Please fill all the form!" , "ERROR",JOptionPane.ERROR_MESSAGE);
			} else if (!isValidStudentID(id)){
				JOptionPane.showMessageDialog(this,"Invalid Register Number,It should be a number","ERROR",JOptionPane.ERROR_MESSAGE);
			} else if (!isValidGrade(grade)){
				JOptionPane.showMessageDialog(this,"Invalid CGPA,It should be a number","ERROR",JOptionPane.ERROR_MESSAGE);
			} else if (!isValidContactNumber(contact)){
				JOptionPane.showMessageDialog(this,"Invalid Phone number,It should be a number","ERROR",JOptionPane.ERROR_MESSAGE);
			} else if (!isValidEmail(email)){
				JOptionPane.showMessageDialog(this,"Invalid Email,It should be proper email","ERROR",JOptionPane.ERROR_MESSAGE);
			} else if (!isValidDate(dob)){
				JOptionPane.showMessageDialog(this,"Invalid Date of Birth,It should be proper Date","ERROR",JOptionPane.ERROR_MESSAGE);
			} else if (!isValidGender()){
				JOptionPane.showMessageDialog(this,"Select the gender","ERROR",JOptionPane.ERROR_MESSAGE);
			} else {
				String[] data={name, id, grade, dob, email, contact, gender};
				tableModel.addRow(data);
	
				jname.setText("");
				jid.setText("");
				jgrade.setText("");
				jdob.setText("");
				jemail.setText("");
				jcontact.setText("");
				genderGroup.clearSelection();
				
				try (Connection con=connectTodatabase()){
					String sql="INSERT INTO students(name,id,grade,dob,email,contact,gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement smt=con.prepareStatement(sql);
					smt.setString(1, name);
					smt.setString(2, id);
					smt.setString(3, grade);
					smt.setDate(4, java.sql.Date.valueOf(convertToMySQLDate(dob)));
					smt.setString(5, email);
					smt.setString(6, contact);
					smt.setString(7, gender);
					
					int dataAdded=smt.executeUpdate();
					if(dataAdded>0) {
						JOptionPane.showMessageDialog(this, "Student data inserted succesfully","Success",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(this,"Failed to insert student data","Error",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource()==reset){
			jname.setText(" ");
				jid.setText(" ");
				jgrade.setText(" ");
				jdob.setText(" ");
				jemail.setText(" ");
				jcontact.setText(" ");
				genderGroup.clearSelection();
		}
		if (e.getSource()==search) {
			String searchid =searchfield.getText();
			for(int i=0;i<tableModel.getRowCount();i++){
				if (tableModel.getValueAt(i, 1).equals(searchid)) {
					studentTable.setRowSelectionInterval(i,i);
					studentTable.setSelectionBackground(Color.darkGray);
					studentTable.setSelectionForeground(Color.WHITE);
					break;
				}
			}
		}
		if (e.getSource()==deleterecord) {
			int selectedRow=studentTable.getSelectedRow();
			if (selectedRow!=-1) {
				String register_no=tableModel.getValueAt(selectedRow, 1).toString();
				try(Connection con=connectTodatabase()){
					String query="DELETE FROM students WHERE id=?";
					PreparedStatement smt=con.prepareStatement(query);
					smt.setString(1, register_no);
					int selectedrows=smt.executeUpdate();
					if (selectedrows > 0) {
		                tableModel.removeRow(selectedRow); // Remove from JTable
		                JOptionPane.showMessageDialog(this, "Record deleted successfully.");
		            } else {
		                JOptionPane.showMessageDialog(this, "Failed to delete record from database.");
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"Please select the Row to delete", "Warrning",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

	//@SuppressWarnings("unused")
	private boolean isValidStudentID(String id){
		return id.matches("^[0-9]+$");
	}
	private boolean isValidGrade(String grade){
		return grade.matches("^(100(\\.0+)?|([0-9]{1,2})(\\.[0-9]+)?)+$");
	}
	private boolean isValidDate(String date){
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
			sdf.setLenient(false);
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}

	}
	private boolean isValidEmail(String email){
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
	}
	private boolean isValidContactNumber(String contact){
		return contact.matches("^[0-9]{10}+$");
	}
	private boolean isValidGender() {
		return genderGroup.getSelection() != null;
	}
	
	
	
	private Connection connectTodatabase() throws SQLException{
		String url="jdbc:mysql://localhost:3306/studentdb";
		String user="root";
		String password="password";
		return DriverManager.getConnection(url,user,password);
	}
	private void loadStudentsFromData() {
		try(Connection con=connectTodatabase()){
			String selectQuery="SELECT * FROM students";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(selectQuery);
			
			while(rs.next()) {
				String[] data= {
						rs.getString("name"),
						rs.getString("id"),
						rs.getString("grade"),
						rs.getString("dob"),
						rs.getString("email"),
						rs.getString("contact"),
						rs.getString("gender")
				};
				tableModel.addRow(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private String convertToMySQLDate(String inputDate) {
	    try {
	        // This parses dd-MM-yyyy user input
	        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	        java.util.Date date = inputFormat.parse(inputDate);

	        // This formats to yyyy-MM-dd for MySQL
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        return outputFormat.format(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() 
             {
                public void run(){
                    new StudentManagementSystemJr();
                }
	    });
	}
}
