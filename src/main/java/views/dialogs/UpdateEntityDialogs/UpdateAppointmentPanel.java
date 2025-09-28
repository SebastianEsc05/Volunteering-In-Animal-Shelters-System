package views.dialogs.UpdateEntityDialogs;

import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import models.AppointmentEntity;
import views.frames.MainFrame;
import views.panels.addentitypanels.AddAppointmentPanel;
import views.panels.entitypanels.AppointmentsPanel;
import views.styles.ComboBoxCustom;
import views.styles.FontUtil;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class UpdateAppointmentPanel extends AddAppointmentPanel {
    private AppointmentEntity appointmententity;
    private ComboBoxCustom statusCombo;

    public UpdateAppointmentPanel(MainFrame owner, int id) {
        super(owner);
        appointmententity = appointmentController.readAppoiment(id);
        statusCombo = new ComboBoxCustom("state");
        statusCombo.setSelectedItem(appointmententity.getStatus());
        activityPanel.setPreferredSize(new Dimension(300, 40));
        componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        //Set texts
        LocalDate date = appointmentController.readAppoiment(id).getDateBooked();
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateField.setText(formatDate);
        animalTextField.setText(appointmententity.getIdAnimal().toString());
        volunteerTextField.setText(String.valueOf(appointmententity.getIdVolunteer()));
        activityTextField.setText(appointmententity.getActivity());
        commentsTextArea.setText(appointmententity.getComments());
        this.componentsPanel.add(statusCombo);

    }

    @Override
    public void addComponents() {
        componentsPanel.add(Box.createRigidArea(new Dimension(280, 40)));
        this.componentsPanel.add(this.dateField);
        this.componentsPanel.add(this.animalTextField);
        this.componentsPanel.add(this.volunteerTextField);
        this.activityPanel.add(this.activityTextField);
        componentsPanel.add(Box.createRigidArea(new Dimension(280, 30)));
        this.componentsPanel.add(this.activityPanel);
        componentsPanel.add(Box.createRigidArea(new Dimension(280, 30)));
        this.componentsPanel.add(textAreascroll);

        this.buttonsPanel.add(this.addBtn);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);
    }

    @Override
    public void addAppointment() {
        try {
            //Get data from textFields
            Integer animalId = null;
            String animalIdText = animalTextField.getText().trim();

            if (appointmententity.isAnimalCheck()){
                if (animalIdText.isEmpty()) {
                    if (animalCheckBox.isSelected()) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar un ID de animal si la actividad involucra un animal", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (!animalIdText.isEmpty()) {
                    if (!animalIdText.matches("\\d+")) {
                        JOptionPane.showMessageDialog(this, "El ID de animal debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    animalId = Integer.parseInt(animalIdText);
                    if (!animalCheckBox.isSelected()) {
                        JOptionPane.showMessageDialog(this, "El ID de animal se ha proporcionado pero la casilla 'Involucra animal' no está seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }else{
                if (!animalIdText.isEmpty()){
                    JOptionPane.showMessageDialog(this, "La asignacion no puede involucrar un animal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int volunteerId = 0;
            if(!volunteerTextField.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El ID de voluntario debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!volunteerTextField.getText().trim().isEmpty() ) {
                volunteerId = Integer.parseInt(volunteerTextField.getText());
            }

            String activity = activityTextField.getText();

            boolean animalCheck = animalCheckBox.isSelected();

            String comments = commentsTextArea.getText();
            if (activity.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de actividad no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LocalDate todayDate = LocalDate.now();
            LocalDate dateBooked = null;

            if (dateField.getText() != null && !dateField.getText().trim().isEmpty()) {
                try {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    df.setLenient(false);
                    Date parsedDate = df.parse(dateField.getText().trim());

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(parsedDate);

                    dateBooked = LocalDate.of(
                            cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH) + 1,
                            cal.get(Calendar.DAY_OF_MONTH)
                    );

                    if (dateBooked.isBefore(todayDate)) {
                        JOptionPane.showMessageDialog(this,
                                "La fecha reservada no puede ser anterior a la fecha actual",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(dateBooked + " esta antes de " + todayDate);
                        return;
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Formato de fecha inválido. Use dd/MM/yyyy",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            boolean success = appointmentController.updateAppoiment(appointmententity.getId(), comments, statusCombo.getSelectedValue(), appointmententity.getDateBooked(), dateBooked, animalId, volunteerId, activity);
            if (success) {
                JOptionPane.showMessageDialog(this, "Se ha modificado la asignación.", "Info", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
                AppointmentsPanel appointmentsPanel = this.owner.getAppointmentPanel();
                appointmentsPanel.refreshTable();
                this.owner.showNewPanel(appointmentsPanel);
            }
        } catch (ControllerException | PersistenceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

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
