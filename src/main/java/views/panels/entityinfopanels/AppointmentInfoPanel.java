package views.panels.entityinfopanels;

import controllers.AppointmentController;
import interfaces.controller.IAppointmentController;
import views.dialogs.UpdateEntityDialogs.UpdateAppointmentDialog;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.entitypanels.AppointmentsPanel;
import views.panels.entitypanels.EntityPanel;
import views.styles.Button;
import views.styles.FontUtil;
import views.styles.StatusLabel;
import views.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentInfoPanel extends EntityPanel {
    private IAppointmentController appointmentController;
    private UpdateAppointmentDialog updateAppoimentDialog;
    private JPanel buttonsPanel;
    private JPanel infoPanel;
    private JLabel headerLabel;
    private JLabel dateLabel;
    private JLabel dateHeader;
    private StatusLabel statusLabel;
    private Button updateBtn;
    private Button deleteBtn;
    private int appointmentId;

    public AppointmentInfoPanel(MainFrame owner, int id) {
        super(owner);
        appointmentController = new AppointmentController();
        updateAppoimentDialog = new UpdateAppointmentDialog();
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setPreferredSize(new Dimension(500, 60));
        buttonsPanel.setOpaque(false);
        appointmentId = id;

        //Id Header
        headerLabel = new JLabel(String.valueOf(id));
        headerLabel.setFont(FontUtil.loadFont( 24, "Inter_Light"));
        //Date booked header
        LocalDate date = appointmentController.readAppoiment(appointmentId).getDateBooked();
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateLabel = new JLabel(formatDate);
        dateLabel.setFont(FontUtil.loadFont( 16, "Inter_18pt-ExtraLight"));
        //Status header
        statusLabel = new StatusLabel(appointmentController.readAppoiment(appointmentId).getStatus());
        updateBtn = new Button("Editar información", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        deleteBtn = new Button("Eliminar", 120, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_DELETE, Style.COLOR_BTN_DELETE_HOVER);
        addComponents();

        //ActionListeners
        updateBtn.addActionListener(e -> {

        });

        deleteBtn.addActionListener(e -> {
            Object[] opciones= {"Si", "Cancelar"};
            int ans = JOptionPane.showOptionDialog(null,
                    "Desea eliminar esta Asignacion?",
                    "Eliminar Asignacion",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);
            if(ans == 0){
                JOptionPane.showMessageDialog(null, "Asignacion Eliminada");
                appointmentController.deleteAppoiment(appointmentId);
                this.owner.showNewPanel(this.owner.getAppointmentPanel());
            }
            AppointmentsPanel appointmentsPanel = this.owner.getAppointmentPanel();
            appointmentsPanel.refreshTable();
            this.owner.showNewPanel(appointmentsPanel);

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
        buttonsPanel.add(backBtn);

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
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        infoPanel.add(headerLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(180, 10)));
        infoPanel.add(statusLabel);

        tablePanel.add(buttonsPanel);
        tablePanel.add(infoPanel);

        //East Panel
        eastPanel.add(this.sideBarPanel);
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
