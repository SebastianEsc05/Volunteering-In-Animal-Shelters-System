package presentation.panels;

import presentation.frames.MainFrame;
import presentation.styles.Button;
import presentation.styles.FontUtil;
import presentation.styles.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private MainFrame owner;
    private JPanel mainPanel;
    private JPanel componentsPanel;
    private Button asignacionBtn;
    private Button refugiosBtn;
    private Button voluntariosBtn;
    private Button animalesBtn;
    private Button masBtn;

    public MainMenuPanel(MainFrame owner) {
        setOpaque(false);
        setPreferredSize(new Dimension(366, 600));
        this.owner = owner;

        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(false);
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        this.componentsPanel = new JPanel();
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        this.componentsPanel.setPreferredSize(new Dimension(366, 450));
        this.componentsPanel.setBorder(new EmptyBorder(35, 0, 0, 0));
        this.asignacionBtn = new Button("Asignaciones", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.refugiosBtn = new Button("Refugios", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.voluntariosBtn = new Button("Voluntarios", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.animalesBtn = new Button("Animales", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.masBtn = new Button("Más", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);

        //Add components
        this.componentsPanel.add(asignacionBtn);
        this.componentsPanel.add(refugiosBtn);
        this.componentsPanel.add(voluntariosBtn);
        this.componentsPanel.add(animalesBtn);
        this.componentsPanel.add(masBtn);

        //Primera prueba
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(componentsPanel);
        add(this.mainPanel);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(0, mainPanel.getY() + 10, getWidth(), 60, 15, 15);

        //Figure 2
        g2d.fillRoundRect(0, componentsPanel.getY(), getWidth(), componentsPanel.getHeight(), 15, 15);

        g2d.setColor(Color.black);

        //title positioning
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = "Control de Refugios";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;

        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 55);

        //sub title positioning
        Font subTittleFont = FontUtil.loadFont(20, "Inter_Light");
        String subTittleText = "Menú Principal";
        FontMetrics metricsSubTittle = g2d.getFontMetrics(subTittleFont);
        int xSubTittle = (getWidth() - metricsSubTittle.stringWidth(subTittleText)) / 2;

        g2d.setFont(subTittleFont);
        g2d.drawString(subTittleText, xSubTittle, componentsPanel.getY() + 35);

    }
}
