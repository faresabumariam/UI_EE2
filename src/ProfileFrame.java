import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileFrame extends JFrame {
    private JButton backButton;
    private JLabel usernameLabel;
    private JPanel userPanel;
    private JToggleButton toggleButton1;


    public ProfileFrame(String title) {

        super(title);
        setSize(500,400);
        setContentPane(userPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            };
        });



    }
}
