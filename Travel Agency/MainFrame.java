package travelagency;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Byron
 */
public class MainFrame extends JFrame {

    private JButton newTicketBtn;
    private JButton ticketListBtn;
    private JButton aboutBtn;
    private JButton exitBtn;

    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem newTicketItem, ticketListItem, aboutItem, exitItem;

    public MainFrame() {
        super();

        //Create components
        newTicketBtn = new JButton("Create a new ticket");
        ticketListBtn = new JButton("Load ticket list");
        aboutBtn = new JButton("About this application");
        exitBtn = new JButton("Exit Application");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        newTicketItem = new JMenuItem("New Ticket");
        ticketListItem = new JMenuItem("Load List");
        exitItem = new JMenuItem("Exit App");
        aboutItem = new JMenuItem("About");

    }

    public void prepareUI() {

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //create panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(newTicketBtn);
        mainPanel.add(ticketListBtn);
        mainPanel.add(aboutBtn);
        mainPanel.add(exitBtn);

        this.add(mainPanel, BorderLayout.CENTER);

        fileMenu.add(newTicketItem);
        fileMenu.add(ticketListItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        newTicketBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTicketFrame frame = new NewTicketFrame();
                frame.prepareUI();
            }
        });

        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTicketFrame frame = new NewTicketFrame();
                frame.prepareUI();
            }
        });

        ticketListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadTicketsFrame frame = new LoadTicketsFrame();
                frame.prepareUI();
            }
        });

        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadTicketsFrame frame = new LoadTicketsFrame();
                frame.prepareUI();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        }); //Window Close on X button

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame frame = new AboutFrame();
                frame.prepareUI();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame frame = new AboutFrame();
                frame.prepareUI();
            }
        });

        //setup the frame
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setTitle("Tickets");
        this.setVisible(true);

    }

    private void exitApp() {
        int i = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit the application?"); //To show conf.. epistrefei int
        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
