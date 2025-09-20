package views.panels.entitypanels;

import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.styles.Button;
import views.styles.CustomTable;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {
    protected MainFrame owner;
    protected JPanel mainPanel;
    protected JPanel tablePanel;
    protected SidebarPanel sideBarPanel;
    protected Button backBtn;
    protected CustomTable table;

    public EntityPanel(MainFrame owner) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.owner = owner;
        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(false);
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.X_AXIS));
        this.sideBarPanel = new SidebarPanel();
        this.tablePanel = new JPanel();
        this.tablePanel.setOpaque(false);
        this.tablePanel.setPreferredSize(new Dimension(690, 600));
        this.backBtn = new Button("←", 50, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        //ActionListeners
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getMainMenuPanel());
        });

    }

    public void addComponents() {
    }

}
