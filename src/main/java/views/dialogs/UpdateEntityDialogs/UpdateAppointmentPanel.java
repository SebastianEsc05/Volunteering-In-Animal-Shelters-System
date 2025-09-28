package views.dialogs.UpdateEntityDialogs;

import models.AppointmentEntity;
import views.frames.MainFrame;
import views.panels.addentitypanels.AddAppointmentPanel;
import views.styles.FontUtil;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateAppointmentPanel extends AddAppointmentPanel {
    private AppointmentEntity appointmententity;

    public UpdateAppointmentPanel(MainFrame owner, int id) {
        super(owner);
        appointmententity = appointmentController.readAppoiment(id);
        LocalDate date = appointmentController.readAppoiment(id).getDateBooked();
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateField.setText(formatDate);
        animalTextField.setText(appointmententity.getIdAnimal().toString());
        volunteerTextField.setText(String.valueOf(appointmententity.getIdVolunteer()));
        activityTextField.setText(appointmententity.getActivity());
        commentsTextArea.setText(appointmententity.getComments());

    }

    @Override
    public void addComponents() {
        this.componentsPanel.add(this.dateField);
        this.componentsPanel.add(this.animalTextField);
        this.componentsPanel.add(this.volunteerTextField);
        this.activityPanel.add(this.activityTextField);
        this.componentsPanel.add(this.activityPanel);
        this.componentsPanel.add(textAreascroll);
        this.buttonsPanel.add(this.addBtn);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Figure 1
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(mainPanel.getX(), 20, mainPanel.getWidth(), 60, 20, 20);

        g2d.setColor(Color.black);

        //title positioning
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = "Modificar Asignacion";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);


    }
}
