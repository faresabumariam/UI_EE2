import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



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
    JButton backButton = new JButton("BACK");


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
        backButton.setBounds(120, 450, 100, 30);
        backButton.setForeground(Color.CYAN);
        backButton.setBackground(Color.darkGray);


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
        frame.add(backButton);
    }

    public void actionEvent() {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    public String makeGETRequest(String urlName){
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        try {
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
            conn.disconnect();
            return sb.toString();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (ProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "";

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
                String name = nameTextField.getText();
                String family = familyTextField.getText();
                String gender = genderComboBox.getSelectedItem().toString();
                String password = passwordField.getText();
                String confirmpassword = confirmPasswordField.getText();
                String country = cityTextField.getText();
                String email = emailTextField.getText();

                if (passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())) {
                    makeGETRequest("https://studev.groept.be/api/a21ib2d04/user_input/"+name+"/"+family+"/"+gender+"/"+password+"/"+confirmpassword+"/"+country+"/"+email );
                    JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "password did not match");
                }


        }

        if (e.getSource() == backButton) {
            JFrame choice = new loginChoiceFrame("main");
            choice.setVisible(true);
            choice.setSize(1000,800);
            choice.setBounds(10, 10, 370, 600);
            dispose();
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
