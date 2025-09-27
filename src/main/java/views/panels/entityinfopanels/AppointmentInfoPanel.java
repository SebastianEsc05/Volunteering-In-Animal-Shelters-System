package views.panels.entityinfopanels;

import controllers.AppointmentController;
import interfaces.controller.IAppointmentController;
import views.dialogs.UpdateEntityDialogs.UpdateAppointmentDialog;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.entitypanels.EntityPanel;
import views.styles.Button;
import views.styles.FontUtil;
import views.styles.StatusLabel;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AppointmentInfoPanel extends EntityPanel {
    private IAppointmentController appointmentController;
    private UpdateAppointmentDialog updateAppoimentDialog;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JLabel headerLabel;
    private JLabel dateHeader;
    private StatusLabel statusLabel;
    private Button updateBtn;
    private Button deleteBtn;
    private int appointmentId;

    public AppointmentInfoPanel(MainFrame owner, int id) {
        super(owner);
        appointmentController = new AppointmentController();
        updateAppoimentDialog = new UpdateAppointmentDialog();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setPreferredSize(new Dimension(500, 60));
        buttonPanel.setOpaque(false);
        appointmentId = id;
        LocalDate date = appointmentController.readAppoiment(appointmentId).getDateBooked();
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        headerLabel = new JLabel(String.valueOf(id)+" "+ formatDate);
        headerLabel.setFont(FontUtil.loadFont( 20, "Inter_18pt-ExtraLight"));
        statusLabel = new StatusLabel(appointmentController.readAppoiment(appointmentId).getStatus());
        updateBtn = new Button("Editar asignacion", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        deleteBtn = new Button("Eliminar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);
        addComponents();

        //ActionListeners
        updateBtn.addActionListener(e -> {

        });
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getAppointmentPanel());
        });
    }

    @Override
    public void addComponents() {
        //SideBarPanel
        this.sideBarPanel = new SidebarPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.black);

                //SideBar tittles position
                Font sideBarTextFont = FontUtil.loadFont(16, "Inter_Regular");
                String sideBarTittle = "Gestionar";
                FontMetrics metricsTittleText = g2d.getFontMetrics(sideBarTextFont);
                int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(sideBarTittle)) / 2;
                g2d.setFont(sideBarTextFont);
                g2d.drawString(sideBarTittle, xTittleText, sideBarPanel.getY()+25);

                g2d.dispose();
            }
        };
        sideBarPanel.add(updateBtn);
        sideBarPanel.add(deleteBtn);

        //InfoPanel
        infoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);

                g2d.setStroke(new BasicStroke(3));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawLine(25, 45, getWidth()-25, 45);

                g2d.dispose();
            }
        };
        infoPanel.setOpaque(false);
        infoPanel.setPreferredSize(new Dimension(500, 290));

        buttonPanel.add(backBtn);
        infoPanel.add(headerLabel, BorderLayout.WEST);
        infoPanel.add(statusLabel, BorderLayout.EAST);

        tablePanel.add(buttonPanel);
        tablePanel.add(infoPanel);

        //East Panel
        this.eastPanel.add(this.sideBarPanel);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Asignacion";
        FontMetrics metricsTittleText = g2d.getFontMetrics(tittleFont);
        int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittleText, sideBarPanel.getY()+57);

    }
}
