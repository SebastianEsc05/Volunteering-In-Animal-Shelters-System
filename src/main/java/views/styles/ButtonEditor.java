package views.styles;

import interfaces.controller.IAppointmentController;
import views.enums.PanelCategory;
import views.frames.MainFrame;
import views.panels.entityinfopanels.AnimalInfoPanel;
import views.panels.entityinfopanels.AppointmentInfoPanel;
import views.panels.entityinfopanels.ShelterInfoPanel;
import views.panels.entityinfopanels.VolunteerInfoPanel;

import javax.swing.*;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor{
    private MainFrame owner;
    private JButton button;
    private PanelCategory category;
    private boolean isPushed;
    private int currentRow;
    private int id;

    public ButtonEditor(JCheckBox checkBox, MainFrame owner, PanelCategory category) {
        super(checkBox);
        this.owner = owner;
        this.category = category;
        button = new JButton("≡");
        button.setFont(new Font("SansSerif", Font.PLAIN, 24));
        button.setBackground(new Color(60, 63, 83));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        button.addActionListener(e -> {
            if (isPushed) {
                switch (category) {
                    case APPOINTMENTS -> owner.showNewPanel(new AppointmentInfoPanel(owner, id));
                    case SHELTERS   -> owner.showNewPanel(new ShelterInfoPanel(owner, id));
                    case ANIMALS    -> owner.showNewPanel(new AnimalInfoPanel(owner, id));
                    case VOLUNTEERS -> owner.showNewPanel(new VolunteerInfoPanel(owner));
                }

            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.currentRow = row;
        this.isPushed = true;
        int id = (int) table.getValueAt(row, table.getColumnModel().getColumnIndex("Id"));
        this.id = id;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return null;
    }
}
