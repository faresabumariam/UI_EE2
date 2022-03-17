
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Registration extends JFrame implements ActionListener {
    JFrame frame;
    String[] gender = {"Male", "Female"};
    JLabel nameLabel = new JLabel("NAME");
    JLabel genderLabel = new JLabel("GENDER");
    JLabel fatherNameLabel = new JLabel("FAMILY");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel confirmPasswordLabel = new JLabel("CONFIRM PASSWORD");
    JLabel countryLabel = new JLabel("COUNTRY");
    JLabel emailLabel = new JLabel("EMAIL");
    JTextField nameTextField = new JTextField();
    JComboBox genderComboBox = new JComboBox(gender);
    JTextField familyTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    JTextField cityTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JButton registerButton = new JButton("CONFIRM");
    JButton resetButton = new JButton("RESET");


    Registration() {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("Registration Form");
        frame.setBounds(40,40,380,600);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {
        int y = 0;
        nameLabel.setBounds(20, y + 20, 40, 70);
        nameLabel.setForeground(Color.YELLOW);
        fatherNameLabel.setBounds(20, y + 70, 80, 70);
        fatherNameLabel.setForeground(Color.YELLOW);
        genderLabel.setBounds(20, y + 120, 100, 70);
        genderLabel.setForeground(Color.YELLOW);
        passwordLabel.setBounds(20, y + 170, 100, 70);
        passwordLabel.setForeground(Color.YELLOW);
        confirmPasswordLabel.setBounds(20, y + 220, 140, 70);
        confirmPasswordLabel.setForeground(Color.YELLOW);
        countryLabel.setBounds(20, y + 270, 100, 70);
        countryLabel.setForeground(Color.YELLOW);
        emailLabel.setBounds(20, y + 320, 100, 70);
        emailLabel.setForeground(Color.YELLOW);
        nameTextField.setBounds(180, y + 43, 165, 23);
        familyTextField.setBounds(180, y + 93, 165, 23);
        genderComboBox.setBounds(180, y + 143, 165, 23);
        passwordField.setBounds(180, y + 193, 165, 23);
        confirmPasswordField.setBounds(180, y + 243, 165, 23);
        cityTextField.setBounds(180, y + 293, 165, 23);
        emailTextField.setBounds(180, y + 343, 165, 23);
        registerButton.setBounds(70, y + 400, 100, 35);
        registerButton.setBackground(Color.darkGray);
        registerButton.setForeground(Color.cyan);
        resetButton.setBackground(Color.darkGray);
        resetButton.setForeground(Color.cyan);
        resetButton.setBounds(220, y + 400, 100, 35);


    }

    public void addComponentsToFrame() {
        frame.add(nameLabel);
        frame.add(genderLabel);
        frame.add(fatherNameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(countryLabel);
        frame.add(emailLabel);
        frame.add(nameTextField);
        frame.add(genderComboBox);
        frame.add(familyTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(cityTextField);
        frame.add(emailTextField);
        frame.add(registerButton);
        frame.add(resetButton);
    }

    public void actionEvent() {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            try {
                //Creating Connection Object
                Connection connection = DriverManager.getConnection("https://studev.groept.be/api/a21ib2d04/users");
                //Preapared Statement
                PreparedStatement Pstatement = connection.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
                //Specifying the values of it's parameter
                Pstatement.setString(1, nameTextField.getText());
                Pstatement.setString(2, genderComboBox.getSelectedItem().toString());
                Pstatement.setString(3, familyTextField.getText());
                Pstatement.setString(4, passwordField.getText());
                Pstatement.setString(5, confirmPasswordField.getText());
                Pstatement.setString(6, cityTextField.getText());
                Pstatement.setString(7, emailTextField.getText());
                //Checking for the Password match
                if (passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())) {
                    //Executing query
                    Pstatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "password did not match");
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }
        if (e.getSource() == resetButton) {
            //Clearing Fields
            nameTextField.setText("");
            genderComboBox.setSelectedItem("Male");
            familyTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            cityTextField.setText("");
            emailTextField.setText("");
        }

    }


    public static void main(String[] args) {
        Registration frame = new Registration();
    }

}
