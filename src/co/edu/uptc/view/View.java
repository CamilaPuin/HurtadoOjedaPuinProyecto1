package co.edu.uptc.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JButton registerParking;
    private JButton createRecepcionist;
    private JButton updateRecepcionist;
    private JButton salesReport;
    private JButton logout;
    private JPanel adminLeftPanel;
    private JPanel adminRightPanel;

    private JButton availableSpaces;
    private JButton registerVehicle;
    private JButton exitVehicle;
    private JButton recepLogOut;
    private JPanel recepLeftPanel;
    private JPanel recepRightPanel;

    public View() {
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().add(recepcionistPanel());
        setVisible(true);
    }

    public JPanel adminPanel() {
        // TODO layout here to organization
        JPanel adminPanel = new JPanel(// new whatLayout
        );
        adminLeftPanel = new JPanel();
        adminLeftPanel.setLayout(new BoxLayout(adminLeftPanel, BoxLayout.Y_AXIS));
        adminLeftPanel.setPreferredSize(new Dimension(150, getHeight())); 

        registerParking = new JButton("Register Parking");
        createRecepcionist = new JButton("Create Recepcionist");
        updateRecepcionist = new JButton("Update Recepcionist");
        salesReport = new JButton("Sales Report");
        logout = new JButton("Logout");
        adminLeftPanel.add(Box.createVerticalStrut(20));
        adminLeftPanel.add(registerParking, "Register Parking");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(createRecepcionist, "Create Recepcionist");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(updateRecepcionist, "Update Recepcionist");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(salesReport, "Sales Report");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(logout, "Logout");
        cardLayout = new CardLayout();
        adminRightPanel = new JPanel(cardLayout);
        JPanel registerParkingPanel = registerParking();
        JPanel createRecepcionistPanel = createRecepcionist();
        JPanel updateRecepcionistPanel = updateRecepcionist();
        JPanel salesReportPanel = salesReport();
        JPanel logoutPanel = logout();
        adminRightPanel.add(registerParkingPanel, "Register Parking");
        adminRightPanel.add(createRecepcionistPanel, "Create Recepcionist");
        adminRightPanel.add(updateRecepcionistPanel, "Update Recepcionist");
        adminRightPanel.add(salesReportPanel, "Sales Report");
        adminRightPanel.add(logoutPanel, "Logout");

        registerParking.addActionListener(this);
        createRecepcionist.addActionListener(this);
        updateRecepcionist.addActionListener(this);
        salesReport.addActionListener(this);
        logout.addActionListener(this);

        adminPanel.add(adminLeftPanel);
        adminPanel.add(adminRightPanel);
        return adminPanel;
    }

    private JPanel registerParking() {
        JPanel registerParkingPanel = new JPanel();
        registerParkingPanel.add(new JLabel("register parking"));
        
    }

    private JPanel createRecepcionist() {
        JPanel createRecepcionistPanel = new JPanel();
        createRecepcionistPanel.add(new JLabel("create recepcionist"));
        return createRecepcionistPanel;
    }

    private JPanel updateRecepcionist() {
        JPanel updateRecepcionistPanel = new JPanel();
        updateRecepcionistPanel.add(new JLabel("update recepcionist"));
        return updateRecepcionistPanel;
    }

    private JPanel salesReport() {
        JPanel salesReportPanel = new JPanel();
        salesReportPanel.add(new JLabel("sales report"));
        return salesReportPanel;
    }

    private JPanel logout() {
        JPanel logoutPanel = new JPanel();
        logoutPanel.add(new JLabel("logout"));
        return logoutPanel;
    }

    public JPanel recepcionistPanel() {
        // TODO layout here to organization
        JPanel recepPanel = new JPanel(// new whatLayout
        );
        recepLeftPanel = new JPanel();
        recepLeftPanel.setLayout(new BoxLayout(recepLeftPanel, BoxLayout.Y_AXIS));
        recepLeftPanel.setPreferredSize(new Dimension(150, getHeight()));
        availableSpaces = new JButton("Available Spaces");
        registerVehicle = new JButton("Register Vehicle");
        exitVehicle = new JButton("Exit Vehicle");
        recepLogOut = new JButton("Log Out");
        recepLeftPanel.add(Box.createVerticalStrut(20));
        recepLeftPanel.add(availableSpaces, "Availabel Spaces");
        recepLeftPanel.add(Box.createVerticalStrut(10));
        recepLeftPanel.add(registerVehicle, "Register Vehicle");
        recepLeftPanel.add(Box.createVerticalStrut(10));
        recepLeftPanel.add(exitVehicle, "Exit Vehicle");
        recepLeftPanel.add(Box.createVerticalStrut(10));
        recepLeftPanel.add(recepLogOut, "Log Out");

        cardLayout = new CardLayout();
        recepRightPanel = new JPanel(cardLayout);

        JPanel availableSpacesPanel = availableSpacesPanel();
        JPanel registerVehiclePanel = registerVehiclePanel();
        JPanel exitVehiclePanel = exitVehiclePanel();
        JPanel recepLogOutPanel = recepLogOutPanel();
        recepRightPanel.add(availableSpacesPanel, "Available Spaces");
        recepRightPanel.add(registerVehiclePanel, "Register Vehicle");
        recepRightPanel.add(exitVehiclePanel, "Exit Vehicle");
        recepRightPanel.add(recepLogOutPanel, "Log Out");

        availableSpaces.addActionListener(this);
        registerVehicle.addActionListener(this);
        exitVehicle.addActionListener(this);
        recepLogOut.addActionListener(this);

        recepPanel.add(recepLeftPanel);
        recepPanel.add(recepRightPanel);
        return recepPanel;
    }

    private JPanel availableSpacesPanel() {
        JPanel availableSpacesPanel = new JPanel();
        availableSpacesPanel.add(new JLabel("available spaces"));
        return availableSpacesPanel;
    }

    private JPanel registerVehiclePanel() {
        JPanel registerVehiclePanel = new JPanel();
        registerVehiclePanel.add(new JLabel("register vehicle"));
        return registerVehiclePanel;
    }

    private JPanel exitVehiclePanel() {
        JPanel exitVehiclePanel = new JPanel();
        exitVehiclePanel.add(new JLabel("exit vehicle"));
        return exitVehiclePanel;
    }

    private JPanel recepLogOutPanel() {
        JPanel recepLogOutPanel = new JPanel();
        recepLogOutPanel.add(new JLabel("log out"));
        return recepLogOutPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerParking)
            cardLayout.show(adminRightPanel, "Register Parking");
        if (e.getSource() == createRecepcionist)
            cardLayout.show(adminRightPanel, "Create Recepcionist");
        if (e.getSource() == updateRecepcionist)
            cardLayout.show(adminRightPanel, "Update Recepcionist");
        if (e.getSource() == salesReport)
            cardLayout.show(adminRightPanel, "Sales Report");
        if (e.getSource() == logout)
            cardLayout.show(adminRightPanel, "Logout");
        if (e.getSource() == availableSpaces)
            cardLayout.show(recepRightPanel, "Available Spaces");
        if (e.getSource() == registerVehicle)
            cardLayout.show(recepRightPanel, "Register Vehicle");
        if (e.getSource() == exitVehicle)
            cardLayout.show(recepRightPanel, "Exit Vehicle");
        if (e.getSource() == recepLogOut)
            cardLayout.show(recepRightPanel, "Log Out");
    }
}