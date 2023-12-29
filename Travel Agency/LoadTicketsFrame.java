package travelagency;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Byron
 */
public class LoadTicketsFrame extends JFrame {
    private JLabel sumTicketsLbl;
    private JLabel maxTicketsLbl;
    private JLabel minTicketsLbl;
    private JLabel allCostLbl;
    
    private JTextField sumTicketsTf;
    private JTextField maxTicketsTf;
    private JTextField minTicketsTf;
    private JTextField allCostTf;
    
    private ArrayList<Traveler> travelersList;
    
    private JTextArea area;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton refreshBtn;
    private JButton exitBtn;

    public LoadTicketsFrame() {
        super();

        sumTicketsLbl = new JLabel("Total Tickets:\t");
        allCostLbl = new JLabel("Sum of Cost:\t");
        maxTicketsLbl = new JLabel("Max Cost of Tickets:\t");
        minTicketsLbl = new JLabel("Min Cost of Tickets:\t");
        
        sumTicketsTf = new JTextField(10);
        allCostTf = new JTextField(10);
        maxTicketsTf = new JTextField(15);
        minTicketsTf = new JTextField(15);
        
        travelersList = new ArrayList();

        loadBtn = new JButton("Load");
        saveBtn = new JButton("Create New Ticket");
        refreshBtn = new JButton("Refresh");
        exitBtn = new JButton("Exit");
        area = new JTextArea();

    }

    public void prepareUI() {

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        topPanel.add(sumTicketsLbl);
        topPanel.add(sumTicketsTf);
        
        topPanel.add(allCostLbl);
        topPanel.add(allCostTf);
        
        topPanel.add(minTicketsLbl);
        topPanel.add(minTicketsTf);
        
        topPanel.add(maxTicketsLbl);
        topPanel.add(maxTicketsTf);
        
        bottomPanel.add(loadBtn);
        bottomPanel.add(saveBtn);
        bottomPanel.add(refreshBtn);
        bottomPanel.add(exitBtn);

        area.setSize(800, 400);
        area.setLocation(10, 350);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadTicketsFrame.this.setVisible(false);
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(LoadTicketsFrame.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    area.setText(""); //Clears previous text on screen
                    String fileName = fc.getSelectedFile().getAbsolutePath();

                    if (fileName != null && !fileName.isEmpty()) {
                        loadFromFile(fileName);
                    }

                }
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTicketFrame frame = new NewTicketFrame();
                frame.prepareUI();
            }
        });

        area.setEditable(false);
        sumTicketsTf.setEditable(false);
        allCostTf.setEditable(false);
        minTicketsTf.setEditable(false);
        maxTicketsTf.setEditable(false);
        this.add(area);
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setTitle("Load Ticket Form");
        this.setResizable(false);
        this.setVisible(true);
    }

    private void loadFromFile(String fileName) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            String[] tokens;
            Traveler traveler;

            while (reader.ready()) {

                line = reader.readLine();

                tokens = line.split("\t");

                if (tokens.length == 9) {
                    double cost = Double.parseDouble(tokens[4]);
                    traveler = new Traveler(tokens[0], tokens[1], tokens[2], tokens[3], cost, tokens[5], tokens[6], tokens[7], tokens[8]);
                    travelersList.add(traveler); //prosthetoume kathe grammh oxi ws apla keimeno alla ws antikeimena dhladh san traveler
                    travelersList.sort(Comparator.comparingDouble(Traveler::getCost));

                    area.append(line);
                    area.append("\n");
                    
                    statistics();
                }

            }

            reader.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    private void refresh() {
        LoadTicketsFrame.this.revalidate();
        LoadTicketsFrame.this.repaint();
    }
    
    private void statistics(){
        int ticketSum;
        double totalCost = 0;
        
        Double maxCost = travelersList.get(0).getCost();
        Double minCost = travelersList.get(0).getCost();
        
        String maxTicketId = travelersList.get(0).getTicketId();
        String minTicketId = travelersList.get(0).getTicketId();
        
        for(ticketSum = 0; ticketSum < travelersList.size(); ticketSum++){
            if(maxCost < travelersList.get(ticketSum).getCost()){ //Check for max
                maxCost = travelersList.get(ticketSum).getCost();
                maxTicketId = travelersList.get(ticketSum).getTicketId();
            }
            if(minCost > travelersList.get(ticketSum).getCost()){ //Check for min
                minCost = travelersList.get(ticketSum).getCost();
                minTicketId = travelersList.get(ticketSum).getTicketId();
            }
            totalCost = totalCost + travelersList.get(ticketSum).getCost(); //Add to the total cost
        }
        sumTicketsTf.setText(Integer.toString(ticketSum));
        allCostTf.setText(Double.toString(totalCost));
        minTicketsTf.setText(Double.toString(minCost) + " Ticket ID: " + minTicketId);
        maxTicketsTf.setText(Double.toString(maxCost) + " Ticket ID: " + maxTicketId);
    }
    
}
