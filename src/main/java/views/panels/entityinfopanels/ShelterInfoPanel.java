package views.panels.entityinfopanels;

import controllers.ShelterController;
import interfaces.controller.IAppointmentController;
import interfaces.controller.IShelterController;
import views.dialogs.UpdateEntityDialogs.UpdateShelterDialog;
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
    private UpdateShelterDialog updateShelterDialog;
    private JPanel buttonsPanel;
    private JScrollPane scrollPane;
    private Button updateBtn;
    private Button deleteBtn;
    private int shelterId;


    public ShelterInfoPanel(MainFrame owner, int id) {
        super(owner);
        this.shelterController = new ShelterController();
        this.updateShelterDialog = new UpdateShelterDialog();
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setPreferredSize(new Dimension(500, 60));
        buttonsPanel.setOpaque(false);
        shelterId = id;

        updateBtn = new Button("Editar información", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        deleteBtn = new Button("Eliminar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);

        //Table model
        model = shelterController.getAnimalsByShelterIdTable(id);
        table = new CustomTable(model, owner, PanelCategory.ANIMALS);
        table.addColumnButton();
        addComponents();

        //ActionListeners
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

                g2d.dispose();
            }
        };

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
