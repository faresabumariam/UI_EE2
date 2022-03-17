import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class mainFrame extends JFrame
{
    private JButton settings;
    private JButton profile;
    private JPanel myPanel;
    private JButton lightsSystem;
    private JFormattedTextField blacklineFormattedTextField;
    private JButton previous;
    private JButton next;
    private JPanel infoPanel1;
    private JPanel infoPanel2;
    private JSpinner spinner1;
    private JToggleButton toggleLight;
    private JToggleButton toggleSound;
    private JLabel soundLabel;
    private JLabel lightLabel;


    public mainFrame(String title) {
        super(title);
        setSize(1000, 800);
        setContentPane(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame settingsFrame = new settingsFrame("settings");
                settingsFrame.setVisible(true);
            }
        });

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame profileFrame = new ProfileFrame("profile");
                profileFrame.setVisible(true);
            }
        });





        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    infoPanel1.remove(infoPanel2);
                    infoPanel1.repaint();
                    infoPanel1.validate();
            }
            });

        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoPanel1.add(infoPanel2);
                infoPanel1.repaint();
                infoPanel1.validate();
            }
        });

        toggleLight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleLight.isSelected())
                {
                    lightLabel.setText("On");
                }
                else
                {
                    lightLabel.setText("Off");
                }
            }
        });

        toggleSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleSound.isSelected())
                {
                    soundLabel.setText("On");
                }
                else
                {
                    soundLabel.setText("Off");
                }
            }
        });
    }




    public static void main(String[] args) {
        JFrame UI = new mainFrame("Group D4");
        UI.setVisible(true);
        UI.pack();
    }
}


