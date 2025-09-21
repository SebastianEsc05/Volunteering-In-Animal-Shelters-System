package presentation.panels;

import presentation.styles.FontUtil;
import presentation.styles.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 35));
        setPreferredSize(new Dimension(366, 400));
        setMaximumSize(new Dimension(275, 350));

    }
}
