package views.panels.entitypanels;

import controllers.AppointmentController;
import interfaces.controller.IAppointmentController;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.addentitypanels.AddAppointmentPanel;
import views.styles.*;
import views.styles.Button;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppointmentsPanel extends EntityPanel {
    private IAppointmentController appoimentController;
    private AddAppointmentPanel addAppointmentPanel;
    private Button newAppoimentBtn;
    private JScrollPane scrollPane;
    private ComboBoxCustom statusComboBox;
    private TxtFieldPh searchField;
    private Button searchBtn;

    public AppointmentsPanel(MainFrame owner) {
        super(owner);
        this.appoimentController = new AppointmentController();
        this.addAppointmentPanel = new AddAppointmentPanel(owner);
        this.newAppoimentBtn = new Button("Nueva asignación", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.statusComboBox = new ComboBoxCustom("stateSearch");
        searchField = new TxtFieldPh("Id", 185, 35, 15, 25);
        searchBtn = new Button("Buscar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        //Table model
        model = appoimentController.getAppoimentTable();
        table = new CustomTable(model);
        table.addColumnButton();

        addComponents();

        //ActionListeners
        newAppoimentBtn.addActionListener(e -> {
            owner.showNewPanel(this.addAppointmentPanel);
        });

        statusComboBox.addActionListener(e -> {;
            actualizarFiltro();
        });

        searchBtn.addActionListener(e -> {
            String idText = searchField.getText().trim();
            if (!idText.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);
                    buscarPorId(id);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                actualizarFiltro(); // Si el campo está vacío, restablecer el filtro
            }
        });
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
        table.setPreferredScrollableViewportSize(new Dimension(600, 410));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(true);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(backBtn);
        tablePanel.add(scrollPane);

        //East Panel
        this.eastPanel.add(this.sideBarPanel);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    public void actualizarFiltro() {
        String estado = statusComboBox.getSelectedValue();
        DefaultTableModel newModel;
        try {
            switch (estado) {
                case "Todos":
                    newModel = appoimentController.getAppoimentTable();

                    break;
                case "Pendiente":
                    newModel = appoimentController.getAppoimentByStatusPendingTable();
                    break;
                case "Cancelada":
                    newModel = appoimentController.getAppoimentByStatusCanceledTable();
                    break;
                case "Finalizada":
                    newModel = appoimentController.getAppoimentByStatusCompletedTable();
                    break;
                default:
                    newModel = appoimentController.getAppoimentTable();
            }
            table.setModel(newModel);
            table.repaint();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void buscarPorId(int id) {
        DefaultTableModel newModel;
        try {
            newModel = appoimentController.getAppoimentByIdTable(id);
            table.setModel(newModel);
            table.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
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

        g2d.dispose();
    }
}
