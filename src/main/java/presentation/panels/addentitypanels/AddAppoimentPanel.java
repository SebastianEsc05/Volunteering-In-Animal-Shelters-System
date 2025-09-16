package presentation.panels.addentitypanels;

import presentation.frames.MainFrame;
import presentation.styles.FontUtil;
import presentation.styles.Style;
import presentation.styles.textfields.FormattedDateField;
import presentation.styles.textfields.TextAreaCustom;
import presentation.styles.textfields.TxtFieldPh;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AddAppoimentPanel extends AddEntityPanel {
    private FormattedDateField dateField;
    private TxtFieldPh animalTextField;
    private TxtFieldPh volunteerTextField;
    private TxtFieldPh activityTextField;
    private TextAreaCustom observationsTextArea;

    public AddAppoimentPanel(MainFrame owner) {
        super(owner);
        this.dateField = new FormattedDateField();
        this.animalTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        this.volunteerTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        this.activityTextField = new TxtFieldPh(" ", 200, 30, 15, 15);
        this.observationsTextArea = new TextAreaCustom(4, 20);

        //Panels
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 60));
        this.componentsPanel.setPreferredSize(new Dimension(366, 400));
        this.componentsPanel.setMaximumSize(new Dimension(366, 400));
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(400, 100));

        JScrollPane textAreascroll = new JScrollPane(this.observationsTextArea);
        textAreascroll.setBorder(null);
        textAreascroll.setAlignmentX(LEFT_ALIGNMENT);

        //ActionListeners
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getAppoimentPanel());
        });

        //Add components
        this.componentsPanel.add(this.dateField);
        this.componentsPanel.add(this.animalTextField);
        this.componentsPanel.add(this.volunteerTextField);
        this.componentsPanel.add(this.activityTextField);
        this.componentsPanel.add(textAreascroll);
        this.buttonsPanel.add(this.backBtn);
        this.buttonsPanel.add(this.addBtn);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);

    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(mainPanel.getX(), 20, mainPanel.getWidth(), 60, 20, 20);

        g2d.setColor(Color.black);

        //title positioning
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = "Nueva Asignación";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);

        //Labels
        Font SubTittleFont = FontUtil.loadFont(14, "Inter_Light");
        g2d.setFont(SubTittleFont);
        g2d.drawString("Fecha", this.dateField.getX(), 200);

    }
}
