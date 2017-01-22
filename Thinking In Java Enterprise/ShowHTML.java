//: c15:ShowHTML.java
// <applet code=ShowHTML width=100 height=50>
// </applet>
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
//import com.bruceeckel.swing.*;

public class ShowHTML extends JApplet {
    JButton send = new JButton("Go");
    JLabel l = new JLabel();

    public void init() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        send.addActionListener(new Al());
        cp.add(send);
        cp.add(l);
    }

    class Al implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                // This can be a CGI program
                // instead of HTML page
                URL url = new URL(getDocumentBase(), "SimpleApplet.html");
                // It displays the output URL using
                // a Web browser as a simple page:
                getAppletContext().showDocument(url);
            } catch (Exception e) {
                l.setText(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        Console.run(new ShowHTML(), 100, 50);
    }
}