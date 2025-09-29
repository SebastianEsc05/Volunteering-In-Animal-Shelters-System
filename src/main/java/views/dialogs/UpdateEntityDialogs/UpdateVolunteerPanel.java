package views.dialogs.UpdateEntityDialogs;

import controllers.VolunteerController;
import dao.exceptions.PersistenceException;
import models.VolunteerEntity;
import views.frames.MainFrame;
import views.panels.addentitypanels.AddVolunteerPanel;
import views.panels.entitypanels.VolunteersPanel;
import views.styles.FontUtil;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateVolunteerPanel extends AddVolunteerPanel {
    private VolunteerEntity volunteerEntity;
    public UpdateVolunteerPanel(MainFrame owner, int entityId) {
        super(owner);
        volunteerEntity = volunteerController.readVolunteer(entityId);
        activityPanel.setPreferredSize(new Dimension(300, 70));
        componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));


        nameTxtField.setText(volunteerEntity.getName_volunteer());
        phoneTxtField.setText(volunteerEntity.getPhone_number());
        emailTxtField.setText(volunteerEntity.getEmail());
        if (volunteerEntity.getDate_birth() != null) {
            Date birthDate = volunteerEntity.getDate_birth();
            DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = birthDate.toLocalDate().format(DATE_FORMATTER);
            birthdayField.setText(formattedDate);
        }
        specialtyTxtField.setText(volunteerEntity.getSpecialty());
        if (volunteerEntity.getSpecialty() != null && !volunteerEntity.getSpecialty().isEmpty()) {
            specialtyCheckBox.setSelected(true);
        } else {
            specialtyCheckBox.setSelected(false);
        }
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(null);
        this.componentsPanel.setPreferredSize(new Dimension(600, 350));
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(400, 100));

        this.birthdayField.setBounds(200, 30, 200, 30);
        this.nameTxtField.setBounds(200, 90, 200, 30);
        this.phoneTxtField.setBounds(200, 140, 200, 30);
        this.emailTxtField.setBounds(200, 200, 200, 30);
        this.specialtyTxtField.setBounds(200, 260, 200, 30);
        this.specialtyCheckBox.setBounds(410, 260, 200, 30);

        //add components
        this.componentsPanel.add(birthdayField);
        this.componentsPanel.add(nameTxtField);
        this.componentsPanel.add(phoneTxtField);
        this.componentsPanel.add(emailTxtField);
        this.componentsPanel.add(specialtyTxtField);
        this.componentsPanel.add(specialtyCheckBox);
        this.componentsPanel.add(activityPanel);
        this.buttonsPanel.add(this.backBtn);
        this.buttonsPanel.add(this.addBtn);

        this.mainPanel.add(Box.createVerticalStrut(70));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);

    }

    @Override
    public void addVolunteer(){
        try {
            Date birthday = null;
            String name = this.nameTxtField.getText().trim();
            String phone = this.phoneTxtField.getText().trim();
            String email = this.emailTxtField.getText().trim();
            String specialty = this.specialtyTxtField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (specialty.isEmpty() && specialtyCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese la especialidad o desmarque la casilla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!specialty.isEmpty() && !specialtyCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Por favor, marque la casilla si tiene especialidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El número de teléfono debe contener solo dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z]{2,})+$")) {
                JOptionPane.showMessageDialog(this, "El formato del email es incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            if (birthdayField.getText() != null && !birthdayField.getText().isEmpty()) {
                try {
                    birthday = Date.valueOf(LocalDate.parse(birthdayField.getText().trim(), DATE_FORMATTER));
                } catch (DateTimeParseException ex) {
                    System.out.println("Error al parsear la fecha: " + ex.getMessage());
                    JOptionPane.showMessageDialog(
                            this,
                            "El formato de la fecha es incorrecto. Use dd/MM/yyyy.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                if (birthday.toLocalDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(this, "La fecha de nacimiento no puede ser en el futuro.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (birthday.toLocalDate().isBefore(LocalDate.of(1900, 1, 1))) {
                    JOptionPane.showMessageDialog(this, "La fecha de nacimiento no puede ser antes del 01/01/1900.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (birthday.toLocalDate().isAfter(LocalDate.now().minusYears(12))) {
                    JOptionPane.showMessageDialog(this, "El voluntario debe ser mayor de 12 años.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            boolean success = volunteerController.updateVolunteer(volunteerEntity.getId_volunteer(), name, phone, email, birthday, specialty);
            if (success) {
                JOptionPane.showMessageDialog(this, "Voluntario agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
                closePanel();
                VolunteersPanel volunteersPanel = this.owner.getVolunteersPanel();
                volunteersPanel.refreshTable();
                this.owner.showNewPanel(volunteersPanel);
            }
        }catch (PersistenceException ex){
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void closePanel(){
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
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
        String tittleText = "Modificar Voluntario";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);

    }



}
