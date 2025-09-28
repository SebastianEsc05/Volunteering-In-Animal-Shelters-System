package views.panels.addentitypanels;

import controllers.AppointmentController;
import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import views.frames.MainFrame;
import views.panels.entitypanels.AppointmentsPanel;
import views.styles.FontUtil;
import views.styles.Style;
import views.styles.textfields.FormattedDateField;
import views.styles.textfields.TextAreaCustom;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AddAppointmentPanel extends AddEntityPanel {
    protected AppointmentController appointmentController;
    protected JPanel activityPanel;
    protected FormattedDateField dateField;
    protected TxtFieldPh animalTextField;
    protected TxtFieldPh volunteerTextField;
    protected TxtFieldPh activityTextField;
    protected TextAreaCustom commentsTextArea;
    protected JScrollPane textAreascroll;
    protected JCheckBox animalCheckBox;

    public AddAppointmentPanel(MainFrame owner) {
        super(owner);
        appointmentController = new AppointmentController();
        activityPanel = new JPanel();
        activityPanel.setOpaque(false);
        activityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        activityPanel.setPreferredSize(new Dimension(300, 70));
        dateField = new FormattedDateField();
        animalTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        volunteerTextField = new TxtFieldPh("id", 5, 100, 30, 15, 15);
        activityTextField = new TxtFieldPh(" ", 200, 30, 15, 15);
        commentsTextArea = new TextAreaCustom(4, 20);
        animalCheckBox = new JCheckBox("Involucra animal");
        animalCheckBox.setFont(FontUtil.loadFont(12, "Inter_Light"));

        //Panels
        componentsPanel = new JPanel() {
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

        textAreascroll = new JScrollPane(this.commentsTextArea);
        textAreascroll.setBorder(null);
        textAreascroll.setAlignmentX(LEFT_ALIGNMENT);

        //ActionListeners
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getAppointmentPanel());
            resetFields();
        });

        addBtn.addActionListener(e -> addAppointment());
        addComponents();
    }

    public void addComponents(){
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

            boolean success = appointmentController.addAppoiment(todayDate, dateBooked, animalId, volunteerId, activity, comments, "pendiente", animalCheck);
            if (success) {
                JOptionPane.showMessageDialog(this, "Asignacion creada con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
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
