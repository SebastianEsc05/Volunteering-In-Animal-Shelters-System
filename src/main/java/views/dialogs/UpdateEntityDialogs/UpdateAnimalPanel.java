package views.dialogs.UpdateEntityDialogs;

import views.frames.MainFrame;
import views.styles.Button;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;

public class UpdateAnimalPanel extends JPanel {
    protected MainFrame owner;
    protected JPanel mainPanel;
    protected JPanel componentsPanel;
    protected JPanel buttonsPanel;
    protected Button updateBtn;
    protected Button backBtn;

    public UpdateAnimalPanel(MainFrame owner) {


        updateBtn = new Button("Actualizar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        backBtn = new Button("←", 50, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);



    }

}
