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
<<<<<<< HEAD

=======
>>>>>>> a260f4f659bb4d71944785739534034df7096f41
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

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
        getContentPane().setLayout(new GridLayout(1,2));
        getContentPane().add(registerVehiclePanel());
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

    private JPanel ticketPanel() {
        JPanel ticketPanel = new JPanel(new GridBagLayout());
        ticketPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(ticketPanel, createLabel("Su ticket ha sido generado exitosamente"), gbc, 0, 0, 1);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        addComponent(ticketPanel, createLabel("Resumen"), gbc, 0, 1, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(ticketPanel, createButton("Imprimir recibo"), gbc, 0, 2, 1);
        // TODO Aqui va el consolidado
        JTextArea consolidado = new JTextArea(5, 10);
        consolidado.setText("Aquí va el consolidado");
        addComponent(ticketPanel, consolidado, gbc, 0, 3, 1);
        addComponent(ticketPanel, createButton("Volver al menú principal"), gbc, 0, 4, 1);
        return ticketPanel;
    }

     // TODO Panel vacio, va el selector de fechas de Santi
    private JPanel generateReportPanel() {
        JPanel generateReport = new JPanel();
        return generateReport;
    }

    private JPanel registerParking() {
        JPanel parkingRegisterPanel = new JPanel(new GridBagLayout());
        parkingRegisterPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(parkingRegisterPanel, createLabel("Registro de estacionamiento"), gbc, 0, 0, 2);
        addComponent(parkingRegisterPanel, createLabel("Datos Básicos"), gbc, 0, 1, 2);
        addComponent(parkingRegisterPanel, createLabel("Nombre"), gbc, 0, 3, 1);
        addComponent(parkingRegisterPanel, createLabel("Dirección"), gbc, 0, 4, 1);
        addComponent(parkingRegisterPanel, createLabel("N° disponible"), gbc, 0, 5, 1);
        addComponent(parkingRegisterPanel, createTextField(), gbc, 1, 3, 1);
        addComponent(parkingRegisterPanel, createTextField(), gbc, 1, 4, 1);
        addComponent(parkingRegisterPanel, createTextField(), gbc, 1, 5, 1);
        addComponent(parkingRegisterPanel, createLabel("Horario de atención"), gbc, 0, 6, 2);
        addComponent(parkingRegisterPanel, createLabel("Hora inicio"), gbc, 0, 7, 1);
        addComponent(parkingRegisterPanel, createLabel("Hora fin"), gbc, 0, 8, 1);
        addComponent(parkingRegisterPanel, createTextField(), gbc, 1, 7, 1);
        addComponent(parkingRegisterPanel, createTextField(), gbc, 1, 8, 1);
        addComponent(parkingRegisterPanel, createButton("Registar"), gbc, 0, 9, 2);
        return parkingRegisterPanel;
    }

    private JPanel createRecepcionist() {
        JPanel recepcionistPanel = new JPanel(new GridBagLayout());
        recepcionistPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(recepcionistPanel, createLabel("Digite los datos para crear el recepcionista"), gbc, 0, 0, 2);
        addComponent(recepcionistPanel, createLabel("Documento"), gbc, 0, 1, 1);
        addComponent(recepcionistPanel, createLabel("Nombres"), gbc, 0, 2, 1);
        addComponent(recepcionistPanel, createLabel("Apellidos"), gbc, 0, 3, 1);
        addComponent(recepcionistPanel, createLabel("Dirección"), gbc, 0, 4, 1);
        addComponent(recepcionistPanel, createLabel("Telefono"), gbc, 0, 5, 1);
        addComponent(recepcionistPanel, createLabel("Email"), gbc, 0, 6, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 1, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 2, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 6, 1);
        addComponent(recepcionistPanel, createButton("Crear"), gbc, 0, 7, 2);
        return recepcionistPanel;
    }

    private JPanel updateRecepcionist() {
        JPanel recepcionistPanel = new JPanel(new GridBagLayout());
        recepcionistPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(recepcionistPanel, createLabel("Digite los datos para actualizar el recepcionista"), gbc, 0, 0, 2);
        addComponent(recepcionistPanel, createLabel("Documento"), gbc, 0, 1, 1);
        addComponent(recepcionistPanel, createLabel("Dirección"), gbc, 0, 2, 1);
        addComponent(recepcionistPanel, createLabel("Telefono"), gbc, 0, 3, 1);
        addComponent(recepcionistPanel, createLabel("Email"), gbc, 0, 4, 1);
        addComponent(recepcionistPanel, createLabel("Nueva contraseña"), gbc, 0, 5, 1);
        addComponent(recepcionistPanel, createLabel("Confirmar contraseña"), gbc, 0, 6, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 1, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 2, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField(), gbc, 1, 6, 1);
        gbc.insets = new Insets(0, 0, 0, 0);
        addComponent(recepcionistPanel, createLabel("- La nueva contraseña no debe ser repetida"), gbc, 0, 7, 2);
        addComponent(recepcionistPanel, createLabel("- No tener caracteres especiales"), gbc, 0, 8, 2);
        addComponent(recepcionistPanel, createLabel("- Debe tener mínimo 8 dígitos"), gbc, 0, 9, 2);
        gbc.insets = new Insets(10, 0, 0, 0);
        addComponent(recepcionistPanel, createButton("Acutualizar"), gbc, 0, 10, 2);
        return recepcionistPanel;
    }

    private JPanel salesReport() {
        JPanel report = new JPanel(new GridBagLayout());
        report.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(report, createLabel("Reporte de ventas de:"), gbc, 0, 0, 1);
        addComponent(report, createLabel("Fecha aqui"), gbc, 1, 0, 1);
        addComponent(report, createLabel("Total ingresos"), gbc, 0, 1, 1);
        addComponent(report, createLabel("ingresos aqui"), gbc, 1, 1, 1);
        addComponent(report, createLabel("Total vehiculos ingresados"), gbc, 0, 2, 1);
        addComponent(report, createLabel("Numero aqui"), gbc, 1, 2, 1);
        JTextArea consolidado = new JTextArea(5, 10);
        consolidado.setText("Aquí va el consolidado");
        addComponent(report, consolidado, gbc, 0, 3, 2);
        addComponent(report, createButton("Regresar al menú"), gbc, 0, 4, 2);
        return report;
    }

    private JPanel logout() {
        JPanel logOutPanel = new JPanel(new GridBagLayout());
        logOutPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(logOutPanel, createLabel("¿Desea cerrar sesión?"), gbc, 0, 0, 2);
        addComponent(logOutPanel, createButton("Si"), gbc, 0, 1, 1);
        addComponent(logOutPanel, createButton("No"), gbc, 1, 1, 1);
        return logOutPanel;
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
        JPanel availableSpacesPanel = new JPanel(new GridBagLayout());
        availableSpacesPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(availableSpacesPanel, createLabel("Disponibilidad"), gbc, 0, 0, 2);
        addComponent(availableSpacesPanel, createLabel("Hay 5 espacios disponibles",25), gbc, 0, 1, 2);
        addComponent(availableSpacesPanel, createLabel("Moto"), gbc, 0, 2, 1);
        addComponent(availableSpacesPanel, createLabel("3 espacios disponibles"), gbc, 1, 2, 1);
        addComponent(availableSpacesPanel, createLabel("Carro"), gbc, 0, 3, 1);
        addComponent(availableSpacesPanel, createLabel("2 espacios disponibles"), gbc, 1, 3, 1);
        addComponent(availableSpacesPanel, createButton("Salir"), gbc, 0, 4, 2);
        return availableSpacesPanel;
    }

    private JPanel registerVehiclePanel() {
        JPanel incomePanel = new JPanel(new GridBagLayout());
        incomePanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(incomePanel, createLabel("Ingrese los datos del vehiculo:"), gbc, 0, 0, 2);
        addComponent(incomePanel, createLabel("Placa"), gbc, 0, 1, 1);
        addComponent(incomePanel, createTextField(), gbc, 1, 1, 1);
        addComponent(incomePanel, createLabel("Tipo"), gbc, 0, 2, 1);
        addComponent(incomePanel, createComboBox(new String[] { "Moto", "Carro" }), gbc, 1, 2, 1);
        addComponent(incomePanel, createButton("Siguiente"), gbc, 0, 3, 2);
        return incomePanel;
    }

    private JPanel exitVehiclePanel() {
        JPanel ticketOutPanel = new JPanel(new GridBagLayout());
        ticketOutPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(ticketOutPanel, createLabel("Ingrese la placa del vehiculo"), gbc, 0, 0, 2);
        addComponent(ticketOutPanel, createLabel("Placa"), gbc, 0, 1, 1);
        addComponent(ticketOutPanel, createTextField(), gbc, 1, 1, 1);
        addComponent(ticketOutPanel, createLabel("Ingrese el dinero para general el recibo"), gbc, 0, 2, 2);
        addComponent(ticketOutPanel, createLabel("Dinero"), gbc, 0, 3, 1);
        addComponent(ticketOutPanel, createTextField(), gbc, 1, 3, 1);
        addComponent(ticketOutPanel, createLabel("Cambio"), gbc, 0, 4, 1);
        addComponent(ticketOutPanel, createTextField(), gbc, 1, 4, 1);
        addComponent(ticketOutPanel, createLabel("Recibo"), gbc, 0, 5, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextArea consolidado = new JTextArea(5, 10);
        consolidado.setText("Aquí va el consolidado");
        addComponent(ticketOutPanel, consolidado, gbc, 0, 6, 2);
        gbc.fill = GridBagConstraints.NONE;
        addComponent(ticketOutPanel, createButton("Registrar salida"), gbc, 0, 7, 2);
        return ticketOutPanel;
    }

    private JPanel recepLogOutPanel() {
        JPanel consolidadoPanel = new JPanel(new GridBagLayout());
        consolidadoPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(consolidadoPanel, createLabel("Consolidado"), gbc, 0, 0, 2);
        JTextArea consolidado = new JTextArea(5, 10);
        consolidado.setText("Aquí va el consolidado");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(consolidadoPanel, consolidado, gbc, 0, 1, 2);
        JTextArea consolidado2 = new JTextArea(5, 10);
        consolidado2.setText("Aquí va el consolidado");
        addComponent(consolidadoPanel, consolidado2, gbc, 0, 2, 2);
        addComponent(consolidadoPanel, createLabel("¿Desea cerrar sesión?"), gbc, 0, 3, 2);
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 10;
        addComponent(consolidadoPanel, createButton("Si"), gbc, 0, 4, 1);
        addComponent(consolidadoPanel, createButton("No"), gbc, 1, 4, 1);
        return consolidadoPanel;
    }

    private JPanel welcome() {
        JPanel welcome = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);
        addComponent(welcome, createLabel("BIENVENIDO A PARKING UPTC",20), gbc, 0, 0, 1);
        addComponent(welcome, createLabel("SELECCIONE UNA OPCIÓN DEL MENÚ",15), gbc, 0, 1, 1);
        return welcome;
    }

    private void addComponent(JPanel panel, Component comp, GridBagConstraints gbc, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(comp, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD,15));
        return label;
    }

    private JLabel createLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, size));
        return label;
    }

    private JTextField createTextField() {
        return new JTextField(10);
    }

    private JComboBox createComboBox(String[] items) {
        return new JComboBox(items);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        return button;
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