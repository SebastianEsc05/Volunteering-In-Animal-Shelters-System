package views.panels.addentitypanels;

import views.frames.MainFrame;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;

public class AddShelterPanel extends AddEntityPanel{

    private JPanel activityPanel;
    private TxtFieldPh nameTextField;
    private TxtFieldPh locationTextField;
    private TxtFieldPh capacityTextField;
    private TxtFieldPh managerTextField;

    public AddShelterPanel(MainFrame owner) {
        super(owner);
        this.activityPanel = new JPanel();
        this.activityPanel.setOpaque(false);
        this.activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        this.activityPanel.setPreferredSize(new Dimension(300, 70));
        this.nameTextField = new TxtFieldPh("Nombre", 50, 100, 30, 15, 15);
        this.locationTextField = new TxtFieldPh("Ubicacion", 50, 100, 30, 15, 15);
        this.capacityTextField = new TxtFieldPh("Capacidad", 10, 100, 30, 15, 15);
        this.managerTextField = new TxtFieldPh("Responsable", 50, 100, 30, 15, 15);


        this.componentsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.black);

                Font SubTittleFont = FontUtil.loadFont(16, "Inter_Light");
                g2d.setFont(SubTittleFont);
                g2d.drawString("Nombre", 50, 45);
                g2d.drawString("Ubicacion", 50, 105);
                g2d.drawString("Capacidad", 50, 165);
                g2d.drawString("Responsable", 50, 235);

                g2d.dispose();
            }
        };
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(null);
        this.componentsPanel.setPreferredSize(new Dimension(600, 350));
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(400, 100));

        this.nameTextField.setBounds(200, 30, 200, 30);
        this.locationTextField.setBounds(200, 90, 200, 30);
        this.capacityTextField.setBounds(200, 150, 200, 30);
        this.managerTextField.setBounds(200, 220, 200, 30);

        //adding components
        this.componentsPanel.add(nameTextField);
        this.componentsPanel.add(locationTextField);
        this.componentsPanel.add(capacityTextField);
        this.componentsPanel.add(managerTextField);
        this.componentsPanel.add(activityPanel);
        this.buttonsPanel.add(this.backBtn);
        this.buttonsPanel.add(this.addBtn);

        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);


        //action listeners
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getSheltersPanel());
            resetFields();
        });


    }

    public void resetFields(){
        this.nameTextField.setText("");
        this.locationTextField.setText("");
        this.capacityTextField.setText("");
        this.managerTextField.setText("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(mainPanel.getX(), 20, mainPanel.getWidth(), 60, 20, 20);

        g2d.setColor(Color.black);

        //title positioning
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = "Nuevo Refugio";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);


    }


}
