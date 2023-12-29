package travelagency;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Byron
 */
public class AboutFrame extends JFrame {
    private ImageIcon image1;
    private JLabel label1;
    
    public void prepareUI() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        JLabel aboutLbl = new JLabel();
 
        aboutLbl.setText("<html><h2>TRAVEL AGENCY</h2><br><h3>Created by: Byron Paniperis AM: 18390200 Completed at:6/13/21</h3> This app has been developed in the framework of Java Development Course for exam purposes, <br><strong>2020-2021.</html>");

        image1 = new ImageIcon(getClass().getResource("desktop.png"));
        label1 = new JLabel(image1);
        
        bottomPanel.add(label1);
        topPanel.add(aboutLbl);
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(bottomPanel,BoxLayout.Y_AXIS);
        this.pack();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setTitle("About");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);

    } 
    
}
