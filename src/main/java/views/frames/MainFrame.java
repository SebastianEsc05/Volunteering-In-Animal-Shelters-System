package views.frames;

import views.panels.entitypanels.AnimalsPanel;
import views.panels.entitypanels.AppointmentsPanel;
import views.panels.MainMenuPanel;
import views.panels.entitypanels.SheltersPanel;
import views.panels.entitypanels.VolunteersPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private MainMenuPanel mainMenuPanel;
    private AppointmentsPanel appoimentPanel;
    private SheltersPanel sheltersPanel;
    private VolunteersPanel volunteersPanel;
    private AnimalsPanel animalsPanel;

    public MainFrame() {
        setTitle("Sistema de Refugios");
        setSize(1000, 630);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        this.mainMenuPanel = new MainMenuPanel(this);
        this.appoimentPanel = new AppointmentsPanel(this);
        this.sheltersPanel = new SheltersPanel(this);
        this.volunteersPanel = new VolunteersPanel(this);
        this.animalsPanel = new AnimalsPanel(this);

        //Add Components
        mainPanel.add(this.mainMenuPanel);
        add(mainPanel);
        setVisible(true);
    }

    public void showNewPanel(JPanel nuevoPanel) {
        mainPanel.removeAll();
        mainPanel.add(nuevoPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public MainMenuPanel getMainMenuPanel() {
        return this.mainMenuPanel;
    }

    public AppointmentsPanel getAppointmentPanel() {
        return this.appoimentPanel;
    }

    public SheltersPanel getSheltersPanel() {
        return sheltersPanel;
    }

    public VolunteersPanel getVolunteersPanel() {
        return volunteersPanel;
    }

    public AnimalsPanel getAnimalsPanel() {
        return animalsPanel;
    }

}


