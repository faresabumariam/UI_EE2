import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class ProfileFrame extends JFrame {
    private JButton backButton;
    private JLabel usernameLabel;
    private JPanel userPanel;
    private JToggleButton toggleButton1;
    private JLabel nameLabel;
    private JLabel familyLabel;
    private JLabel genderLabel;
    private JLabel emailLabel;
    private JLabel countryLabel;
    private JLabel family;
    private JLabel gender;
    private JLabel email;
    private JLabel country;
    private JLabel first;
    private JLabel info;


    public ProfileFrame(String title) {

        super(title);
        setSize(500,400);
        setContentPane(userPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String info = makeGETRequest("https://studev.groept.be/api/a21ib2d04/getLastUserInfo");
        first.setText(parseJSON(info, "name"));
        family.setText(parseJSON(info, "family"));
        gender.setText(parseJSON(info, "gender"));
        email.setText(parseJSON(info, "email"));
        country.setText(parseJSON(info,"country"));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            };
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

    public String parseJSON(String jsonString, String key){
        String var = "";
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject curObject = array.getJSONObject(i);
                var+=curObject.getString(key);
            }}
        catch (JSONException e){
            e.printStackTrace();
        }

        return var;
    }

}


