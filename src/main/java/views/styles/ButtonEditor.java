package views.styles;

import views.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor{
    private MainFrame owner;
    private JButton button;
    private CustomTable table;
    private boolean isPushed;
    private int currentRow;

    public ButtonEditor(JCheckBox checkBox, MainFrame owner) {
        super(checkBox);
        this.owner = owner;
        button = new JButton("≡");
        button.setFont(new Font("SansSerif", Font.PLAIN, 24));
        button.setBackground(new Color(60, 63, 83));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        button.addActionListener(e -> {
            if (isPushed) {
                Long id = (Long) table.getModel().getValueAt(currentRow, 0);


            }
        });

    }
}
