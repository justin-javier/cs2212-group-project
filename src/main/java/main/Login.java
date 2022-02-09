package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.BufferedReader;


/**
 * @author jason
 * Login class will open a login window for the user to input credentials for login
 * will work in conjunction with the MainUi class
 */
/**
 * @author jason
 *
 */
public class Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	static int loggedin = 0;
	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit, cancel;
	/**
	 * login method creates the login window
	 */
	Login() {
		// Username Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();
		// Password Label
		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JPasswordField();
		// Submit Button
		submit = new JButton("Submit");
		panel = new JPanel(new GridLayout(3, 1));
		// set labels password and username
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
		message = new JLabel();
		panel.add(message);
		panel.add(submit);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Adding the listeners to components..
		submit.addActionListener(this);
		// Set window properties
		add(panel, BorderLayout.CENTER);
		setTitle("Country_Info Login");
		setSize(500,300);
		setVisible(true);
	}

	/**
	 * main method will initiate the login and will check until the user valid or invalid credentials
	 * will exit and close object when submitted
	 */
	public static void main(String[] args) {
		new Login();//open an instance of Login method
		while(loggedin==0) {//while user has not inputted credentials 
		}
		//		System.exit(0);
	}
	/**
	 * @param username 	user inputs a string username for login verification
	 * @param password 	user inputs a string password for login verification
	 * @return 			returns true if login was successful, otherwise false
	 * @throws Exception when login file is not found, error will be thrown
	 */
	public Boolean loginfile(String username, String password) throws Exception {
		String[][] info = new String[20][2];//store login information in these files
		String[] temp = new String[2];
		String thisLine = null;
		int i = 0;
		//try to read login file and insert credentials into an array to verify the user's information
		try {
			FileReader fr=new FileReader("src/login.txt");    
			BufferedReader br = new BufferedReader(fr);
			while ((thisLine = br.readLine()) != null) {
				temp = thisLine.split(",");
				info[i][0] = temp[0];
				info[i][1] = temp[1];
				i++;
			}
			br.close();//close bufferedreader after reading
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		//loop to compare valid login credentials with user inputted credentials
		for(i=0;i<20;i++) {
			if((username.equals(info[i][0]))&&(password.equals(info[i][1]))) {
				loggedin = 2;
				//				panel.setVisible(false);
				return true;//return true if user credentials is valid
			}
		}
		loggedin = 1;
		//		panel.setVisible(false);
		return false;//user information is invalid, return false
	}
	/**
	 * @param ae input action from submit button
	 * when the user submits their login information, the code will verify the credentials
	 */
	public void actionPerformed(ActionEvent ae) {
		String userName = userName_text.getText();
		String password = String.valueOf(password_text.getPassword());
		//      if (userName.trim().equals("admin") && password.trim().equals("admin")) {
		try {
			System.out.println(userName);
			System.out.println(password);
			//if credentials is valid, welcome user
			if(loginfile(userName,password)) {
				message.setText(" Hello " + userName + "");
				//invalid credentials
			} else {
				message.setText(" Invalid user.. ");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * close function allows for closing through outside classes
	 */
	public void close() {
		System.exit(0);
	}
}

