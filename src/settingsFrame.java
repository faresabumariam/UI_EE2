import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class settingsFrame extends JFrame {
    private JButton backButton;
    private JButton button1;
    private JLabel settings;
    private JPanel settingsPanel;
    private JLabel sound;
    private JSlider slider1;
    private JSlider slider2;


    public settingsFrame(String title)
    {
        super(title);
        setSize(500,400);
        setContentPane(settingsPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            };
        });



    }




}
