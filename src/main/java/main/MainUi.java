 package main;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

/**
 * @author jason
 * MainUI class links the login screen to the main program
 * after valid login the user will be sent to the main function of comparing data 
 */
public class MainUi extends Login{

	private static final long serialVersionUID = 1L;

	public static void main(String arg[]) {
		Login s =  new Login();
		// while the user is not logged in, check on a delay to see if credentials have been inputted
		while(s.loggedin == 0) {
			try {
				TimeUnit.SECONDS.sleep(1);//1 second delay
			} catch (InterruptedException e) {
				e.printStackTrace();//throw an error if login screen fails
			}
		}
		// if logged in is successful dispose of the login screen and send the user to the main program function
		if(s.loggedin==2) {
			s.dispose();
			//dispose of login screen and open an instance of the main program frame
			JFrame frame = UI.getInstance();
			frame.setSize(900, 600);
			frame.setResizable(false);
			frame.pack();
			frame.setVisible(true);
		}else { // if the user has inputted invalid credentials, exit the program
			System.exit(0);
		}	
	}
}
