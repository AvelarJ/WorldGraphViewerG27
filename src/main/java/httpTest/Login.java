package httpTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Sasa Vecerak
 *
 */
public class Login extends JFrame implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField username_input;
    JTextField password_input;
    JButton submit, cancel;

    Login() {
        
        // User Label
    	
        user_label = new JLabel();
        user_label.setText("User Name :");
        username_input = new JTextField();
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_input = new JPasswordField();

        // Submit

        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(user_label);
        panel.add(username_input);
        panel.add(password_label);
        panel.add(password_input);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners 
        
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(600, 200);
        setVisible(true);
    }


    /**
     * Checks if login valid, redirects to MainUI if it is, else, terminates 
     */
    public void actionPerformed(ActionEvent ae) {
    	
    	// Fetch user inputs
    	
        String username = username_input.getText();
		String password = password_input.getText();
       
		// Instantiate new User object and ProxyServer object
		
        User user = new User(username, password);       
		ProxyServer proxy = new ProxyServer(user);
		
		// Check whether credentials are valid 
		
		Boolean validLogin = proxy.login(user.getUsername(), user.getPassword());
		
		// If valid, open MainUI, otherwise terminate program
		
        if(validLogin) {
    		JFrame frame = MainUI.getInstance();
			frame.setSize(900, 600);
			frame.pack();
			frame.setVisible(true);
		  	setVisible(false); 
        	dispose(); 	
        } else {
        	message.setText("Invalid");
        	JOptionPane.showMessageDialog(null, "Username and/or Password Incorrect... exiting ...");
        	setVisible(false); 
        	dispose(); 
        }
    }
    
    /**
     * Main method calls Login()
     * @param args
     */
    public static void main(String[] args) {
        new Login();
    }

}