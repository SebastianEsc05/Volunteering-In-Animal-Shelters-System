package views.panels.entityinfopanels;

import controllers.ShelterController;
import interfaces.controller.IShelterController;
import models.ShelterEntity;
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

public class ShelterInfoPanel extends EntityPanel {
    private IShelterController shelterController;
    private ShelterEntity shelterEntity;
    private UpdateEntityDialog updateEntityDialog;
    private JPanel buttonsPanel;
    private JPanel labelsPanel;
    private JLabel headerLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel capacityLabel;
    private JLabel responsibleLabel;
    private JLabel adressLabel;
    private JScrollPane scrollPane;
    private Button updateBtn;
    private Button deleteBtn;
    private int shelterId;


    public ShelterInfoPanel(MainFrame owner, int id) {
        super(owner);
        this.shelterController = new ShelterController();
        shelterEntity = shelterController.readShelter(id);

        //Buttons Panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setPreferredSize(new Dimension(500, 60));
        buttonsPanel.setOpaque(false);
        shelterId = id;

        //Header
        headerLabel = new JLabel("Animales en refugio");
        headerLabel.setFont(FontUtil.loadFont( 24, "Inter_Light"));

        updateBtn = new Button("Editar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        deleteBtn = new Button("Eliminar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);

        //Table model
        model = shelterController.getAnimalsByShelterIdTable(id);
        table = new CustomTable(model, owner, PanelCategory.ANIMALS, this);
        table.addColumnButton();
        addComponents();

        //ActionListeners
        updateBtn.addActionListener(e -> {
            updateEntityDialog = new UpdateEntityDialog(owner, PanelCategory.SHELTERS, id);
        });

        deleteBtn.addActionListener(e -> {
            Object[] opciones = {"Si","Cancelar"};

            int ans = JOptionPane.showOptionDialog(
                    null,
                    "Desea eliminar este refugio?",
                    "Eliminar Refugio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if(ans == 0){
                if(model.getRowCount()>0){
                    JOptionPane.showMessageDialog(null,"Su refugio no puede ser Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Refugio Eliminado");
                    shelterController.deleteShelter(id);
                    this.owner.showNewPanel(this.owner.getSheltersPanel());
                }
            }
        });
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getSheltersPanel());
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

                //SideBar tittles position
                Font sideBarTextFont = FontUtil.loadFont(14, "Inter_Regular");
                String nameTittle = "Nombre";
                String capacityTittle = "Capacidad";
                String responsibleTittle = "Responsable";
                String adressTittle = "Direccion";
                FontMetrics metricsTittle = g2d.getFontMetrics(sideBarTextFont);
                int xNameTittle = (sideBarPanel.getWidth() - metricsTittle.stringWidth(nameTittle)) / 2;
                int xCapacityTittle = (sideBarPanel.getWidth() - metricsTittle.stringWidth(capacityTittle)) / 2;
                int xResponsibleTittl = (sideBarPanel.getWidth() - metricsTittle.stringWidth(responsibleTittle)) / 2;
                int xAdressTittle = (sideBarPanel.getWidth() - metricsTittle.stringWidth(adressTittle)) / 2;
                g2d.setFont(sideBarTextFont);
                g2d.drawString(nameTittle, xNameTittle, sideBarPanel.getY()+90);
                g2d.drawString(capacityTittle, xCapacityTittle, sideBarPanel.getY()+140);
                g2d.drawString(responsibleTittle, xResponsibleTittl, sideBarPanel.getY()+190);
                g2d.drawString(adressTittle, xAdressTittle, sideBarPanel.getY()+240);

                g2d.dispose();
            }
        };
        //Labels Panel
        setLabels();
        labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setOpaque(false);
        labelsPanel.add(idLabel);

        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelsPanel.add(Box.createVerticalStrut(30));

        labelsPanel.add(nameLabel);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelsPanel.add(Box.createVerticalStrut(30));
        labelsPanel.add(capacityLabel);

        capacityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelsPanel.add(Box.createVerticalStrut(30));
        labelsPanel.add(responsibleLabel);

        responsibleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelsPanel.add(Box.createVerticalStrut(30));
        labelsPanel.add(adressLabel);

        adressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBarPanel.add(labelsPanel);

        buttonsPanel.add(backBtn);
        //Table Panel
        table.setPreferredScrollableViewportSize(new Dimension(600, 340));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(backBtn);
        tablePanel.add(headerLabel);
        tablePanel.add(scrollPane);
        tablePanel.add(Box.createRigidArea(new Dimension(200, 0)));
        tablePanel.add(updateBtn);
        tablePanel.add(deleteBtn);

        //East Panel
        eastPanel.add(this.sideBarPanel);

        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    public void setLabels(){
        //Create labels
        idLabel = new JLabel(String.valueOf(shelterEntity.getIdShelter()));
        nameLabel = new JLabel(shelterEntity.getNameShelter());
        capacityLabel = new JLabel(String.valueOf(shelterEntity.getCapacity()));
        responsibleLabel = new JLabel(shelterEntity.getResponsible());
        adressLabel = new JLabel(shelterEntity.getLocation());

        //Set Font
        idLabel.setFont(FontUtil.loadFont( 25, "Inter_Regular"));
        nameLabel.setFont(FontUtil.loadFont(16, "Inter_Light"));
        capacityLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));
        responsibleLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));
        adressLabel.setFont(FontUtil.loadFont( 16, "Inter_Light"));

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Refugio";
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
