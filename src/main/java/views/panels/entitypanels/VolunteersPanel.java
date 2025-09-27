package views.panels.entitypanels;

import controllers.VolunteerController;
import interfaces.controller.IVolunteerController;
import views.enums.PanelCategory;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.addentitypanels.AddVolunteerPanel;
import views.styles.Button;
import views.styles.CustomTable;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VolunteersPanel extends EntityPanel {
    private IVolunteerController volunteerController;
    private AddVolunteerPanel addVolunteerPanel;
    private Button newVolunteerBtn;
    private JScrollPane scrollPane;
    private TxtFieldPh searchField;
    private Button searchBtn;

    public VolunteersPanel(MainFrame owner) {
        super(owner);
        volunteerController = new VolunteerController();
        addVolunteerPanel = new AddVolunteerPanel(owner);
        this.newVolunteerBtn = new Button("Nuevo Voluntario", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        searchField = new TxtFieldPh("Id", 185, 35, 15, 25);
        searchBtn = new Button("Buscar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        //Table model
        model = volunteerController.getVolunteerTable();
        table = new CustomTable(model, owner, PanelCategory.VOLUNTEERS);
        table.addColumnButton();
        addComponents();

        //ActionListeners
        newVolunteerBtn.addActionListener(e -> {
            owner.showNewPanel(this.addVolunteerPanel);
        });

        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getMainMenuPanel());
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
                model = volunteerController.getVolunteerTable();
                table.setModel(model);
                table.repaint();
            }
        });

    }

    public void buscarPorId(int id) {
        DefaultTableModel newModel;
        try {
            newModel = volunteerController.getVooluntersByIdTable(id);
            table.setModel(newModel);
            table.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @Override
    public void addComponents() {
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

                g2d.dispose();
            }
        };
        this.sideBarPanel.add(newVolunteerBtn);
        sideBarPanel.add(searchField);
        sideBarPanel.add(searchBtn);

        //Table Panel
        table.setPreferredScrollableViewportSize(new Dimension(600, 410));
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Voluntarios";
        FontMetrics metricsTittleText = g2d.getFontMetrics(tittleFont);
        int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittleText, sideBarPanel.getY()+57);

//        //table border
//        g2d.setColor(Style.COLOR_BACKGROUND);
//        g2d.setStroke(new BasicStroke(3));
//        g2d.drawRoundRect(tablePanel.getX()+20, scrollPane.getY()-10, scrollPane.getWidth()+20, sideBarPanel.getHeight()+30, 20, 20);
//        g2d.setColor(Style.COLOR_BACKGROUND_DARK);
//        g2d.setStroke(new BasicStroke(1.5f));
//        g2d.drawLine(tablePanel.getX()+40, scrollPane.getY()+30, tablePanel.getX()+(tablePanel.getWidth()-35), scrollPane.getY()+30);
//

    }
}
