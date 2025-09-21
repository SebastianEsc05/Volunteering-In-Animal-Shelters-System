package presentation.panels.entitypanels;

import presentation.frames.MainFrame;
import presentation.panels.addentitypanels.AddAnimalPanel;
import presentation.panels.addentitypanels.AddAppoimentPanel;
import presentation.styles.Button;
import presentation.styles.FontUtil;
import presentation.styles.Style;

import javax.swing.*;
import java.awt.*;

public class AnimalsPanel extends EntityPanel{
    private AddAnimalPanel addAnimalPanel;
    private Button newAnimalBtn;

    public AnimalsPanel(MainFrame owner) {
        super(owner);
        this.addAnimalPanel = new AddAnimalPanel(owner);
        this.newAnimalBtn = new Button("Nuevo Animal", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        addComponents();

        //ActionListeners
        newAnimalBtn.addActionListener(e -> {
            owner.showNewPanel(this.addAnimalPanel);
        });

    }

    @Override
    public void addComponents(){
        this.sideBarPanel.setTitle("Animales");
        this.sideBarPanel.add(newAnimalBtn);
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

    }
}
