package views.panels.addentitypanels;

import views.frames.MainFrame;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.FormattedDateField;
import views.styles.textfields.TextAreaCustom;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;

public class AddVolunteerPanel extends AddEntityPanel{
    private JPanel activityPanel;
    private FormattedDateField birthdayField;
    private TxtFieldPh nameTxtField;
    private TxtFieldPh phoneTxtField;
    private TxtFieldPh emailTxtField;
    private TxtFieldPh specialtyTxtField;
    private JCheckBox specialtyCheckBox;

    public AddVolunteerPanel(MainFrame owner) {
        super(owner);
        this.activityPanel = new JPanel();
        this.activityPanel.setOpaque(false);
        this.activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        this.activityPanel.setPreferredSize(new Dimension(300, 70));
        this.birthdayField = new FormattedDateField();

        this.nameTxtField = new TxtFieldPh("Nombre", 5, 100, 30, 15, 15);
        this.phoneTxtField = new TxtFieldPh("Teléfono", 5, 100, 30, 15, 15);
        this.emailTxtField = new TxtFieldPh("Email", 5, 100, 30, 15, 15);
        this.specialtyTxtField = new TxtFieldPh("Especialidad", 50, 100, 30, 15, 15);
        specialtyCheckBox = new JCheckBox("¿Tiene especialidad?");
        specialtyCheckBox.setFont(FontUtil.loadFont(12, "Inter_Light"));

        //Panels
        this.componentsPanel = new JPanel(){
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
                g2d.drawString("Fecha Nacimiento", 50, 45);
                g2d.drawString("Nombre", 50, 105);
                g2d.drawString("Telefono", 50, 165);
                g2d.drawString("Email", 50, 235);
                g2d.drawString("Especialidad", 50, 295);
                g2d.dispose();
            }
        };
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(null);
        this.componentsPanel.setPreferredSize(new Dimension(600, 350));
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(400, 100));

        this.birthdayField.setBounds(200, 30, 200, 30);
        this.nameTxtField.setBounds(200, 90, 200, 30);
        this.phoneTxtField.setBounds(200, 150, 200, 30);
        this.emailTxtField.setBounds(200, 220, 200, 30);
        this.specialtyCheckBox.setBounds(200, 280, 200, 30);

        //add components
        this.componentsPanel.add(birthdayField);
        this.componentsPanel.add(nameTxtField);
        this.componentsPanel.add(phoneTxtField);
        this.componentsPanel.add(emailTxtField);
        this.componentsPanel.add(specialtyCheckBox);
        this.componentsPanel.add(specialtyTxtField);
        this.componentsPanel.add(activityPanel);
        this.buttonsPanel.add(this.backBtn);
        this.buttonsPanel.add(this.addBtn);

        this.mainPanel.add(Box.createVerticalStrut(70));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);

        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getVolunteersPanel());
             resetFields();
        });



    }
    public void resetFields(){
        this.birthdayField.setText("");
        this.nameTxtField.setText("");
        this.phoneTxtField.setText("");
        this.emailTxtField.setText("");
        this.specialtyTxtField.setText("");
        this.specialtyCheckBox.setSelected(false);
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
        String tittleText = "Nuevo Voluntario";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);

    }

}
