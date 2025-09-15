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
        setPreferredSize(new Dimension(500, 600));
        this.owner = owner;
        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(false);
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        this.componentsPanel = new JPanel();
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(500, 40));
        this.buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 0));
        this.addBtn = new Button("Agregar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.backBtn = new Button("Volver", 100, 35, 14, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        this.buttonsPanel.add(backBtn);
        this.buttonsPanel.add(addBtn);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);

    }

}
