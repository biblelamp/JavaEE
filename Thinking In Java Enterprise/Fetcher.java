//: c15:Fetcher.java
// <applet code=Fetcher width=500 height=300>
// </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
//import com.bruceeckel.swing.*;

public class Fetcher extends JApplet {
    JButton fetchIt = new JButton("Fetch the Data");
    JTextField f = new JTextField("Fetcher.java", 20);
    JTextArea t = new JTextArea(10, 40);

    public void init() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        fetchIt.addActionListener(new FetchL());
        cp.add(new JScrollPane(t));
        cp.add(f);
        cp.add(fetchIt);
    }

    public class FetchL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                URL url = new URL(getDocumentBase(), f.getText());
                t.setText(url + "\n");
                InputStream is = url.openStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String line;
            while ((line = in.readLine()) != null)
                t.append(line + "\n");
            } catch (Exception ex) {
                t.append(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        Console.run(new Fetcher(), 500, 300);
    }
}