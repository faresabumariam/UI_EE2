import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginChoiceFrame extends JFrame {
    private JButton logIN;
    private JPanel panel1;
    private JButton signUp;


    public loginChoiceFrame(String title) {
        super(title);
        setSize(1000, 800);
        setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        logIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame login = new LoginFrame();
                login.setVisible(true);
                login.setBounds(10, 10, 370, 600);
                dispose();
            }
        });


        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame signup = new Registration();
//                signup.setVisible(true);
//                signup.setBounds(10, 10, 370, 600);
                dispose();
            }
        });



    }

    public static void main(String[] a) {
        loginChoiceFrame frame = new loginChoiceFrame("Choose");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setResizable(false);
    }

}



