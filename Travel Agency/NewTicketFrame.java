package travelagency;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
        
/**
 *
 * @author Byron
 */
public class NewTicketFrame extends JFrame  {

    private JLabel ticketIdLbl;
    private JLabel issueDateLbl;
    private JLabel clientNameLbl;
    private JLabel itineraryLbl;
    private JLabel costLbl;
    private JLabel seatNumLbl;
    private JLabel flightIdLbl;
    private JLabel departureTimeLbl;
    private JLabel arrivalTimeLbl;

    private JTextArea area;

    private ArrayList<Traveler> travelersList;

    private JTextField ticketIdTf;
    private JTextField issueDateTf;
    private JTextField clientNameTf;
    private JTextField itineraryTf;
    private JTextField costTf;
    private JTextField seatNumTf;
    private JTextField flightIdTf;
    private JTextField departureTimeTf;
    private JTextField arrivalTimeTf;

    private JButton saveBtn;
    private JButton saveTicketBtn;
    private JButton ticketIdBtn;

    public NewTicketFrame() {
        super();

        ticketIdLbl = new JLabel("Ticket ID:\t");
        issueDateLbl = new JLabel("Issue Date:\t");
        clientNameLbl = new JLabel("Client Name:\t");
        itineraryLbl = new JLabel("Itinerary:\t");
        costLbl = new JLabel("Cost:\t");
        seatNumLbl = new JLabel("Seat Number:\t");
        flightIdLbl = new JLabel("Flight ID:\t");
        departureTimeLbl = new JLabel("Departure Time:\t");
        arrivalTimeLbl = new JLabel("Arrival Time:\t");

        travelersList = new ArrayList();

        area = new JTextArea();

        ticketIdTf = new JTextField("");
        issueDateTf = new JTextField("");
        clientNameTf = new JTextField("");
        itineraryTf = new JTextField("");
        costTf = new JTextField("");
        seatNumTf = new JTextField("");
        flightIdTf = new JTextField("");
        departureTimeTf = new JTextField("");
        arrivalTimeTf = new JTextField("");

        saveTicketBtn = new JButton("Save Ticket");
        saveBtn = new JButton("Save");
        ticketIdBtn = new JButton("Get Ticket ID");
    }

    public void prepareUI() {

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        //add components to the panels
        topPanel.add(ticketIdLbl);
        topPanel.add(ticketIdTf);

        topPanel.add(issueDateLbl);
        topPanel.add(issueDateTf);

        topPanel.add(clientNameLbl);
        topPanel.add(clientNameTf);

        topPanel.add(itineraryLbl);
        topPanel.add(itineraryTf);

        topPanel.add(costLbl);
        topPanel.add(costTf);

        topPanel.add(seatNumLbl);
        topPanel.add(seatNumTf);

        topPanel.add(flightIdLbl);
        topPanel.add(flightIdTf);

        topPanel.add(departureTimeLbl);
        topPanel.add(departureTimeTf);

        topPanel.add(arrivalTimeLbl);
        topPanel.add(arrivalTimeTf);

        bottomPanel.add(ticketIdBtn);
        bottomPanel.add(saveTicketBtn);
        bottomPanel.add(saveBtn);

        saveTicketBtn.setBounds(10, 900, 100, 40);
        saveBtn.setBounds(900, 900, 70, 40);

        area.setSize(965, 400);
        area.setLocation(10, 350);

        ticketIdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTicketId();
            }
        });

        saveTicketBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTicket();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (travelersList.isEmpty()) {
                    JOptionPane.showMessageDialog(NewTicketFrame.this,
                            "Nothing to Save",
                            "File Access Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(NewTicketFrame.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    String fileName = fc.getSelectedFile().getPath() + ".txt"; //Adding the .txt on the end converts the file in text format

                    if (fileName != null && !fileName.isEmpty()) {
                        saveTravelerList(fileName);
                    }
                }
            }
        });

        //area.setEditable(false);
        ticketIdTf.setEditable(false);
        this.add(area);
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setTitle("New Ticket Form");
        this.setResizable(false);
        this.setVisible(true);
    }

    private void startTicket() {
        String ticketId = ticketIdTf.getText().trim();
        String issueDate = issueDateTf.getText().trim();
        String clientName = clientNameTf.getText().trim();
        String itinerary = itineraryTf.getText().trim();
        double cost = Double.parseDouble(costTf.getText());
        String seatNum = seatNumTf.getText().trim();
        String flightId = flightIdTf.getText().trim();
        String departureTime = departureTimeTf.getText().trim();
        String arrivalTime = arrivalTimeTf.getText().trim();

        saveTicket(ticketId, issueDate, clientName, itinerary, cost, seatNum, flightId, departureTime, arrivalTime);
    }

    private void saveTravelerList(String fileName) {

        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(fileName, true));

            for (Traveler traveler : travelersList) {
                file.write(traveler.toString());
                file.newLine();
            }

            file.close();
            JOptionPane.showMessageDialog(
                    NewTicketFrame.this,
                    travelersList.size() + " records saved to " + fileName,
                    "Save Completed",
                    JOptionPane.INFORMATION_MESSAGE);

            NewTicketFrame.this.setVisible(false); // Closes Window After Saving The List

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void saveTicket(String ticketId, String issueDate, String clientName, String itinerary, double cost, String seatNum, String flightId, String departureTime, String arrivalTime) {
        Traveler traveler = new Traveler(ticketId, issueDate, clientName, itinerary, cost, seatNum, flightId, departureTime, arrivalTime);
        
        travelersList.add(traveler);
        
        area.append(traveler.toString());
        area.append("\n");
    }

    public void getTicketId() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String ticketId = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        ticketIdTf.getText();
        ticketIdTf.setText(ticketId);

    }

}
