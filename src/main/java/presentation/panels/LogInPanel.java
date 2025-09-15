package presentation.panels;

import presentation.frames.LogInFrame;
import presentation.styles.Button;
import presentation.styles.FontUtil;
import presentation.styles.Style;
import presentation.styles.textfields.PsswrdFieldPh;
import presentation.styles.textfields.TxtFieldPh;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogInPanel extends JPanel {
    LogInFrame owner;
    private JPanel mainPanel;
    private JPanel componentsPanel;
    private TxtFieldPh txtUser;
    private PsswrdFieldPh txtPassword;
    private Button btnLogIn;

    public LogInPanel(LogInFrame owner) {
        setOpaque(false);
        setPreferredSize(new Dimension(340, 450));
        this.owner = owner;

        //Components
        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(false);
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        this.componentsPanel = new JPanel();
        this.componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        this.componentsPanel.setBorder(new EmptyBorder(35, 0, 0, 0));
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setPreferredSize(new Dimension(340, 260));
        this.txtUser = new TxtFieldPh("Usuario", 257, 44, 24, 25);
        this.txtPassword = new PsswrdFieldPh("Contraseña", 257, 44, 24, 25);
        this.btnLogIn = new Button("Ingresar", 180, 45, 20, 30, Color.white, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);

        //Add components
        this.mainPanel.add(Box.createVerticalStrut(120));
        componentsPanel.add(this.txtUser);
        componentsPanel.add(this.txtPassword);
        componentsPanel.add(this.btnLogIn);
        this.mainPanel.add(componentsPanel);
        add(this.mainPanel);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(0, mainPanel.getY()+10, getWidth(), 60, 15, 15);

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
        String subTittleText = "Inicio de sesión";
        FontMetrics metricsSubTittle = g2d.getFontMetrics(subTittleFont);
        int xSubTittle = (getWidth() - metricsSubTittle.stringWidth(subTittleText)) / 2;

        g2d.setFont(subTittleFont);
        g2d.drawString(subTittleText, xSubTittle, componentsPanel.getY()+35);
    }
}
