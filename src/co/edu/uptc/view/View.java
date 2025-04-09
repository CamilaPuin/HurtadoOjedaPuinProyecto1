package co.edu.uptc.view;

import co.edu.uptc.model.Exceptions.DateVehicleNotFoundException;
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
    private JButton logoutAdmin;
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
    private boolean alertaTotalMostrada;
    private boolean alertaMotosMostrada;
    private boolean alertaCarrosMostrada;

    public View() {
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        this.presenter = new Presenter(this);
        buttonsMap = new HashMap<>();
        textFieldsMap = new HashMap<>();
        datePicker = new DatePicker();
        getContentPane().add(loginPanel(), "LoginPanel");
        // TODO pasar a locales y en ingles (Juan)
        alertaTotalMostrada = false;
        alertaMotosMostrada = false;
        alertaCarrosMostrada = false;
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
        addComponent(login, createButton("Ingresar", "incomeLoginPanel"), gbc, 0, 2, 2);
        return login;
    }

    private String readLogin() {
        String id = textFieldsMap.get("LoginUser").getText();
        String password = textFieldsMap.get("LoginPassword").getText();
        textFieldsMap.get("LoginUser").setText("");
        textFieldsMap.get("LoginPassword").setText("");
        return presenter.logIn(id, password);
    }

    private JPanel welcome() {
        JPanel welcome = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);
        addComponent(welcome, createLabel("BIENVENIDO A PARKING UPTC", 20), gbc, 0, 0, 1);
        addComponent(welcome, createLabel("SELECCIONE UNA OPCIÓN DEL MENÚ", 15), gbc, 0, 1, 1);

        return welcome;
    }

    private JPanel adminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(1, 2));
        adminLeftPanel = new JPanel();
        adminLeftPanel.setLayout(new BoxLayout(adminLeftPanel, BoxLayout.Y_AXIS));
        adminLeftPanel.setPreferredSize(new Dimension(150, getHeight()));
        adminLeftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        createRecepcionist = new JButton("Crear Recepcionista");
        updateRecepcionist = new JButton("Actualizar Recepcionista");
        salesReport = new JButton("Reporte de Ventas");
        logoutAdmin = new JButton("Cerrar sesión");
        ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setAlignmentX(CENTER_ALIGNMENT);
        createRecepcionist.setAlignmentX(CENTER_ALIGNMENT);
        updateRecepcionist.setAlignmentX(CENTER_ALIGNMENT);
        salesReport.setAlignmentX(CENTER_ALIGNMENT);
        logoutAdmin.setAlignmentX(CENTER_ALIGNMENT);
        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(createRecepcionist);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(updateRecepcionist);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(salesReport);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(logoutAdmin);
        adminLeftPanel.add(centerPanel, BorderLayout.CENTER);
        adminCardLayout = new CardLayout();
        adminRightPanel = new JPanel(adminCardLayout);
        JPanel welcome = welcome();
        JPanel createRecepcionistPanel = createRecepcionist();
        JPanel updateRecepcionistPanel = updateRecepcionist();
        JPanel salesReportPanel = salesReport();
        JPanel generateReportPanel = generateReportPanel();
        JPanel logoutPanel = logoutAdmin();
        adminRightPanel.add(welcome, "Welcome");
        adminRightPanel.add(createRecepcionistPanel, "Create Recepcionist");
        adminRightPanel.add(updateRecepcionistPanel, "Update Recepcionist");
        adminRightPanel.add(salesReportPanel, "Sales Report");
        adminRightPanel.add(generateReportPanel, "Generate Report");
        adminRightPanel.add(logoutPanel, "Logout");
        createRecepcionist.addActionListener(this);
        updateRecepcionist.addActionListener(this);
        salesReport.addActionListener(this);
        logoutAdmin.addActionListener(this);
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
        addComponent(recepcionistPanel, createTextField("createID"), gbc, 1, 1, 1);
        addComponent(recepcionistPanel, createTextField("createName"), gbc, 1, 2, 1);
        addComponent(recepcionistPanel, createTextField("createLastName"), gbc, 1, 3, 1);
        addComponent(recepcionistPanel, createTextField("createAddress"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createTextField("createPhone"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createTextField("createEmail"), gbc, 1, 6, 1);
        addComponent(recepcionistPanel, createButton("Crear", "createOfCreateRecepcionist"), gbc, 0, 7, 2);
        return recepcionistPanel;
    }

    private void readCreateRecepcionist() {
        if (numericValue(textFieldsMap.get("createID").getText()))
            if (alphabethicString(textFieldsMap.get("createName").getText()))
                if (alphabethicString(textFieldsMap.get("createLastName").getText()))
                    if (phoneVerification(textFieldsMap.get("createPhone").getText()))
                        if (validEmail(textFieldsMap.get("createEmail").getText())) {
                            presenter.createRecepcionist(
                                    textFieldsMap.get("createName").getText(),
                                    textFieldsMap.get("createLastName").getText(),
                                    textFieldsMap.get("createEmail").getText(),
                                    textFieldsMap.get("createPhone").getText(),
                                    textFieldsMap.get("createAddress").getText(),
                                    textFieldsMap.get("createID").getText());
                            JOptionPane.showMessageDialog(this, "Recepcionista registrado con éxito",
                                    "Recepcionista registrado",
                                    JOptionPane.INFORMATION_MESSAGE);
                            textFieldsMap.get("createName").setText("");
                            textFieldsMap.get("createLastName").setText("");
                            textFieldsMap.get("createEmail").setText("");
                            textFieldsMap.get("createPhone").setText("");
                            textFieldsMap.get("createAddress").setText("");
                            textFieldsMap.get("createID").setText("");
                        } else
                            JOptionPane.showMessageDialog(this, "Formato de correo electrónico inválido",
                                    "Email invalido",
                                    JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(this, "Formato de teléfono inválido",
                                "Teléfono invalido",
                                JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this, "Formato de apellidos inválido",
                            "Apellidos invalidos",
                            JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Formato de nombre inválido",
                        "Nombre invalido",
                        JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Formato de documento inválido",
                    "Documento invalido",
                    JOptionPane.ERROR_MESSAGE);
    }

    private JPanel updateRecepcionist() {
        JPanel recepcionistPanel = new JPanel(new GridBagLayout());
        recepcionistPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(recepcionistPanel, createLabel("Digite los datos para actualizar el recepcionista"), gbc, 0, 0, 2);
        addComponent(recepcionistPanel, createLabel("Documento"), gbc, 0, 1, 1);
        addComponent(recepcionistPanel, createTextField("updateDocument"), gbc, 1, 1, 1);
        gbc.insets = new Insets(5, 0, 15, 0);
        addComponent(recepcionistPanel, createButton("Buscar", "searchOfUpdateRecepcionist"), gbc, 0, 2, 2);
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(recepcionistPanel, createLabel("Nombre"), gbc, 0, 3, 1);
        addComponent(recepcionistPanel, createTextField("updateName"), gbc, 1, 3, 1);
        textFieldsMap.get("updateName").setEnabled(false);
        addComponent(recepcionistPanel, createLabel("Direccion"), gbc, 0, 4, 1);
        addComponent(recepcionistPanel, createTextField("updateAddress"), gbc, 1, 4, 1);
        addComponent(recepcionistPanel, createLabel("Telefono"), gbc, 0, 5, 1);
        addComponent(recepcionistPanel, createTextField("updatePhone"), gbc, 1, 5, 1);
        addComponent(recepcionistPanel, createLabel("Email"), gbc, 0, 6, 1);
        addComponent(recepcionistPanel, createTextField("updateEmail"), gbc, 1, 6, 1);
        addComponent(recepcionistPanel, createLabel("Nueva contraseña"), gbc, 0, 7, 1);
        addComponent(recepcionistPanel, createPasswordField("updateNewPassword"), gbc, 1, 7, 1);
        addComponent(recepcionistPanel, createLabel("Confirmar contraseña"), gbc, 0, 8, 1);
        addComponent(recepcionistPanel, createPasswordField("updateConfirmPassword"), gbc, 1, 8, 1);
        gbc.insets = new Insets(0, 0, 0, 0);
        addComponent(recepcionistPanel, createLabel("- La nueva contraseña no debe ser repetida"), gbc, 0, 9, 2);
        addComponent(recepcionistPanel, createLabel("- No tener caracteres especiales"), gbc, 0, 10, 2);
        addComponent(recepcionistPanel, createLabel("- Debe tener mínimo 8 caracteres"), gbc, 0, 11, 2);
        gbc.insets = new Insets(10, 0, 0, 0);
        addComponent(recepcionistPanel, createButton("Actualizar", "updateOfUpdateRecepcionist"), gbc, 0, 12, 2);
        return recepcionistPanel;
    }

    private boolean setRecepcionistInfo(String[] recepcionistInfo) {
        if (recepcionistInfo == null) {
            JOptionPane.showMessageDialog(this, "Recepcionista no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        textFieldsMap.get("updateName").setText(recepcionistInfo[0]);
        textFieldsMap.get("updateAddress").setText(recepcionistInfo[1]);
        textFieldsMap.get("updatePhone").setText(recepcionistInfo[2]);
        textFieldsMap.get("updateEmail").setText(recepcionistInfo[3]);
        return true;
    }

    private void readUpdateRecepcionist() {
        if (alphabethicString(textFieldsMap.get("createName").getText()))
            if (alphabethicString(textFieldsMap.get("createLastName").getText()))
                if (phoneVerification(textFieldsMap.get("createPhone").getText()))
                    if (validEmail(textFieldsMap.get("createEmail").getText()))
                        if (presenter.updateRecepcionist(textFieldsMap.get("updateDocument").getText(),
                                textFieldsMap.get("updateAddress").getText(),
                                textFieldsMap.get("updatePhone").getText(),
                                textFieldsMap.get("updateEmail").getText(),
                                textFieldsMap.get("updateNewPassword").getText(),
                                textFieldsMap.get("updateConfirmPassword").getText())) {
                            JOptionPane.showMessageDialog(this, "Información actualizada correctamente.", "Éxito",
                                    JOptionPane.INFORMATION_MESSAGE);
                            resetUpdateRecepcionistPanel();
                        } else
                            JOptionPane.showMessageDialog(this, "Formato de correo electrónico inválido",
                                    "Email invalido",
                                    JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(this, "Formato de teléfono inválido",
                                "Teléfono invalido",
                                JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this, "Formato de apellidos inválido",
                            "Apellidos invalidos",
                            JOptionPane.ERROR_MESSAGE);
            else

                JOptionPane.showMessageDialog(this, "Formato de nombre inválido",
                        "Nombre invalido",
                        JOptionPane.ERROR_MESSAGE);

    }

    private void resetUpdateRecepcionistPanel() {
        textFieldsMap.get("updateDocument").setText("");
        textFieldsMap.get("updateName").setText("");
        textFieldsMap.get("updateAddress").setText("");
        textFieldsMap.get("updatePhone").setText("");
        textFieldsMap.get("updateEmail").setText("");
        textFieldsMap.get("updateNewPassword").setText("");
        textFieldsMap.get("updateConfirmPassword").setText("");
    }

    private JPanel salesReport() {
        JPanel report = new JPanel(new GridBagLayout());
        report.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(report, createLabel("Reporte de ventas de:"), gbc, 0, 0, 1);
        addComponent(report, createLabel(datePicker.getSelectedDateAsString()), gbc, 1, 0, 1);
        addComponent(report, createLabel("Total ingresos"), gbc, 0, 1, 1);
        addComponent(report, createLabel(presenter.totalMoney(datePicker.getSelectedDate()) + ""), gbc, 1, 1, 1);
        addComponent(report, createLabel("Total vehiculos ingresados"), gbc, 0, 2, 1);
        addComponent(report, createLabel(presenter.totalVehicles(datePicker.getSelectedDate()) + ""), gbc, 1, 2, 1);
        String[] col = { "Nombre", "Apellidos", "Ingreso", "Vehiculos" };
        JTable table = new JTable();
        try {
            table = new JTable(presenter.getConsolidatedRecepcionists(datePicker.getSelectedDate()), col);

        } catch (DateVehicleNotFoundException e) {
            optionPanel(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, "Aceptar");
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 60));
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(report, scrollPane, gbcTable, 0, 3, 2);
        addComponent(report, createButton("Regresar al menú", "backOfSalesReport"), gbc, 0, 4, 2);
        return report;
    }

    private JPanel logoutAdmin() {
        JPanel logOutPanel = new JPanel(new GridBagLayout());
        logOutPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(logOutPanel, createLabel("¿Desea cerrar sesión?"), gbc, 0, 0, 2);
        addComponent(logOutPanel, createButton("Si", "yesAdminLogOut"), gbc, 0, 1, 1);
        addComponent(logOutPanel, createButton("No", "noAdminLogout"), gbc, 1, 1, 1);
        return logOutPanel;
    }

    private JPanel ticketPanel(String plate) {
        ticketPanel = new JPanel(new GridBagLayout());
        ticketPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addComponent(ticketPanel, createLabel("Su ticket ha sido generado exitosamente"), gbc, 0, 0, 2);

        gbc.gridy = 1;
        addComponent(ticketPanel, createLabel("Resumen"), gbc, 0, 1, 2);

        String[] col = { "Placa", "Fecha", "Hora ingreso", "Tipo de vehiculo" };
        tableModel = new DefaultTableModel(col, 0);
        ticketRegisterTable = new JTable(tableModel);
        updateTable(plate);
        JScrollPane scrollPane = new JScrollPane(ticketRegisterTable);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        addComponent(ticketPanel, scrollPane, gbc, 0, 2, 2);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        addComponent(ticketPanel, createButton("Imprimir recibo", "printOfTicketPanel"), gbc, 0, 3, 1);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addComponent(ticketPanel, createButton("Regresar", "backOfTicketPanel"), gbc, 1, 3, 1);

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
        datePicker.setColor(Color.BLUE);
        addComponent(generateReport, datePicker, gbc, 0, 0, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        addComponent(generateReport, createButton("Confirmar", "confirmOfSalesReport"), gbc, 0, 1, 1);
        return generateReport;
    }

    private JPanel recepcionistPanel() {
        JPanel recepPanel = new JPanel(new GridLayout(1, 2));
        recepLeftPanel = new JPanel(new BorderLayout());
        recepLeftPanel.setPreferredSize(new Dimension(150, getHeight()));
        recepLeftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        availableSpaces = new JButton("Espacios Disponibles");
        registerVehicle = new JButton("Registrar Vehiculo");
        exitVehicle = new JButton("Salida Vehiculo");
        recepLogOut = new JButton("Cerrar Sesión");
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
        JPanel exitVehiclePanel = exitVehiclePanel();
        JPanel recepLogOutPanel = logOutRecep();
        JPanel ticketPanel = ticketPanel("");
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
            for (int i = 0; i < lines.length; i++) {
                addComponent(panel, createLabel(lines[i], i == 0 ? 25 : 12), gbc, 1, i + 1, 1);
            }
            addComponent(panel, createButton("Salir", "outOfAvailableSpaces"), gbc, 0, 4, 2);
            String[] etiquetas = { "Total", "Motos", "Carros" };
            for (int i = 0; i < lines.length; i++) {
                int espaciosDisponibles = Integer.parseInt(lines[i].replaceAll("\\D+", ""));
                if (espaciosDisponibles <= 5) {
                    switch (i) {
                        case 0:
                            if (!alertaTotalMostrada) {
                                showAlert(etiquetas[i], espaciosDisponibles);
                                alertaTotalMostrada = true;
                            }
                            break;
                        case 1:
                            if (!alertaMotosMostrada) {
                                showAlert(etiquetas[i], espaciosDisponibles);
                                alertaMotosMostrada = true;
                            }
                            break;
                        case 2:
                            if (!alertaCarrosMostrada) {
                                showAlert(etiquetas[i], espaciosDisponibles);
                                alertaCarrosMostrada = true;
                            }
                            break;
                    }
                }
            }
        }
        return panel;
    }

    private void showAlert(String tipo, int espaciosDisponibles) {
        optionPanel("¡Alerta! Espacios limitados.\n\n" + tipo + ": " + espaciosDisponibles, "Advertencia",
                JOptionPane.WARNING_MESSAGE,
                "Entendido");
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
        addComponent(incomePanel, createTextField("plateRegisterVehicle"), gbc, 1, 1, 1);
        addComponent(incomePanel, createLabel("Tipo"), gbc, 0, 2, 1);
        addComponent(incomePanel, comboBox, gbc, 1, 2, 1);
        addComponent(incomePanel, createButton("Siguiente", "nextOfRegisterVehicle"), gbc, 0, 3, 2);
        return incomePanel;
    }

    private JPanel exitVehiclePanel() {
        ticketOutPanel = new JPanel(new GridBagLayout());
        ticketOutPanel.setSize(400, 600);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(ticketOutPanel, createLabel("Ingrese la placa del vehiculo"), gbc, 0, 0, 2);
        addComponent(ticketOutPanel, createLabel("Placa"), gbc, 0, 1, 1);
        addComponent(ticketOutPanel, createTextField("plateExitVehicle"), gbc, 1, 1, 1);
        addComponent(ticketOutPanel, createLabel("Ingrese el dinero para generar el recibo"), gbc, 0, 2, 2);
        addComponent(ticketOutPanel, createLabel("Dinero"), gbc, 0, 3, 1);
        addComponent(ticketOutPanel, createTextField("moneyExitVehicle"), gbc, 1, 3, 1);
        addComponent(ticketOutPanel, createLabel("Recibo"), gbc, 0, 5, 2);
        addComponent(ticketOutPanel, createButton("Generar recibo", "generateTicketOfExitVehicle"), gbc, 0, 6, 2);
        addComponent(ticketOutPanel, createButton("Registrar salida", "registerExitOfExitVehicle"), gbc, 0, 8, 2);
        return ticketOutPanel;
    }

    private JPanel logOutRecep() {
        JPanel consolidadoPanel = new JPanel(new GridBagLayout());
        consolidadoPanel.setPreferredSize(new Dimension(400, 600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(consolidadoPanel, createLabel("Consolidado"), gbc, 0, 0, 2);
        String[] colOne = { "Nombre", "Apellidos", "Hora de entrada" };
        Object[] dataOne = {
                presenter.obtainRecepcionistData()[0],
                presenter.obtainRecepcionistData()[1],
                recepEntryTime
        };
        DefaultTableModel modelOne = new DefaultTableModel(new Object[][] { dataOne }, colOne);
        JTable tableOne = new JTable(modelOne);

        tableOne.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableOne.setShowGrid(true);
        tableOne.setGridColor(Color.GRAY);
        JScrollPane scrollPaneOne = new JScrollPane(tableOne);
        scrollPaneOne.setPreferredSize(new Dimension(400, 100));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(consolidadoPanel, scrollPaneOne, gbc, 0, 1, 2);

        String[] colTwo = { "Hora salida", "Total ingresos", "Total vehiculos" };
        Object[] dataTwo = {
                recepExitTime,
                presenter.income(),
                presenter.numAttendedVehicles()
        };

        DefaultTableModel modelTwo = new DefaultTableModel(new Object[][] { dataTwo }, colTwo);
        JTable tableTwo = new JTable(modelTwo);

        tableTwo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableTwo.setShowGrid(true);
        tableTwo.setGridColor(Color.GRAY);
        JScrollPane scrollPaneTwo = new JScrollPane(tableTwo);
        scrollPaneTwo.setPreferredSize(new Dimension(400, 100));
        addComponent(consolidadoPanel, scrollPaneTwo, gbc, 0, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 10, 10);
        addComponent(consolidadoPanel, createLabel("¿Desea cerrar sesión?"), gbc, 0, 3, 2);
        addComponent(consolidadoPanel, createButton("Si", "yesOfRecepcionistLogOut"), gbc, 0, 4, 1);
        addComponent(consolidadoPanel, createButton("No", "noOfRecepcionistLogOut"), gbc, 1, 4, 1);
        consolidadoPanel.revalidate();
        consolidadoPanel.repaint();
        return consolidadoPanel;
    }

    private boolean readRegisterVehicle() {
        String plate = textFieldsMap.get("plateRegisterVehicle").getText().trim().toUpperCase();
        String type = comboBox.getSelectedItem().toString();

        if (plate.isEmpty()) {
            showErrorMessage("Por favor, ingrese una placa válida.");
            return false;
        }

        if (type.equals("Carro") && !plate.matches("^[A-Z]{3}\\d{3}$")) {
            showErrorMessage("Formato de placa inválido para un carro. Debe ser 3 letras seguidas de 3 números.");
            return false;
        }

        if (type.equals("Moto") && !plate.matches("^[A-Z]{3}\\d{2}[A-Z]$")) {
            showErrorMessage("Formato de placa inválido para una moto. Debe ser 3 letras, 2 números y 1 letra.");
            return false;
        }

        if (presenter.foundedVehicle(plate)) {
            showErrorMessage("El vehiculo ya se encuentra registrado.");
            return false;
        }
        String result = presenter.registerVehicle(plate, type);
        if (result.contains("No hay espacio disponible")) {
            showErrorMessage(result);
            return false;
        }
        showInfoMessage(result);
        recepRightPanel.remove(ticketPanel);
        ticketPanel = ticketPanel(plate);
        recepRightPanel.add(ticketPanel, "TicketPanel");
        recepcionistCardLayout.show(recepRightPanel, "TicketPanel");
        comboBox.setSelectedIndex(0);
        return true;
    }

    private void readExitVehicle() {
        JTextField plateField = textFieldsMap.get("plateExitVehicle");
        String plate = plateField.getText().trim().toUpperCase();

        if (plate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una placa válida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!plate.matches("^[A-Z]{3}\\d{3}$") && !plate.matches("^[A-Z]{3}\\d{2}[A-Z]$")) {
            JOptionPane.showMessageDialog(this,
                    "Formato de placa inválido. Debe ser 3 letras seguidas de 3 números (carro) o 3 letras, 2 números y 1 letra (moto).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        presenter.exitVehicle(plate);
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

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void entryTimeRecepcionist() {
        recepEntryTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void exitTimeRecepcionist() {
        recepExitTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public boolean validEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private void resetExitVehiclePanel() {
        textFieldsMap.get("plateExitVehicle").setText("");
        textFieldsMap.get("moneyExitVehicle").setText("");
        for (Component comp : ticketOutPanel.getComponents()) {
            if (comp instanceof JScrollPane || comp instanceof JTable) {
                ticketOutPanel.remove(comp);
            }
        }
        ticketOutPanel.revalidate();
        ticketOutPanel.repaint();
    }

    private boolean alphabethicString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (!((character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    private boolean phoneVerification(String str) {
        if (str == null || str.length() != 10) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean numericValue(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonsMap.get("incomeLoginPanel")) {
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

        else if (e.getSource() == updateRecepcionist) {
            resetUpdateRecepcionistPanel();
            adminCardLayout.show(adminRightPanel, "Update Recepcionist");
        }

        else if (e.getSource() == salesReport)
            adminCardLayout.show(adminRightPanel, "Generate Report");

        else if (e.getSource() == logoutAdmin)
            adminCardLayout.show(adminRightPanel, "Logout");

        else if (e.getSource() == availableSpaces) {
            JPanel updatedPanel = availableSpacesPanel();
            recepRightPanel.add(updatedPanel, "Available Spaces");
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

        else if (e.getSource() == buttonsMap.get("createOfCreateRecepcionist"))
            readCreateRecepcionist();

        else if (e.getSource() == buttonsMap.get("updateOfUpdateRecepcionist")) {
            if (setRecepcionistInfo(presenter.obtainRecepcionist(textFieldsMap.get("updateDocument").getText()))) {
                readUpdateRecepcionist();
            }
        }

        else if (e.getSource() == buttonsMap.get("noAdminLogout")) {
            cardLayout.show(adminRightPanel, "Welcome");

        } else if (e.getSource() == buttonsMap.get("yesAdminLogOut")) {
            cardLayout.show(getContentPane(), "LoginPanel");

        } else if (e.getSource() == buttonsMap.get("printOfTicketPanel")) {
            JOptionPane.showMessageDialog(this, "Imprimiendo recibo", "Recibo", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == buttonsMap.get("nextOfRegisterVehicle")) {
            if (readRegisterVehicle()) {
                textFieldsMap.get("plateRegisterVehicle").setText("");
            }
        }

        else if (e.getSource() == buttonsMap.get("registerExitOfExitVehicle")) {
            readExitVehicle();
            recepcionistCardLayout.show(recepRightPanel, "Welcome");
            optionPanel("Operación exitosa", "Exito", 1, "OK");
            resetExitVehiclePanel();
        }

        else if (e.getSource() == buttonsMap.get("outOfAvailableSpaces"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");

        else if (e.getSource() == buttonsMap.get("yesOfRecepcionistLogOut")) {
            cardLayout.show(getContentPane(), "LoginPanel");

        }

        else if (e.getSource() == buttonsMap.get("backOfTicketPanel"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");

        else if (e.getSource() == buttonsMap.get("noOfRecepcionistLogOut"))
            recepcionistCardLayout.show(recepRightPanel, "Welcome");
        else if (e.getSource() == buttonsMap.get("searchOfUpdateRecepcionist")) {
            setRecepcionistInfo(presenter.obtainRecepcionist(textFieldsMap.get("updateDocument").getText()));
        }

        else if (e.getSource() == buttonsMap.get("confirmOfSalesReport")) {
            if (datePicker.getSelectedDate() != null) {
                int option = optionPanel("Desea continuar con la fecha: " +
                        datePicker.getSelectedDateAsString(),
                        "Continuar", 3, "Si", "No");
                if (option == 0) {
                    try {
                        Object[][] data = presenter.getConsolidatedRecepcionists(datePicker.getSelectedDate());
                    } catch (DateVehicleNotFoundException ex) {
                        optionPanel(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, "Aceptar");
                    }
                }
                adminCardLayout.show(adminRightPanel, "Sales Report");
            } else {
                optionPanel("Seleccione una fecha", "Seleccione una fecha", 2, "Continuar");
            }
        }

        if (e.getSource() == buttonsMap.get("generateTicketOfExitVehicle")) {

            String plate = textFieldsMap.get("plateExitVehicle").getText();
            String dineroStr = textFieldsMap.get("moneyExitVehicle").getText();

            if (plate.isEmpty() || dineroStr.isEmpty()) {
                optionPanel("Por favor, complete los campos de placa y dinero.", "Rellenar datos", 2, "Aceptar");
                textFieldsMap.get("PlacaExitVehicle").setText("");
            }

            double dinero = Double.parseDouble(dineroStr);
            double cost = presenter.costTikect(plate, dinero);
            if (cost == 0) {
                optionPanel("No hay ningun vehiculo registrado con esa placa", "Alerta vehiculo",
                        JOptionPane.ERROR_MESSAGE, "Continuar");
            } else {
                Object[] data = {
                        plate,
                        cost,
                        dineroStr,
                        presenter.calculteChange(plate, dinero),
                        presenter.hoursVehicle(plate)
                };

                String[] col = { "Placa", "Valor", "Recibido", "Cambio", "Horas" };
                JTable table = new JTable(new Object[][] { data }, col);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new Dimension(300, 60));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.NONE;
                gbc.weightx = 0;
                gbc.weighty = 0;
                gbc.insets = new Insets(10, 10, 10, 10);

                ticketOutPanel.add(scrollPane, gbc);
                presenter.exitVehicle(plate);
                if (!presenter.foundedVehicle(plate))
                    optionPanel("Operación exitosa", "Exito", 1, "OK");
                ticketOutPanel.revalidate();
                ticketOutPanel.repaint();
                updateTable(plate);
            }

        }
        if (e.getSource() == buttonsMap.get("backOfSalesReport")) {
            adminCardLayout.show(adminRightPanel, "Welcome");
        }
    }
}
