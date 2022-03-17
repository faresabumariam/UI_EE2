import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton backButton = new JButton("BACK");
    JCheckBox showPassword = new JCheckBox("Show Password");


    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        getContentPane().setBackground(Color.darkGray);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        userLabel.setForeground(Color.YELLOW);
        passwordLabel.setBounds(50, 220, 100, 30);
        passwordLabel.setForeground(Color.YELLOW);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 260, 150, 30);
        showPassword.setForeground(Color.cyan);
        showPassword.setBackground(Color.DARK_GRAY);
        loginButton.setBounds(50, 340, 100, 30);
        loginButton.setForeground(Color.YELLOW);
        loginButton.setBackground(Color.darkGray);
        resetButton.setBounds(200, 340, 100, 30);
        resetButton.setForeground(Color.YELLOW);
        resetButton.setBackground(Color.darkGray);
        backButton.setBounds(120, 400, 100, 30);
        backButton.setForeground(Color.CYAN);
        backButton.setBackground(Color.darkGray);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(backButton);

    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("4D") && pwdText.equalsIgnoreCase("4D")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                JFrame mainFrame = new mainFrame("main");
                mainFrame.setVisible(true);
                mainFrame.pack();
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        if (e.getSource() == backButton) {
            JFrame choice = new loginChoiceFrame("main");
            choice.setVisible(true);
            choice.setSize(1000,800);
            choice.setBounds(10, 10, 370, 600);
            dispose();
        }


        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


    }

}

