package views.panels.addentitypanels;

import controllers.AnimalController;
import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import interfaces.controller.IAnimalController;
import views.frames.MainFrame;
import views.panels.entitypanels.AnimalsPanel;
import views.panels.entitypanels.AppointmentsPanel;
import views.styles.ComboBoxCustom;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.FormattedDateField;
import views.styles.textfields.TextAreaCustom;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

public class AddAnimalPanel extends AddEntityPanel{
    protected IAnimalController animalController;
    protected JPanel activityPanel;
    protected TxtFieldPh nameTextField;
    protected TxtFieldPh speciesTextField;
    protected TxtFieldPh shelterTextField;
    protected ComboBoxCustom healthStatusComboBox;
    protected TxtFieldPh ageTextField;
    protected TextAreaCustom commentsTextArea;
    protected JScrollPane textAreaScroll;


    public AddAnimalPanel(MainFrame owner) {
        super(owner);
        this.animalController = new AnimalController();
        this.activityPanel = new JPanel();
        this.activityPanel.setOpaque(false);
        this.activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        this.activityPanel.setPreferredSize(new Dimension(300, 70));
        this.ageTextField = new TxtFieldPh("Edad", 5, 100, 30, 15, 15);
        this.nameTextField = new TxtFieldPh("Nombre", 50, 100, 30, 15, 15);
        this.speciesTextField = new TxtFieldPh("Especie", 50, 100, 30, 15, 15);
        this.healthStatusComboBox = new ComboBoxCustom("health");
        this.shelterTextField = new TxtFieldPh("Refugio", 50, 100, 30, 15, 15);
        this.commentsTextArea = new TextAreaCustom(4,20);

        this.componentsPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.setColor(Color.black);

                Font SubTittleFont = FontUtil.loadFont(16,"Inter_Light");
                g2d.setFont(SubTittleFont);
                g2d.drawString("Edad",50,45);
                g2d.drawString("Especie",50,95);
                g2d.drawString("Nombre",50,145);
                g2d.drawString("Estado de Salud",50,195);
                g2d.drawString("Refugio",50,245);


                String detailsText = "Observaciones";
                FontMetrics metricsDetails = g2d.getFontMetrics(SubTittleFont);
                int xDetailsText = (getWidth() - metricsDetails.stringWidth(detailsText)) / 2;
                g2d.drawString(detailsText, xDetailsText, 280);

                g2d.dispose();
            }
        };
        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(null);
        this.componentsPanel.setPreferredSize(new Dimension(366,400));
        this.componentsPanel.setMaximumSize(new Dimension(366, 400));
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(400,100));

        textAreaScroll = new JScrollPane(this.commentsTextArea);
        textAreaScroll.setBorder(null);
        textAreaScroll.setAlignmentX(LEFT_ALIGNMENT);

        addBtn.addActionListener(e->{
            addAnimal();
        });
        backBtn.addActionListener(e->{
            this.owner.showNewPanel(this.owner.getAnimalsPanel());
            resetFields();
        });
        addComponents();
    }

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

        this.buttonsPanel.add(this.backBtn);
        this.buttonsPanel.add(this.addBtn);
        this.componentsPanel.add(this.activityPanel);
        this.componentsPanel.add(commentsTextArea);
        this.commentsTextArea.setBounds(15,285,340,100);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(buttonsPanel);
        add(this.mainPanel);
    }

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


        boolean success = animalController.addAnimal(name, age,LocalDate.now(),status,specie, shelterID);
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

    public void resetFields(){
        this.nameTextField.setText("");
        this.speciesTextField.setText("");
        this.shelterTextField.setText("");
        this.ageTextField.setText("");
        this.commentsTextArea.setText("");
        this.healthStatusComboBox.setSelectedIndex(0);
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
        String tittleText = "Nuevo Animal";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);
    }
}
