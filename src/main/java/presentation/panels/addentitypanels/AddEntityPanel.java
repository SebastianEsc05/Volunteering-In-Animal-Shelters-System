package presentation.panels.addentitypanels;

import presentation.frames.MainFrame;
import presentation.styles.Button;
import presentation.styles.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddEntityPanel extends JPanel {
    protected MainFrame owner;
    protected JPanel mainPanel;
    protected JPanel componentsPanel;
    protected JPanel buttonsPanel;
    protected Button addBtn;
    protected Button backBtn;

    public AddEntityPanel(MainFrame owner) {
        setOpaque(false);
        this.owner = owner;
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        this.mainPanel.setOpaque(false);
        this.buttonsPanel = new JPanel();
        this.addBtn = new Button("Agregar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.backBtn = new Button("←", 50, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);
    }
}
