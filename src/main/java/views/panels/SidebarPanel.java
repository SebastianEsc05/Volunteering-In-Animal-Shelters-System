package views.panels;

import views.styles.FontUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SidebarPanel extends JPanel {
    String title;

    public SidebarPanel() {
        setPreferredSize(new Dimension(310, 600));
        setOpaque(false);
        setLayout(new FlowLayout());
        setBorder(new EmptyBorder(150, 25, 0, 0));

    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //title positioning
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = this.title;
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth()+30 - metricsTittle.stringWidth(tittleText)) / 2;

        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 50);
    }
}
