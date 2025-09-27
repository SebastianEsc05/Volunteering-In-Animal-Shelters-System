package views.styles;

import javax.swing.*;
import java.awt.*;

public class StatusLabel extends JLabel {
    private Status status;

    public StatusLabel(String estado) {
        super("", SwingConstants.CENTER);
        switch (estado) {
            case "completada" -> this.status = Status.COMPLETADA;
            case "pendiente" -> this.status = Status.PENDIENTE;
            case "cancelada" -> this.status = Status.CANCELADA;
            default -> throw new IllegalArgumentException("Estado no válido: " + estado);
        }
        setText(status.getTexto());
        setFont(FontUtil.loadFont( 14, "Inter_SemiBold"));
        setForeground(Color.WHITE);
        setOpaque(false);
        setPreferredSize(new Dimension(100, 20));
    }

    public void setStatus(Status estado) {
        this.status = estado;
        setText(estado.getTexto());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(status.getColor());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);

        super.paintComponent(g2d);
        g2d.dispose();
    }

    //ENUM "status" CLASS
    public enum Status {
        COMPLETADA("Completada", new Color(33, 150, 243)),
        PENDIENTE("Pendiente", new Color(244, 209, 34)),
        CANCELADA("Cancelada", new Color(244, 67, 54));

        private final String texto;
        private final Color color;

        Status(String texto, Color color) {
            this.texto = texto;
            this.color = color;
        }

        public String getTexto() {
            return texto;
        }

        public Color getColor() {
            return color;
        }
    }

}
