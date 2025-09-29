package views.panels.entitypanels;

import controllers.AppointmentController;
import interfaces.controller.IAppointmentController;
import views.enums.PanelCategory;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.addentitypanels.AddAppointmentPanel;
import views.panels.entityinfopanels.AppointmentInfoPanel;
import views.styles.*;
import views.styles.Button;
import views.styles.textfields.TxtFieldPh;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppointmentsPanel extends EntityPanel {
    private IAppointmentController appoimentController;
    private AddAppointmentPanel addAppointmentPanel;
    private AppointmentInfoPanel appointmentInfoPanel;
    private Button newAppoimentBtn;
    private JScrollPane scrollPane;
    private ComboBoxCustom statusComboBox;
    private TxtFieldPh searchField;
    private Button searchBtn;

    public AppointmentsPanel(MainFrame owner) {
        super(owner);
        this.appoimentController = new AppointmentController();
        this.addAppointmentPanel = new AddAppointmentPanel(owner);
//        this.appointmentInfoPanel = new AppointmentInfoPanel(owner);
        this.newAppoimentBtn = new Button("Nueva asignación", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.statusComboBox = new ComboBoxCustom("stateSearch");
        searchField = new TxtFieldPh("Id", 185, 35, 15, 25);
        searchBtn = new Button("Buscar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        //Table model
        model = appoimentController.getAppointmentTable();
        table = new CustomTable(model, owner, PanelCategory.APPOINTMENTS);
        table.addColumnButton();
        addComponents();

        //ActionListeners
        newAppoimentBtn.addActionListener(e -> {
            owner.showNewPanel(this.addAppointmentPanel);
        });

        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getMainMenuPanel());
            resetSearchField();
            refreshTable();
        });

        statusComboBox.addActionListener(e -> {
            updateFilter();
        });

        searchBtn.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                try {
                    int id = Integer.parseInt(searchText);
                    DefaultTableModel newModel = appoimentController.getAppointmentsByIdTable(id);
                    table.setModel(newModel);
                    table.addColumnButton();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un Id válido.", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                DefaultTableModel newModel = appoimentController.getAppointmentTable();
                table.setModel(newModel);
                table.addColumnButton();
            }
        });

    }
    public void resetSearchField(){
        searchField.setText("");
    }

    public void refreshTable(){
        table.setModel(appoimentController.getAppointmentTable());
        table.addColumnButton();
        revalidate();
        repaint();
    }

    @Override
    public void addComponents(){

        //SideBarPanel
        this.sideBarPanel = new SidebarPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.black);

                //SideBar tittles position
                Font sideBarTextFont = FontUtil.loadFont(16, "Inter_Regular");
                String sideBarTittle = "Menu";
                FontMetrics metricsTittleText = g2d.getFontMetrics(sideBarTextFont);
                int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(sideBarTittle)) / 2;
                g2d.setFont(sideBarTextFont);
                g2d.drawString(sideBarTittle, xTittleText, sideBarPanel.getY()+25);

                //Filter tittles position
                Font sideBarTextFont2 = FontUtil.loadFont(16, "Inter_18pt-ExtraLight");
                String sideBarTittle2 = "Estado";
                String sideBarTittle3 = "Buscar Por Id";
                FontMetrics metricsTittleText2 = g2d.getFontMetrics(sideBarTextFont2);
                int xTittleText2 = (sideBarPanel.getWidth() - metricsTittleText2.stringWidth(sideBarTittle2)) / 2;
                int xTittleText3 = (sideBarPanel.getWidth() - metricsTittleText2.stringWidth(sideBarTittle3)) / 2;
                g2d.setFont(sideBarTextFont2);
                g2d.drawString(sideBarTittle2, xTittleText2, sideBarPanel.getY()+95);
                g2d.drawString(sideBarTittle3, xTittleText3, sideBarPanel.getY()+170);

                g2d.dispose();
            }
        };
        this.sideBarPanel.add(newAppoimentBtn);
        this.sideBarPanel.add(statusComboBox);
        this.sideBarPanel.add(searchField);
        this.sideBarPanel.add(searchBtn);

        //Table Panel
        table.setPreferredScrollableViewportSize(new Dimension(600, 340));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(backBtn);
        tablePanel.add(scrollPane);

        //East Panel
        this.eastPanel.add(this.sideBarPanel);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    public void updateFilter() {
        String status = statusComboBox.getSelectedValue();
        DefaultTableModel newModel;
        try {
            switch (status) {
                case "Todos":
                    newModel = appoimentController.getAppointmentTable();
                    break;
                case "Pendiente":
                    newModel = appoimentController.getAppointmentByStatusPendingTable();
                    break;
                case "Cancelada":
                    newModel = appoimentController.getAppointmentByStatusCanceledTable();
                    break;
                case "Finalizada":
                    newModel = appoimentController.getAppointmentByStatusCompletedTable();
                    break;
                default:
                    newModel = appoimentController.getAppointmentTable();
            }
            table.setModel(newModel);
            table.addColumnButton();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public AppointmentInfoPanel getAppointmentInfoPanel() {
        return appointmentInfoPanel;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Asignaciones";
        FontMetrics metricsTittleText = g2d.getFontMetrics(tittleFont);
        int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittleText, sideBarPanel.getY()+57);

        //table border
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(tablePanel.getX()+20, scrollPane.getY()-10, scrollPane.getWidth()+20, sideBarPanel.getHeight()+30, 20, 20);
        g2d.setColor(Style.COLOR_BACKGROUND_DARK);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawLine(tablePanel.getX()+40, scrollPane.getY()+30, tablePanel.getX()+(tablePanel.getWidth()-35), scrollPane.getY()+30);

    }
}
