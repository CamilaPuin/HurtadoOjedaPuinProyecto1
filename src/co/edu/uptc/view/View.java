package co.edu.uptc.view;

import co.edu.uptc.presenter.Presenter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import raven.datetime.component.date.DatePicker;

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
    private DatePicker datePicker;
    private JPanel ticketOutPanel;
    private String recepEntryTime;
    private String recepExitTime;
    private JPanel ticketPanel;
    private DefaultTableModel tableModel;
    private JTable ticketRegisterTable;

    public View() {
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        presenter = new Presenter();
        buttonsMap = new HashMap<>();
        textFieldsMap = new HashMap<>();
        // datePicker = new DatePicker();
        getContentPane().add(loginPanel(), "LoginPanel");

        setVisible(true);
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
        return login;
    }

    public JPanel adminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(1, 2));
        adminLeftPanel = new JPanel();
        adminLeftPanel.setLayout(new BoxLayout(adminLeftPanel, BoxLayout.Y_AXIS));
        adminLeftPanel.setPreferredSize(new Dimension(150, getHeight()));
        adminLeftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));

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

        gbc.insets = new Insets(5, 0, 15, 0);
        addComponent(recepcionistPanel, createButton("Buscar", "buscarRecepcionist"), gbc, 0, 2, 2);
        gbc.insets = new Insets(10, 10, 10, 10);

        addComponent(recepcionistPanel, createLabel("Nombre"), gbc, 0, 3, 1);
        addComponent(recepcionistPanel, createLabel("Direccion"), gbc, 0, 4, 1);
        addComponent(recepcionistPanel, createLabel("Telefono"), gbc, 0, 5, 1);
        addComponent(recepcionistPanel, createLabel("Email"), gbc, 0, 6, 1);
        addComponent(recepcionistPanel, createLabel("Nueva contraseña"), gbc, 0, 7, 1);
        addComponent(recepcionistPanel, createLabel("Confirmar contraseña"), gbc, 0, 8, 1);
        addComponent(recepcionistPanel, createTextField("UpdateName"), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField("UpdateDireccion"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField("UpdateTelefono"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField("UpdateEmail"), gbc, 1, 6, 1);
        addComponent(recepcionistPanel, createPasswordField("UpdateNewPassword"), gbc, 1, 7, 1);
        addComponent(recepcionistPanel, createPasswordField("UpdateConfirmPassword"), gbc, 1, 8, 1);

        gbc.insets = new Insets(0, 0, 0, 0);
        addComponent(recepcionistPanel, createLabel("- La nueva contraseña no debe ser repetida"), gbc, 0, 9, 2);
        addComponent(recepcionistPanel, createLabel("- No tener caracteres especiales"), gbc, 0, 10, 2);
        addComponent(recepcionistPanel, createLabel("- Debe tener mínimo 8 dígitos"), gbc, 0, 11, 2);

        gbc.insets = new Insets(10, 0, 0, 0);
        addComponent(recepcionistPanel, createButton("Actualizar", "actualizarUpdateRecepcionist"), gbc, 0, 12, 2);

        return recepcionistPanel;
    }

    private void setRecepcionistInfo(String[] recepcionistInfo) {
        textFieldsMap.get("UpdateName").setText(recepcionistInfo[0]);
        textFieldsMap.get("UpdateDireccion").setText(recepcionistInfo[1]);
        textFieldsMap.get("UpdateTelefono").setText(recepcionistInfo[2]);
        textFieldsMap.get("UpdateEmail").setText(recepcionistInfo[3]);
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
        String[] col = { "Nombre", "Apellidos", "Ingreso", "Vehiculos" };

        JTable table = new JTable(presenter.getConsolidatedRecepcionists(), col);
        JScrollPane scrollPane = new JScrollPane(table);

        addComponent(report, scrollPane, gbc, 0, 3, 2);
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

    private JPanel ticketPanel(String plate) {
        ticketPanel = new JPanel(new GridBagLayout());
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

        String[] col = { "Placa", "Fecha", "Hora ingreso", "Tipo de vehiculo" };
        tableModel = new DefaultTableModel(col, 0);
        ticketRegisterTable = new JTable(tableModel);
        updateTable(plate);

        JScrollPane scrollPane = new JScrollPane(ticketRegisterTable);
        scrollPane.setPreferredSize(new Dimension(10, 20));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(1, 1, 1, 1);
        addComponent(ticketPanel, scrollPane, gbc, 0, 3, 1);

        return ticketPanel;
    }

    private void updateTable(String plate) {
        tableModel.setRowCount(0);
        Object[] data = {
                plate,
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
                comboBox.getSelectedItem().toString()
        };
        tableModel.addRow(data);
    }

    private JPanel generateReportPanel() {
        JPanel generateReport = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // datePicker.setColor(Color.BLUE);
        // addComponent(generateReport, datePicker, gbc, 0, 0, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        addComponent(generateReport, createButton("Confirmar", "ConfirmarDateSales"), gbc, 0, 1, 1);
        return generateReport;
    }

    public JPanel recepcionistPanel() {
        JPanel recepPanel = new JPanel(new GridLayout(1, 2));
        recepLeftPanel = new JPanel(new BorderLayout());
        recepLeftPanel.setPreferredSize(new Dimension(150, getHeight()));
        recepLeftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));

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
        ticketPanel = ticketPanel("");
        JPanel exitVehiclePanel = exitVehiclePanel();
        JPanel recepLogOutPanel = logOutRecep();
        recepRightPanel.add(welcome, "Welcome");
        recepRightPanel.add(availableSpacesPanel, "Available Spaces");
        recepRightPanel.add(registerVehiclePanel, "Register Vehicle");
        recepRightPanel.add(exitVehiclePanel, "Exit Vehicle");
        recepRightPanel.add(recepLogOutPanel, "Log Out");
        recepRightPanel.add(ticketPanel, "TicketPanel");
        availableSpaces.addActionListener(this);
        registerVehicle.addActionListener(this);
        exitVehicle.addActionListener(this);
        recepLogOut.addActionListener(this);
        recepPanel.add(recepLeftPanel);
        recepPanel.add(recepRightPanel);
        return recepPanel;
    }

    private JPanel availableSpacesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] lines = presenter.availableSpaces().split("\n");
        if (!lines[0].equals("Hay -1 espacios disponibles")) {
            addComponent(panel, createLabel("Disponibilidad"), gbc, 0, 0, 2);
            IntStream.range(0, lines.length)
                    .forEach(i -> addComponent(panel, createLabel(lines[i], i == 0 ? 25 : 12),
                            gbc, 1, i + 1, 1));
            addComponent(panel, createButton("Salir", "SalirAvailableSpaces"), gbc, 0, 4,
                    2);
            boolean alertaMostrada = false;
            if (!alertaMostrada) {
                String[] etiquetas = { "Total", "Motos", "Carros" };
                String alerta = IntStream.range(0, lines.length)
                        .mapToObj(i -> etiquetas[i] + ": " + lines[i].replaceAll("\\D+", ""))
                        .filter(text -> Integer.parseInt(text.replaceAll("\\D+", "")) <= 5)
                        .collect(Collectors.joining("\n"));
                if (!alerta.isEmpty()) {
                    optionPanel("¡Alerta! Espacios limitados.\n\n" + alerta, "Advertencia",
                            JOptionPane.WARNING_MESSAGE,
                            "Entendido");
                    alertaMostrada = true;
                }
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
        ticketOutPanel = new JPanel(new GridBagLayout());
        ticketOutPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addComponent(ticketOutPanel, createLabel("Ingrese la placa del vehiculo"), gbc, 0, 0, 2);
        addComponent(ticketOutPanel, createLabel("Placa"), gbc, 0, 1, 1);
        addComponent(ticketOutPanel, createTextField("PlacaExitVehicle"), gbc, 1, 1, 1);
        addComponent(ticketOutPanel, createLabel("Ingrese el dinero para generar el recibo"), gbc, 0, 2, 2);
        addComponent(ticketOutPanel, createLabel("Dinero"), gbc, 0, 3, 1);
        addComponent(ticketOutPanel, createTextField("DineroExitVehicle"), gbc, 1, 3, 1);
        addComponent(ticketOutPanel, createLabel("Recibo"), gbc, 0, 5, 2);

        JButton generarReciboButton = createButton("Generar recibo y registar salida", "GenerarReciboExitVehicle");
        addComponent(ticketOutPanel, generarReciboButton, gbc, 0, 6, 2);

        JButton registerButton = createButton("Salir", "RegistrarSalidaExitVehicle");
        addComponent(ticketOutPanel, registerButton, gbc, 0, 8, 2);
        return ticketOutPanel;
    }

    private JPanel logOutRecep() {
        JPanel consolidadoPanel = new JPanel(new GridBagLayout());
        consolidadoPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(consolidadoPanel, createLabel("Consolidado"), gbc, 0, 0, 2);
        String[] colOne = { "Nombre", "Apellidos", "Hora de entrada" };
        Object[] dataOne = {
                presenter.obtainRecepcionistData()[0],
                presenter.obtainRecepcionistData()[1],
                recepEntryTime
        };
        JTable tableOne = new JTable(new Object[][] { dataOne }, colOne);
        JScrollPane scrollPaneOne = new JScrollPane(tableOne);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(consolidadoPanel, scrollPaneOne, gbc, 0, 1, 2);
        String[] colTwo = { "Hora salida", "Total ingresos", "Total vehiculos ingresados" };
        Object[] dataTwo = {
                recepExitTime,
                presenter.income(),
                presenter.numAttendedVehicles()
        };
        JTable tableTwo = new JTable(new Object[][] { dataTwo }, colTwo);
        JScrollPane scrollPaneTwo = new JScrollPane(tableTwo);

        addComponent(consolidadoPanel, scrollPaneTwo, gbc, 0, 2, 2);
        addComponent(consolidadoPanel, createLabel("¿Desea cerrar sesión?"), gbc, 0, 3, 2);
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 10;
        addComponent(consolidadoPanel, createButton("Si", "SiRecepLogOut"), gbc, 0, 4, 1);
        addComponent(consolidadoPanel, createButton("No", "NoRecepLogOut"), gbc, 1, 4, 1);

        consolidadoPanel.revalidate();
        consolidadoPanel.repaint();
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

    private void readCreateRecepcionist() {
        if (validEmail(textFieldsMap.get("CreateEmail").getText())) {
            presenter.createRecepcionist(
                    textFieldsMap.get("CreateNombres").getText(),
                    textFieldsMap.get("CreateApellidos").getText(),
                    textFieldsMap.get("CreateEmail").getText(),
                    textFieldsMap.get("CreateTelefono").getText(),
                    textFieldsMap.get("CreateDireccion").getText(),
                    textFieldsMap.get("CreateDocumento").getText());
            JOptionPane.showMessageDialog(this, "Recepcionista registrado con éxito", "Recepcionista registrado",
                    JOptionPane.INFORMATION_MESSAGE);
            textFieldsMap.get("CreateNombres").setText("");
            textFieldsMap.get("CreateApellidos").setText("");
            textFieldsMap.get("CreateEmail").setText("");
            textFieldsMap.get("CreateTelefono").setText("");
            textFieldsMap.get("CreateDireccion").setText("");
            textFieldsMap.get("CreateDocumento").setText("");
        } else
            JOptionPane.showMessageDialog(this, "Formato de correo electrónico inválido", "Email invalido",
                    JOptionPane.ERROR_MESSAGE);
    }

    private void readUpdateRecepcionist() {
        if (validEmail(textFieldsMap.get("UpdateEmail").getText())) {
            presenter.updateRecepcionist(textFieldsMap.get("UpdateEmail").getText(),
                    textFieldsMap.get("UpdateTelefono").getText(),
                    textFieldsMap.get("UpdateDireccion").getText(),
                    textFieldsMap.get("UpdateDocumento").getText(),
                    textFieldsMap.get("UpdateNuevaContraseña").getText(),
                    textFieldsMap.get("UpdateConfirmarContraseña").getText());
            textFieldsMap.get("UpdateNombres").setText("");
            textFieldsMap.get("UpdateApellidos").setText("");
            textFieldsMap.get("UpdateDireccion").setText("");
            textFieldsMap.get("UpdateTelefono").setText("");
            textFieldsMap.get("UpdateEmail").setText("");
            textFieldsMap.get("UpdateDocumento").setText("");
            textFieldsMap.get("UpdateNuevaContraseña").setText("");
            textFieldsMap.get("UpdateConfirmarContraseña").setText("");
            JOptionPane.showMessageDialog(this, "Información actualizada", "Información actualizada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(this, "Formato de correo electrónico inválido", "Email invalido",
                    JOptionPane.ERROR_MESSAGE);
    }

    private void readRegisterVehicle() {
        presenter.registerVehicle(textFieldsMap.get("PlacaRegisterVehicle").getText(),
                comboBox.getSelectedItem().toString());
        updateTable(textFieldsMap.get("PlacaRegisterVehicle").getText());
        recepcionistCardLayout.show(recepRightPanel, "TicketPanel");
        textFieldsMap.get("PlacaRegisterVehicle").setText("");
        comboBox.setSelectedIndex(0);
    }

    private void readExitVehicle() {
        presenter.exitVehicle(textFieldsMap.get("PlacaExitVehicle").getText());
    }

    private String readLogin() {
        String id = textFieldsMap.get("LoginUser").getText();
        String password = textFieldsMap.get("LoginPassword").getText();
        textFieldsMap.get("LoginUser").setText("");
        textFieldsMap.get("LoginPassword").setText("");
        return presenter.logIn(id, password);
    }

    public int optionPanel(String message, String tittle, int icon, String buttonText, String secondButton) {
        Object[] opciones = { buttonText, secondButton };
        return JOptionPane.showOptionDialog(null, message, tittle, JOptionPane.DEFAULT_OPTION, icon, null, opciones,
                opciones[0]);
    }

    public int optionPanel(String message, String tittle, int icon, String buttonText) {
        Object[] opciones = { buttonText };
        return JOptionPane.showOptionDialog(null, message, tittle, JOptionPane.DEFAULT_OPTION, icon, null, opciones,
                opciones[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonsMap.get("IngresarLoginPanel")) {
            String userRole = readLogin();
            if (userRole.equals("Recepcionista")) {
                entryTimeRecepcionist();
                getContentPane().add(recepcionistPanel(), "RecepPanel");
                cardLayout.show(getContentPane(), "RecepPanel");
            } else if (userRole.equals("Administrador")) {
                getContentPane().add(adminPanel(), "AdminPanel");
                cardLayout.show(getContentPane(), "AdminPanel");
            } else {
                JOptionPane.showMessageDialog(this, "Error: Usuario o contraseña incorrectos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == createRecepcionist)
            adminCardLayout.show(adminRightPanel, "Create Recepcionist");

        else if (e.getSource() == updateRecepcionist)
            adminCardLayout.show(adminRightPanel, "Update Recepcionist");

        else if (e.getSource() == salesReport)
            adminCardLayout.show(adminRightPanel, "Sales Report");

        else if (e.getSource() == logout)
            adminCardLayout.show(adminRightPanel, "Logout");

        else if (e.getSource() == availableSpaces) {
            Component[] components = recepRightPanel.getComponents();
            for (Component comp : components) {
                if (comp instanceof JPanel && !comp.isVisible()) {
                    JPanel availableSpacesPanel = (JPanel) comp;
                    availableSpacesPanel.revalidate();
                    availableSpacesPanel.repaint();
                    getContentPane().removeAll();
                    getContentPane().add(recepcionistPanel(), "RecepPanel");
                }
            }
            recepcionistCardLayout.show(recepRightPanel, "Available Spaces");
        }

        else if (e.getSource() == registerVehicle)
            recepcionistCardLayout.show(recepRightPanel, "Register Vehicle");

        else if (e.getSource() == exitVehicle)
            recepcionistCardLayout.show(recepRightPanel, "Exit Vehicle");

        else if (e.getSource() == recepLogOut) {
            exitTimeRecepcionist();
            JPanel nuevoPanelLogOut = logOutRecep();
            recepRightPanel.add(nuevoPanelLogOut, "Log Out");
            recepRightPanel.remove(recepRightPanel.getComponent(1));
            recepRightPanel.add(logOutRecep(), "Log Out");
            recepcionistCardLayout.show(recepRightPanel, "Log Out");
        }

        else if (e.getSource() == buttonsMap.get("crearCreateRecepcionist"))
            readCreateRecepcionist();

        else if (e.getSource() == buttonsMap.get("actualizarUpdateRecepcionist"))
            readUpdateRecepcionist();

        else if (e.getSource() == buttonsMap.get("NoLogOutAdmin")) {
            cardLayout.show(adminRightPanel, "Welcome");

        } else if (e.getSource() == buttonsMap.get("SiLogOutAdmin")) {
            cardLayout.show(getContentPane(), "LoginPanel");

        } else if (e.getSource() == buttonsMap.get("ImprimirReciboTicketPanel")) {
            JOptionPane.showMessageDialog(this, "Imprimiendo recibo", "Recibo", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == buttonsMap.get("SiguienteRegisterVehicle"))
            readRegisterVehicle();

        else if (e.getSource() == buttonsMap.get("RegistrarSalidaExitVehicle")) {
            recepcionistCardLayout.show(recepRightPanel, "Welcome");
            resetExitVehiclePanel();
            readExitVehicle();
        }

        else if (e.getSource() == buttonsMap.get("SalirAvailableSpaces"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");

        else if (e.getSource() == buttonsMap.get("SiRecepLogOut")) {
            cardLayout.show(getContentPane(), "LoginPanel");

        }

        else if (e.getSource() == buttonsMap.get("MenuTicketPanel"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");

        else if (e.getSource() == buttonsMap.get("NoRecepLogOut"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");
        else if (e.getSource() == buttonsMap.get("buscarRecepcionist")) {
            setRecepcionistInfo(presenter.obtainRecepcionist(textFieldsMap.get("UpdateDocumento").getText()));
        }

        else if (e.getSource() == buttonsMap.get("ConfirmarDateSales")) {
            if (datePicker.getSelectedDate() != null) {
                int option = optionPanel("Desea continuar con la fecha: " +
                        datePicker.getSelectedDateAsString(),
                        "Continuar", 3, "Si", "No");
                if (option == 0) {
                    dateSale();
                }

            } else {

                optionPanel("Seleccione una fecha", "Seleccione una fecha", 2, "Continuar");
            }
        }
        if (e.getSource() == buttonsMap.get("GenerarReciboExitVehicle")) {

            String placa = textFieldsMap.get("PlacaExitVehicle").getText();
            String dineroStr = textFieldsMap.get("DineroExitVehicle").getText();

            if (placa.isEmpty() || dineroStr.isEmpty()) {
                optionPanel("Por favor, complete los campos de placa y dinero.", "Rellenar datos", 2, "Aceptar");
                textFieldsMap.get("PlacaExitVehicle").setText("");
            }

            try {
                double dinero = Double.parseDouble(dineroStr);
                double cost = presenter.costTikect(placa, dinero);
                if (cost == -1) {
                    optionPanel("No hay ningun vehiculo registrado con esa placa", "Alerta vehiculo", 2, "Continuar");

                } else {
                    Object[] data = {
                            placa,
                            cost,
                            dineroStr,
                            presenter.calculteChange(placa, dinero),
                            presenter.hoursVehicle(placa)
                    };

                    String[] col = { "Placa", "Valor", "Recibido", "Cambio", "Horas" };
                    JTable table = new JTable(new Object[][] { data }, col);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setPreferredSize(new Dimension(100, 20));

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    gbc.gridwidth = 2;
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    gbc.insets = new Insets(10, 10, 10, 10);

                    ticketOutPanel.add(scrollPane, gbc);
                    presenter.exitVehicle(placa);
                    if(!presenter.foundedVehicle(placa) )
                   optionPanel("Operación exitosa", "Exito", 1, "OK");
                }
                ticketOutPanel.revalidate();
                ticketOutPanel.repaint();

            } catch (NumberFormatException ex) {
                optionPanel("Ingrese un valor numérico válido en el campo 'Dinero'.", "Ingresar dinero", 2,
                        "Continuar");
            }
        }
    }

    private void dateSale() {
        LocalDate date = datePicker.getSelectedDate();
        if (date != null) {
            presenter.salesReport(date);
        }
    }

    private void resetExitVehiclePanel() {
        textFieldsMap.get("PlacaExitVehicle").setText("");
        textFieldsMap.get("DineroExitVehicle").setText("");
        for (Component comp : ticketOutPanel.getComponents()) {
            if (comp instanceof JScrollPane || comp instanceof JTable) {
                ticketOutPanel.remove(comp);
            }
        }
        ticketOutPanel.revalidate();
        ticketOutPanel.repaint();
    }

    private void entryTimeRecepcionist() {
        recepEntryTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void exitTimeRecepcionist() {
        recepExitTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static boolean validEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

}