package views.panels.entityinfopanels;

import controllers.AppointmentController;
import interfaces.controller.IAppointmentController;
import models.AppointmentEntity;
import views.dialogs.UpdateEntityDialogs.UpdateAppoimentDialog;
import views.frames.MainFrame;
import views.panels.entitypanels.EntityPanel;
import views.styles.Button;
import views.styles.StatusLabel;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;

public class AppoimentInfoPanel extends EntityPanel {
    private IAppointmentController appoimentController;
    private UpdateAppoimentDialog updateAppoimentDialog;
    private JPanel detailPanel;
    private StatusLabel statusLabel;
    private Button updateBtn;
    private Button deleteBtn;
    private Button backBtn;
    private int appointmentId;

    public AppoimentInfoPanel(MainFrame owner, int appointmentId) {
        super(owner);
        appoimentController = new AppointmentController();
        updateAppoimentDialog = new UpdateAppoimentDialog();
        statusLabel = new StatusLabel(appoimentController.readAppoiment(appointmentId).getStatus());
        this.appointmentId = appointmentId;
        detailPanel = new JPanel();
        updateBtn = new Button("Editar asignacion", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        deleteBtn = new Button("Eliminar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);

        //

        //ActionListeners
        updateBtn.addActionListener(e -> {

        });

    }

    @Override
    public void addComponents() {
        super.addComponents();
    }
}
