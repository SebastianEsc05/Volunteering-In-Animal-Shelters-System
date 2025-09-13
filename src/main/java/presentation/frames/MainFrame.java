package presentation.frames;

import presentation.panels.AsignacionesPanel;
import presentation.panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private MainMenuPanel mainMenuPanel;
    private AsignacionesPanel asignacionesPanel;

    public MainFrame() {
        setTitle("Sistema de Refugios");
        setSize(1000, 630);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        this.mainMenuPanel = new MainMenuPanel(this);

        //Add Components
        mainPanel.add(this.mainMenuPanel);

        add(mainPanel);
        setVisible(true);
    }
}
