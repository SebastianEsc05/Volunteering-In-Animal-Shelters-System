package views.panels.entityinfopanels;

import controllers.VolunteerController;
import interfaces.controller.IVolunteerController;
import models.VolunteerEntity;
import views.dialogs.UpdateEntityDialogs.UpdateEntityDialog;
import views.enums.PanelCategory;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.entitypanels.EntityPanel;
import views.styles.Button;
import views.styles.CustomTable;
import views.styles.FontUtil;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;

public class VolunteerInfoPanel extends EntityPanel {
    private IVolunteerController volunteerController;
    private VolunteerEntity volunteerEntity;
    private UpdateEntityDialog updateEntityDialog;
    private JPanel buttonsPanel;
    private JPanel infoPanel;
    private JPanel labelsPanel;
    private JLabel headerLabel;
    private JLabel volunteerIdlabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel birthDateLabel;
    private JLabel specialtyLabel;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JScrollPane scrollPane;
    private int volunteerId;

    public VolunteerInfoPanel(MainFrame owner, int id) {
        super(owner);
        volunteerController = new VolunteerController();
        volunteerEntity = volunteerController.readVolunteer(id);

        //Buttons Panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(500, 60));
        buttonsPanel.setOpaque(false);
        volunteerId = id;

        //Id Header
        headerLabel = new JLabel(String.valueOf(volunteerId));
        headerLabel.setFont(new java.awt.Font("Inter", java.awt.Font.PLAIN, 24));

        updateBtn = new views.styles.Button("Editar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        deleteBtn = new Button("Eliminar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);

        //Table model
        model = volunteerController.getAppointmentsByVolunteerId(volunteerId);
        table = new CustomTable(model, owner, PanelCategory.VOLUNTEERS);
        table.addColumnButton();
        addComponents();

        //ActionListeners
        updateBtn.addActionListener(e -> {
            updateEntityDialog = new UpdateEntityDialog(owner, PanelCategory.VOLUNTEERS,volunteerId);
        });

        deleteBtn.addActionListener(e -> {
            Object[] opciones = {"Si","Cancelar"};

            int ans = JOptionPane.showOptionDialog(
                    null,
                    "Desea eliminar este voluntario?",
                    "Eliminar voluntario",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if(ans == 0){
                if(model.getRowCount()>0){
                    JOptionPane.showMessageDialog(null,"Su voluntario no puede ser Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"voluntario Eliminado");
                    volunteerController.deleteVolunteer(volunteerId);
                    this.owner.showNewPanel(this.owner.getVolunteersPanel());
                }
            }
        });
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getVolunteersPanel());
        });

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

                g2d.dispose();
            }
        };
        //Labels Panel
        setLabels();
        labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setOpaque(false);
        labelsPanel.add(volunteerIdlabel);
        sideBarPanel.add(labelsPanel);

        buttonsPanel.add(backBtn);
        //Table Panel
        table.setPreferredScrollableViewportSize(new Dimension(600, 340));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(backBtn);
        tablePanel.add(scrollPane);

        //East Panel
        eastPanel.add(this.sideBarPanel);
        eastPanel.add(updateBtn);
        eastPanel.add(deleteBtn);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }


    public void setLabels(){
        //Create labels
        volunteerIdlabel = new JLabel(String.valueOf(volunteerEntity.getId_volunteer()));
        nameLabel = new JLabel("Nombre: " + volunteerEntity.getName_volunteer());
        phoneLabel = new JLabel("Teléfono: " + volunteerEntity.getPhone_number());
        emailLabel = new JLabel("Email: " + volunteerEntity.getEmail());
        birthDateLabel = new JLabel("Fecha de Nacimiento: " + volunteerEntity.getDate_birth());
        specialtyLabel = new JLabel("Especialidad: " + volunteerEntity.getSpecialty());

        //set Font
        nameLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));
        phoneLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));
        emailLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));
        birthDateLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));
        specialtyLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));

    }
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Voluntario";
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
