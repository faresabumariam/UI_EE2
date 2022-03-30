import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.*;


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
    SpinnerModel nP = new SpinnerNumberModel(1,1,20,1);
    private JSpinner numberOfPeople;
    private JToggleButton toggleLight;
    private JToggleButton toggleSound;
    private JLabel soundLabel;
    private JLabel lightLabel;
    private JPanel statsPanel;
    private JButton Histogram;
    private JLabel statsSlider;
    private JLabel statsLabel;
    int i = 0;
    private JButton histogram;



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

        histogram= new JButton("Hist");

        ArrayList<String> stats = new ArrayList<>();
        stats.add("You spend most of your time in zone: ");
        stats.add("You saved: "+"euros");
        stats.add("You have not left your room in: sec, touch some grass!");

//        statsLabel.setText(stats.get(0));
//        statsLabel.repaint();
//        statsLabel.validate();

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(stats.get(i));

                if(i!=stats.size()-1){
                    i++;
                }
                else{
                    i=0;
                }
                statsSlider.setText(stats.get(i));
//                statsLabel.repaint();
//                statsLabel.validate();

            }
        });

        numberOfPeople.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numberOfPeople.setModel(nP);
                int value = (int) numberOfPeople.getValue();
                System.out.println(value);
                makeGETRequest("https://studev.groept.be/api/a21ib2d04/nrofpeople_input/"+ String.valueOf(value));
            }
        });

        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(i!=0){
                    i--;
                }
                else{
                    i=stats.size()-1;
                }

                statsSlider.setText(stats.get(i));
//                statsLabel.repaint();
//                statsLabel.validate();
                i = (i>stats.size()-1)? 0 : i;

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


        Histogram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame hist = new HistogramFrame();
                hist.repaint();
                hist.validate();
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


