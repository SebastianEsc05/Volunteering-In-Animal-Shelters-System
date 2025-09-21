package views.frames;

import views.panels.LogInPanel;

import javax.swing.*;
import java.awt.*;

public class LogInFrame extends JFrame {
    private JPanel mainPanel;
    private LogInPanel logInPanel;

    public LogInFrame() {

        setTitle("Iniciar Sesión");
        setSize(400, 450);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        this.logInPanel = new LogInPanel(this);

        mainPanel.add(logInPanel);
        add(mainPanel);
        setVisible(true);

    }
}
