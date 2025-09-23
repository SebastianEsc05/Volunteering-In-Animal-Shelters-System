package views.panels.entitypanels;

import controllers.AppoimentController;
import interfaces.controller.IAppoimentController;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.addentitypanels.AddAppoimentPanel;
import views.styles.*;
import views.styles.Button;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppoimentsPanel extends EntityPanel {
    private IAppoimentController appoimentController;
    private AddAppoimentPanel addAppoimentPanel;
    private Button newAppoimentBtn;
    private ComboBoxCustom statusComboBox;
    private JScrollPane scrollPane;
    private TxtFieldPh searchField;

    public AppoimentsPanel(MainFrame owner) {
        super(owner);
        this.addAppoimentPanel = new AddAppoimentPanel(owner);
        this.newAppoimentBtn = new Button("Nueva asignación", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.statusComboBox = new ComboBoxCustom("stateSearch");
        this.appoimentController = new AppoimentController();

        //Table model

        model = appoimentController.getAppoimentTable();
        table = new CustomTable(model);

        addComponents();

        //ActionListeners
        newAppoimentBtn.addActionListener(e -> {
            owner.showNewPanel(this.addAppoimentPanel);
        });

    }

    @Override
    public void addComponents(){
        //SideBarPanel
        this.sideBarPanel = new SidebarPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.black);
                g2d.dispose();
            }
        };
        this.sideBarPanel.add(newAppoimentBtn);
        this.sideBarPanel.add(statusComboBox);

        //Table Panel
        table.setPreferredScrollableViewportSize(new Dimension(600, 410));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(backBtn);
        tablePanel.add(scrollPane);

        //East Panel
        this.eastPanel.add(this.sideBarPanel);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    private void loadTable(){

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Asignaciones";
        FontMetrics metricsTittleText = g2d.getFontMetrics(tittleFont);
        int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittleText, sideBarPanel.getY()+57);

        //table border
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(tablePanel.getX()+20, scrollPane.getY()-10, scrollPane.getWidth()+20, sideBarPanel.getHeight()+30, 20, 20);

    }
}
