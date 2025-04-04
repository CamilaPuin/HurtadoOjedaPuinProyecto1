package co.edu.uptc.view;

import co.edu.uptc.presenter.Presenter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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

        add(availableSpacesPanel());
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

        ImageIcon imageAdmin = new ImageIcon(getClass().getResource("/resources/administrador.png"));
        ImageIcon imageRecep = new ImageIcon(getClass().getResource("/resources/recepcionista.png"));

        JLabel admin = new JLabel(imageAdmin);
        JLabel recep = new JLabel(imageRecep);

        addComponent(userType, recep, gbc, 0, 1, 1);
        addComponent(userType, admin, gbc, 1, 1, 1);

        addComponent(userType, createButton("Recepcionista", "RecepcionistaUserType"), gbc, 0, 2, 1);
        addComponent(userType, createButton("Administrador", "AdministradorUserType"), gbc, 1, 2, 1);

        return userType;
    }

    private JPanel loginPanel() {
        JPanel login = new JPanel(new GridBagLayout());
        login.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(login, createLabel("Usuario"), gbc, 0, 0, 1);
        addComponent(login, createLabel("Contraseña"), gbc, 0, 1, 1);
        addComponent(login, createTextField("LoginUser"), gbc, 1, 0, 1);
        addComponent(login, createPasswordField("LoginPassword"), gbc, 1, 1, 1);
        addComponent(login, createButton("Ingresar", "IngresarLoginPanel"), gbc, 0, 2, 2);
        addComponent(login, createButton("Regresar", "RegresarLoginPanel"), gbc, 0, 3, 2);
        return login;
    }

    public JPanel adminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(1, 2));
        adminLeftPanel = new JPanel();

        adminLeftPanel.setLayout(new BoxLayout(adminLeftPanel, BoxLayout.Y_AXIS));
        adminLeftPanel.setPreferredSize(new Dimension(150, getHeight()));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        createRecepcionist = new JButton("Create Recepcionist");
        updateRecepcionist = new JButton("Update Recepcionist");
        salesReport = new JButton("Sales Report");
        logout = new JButton("Logout");
        ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(CENTER_ALIGNMENT);
        createRecepcionist.setAlignmentX(CENTER_ALIGNMENT);
        updateRecepcionist.setAlignmentX(CENTER_ALIGNMENT);
        salesReport.setAlignmentX(CENTER_ALIGNMENT);
        logout.setAlignmentX(CENTER_ALIGNMENT);

        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(createRecepcionist);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(updateRecepcionist);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(salesReport);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(logout);

        adminLeftPanel.add(centerPanel, BorderLayout.CENTER);
        adminCardLayout = new CardLayout();
        adminRightPanel = new JPanel(adminCardLayout);

        JPanel welcome = welcome();
        JPanel createRecepcionistPanel = createRecepcionist();
        JPanel updateRecepcionistPanel = updateRecepcionist();
        JPanel salesReportPanel = salesReport();
        JPanel logoutPanel = logoutAdmin();

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
        addComponent(recepcionistPanel, createTextField("CreateDocumento"), gbc, 1, 1, 1);
        addComponent(recepcionistPanel, createTextField("CreateNombres"), gbc, 1, 2, 1);
        addComponent(recepcionistPanel, createTextField("CreateApellidos"), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField("CreateDireccion"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField("CreateTelefono"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField("CreateEmail"), gbc, 1, 6, 1);
        addComponent(recepcionistPanel, createButton("Crear", "crearCreateRecepcionist"), gbc, 0, 7, 2);
        return recepcionistPanel;
    }

    private JPanel updateRecepcionist() {
        JPanel recepcionistPanel = new JPanel(new GridBagLayout());
        recepcionistPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(recepcionistPanel, createLabel("Digite los datos para actualizar el recepcionista"), gbc, 0, 0, 2);
        addComponent(recepcionistPanel, createLabel("Documento"), gbc, 0, 1, 1);
        addComponent(recepcionistPanel, createTextField("UpdateDocumento"), gbc, 1, 1, 1);
        addComponent(recepcionistPanel, createButton("Buscar", "buscarUpdateRecepcionist"), gbc, 0, 2, 2);
        addComponent(recepcionistPanel, createLabel("Name"), gbc, 0, 3, 1);
        addComponent(recepcionistPanel, createTextField("NameFounded"), gbc, 1, 3, 1);
        textFieldsMap.get("NameFounded").setEnabled(false);
        addComponent(recepcionistPanel, createLabel("Dirección"), gbc, 0, 4, 1);
        addComponent(recepcionistPanel, createLabel("Teléfono"), gbc, 0, 5, 1);
        addComponent(recepcionistPanel, createLabel("Email"), gbc, 0, 6, 1);
        addComponent(recepcionistPanel, createLabel("Nueva contraseña"), gbc, 0, 7, 1);
        addComponent(recepcionistPanel, createLabel("Confirmar contraseña"), gbc, 0, 8, 1);
        addComponent(recepcionistPanel, createTextField("UpdateDireccion"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField("UpdateTelefono"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField("UpdateEmail"), gbc, 1, 6, 1);
        addComponent(recepcionistPanel, createTextField("UpdateNuevaContraseña"), gbc, 1, 7, 1);
        addComponent(recepcionistPanel, createTextField("UpdateConfirmarContraseña"), gbc, 1, 8, 1);
        gbc.insets = new Insets(0, 0, 0, 0);
        addComponent(recepcionistPanel, createLabel("- La nueva contraseña no debe ser repetida"), gbc, 0, 9, 2);
        addComponent(recepcionistPanel, createLabel("- No tener caracteres especiales"), gbc, 0, 10, 2);
        addComponent(recepcionistPanel, createLabel("- Debe tener mínimo 8 dígitos"), gbc, 0, 11, 2);
        gbc.insets = new Insets(10, 0, 0, 0);
        addComponent(recepcionistPanel, createButton("Actualizar", "actualizarUpdateRecepcionist"), gbc, 0, 12, 2);
        return recepcionistPanel;
    }

    private String getFullName(String id) {
        String fullName = presenter.getFullName(id);
        if (fullName.equals(""))
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        return fullName;
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
        addComponent(report, createButton("Regresar al menú", "RegresarSalesReport"), gbc, 0, 4, 2);
        return report;
    }

    private JPanel logoutAdmin() {
        JPanel logOutPanel = new JPanel(new GridBagLayout());
        logOutPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(logOutPanel, createLabel("¿Desea cerrar sesión?"), gbc, 0, 0, 2);
        addComponent(logOutPanel, createButton("Si", "SiLogOutAdmin"), gbc, 0, 1, 1);
        addComponent(logOutPanel, createButton("No", "NoLogOutAdmin"), gbc, 1, 1, 1);
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
        addComponent(ticketPanel, createButton("Imprimir recibo", "ImprimirReciboTicketPanel"), gbc, 0, 2, 1);
        addComponent(ticketPanel, createButton("Regresar", "MenuTicketPanel"), gbc, 0, 4, 1);
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

        JPanel recepPanel = new JPanel(new GridLayout(1, 2));

        recepLeftPanel = new JPanel(new BorderLayout());
        recepLeftPanel.setPreferredSize(new Dimension(150, getHeight()));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        availableSpaces = new JButton("Available Spaces");
        registerVehicle = new JButton("Register Vehicle");
        exitVehicle = new JButton("Exit Vehicle");
        recepLogOut = new JButton("Log Out");
        ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(CENTER_ALIGNMENT);
        availableSpaces.setAlignmentX(CENTER_ALIGNMENT);
        registerVehicle.setAlignmentX(CENTER_ALIGNMENT);
        exitVehicle.setAlignmentX(CENTER_ALIGNMENT);
        recepLogOut.setAlignmentX(CENTER_ALIGNMENT);

        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(availableSpaces);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(registerVehicle);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(exitVehicle);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(recepLogOut);

        recepLeftPanel.add(centerPanel, BorderLayout.CENTER);
        recepcionistCardLayout = new CardLayout();
        recepRightPanel = new JPanel(recepcionistCardLayout);

        JPanel welcome = welcome();
        JPanel availableSpacesPanel = availableSpacesPanel();
        JPanel registerVehiclePanel = registerVehiclePanel();
        JPanel registerVehiclePanel2 = ticketPanel();
        JPanel exitVehiclePanel = exitVehiclePanel();
        JPanel recepLogOutPanel = logOutRecep();
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

    private static boolean alertaMostrada = false;

    private JPanel availableSpacesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        String[] lines = presenter.availableSpaces().split("\n");
        addComponent(panel, createLabel("Disponibilidad"), gbc, 0, 0, 2);
        IntStream.range(0, lines.length)
                .forEach(i -> addComponent(panel, createLabel(lines[i], i == 0 ? 25 : 12), gbc, 1, i + 1, 1));
        addComponent(panel, createButton("Salir", "SalirAvailableSpaces"), gbc, 0, 4, 2);
        if (!alertaMostrada) {
            String[] etiquetas = { "Total", "Motos", "Carros" };
            String alerta = IntStream.range(0, lines.length)
                    .mapToObj(i -> etiquetas[i] + ": " + lines[i].replaceAll("\\D+", ""))
                    .filter(text -> Integer.parseInt(text.replaceAll("\\D+", "")) <= 5)
                    .collect(Collectors.joining("\n"));
            if (!alerta.isEmpty()) {
                optionPanel("¡Alerta! Espacios limitados.\n\n" + alerta, "Advertencia", JOptionPane.WARNING_MESSAGE,
                        "Entendido");
                alertaMostrada = true;
            }
        }
        return panel;
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
        addComponent(incomePanel, createButton("Siguiente", "SiguienteRegisterVehicle"), gbc, 0, 3, 2);
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
        addComponent(ticketOutPanel, createButton("Registrar salida", "RegistrarSalidaExitVehicle"), gbc, 0, 7, 2);
        return ticketOutPanel;
    }

    private JPanel logOutRecep() {
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
        addComponent(consolidadoPanel, createButton("Si", "SiRecepLogOut"), gbc, 0, 4, 1);
        addComponent(consolidadoPanel, createButton("No", "NoRecepLogOut"), gbc, 1, 4, 1);
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

    private JPasswordField createPasswordField(String text) {
        JPasswordField textField = new JPasswordField(10);
        textFieldsMap.put(text, textField);
        return textField;
    }

    private JButton createButton(String text, String name) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        buttonsMap.put(name, button);
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
        recepcionistCardLayout.show(recepRightPanel, "TicketPanel");

    }

    private void readExitVehicle() {
        presenter.exitVehicle(textFieldsMap.get("PlacaExitVehicle").getText());
        // dinero???
        // textFieldsMap

    }

    private boolean readLogin() {
        String id = textFieldsMap.get("LoginUser").getText();
        String password = textFieldsMap.get("LoginPassword").getText();
        textFieldsMap.get("LoginUser").setText("");
        textFieldsMap.get("LoginPassword").setText("");
        return presenter.logIn(id, password, userType);
    }

    public void optionPanel(String message, String tittle, int icon, String buttonText) {
        Object[] opciones = { buttonText };
        JOptionPane.showOptionDialog(null, message, tittle, JOptionPane.DEFAULT_OPTION, icon, null, opciones,
                opciones[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonsMap.get("RecepcionistaUserType")) {
            userType = "Recepcionista";
            // llamado al metodo de comprobar login
            cardLayout.show(getContentPane(), "LoginPanel");
        }
        if (e.getSource() == buttonsMap.get("AdministradorUserType")) {
            userType = "Administrador";
            // llamado al metodo de comprobar login
            cardLayout.show(getContentPane(), "LoginPanel");
        }
        if (e.getSource() == buttonsMap.get("IngresarLoginPanel")) {
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
        else if (e.getSource() == buttonsMap.get("crearCreateRecepcionistr"))
            readCreateRecepcionist();
        else if (e.getSource() == buttonsMap.get("actualizarUpdateRecepcionist"))
            readUpdateRecepcionist();
        else if (e.getSource() == buttonsMap.get("NoLogOutAdmin")) {
            cardLayout.show(adminRightPanel, "Welcome");
        } else if (e.getSource() == buttonsMap.get("SiLogOutAdmin")) {
            cardLayout.show(getContentPane(), "UserTypePanel");
        } else if (e.getSource() == buttonsMap.get("ImprimirReciboTicketPanel")) {
            // imprimir recibo
        } else if (e.getSource() == buttonsMap.get("SiguienteRegisterVehicle"))
            readRegisterVehicle();
        else if (e.getSource() == buttonsMap.get("RegistrarSalidaExitVehicle"))
            readExitVehicle();
        else if (e.getSource() == buttonsMap.get("SalirAvailableSpaces"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");
        else if (e.getSource() == buttonsMap.get("SiRecepLogOut"))
            cardLayout.show(getContentPane(), "UserTypePanel");
        else if (e.getSource() == buttonsMap.get("MenuTicketPanel"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");
        else if (e.getSource() == buttonsMap.get("NoRecepLogOut"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");

        // Lógica para el botón "Buscar" en actualizar recepcionista
        else if (e.getSource() == buttonsMap.get("buscarRecepcionist")) {
            // Obtener el ID del campo de texto
            String documento = textFieldsMap.get("UpdateDocumento").getText();
            // Buscar el nombre completo
            String nombre = getFullName(documento);
            // Mostrar el resultado en el campo NameFounded
            textFieldsMap.get("NameFounded").setText(nombre);
        }

        // si no de recepcionista
        // falta RegresarSalesReport de admin
        // volver al incio en ticket panel
    }

}