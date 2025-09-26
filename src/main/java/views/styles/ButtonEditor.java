package views.styles;

import javax.swing.*;

public class ButtonEditor extends DefaultCellEditor{
    private JButton button;
    private boolean isPushed;
    private int filaActual;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);

    }
}
