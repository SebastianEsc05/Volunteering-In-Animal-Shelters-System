package presentation.panels.entitypanels;

import presentation.frames.MainFrame;
import presentation.styles.Button;
import presentation.styles.FontUtil;
import presentation.styles.Style;

import javax.swing.*;
import java.awt.*;

public class VolunteersPanel extends EntityPanel {
    private Button newVolunteerBtn;

    public VolunteersPanel(MainFrame owner) {
        super(owner);
        this.newVolunteerBtn = new Button("Nuevo Voluntario", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        addComponents();

    }

    @Override
    public void addComponents() {
        this.sideBarPanel.setTitle("Voluntarios");
        this.sideBarPanel.add(this.newVolunteerBtn);
        this.tablePanel.add(backBtn);
        this.mainPanel.add(this.sideBarPanel);
        this.mainPanel.add(Box.createHorizontalStrut(0));
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(sideBarPanel.getX() + 30, sideBarPanel.getY() + 10, 275, 60, 20, 20);

        //Figure 2
        g2d.fillRoundRect(sideBarPanel.getX() + 30, sideBarPanel.getY() + 110, 275, 440, 20, 20);
        g2d.setColor(Color.black);

        //Menu text position
        Font menuTextFont = FontUtil.loadFont(15, "Inter_Medium");
        String menuText = "Menú";
        FontMetrics metricsMenuText = g2d.getFontMetrics(menuTextFont);
        int xMenuText = (sideBarPanel.getWidth() + 25 - metricsMenuText.stringWidth(menuText)) / 2;

        g2d.setFont(menuTextFont);
        g2d.drawString(menuText, xMenuText, sideBarPanel.getY() + 140);

    }
}
