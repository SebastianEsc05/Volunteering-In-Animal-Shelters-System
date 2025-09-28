package views.dialogs.UpdateEntityDialogs;

import controllers.ControllerException;
import controllers.ShelterController;
import dao.exceptions.PersistenceException;
import models.ShelterEntity;
import views.frames.MainFrame;
import views.panels.addentitypanels.AddAppointmentPanel;
import views.panels.addentitypanels.AddShelterPanel;
import views.panels.entitypanels.SheltersPanel;
import views.styles.ComboBoxCustom;
import views.styles.FontUtil;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;

public class UpdateShelterPanel extends AddShelterPanel {
    private ShelterEntity shelterEntity;

    public UpdateShelterPanel(MainFrame owner, int id) {
        super(owner);
        shelterEntity = shelterController.readShelter(id);

        //Set texts
        String nameTextField = shelterController.readShelter(id).getNameShelter();
        String locationTextField = shelterController.readShelter(id).getLocation();
        String capacityTextField = String.valueOf(shelterController.readShelter(id).getCapacity());
        String managerTextField = shelterController.readShelter(id).getResponsible();
        this.nameTextField.setText(nameTextField);
        this.locationTextField.setText(locationTextField);
        this.capacityTextField.setText(capacityTextField);
        this.managerTextField.setText(managerTextField);
        this.nameTextField.setBounds(200, 30, 200, 30);
        this.locationTextField.setBounds(200, 90, 200, 30);
        this.capacityTextField.setBounds(200, 150, 200, 30);
        this.managerTextField.setBounds(200, 220, 200, 30);



    }
    @Override
    public void addComponents() {
        this.componentsPanel.add(this.nameTextField);
        this.componentsPanel.add(this.locationTextField);
        this.componentsPanel.add(this.capacityTextField);
        this.componentsPanel.add(this.managerTextField);
        this.buttonsPanel.add(this.addBtn);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(30));
        this.mainPanel.add(this.buttonsPanel);
        this.add(mainPanel, BorderLayout.CENTER);

    }

    @Override
    public void addShelter() {
        try {
            String name = this.nameTextField.getText().trim();
            String location = this.locationTextField.getText().trim();
            String capacityStr = this.capacityTextField.getText().trim();
            String manager = this.managerTextField.getText().trim();

            if (name.isEmpty() || location.isEmpty() || capacityStr.isEmpty() || manager.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.length() > 50 || location.length() > 50 || manager.length() > 50) {
                JOptionPane.showMessageDialog(this, "Los campos de texto no pueden tener más de 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!name.matches("[a-zA-ZÀ-ÿ\\s]+") || !location.matches("[a-zA-ZÀ-ÿ\\s]+") || !manager.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                JOptionPane.showMessageDialog(this, "Los campos de texto solo pueden contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int capacity;
            try {
                capacity = Integer.parseInt(capacityStr);
                if (capacity <= 0) throw new NumberFormatException();
                if (capacity > 5000) {
                    JOptionPane.showMessageDialog(this, "La capacidad no puede ser mayor a 5 mil.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La capacidad debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ShelterController shelterController = new ShelterController();
            boolean success = shelterController.updateShelter(shelterEntity.getIdShelter(), name, location, capacity, manager);

            if (success) {
                JOptionPane.showMessageDialog(this, "Refugio agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetFields();

                SheltersPanel sheltersPanel = this.owner.getSheltersPanel();
                sheltersPanel.refreshTable();
                this.owner.showNewPanel(sheltersPanel);
            }

        } catch (ControllerException | PersistenceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resetName();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        String tittleText = "Modificar Refugio";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);

    }
}
