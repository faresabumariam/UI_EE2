import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
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
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class HistogramFrame extends JFrame
{
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;
    private JPanel barPanel;
    private JPanel labelPanel;
    private JButton backButton;
    private List<Bar> bars = new ArrayList<Bar>();


    public HistogramFrame()
    {
//        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );
        barPanel.setBackground(Color.darkGray);

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );
        labelPanel.setBackground(Color.darkGray);

        backButton = new JButton("BACK");
        backButton.setBackground(Color.DARK_GRAY);

        backButton.setForeground(Color.white);

        add(barPanel, BorderLayout.NORTH);
        add(labelPanel, BorderLayout.CENTER);
        add(backButton,BorderLayout.AFTER_LAST_LINE);

        this.createAndShowGUI();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame main = new mainFrame("main");
                main.setVisible(true);



            }
        });
    }

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setForeground(Color.white);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            barLabel.setForeground(Color.white);
            labelPanel.add( barLabel );

        }
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }


    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }




    private void createAndShowGUI() {
        HistogramPanel panel = new HistogramPanel();

        String zone1 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "1");
        String z1 = panel.parseJSON(zone1, "countZone");
        String zone2 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "2");
        String z2 = panel.parseJSON(zone2, "countZone");
        String zone3 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "3");
        String z3 = panel.parseJSON(zone3, "countZone");
        String zone4 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "4");
        String z4 = panel.parseJSON(zone4, "countZone");
        String zone5 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "5");
        String z5 = panel.parseJSON(zone5, "countZone");
        String zone6 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "6");
        String z6 = panel.parseJSON(zone6, "countZone");
        String zone7 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "7");
        String z7 = panel.parseJSON(zone7, "countZone");
        String zone8 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "8");
        String z8 = panel.parseJSON(zone8, "countZone");
        String zone9 = panel.makeGETRequest("https://studev.groept.be/api/a21ib2d04/statsSection/" + "9");
        String z9 = panel.parseJSON(zone9, "countZone");

        panel.addHistogramColumn("Zone 1", Integer.parseInt(z1), Color.cyan);
        panel.addHistogramColumn("Zone 2", Integer.parseInt(z2), Color.YELLOW);
        panel.addHistogramColumn("Zone 3", Integer.parseInt(z3), Color.cyan);
        panel.addHistogramColumn("Zone 4", Integer.parseInt(z4), Color.yellow);
        panel.addHistogramColumn("Zone 5", Integer.parseInt(z5), Color.cyan);
        panel.addHistogramColumn("Zone 6", Integer.parseInt(z6), Color.yellow);
        panel.addHistogramColumn("Zone 7", Integer.parseInt(z7), Color.CYAN);
        panel.addHistogramColumn("Zone 8", Integer.parseInt(z8), Color.yellow);
        panel.addHistogramColumn("Zone 9", Integer.parseInt(z9), Color.CYAN);
        panel.layoutHistogram();
        panel.setBackground(Color.darkGray);
        panel.setForeground(Color.darkGray);


        JFrame frame = new JFrame("Histogram Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {

//        EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
////                createAndShowGUI();
//            }
//        });

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



