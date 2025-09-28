package views.dialogs.UpdateEntityDialogs;

import views.enums.PanelCategory;
import views.frames.MainFrame;
import views.panels.MainMenuPanel;
import views.panels.addentitypanels.AddAppointmentPanel;
import views.styles.Button;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;

public class UpdateEntityDialog extends JDialog {
    private MainFrame owner;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private PanelCategory category;
    private int entityId;

    public UpdateEntityDialog(MainFrame owner, PanelCategory category, int id) {
        super(owner, " ", true);
        setSize(800, 630);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.owner = owner;
        mainPanel = new JPanel();
        entityId = id;
        mainPanel.setOpaque(false);
        setPanel(category);
        add(mainPanel);
        setVisible(true);

    }

    public void setPanel(PanelCategory category) {
        switch(category){
            case APPOINTMENTS -> showNewPanel(new UpdateAppointmentPanel(owner, entityId));
            case SHELTERS -> showNewPanel(new UpdateShelterPanel(owner, entityId));
            case ANIMALS -> showNewPanel(new UpdateAnimalPanel(owner));
            case VOLUNTEERS -> showNewPanel(new UpdateVolunteerPanel(owner));
        }
    }

    public void showNewPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
