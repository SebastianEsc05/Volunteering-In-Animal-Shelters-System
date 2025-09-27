package views.panels.entitypanels;

import controllers.ShelterController;
import interfaces.controller.IShelterController;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.addentitypanels.AddShelterPanel;
import views.styles.Button;
import views.styles.CustomTable;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;

public class SheltersPanel extends EntityPanel {
    private IShelterController shelterController;
    private AddShelterPanel addShelterPanel;
    private Button newShelterBtn;
    private JScrollPane scrollPane;
    private TxtFieldPh searchField;
    private Button searchBtn;

    public SheltersPanel(MainFrame owner) {
        super(owner);
        shelterController = new ShelterController();
        addShelterPanel = new AddShelterPanel(owner);
        newShelterBtn = new Button("Nuevo refugio", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        searchField = new TxtFieldPh("Id", 185, 35, 15, 25);
        searchBtn = new Button("Buscar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        //Table model
        model = shelterController.getShelterTable();
        table = new CustomTable(model, owner);

        addComponents();

        //ActionListeners
        newShelterBtn.addActionListener(e -> {
            owner.showNewPanel(this.addShelterPanel);
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
            }
        };
        this.sideBarPanel.add(newShelterBtn);
        sideBarPanel.add(searchField);
        sideBarPanel.add(searchBtn);

        //TablePanel
        table.setPreferredScrollableViewportSize(new Dimension(600, 410));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(backBtn);
        tablePanel.add(scrollPane);

        //East Panel
        eastPanel.add(this.sideBarPanel);
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
        String tittleText = "Refugios";
        FontMetrics metricsTittleText = g2d.getFontMetrics(tittleFont);
        int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittleText, sideBarPanel.getY()+57);

        //table border
//        g2d.setColor(Style.COLOR_BACKGROUND);
//        g2d.setStroke(new BasicStroke(3));
//        g2d.drawRoundRect(tablePanel.getX()+20, scrollPane.getY()-10, scrollPane.getWidth()+20, sideBarPanel.getHeight()+30, 20, 20);
//        g2d.setColor(Style.COLOR_BACKGROUND_DARK);
//        g2d.setStroke(new BasicStroke(1.5f));
//        g2d.drawLine(tablePanel.getX()+40, scrollPane.getY()+30, tablePanel.getX()+(tablePanel.getWidth()-30), scrollPane.getY()+30);

    }
}
