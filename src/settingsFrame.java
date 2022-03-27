import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class settingsFrame extends JFrame {
    private JButton backButton;
    private JButton button1;
    private JLabel settings;
    private JPanel settingsPanel;
    private JLabel sound;
    private JSlider slider1;
    private JSlider slider2;
    private JLabel song;
    private JButton Previous;
    private JButton Next;
    private JLabel songLabel;
    private int value1;
    private int value2;
    private int counter = 1;

    public settingsFrame(String title) {
        super(title);
        setSize(600, 400);
        setContentPane(settingsPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value1 = slider2.getValue();
                makeGETRequest("https://studev.groept.be/api/a21ib2d04/lightsButton_input/" + String.valueOf(value1));
            }
        });

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value2 = slider1.getValue();
                makeGETRequest("https://studev.groept.be/api/a21ib2d04/soundButton_input/" + String.valueOf(value2));
            }
        });

        Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                song.setText("Star Wars");
                if(counter > 1 ){
                    if(counter == 2){
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(1));
                        song.setText("Star Wars");
                        counter--;
                    }
                    else if(counter == 3){
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(2));
                        song.setText("LondenBridge");
                        counter--;
                    }
                    else if(counter == 4){
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(3));
                        song.setText("Twinkle Twinkle");
                        counter--;
                    }
                else {
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(1));
                        song.setText("Star Wars");
                    }
                }
            }
        });

        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter < 4){
                    if(counter == 1){
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(2));
                        song.setText("LondenBridge");
                        counter++;
                    }
                    else if(counter == 2){
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(3));
                        song.setText("Twinkle Twinkle");
                        counter++;
                    }
                    else if(counter == 3){
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(4));
                        song.setText("Manaderna");
                        counter++;
                    }
                else {
                        makeGETRequest("https://studev.groept.be/api/a21ib2d04/songButton_input/" + String.valueOf(4));
                        song.setText("Manaderna");
                    }
                }
            }
        });
    }


    public String makeGETRequest(String urlName) {
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
            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }
            conn.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }
}




