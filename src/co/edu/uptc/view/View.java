package co.edu.uptc.view;

import javax.smartcardio.Card;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import co.edu.uptc.presenter.Presenter;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class View extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private CardLayout adminCardLayout;
    private CardLayout recepcionistCardLayout;
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
    private Presenter presenter;

    private HashMap<String, JButton> buttonsMap;
    private HashMap<String, JTextField> textFieldsMap;
    private JComboBox<String> comboBox;
    private String userType;

    public View() {
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        presenter = new Presenter();
        buttonsMap = new HashMap<>();
        textFieldsMap = new HashMap<>();
        getContentPane().add(adminPanel(), "AdminPanel");

        getContentPane().add(userType(), "UserTypePanel");
        getContentPane().add(loginPanel(), "LoginPanel");
        getContentPane().add(adminPanel(), "AdminPanel");
        getContentPane().add(recepcionistPanel(), "RecepPanel");

        setVisible(true);
    }

    private JPanel userType() {
        JPanel userType = new JPanel(new GridBagLayout());
        userType.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 60, 10, 10);
        addComponent(userType, createLabel("Seleccione su tipo de usuario", 20), gbc, 0, 0, 2);
        JTextArea admin = new JTextArea(10, 15);
        admin.setText("Aca va el recepcionista");
        JTextArea recep = new JTextArea(10, 15);
        recep.setText("Aca va el administrador");
        addComponent(userType, admin, gbc, 0, 1, 1);
        addComponent(userType, recep, gbc, 1, 1, 1);
        addComponent(userType, createButton("Recepcionista"), gbc, 0, 2, 1);
        addComponent(userType, createButton("Administrador"), gbc, 1, 2, 1);
        return userType;
    }

    private JPanel loginPanel() {
        JPanel login = new JPanel(new GridBagLayout());
        login.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(login, createLabel("Usuario"), gbc, 0, 0, 1);
        addComponent(login, createTextField("LoginUser"), gbc, 1, 0, 1);
        addComponent(login, createLabel("Contraseña"), gbc, 0, 1, 1);
        addComponent(login, createTextField("LoginPassword"), gbc, 1, 1, 1);
        addComponent(login, createButton("Ingresar"), gbc, 0, 2, 2);
        return login;
    }

    public JPanel adminPanel() {
        // TODO layout here to organization
        JPanel adminPanel = new JPanel(new GridLayout(1, 2));
        adminLeftPanel = new JPanel();
        adminLeftPanel.setLayout(new BoxLayout(adminLeftPanel, BoxLayout.Y_AXIS));
        adminLeftPanel.setPreferredSize(new Dimension(150, getHeight()));

        createRecepcionist = new JButton("Create Recepcionist");
        updateRecepcionist = new JButton("Update Recepcionist");
        salesReport = new JButton("Sales Report");
        logout = new JButton("Logout");
        adminLeftPanel.add(Box.createVerticalStrut(20));
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(createRecepcionist, "Create Recepcionist");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(updateRecepcionist, "Update Recepcionist");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(salesReport, "Sales Report");
        adminLeftPanel.add(Box.createVerticalStrut(10));
        adminLeftPanel.add(logout, "Logout");
        adminCardLayout = new CardLayout();
        adminRightPanel = new JPanel(adminCardLayout);

        JPanel welcome = welcome();
        JPanel createRecepcionistPanel = createRecepcionist();
        JPanel updateRecepcionistPanel = updateRecepcionist();
        JPanel salesReportPanel = salesReport();
        JPanel logoutPanel = logout();

        adminRightPanel.add(welcome, "Welcome");
        adminRightPanel.add(createRecepcionistPanel, "Create Recepcionist");
        adminRightPanel.add(updateRecepcionistPanel, "Update Recepcionist");
        adminRightPanel.add(salesReportPanel, "Sales Report");
        adminRightPanel.add(logoutPanel, "Logout");
        createRecepcionist.addActionListener(this);
        updateRecepcionist.addActionListener(this);
        salesReport.addActionListener(this);
        logout.addActionListener(this);

        adminPanel.add(adminLeftPanel);
        adminPanel.add(adminRightPanel);
        return adminPanel;
    }

    private JPanel createRecepcionist() {
        JPanel recepcionistPanel = new JPanel(new GridLayout(1, 2));
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
        addComponent(recepcionistPanel, createTextField("CreateDocumento"), gbc, 1, 1, 1);
        addComponent(recepcionistPanel, createTextField("CreateNombres"), gbc, 1, 2, 1);
        addComponent(recepcionistPanel, createTextField("CreateApellidos"), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField("CreateDireccion"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField("CreateTelefono"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField("CreateEmail"), gbc, 1, 6, 1);
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
        addComponent(recepcionistPanel, createTextField("UpdateDocumento"), gbc, 1, 1, 1);
        // añadir el field del namobre

        addComponent(recepcionistPanel, createTextField("UpdateDireccion"), gbc, 1, 2, 1);
        addComponent(recepcionistPanel, createTextField("UpdateTelefono"), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField("UpdateEmail"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField("UpdateNuevaContraseña"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField("UpdateConfirmarContraseña"), gbc, 1, 6, 1);
        gbc.insets = new Insets(0, 0, 0, 0);
        addComponent(recepcionistPanel, createLabel("- La nueva contraseña no debe ser repetida"), gbc, 0, 7, 2);
        addComponent(recepcionistPanel, createLabel("- No tener caracteres especiales"), gbc, 0, 8, 2);
        addComponent(recepcionistPanel, createLabel("- Debe tener mínimo 8 dígitos"), gbc, 0, 9, 2);
        gbc.insets = new Insets(10, 0, 0, 0);
        addComponent(recepcionistPanel, createButton("Actualizar"), gbc, 0, 10, 2);
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

    // TODO parametros??
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
        return ticketPanel;
    }

    // TODO Panel vacio, va el selector de fechas de Santi
    private JPanel generateReportPanel() {
        JPanel generateReport = new JPanel();
        return generateReport;
    }

    public JPanel recepcionistPanel() {
        // TODO layout here to organization
        JPanel recepPanel = new JPanel(new GridLayout(1, 2));
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
        recepcionistCardLayout = new CardLayout();
        recepRightPanel = new JPanel(recepcionistCardLayout);
        JPanel welcome = welcome();
        JPanel availableSpacesPanel = availableSpacesPanel();
        JPanel registerVehiclePanel = registerVehiclePanel();
        JPanel registerVehiclePanel2 = ticketPanel();
        JPanel exitVehiclePanel = exitVehiclePanel();
        JPanel recepLogOutPanel = recepLogOutPanel();
        recepRightPanel.add(welcome, "Welcome");
        recepRightPanel.add(availableSpacesPanel, "Available Spaces");
        recepRightPanel.add(registerVehiclePanel, "Register Vehicle");
        recepRightPanel.add(exitVehiclePanel, "Exit Vehicle");
        recepRightPanel.add(recepLogOutPanel, "Log Out");
        recepRightPanel.add(registerVehiclePanel2, "TicketPanel");
        availableSpaces.addActionListener(this);
        registerVehicle.addActionListener(this);
        exitVehicle.addActionListener(this);
        recepLogOut.addActionListener(this);

        recepPanel.add(recepLeftPanel);
        recepPanel.add(recepRightPanel);
        return recepPanel;
    }

    private JPanel availableSpacesPanel() {
        // modificar espacios por defecto
        JPanel availableSpacesPanel = new JPanel(new GridBagLayout());
        availableSpacesPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(availableSpacesPanel, createLabel("Disponibilidad"), gbc, 0, 0, 2);
        addComponent(availableSpacesPanel, createLabel("Hay 5 espacios disponibles", 25), gbc, 0, 1, 2);
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
        comboBox = new JComboBox<>(new String[] { "Moto", "Carro" });
        addComponent(incomePanel, createLabel("Ingrese los datos del vehiculo:"), gbc, 0, 0, 2);
        addComponent(incomePanel, createLabel("Placa"), gbc, 0, 1, 1);
        addComponent(incomePanel, createTextField("PlacaRegisterVehicle"), gbc, 1, 1, 1);
        addComponent(incomePanel, createLabel("Tipo"), gbc, 0, 2, 1);
        addComponent(incomePanel, comboBox, gbc, 1, 2, 1);
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
        addComponent(ticketOutPanel, createTextField("PlacaExitVehicle"), gbc, 1, 1, 1);
        addComponent(ticketOutPanel, createLabel("Ingrese el dinero para generar el recibo"), gbc, 0, 2, 2);
        addComponent(ticketOutPanel, createLabel("Dinero"), gbc, 0, 3, 1);
        addComponent(ticketOutPanel, createTextField("DineroExitVehicle"), gbc, 1, 3, 1);
        addComponent(ticketOutPanel, createLabel("Cambio"), gbc, 0, 4, 1);
        addComponent(ticketOutPanel, createTextField("CambioExitVehicle"), gbc, 1, 4, 1);
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
        addComponent(welcome, createLabel("BIENVENIDO A PARKING UPTC", 20), gbc, 0, 0, 1);
        addComponent(welcome, createLabel("SELECCIONE UNA OPCIÓN DEL MENÚ", 15), gbc, 0, 1, 1);
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
        label.setFont(new Font("Arial", Font.BOLD, 15));
        return label;
    }

    private JLabel createLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, size));
        return label;
    }

    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(10);
        textFieldsMap.put(text, textField);
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        buttonsMap.put(text, button);
        return button;
    }

    // TODO passwords
    private void readCreateRecepcionist() {
        presenter.createRecepcionist(
                textFieldsMap.get("CreateNombres").getText(),
                textFieldsMap.get("CreateApellidos").getText(),
                textFieldsMap.get("CreateEmail").getText(),
                textFieldsMap.get("CreateTelefono").getText(),
                textFieldsMap.get("CreateDireccion").getText(),
                textFieldsMap.get("CreateDocumento").getText());
    }

    private void readUpdateRecepcionist() {
        presenter.updateRecepcionist(textFieldsMap.get("UpdateEmail").getText(),
                textFieldsMap.get("UpdateTelefono").getText(),
                textFieldsMap.get("UpdateDireccion").getText(),
                textFieldsMap.get("UpdateDocumento").getText(),
                textFieldsMap.get("UpdateNuevaContraseña").getText(),
                textFieldsMap.get("UpdateConfirmarContraseña").getText());
    }

    private void readRegisterVehicle() {
        presenter.registerVehicle(textFieldsMap.get("PlacaRegisterVehicle").getText(),
                comboBox.getSelectedItem().toString());
        // pedir el ticket generado
        cardLayout.show(recepRightPanel, "TicketPanel");

    }

    private void readExitVehicle() {
        presenter.exitVehicle(textFieldsMap.get("PlacaExitVehicle").getText());
        // dinero???
        // textFieldsMap

    }

    private boolean readLogin() {
        String id = textFieldsMap.get("LoginUser").getText();
        String password = textFieldsMap.get("LoginPassword").getText();
        return presenter.logIn(id, password, userType);
    }

    private void checkLogIn(String usertype) {
        // En este ejemplo se almacena el tipo de usuario, luego se usa en readLogin()
        userType = usertype;
    }

    public void optionPanel(String message, String tittle, int icon, String buttonText) {
        Object[] opciones = { buttonText };
        JOptionPane.showOptionDialog(null, message, tittle, JOptionPane.DEFAULT_OPTION, icon, null, opciones,
                opciones[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonsMap.get("Recepcionista")) {
            userType = "Recepcionista";
            checkLogIn(userType);
            // llamado al metodo de comprobar login
            cardLayout.show(getContentPane(), "LoginPanel");
        }
        if (e.getSource() == buttonsMap.get("Administrador")) {
            userType = "Administrador";
            checkLogIn(userType);
            // llamado al metodo de comprobar login
            cardLayout.show(getContentPane(), "LoginPanel");
        }
        if (e.getSource() == buttonsMap.get("Ingresar")) {
            if (userType == "Recepcionista" && readLogin())
                cardLayout.show(getContentPane(), "RecepPanel");
            else if (userType == "Administrador" && readLogin())
                cardLayout.show(getContentPane(), "AdminPanel");
        }
        if (e.getSource() == buttonsMap.get("Ingresar")) {
            // Se valida el login y se redirige según el resultado
            if (userType.equals("Recepcionista") && readLogin())
                cardLayout.show(getContentPane(), "RecepPanel");
            else if (userType.equals("Administrador") && readLogin())
                cardLayout.show(getContentPane(), "AdminPanel");
            else
                JOptionPane.showMessageDialog(this, "Error: Usuario o contraseña incorrectos", "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == createRecepcionist)
            adminCardLayout.show(adminRightPanel, "Create Recepcionist");
        else if (e.getSource() == updateRecepcionist)
            adminCardLayout.show(adminRightPanel, "Update Recepcionist");
        else if (e.getSource() == salesReport)
            adminCardLayout.show(adminRightPanel, "Sales Report");
        else if (e.getSource() == logout)
            adminCardLayout.show(adminRightPanel, "Logout");
        else if (e.getSource() == availableSpaces)
            recepcionistCardLayout.show(recepRightPanel, "Available Spaces");
        else if (e.getSource() == registerVehicle)
            recepcionistCardLayout.show(recepRightPanel, "Register Vehicle");
        else if (e.getSource() == exitVehicle)
            recepcionistCardLayout.show(recepRightPanel, "Exit Vehicle");
        else if (e.getSource() == recepLogOut)
            recepcionistCardLayout.show(recepRightPanel, "Log Out");
        // mostrar el user type??
        else {
            if (e.getSource() == buttonsMap.get("Crear"))
                readCreateRecepcionist();
            else if (e.getSource() == buttonsMap.get("Actualizar"))
                readUpdateRecepcionist();
            else if (e.getSource() == buttonsMap.get("No")) {
                cardLayout.show(adminRightPanel, "Welcome");
            } else if (e.getSource() == buttonsMap.get("Si")) {
                // show the loginPanel();
            } else if (e.getSource() == buttonsMap.get("Imprimir recibo")) {
                // imprimir recibo
            } else if (e.getSource() == buttonsMap.get("Siguiente"))
                readRegisterVehicle();
            else if (e.getSource() == buttonsMap.get("Registrar salida"))
                readExitVehicle();
            // si no de recepcionista
        }
    }
}