package views.panels.entitypanels;

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
    private AddAppoimentPanel addAppoimentPanel;
    private Button newAppoimentBtn;
    private ComboBoxCustom statusComboBox;
    private TxtFieldPh searchField;

    public AppoimentsPanel(MainFrame owner) {
        super(owner);
        this.addAppoimentPanel = new AddAppoimentPanel(owner);
        this.newAppoimentBtn = new Button("Nueva asignación", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.statusComboBox = new ComboBoxCustom("stateSearch");

        //Table model
        model = new DefaultTableModel(new Object[]{"Id", "Fecha de realizacion", "Estado",  "Ver"}, 0);
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
        JScrollPane scroll = new JScrollPane(table);
//        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.white);
        scroll.setBorder(BorderFactory.createLineBorder(Style.COLOR_BACKGROUND_DARK, 1, true));
        tablePanel.add(backBtn);
        tablePanel.add(scroll);

        //East Panel
        this.eastPanel.add(this.sideBarPanel);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
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


    }
}
