package views.dialogs.UpdateEntityDialogs;

import controllers.AnimalController;
import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import interfaces.controller.IAnimalController;
import models.AnimalEntity;
import views.frames.MainFrame;
import views.panels.addentitypanels.AddAnimalPanel;
import views.panels.entitypanels.AnimalsPanel;
import views.styles.Button;
import views.styles.ComboBoxCustom;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.TextAreaCustom;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * nombre
 * edad
 * fecha_ingreso
 * estado_salud
 * especie
 * id_refugio
 */
public class UpdateAnimalPanel extends AddAnimalPanel {
    private AnimalEntity animalEntity;
    private ComboBoxCustom statusCombo;

    public UpdateAnimalPanel(MainFrame owner,int id) {
        super(owner);
        animalController = new AnimalController();

        animalEntity = animalController.readAnimal(id);
        statusCombo = new ComboBoxCustom("health");

        activityPanel.setPreferredSize(new Dimension(300, 40));
        componentsPanel.setLayout(null);

        ageTextField.setText(String.valueOf(animalEntity.getAge()));
        nameTextField.setText(String.valueOf(animalEntity.getName()));
        statusCombo.setSelectedItem(animalEntity.getHealth_situation());
        speciesTextField.setText(String.valueOf(animalEntity.getSpecie()));
        shelterTextField.setText(String.valueOf(animalEntity.getId_shelter()));

        addComponents();
    }
    @Override
    public void addComponents(){
        this.componentsPanel.add(this.ageTextField);
        this.ageTextField.setBounds(235,20,110,35);
        this.componentsPanel.add(this.speciesTextField);
        this.speciesTextField.setBounds(235,70,110,35);
        this.componentsPanel.add(this.nameTextField);
        this.nameTextField.setBounds(235,120,110,35);
        this.componentsPanel.add(this.healthStatusComboBox);
        this.healthStatusComboBox.setBounds(235,170,110,35);
        this.componentsPanel.add(this.shelterTextField);
        this.shelterTextField.setBounds(235,220,110,35);
        this.componentsPanel.add(this.commentsTextArea);
        this.commentsTextArea.setBounds(15,285,340,100);

        this.buttonsPanel.add(addBtn);


        this.mainPanel.add(Box.createVerticalStrut(50));
        this.mainPanel.add(componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(buttonsPanel);

        add(this.mainPanel);
    }

    @Override
    public void addAnimal(){
        try{
            String ageText = ageTextField.getText();
            if(ageText.isEmpty()){
                JOptionPane.showMessageDialog(this, "El campo de edad no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if(!ageText.matches("\\d+")){
                JOptionPane.showMessageDialog(this, "El campo de edad debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int age = Integer.parseInt(ageText);
            if(age < 0){
                JOptionPane.showMessageDialog(this, "El campo de edad no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = nameTextField.getText();
            if (name.isEmpty()){
                JOptionPane.showMessageDialog(this, "El campo de nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String specie = speciesTextField.getText();
            if(specie.isEmpty()){
                JOptionPane.showMessageDialog(this, "El campo de especie no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String shelterIDText = shelterTextField.getText().trim();
            if(shelterIDText.isEmpty()){
                JOptionPane.showMessageDialog(this, "El campo de Refugio no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!shelterIDText.matches("\\d+")){
                JOptionPane.showMessageDialog(this, "El campo de Refugio debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int shelterID = Integer.parseInt(shelterIDText.trim());
            String status = Objects.requireNonNull(healthStatusComboBox.getSelectedItem()).toString();


            boolean success = animalController.updateAnimal(animalEntity.getId(),name, age, LocalDate.now(),status,specie, shelterID);
            if(success){
                JOptionPane.showMessageDialog(this, "Animal agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
                AnimalsPanel animalsPanel = this.owner.getAnimalsPanel();
                animalsPanel.refreshTable();
                this.owner.showNewPanel(animalsPanel);
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

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.fillRoundRect(mainPanel.getX(), 20, mainPanel.getWidth(), 60, 20, 20);

        g2d.setColor(Color.black);

        //title positioning
        Font tittleFont = FontUtil.loadFont(24, "Inter_Light");
        String tittleText = "Modificar Animal";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);
    }
}
