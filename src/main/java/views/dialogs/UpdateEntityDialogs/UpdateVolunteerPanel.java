package views.dialogs.UpdateEntityDialogs;

import views.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class UpdateVolunteerPanel extends JPanel {

    public UpdateVolunteerPanel(MainFrame owner) {

    }
    public void closePanel(){
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }
}
