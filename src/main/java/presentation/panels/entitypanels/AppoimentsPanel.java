package presentation.panels.entitypanels;

import presentation.frames.MainFrame;
import presentation.styles.*;
import presentation.styles.Button;
import presentation.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;

public class AppoimentsPanel extends EntityPanel {
    private Button newAppoimentBtn;
    private ComboBoxCustom statusComboBox;
    private TxtFieldPh searchField;

    public AppoimentsPanel(MainFrame owner) {
        super(owner);
        this.newAppoimentBtn = new Button("Nueva asignación", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        addComponents();

    }

    @Override
    public void addComponents(){
        this.sideBarPanel.setTitle("Asignaciones");
        this.sideBarPanel.add(newAppoimentBtn);
        //this.sideBarPanel.add(statusComboBox);
        //this.sideBarPanel.add(searchField);
        this.tablePanel.add(backBtn);
        this.mainPanel.add(this.sideBarPanel);
        this.mainPanel.add(Box.createHorizontalStrut(0));
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(sideBarPanel.getX()+30, sideBarPanel.getY()+10, 275, 60, 20, 20);

        //Figure 2
        g2d.fillRoundRect(sideBarPanel.getX()+30, sideBarPanel.getY()+110, 275, 440, 20, 20);
        g2d.setColor(Color.black);

        //Menu text position
        Font menuTextFont = FontUtil.loadFont(15, "Inter_Medium");
        String menuText = "Menú";
        FontMetrics metricsMenuText = g2d.getFontMetrics(menuTextFont);
        int xMenuText = (sideBarPanel.getWidth()+25 - metricsMenuText.stringWidth(menuText)) / 2;

        g2d.setFont(menuTextFont);
        g2d.drawString(menuText, xMenuText, sideBarPanel.getY()+140);

        //Filter text position
        Font filterTextFont = FontUtil.loadFont(15, "Inter_Medium");
        String filterText = "Filtros";
        FontMetrics metricsfilterText = g2d.getFontMetrics(filterTextFont);
        int xFilterText = (sideBarPanel.getWidth()+25 - metricsfilterText.stringWidth(filterText)) / 2;

        g2d.setFont(menuTextFont);
        g2d.drawString(filterText, xFilterText, sideBarPanel.getY()+230);

    }
}
