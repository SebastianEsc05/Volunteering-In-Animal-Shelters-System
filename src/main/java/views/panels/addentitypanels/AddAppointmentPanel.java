package views.panels.addentitypanels;

import controllers.AppointmentController;
import controllers.ControllerException;
import views.frames.MainFrame;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.FormattedDateField;
import views.styles.textfields.TextAreaCustom;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AddAppointmentPanel extends AddEntityPanel {
    private JPanel activityPanel;
    private FormattedDateField dateField;
    private TxtFieldPh animalTextField;
    private TxtFieldPh volunteerTextField;
    private TxtFieldPh activityTextField;
    private TextAreaCustom commentsTextArea;
    private JCheckBox animalCheckBox;

    public AddAppointmentPanel(MainFrame owner) {
        super(owner);
        this.activityPanel = new JPanel();
        this.activityPanel.setOpaque(false);
        this.activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        this.activityPanel.setPreferredSize(new Dimension(300, 70));
        this.dateField = new FormattedDateField();
        this.animalTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        this.volunteerTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        this.activityTextField = new TxtFieldPh(" ", 200, 30, 15, 15);
        this.commentsTextArea = new TextAreaCustom(4, 20);
        animalCheckBox = new JCheckBox("Involucra animal");
        animalCheckBox.setFont(FontUtil.loadFont(12, "Inter_Light"));

        //Panels
        this.componentsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.black);

                //Labels
                Font SubTittleFont = FontUtil.loadFont(16, "Inter_Light");
                g2d.setFont(SubTittleFont);
                g2d.drawString("Fecha", 50, 45);
                g2d.drawString("Animal", 170, 45);
                g2d.drawString("Voluntario", 270, 45);

                String activityText = "Actividad";
                FontMetrics metricsActivity = g2d.getFontMetrics(SubTittleFont);
                int xActivityText = (getWidth() - metricsActivity.stringWidth(activityText)) / 2;
                g2d.drawString(activityText, xActivityText, 135);

                String detailsText = "Observaciones";
                FontMetrics metricsDetails = g2d.getFontMetrics(SubTittleFont);
                int xDetailsText = (getWidth() - metricsDetails.stringWidth(detailsText)) / 2;
                g2d.drawString(detailsText, xDetailsText, 270);

                g2d.dispose();
            }
        };

        this.componentsPanel.setOpaque(false);
        this.componentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 60));
        this.componentsPanel.setPreferredSize(new Dimension(366, 400));
        this.componentsPanel.setMaximumSize(new Dimension(366, 400));
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setPreferredSize(new Dimension(400, 100));

        JScrollPane textAreascroll = new JScrollPane(this.commentsTextArea);
        textAreascroll.setBorder(null);
        textAreascroll.setAlignmentX(LEFT_ALIGNMENT);

        //ActionListeners
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getAppoimentPanel());
        });

        addBtn.addActionListener(e -> addAppointment());

        //Add components
        this.componentsPanel.add(this.dateField);
        this.componentsPanel.add(this.animalTextField);
        this.componentsPanel.add(this.volunteerTextField);
        this.activityPanel.add(this.activityTextField);
        this.activityPanel.add(this.animalCheckBox);
        this.componentsPanel.add(this.activityPanel);
        this.componentsPanel.add(textAreascroll);
        this.buttonsPanel.add(this.backBtn);
        this.buttonsPanel.add(this.addBtn);
        this.mainPanel.add(Box.createVerticalStrut(110));
        this.mainPanel.add(this.componentsPanel);
        this.mainPanel.add(Box.createVerticalStrut(10));
        this.mainPanel.add(this.buttonsPanel);
        add(this.mainPanel);
    }

    public void addAppointment() {
        try {
            //Get data from textFields
            Integer animalId = null;
            String animalIdText = animalTextField.getText().trim();

            if(animalIdText.isEmpty()){
                if(animalCheckBox.isSelected()){
                    JOptionPane.showMessageDialog(this, "Debe ingresar un ID de animal si la actividad involucra un animal", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!animalIdText.isEmpty()) {
                if (animalIdText.matches("\\d+")) {
                    animalId = Integer.parseInt(animalIdText);
                }
                if(!animalCheckBox.isSelected()){
                    JOptionPane.showMessageDialog(this, "El ID de animal se ha proporcionado pero la casilla 'Involucra animal' no está seleccionada.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                    JOptionPane.showMessageDialog(this, "El ID de animal debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            int volunteerId = 0;
            if (!volunteerTextField.getText().trim().isEmpty()) {
                volunteerId = Integer.parseInt(volunteerTextField.getText());
            }

            String activity = activityTextField.getText();
            boolean animalCheck = animalCheckBox.isSelected();
            String comments = commentsTextArea.getText();
            if (activity.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de actividad no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Actual date from today
            LocalDateTime todayDate = LocalDateTime.now();

            //Get Date booked from dateField
            LocalDateTime dateBooked = null;
            if (dateField.getDate() != null) {
                dateBooked = dateField.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                if(dateBooked.isAfter(todayDate)){
                    JOptionPane.showMessageDialog(this, "La fecha reservada no puede ser anterior a la fecha actual", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            AppointmentController appoimentController = new AppointmentController();
            boolean success = appoimentController.addAppoiment(todayDate, dateBooked, animalId, volunteerId, activity, comments,"pendiente", animalCheck);
            if (success){
                JOptionPane.showMessageDialog(this, "Asignacion creada con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
                resetFields();


            }else{
                JOptionPane.showMessageDialog(this,  "Ocurrio un error al guardar la asignacion",  "Error",  JOptionPane.ERROR_MESSAGE);

            }

        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetFields() {
        this.animalTextField.setText("");
        this.volunteerTextField.setText("");
        this.activityTextField.setText("");
        this.commentsTextArea.setText("");
        this.dateField.setValue(null);
        this.animalCheckBox.setSelected(false);
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
        String tittleText = "Nueva Asignación";
        FontMetrics metricsTittle = g2d.getFontMetrics(tittleFont);
        int xTittle = (getWidth() - metricsTittle.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittle, 60);


    }
}
