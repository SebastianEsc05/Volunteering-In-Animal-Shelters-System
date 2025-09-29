package views.panels;

import views.frames.MainFrame;
import views.styles.Button;
import views.styles.FontUtil;
import views.styles.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private MainFrame owner;
    private JPanel mainPanel;
    private JPanel componentsPanel;
    private Button appoimentsBtn;
    private Button sheltersBtn;
    private Button volunteerBtn;
    private Button animalsBtn;
    private Button moreBtn;

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
        this.appoimentsBtn = new Button("Asignaciones", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.sheltersBtn = new Button("Refugios", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.volunteerBtn = new Button("Voluntarios", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.animalsBtn = new Button("Animales", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        //this.moreBtn = new Button("Más", 185, 45, 20, 25, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);

        //ActionListeners
        appoimentsBtn.addActionListener(e -> {
            owner.showNewPanel(owner.getAppointmentPanel());
        });

        sheltersBtn.addActionListener(e -> {
            owner.showNewPanel(owner.getSheltersPanel());
        });

        volunteerBtn.addActionListener(e -> {
            owner.showNewPanel(owner.getVolunteersPanel());
        });

        animalsBtn.addActionListener(e -> {
            owner.showNewPanel(owner.getAnimalsPanel());
        });

        //Add components
        this.componentsPanel.add(appoimentsBtn);
        this.componentsPanel.add(sheltersBtn);
        this.componentsPanel.add(volunteerBtn);
        this.componentsPanel.add(animalsBtn);
        //this.componentsPanel.add(moreBtn);
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
        g2d.fillRoundRect(0, mainPanel.getY() + 10, getWidth(), 60, 20, 20);

        //Figure 2
        g2d.fillRoundRect(0, componentsPanel.getY(), getWidth(), componentsPanel.getHeight(), 20, 20);
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
