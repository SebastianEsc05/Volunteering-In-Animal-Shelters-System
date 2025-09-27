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

public class AppointmentInfoPanel extends EntityPanel {
    private IAppointmentController appoimentController;
    private UpdateAppointmentDialog updateAppoimentDialog;
    private JPanel detailPanel;
    private StatusLabel statusLabel;
    private Button updateBtn;
    private Button deleteBtn;
    private int appointmentId;

    public AppointmentInfoPanel(MainFrame owner, int id) {
        super(owner);
        appoimentController = new AppointmentController();
        updateAppoimentDialog = new UpdateAppointmentDialog();
        appointmentId = id;
        statusLabel = new StatusLabel(appoimentController.readAppoiment(appointmentId).getStatus());
        detailPanel = new JPanel();
        updateBtn = new Button("Editar asignacion", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
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
            if(ans == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "Asignacion Eliminada");
                appoimentController.deleteAppoiment(appointmentId);
                this.owner.showNewPanel(this.owner.getAppointmentPanel());
            }


        });
        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getAppointmentPanel());
        });
    }

    private void setAppointmentId(int id ) {
        this.appointmentId = appointmentId;

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
                String sideBarTittle = "Menu";
                FontMetrics metricsTittleText = g2d.getFontMetrics(sideBarTextFont);
                int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(sideBarTittle)) / 2;
                g2d.setFont(sideBarTextFont);
                g2d.drawString(sideBarTittle, xTittleText, sideBarPanel.getY()+25);

                g2d.dispose();
            }
        };
        sideBarPanel.add(detailPanel);
        sideBarPanel.add(updateBtn);
        sideBarPanel.add(deleteBtn);

        tablePanel.add(backBtn);
        tablePanel.add(statusLabel);

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
