package views.panels.addentitypanels;

import views.frames.MainFrame;
import views.styles.ComboBoxCustom;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;

public class AddAnimalPanel extends AddEntityPanel{
    private JPanel activityPanel;
    private TxtFieldPh nameTextField;
    private TxtFieldPh ageTextField;
    private TxtFieldPh speciesTextField;
    private ComboBoxCustom healthStatusComboBox;

    public AddAnimalPanel(MainFrame owner) {
        super(owner);
        this.activityPanel = new JPanel();
        this.activityPanel.setOpaque(false);
        this.activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        this.activityPanel.setPreferredSize(new Dimension(300, 70));

        this.nameTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        this.ageTextField = new TxtFieldPh("Nombre", 5, 100, 30, 15, 15);
        this.speciesTextField = new TxtFieldPh("Especie", 5, 100, 30, 15, 15);
        this.healthStatusComboBox = new ComboBoxCustom("health");

        this.componentsPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
    }
}
