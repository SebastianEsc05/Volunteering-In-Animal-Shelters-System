package presentation.panels.addentitypanels;

import presentation.frames.MainFrame;
import presentation.styles.FontUtil;
import presentation.styles.Style;
import presentation.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;

public class AddAppoimentPanel extends AddEntityPanel {
    //Add LocalDate label
    //Add LocalDate txtField
    private TxtFieldPh animalTextField;
    private TxtFieldPh volunteerTextField;
    private TxtFieldPh activityTextField;
    private JTextArea observationsTextArea;

    public AddAppoimentPanel(MainFrame owner) {
        super(owner);
        this.componentsPanel.setPreferredSize(new Dimension(200, 400));
        this.animalTextField = new TxtFieldPh("id", 100, 30, 12, 15);
        this.volunteerTextField = new TxtFieldPh("id", 100, 30, 12, 15);
        this.activityTextField = new TxtFieldPh(" ", 200, 30, 12, 15);

        //ActionListeners
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getAppoimentPanel());
        });

        //Add components
        this.componentsPanel.add(this.animalTextField);
        this.componentsPanel.add(this.volunteerTextField);
        this.componentsPanel.add(this.activityTextField);
        //this.componentsPanel.add(observationsTextArea);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(65, mainPanel.getY() + 10, getWidth()-140, 60, 20, 20);

        //Figure 2
        g2d.fillRoundRect(65, componentsPanel.getY(), getWidth()-140, componentsPanel.getHeight(), 20, 20);

        //title positioning
        g2d.setColor(Color.black);
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = "Nueva Asignación";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;

        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 55);

    }
}
