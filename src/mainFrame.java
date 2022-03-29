import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


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
    private JSpinner numberOfPeople;
    private JToggleButton toggleLight;
    private JToggleButton toggleSound;
    private JLabel soundLabel;
    private JLabel lightLabel;
    private JLabel logo;


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
                    makeGETRequest("https://studev.groept.be/api/a21ib2d04/lightControl_input/"+"1" );

                }
                else
                {
                    lightLabel.setText("Off");
                    makeGETRequest("https://studev.groept.be/api/a21ib2d04/lightControl_input/"+"0" );
                }
            }
        });

        toggleSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleSound.isSelected())
                {
                    soundLabel.setText("On");
                    makeGETRequest("https://studev.groept.be/api/a21ib2d04/soundControl_input/"+"1" );

                }
                else
                {
                    soundLabel.setText("Off");
                    makeGETRequest("https://studev.groept.be/api/a21ib2d04/soundControl_input/"+"0" );

                }
            }
        });


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

    public static void main(String[] args) {
        JFrame UI = new mainFrame("Group D4");
        UI.setVisible(true);
        UI.pack();
    }
}


